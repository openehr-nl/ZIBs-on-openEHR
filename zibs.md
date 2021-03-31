---
title: ZIBs
layout: default
nav_order: 2
search_exclude: true

data: 
    Administratief:
        - Betaler-v3.1.1
        - Contact-v4.0.1
        - Contactpersoon-v3.4
        - Patient-v3.2
        - Zorgaanbieder-v3.4
        - ZorgTeam-v1.0
        - Zorgverlener-v3.5
    Behandeling:
        - BehandelAanwijzing2-v1.0
        - Behandeldoel-v3.2
        - UitkomstVanZorg-v3.2
        - VerpleegkundigeInterventie-v3.2
        - Verrichting-v5.2
        - VrijheidsbeperkendeInterventie-v1.0
        - ZorgAfspraak-v1.0
    Klinische context:
        - Alert-v4.1
        - AllergieIntolerantie-v3.3
        - Blaasfunctie-v3.2
        - Brandwond-v3.4
        - Darmfunctie-v3.1.1
        - DecubitusWond-v3.4
        - FunctieHoren-v3.2
        - FunctieZien-v3.1
        - FunctioneleOfMentaleStatus-v3.2
        - Huidaandoening-v3.3
        - Infuus-v3.3
        - MedischHulpmiddel-v3.3.1
        - OntwikkelingKind-v1.2
        - Patientbespreking-v1.0
        - Pijnkenmerken-v1.0
        - Probleem-v4.4
        - SOEPVerslag-v1.0
        - SondeSysteem-v3.3
        - Stoma-v3.3
        - Vaccinatie-v4.0
        - Voedingsadvies-v3.2
        - VoedingspatroonZuigeling-v1.1
        - Wond-v3.3
        - ZorgEpisode-v1.0
        - Zwangerschap-v4.0
    Medicatie:
        - MedicatieContraIndicatie-v1.0
        - MedicatieGebruik2-v1.1.1
        - MedicatieToediening2-v1.1.1
        - Medicatieafspraak-v1.2
        - Medicatieverstrekking-v2.0.2
        - Toedieningsafspraak-v1.0.3
        - Verstrekkingsverzoek-v1.0.3
    Metingen:
        - Ademhaling-v3.2
        - Bloeddruk-v3.2.1
        - DAS-v1.0
        - Hartfrequentie-v3.4
        - LaboratoriumUitslag-v4.6
        - Lichaamsgewicht-v3.2
        - Lichaamslengte-v3.1.1
        - Lichaamstemperatuur-v3.1.2
        - O2Saturatie-v3.1
        - Polsfrequentie-v3.3
        - Refractie-v1.0
        - Schedelomvang-v1.3
        - TekstUitslag-v4.4
        - Visus-v1.0
        - Vochtbalans-v1.0.1
    Patiënten:
        - AlcoholGebruik-v3.2
        - BurgerlijkeStaat-v3.1
        - DrugsGebruik-v3.3
        - Familieanamnese-v3.1
        - Gezinssituatie-v3.2
        - GezinssituatieKind-v1.2
        - HulpVanAnderen-v3.01
        - JuridischeSituatie-v2.0
        - Levensovertuiging-v3.2
        - Nationaliteit-v3.0
        - Opleiding-v3.2
        - ParticipatieInMaatschappij-v3.1
        - Taalvaardigheid-v3.2
        - TabakGebruik-v3.2
        - Wilsverklaring-v3.1.1
        - Woonsituatie-v3.3
        - Ziektebeleving-v3.1
    Scorelijsten:
        - ApgarScore-v1.0.1
        - BarthelIndex-v3.1
        - ChecklistPijngedrag-v1.1
        - ComfortScore-v1.1
        - DOSScore-v1.0
        - FLACCpijnScore-v1.1
        - GlasgowComaScale-v3.2
        - MUSTScore-v3.1
        - Pijnscore-v4.0
        - SNAQ65+Score-v1.2
        - SNAQScore-v3.2
        - SNAQrcScore-v1.1
        - StrongKidsScore-v1.1
        - TNMTumorClassificatie-v1.0
    Subbouwstenen:
        - Adresgegevens-v1.1
        - AnatomischeLocatie-v1.0
        - Bereik-v1.0.1
        - Contactgegevens-v1.2
        - FarmaceutischProduct-v2.1.2
        - GebruiksInstructie-v1.2.1
        - Naamgegevens-v1.1
        - TijdsInterval-v1.0
    Zelfzorg:
        - Mobiliteit-v3.3
        - VermogenTotDrinken-v3.1.1
        - VermogenTotEten-v3.1.1
        - VermogenTotMondverzorging-v3.1
        - VermogenTotToiletgang-v3.1.1
        - VermogenTotUiterlijkeVerzorging-v1.0.1
        - VermogenTotVerpleegtechnischeHandelingen-v1.0.1
        - VermogenTotZelfstandigMedicatiegebruik-v1.0.1
        - VermogenTotZichKleden-v3.1.1
        - VermogenTotZichWassen-v3.1.1
---

# ZIB's 

Zorginformatiebouwstenen worden gebruikt om inhoudelijke (niet technische) afspraken vast te leggen ten behoeve van het standaardiseren van informatie, die gebruikt wordt in het zorgproces. 
Meer informatie zijn beschikbaar bij de ['Registratie aan de bron'](https://www.registratieaandebron.nl/over-het-programma) programma.

Om de mapping te realiseren, gebruiken we de huidige [publicatie 2020(NL)](https://zibs.nl/wiki/ZIB_Publicatie_2020(NL)).
Hieronder een overzicht van alle gedefineerde zibs en ook degene die al in ontwikkeling zijn:

{% assign mappingsPages = site.html_pages | where: 'parent', 'Mappings' %}

{% for categoryInfo in page.data %}
{% assign category=categoryInfo[0] %}
{% assign zibs=categoryInfo[1] | sort %}
### groep: {{ category }}, aantal: {{ zibs | size }} 
<table>
    {% tablerow zib in zibs cols:3 %}
        {% assign zibMapping = mappingsPages | where: "zib", zib | first %}
        {% if zibMapping %}
            <a href="{{ zibMapping.url }}">{{ zibMapping.zib }}</a>{% include status.html status=zibMapping.status.globaal %}
        {% else %}
            {{ zib }} 
        {% endif %}
    {% endtablerow %}
</table>

{% endfor %}
