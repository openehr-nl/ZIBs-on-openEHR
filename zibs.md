---
title: ZIBs
layout: default
nav_order: 2

data: 
    Administratief:
        - Betaler-v3.1
        - Contact-v4.0
        - Contactpersoon-v3.3
        - Patient-v3.1.1
        - Zorgaanbieder-v3.3
        - Zorgverlener-v3.4
    Basis elementen:
        - BasisElementen-v1.0.1
    Behandeling:
        - BehandelAanwijzing-v3.2
        - Behandeldoel-v3.1
        - UitkomstVanZorg-v3.1
        - VerpleegkundigeInterventie-v3.2
        - Verrichting-v5.1
        - VrijheidsbeperkendeMaatregelen-v3.2
    Klinische context:
        - Alert-v4.0
        - AllergieIntolerantie-v3.3
        - Blaasfunctie-v3.2
        - Brandwond-v3.3
        - Darmfunctie-v3.1.1
        - DecubitusWond-v3.3
        - FunctieHoren-v3.2
        - FunctieZien-v3.1
        - FunctioneleOfMentaleStatus-v3.1.1
        - Huidaandoening-v3.2
        - Infuus-v3.3
        - MedischHulpmiddel-v3.3
        - OntwikkelingKind-v1.2
        - Probleem-v4.3
        - SondeSysteem-v3.3
        - Stoma-v3.2	
        - Vaccinatie-v4.0	
        - Voedingsadvies-v3.2	
        - VoedingspatroonZuigeling-v1.0
        - Wond-v3.2
        - Zwangerschap-v3.1.1
    Medicatie:
        - MedicatieGebruik2-v1.1
        - MedicatieToediening2-v1.1
        - Medicatieafspraak-v1.1
        - Medicatieverstrekking-v2.0.1
        - Toedieningsafspraak-v1.0.2	
        - Verstrekkingsverzoek-v1.0.2	
    Metingen:
        - Ademhaling-v3.2
        - AlgemeneMeting-v3.0
        - Bloeddruk-v3.2
        - Hartfrequentie-v3.3
        - LaboratoriumUitslag-v4.5
        - Lichaamsgewicht-v3.1
        - Lichaamslengte-v3.1
        - Lichaamstemperatuur-v3.1.1
        - O2Saturatie-v3.1
        - Polsfrequentie-v3.3
        - Schedelomvang-v1.2	
        - TekstUitslag-v4.3	
        - Vochtbalans-v1.0
    Patiënten:
        - AlcoholGebruik-v3.1
        - BurgerlijkeStaat-v3.1
        - DrugsGebruik-v3.2
        - Familieanamnese-v3.1
        - Gezinssituatie-v3.2
        - GezinssituatieKind-v1.2
        - HulpVanAnderen-v3.01
        - JuridischeSituatie-v1.0
        - Levensovertuiging-v3.2
        - Nationaliteit-v3.0
        - Opleiding-v3.1
        - ParticipatieInMaatschappij-v3.1
        - Taalvaardigheid-v3.1	
        - TabakGebruik-v3.2	
        - Wilsverklaring-v3.1	
        - Woonsituatie-v3.2
        - Ziektebeleving-v3.1
    Scorelijsten:
        - ApgarScore-v1.0
        - BarthelIndex-v3.1
        - ChecklistPijngedrag-v1.1
        - ComfortScore-v1.1
        - DOSScore-v1.0
        - FLACCpijnScore-v1.1
        - GlasgowComaScale-v3.1
        - MUSTScore-v3.1
        - Pijnscore-v3.2
        - SNAQ65+Score-v1.2	
        - SNAQScore-v3.2	
        - SNAQrcScore-v1.1	
        - StrongKidsScore-v1.1
    Subbouwstenen:
        - Adresgegevens-v1.1
        - Bereik-v1.0.1
        - Contactgegevens-v1.1.1
        - FarmaceutischProduct-v2.1.1
        - GebruiksInstructie-v1.2
        - Naamgegevens-v1.0.1	
        - TijdsInterval-v1.0
    Zelfzorg:
        - Mobiliteit-v3.2
        - VermogenTotDrinken-v3.1
        - VermogenTotEten-v3.1
        - VermogenTotMondverzorging-v3.1
        - VermogenTotToiletgang-v3.1
        - VermogenTotUiterlijkeVerzorging-v1.0
        - VermogenTotVerpleegtechnischeHandelingen-v1.0
        - VermogenTotZelfstandigMedicatiegebruik-v1.0.1	
        - VermogenTotZichKleden-v3.1
        - VermogenTotZichWassen-v3.1
---

# ZIB's 

Zorginformatiebouwstenen worden gebruikt om inhoudelijke (niet technische) afspraken vast te leggen ten behoeve van het standaardiseren van informatie, die gebruikt wordt in het zorgproces. 
Meer informatie zijn beschikbaar bij de ['Registratie aan de bron'](https://www.registratieaandebron.nl/over-het-programma) programma.

Om de mapping te realiseren, gebruiken we de [huidige (pre)publicatie 2019-2(NL)](https://zibs.nl/wiki/ZIB_Publicatie_2019(NL)).
Hieronder een overzicht van alle gedefineerde zibs en ook degene die al in ontwikkeling zijn:

{% assign mappingsPages = site.pages | where: 'parent', 'Mappings' %}

{% for categoryInfo in page.data %}
{% assign category=categoryInfo[0] %}
{% assign zibs=categoryInfo[1] | sort %}
### groep: {{ category }}, aantal: {{ zibs | size }} 
<table>
    {% tablerow zib in zibs cols:3 %}
        {% assign found = false %}
        {% for mappingPage in mappingsPages %}
            {% if mappingPage.zib.name == zib %}
                {% assign found = mappingPage %}
            {% endif %}
        {% endfor %}
        {% if found %}
            <a href="{{ found.url }}">{{ found.zib.name }}</a>{% include status.html status=found.status.global %}
        {% else %}
            {{ zib }} 
        {% endif %}
    {% endtablerow %}
</table>

{% endfor %}
