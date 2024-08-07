package ControladorPresentacio;

import ControladorDomini.*;
import ControladorsDePresentacio.*;

public class CtrlPresentacion {
    static CtrlDominio cd = new CtrlDominio();
    static CtrlLoginView cl = new CtrlLoginView();
    static CtrlMainView cm = new CtrlMainView();
    static CtrlTecladoView cv = new CtrlTecladoView();

    /**
     * Funcio que inicia l'aplicacio, comproba l'estat per iniciar un usuari o en la
     * pantalla d'iniciar sesio
     * 
     * @author Hosssam
     */
    public void iniciaPrograma() {
        String state = cd.compruebaEstado();
        if (!state.equals("Invitado"))
            cargaPerfil(state);
        else
            cl.iniciarSesion();
    }

    /**
     * Funcio que fa una crida al controlador de domini perque es comuniqui amb
     * persistencia per iniciar sesio
     * a l'ususari user i modificar l'estat si les dades son correctes.
     * 
     * @param user     String que correspon al nom de l'usuari al que es vol accedir
     * @param pass String que correspon a la contrasena de l'usuari al que es
     *                 vol accedir
     * @return Boolea que es true si es pot accedir a l'usuari
     * 
     * @author Hossam
     */
    public static boolean iniciarSesion(String user, String pass) {
        boolean accede = cd.iniciarSesion(user, pass);
        if (accede)
            cd.actualizaEstado("");
        return accede;
    }

    /**
     * Funcio que fa una crida al controlador de login per mostrar la vista de crear
     * usuari
     * 
     * @author Hossam
     */
    public void crearUsuario() {
        cl.crearUsuario();
    }

    /**
     * Funcio que mostra la pàgina principal de l'aplicació després d'haver iniciat sessió.
     *
     * @author Arnau
     */
    public static void mostrarPrincipal() {
        cm.muestraPaginaPrincipal();
    }

    /**
     * Funcio que crida a domini per crear l'usuari user i actualitzar l'estat de
     * l'aplicacio si es crea
     * 
     * @param user     String que correspon al nom de l'usuari que es vol crear
     * @param password String que correspon a la contrasena de l'usuari que es vol
     *                 crear
     * @return Boolea que es true si s'ha pogut crear l'usuari
     * 
     * @author Hossam
     */
    public static boolean creaUser(String user, String password) {
        boolean crea = cd.crearUsuario(user, password);
        if (crea)
            cd.actualizaEstado("");
        return crea;
    }

    /**
     * Funcio que actualitza l'estat de l'aplicacio i fa una crida al controlador
     * main per mostrar el perfil de l'usuari user
     * 
     * @param user String que correspon al nom de l'usuari que es vol carregar el
     *             seu perfil
     * 
     * @author Hossam
     */
    public static void cargaPerfil(String user) {
        cd.actualizaEstado(user);
        cm.muestraPaginaPrincipal();
    }

    /**
     * Funcio que crida al controlador de domini per tal d'obtenir els teclats de
     * l'usuari actual
     * 
     * @return Vector de Strings amb el nom dels diferents teclats que pertañen a
     *         l'usuari
     * @author Ona
     */
    public static String[] obtenerTecs() {
        return cd.obtenerTecladosPerfil();
    }

    /**
     * Funcio que fa una crida a domini per crear el teclat nomTec
     * 
     * @param nombreTec    String que correspon al nom del teclat
     * @param documento  String que conte el text utilitzat per crear el teclat
     * @param algoritmo1 Boolea que es true si s'utilitza l'algorisme QAP pel teclat
     *                   que es vol crear
     * @return Boolea que indica si s'ha creat el teclat (true) o no (false)
     * 
     * @author Hossam
     */
    public static boolean crearTeclado(String nombreTec, String documento, boolean algoritmo1) {
        return cd.crearTeclado(nombreTec, documento, algoritmo1);
    }

    /**
     * Funcio que crida al controlador de la vista teclat per tal de mostrar el
     * teclat
     * 
     * @param nomTec Nom del teclat que esl vol mostrar
     */
    public static void muestraTeclado(String nomTec) {
        String alfabeto = cd.getAlfabetoTec(nomTec);
        int filas = cd.getFilasTec(nomTec);
        int columnas = cd.getColumnasTec(nomTec);
        int alg = cd.getAlgTec(nomTec);

        cv.mostrarTeclado(alfabeto, filas, columnas, nomTec, alg);
    }

    /**
     * Funcio que fa una crida a domini per obtenir el document del teclat nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir el
     *               document
     * @return String que correspon al document del teclat
     * 
     * @author Hossam
     */
    public static String obtenerDocTeclado(String nomTec) {
        return cd.obtenerDocTeclado(nomTec);
    }

    /**
     * Funcio que fa una crida a domini perque modifiqui el nom del teclat nombreTec
     * 
     * @param nombreTec String que correspon al nom actual del teclat
     * @param newNomTec String que correspon al nom nou del teclat
     * @return Boolea que es true si s'ha modificat el nom del teclat correctament
     * 
     * @author Hossam
     */
    public static boolean modificaNomTec(String nombreTec, String newNomTec) {
        return cd.modificarNombreTeclado(nombreTec, newNomTec);
    }

    /**
     * Funcio que fa una crida a domini perque guardi el nou document del teclat
     * nombreTec
     * 
     * @param nombreTec String que correspon al nom del teclat
     * @param doc       String que correspon al contingut del document del teclat
     *                  nombreTec
     * @param alg       int que defineix l'algorisme amb que es va crear el teclat
     * @return Boolea que es true si s'ha modificat el document del teclat
     *         correctament
     * 
     * @author Hossam
     */
    public static boolean modificaDocTec(String nombreTec, String doc, int alg) {
        return cd.modificarDocumentoTeclado(nombreTec, doc, alg);
    }

    /**
     * Funcio que fa una crida a domini perque guardi la nova distribucio del teclat
     * nomTec
     * 
     * @param nomTec   String que correspon al nom del teclat
     * @param filas    int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol
     *                 mostrar
     * @param alg      int que defineix l'algorisme amb que es va crear el teclat
     * @return Boolea que es true si s'ha modificat el teclat correctament
     * 
     * @author Hossam
     */
    public static boolean modificaDistribucionTec(String nomTec, int filas, int columnas, String alfabeto, int alg) {
        return cd.modificaDistribucionTec(nomTec, filas, columnas, alfabeto, alg);
    }

    /**
     * Funcio que crida al controlador de domini per tal de borrar el teclat passat
     * com a parametre
     * 
     * @param nomTec Nom del teclat que es vol borrar
     * @author Ona
     */
    public static void borrarTeclado(String nomTec) {
        cd.borrarTeclado(nomTec);
    }

    /**
     * Funcio que actualitza l'estat de l'aplicacio i fa una crida al controlador
     * de login per mostrar la vista d'iniciar sesio
     * 
     * @param user String del nom de l'usuari que es vol mostrar el perfil
     * 
     * @author Hossam
     */
    public static void mostrarVistaInicioSesion(String user) {
        cd.actualizaEstado(user);
        cl.iniciarSesion();
    }

    /**
     * Funcio que crida al controlador de domini per eliminar l'usuari actual
     *
     * @author Arnau
     */
    public static void eliminarUsuario() {
        cd.eliminarUsuarioVista();
    }

    /**
     * Funcio que comprova si el nou nom de l'usuari existeix
     * 
     * @param nombreUsuario String que representa el nom de l'usuari
     * @return Boolea que indica si el nom existeix
     *
     * @author Arnau
     */
    public static boolean cambiarNombreUsuarioVista(String nombreUsuario) {
        return cd.cambiarNombreUsuarioVista(nombreUsuario);
    }

    /**
     * Funcio que crida al controlador de domini per obtenir la contrassenya de
     * l'usuari acual
     * 
     * @param nombreUsuario String que representa el nom de l'usuari
     * @return String que correspon a la contrassenya de l'usuari
     *
     * @author Arnau
     */
    public static String obtenerPasswordUsuario(String nombreUsuario) {
        return cd.obtenerPasswordUsuario(nombreUsuario);
    }

    /**
     * Funcio que crida al controlador de domini per obtenir el nom de l'usuari
     * actual
     * 
     * @return String que correspo al nom de l'usuari actual
     *
     * @author Arnau
     */
    public static String[] obtenerUsers() {
        return cd.obtenerUsuariosVista();
    }

    /**
     * Funcio que crida al controlador de domini per obtenir el nom de l'usuari
     * actual
     * 
     * @return String que correspo al nom de l'usuari actual
     *
     * @author Arnau
     */
    public static String getNombreUsuario() {
        return cd.getNombreUsuario();
    }

}
