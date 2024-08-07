package ControladorsDePresentacio;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Presentacio.*;

import ControladorPresentacio.*;

public class CtrlTecladoView {
    JFrame acceso;

    /**
     * Funcio que mostra el JFrame ver teclado, en el que es mostra el teclat nomTec
     * 
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol mostrar
     * @param filas int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param nomTec String que correspon al nom del teclat
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     * 
     * @author Hossam
     */
    public  void mostrarTeclado(String alfabeto, int filas, int columnas, String nomTec, int alg){
        acceso = new VerTecladoView(this, alfabeto, filas, columnas, nomTec, alg);
        acceso.setVisible(true);
    }

    /**
     * Funcion que mostra el JFrame editar teclado, en el que es pot editar el nom y document del teclat nomTec
     * 
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol mostrar
     * @param filas int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param nomTec String que correspon al nom del teclat
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     * 
     * @author Hossam
     */
    public void muestraVistaEditarTeclado(String nomTec, String alfabeto, int filas, int columnas, int alg){
        if (acceso != null) acceso.setVisible(false);
        String doc = CtrlPresentacion.obtenerDocTeclado(nomTec);
        acceso = new EditarTecladoView(this, nomTec, doc, alfabeto, filas, columnas, alg);
        acceso.setVisible(true);
    }

    /**
     * Funcio que retorna a la vista anterior, si estavem a editar teclado tornem a ver teclado
     * i si estavem a ver teclado tornem a la pagina principal
     * 
     * @param vista String que indica el nom de la vista que volem mostrar
     * @param nomTec String del nom del teclat que estabem mostrant o editant
     * 
     * @author Hossam
     */
    public void vistaAnterior(String vista, String nomTec){
        if (acceso != null) acceso.setVisible(false);
        if (vista.equals("principal")) CtrlPresentacion.mostrarPrincipal();
        else if (vista.equals("editar")) CtrlPresentacion.muestraTeclado(nomTec);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio perque contacti amb domini i modifiqui el nom del teclat nombreTec,
     * si es realitza be mostra un missatge d'exit, sino un missatge d'error
     * 
     * @param nomTec String que correspon al nom actual del teclat
     * @param newNomTec String que correspon al nom nou del teclat
     * 
     * @author Hossam
     */
    public void modificaNomTec(String nomTec, String newNomTec){
        if (!CtrlPresentacion.modificaNomTec(nomTec, newNomTec))mostrarMensajeError("El nombre ya está en uso o no es correcto.");
        else mostrarMensajeExito("Se ha guardado correctamente el nuevo nombre");
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
        JOptionPane.showMessageDialog(null, mensaje, "Modificacion Realizada", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Funcio que fa una crida al controlador de presentacio perque contacti amb domini i guardi el nou document del teclat nomTec,
     * si es guarda correctament es retorna a l'usuari a la vista pagina principal i mostra un missatge d'exit, sino mostra missatge d'error
     * 
     * @param nomTec String que correspon al nom del teclat
     * @param newDocTec String que correspon al contingut del document del teclat nombreTec
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     * 
     * @author Hossam
     */
    public void modificaDocTec(String nomTec, String newDocTec, int alg){
        if (newDocTec.equals("") || newDocTec.equals(" ") || newDocTec.equals(null) || !CtrlPresentacion.modificaDocTec(nomTec, newDocTec, alg)) mostrarMensajeError("No se ha podido guardar el documento.");
        else {
            if (acceso != null) acceso.setVisible(false);
            CtrlPresentacion.mostrarPrincipal();
            mostrarMensajeExito("Se ha guardado correctamente el documento");
        }
    }

    /**
     * Funcio que fa una crida al controlador de presentacio perque contacti amb domini i guardi la nova distribucio del teclat nomTec
     * si es guarda be mostra un missatge d'exit, sino un missatge d'error
     * 
     * @param nomTec String que correspon al nom del teclat
     * @param filas int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol mostrar
     * 
     * @author Hossam
     */
    public void modificaDistribucionTec(String nomTec, int filas, int columnas, String alfabeto){
        if (!CtrlPresentacion.modificaDistribucionTec(nomTec, filas, columnas, alfabeto, 0)) mostrarMensajeError("No se ha podido guardar el documento.");
        else mostrarMensajeExito("Se ha guardado correctamente la nueva distribución");
    }
}
