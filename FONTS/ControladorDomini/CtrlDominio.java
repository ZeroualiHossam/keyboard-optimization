package ControladorDomini;

import java.io.File;
import ControladorPersistencia.*;
import ControladorsDeDomini.*;
import Domini.*;

public class CtrlDominio {
    Usuario usuarioAct = new Usuario("Invitado");

    /**
     * Funcio que s'encarrega de la creacio del teclat amb els parametres introduits
     * per l'usuari, fa les crides necesaries per
     * calcular el layout, l'alfabet mes similar, la frecuencia de lletres i la
     * creacio dels archius necesaris per emmagatzemar la
     * informacio del teclat
     *
     * @param nameTec    String que correspon al nom del teclat
     * @param documento  String que conte el text utilitzat per crear el teclat
     * @param algoritmo1 Boolea que es true si s'utilitza l'algorisme QAP pel teclat
     *                   que es vol crear
     * @return Boolea que indica si s'ha creat el teclat (true) o no (false)
     *
     * @author Hossam
     */
    public boolean crearTeclado(String nameTec, String documento, boolean algoritmo1) {

        if (documento.equals("") || documento.equals(" ") || nameTec.equals("") || nameTec.equals(" "))
            return false;
        else if (!CtrlPersistencia.existeTeclado(usuarioAct.getNombre(), nameTec)) {

            Documento actual = CtrlDocumento.comprobaTipus(documento);
            actual.calculaLletres();
            String alfabeto = CtrlAlfabeto.getAlfabetoMasSimilar(actual.getLetras());
            actual.calculaMatriz(alfabeto);
            
            Teclado tecActual = CtrlTeclado.crearTeclado(nameTec, alfabeto, actual.getMatriz_frecuencias(), algoritmo1);

            tecActual.setDocumento(actual);
            tecActual.setUsuario(usuarioAct);

            Layout aux = tecActual.getLayout();
            int filas = aux.getFilas();
            int columnas = aux.getColumnas();
            int alg;
            if (algoritmo1)
                alg = 1;
            else
                alg = 2;
            alfabeto = ubicaTeclas(alfabeto, tecActual.getPosiciones(), filas, columnas);
            CtrlPersistencia.crearTeclado(usuarioAct.getNombre(), nameTec, documento, alfabeto,
                    (usuarioAct.getPathUsuario() + File.separator + nameTec), filas, columnas, alg);

            return true;
        } else
            return false;
    }

    /**
     * Funcio que fa una crida a persistencia per obtenir el nombre de files que te
     * el teclat nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir les
     *               files
     * @return int que correspon al nombre de files del teclat
     * 
     * @author Hossam
     */
    public int getFilasTec(String nomTec) {
        return CtrlPersistencia.getFilasTec(nomTec, usuarioAct.getNombre());
    }

    /**
     * Funcio que fa una crida a persistencia per obtenir el valor de quin algorisme
     * ha utilitzat el teclat per crearse
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir
     *               l'algorisme
     * @return int que correspon a 1 per QAP, o 2 per Hill Climbing
     * 
     * @author Hossam
     */
    public int getAlgTec(String nomTec) {
        return CtrlPersistencia.getAlgoritmoTec(nomTec, usuarioAct.getNombre());
    }

    /**
     * Funcio que fa una crida a persistencia per obtenir el nombre de columnes que
     * te el teclat nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir les
     *               columnes
     * @return int que correspon al nombre de files del teclat
     * 
     * @author Hossam
     */
    public int getColumnasTec(String nomTec) {
        return CtrlPersistencia.getColumnasTec(nomTec, usuarioAct.getNombre());
    }

    /**
     * Funcio que fa una crida a persistencia per obtenir l'alfabet del teclat
     * nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir
     *               l'alfabet
     * @return String que correspon a l'alfabet del teclat
     * 
     * @author Hossam
     */
    public String getAlfabetoTec(String nomTec) {
        return CtrlPersistencia.getAlfabetoTec(nomTec, usuarioAct.getNombre());
    }

    /**
     * Funcio que fa una crida a persistencia per obtenir el document del teclat
     * nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir el
     *               document
     * @return String que correspon al document del teclat
     * 
     * @author Hossam
     */
    public String obtenerDocTeclado(String nomTec) {
        return CtrlPersistencia.obtenerDocTeclado(nomTec, usuarioAct.getNombre());
    }

    /**
     * Funcio que ordena les tecles amb les posicions obtingudes de l'algorisme
     * executat i que calcula l'alfabet definitiu pel teclat.
     * Afegeix un espai si el nombre de tecles que corresponen a l'ultima fila es
     * parell o igual a 0, i en cas de ser imparell afegeix
     * tant un espai com el caracter '.', aixo ens permet tenir una distribucio mes
     * similar a un teclat convencional.
     * 
     * @param alfabeto   String que conte l'alfabet del teclat
     * @param posiciones array que conte les posiciones pels caracters de l'alfabet
     * @param filas      int que correspon al nombre de files del teclat
     * @param columnas   int que correspon al nombre de columnes del teclat
     * @return String que correspon a l'alfabet ja modificat, amb l'espai afegit o
     *         tant l'espai com el punt.
     * 
     * @author Hossam
     */
    public String ubicaTeclas(String alfabeto, int[] posiciones, int filas, int columnas) {

        String[] alf = new String[alfabeto.length()];
        char[] a = alfabeto.toCharArray();

        for (int i = 0; i < posiciones.length; ++i){
            alf[i] =  Character.toString(a[posiciones[i]]);
        }

        StringBuilder alfabDef = new StringBuilder();
        for (int i = 0; i < alf.length; i++) {
            alfabDef.append(alf[i]);
        }

        String result = alfabDef.toString();

        int ultima = result.length() - (filas - 1) * columnas;
        if (ultima == 0 || ultima % 2 == 0)
            result = result + " ";
        else
            result = result + " .";

        return result;
    }

    /**
     * Funcio que fa una crida a persistencia per guardar la nova distribucio del
     * teclat nameTec
     * 
     * @param nameTec  String que indica el nom del teclat del que volem modificar
     * @param filas    int que te el valor de files que te el teclat
     * @param columnas int que te el valor de columnes que te el teclat
     * @param alfabeto String que correspon al alfabet del teclat
     * @param alg      int que indica el algoritmo utilizado, 1 para QAP y 2 para
     *                 Hill Climbing
     * @return Boolea que es true en cas que es s'hagi modificat correctament la
     *         distribucio
     * 
     * @author Hossam
     */
    public boolean modificaDistribucionTec(String nameTec, int filas, int columnas, String alfabeto, int alg) {
        if (CtrlPersistencia.guardarTeclado(alfabeto, (usuarioAct.getPathUsuario() + File.separator + nameTec), filas,
                columnas, alg))
            return true;
        else
            return false;
    }

    /**
     * Funcio que crida a persistencia per crear un usuari si els Strings que rep no
     * son buits
     * 
     * @param user     String que conte el nom de l'usuari de qui es el teclat
     * @param password String que correspon a la password de l'usuari user
     * 
     * @return Boolea que es true si s'ha pogut crear l'usuari
     * 
     * @author Hossam
     */
    public boolean crearUsuario(String user, String password) {
        if (password.equals("") || password.equals(" "))
            return false;
        else {
            if (CtrlPersistencia.crearUsuario(user, password)) {
                usuarioAct = new Usuario(user);
                return true;
            }
            return false;
        }
    }

    /**
     * Funcio que fa una crida a persistencia per comprobar les dades per iniciar
     * sessio
     * i actualitzar l'usuari actual en cas que siguin correctes les dades
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param pass String que correspon a la password de l'usuari user
     * @return Boolea que es true si les dades indicades son correctes
     * 
     * @author Hossam
     */
    public boolean iniciarSesion(String user, String pass) {
        if (!CtrlPersistencia.accederCuenta(user, pass))
            return false;
        else {
            usuarioAct = new Usuario(user);
            return true;
        }
    }

    /**
     * Funcio que fa una crida a persistencia per comprobar l'estat de l'ultima
     * sessio de l'aplicacio
     * 
     * @return String que indica el nom de l'usuari de l'ulitma sessio o Invitado si
     *         es va tancar la sessio
     *
     * @author Hossam
     */
    public String compruebaEstado() {
        String user = CtrlPersistencia.getEstado();
        if (!user.equals("Invitado"))
            usuarioAct = new Usuario(user);

        return user;
    }
    /**
     * Funcio que actualitza l'estat de l'aplicacio
     * 
     * @param user String que indica que operacio s'ha de realitzar, actualitzar a Invitado, o al nou  user
     * 
     * @author Hossam
     */
    public void actualizaEstado(String user) {
        if (user.equals("close"))
            usuarioAct = new Usuario("Invitado");
        else if (!user.equals("")) usuarioAct = new Usuario(user);
        
        CtrlPersistencia.actualizaEstado(usuarioAct.getNombre());
    }

    /**
     * Funcio que obte el nom de l'usuari actual
     * 
     * @return String que es el nom de l'usuari actual
     * 
     * @author Hossam
     */
    public String getNombreUsuario() {
        return usuarioAct.getNombre();
    }

    /**
     * Funcio encarregada de cridar al controlador de persistencia per tal
     * d'eliminar el teclat passat com a parametre
     * 
     * @param nomTec String que representa el nom del teclat
     * 
     * @author Ona
     */
    public void borrarTeclado(String nomTec) {
        CtrlPersistencia.eliminarTeclado(usuarioAct.getNombre(), nomTec);
    }

    /**
     * Funcio que fa una crida a persistencia perque modifiqui el nom del teclat
     * nomTec
     * 
     * @param nomTec String que correspon al nom actual del teclat
     * @param nuevoNombreTeclado String que correspon al nom nou del teclat
     * @return Boolea que es true si s'ha modificat el nom del teclat correctament
     * 
     * @author Hossam
     */
    public boolean modificarNombreTeclado(String nomTec, String nuevoNombreTeclado) {
        if (nuevoNombreTeclado.equals(" ") || nuevoNombreTeclado.equals(""))
            return false;
        else
            return CtrlPersistencia.modificarNombreTeclado(
                    "../../DATA/" + usuarioAct.getNombre() + File.separator + nomTec, nuevoNombreTeclado,
                    usuarioAct.getNombre());
    }

    /**
     * Funcio que fa una crida a persistencia perque guardi el nou document del
     * teclat nomTec
     * 
     * @param nomTec String que correspon al nom del teclat
     * @param documento String que correspon al contingut del document del teclat
     *                  nombreTec
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     * @return Boolea que es true si s'ha modificat el document del teclat
     *         correctament
     * 
     * @author Hossam
     */
    public boolean modificarDocumentoTeclado(String nomTec, String documento, int alg) {
        if (CtrlPersistencia.modificarDocumentoTeclado(usuarioAct.getNombre(), nomTec, documento)) {
            borrarTeclado(nomTec);
            boolean algoritmo1 = false;
            if (alg == 1)
                algoritmo1 = true;
            crearTeclado(nomTec, documento, algoritmo1);
            return true;
        } else
            return false;
    }

    /**
     * Funcio encarregada de cridar al controlador de persistencia per tal d'obtenir
     * la llista de teclats de l'usuari
     * 
     * @return Vector de strings que conte els noms dels teclats que te l'usuari
     * 
     * @author Ona
     */
    public String[] obtenerTecladosPerfil() {
        return CtrlPersistencia.getTecladosUsuario(getNombreUsuario());
    }

    /**
     * Funcio encarregada de cridar al controlador de persistencia per tal d'obtenir
     * la contrassenya de l'usuari
     * 
     * @param nombreUsuario String que representa el nom de l'usuari
     * @return String que representa la contrassenya de l'usuari
     *
     * @author Arnau
     */
    public String obtenerPasswordUsuario(String nombreUsuario) {
        return CtrlPersistencia.getUserPassword(nombreUsuario);
    }

    /**
     * Funcio encaregada d'eliminar l'usuari actual
     *
     * @author Arnau
     */
    public void eliminarUsuarioVista() {
        CtrlPersistencia.deleteUser(getNombreUsuario());
        usuarioAct = new Usuario("Invitado");
    }

    /**
     * Funcio encarregada de modificar el nom de l'usuari
     * 
     * @param nombreUsuario String que representa el nou nom de l'usuari
     * @return Boolea que indica si s'ha realitzat o no la modificacio
     *
     * @author Arnau
     */
    public boolean cambiarNombreUsuarioVista(String nombreUsuario) {
        if (CtrlPersistencia.modificaNombreUsuario("../../DATA/" + usuarioAct.getNombre(), nombreUsuario)) {
            usuarioAct = new Usuario(nombreUsuario);
            return true;
        }
        return false;
    }

    /**
     * Funcio encarregada d'obtenir els usuaris que tenen un compte creat
     * 
     * @return Vector de strings que conte el nom dels usuari existents
     *
     * @author Arnau
     */
    public String[] obtenerUsuariosVista() {
        return CtrlPersistencia.getUsuarios(usuarioAct.getNombre());
    }

}
