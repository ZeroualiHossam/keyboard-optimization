package ControladorsDePresentacio;

import javax.swing.*;
import Presentacio.*;

import ControladorPresentacio.*;

public class CtrlMainView {

    JFrame acceso;

    /**
     * Funcio encarregada de mostrar la vistsa de la pantalla principal
     * 
     * @author Ona
     */
    public void muestraPaginaPrincipal() {
        if (acceso != null)
            acceso.setVisible(false);
        String[] teclados = CtrlPresentacion.obtenerTecs();
        acceso = new PaginaPrincipalView(this, teclados);
        acceso.setVisible(true);
    }

    /**
     * Funcio encarregada de mostrar la vista per crear un nou teclat
     * 
     * @author Ona
     */
    public void vistaCrearTeclado() {
        if (acceso != null)
            acceso.setVisible(false);
        acceso = new NuevoTecladoView(this);
        acceso.setVisible(true);
    }

    /**
     * Funcio que actualitza l'estat de l'aplicacio i fa una crida al controlador
     * de presentacio accedir a l'usuari user
     * 
     * @param user String que correspon al nom de l'usuari que es vol carregar el
     *             seu perfil
     * 
     * @author Ona
     */
    public void aceedeUsuario(String user) {
        if (acceso != null)
            acceso.setVisible(false);
        CtrlPresentacion.cargaPerfil(user);
    }

    /**
     * Funcio que actualitza la vista del perfil de l'usuari.
     * 
     * 
     * @author Ona
     */
    public void vistaPerfilUsuario() {
        if (acceso != null)
            acceso.setVisible(false);
        String user = CtrlPresentacion.getNombreUsuario();
        acceso = new ProfileView(this, user);
        acceso.setVisible(true);
    }

    /**
     * Funcio encarregada de validar les dades proporcionades per crear un teclat.
     * Si les dades pasades com a parametre son correctes, crida al controlador de
     * presentacio per crear el teclat. Si son dades incorrectes, mostra missatges
     * d'error.
     * 
     * @param nombreTec  String que correspon al nom del teclat que s'esta creant
     * @param documento  String que correspon al document introduit per l'usuari
     * @param algoritmo1 Boolea que indica si l'usuari ha triat l'algoritme 1
     * @param algoritmo2 Boolea que indica si l'usuari ha triat l'algoritme 2
     * @author Ona
     */
    public void crearTeclado(String nombreTec, String documento, boolean algoritmo1, boolean algoritmo2) {
        if (nombreTec == null || documento == null || (algoritmo1 == false && algoritmo2 == false)) {
            mostrarMensajeError("Faltan campos para rellenar");
        } else if (CtrlPresentacion.crearTeclado(nombreTec, documento, algoritmo1)) {
            if (acceso != null)
                acceso.setVisible(false);
            muestraPaginaPrincipal();
            mostrarMensajeExito("Teclado creado con éxito");

        } else
            mostrarMensajeError("Los datos introducidos son erróneos.");

    }

    /**
     * Funcio encarregada de canviar la vista a la pagina principal
     * 
     * @param vista String que indica la vista
     * @author Ona
     */
    public void vistaAnterior(String vista) {
        if (vista.equals("crear"))
            CtrlPresentacion.mostrarPrincipal();
    }

    /**
     * Funcio que mostra el missatge d'error mensaje per pantalla
     * 
     * @param mensaje String que es el missatge d'error que mostra per pantalla
     * 
     * @author Hossam
     */
    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Funcio que mostra el missatge d'exit mensaje per pantalla
     * 
     * @param mensaje String que es el missatge d'exit que mostra per pantalla
     * 
     * @author Hossam
     */
    private void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Teclado Creado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per mostrar el teclat
     * nomTec
     * 
     * @param nomTec String que correspon al nom del teclat que s'ha de mostrar
     * 
     * @author Hossam
     */
    public void muestraTeclado(String nomTec) {
        CtrlPresentacion.muestraTeclado(nomTec);
        acceso.setVisible(false);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per tal de borrar el
     * teclat passat com a parametre
     * 
     * @param nombreTec String que representa el nom del teclat que es vol eliminar
     * @author Ona
     */
    public void borrarTeclado(String nombreTec) {
        CtrlPresentacion.borrarTeclado(nombreTec);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per tal d'obtenir els
     * teclats que te l'usuari actual
     * 
     * @return Llista que conte els noms dels teclats que te l'usuari actual
     * @author Ona
     */
    public String[] obtenerTeclados() {
        return CtrlPresentacion.obtenerTecs();
    }

    /**
     * Funcio encarregada de mostrar la vista d'inici de sessio
     * 
     * @author Arnau
     */
    public void mostrarVistaInicioSesion() {
        if (acceso != null)
            acceso.setVisible(false);
        CtrlPresentacion.mostrarVistaInicioSesion("close");
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per tal d'eliminar un
     * usuari
     * 
     * @author Arnau
     */
    public void eliminarUsuario() {
        CtrlPresentacion.eliminarUsuario();
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per tal de cambiar el
     * nom de l'usuari
     * 
     * @param nombreUsuario String que representa el nom de l'usuari
     * @return String que representa el nou nom d'usuari
     * @author Arnau
     */
    public boolean cambiarNombreUsuario(String nombreUsuario) {
        return CtrlPresentacion.cambiarNombreUsuarioVista(nombreUsuario);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio per tal d'obtenir la
     * contrassenya de l'usuari
     * 
     * @param nombreUsuario String que representa el nom de l'usuari
     * @return String que representa la contrassenya de l'usuari
     * @author Arnau
     */
    public String obtenerPasswordUsuario(String nombreUsuario) {
        return CtrlPresentacion.obtenerPasswordUsuario(nombreUsuario);
    }

    /**
     * Funcio encarregada de verificar que les contrasenyes coincideixin
     * 
     * @param contrasenaUsuario   String que representa la contrasenya de l'usuari
     * @param contrasenaIngresada String que representa la contrasenya introduida
     *                            per l'usuari
     * @return Boolea que indica si les contrasenyes coincideixen o no
     * @author Arnau
     */
    public boolean verificarContrasena(String contrasenaUsuario, String contrasenaIngresada) {
        return contrasenaUsuario.equals(contrasenaIngresada);
    }

    /**
     * Funcio encarregada de mostrar la vista per canviar d'usuari
     * 
     * @author Arnau
     */
    public void cambiarUsuarios() {
        String[] users = CtrlPresentacion.obtenerUsers();
        if (acceso != null)
            acceso.setVisible(false);
        acceso = new CambiarUsuarioView(this, users);
        acceso.setVisible(true);
    }

}