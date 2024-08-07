package Persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;



public class GestorDocumentos {
    
    /**
     * Funcio que guarda el document pel teclat nameTec de l'usuari nameUser
     * 
     * @param nameUser String que es el nom de l'usuari a qui pertany el teclat i document
     * @param nameTec String que es el nom del teclat a qui correspon el document
     * @param documento String que correspon al document del teclat
     * @return Boolea que es true si s'ha pogut crear el document
     * 
     * @author Hossam
     */
    public static boolean crearDocumento(String nameUser, String nameTec, String documento){
    
        try {
            File file = new File("../../DATA/" + nameUser + File.separator +  nameTec + File.separator + "doc");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(documento);
            writer.close();
            return true;
        }
        
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Funcio que obte el document del teclat nameTec de l'usuari nameUser
     * 
     * @param nameUser String que es el nom de l'usuari a qui pertany el teclat i document
     * @param nameTec String que es el nom del teclat a qui correspon el document
     * @return String que correspon al contigut del document del teclat nameTec de l'usuari nameUser
     * 
     * @author Hossam
     */
    public static String obtenerDocumento(String nameUser, String nameTec){
        String documento = " ";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("../../DATA/" + nameUser + File.separator +  nameTec + File.separator + "doc"));) {
            String line;
            StringBuilder contenido = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                contenido.append(line).append("\n");
            }

            documento = contenido.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return documento;
    }

    /**
     * Funcio que elimina el document del teclat nameTec de l'usuari nameUser
     * 
     * @param nameUser String que es el nom de l'usuari a qui pertany el teclat i document
     * @param nameTec String que es el nom del teclat a qui correspon el document
     * 
     * @author Hossam
     */
    public static void eliminarDocumento(String nameUser, String nameTec){

        File elim = new File("../../DATA/" + nameUser + File.separator +  nameTec + File.separator + "doc");
        if (elim.exists() && !elim.isDirectory()) elim.delete();
        
    }

}
