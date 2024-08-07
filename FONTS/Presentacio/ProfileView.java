package Presentacio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ControladorsDePresentacio.*;

public class ProfileView extends JFrame implements ActionListener {

    CtrlMainView cm;
    private JLabel titulo = new JLabel("Perfil");
    private JLabel nameUser = new JLabel();
    private JLabel nombreUsuario = new JLabel("Modificar Nombre de Usuario");
    private JButton eliminarCuentaBtn = new JButton("Eliminar Cuenta");
    private JButton cambiarUsuarioBtn  = new JButton("Cambiar Usuario");
    private JButton volverButton = new JButton("Volver");
    private JButton logoutBtn = new JButton("Logout");
    private JTextField nombreField = new JTextField();
    private JButton guardarNombreBtn = new JButton("Guardar Nombre");
    JButton vistaAnterior = new JButton("<-");

    private static final String CONFIRM_DELETE_MESSAGE = "¿Estás seguro que quieres eliminar la cuenta?";

    /**
     * Vista que representa el perfil de l'usuari a l'aplicació. Proporciona opcions per canviar el nom d'usuari,
     * eliminar el compte i tancar la sessió.
     * 
     * @param cMain Controlador Main per fer crides a altres funcions
     * @param user Nom de l'usuari actual
     *
     * @author Arnau
     */
    public ProfileView(CtrlMainView cMain, String user) {

        // Configuración de los componentes
        cm = cMain;
        setSize(414, 636);

        int xBase = getWidth() / 10;
        int yBase = getHeight() / 2;

        Font titulos = new Font("Arial", Font.BOLD, 20);

        titulo.setFont(titulos);

        eliminarCuentaBtn.addActionListener(this);
        cambiarUsuarioBtn.addActionListener(this);
        logoutBtn.addActionListener(this);
        volverButton.addActionListener(this);
        guardarNombreBtn.addActionListener(this);

        nameUser.setText(user);
        nameUser.setFont(titulos);

        nombreField.setText(user);
        nameUser.setBounds(xBase, yBase/5, (getWidth() * 2) / 3, getHeight() / 15);
        nombreUsuario.setBounds(xBase, yBase / 3, (getWidth() * 2) / 3, getHeight() / 15);
        nombreField.setBounds(xBase, yBase / 3 + nombreUsuario.getHeight(), getWidth() - xBase * 2, getHeight() / 20);
        guardarNombreBtn.setBounds(getWidth() / 2, yBase / 4 + nombreUsuario.getHeight() + 2 * nombreField.getHeight(), getWidth() / 2 - xBase, getHeight() / 20);
        cambiarUsuarioBtn.setBounds(xBase, yBase/2 + yBase/4 , getWidth() - 4*(getWidth()/20) , getHeight() / 10);
        vistaAnterior.setBounds(xBase/4, xBase/4, getWidth()/10, getWidth()/12);
        vistaAnterior.addActionListener(this);
        vistaAnterior.setFocusable(false);

        eliminarCuentaBtn.setBounds(xBase, yBase/2 + yBase/4 + cambiarUsuarioBtn.getHeight() , getWidth() - 4*(getWidth()/20) , getHeight() / 10);
        logoutBtn.setBounds(xBase, yBase/2 + yBase/4 + 2*cambiarUsuarioBtn.getHeight(), getWidth() - 4*(getWidth()/20) , getHeight() / 10);
       
        add(eliminarCuentaBtn);
        add(vistaAnterior);
        add(nameUser);
        add(nombreField);
        add(nombreUsuario);
        add(guardarNombreBtn);
        add(cambiarUsuarioBtn);
        add(logoutBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
    }

    /**
     * Funció per gestionar les accions dels botons i altres elements interactius de la vista.
     *
     * @param e Event d'acció generat per l'interacció amb els botons de la vista.
     * @author Arnau
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cambiarUsuarioBtn) {
            cm.cambiarUsuarios();
        }
        else if (e.getSource() == logoutBtn) {
            cm.mostrarVistaInicioSesion();
            this.dispose();
            JOptionPane.showMessageDialog(
                    this,
                    "Se ha cerrado la sesión con éxito",
                    "Sesión cerrada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        else if (e.getSource() == vistaAnterior){
            cm.muestraPaginaPrincipal();
        }
        else if (e.getSource() == eliminarCuentaBtn) {
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    CONFIRM_DELETE_MESSAGE,
                    "Eliminar cuenta",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                cm.eliminarUsuario();
                cm.mostrarVistaInicioSesion();
                this.dispose();
                JOptionPane.showMessageDialog(
                        this,
                        "Cuenta eliminada con éxito",
                        "Cuenta eliminada",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
        else if (e.getSource() == guardarNombreBtn) {
            String nuevoNombre = nombreField.getText();

            boolean nombreCambiado = cm.cambiarNombreUsuario(nuevoNombre);

            if (nombreCambiado) {
                cm.muestraPaginaPrincipal(); //enviar a pantalla login, principal, perfil?
                this.dispose();
                JOptionPane.showMessageDialog(
                        this,
                        "Nombre de usuario modificado con éxito",
                        "Modificación exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "El nombre de usuario no se ha podido cambiar",
                        "Error al cambiar el nombre",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

    }

}