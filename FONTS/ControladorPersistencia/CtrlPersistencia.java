package ControladorPersistencia;

import java.io.File;
import Persistencia.*;

public class CtrlPersistencia {

    /**
     * Funcio que crida al gestor de teclats per comprova si exsiteix el teclat nomTec entre els que te l'usuari user
     * 
     * @param user String que corresponde al nom de l'usuari a qui pertany el teclat nameTec
     * @param nameTec String que es el nom del teclat
     * @return Booleano que es true en cas que existeixi el teclat
     * 
     * @author Hossam
     */
    public static boolean existeTeclado(String user, String nameTec){
        return GestorTeclados.existeTeclado(user, nameTec);
    }


    /**
     * Funcio que crida a les funcions dels gestors de teclats i documents per crear els carpetes i usuasris 
     * necesaris per guardar la informacio del teclat que es vol crear
     * 
     * @param user String que correspon al nom de l'usuari que crea el teclat
     * @param nameTec String que es el nom del teclat que comprobem
     * @param documento String que conte el document del teclat que es vol crear
     * @param alfabeto String que correspon al alfabet del teclat
     * @param pathUser String que correspon al path de la carpeta de l'usuari
     * @param filas int que te el valor de files que te el teclat
     * @param columnas int que te el valor de columnes que te el teclat
     * @param alg int que indica el algoritmo utilizado, 1 para QAP y 2 para Hill Climbing
     * 
     * @author Hossam
     */
    public static void crearTeclado(String user, String nameTec, String documento, String alfabeto, String pathUser, int filas, int columnas, int alg){
        GestorTeclados.crearTeclado(user, nameTec);
        GestorDocumentos.crearDocumento(user, nameTec, documento);
        GestorTeclados.guardarTeclado(pathUser, alfabeto, filas, columnas, alg);
    }

    /**
     * Funcio que fa una crida al gestor de teclats per obtenir el nombre de files que te el teclat nomTec
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param nomTec String que indica el nom del teclat del que volem obtenir les files
     * @return int que correspon al nombre de files del teclat
     * 
     * @author Hossam
     */
    public static int getFilasTec(String nomTec, String user){
        return GestorTeclados.obtenerFilasTeclado(user + File.separator + nomTec);
    }

    /**
     * Funcio que fa una crida al gestor de teclats per obtenir el nombre de columnes que te el teclat nomTec
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param nomTec String que indica el nom del teclat del que volem obtenir les columnes
     * @return int que correspon al nombre de files del teclat
     * 
     * @author Hossam
     */
    public static int getColumnasTec(String nomTec, String user){
        return GestorTeclados.obtenerColumnasTeclado(user + File.separator + nomTec);
    }

     /**
     * Funcio que fa una crida al gestor de teclats per obtenir l'alfabet del teclat nomTec
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param nomTec String que indica el nom del teclat del que volem obtenir l'alfabet
     * @return String que correspon a l'alfabet del teclat
     * 
     * @author Hossam
     */
    public static String getAlfabetoTec(String nomTec, String user){
        return GestorTeclados.obtenerAlfabetoTeclado(user + File.separator + nomTec);
    }

     /**
     * Funcio que fa una crida al gestor de teclats per obtenir el valor de quin algorisme ha utilitzat el teclat per crearse
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param nomTec String que indica el nom del teclat del que volem obtenir l'algorisme
     * @return int que correspon a 1 per QAP, o 2 per Hill Climbing 
     * 
     * @author Hossam
     */
    public static int getAlgoritmoTec(String nomTec, String user){
        return GestorTeclados.obtenerAlgTeclado(user + File.separator + nomTec);
    }

    /**
     * Funcio que fa una crida al gestor de documents per obtenir el document del teclat nomTec
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir el document
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @return String que correspon al document del teclat
     * 
     * @author Hossam
     */
    public static String obtenerDocTeclado(String nomTec, String user){
        return GestorDocumentos.obtenerDocumento(user, nomTec);
    }

    /**
     * Funcio que fa una crida al gestor de teclats per guardar la nova distribucio del teclat
     * 
     * @param alfabeto String que correspon al alfabet del teclat
     * @param pathUser String que correspon al path de la carpeta de l'usuari
     * @param filas int que te el valor de files que te el teclat
     * @param columnas int que te el valor de columnes que te el teclat
     * @param alg int que indica el algoritmo utilizado, 1 para QAP y 2 para Hill Climbing
     * 
     * @return Boolea que es true si s'ha guardat correctament el teclat.
     * 
     * @author Hossam
     */
    public static boolean guardarTeclado(String alfabeto, String pathUser, int filas, int columnas, int alg){
        return GestorTeclados.guardarTeclado(pathUser, alfabeto, filas, columnas, alg);
    }


    /**
     * Funcio que crida al gestor d'usuaris per crear un usuari si no existeix cap amn el mateix nom
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param password String que correspon a la password de l'usuari user
     * 
     * @return Boolea que es true si s'ha pogut crear l'usuari
     * 
     * @author Hossam
     */
    public static boolean crearUsuario(String user, String password){
        if (GestorUsuarios.existeUsuario(user)) return false;
        else GestorUsuarios.crearUsuario(user, password);
        return true;
    }


    /**
     * Funcio que fa crides al gestor d'usuaris per comprobar les dades per iniciar sessio
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @param pass String que correspon a la password de l'usuari user
     * @return Boolea que es true si les dades indicades son correctes
     * 
     * @author Hossam
     */
    public static boolean accederCuenta(String user, String pass){
        if (!GestorUsuarios.existeUsuario(user)) return false;
        else if (pass.equals(GestorUsuarios.obtenerPasswordUser(user))) return true;
        return false;
    }

    /** 
     * Funcio que fa una crida al gestor de teclats per eliminar el teclat nomTec de l'usuari user
     * 
     * @param nomTec String que indica el nom del teclat del que volem obtenir el document
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * 
     * @author Hossam
     */
    public static void eliminarTeclado(String user, String nomTec) {
        GestorTeclados.eliminarTeclado(user + File.separator + nomTec);
    }

    /** 
     * Funcio que crida al gestor d'usuaris per comprobar l'estat de la ultima sesio
     * 
     * @return String que indica el nom de l'usuari de l'ultima sesio o Invitado si no habia cap sesio oberta
     * 
     * @author Hossam
     */
    public static String getEstado(){
        return GestorUsuarios.obtenerEstado();
    }

    /** 
     * Funcio que crida al gestor d'usuaris per actualitzar l'estat de la sesio, guarda el nom de 
     * l'usuari actual o Invitado en cas de no tenir ninguna sesio obreta
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * 
     * @author Hossam
     */
    public static void actualizaEstado(String user){
        GestorUsuarios.actualizaEstado(user);
    }

    /**
     * Modifica el document associat al teclat "nomTec" de l'usuari "user" amb el nou document proporcionat.
     *
     * @param user String que representa el nom de l'usuari propietari del teclat
     * @param nomTec String que indica el nom del teclat
     * @param documento String que conté el nou document del teclat
     * @return Boolean que indica si s'ha modificat correctament el document del teclat
     *
     * @author Hossam
     */
    public static boolean modificarDocumentoTeclado(String user, String nomTec, String documento){
        return GestorDocumentos.crearDocumento(user, nomTec, documento);
    }

    /**
     * Modifica el nom del teclat "nomTec" pertanyent a l'usuari "user" pel nou nom indicat.
     *
     * @param path String que indica el path de la carpeta de l'usuari
     * @param nomTec String que representa el nom actual del teclat
     * @param user String que correspon al nom de l'usuari propietari del teclat
     * @return Boolean que indica si s'ha modificat correctament el nom del teclat
     *
     * @author Hossam
     */
    public static boolean modificarNombreTeclado(String path, String nomTec, String user){
        if (GestorTeclados.existeTeclado(user, nomTec)) return false;
        else GestorTeclados.cambiarNombrTeclado(path, nomTec);
        return true;
    }

    /** 
     * Funcio que retorna el nom de tots els usuaris que s'han creat excepte l'usuari user
     * 
     * @param user String que conte el nom de l'usuari actual
     * @return Array de Strings que conte els noms del usuaris que hi ha creats
     * 
     * @author Hossam
     */
    public static String[] getUsuarios(String user){
        return GestorUsuarios.retornaUsuarios(user);
    }

    /**
     * Funcio que fa crides al gestor de teclats per modificar el nom de l'usuari actual 
     * si no existeix un amb el nou nom user
     * 
     * @param path String que indica la path de la carpeta de l'usuari user
     * @param user String que indica el nou nom per l'usuari 
     * 
     * @return Boolea que es true si  s'ha modificat el nom del usuari
     * 
     * @author Hossam
     */
    public static boolean modificaNombreUsuario(String path, String user){
        if (GestorUsuarios.existeUsuario(user)) return false;
        else{
            GestorUsuarios.cambiarNombreUsuario(path, user);
            return true;
        }
    }

    /** 
     * Funcio que fa una crida al gestor d'usuaris per obtenir la contrasena de l'usuari user
     * 
     * @param user String que conte el nom de l'usuari de qui es el teclat
     * @return String que correspon a la password de l'usuari
     * 
     * @author Hossam
     */
    public static String getUserPassword(String user){
        return GestorUsuarios.obtenerPasswordUser(user);
    }

    /** 
     * Funcio que fa una crida al gestor d'usuaris per eliminar l'usuari user
     * 
     * @param user String que conte el nom de l'usuari que volem eliminar
     * 
     * @author Hossam
     */
    public static void deleteUser(String user){
        GestorUsuarios.eliminarUsuario(user);
    }

    /** 
     * Funcio que fa una crida al gestor de teclats per obtenir el nom dels teclats que te l'usuari user
     * 
     * @param user String que conte el nom de l'usuari del que volem els teclats
     * @return Array que conte el nom dels teclats de l'usuari user
     * 
     * @author Hossam
     */
    public static String[] getTecladosUsuario(String user){
        return GestorTeclados.retornaTeclados(user);
    }

}
