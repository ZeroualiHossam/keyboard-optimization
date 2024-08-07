package Domini;

/**
 * Classe que representa un teclat amb funcionalitats per gestionar les seves propietats i operacions.
 * Conté mètodes per obtenir i establir el nom, l'alfabet, el layout, l'usuari, el document i la matriu de teclat,
 * així com funcions per canviar el nom i col·locar les lletres de l'alfabet al teclat.
 *
 * @author Ona
 */
public class Teclado {
    private String nombre;
    private String stringAlfabet;
    private Layout layout;
    private Usuario usuario;
    private Documento documento;
    public int[] posiciones;
    private char[][] matrizTeclado;

    /**
     * Constructora de la classe Teclado.
     *
     * @param nombre   El nom del teclat.
     * @param alfabeto L'alfabet del teclat en format de cadena.
     * @param layout   El layout del teclat.
     *
     * @author Ona
     */
    public Teclado(String nombre, String alfabeto, Layout layout) {
        this.nombre = nombre;
        this.layout = layout;
        stringAlfabet = alfabeto;
    }

    /**
     * Obté el nom del teclat.
     *
     * @return El nom del teclat.
     *
     * @author Ona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Estableix el nom del teclat.
     *
     * @param nombre El nou nom per al teclat.
     *
     * @author Ona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obté l'alfabet del teclat.
     *
     * @return L'alfabet del teclat en format de cadena.
     *
     * @author Ona
     */
    public String getAlfabet() {
        return stringAlfabet;
    }

    /**
     * Estableix l'alfabet del teclat.
     *
     * @param stringAlfabet La nova cadena d'alfabet per al teclat.
     *
     * @author Ona
     */
    public void setAlfabet(String stringAlfabet) {
        this.stringAlfabet = stringAlfabet;
    }

    /**
     * Obté el layout del teclat.
     *
     * @return El layout del teclat.
     *
     * @author Ona
     */
    public Layout getLayout() {
        return layout;
    }

    /**
     * Obté el layout del teclat.
     *
     * @return El layout del teclat.
     *
     * @author Ona
     */
    public int[] getPosiciones() {
        return posiciones;
    }

    /**
     * Estableix el layout del teclat.
     *
     * @param layout El nou layout per al teclat.
     *
     * @author Ona
     */
    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    /**
     * Obté l'usuari associat al teclat.
     *
     * @return L'usuari associat al teclat.
     *
     * @author Ona
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Estableix l'usuari associat al teclat.
     *
     * @param usuario L'usuari a associar amb el teclat.
     *
     * @author Ona
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obté el document associat al teclat.
     *
     * @return El document associat al teclat.
     *
     * @author Ona
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Estableix el document associat al teclat.
     *
     * @param documento El document a associar amb el teclat.
     *
     * @author Ona
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * Obté la matriu del teclat.
     *
     * @return La matriu que representa el teclat.
     *
     * @author Ona
     */
    public char[][] getMatrizTeclado() {
        return matrizTeclado;
    }

    /**
     * Estableix la matriu del teclat.
     *
     * @param matrizTeclado La nova matriu que representa el teclat.
     *
     * @author Ona
     */
    public void setMatrizTeclado(char[][] matrizTeclado) {
        this.matrizTeclado = matrizTeclado;
    }

    /**
     * Canvia el nom del teclat.
     *
     * @param nombreNuevo El nou nom per al teclat.
     *
     * @author Ona
     */
    public void cambiarNombre(String nombreNuevo) {
        this.nombre = nombreNuevo;
    }

    /**
     * Funcio que realitza una crida a la classe Layout per tal de colocar les
     * lletres de l'alfabet a la seva poscio del teclat
     * 
     * @author Ona
     */
    public void colocaLetras() {
        this.matrizTeclado = layout.colocaLetrasMatriz(stringAlfabet, posiciones);
    }

}
