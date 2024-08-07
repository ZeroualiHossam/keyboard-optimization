package Domini;

import java.util.ArrayList;
import java.util.Collections;


public class Freq_Palabras extends Documento{


    public Freq_Palabras() {

    }
    /**
     * Calcula la matriz de frecuencias entre pares para los caracteres del documento que corresponde al teclado
     * 
     * @param alfabeto String que corresponde al alfabeto del documento.
     * 
     * @author Hossam
     */
    @Override
    public void calculaMatriz(String alfabeto){

        ArrayList<Character> letrasTec = new ArrayList<>();

        for (int i = 0; i < alfabeto.length(); ++i){
            char c = alfabeto.charAt(i);
            letrasTec.add(c);
        }

        letras = letrasTec;

        matriz_frecuencias = new float[letras.size()][letras.size()];
        documento = documento.toLowerCase();
        String[] palabras = documento.split(","); // Vector de strings de la forma [palabra numero]
        int posicioA = 0, posicioB = 0;

        for (int i = 0; i < palabras.length; ++i){ // Recorro el string

            char[] text = palabras[i].toCharArray();
            int tam_par = 0; //Longitud palabra

            for (int r = 0; r < text.length; ++r) if(text[r] == ' ') tam_par = r; //Obtengo la palabra recorriendola y me guardo la posicion en la que acaba

            int repeticions = 0, mult = 1;

            for (int z = text.length-1; z > tam_par; --z){ // Recorro la palabra desde el final para calcular su frecuencia.
                repeticions += mult*Character.getNumericValue(text[z]);
                mult = mult*10;
            }

            for (int j = 0; j < tam_par-1; ++j){ // Recorro la palabra de nuevo para calcular sus freq
                for (int k = 0; k < letras.size(); ++k){ // Recorrido para mirar que posicion de la matriz le corresponde
                    if (letras.get(k).equals(text[j])) posicioA = k;
                    if (letras.get(k).equals(text[j+1])) posicioB = k;
                }
                matriz_frecuencias[posicioA][posicioB] += repeticions;
            }
        }
    }

    /**
    * Funcion que calcula los caracteres unicos que tiene el documento del teclado, para poder comprobar que alfabeto corresponde al documento
    *
    * @author Hossam
    */

    @Override
    public  void calculaLletres(){
        documento = documento.toLowerCase();

        char[] text = documento.toCharArray();
        boolean skip = false;
        for (int i = 0; i < text.length; ++i){
            if (text[i] ==  ' ') skip = true;
            if (text[i] ==  ',') skip = false;
            if (!skip && !letras.contains(text[i])) letras.add(text[i]);
        }
        Collections.sort(letras);
    }
}