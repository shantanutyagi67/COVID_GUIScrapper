# Notes for Developers
<edge cases> 	1. Reset button will execute if you click on search box and then click on reset button.
		2. Typing "Type Country Name..." in search and then clicking reset buton wont result in a reset give a valid counntry 		was fetched before doing this.
		3. reseting search box text back to "Type Country Name..." in some cases needs to be taken care of as the reset button 				works by identifying this text.
<edge cases resolved> 	1. Successive fetching of same countries wont take place.
			2. Successive reset wont take place.
			3. empty fetching wont take place.
			4. invalid country code do not result in fetching.
			 
# COVID_Scrapper Manual for Users
Code : src\main\java\com\Covid
1. Uses jsoup to scrap data and flag image from https://www.worldometers.info/coronavirus/
2. Current global data can be seen.
3. Type the name of the country in search (case insensitive for names) from the keywords mentioned at the end.
4. Click on fetch button to fetch the data from the URL and display in the GUI.
5. Reset button will take you back to the current global data.
6. Country flags will be downloaded in the relative project path.
7. The flag images are deleted right after they are downloaded.

VALID COUNTRY NAMES(case insensitive) :
US
Brazil
Russia
Spain
UK
India
Italy
Peru
Germany
Iran
Turkey
France
Chile
Mexico
Saudi-Arabia
Pakistan
Canada
China
Qatar
Bangladesh
Belgium
South-Africa
Belarus
Netherlands
Sweden
Ecuador
United-Arab-Emirates
Colombia
Singapore
Portugal
Egypt
Kuwait
Indonesia
Switzerland
Ukraine
Poland
Ireland
Argentina
Philippines
Afghanistan
Romania
Dominican-Republic
Israel
Oman
Japan
Austria
Panama
Bahrain
Bolivia
Iraq
Armenia
Kazakhstan
Nigeria
Denmark
Serbia
south-korea
Algeria
Ghana
Moldova
Czechia
Norway
Malaysia
Morocco
Cameroon
Azerbaijan
Australia
Guatemala
Finland
Honduras
Sudan
Tajikistan
Uzbekistan
Senegal
Djibouti
Guinea
democratic-republic-of-the-congo
Luxembourg
Hungary
Nepal
cote-d-ivoire
Haiti
macedonia
Thailand
El-Salvador
Gabon
Greece
Kenya
Bulgaria
bosnia-and-herzegovina
Venezuela
Somalia
Croatia
Cuba
Ethiopia
Mayotte
Kyrgyzstan
Estonia
Maldives
Sri-Lanka
central-african-republic
Iceland
Lithuania
South-Sudan
Mali
Slovakia
New-Zealand
Slovenia
Guinea-Bissau
Lebanon
Costa Rica
equatorial-guinea
Albania
Zambia
Paraguay
Nicaragua
china-hong-kong-sar
Madagascar
Latvia
Tunisia
Mauritania
Sierra-Leone
Niger
Cyprus
Burkina-Faso
Andorra
Uruguay
Chad
Jordan
Georgia
French-Guiana
Diamond-Princess
San-Marino
Congo
Uganda
Malta
Jamaica
Cabo-Verde
Channel-Islands
Sao-Tome-and-Principe
Tanzania
Togo
Yemen
Réunion
Palestine
Rwanda
Taiwan
Malawi
Mozambique
Liberia
Eswatini
Mauritius
Isle-of-Man
Vietnam
Montenegro
Benin
Zimbabwe
Libya
Myanmar
Martinique
Mongolia
Faeroe-Islands
Gibraltar
Guadeloupe
Cayman-Islands
Guyana
Syria
Bermuda
Brunei
Comoros
Suriname
Cambodia
Trinidad-and-Tobago
Bahamas
Aruba
Monaco
Barbados
Angola
Burundi
Liechtenstein
Sint-Maarten
French-Polynesia
Bhutan
Macao
Botswana
Saint-Martin
Eritrea
Namibia
Gambia
saint-vincent-and-the-grenadines
Antigua-and-Barbuda
Timor-Leste
Grenada
Curaçao
New-Caledonia
Belize
Laos
Saint-Lucia
Dominica
Fiji
Saint-Kitts-and-Nevis
Falkland-Islands
Greenland
turks-and-caicos-islands
holy-see
Montserrat
Seychelles
MS-Zaandam
Western-Sahara
British-Virgin-Islands
Papua-New-Guinea
Caribbean-Netherlands
saint-barthelemy
Lesotho
Anguilla
saint-pierre-and-miquelon
