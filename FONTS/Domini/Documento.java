package Domini;

import java.util.ArrayList;

/**
 * Classe abstracta que representa un document i conté funcions per a la gestió de dades relacionades amb el document.
 * És una classe abstracta que defineix funcions abstractes per calcular lletres i una matriu basada en un alfabet.
 *
 * @author Hossam
 */
public abstract class Documento {

    String documento;
    float[][] matriz_frecuencias;
    ArrayList<Character> letras = new ArrayList<>();

    /**
     * Obté el contingut del document.
     *
     * @return El contingut del document en forma de cadena de caràcters.
     *
     * @author Hossam
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Obté la matriu de freqüències del document.
     *
     * @return La matriu de freqüències de caràcters del document.
     *
     * @author Hossam
     */
    public float[][] getMatriz_frecuencias() {
        return matriz_frecuencias;
    }

    /**
     * Obté la llista de lletres continguda al document.
     *
     * @return La llista de lletres del document.
     *
     * @author Hossam
     */
    public ArrayList<Character> getLetras() {
        return letras;
    }

    /**
     * Estableix la matriu de freqüències del document.
     *
     * @param matriz_frecuencias La nova matriu de freqüències a assignar al document.
     *
     * @author Hossam
     */
    public void setMatriz_frecuencias(float[][] matriz_frecuencias) {
        this.matriz_frecuencias = matriz_frecuencias;
    }

    /**
     * Estableix la llista de lletres del document.
     *
     * @param letras La nova llista de lletres a assignar al document.
     *
     * @author Hossam
     */
    public void setLetras(ArrayList<Character> letras) {
        this.letras = letras;
    }

    /**
     * Estableix el contingut del document.
     *
     * @param documento La nova cadena de caràcters que representa el contingut del document.
     *
     * @author Hossam
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Funció abstracta per calcular les lletres contingudes al document.
     *
     * @author Hossam
     */
    public abstract void calculaLletres();

    /**
     * Funció abstracta per calcular la matriu de freqüències basada en un alfabet.
     *
     * @param alfabeto L'alfabet sobre el qual es calcula la matriu de freqüències.
     *
     * @author Hossam
     */
    public abstract void calculaMatriz(String alfabeto);

}
