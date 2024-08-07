package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import ControladorsDePresentacio.*;

public class NuevoTecladoView extends JFrame {

    CtrlMainView cm;
    JLabel titulo = new JLabel("CREAR TECLADO");
    JLabel titulo2 = new JLabel("Introduce el nombre del nuevo teclado:");
    JLabel titulo3 = new JLabel("Introduce el texto o adjunta un archivo:");
    JTextField nombreTeclado = new JTextField();
    JTextArea textoTeclado = new JTextArea();
    JButton adjuntarArchivo = new JButton("Adjuntar .txt");
    JButton crearTecladoButton = new JButton("CREAR TECLADO");
    JButton cancelar = new JButton("Cancelar");
    JLabel titulo4 = new JLabel("Escoje que algoritmo quieres usar:");
    JRadioButton algoritmo1 = new JRadioButton("QAP");
    JRadioButton algoritmo2 = new JRadioButton("Hill Climbing");
    ButtonGroup group = new ButtonGroup();
    JButton vistaAnterior = new JButton("<-");
    JScrollPane scrollDoc = new JScrollPane();

    /**
     * Constructora que ubica i defineix els elements que es necesiten al JFrame per
     * crear un nou teclat
     * 
     * @param cMain Controlador per realitzar crides a altres funcions i
     *              comunicar-se amb el controlador de presentacio
     * @author Ona
     */
    public NuevoTecladoView(CtrlMainView cMain) {
        cm = cMain;

        int xBase = getWidth() / 10;
        int yBase = getHeight() / 2;

        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(getWidth() / 4, getHeight() / 32, getWidth() / 2, getHeight() / 13);
        add(titulo);

        vistaAnterior.setBounds(xBase / 2, yBase / 13, getWidth() / 8, getHeight() / 21);
        add(vistaAnterior);

        titulo2.setBounds(getWidth() / 8, yBase / 2, xBase * 7, getHeight() / 21);
        add(titulo2);
        nombreTeclado.setBounds(getWidth() / 8, getHeight() / 4, getWidth() - 2 * (getWidth() / 8), getHeight() / 21);
        add(nombreTeclado);

        titulo3.setBounds(getWidth() / 8, getHeight() / 5, xBase * 7, getHeight() / 21);
        add(titulo3);

        adjuntarArchivo.setBounds(getWidth() / 8, getHeight() / 4, getWidth() - 2 * (getWidth() / 8), getHeight() / 21);
        add(adjuntarArchivo);

        scrollDoc = new JScrollPane(textoTeclado);
        scrollDoc.setBounds(getWidth() / 8, getHeight() / 3 - 10, xBase * 7, getHeight() / 4);
        add(scrollDoc);

        titulo4.setBounds(getWidth() / 8, getHeight() / 3 + textoTeclado.getHeight(), xBase * 7, getHeight() / 21);
        add(titulo4);

        algoritmo1.setBounds(getWidth() / 8, yBase * 3 + titulo4.getHeight(), xBase * 5, getHeight() / 21);
        add(algoritmo1);

        algoritmo2.setBounds(getWidth() / 8, yBase * 3 + algoritmo1.getHeight() + 30, xBase * 5, getHeight() / 21);
        add(algoritmo2);

        group.add(algoritmo1);
        group.add(algoritmo2);

        crearTecladoButton.setBounds(xBase * 3, yBase * 4, getWidth() / 3, getHeight() / 13);
        add(crearTecladoButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 636);
        setLayout(null);
        setLocationRelativeTo(null);

        crearTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.crearTeclado(nombreTeclado.getText(), textoTeclado.getText(), algoritmo1.isSelected(),
                        algoritmo2.isSelected());
            }
        });

        vistaAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.muestraPaginaPrincipal();
                NuevoTecladoView.this.setVisible(false);
            }
        });

        adjuntarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(NuevoTecladoView.this);
        
                if (result == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    
                    if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                        String textoAdjunto = obtenerContenidoTxt(selectedFile);
                        textoTeclado.append(textoAdjunto);
                    }
                    else JOptionPane.showMessageDialog(NuevoTecladoView.this, "El archivo seleccionado no es de texto", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        });

        // Agrega un ComponentListener al JFrame para manejar eventos de
        // redimensionamiento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajusta el tamaño y la posición de los componentes cuando la ventana se
                // redimensiona
                adjustComponentSizesAndPositions();
            }
        });

    }

    /**
     * Funcio encarregada de llegir el contingut d'un arxiu de text i retornar el
     * contingut llegit en forma de cadena de text.
     *
     * @param file Arxiu de text adjuntat per l'usuari
     * @return Cadena de text que representa el contingut de l'arxiu llegit
     * @author Ona
     */
    private String obtenerContenidoTxt(java.io.File file) {
        StringBuilder contenido = new StringBuilder();
        try (java.util.Scanner scanner = new java.util.Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }

    /**
     * Funcio que fa el resize dels components del JFrame
     * 
     * @author Ona
     */
    private void adjustComponentSizesAndPositions() {

        int xBase = getWidth() / 10; // 41.4
        int yBase = getHeight() / 5; // 127.2

        titulo.setBounds(getWidth() / 4, getHeight() / 32, getWidth() / 2, getHeight() / 13);
        vistaAnterior.setBounds(xBase / 2, yBase / 13, getWidth() / 8, getHeight() / 21);
        titulo2.setBounds(getWidth() / 8, yBase / 2, xBase * 7, getHeight() / 21);
        nombreTeclado.setBounds(getWidth() / 8, (2 * getHeight()) / 13, getWidth() - 2 * (getWidth() / 8), getHeight() / 21);
        titulo3.setBounds(getWidth() / 8, getHeight() / 5, xBase * 7, getHeight() / 21);
        adjuntarArchivo.setBounds(getWidth() / 8, getHeight() / 4, getWidth() - 2 * (getWidth() / 8), getHeight() / 21);
        scrollDoc.setBounds(getWidth() / 8, getHeight() / 3 - 10, getWidth() - 2 * (getWidth() / 8), getHeight() / 3);
        titulo4.setBounds(getWidth() / 8, getHeight() / 3 + textoTeclado.getHeight(), xBase * 7, getHeight() / 21);
        algoritmo1.setBounds(getWidth() / 8, yBase * 3 + titulo4.getHeight(), xBase * 5, getHeight() / 19);
        algoritmo2.setBounds(getWidth() / 8, yBase * 3 + algoritmo1.getHeight() + 30, xBase * 5, getHeight() / 19);
        crearTecladoButton.setBounds(xBase * 3, yBase * 4, getWidth() / 3, getHeight() / 13);

    }

}
