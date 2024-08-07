package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;


public class GestorTeclados {

    /**
     * Funcio que comproba si el teclat nameTec de l'usuari nameUser existeix
     * 
     * @param nameUser String que correspon al nom de l'usuari que es vol comprobar
     * @param nameTec String que correspon al nom del teclat que es vol comprobar
     * @return Boolea que es true si el teclat nameTec existeix
     * 
     * @author Hossam
     */
    public static boolean existeTeclado(String nameUser, String nameTec){
        
        File data = new File("../../DATA" + File.separator + nameUser);
        File[] tecs = data.listFiles();

        if(tecs == null)  return false;
        else {
            for (int i = 0; i < tecs.length; ++i){
                if (tecs[i].getName().equals(nameTec)) return true;
            }
        }
        return false;
        
    }
    
    /**
     * Funcio que crea el directori que contindra els documents del teclat nameTec
     * 
     * @param nameUser String que correspon al nom de l'usuari qui crea el teclat
     * @param nameTec String que correspon al nom del teclat que es vol crear
     * 
     * @author Hossam
     */
    public static void crearTeclado(String nameUser, String nameTec){
        File carpetaTeclado = new File("../../DATA/" + nameUser + File.separator +  nameTec);
        carpetaTeclado.mkdir();
        
    }

    /** Funcio que elimina el teclat que indica userTec
     * 
     * @param userTec String que conte el nom de l'usuari i el teclat que es vol eliminar
     *
     * @author Hossam
     */
    public static void eliminarTeclado(String userTec) {
        File elim = new File("../../DATA" + File.separator + userTec);
        
        if (elim.exists() && elim.isDirectory()) {
            File[] contenido = elim.listFiles();

            if (contenido != null) {
                for (int i = 0; i < contenido.length; i++) {
                    File archivo = contenido[i];
                    if (archivo.isDirectory()) {
                        eliminarTeclado(elim.getName() + File.separator + archivo.getName());
                    } else {
                        archivo.delete();
                    }
                }
            }
            elim.delete();
        }
    }

    /**
     * Funcio que obte els noms dels teclats creats excepte l'usuari user
     * 
     * @param user String que correspon al nom de l'usuari del que es volen els teclats
     * @return Array que conte els noms dels teclats que te creats l'usuari user
     * 
     * @author Hossam
     */
    public static String[] retornaTeclados(String user){

        File data = new File("../../DATA" + File.separator + user);
        File[] tecs = data.listFiles();
        int pos = 0, num = 1;
        for (int i = 0; i < tecs.length; ++i) if (tecs[i].getName().equals(".DS_Store")) ++num;
        
        String[] teclados = new String[tecs.length-num];
        
        if (teclados.length != 0){
            for (int i = 0; i < tecs.length; ++i) {
                if (!tecs[i].getName().equals("pass") && !tecs[i].getName().equals(".DS_Store")){
                    teclados[pos] = tecs[i].getName();
                    ++pos;
                    
                }
            }
        } 

        if (pos == 0){
            String[] notecs = {"No hay teclados creados."};
            teclados = notecs;
        }

        return teclados;
    }
    /**
     * Funcio que guarda les dades que composen el teclat a un arxiu de text
     * 
     * @param alfabeto String que correspon al alfabet del teclat
     * @param path String que correspon al path de la carpeta del teclat 
     * @param filas int que te el valor de files que te el teclat
     * @param columnas int que te el valor de columnes que te el teclat
     * @param alg int que indica el algoritmo utilizado, 1 para QAP y 2 para Hill Climbing
     * @return Boolea que es true si s'ha pogut guardar el teclat
     * 
     * @author Hossam
     */
    public static boolean guardarTeclado(String path, String  alfabeto, int filas, int columnas, int alg){

        int mida = alfabeto.length();
        char[] posiciones = alfabeto.toCharArray();
        
        try  (BufferedWriter writer = new BufferedWriter(new FileWriter(path + File.separator + "info"))){

            writer.write(String.valueOf(filas));
            writer.newLine();
            writer.write(String.valueOf(columnas));
            writer.newLine();
            writer.write(String.valueOf(alg));
            writer.newLine();
            for (int i = 0; i < mida; ++i) {
                writer.write(posiciones[i]);
                if (i + 1 < mida) writer.newLine();
            }

            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
     /**
     * Funcio que obte l'alfabet del teclat que indica userTec
     * 
     * @param userTec String que conte el nom de l'usuari i el teclat del que es vol obtenir l'alfabet
     * @return String que correspon a l'alfabet del teclat
     * 
     * @author Hossam
     */
    public static String obtenerAlfabetoTeclado(String userTec){

        String alfabeto = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA" + File.separator + userTec + File.separator + "info"))) {
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null) {
                    if (num  > 2) alfabeto = alfabeto + line;
                    ++num;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return alfabeto;
    }

     /**
     * Funcio que obte el valor de les columnes del teclat que indica userTec
     * 
     * @param userTec String que conte el nom de l'usuari i el teclat del que es volen obtenir les columnes
     * @return int que correspon al tamany de les columnes del teclat indicat per userTec
     * 
     * @author Hossam
     */
    public static int obtenerColumnasTeclado(String userTec){

        String columnas = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA" + File.separator + userTec + File.separator + "info"))) {
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null && num < 2) {
                    if (num  == 1) columnas =  line;
                    ++num;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int col;
        try {
            col = Integer.parseInt(columnas);
        } catch (NumberFormatException e) {
            col = -1; 
            System.err.println("Error al analizar la fila como entero: " + e.getMessage());
        }
        return col;
    }

     /**
     * Funcio que obte el valor de les files del teclat que indica userTec
     * 
     * @param userTec String que conte el nom de l'usuari i el teclat del que es volen obtenir les files
     * @return int que correspon al tamany de les files del teclat indicat per userTec
     * 
     * @author Hossam
     */
    public static int obtenerFilasTeclado(String userTec){

        String filas = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA" + File.separator + userTec + File.separator + "info"))) {
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null && num < 1) {
                    if (num  == 0) filas =  line;
                    ++num;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int fila;

        try {
            fila = Integer.parseInt(filas);
        } catch (NumberFormatException e) {
            fila = -1; 
            System.err.println("Error al analizar la fila como entero: " + e.getMessage());
        }
        return fila;
    }
    
    /**
     * Funcio que obte el valor de quin algorisme ha utilitzat el teclat que indica userTec per crearse
     * 
     * @param userTec String que conte el nom de l'usuari i el teclat del que es vol obtenir l'algorisme
     * @return int que correspon a 1 per QAP, o 2 per Hill Climbing 
     * 
     * @author Hossam
     */
    public static int obtenerAlgTeclado(String userTec){

        String alg = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA" + File.separator + userTec + File.separator + "info"))) {
            String line;
            int num = 0;
            while ((line = reader.readLine()) != null && num < 3) {
                    if (num  == 2) alg =  line;
                    ++num;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        int algoritmo;

        try {
            algoritmo = Integer.parseInt(alg);
        } catch (NumberFormatException e) {
            // Manejo de la excepción: proporciona un valor predeterminado o un mensaje de error
            algoritmo = -1; // O cualquier otro valor predeterminado que desees
            System.err.println("Error al analizar la fila como entero: " + e.getMessage());
        }
        return algoritmo;
    }

    /** Funcio que modifica el nom del teclat que es troba al pathActual pel nou nom newNombreTeclado
     * 
     * @param pathActual Ubicacio del teclat que es vol modificar el seu nom
     * @param newNombreTeclado String que correspon al nou nom que es vol atribuir al teclat
     * 
     * @return Boolea que es true si s'ha cambiat el nom del teclat
     * 
     * @author Hossam
     */
    public static boolean cambiarNombrTeclado(String pathActual, String newNombreTeclado){
        Path rutaPrin = Paths.get(pathActual);
        Path nuevaRuta = Paths.get(rutaPrin.getParent().toString(), newNombreTeclado);

        try {
            Files.move(rutaPrin, nuevaRuta);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}



