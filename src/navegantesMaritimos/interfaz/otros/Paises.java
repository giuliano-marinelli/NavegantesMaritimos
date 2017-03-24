package navegantesMaritimos.interfaz.otros;

import java.util.Random;
import javax.swing.JComboBox;

/**
 * Esta clase permite realizar acciones con la clase pais, contiene todos los
 * paises del mundo con su respectivo alias. Sirve para llenar las combo box de
 * seleccion de pais, para obtener el alias de un respectivo pais y tambien
 * puede devolver un pais aleatorio.
 */
public class Paises {

    //Arreglo que contiene a todos los paises con sus alias.
    private static final Pais[] PAISES
            = {
                new Pais("AF", "Afganistán"),
                new Pais("AL", "Albania"),
                new Pais("DE", "Alemania"),
                new Pais("AD", "Andorra"),
                new Pais("AO", "Angola"),
                new Pais("AG", "Antigua y Barbuda"),
                new Pais("SA", "Arabia Saudí"),
                new Pais("DZ", "Argelia"),
                new Pais("AR", "Argentina"),
                new Pais("AM", "Armenia"),
                new Pais("AU", "Australia"),
                new Pais("AT", "Austria"),
                new Pais("AZ", "Azerbaiyán"),
                new Pais("BS", "Bahamas"),
                new Pais("BH", "Bahrein"),
                new Pais("BD", "Bangladesh"),
                new Pais("BB", "Barbados"),
                new Pais("BE", "Bélgica"),
                new Pais("BZ", "Belice"),
                new Pais("BJ", "Benin"),
                new Pais("BY", "Bielorrusia"),
                new Pais("MM", "Birmania"),
                new Pais("BO", "Bolivia"),
                new Pais("BA", "Bosnia y Herzegovina"),
                new Pais("BW", "Botswana"),
                new Pais("BR", "Brasil"),
                new Pais("BN", "Brunei"),
                new Pais("BG", "Bulgaria"),
                new Pais("BF", "Burkina Faso"),
                new Pais("BI", "Burundi"),
                new Pais("BT", "Bután"),
                new Pais("CV", "Cabo Verde"),
                new Pais("KH", "Camboya"),
                new Pais("CM", "Camerún"),
                new Pais("CA", "Canadá"),
                new Pais("TD", "Chad"),
                new Pais("CL", "Chile"),
                new Pais("CN", "China"),
                new Pais("CY", "Chipre"),
                new Pais("VA", "Ciudad del Vaticano (Santa Sede)"),
                new Pais("CO", "Colombia"),
                new Pais("KM", "Comores"),
                new Pais("CG", "Congo"),
                new Pais("CD", "Congo, República Democrática del"),
                new Pais("KR", "Corea"),
                new Pais("KP", "Corea del Norte"),
                new Pais("CI", "Costa de Marfíl"),
                new Pais("CR", "Costa Rica"),
                new Pais("HR", "Croacia (Hrvatska)"),
                new Pais("CU", "Cuba"),
                new Pais("DK", "Dinamarca"),
                new Pais("DJ", "Djibouti"),
                new Pais("DM", "Dominica"),
                new Pais("EC", "Ecuador"),
                new Pais("EG", "Egipto"),
                new Pais("SV", "El Salvador"),
                new Pais("AE", "Emiratos Árabes Unidos"),
                new Pais("ER", "Eritrea"),
                new Pais("SI", "Eslovenia"),
                new Pais("ES", "España"),
                new Pais("US", "Estados Unidos"),
                new Pais("EE", "Estonia"),
                new Pais("ET", "Etiopía"),
                new Pais("FJ", "Fiji"),
                new Pais("PH", "Filipinas"),
                new Pais("FI", "Finlandia"),
                new Pais("FR", "Francia"),
                new Pais("GA", "Gabón"),
                new Pais("GM", "Gambia"),
                new Pais("GE", "Georgia"),
                new Pais("GH", "Ghana"),
                new Pais("GD", "Granada"),
                new Pais("GR", "Grecia"),
                new Pais("GT", "Guatemala"),
                new Pais("GY", "Guayana"),
                new Pais("GN", "Guinea"),
                new Pais("GQ", "Guinea Ecuatorial"),
                new Pais("GW", "Guinea-Bissau"),
                new Pais("HT", "Haití"),
                new Pais("HN", "Honduras"),
                new Pais("HU", "Hungría"),
                new Pais("IN", "India"),
                new Pais("ID", "Indonesia"),
                new Pais("IQ", "Irak"),
                new Pais("IR", "Irán"),
                new Pais("IE", "Irlanda"),
                new Pais("IS", "Islandia"),
                new Pais("MH", "Islas Marshall"),
                new Pais("PW", "Islas Palau"),
                new Pais("SB", "Islas Salomón"),
                new Pais("IL", "Israel"),
                new Pais("IT", "Italia"),
                new Pais("JM", "Jamaica"),
                new Pais("JP", "Japón"),
                new Pais("JO", "Jordania"),
                new Pais("KZ", "Kazajistán"),
                new Pais("KE", "Kenia"),
                new Pais("KG", "Kirguizistán"),
                new Pais("KI", "Kiribati"),
                new Pais("KW", "Kuwait"),
                new Pais("LA", "Laos"),
                new Pais("LS", "Lesotho"),
                new Pais("LV", "Letonia"),
                new Pais("LB", "Líbano"),
                new Pais("LR", "Liberia"),
                new Pais("LY", "Libia"),
                new Pais("LI", "Liechtenstein"),
                new Pais("LT", "Lituania"),
                new Pais("LU", "Luxemburgo"),
                new Pais("MK", "Macedonia, Ex-República Yugoslava de"),
                new Pais("MG", "Madagascar"),
                new Pais("MY", "Malasia"),
                new Pais("MW", "Malawi"),
                new Pais("MV", "Maldivas"),
                new Pais("ML", "Malí"),
                new Pais("MT", "Malta"),
                new Pais("MA", "Marruecos"),
                new Pais("MU", "Mauricio"),
                new Pais("MR", "Mauritania"),
                new Pais("MX", "México"),
                new Pais("FM", "Micronesia"),
                new Pais("MD", "Moldavia"),
                new Pais("MC", "Mónaco"),
                new Pais("MN", "Mongolia"),
                new Pais("MZ", "Mozambique"),
                new Pais("NA", "Namibia"),
                new Pais("NR", "Nauru"),
                new Pais("NP", "Nepal"),
                new Pais("NI", "Nicaragua"),
                new Pais("NE", "Níger"),
                new Pais("NG", "Nigeria"),
                new Pais("NO", "Noruega"),
                new Pais("NZ", "Nueva Zelanda"),
                new Pais("OM", "Omán"),
                new Pais("NL", "Países Bajos"),
                new Pais("PA", "Panamá"),
                new Pais("PG", "Papúa Nueva Guinea"),
                new Pais("PK", "Paquistán"),
                new Pais("PY", "Paraguay"),
                new Pais("PE", "Perú"),
                new Pais("PL", "Polonia"),
                new Pais("PT", "Portugal"),
                new Pais("QA", "Qatar"),
                new Pais("CF", "República Centroafricana"),
                new Pais("CZ", "República Checa"),
                new Pais("ZA", "República de Sudáfrica"),
                new Pais("DO", "República Dominicana"),
                new Pais("SK", "República Eslovaca"),
                new Pais("RW", "Ruanda"),
                new Pais("RO", "Rumania"),
                new Pais("RU", "Rusia"),
                new Pais("EH", "Sahara Occidental"),
                new Pais("KN", "Saint Kitts y Nevis"),
                new Pais("WS", "Samoa"),
                new Pais("SM", "San Marino"),
                new Pais("VC", "San Vicente y Granadinas"),
                new Pais("LC", "Santa Lucía"),
                new Pais("ST", "Santo Tomé y Príncipe"),
                new Pais("SN", "Senegal"),
                new Pais("SC", "Seychelles"),
                new Pais("SL", "Sierra Leona"),
                new Pais("SG", "Singapur"),
                new Pais("SY", "Siria"),
                new Pais("SO", "Somalia"),
                new Pais("LK", "Sri Lanka"),
                new Pais("SZ", "Suazilandia"),
                new Pais("SD", "Sudán"),
                new Pais("SE", "Suecia"),
                new Pais("CH", "Suiza"),
                new Pais("SR", "Surinam"),
                new Pais("TH", "Tailandia"),
                new Pais("TW", "Taiwán"),
                new Pais("TZ", "Tanzania"),
                new Pais("TJ", "Tayikistán"),
                new Pais("TG", "Togo"),
                new Pais("TO", "Tonga"),
                new Pais("TT", "Trinidad y Tobago"),
                new Pais("TN", "Túnez"),
                new Pais("TM", "Turkmenistán"),
                new Pais("TR", "Turquía"),
                new Pais("TV", "Tuvalu"),
                new Pais("UA", "Ucrania"),
                new Pais("UG", "Uganda"),
                new Pais("UY", "Uruguay"),
                new Pais("UZ", "Uzbekistán"),
                new Pais("VU", "Vanuatu"),
                new Pais("VE", "Venezuela"),
                new Pais("VN", "Vietnam"),
                new Pais("YE", "Yemen"),
                new Pais("ZM", "Zambia"),
                new Pais("ZW", "Zimbabue")
            };

    /**
     * Agrega todos los paises al combo box recibido por parametro.
     *
     * @param comboBoxPais
     */
    public static void agregarAComboBox(JComboBox comboBoxPais) {
        for (int i = 0; i < PAISES.length; i++) {
            comboBoxPais.addItem(PAISES[i]);
        }
    }

    /**
     * Devuelve el alias que corresponde al pais indicado.
     *
     * @param pais
     * @return la cadena mencionada.
     */
    public static String conseguirAlias(String pais) {
        String res = "";
        int i = 0;
        while (i < PAISES.length && res.equals("")) {
            if (PAISES[i].getNombre().equals(pais)) {
                res = PAISES[i].getAlias();
            }
            i++;
        }
        return res;
    }

    /**
     * Devuelve el nombre de un pais aleatorio.
     *
     * @return la cadena mencionada.
     */
    public static String getPaisRandom() {
        int indice = new Random().nextInt(PAISES.length);
        String res = (PAISES[indice].getNombre());
        return res;
    }

}
