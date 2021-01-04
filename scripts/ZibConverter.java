package nl.openehr;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ZibConverter {

    private static final String HEADER = "ZIB_Id,ZIB_Naam,ZIB_Type,ZIB_Card,ZIB_DefCode,ZIB_Verwijzing,openEHR_Path,openEHR_Naam,openEHR_Type,openEHR_Card,openEHR_Term,Commentaar";

    private static final int ZIB_Id_COL = 11;
    private static final int ZIB_Naam_COL_MIN = 1;
    private static final int ZIB_Naam_COL_MAX = 6;
    private static final int ZIB_Type_COL = 8;
    private static final int ZIB_Type_COL_ALT = 10;
    private static final int ZIB_Card_COL = 9;
    private static final int ZIB_DefCode_COL = 13;
    private static final int ZIB_Verwijzing_COL = 14;

    private Workbook workbook;

    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            throw new IllegalArgumentException("Requires two arguments: inputdir outputdir");
        }

        String inputdir = args[0];
        String outputdir = args[1];

        File dir = new File(inputdir);

        for(File file : dir.listFiles((dirx, filename) -> filename.startsWith("nl.zorg") && filename.endsWith(".xlsx"))) {
            System.out.println(file.getName());
            ZibConverter importer = new ZibConverter();
            importer.open(file);
            importer.convertZibToCsv(outputdir);
            importer.close();
        }
    }

    private void open(File file) throws IOException {
        workbook = WorkbookFactory.create(file, null, true);
    }

    private void close() throws IOException {
        workbook.close();
        workbook = null;
    }

    private void convertZibToCsv(String outputdir) throws FileNotFoundException {
        Sheet sheet = workbook.getSheet("Data");
        Objects.requireNonNull(sheet);

        int rownum = 2;

        String zibnaam = getCellValue(sheet.getRow(rownum), ZIB_Naam_COL_MIN).toLowerCase(Locale.ROOT);

        try(PrintStream out = new PrintStream(new FileOutputStream(outputdir + "/" + zibnaam + ".csv"))) {

            out.println(HEADER);

            while (true) {
                Row row = sheet.getRow(rownum);
                if (row == null) {
                    break;
                }

                int mergeHeight = getMergeHeight(row.getCell(1));

                List<Row> rows = new ArrayList<>(mergeHeight);
                for(int i = 0; i < mergeHeight; i++) {
                    Row subRow = sheet.getRow(rownum + i);
                    Objects.requireNonNull(subRow, "Row " + (rownum + i) + " is null");
                    rows.add(subRow);
                }

                String ZIB_Id = getCellValue(row, ZIB_Id_COL);
                String ZIB_Naam = getZIB_Naam(row);
                String ZIB_Type = getCellValue(rows, ZIB_Type_COL);
                if (ZIB_Type == null || ZIB_Type.isEmpty()) {
                    ZIB_Type = getCellValue(rows, ZIB_Type_COL_ALT);
                }

                String ZIB_Card = getCellValue(rows, ZIB_Card_COL);
                String ZIB_DefCode = getCellValue(rows, ZIB_DefCode_COL);
                String ZIB_Verwijzing = getCellValue(rows, ZIB_Verwijzing_COL);

                out.println(ZIB_Id + "," + ZIB_Naam + "," + escape(ZIB_Type) + "," + ZIB_Card + "," + ZIB_DefCode + "," + ZIB_Verwijzing + ",,,,,,");
                rownum += mergeHeight;
            }
        }
    }

    private int getMergeHeight(Cell cell) {
        for(CellRangeAddress region : cell.getSheet().getMergedRegions()) {
            if(region.isInRange(cell)) {
                return region.getLastRow() - region.getFirstRow() + 1;
            }
        }
        return 1;
    }

    private String getZIB_Naam(Row row) {
        String ZIB_Naam = null;

        int cellnum = ZIB_Naam_COL_MIN;

        while((ZIB_Naam == null || ZIB_Naam.isEmpty()) && cellnum <= ZIB_Naam_COL_MAX) {
            ZIB_Naam = getCellValue(row, cellnum);
            cellnum++;
        }

        return ZIB_Naam;
    }

    private String getCellValue(List<Row> rows, int cellnum) {
        return rows.stream().map(row -> getCellValue(row, cellnum)).filter(value -> value != null && !value.isEmpty()).collect(Collectors.joining("; "));
    }

    private String getCellValue(Row row, int cellnum) {
        Cell cell = row.getCell(cellnum);
        Objects.requireNonNull(cell, "Cell " + cellnum + " of row " + row.getRowNum() + " is null");
        return cell.getStringCellValue();
    }

    private String escape(String value) {
        if(value.contains(",")) {
            return "\"" + value + "\"";
        } else {
            return value;
        }
    }
}
