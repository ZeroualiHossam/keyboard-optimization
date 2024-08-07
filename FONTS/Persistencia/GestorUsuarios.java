package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;


//Iniciar seesion desde aqui o mirar si existe para crearlo

public class GestorUsuarios {

    public GestorUsuarios (){

    }

    /**
     * Funcio que obte l'estat de l'aplicacio, el nom de l'usuari que esta obert o Invitado si no hi ha cap sesio oberta
     * 
     * @return String que correspon a Invitado o nom de l'ultim usuari obert
     * 
     * @author Hossam
     */
    public static String obtenerEstado(){
        String state ="";

        String rutaArchivo = "../../DATA/state";
        File estado = new File(rutaArchivo);

        if (!estado.exists()){
            return "Invitado";
        }
        else{

            try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA/"  + File.separator + "state"));) {
            String line;

            if ((line = reader.readLine()) != null) {
                state = line;
            }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return state;
    }

    /**
     * Funcio que modifica l'estat de l'aplicacio cambiant el nom de l'ultim usuari obert
     * 
     * @param nameUser String que correspon al nom del usuari actual o Invitado si s'ha tancat sesio
     * @return Boolea que es true si s'ha modificat correctament l'estat de l'aplicacio
     * 
     * @author Hossam
     */
    public static boolean actualizaEstado(String nameUser){

        try {
            File file = new File("../../DATA/" + File.separator + "state");    
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(nameUser);
            writer.close();
            return true;
        }
            
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Funcio que crea un nou usuari amb el nom user i contrasena password
     * 
     * @param user String que correspon al nom de l'usuari que es vol crear
     * @param password String que correspon a la password de l'usuari user
     * 
     * @author Hossam
     */
    public static void crearUsuario(String user, String password){

        File carpetaUsuario = new File("../../DATA" + File.separator + user);
        carpetaUsuario.mkdir();

        try  (BufferedWriter writer = new BufferedWriter(new FileWriter(carpetaUsuario.getAbsolutePath() + File.separator + "pass"))){
            writer.write(password);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** Funcio que elimina l'usuari user, que es l'actual
     * 
     * @param user String que correspon al nom de l'usuari que es vol eliminar
     *
     * @author Hossam
     */
    public static void eliminarUsuario(String user) {
        
        File elim = new File("../../DATA" + File.separator + user);
        
        if (elim.exists() && elim.isDirectory()) {
            File[] contenido = elim.listFiles();

            if (contenido != null) {
                for (int i = 0; i < contenido.length; i++) {
                    File archivo = contenido[i];
                    if (archivo.isDirectory()) {
                        eliminarUsuario(elim.getName() + File.separator + archivo.getName());
                    } else {
                        archivo.delete();
                    }
                }
            }
            elim.delete();
        }
    }


    /** Funcio que modifica el nom de l'usuari que es troba al pathActual pel nou nom newNombreUsuario
     * 
     * @param pathActual Ubicacio de l'usuari que es vol modificar el seu nom
     * @param newNombreUsuario String que correspon al nou nom que es vol atribuir a l'usuari
     * 
     * @author Hossam
     */
    public static void cambiarNombreUsuario(String pathActual, String newNombreUsuario){
        Path rutaPrin = Paths.get(pathActual);
        Path nuevaRuta = Paths.get(rutaPrin.getParent().toString(), newNombreUsuario);

        try {
            Files.move(rutaPrin, nuevaRuta);
        } catch (IOException e) {
            System.err.println("Error al renombrar al usuario");
        }
    }

    /**
     * Funcio que comproba si l'usuari user existeix
     * 
     * @param user String que correspon al nom de l'usuari que es vol comprobar
     * @return Boolea que es true si l'usuari user existeix
     * 
     * @author Hossam
     */
    public static boolean existeUsuario(String user){

        File data = new File("../../DATA");
        File[] users = data.listFiles();

        if(users == null)  return false;
        else {
            for (int i = 0; i < users.length; ++i){
                if (users[i].getName().equals(user)) return true;
            }
        }
        return false;
    }

    /**
     * Funcio que obte la contrasena de l'usuari nomUser
     * 
     * @param nomUser String que correspon al nom de l'usuari del que es vol la contrasena
     * @return String que conte la password de l'usuari nomUser
     * 
     * @author Hossam
     */
    public static String obtenerPasswordUser(String nomUser){

        String pass = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA" + File.separator + nomUser + File.separator + "pass"))) {
            pass = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return pass;
    }


    /**
     * Funcio que obte els noms dels usuaris creats excepte l'usuari actual
     * 
     * @param user String que correspon al nom de l'usuari actual
     * @return Array que conte els noms dels usuaris creats excepte l'usuari user
     * 
     * @author Hossam
     */
    public static String[] retornaUsuarios(String user){

        File data = new File("../../DATA");
        File[] users = data.listFiles();

        int pos = 0, num = 1;
        String[] usuarios;
        for (int i = 0; i < users.length; ++i) if (users[i].getName().equals(".DS_Store")) ++num;

        if ((users.length - num - 1) == 0) usuarios =  new String[]{"No hay mas usuarios creados"};
        else usuarios = new String[users.length-num];

        for (int i = 0; i < users.length; ++i) {
            if (!users[i].getName().equals(user) && !users[i].getName().equals("state") && !users[i].getName().equals(".DS_Store")){
                usuarios[pos] = users[i].getName();
                ++pos;
            }

        }
        return usuarios;
    }

}