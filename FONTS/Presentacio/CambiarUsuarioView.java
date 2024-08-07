package Presentacio;

import ControladorsDePresentacio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarUsuarioView extends JFrame implements ActionListener {

    CtrlMainView cm;
    JList<String> listaUsuarios;
    JButton seleccionarUsuarioBtn;
    JButton volverButton;
    JPasswordField passwordField;

    /**
     * Constructor que mostra la llista d'usuaris disponibles i permet canviar d'usuari.
     *
     * @param cMain Controlador principal per a la gestió del canvi d'usuari i altres funcionalitats.
     * @param usuarios Array que conté els noms dels usuaris per seleccionar-ne un.
     * @author Arnau
     */
    public CambiarUsuarioView(CtrlMainView cMain, String[] usuarios) {
        cm = cMain;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Selecciona el usuario");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        panel.add(titulo, constraints);


        listaUsuarios = new JList<>(usuarios);
        if(usuarios[0].equals("No hay mas usuarios creados"));
        
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, constraints);

        seleccionarUsuarioBtn = new JButton("Seleccionar Usuario");
        seleccionarUsuarioBtn.addActionListener(this);
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(seleccionarUsuarioBtn, constraints);

        volverButton = new JButton("Volver");
        volverButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        panel.add(volverButton, constraints);

        panel.setBounds(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 636);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Funció per gestionar les accions dels botons i altres elements interactius de la vista.
     *
     * @param e Event d'acció generat per l'interacció amb els botons de la vista.
     * @author Arnau
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == seleccionarUsuarioBtn) {
            String usuarioSeleccionado = listaUsuarios.getSelectedValue();
            if (usuarioSeleccionado != null && !usuarioSeleccionado.equals("No hay mas usuarios creados")) {
                JPanel passwordPanel = new JPanel();
                passwordField = new JPasswordField(20);
                passwordPanel.add(new JLabel("Contraseña:"));
                passwordPanel.add(passwordField);

                int result = JOptionPane.showConfirmDialog(null, passwordPanel, "Introduce la contraseña",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {

                    char[] password = passwordField.getPassword();
                    String enteredPassword = new String(password);
                    String contrasenaUsuario = cm.obtenerPasswordUsuario(usuarioSeleccionado);
                    boolean contrasenaCorrecta = cm.verificarContrasena(contrasenaUsuario, enteredPassword);

                    if (contrasenaCorrecta) {
                        cm.aceedeUsuario(usuarioSeleccionado);
                        JOptionPane.showMessageDialog(null, "Usuario cambiado con éxito");
                        this.dispose();
                    } 
                    else JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        }
        else if (e.getSource() == volverButton) {
            cm.vistaPerfilUsuario();
            this.dispose();
        }
    }
}