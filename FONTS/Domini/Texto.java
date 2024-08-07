package Domini;

import java.util.ArrayList;
import java.util.Collections;


public class Texto extends Documento{

    public Texto() {

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
        char[] text = documento.toCharArray();
        int posicioA = 0, posicioB = 0;

        for (int i = 0; i < text.length-1; ++i){ 
            if (text[i] != ' '){
                for(int j = 0; j < letras.size(); ++j){
                    if (letras.get(j).equals(text[i])) posicioA = j;
                    if (letras.get(j).equals(text[i+1]))posicioB = j;
                }
                matriz_frecuencias[posicioA][posicioB] += 1;
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

        for (int i = 0; i < documento.length(); ++i){
            char c = documento.charAt(i);
            if (!letras.contains(c)) {
                letras.add(c);
            }
        }
        Collections.sort(letras);
    }

}