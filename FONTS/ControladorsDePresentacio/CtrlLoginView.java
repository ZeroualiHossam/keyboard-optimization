package ControladorsDePresentacio;

import javax.swing.*;
import Presentacio.*;

import ControladorPresentacio.*;

public class CtrlLoginView {
    JFrame acceso;

    /**
     * Funcio que mostra el JFrame per iniciar sesio i tanca el que habia obert
     * 
     * @author Hossam
     */
    public void iniciarSesion(){
        if (acceso != null ) acceso.setVisible(false);
        acceso = new IniciarSesionView(this);
        acceso.setVisible(true);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio perque es comuniqui amb domini i persistencia per iniciar sesio
     * a l'ususari user, en cas que les dades siguin erronies mostra un error per pantalla
     * 
     * @param user String que correspon al nom de l'usuari al que es vol accedir
     * @param password String que correspon a la contrasena de l'usuari al que es vol accedir
     * 
     * @author Hossam
     */
    public void accederUsuario(String user, String password){
        if(CtrlPresentacion.iniciarSesion(user, password)){
            //Funcion para cargar los teclados
            if (acceso != null ) acceso.setVisible(false);
            CtrlPresentacion.cargaPerfil(user);
        }
        else mostrarMensajeError("Los datos introducidos son erróneos.");
        
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
     * Funcio que mostra el JFrame de crear usuari i tanca el que habia obert
     * 
     * @author Hossam
     */
    public void crearUsuario(){
        if (acceso != null ) acceso.setVisible(false);
        acceso = new CrearUsuarioView(this);
        acceso.setVisible(true);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio perque es comuniqui amb domini i persistencia per crear l'ususari user,
     *  en cas que les dades siguin erronies o no es pugui crear mostra un error per pantalla
     *
     * @param user String que correspon al nom de l'usuari que es vol crear
     * @param password String que correspon a la contrasena de l'usuari que es vol crear
     *
     * @author Hossam
     */
    public void creaUser(String user, String password){
        if(CtrlPresentacion.creaUser(user, password)){
            if (acceso != null ) acceso.setVisible(false);
            CtrlPresentacion.cargaPerfil(user);
        }
        else mostrarMensajeError("El username ya está en uso o alguno de los campos es erróneo.");
        
    }

    

}
