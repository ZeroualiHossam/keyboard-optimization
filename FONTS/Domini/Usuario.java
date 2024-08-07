package Domini;

import java.io.File;

/**
 * Representa un usuario del sistema, el cual tiene teclados asociados.
 *
 * @author Arnau
 */
public class Usuario {

    private String nombre;
    private File carpetaUsuario;
    private String pathUsuario = "../../DATA/"; // Crear carpetas para usuarios en la carpeta DATA

    /**
     * Constructora para crear un nuevo usuario.
     * 
     * @param nombre Nombre del usuario.
     *
     * @author Arnau
     */
    public Usuario(String nombre){
        pathUsuario = pathUsuario + nombre;
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario.
     *
     * @author Arnau
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Obtiene la carpeta asociada al usuario.
     * 
     * @return El directorio asociado al usuario.
     *
     * @author Arnau
     */
    public File getCarpetaUsuario() { return carpetaUsuario; }

    /**
     * Establece la carpeta asociada al usuario.
     *
     * @param c Directorio que se asignará como carpeta del usuario.
     *
     * @author Arnau
     */
    public void setCarpetaUsuario(File c) { carpetaUsuario = c; }

    /**
     * Obtiene el path asociado al usuario.
     * 
     * @return El path asociado al usuario.
     *
     * @author Arnau
     */
    public String getPathUsuario() { return pathUsuario; }

}