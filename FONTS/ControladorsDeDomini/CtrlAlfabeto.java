package ControladorsDeDomini;

import Domini.Alfabeto;

import java.util.ArrayList;
import java.util.Map;

/**
 * Clase CtrlAlfabeto, usada para encontrar el alfabeto más similar a un conjunto de letras dado.
 *
 * @author Arnau
 */
public class CtrlAlfabeto {

    private static Alfabeto alfabeto = new Alfabeto();

    /**
     * Constructor de la clase CtrlAlfabeto
     *
     * @author Arnau
     */
    //creamos una instancia de la clase Alfabeto
    public CtrlAlfabeto() {
        alfabeto = new Alfabeto();
    }

    /**
     * Encuentra el alfabeto más similar al conjunto de letras recibido.
     *
     * @param letrasDoc Lista de caracteres generadas en la clase Documento.
     * @return Nombre del alfabeto predefinido en la clase Alfabetos más similar al conjunto de letras recibido.
     *
     * @author Arnau
     */
    public static String getAlfabetoMasSimilar(ArrayList<Character> letrasDoc){

        ArrayList<Character> letras = new ArrayList<>();

        for (int i = 0; i < letrasDoc.size(); ++i){
            if (esAlfabetico(letrasDoc.get(i))) letras.add(letrasDoc.get(i));
        }

        Map<String, String> alfabetosPredefinidos=alfabeto.getAlfabetos();

        String mejorCoincidencia=null;
        int maxCoincidencias = 0;

        for (Map.Entry<String, String> entry:alfabetosPredefinidos.entrySet()) {
            String nombreAlfabeto=entry.getKey();
            String alfabetoPredefinido=entry.getValue();
            int coincidencias=countCoincidencias(new ArrayList<>(letras), alfabetoPredefinido);

            if(coincidencias > maxCoincidencias){
                maxCoincidencias=coincidencias;
                mejorCoincidencia=nombreAlfabeto;
            }
        }
        
        return alfabeto.getAlfabeto(mejorCoincidencia);
    }

    /**
     * Calcula el numero de coincidencias que tienen los caracteres del documento del teclado con un alfabeto
     *
     * @param letras Lista de caracteres del documento que corresponde al teclado
     * @param alfabetoComp String que corresponde al alfabeto que se comprueba
     * @return Numero de coincidencias que hay entre el alfabeto y los caracteres del documento.
     * 
     * @author Hossam
     */
    public static int countCoincidencias(ArrayList<Character> letras, String alfabetoComp) {
        int coincidencias=0;

        for (int i = 0; i < letras.size(); ++i){
            if (alfabetoComp.indexOf(letras.get(i)) != -1) ++coincidencias;
        }
        return coincidencias;
    }

    /**
     * Funcion que comprueba si el caracter que recibe pertenece a alguno de nuestros alfabetos
     * 
     * @param c Caracter que se quiere comprobar si pertenece a alguno de nuestros alfabetos
     * @return Booleano que es true si el caracter pertenece a alguno de los alfabetos
     *
     * @author Hossam
     */
    public static boolean esAlfabetico(char c) {
        Map<String, String> alfabetosPredefinidos = alfabeto.getAlfabetos();
        
        //Array de tantas columnas como alfabetos tenemos (10) y 1 fila.
        String[] alfabetoArray = alfabetosPredefinidos.values().toArray(new String[0]);

        for (int i = 0; i < alfabetoArray.length; i++) {
            String alfabetoStr = alfabetoArray[i];
            if (alfabetoStr.indexOf(c) != -1) {
                return true;
            }
        }
        
        return c == 'ñ';
    }
}
