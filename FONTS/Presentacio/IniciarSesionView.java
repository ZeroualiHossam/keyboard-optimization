package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControladorsDePresentacio.*;


public class IniciarSesionView extends JFrame {

    CtrlLoginView cl;
    JButton accederButton = new JButton("Acceder");
    JButton registerButton = new JButton("Registrarse");
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField("Password");
    JLabel textoPag = new JLabel("GESTOR DE TECLADOS");

    /**
     *  Constructora que ubica i defineix els elements que es necesiten al JFrame per fer el cas d'us iniciar sesio
     * o canviar de JFrame per registrarse
     * 
     * @param clo Controlador per fer crides a altres funcions i gestionar els altres JFrames
     * 
     * @author Hossam
     */
    public IniciarSesionView(CtrlLoginView clo) {

        cl = clo;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 636);
        setLayout(null);
        setLocationRelativeTo(null);

        passwordText.setEchoChar((char) 0);
        Font nFont = new Font("Arial", Font.PLAIN, 24);
        textoPag.setFont(nFont);
        
        accederButton.setFocusable(false); //Que no se pueda marcar.
        registerButton.setFocusable(false); //Que no se pueda marcar.
        
        textoPag.setBounds(getWidth()/7, getHeight()/6, getWidth() - (2*getWidth()/9), getHeight()/10);
        usernameText.setBounds(getWidth()/15,  getHeight()/3, getWidth() - 2*getWidth()/15, getHeight()/20);
        passwordText.setBounds(getWidth()/15,  getHeight()/3 + usernameText.getHeight() + usernameText.getHeight()/2, getWidth() - 2*getWidth()/15, getHeight()/20);
        accederButton.setBounds(getWidth()/4, getHeight()/2+ getHeight()/30, getWidth()/2, getHeight()/10);
        registerButton.setBounds(getWidth()/4, getHeight()/2 + accederButton.getHeight() + getHeight()/24, getWidth()/2, getHeight()/10);
        
        add(usernameText);
        add(passwordText);
        add(accederButton);
        add(registerButton);
        add(textoPag);

        // Agrega un ComponentListener al JFrame para manejar eventos de redimensionamiento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajusta el tamaño y la posición de los componentes cuando la ventana se redimensiona
                adjustComponentSizesAndPositions();
            }
        });

        usernameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameText.getText().equals("Username")) usernameText.setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameText.getText().isEmpty()) usernameText.setText("Username");
            }
        });

        passwordText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] password = passwordText.getPassword();
                if (password.length > 0 && String.valueOf(password).equals("Password")) {
                    passwordText.setText("");
                    passwordText.setEchoChar('*'); // Ocultar el texto al escribir
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] password = passwordText.getPassword();
                if (password.length == 0) {
                    passwordText.setText("Password");
                    passwordText.setEchoChar((char) 0); // Mostrar el texto cuando está vacío
                }
            }
        });

        accederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass = passwordText.getPassword();
                String password = new String(pass);
                cl.accederUsuario(usernameText.getText(), password);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.crearUsuario();
            }
        });

    }

    /**
     * Funcio que fa el resize dels components del JFrame
     *
     * @author Hossam
     */
    private void adjustComponentSizesAndPositions() {
        usernameText.setBounds(getWidth()/15,  getHeight()/3, getWidth() - 2*getWidth()/15, getHeight()/20);
        passwordText.setBounds(getWidth()/15,  getHeight()/3 + usernameText.getHeight() + usernameText.getHeight()/2, getWidth() - 2*getWidth()/15, getHeight()/20);
        accederButton.setBounds(getWidth()/4, getHeight()/2+ getHeight()/30, getWidth()/2, getHeight()/10);
        registerButton.setBounds(getWidth()/4, getHeight()/2 + accederButton.getHeight() + getHeight()/24, getWidth()/2, getHeight()/10);
    }
}
