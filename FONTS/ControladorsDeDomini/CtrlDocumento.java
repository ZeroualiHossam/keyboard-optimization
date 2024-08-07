package ControladorsDeDomini;

import Domini.Documento;
import Domini.Texto;
import Domini.Freq_Palabras;

public class CtrlDocumento {

    /**
     * Funcion que comprueba de que tipo es el documento introducido para el teclado 
     * 
     * @param documento String que corresponde al documento del teclado que se crea
     * @return Documento que es un Texto o Freq_Palabras
     * 
     * @author Hossam
     */
    public static Documento comprobaTipus(String documento) {
        Documento doc = new Texto();
        char[] text = documento.toCharArray();
        boolean encontrado = false;
        
        for (int i = 0; i < text.length-1 && !encontrado; ++i) {
            if (text[i] == ' ') {
                encontrado = true;
                if (Character.isDigit(text[i + 1])) doc = new Freq_Palabras();
                else doc = new Texto();
            }
        }

        doc.setDocumento(documento);
        return doc;
    }
}
