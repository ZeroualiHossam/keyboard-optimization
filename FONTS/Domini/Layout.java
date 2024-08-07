package Domini;

/**
 * Classe que representa un layout per a la disposició de tecles en un teclat.
 * Conté funcions per gestionar la disposició, la matriu de distàncies i la col·locació de lletres a un teclat.
 *
 * @author Ona
 */
public class Layout {
    private int numTeclas;
    private int filas;
    private int columnas;
    private float[][] matrizDistancias;

    /**
     * Constructor de la classe Layout que inicialitza la disposició del teclat i la matriu de distàncies.
     *
     * @param numTeclas Número de tecles per al teclat.
     *
     * @author Ona
     */
    public Layout(int numTeclas) {
        this.numTeclas = numTeclas;
        calcularDisposicion(numTeclas);
        this.matrizDistancias = new float[numTeclas][numTeclas];
        inicializarMatrizDistancias();
    }

    /**
     * Obté el número de tecles del teclat.
     *
     * @return El número de tecles del teclat.
     *
     * @author Ona
     */
    public int getNumTeclas() {
        return numTeclas;
    }

    /**
     * Estableix el número de tecles del teclat.
     *
     * @param numTeclas El nou número de tecles per al teclat.
     *
     * @author Ona
     */
    public void setNumTeclas(int numTeclas) {
        this.numTeclas = numTeclas;
    }

    /**
     * Obté el nombre de files de la disposició del teclat.
     *
     * @return El nombre de files de la disposició del teclat.
     *
     * @author Ona
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Estableix el nombre de files de la disposició del teclat.
     *
     * @param filas El nou nombre de files per a la disposició del teclat.
     *
     * @author Ona
     */
    public void setFilas(int filas) {
        this.filas = filas;
    }

    /**
     * Obté el nombre de columnes de la disposició del teclat.
     *
     * @return El nombre de columnes de la disposició del teclat.
     *
     * @author Ona
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Estableix el nombre de columnes de la disposició del teclat.
     *
     * @param columnas El nou nombre de columnes per a la disposició del teclat.
     *
     * @author Ona
     */
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    /**
     * Obté la matriu de distàncies entre les tecles del teclat.
     *
     * @return La matriu de distàncies entre les tecles del teclat.
     *
     * @author Ona
     */
    public float[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    /**
     * Estableix la matriu de distàncies entre les tecles del teclat.
     *
     * @param matrizDistancias La nova matriu de distàncies per al teclat.
     *
     * @author Ona
     */
    public void setMatrizDistancias(float[][] matrizDistancias) {
        this.matrizDistancias = matrizDistancias;
    }

    /**
     * Funcio que calcula les files y columnes optimes que ha de tenir el teclat en
     * funcio del numero de tecles.
     *
     * @param teclas Numero de lletres que conte l'alfabet
     *
     * @author Ona
     */
    public void calcularDisposicion(int teclas) {
        this.filas = (int) Math.sqrt(teclas);
        this.columnas = teclas / this.filas;

        if (this.filas * this.columnas < teclas) {
            if (this.filas < this.columnas) {
                this.filas++;
            } else {
                this.columnas++;
            }
        }
    }

    /**
     * Funcio encarregada d'inicialitzar la matriu de distancies. Cada casella conte
     * la distancia que hi ha entre la tecla d'aquella fila i d'aquella columna.
     * 
     * @author Ona
     */
    public void inicializarMatrizDistancias() {
        for (int i = 0; i < numTeclas; i++) {
            for (int j = 0; j < numTeclas; j++) {
                int fila_i = i / this.columnas;
                int columna_i = i % this.columnas;
                int fila_j = j / this.columnas;
                int columna_j = j % this.columnas;
                if (i == j) {
                    this.matrizDistancias[i][j] = 0;
                } else {
                    float distancia;
                    if (fila_i != fila_j && columna_i != columna_j) { // teclas no están en filas y columnas distintas.
                                                                      // distancia euclidiana
                        distancia = (float) Math
                                .sqrt(Math.pow(fila_i - fila_j, 2) + Math.pow(columna_i - columna_j, 2));
                    } else { // distancia con el número de teclas entre medio
                        distancia = Math.abs(fila_i - fila_j) + Math.abs(columna_i - columna_j);
                    }
                    this.matrizDistancias[i][j] = distancia;
                }
            }
        }
    }

    /**
     * Col·loca les lletres de l'alfabet al teclat.
     * Funció per a posicionar les lletres de l'alfabet a les seves posicions de teclat a partir d'una seqüència de posicions.
     *
     * @param stringAlfabet Cadena que conté les lletres de l'alfabet.
     * @param posiciones    Vector d'enters que indica la posició a la qual pertany cada lletra.
     * @return Matriu de caràcters que conté les lletres de l'alfabet col·locades a la seva posició representant el teclat.
     * @author Ona
     */
    public char[][] colocaLetrasMatriz(String stringAlfabet, int[] posiciones) {

        int mida = stringAlfabet.length();
        char[][] matrizTeclado = new char[filas][columnas];
        int k = 0;

        for (int i = 0; i < filas; ++i) {
            for (int j = 0; j < columnas; ++j) {
                if (k < mida) {

                    int pos = posiciones[k];
                    char c = stringAlfabet.charAt(pos);
                    matrizTeclado[i][j] = c;
                    ++k;
                }
            }
        }

        return matrizTeclado;
    }
}