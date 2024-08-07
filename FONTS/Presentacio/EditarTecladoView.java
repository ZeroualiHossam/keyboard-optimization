package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControladorsDePresentacio.*;

public class EditarTecladoView extends JFrame {

    JLabel nomLabel = new JLabel("Editar Nombre de Teclado");
    JTextField nomText = new JTextField();
    JLabel docLabel = new JLabel("Editar Documento de Teclado");
    JTextArea docText = new JTextArea();
    JButton modificaNombre = new JButton("Guardar Nombre");
    JButton modificaDocumento = new JButton("Guardar Documento");
    JScrollPane scrollDoc = new JScrollPane();
    CtrlTecladoView ct;
    String nomTeclat;
    JButton vistaAnterior = new JButton("<-");
    String alf;
    int filas, columnas;

    /**
     * Constructora que ubica i defineix els elements que es necesiten al JFrame per editar
     * el document del teclat nomTec o el propi nomTec
     *
     * @param cto Controlador per fer crides a altres funcions i gestionar els altres JFrames
     * @param nomTec String que correspon al nom del teclat
     * @param doc String que correspon al document del teclat nomTec
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol m ostrar
     * @param filasTec int que correspon a les files del teclat
     * @param columnasTec int que correspon a les columnes del teclat
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     *
     * @author Hossam
     */
    public EditarTecladoView(CtrlTecladoView cto, String nomTec, String doc, String alfabeto, int filasTec, int columnasTec, int alg)  {
        
        ct = cto;
        nomTeclat = nomTec;
        alf = alfabeto;
        filas = filasTec;
        columnas = columnasTec;

        setSize(414, 636);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        int xBase = getWidth() / 10;
        int yBase = getHeight() / 5;

        Font nFont = new Font("Arial", Font.PLAIN, 16);

        nomLabel.setFont(nFont);
        docLabel.setFont(nFont);
        nomText.setText(nomTec);
        docText.setText(doc);
        nomText.setFont(nFont);

        nomLabel.setBounds(xBase, yBase / 3, (getWidth() * 2) / 3, getHeight() / 15);
        nomText.setBounds(xBase, yBase / 3 + nomLabel.getHeight(), getWidth() - xBase * 2, getHeight() / 20);
        modificaNombre.setBounds(getWidth() / 2, yBase / 4 + nomLabel.getHeight() + 2 * nomText.getHeight(), getWidth() / 2 - xBase, getHeight() / 20);
        docLabel.setBounds(xBase, yBase * 2 - nomLabel.getHeight(), (getWidth() * 2) / 3, getHeight() / 15);


        scrollDoc = new JScrollPane(docText);
        scrollDoc.setBounds(xBase, yBase * 2, getWidth() - xBase * 2, getHeight() / 3 + nomLabel.getHeight());
        modificaDocumento.setBounds(getWidth() / 2, yBase + scrollDoc.getHeight() + 5 * nomText.getHeight(), getWidth() / 2 - xBase, getHeight() / 20);
        
        vistaAnterior.setBounds(xBase/4, xBase/4, getWidth()/10, getWidth()/12);
        vistaAnterior.setFocusable(false);

        add(vistaAnterior);
        add(scrollDoc);
        add(modificaDocumento);
        add(docLabel);
        add(modificaNombre);
        add(nomText);
        add(nomLabel);

        vistaAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ct.vistaAnterior("editar", nomTeclat);
            }
        });

        modificaDocumento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ct.modificaDocTec(nomTeclat, docText.getText(), alg);
            }
        });

        modificaNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ct.modificaNomTec(nomTeclat, nomText.getText());
                nomTeclat = nomText.getText();
            }
        });

        // Agrega un ComponentListener al JFrame para manejar eventos de redimensionamiento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajusta el tamaño y la posición de los componentes cuando la ventana se redimensiona
                adjustComponentSizesAndPositions();
            }
        });

    }
    
    /**
     * Funcio que fa el resize dels components del JFrame
     *
     * @author Hossam
     */
    private void adjustComponentSizesAndPositions() {
        
        int xBase = getWidth() / 10;
        int yBase = getHeight() / 5;

        nomLabel.setBounds(xBase, yBase / 3, (getWidth() * 2) / 3, getHeight() / 15);
        nomText.setBounds(xBase, yBase / 3 + nomLabel.getHeight(), getWidth() - xBase * 2, getHeight() / 20);
        modificaNombre.setBounds(getWidth() / 2, yBase / 4 + nomLabel.getHeight() + 2 * nomText.getHeight(), getWidth() / 2 - xBase, getHeight() / 20);
        docLabel.setBounds(xBase, yBase * 2 - nomLabel.getHeight(), (getWidth() * 2) / 3, getHeight() / 15);
        scrollDoc.setBounds(xBase, yBase * 2, getWidth() - xBase * 2, getHeight() / 3 + nomLabel.getHeight());
        modificaDocumento.setBounds(getWidth() / 2, yBase + scrollDoc.getHeight() + 5 * nomText.getHeight(), getWidth() / 2 - xBase, getHeight() / 20);
    }

}

