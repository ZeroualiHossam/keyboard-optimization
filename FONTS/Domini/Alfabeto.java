package Domini;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Alfabeto que contiene y gestiona los alfabetos predefinidos de nuestro sistema.
 *
 * @author Arnau
 */
public class Alfabeto {

    //Definicion de los alfabetos.
    private static final String A_INGLES = "abcdefghijklmnopqrstuvwxyz";
    private static final String A_ESPANOL = "abcdefghijklmnñopqrstuvwxyz";
    private static final String A_CYRILLIC = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String  A_THAI = "กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรลวศษสหฬอฮ";
    private static final String A_GEORGIAN = "აბგდევზთიკლმნოპჟრსტუფქღყშჩცძწჭხჯჰ";
    private static final String A_GREEK = "αβγδεζηθικλμνξοπρστυφχψω";
    private static final String A_ARABIC= "ابتثجحخدذرزسشصضطظعغفقكلمنهوي";
    private static final String A_DAVANGERI = "ऄअआइईउऊऋऌऍऎएऐऑऒओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह";
    private static final String A_TAMIL = "அஆஇஈஉஊஎஏஐஒஓஔகஙசஞடணதநபமயரலவஶஷஸஹாிீுூெேைொோௌ்";
    private static final String A_VIETNAM = "aăâbcdđeêghiklmnoôơpqrstuưvxy";


    //Mapa donde se guardan los alfabetos que predefinimos.
    private static final Map<String, String> ALFABETOS = new HashMap<>();

    public Alfabeto() {
        //Añadimos los alfabetos al mapa.
        ALFABETOS.put("Ingles",A_INGLES);
        ALFABETOS.put("Espanol",A_ESPANOL);
        ALFABETOS.put("Cyrilico",A_CYRILLIC);
        ALFABETOS.put("Thailandes",A_THAI);
        ALFABETOS.put("Georgiano",A_GEORGIAN);
        ALFABETOS.put("Griego",A_GREEK);
        ALFABETOS.put("Arabe",A_ARABIC);
        ALFABETOS.put("Davangeri",A_DAVANGERI);
        ALFABETOS.put("Tamil",A_TAMIL);
        ALFABETOS.put("Vietnam",A_VIETNAM);
    }

    /**
     * Obtiene todos los alfabetos predefinidos de nuestro sistema.
     *
     * @return Mapa que contiene los nombres de los alfabetos y sus caracteres.
     *
     * @author Arnau
     */
    public Map<String, String> getAlfabetos() {
        return ALFABETOS;
    }

    /**
     * Obtiene un alfabeto especifico a partir de su nombre.
     *
     * @param nombre Nombre del alfabeto que se quiere obtener.
     * @return Cadena de caracteres que representan el alfabeto predefinido corresponiente.
     *
     * @author Arnau
     */
    public String getAlfabeto(String nombre) {
        return ALFABETOS.get(nombre);
    }

}
