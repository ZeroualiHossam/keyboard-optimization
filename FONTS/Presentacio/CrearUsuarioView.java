package Presentacio;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ControladorsDePresentacio.*;

public class CrearUsuarioView extends JFrame implements ActionListener {
    JButton crearUserButton = new JButton("Crear Usuario");
    JButton volverButton = new JButton("Volver a Iniciar Sesion");
    JTextField usernameText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JLabel textoCrearUsuario = new JLabel("Crea tu Username");
    JLabel textoCrearPass = new JLabel("Crea tu contraseña");
    JLabel tituloPagina = new JLabel("Crear Usuario");

    CtrlLoginView cl;

    /**
     *  Constructora que ubica i defineix els elements que es necesiten al JFrame per fer el cas d'us crear usuari
     * o canviar de JFrame per iniciar sesio
     * 
     * @param clo Controlador per fer crides a altres funcions i gestionar els altres JFrames
     * 
     * @author Hossam
     */
    public CrearUsuarioView(CtrlLoginView clo) {

        cl = clo;
        setSize(414, 636);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        int xBase = getWidth()/10;
        int yBase = getHeight()/5;

        Font tFont = new Font("Arial", Font.PLAIN, 26);
        tituloPagina.setFont(tFont);
        

        Font nFont = new Font("Arial", Font.PLAIN, 14);
        textoCrearUsuario.setFont(nFont);
        textoCrearPass.setFont(nFont);

        tituloPagina.setBounds(xBase, yBase/4, getWidth(), getHeight()/10);
        textoCrearUsuario.setBounds(xBase, yBase, (getWidth()*2)/3, getHeight()/15);
        usernameText.setBounds(xBase, textoCrearUsuario.getHeight()+yBase, (getWidth()*3)/4, getHeight()/20);
        textoCrearPass.setBounds(xBase,  yBase + 2*usernameText.getHeight() + textoCrearUsuario.getHeight(), (getWidth()*3)/2, getHeight()/15);
        passwordText.setBounds(xBase, yBase + 2*usernameText.getHeight() + textoCrearUsuario.getHeight() + textoCrearPass.getHeight(), (getWidth()*3)/4, getHeight()/20);
        crearUserButton.setBounds(getWidth()/4, yBase*3 - textoCrearUsuario.getHeight()/2, getWidth()/2, getHeight()/10);
        volverButton.setBounds(getWidth()/4, yBase*3 + crearUserButton.getHeight(), getWidth()/2, getHeight()/10);
        
        crearUserButton.addActionListener(this);
        crearUserButton.setFocusable(false);

        volverButton.addActionListener(this);
        volverButton.setFocusable(false);

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

        add(tituloPagina);
        add(volverButton);
        add(crearUserButton);
        add(passwordText);
        add(textoCrearPass);
        add(usernameText);
        add(textoCrearUsuario);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustComponentSizesAndPositions();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volverButton) {
            cl.iniciarSesion();
        }

        else if (e.getSource() == crearUserButton){
            char[] pass = passwordText.getPassword();
            String password = new String(pass);
            cl.creaUser(usernameText.getText(), password);
        }
    }

    /**
     * Funcio que fa el resize dels components del JFrame
     *
     * @author Hossam
     */
    private void adjustComponentSizesAndPositions(){

        int xBase = getWidth()/10;
        int yBase = getHeight()/5;

        tituloPagina.setBounds(xBase, yBase/4, getWidth(), getHeight()/10);
        textoCrearUsuario.setBounds(xBase, yBase, (getWidth()*2)/3, getHeight()/15);
        usernameText.setBounds(xBase, textoCrearUsuario.getHeight()+yBase, (getWidth()*3)/4, getHeight()/20);
        textoCrearPass.setBounds(xBase,  yBase + 2*usernameText.getHeight() + textoCrearUsuario.getHeight(), (getWidth()*3)/2, getHeight()/15);
        passwordText.setBounds(xBase, yBase + 2*usernameText.getHeight() + textoCrearUsuario.getHeight() + textoCrearPass.getHeight(), (getWidth()*3)/4, getHeight()/20);
        crearUserButton.setBounds(getWidth()/4, yBase*3 - textoCrearUsuario.getHeight()/2, getWidth()/2, getHeight()/10);
        volverButton.setBounds(getWidth()/4, yBase*3 + crearUserButton.getHeight(), getWidth()/2, getHeight()/10);
    }

}



