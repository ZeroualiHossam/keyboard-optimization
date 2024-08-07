package Presentacio;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import ControladorsDePresentacio.*;

public class PaginaPrincipalView extends JFrame {

    CtrlMainView cm;
    JButton addButton = new JButton("Nuevo Teclado");
    JLabel texto = new JLabel();
    JPanel listaTeclados = new JPanel();
    JLabel tituloPanel = new JLabel("Tus Teclados");
    JButton perfilButton = new JButton("Perfil");
    JButton cancelar = new JButton("Cancelar");
    JLabel borrarTec = new JLabel("Borrar teclado:");
    JButton borrarBtn = new JButton("Borrar");
    String selectedTec = null;
    JList<String> lista;
    JComboBox<String> desplegableTec;
    String[] tecs;

    /**
     * Constructora que ubica i defineix els elements que es necesiten al JFrame per
     * visualitzar la pagina principal del programa
     *
     * @param cMain Controlador per realitzar crides a altres funcions i
     *              comunicar-se amb el controlador de presentacio
     * @param tecls Vector de string que representen els teclats que te l'usuari
     *              actual
     * @author Ona
     */
    public PaginaPrincipalView(CtrlMainView cMain, String[] tecls) {

        cm = cMain;
        tecs = tecls;

        int xBase = getWidth() / 10;
        int yBase = getHeight() / 5;

        // títol
        texto.setBounds(xBase * 3 + 30, yBase / 6, getWidth(), getHeight() / 10);
        texto.setText("PAGINA PRINCIPAL");
        add(texto);

        // boton nuevoTeclado
        addButton.setBounds(xBase, yBase * 4, xBase * 5, yBase / 2);
        // addButton.addActionListener(this);
        addButton.setFocusable(false); // Que no se pueda marcar.
        add(addButton);

        // boton perfil
        perfilButton.setBounds(xBase + addButton.getWidth(), yBase * 4, xBase * 3, yBase / 2);
        perfilButton.setFocusable(false); // Que no se pueda marcar.
        // perfilButton.addActionListener(this);
        add(perfilButton);

        // Llista teclados
        listaTeclados.setName("Tus Teclados:");
        listaTeclados.setLayout(new BorderLayout());

        tituloPanel.setHorizontalAlignment(SwingConstants.LEFT);
        tituloPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        String[] elementos = tecs;
        lista = new JList<>(elementos);
        lista.setCellRenderer(new BotonRenderer());
        lista.setFixedCellHeight(50);

        listaTeclados.add(tituloPanel, BorderLayout.NORTH);
        listaTeclados.add(new JScrollPane(lista), BorderLayout.CENTER);

        listaTeclados.setBounds(xBase, getWidth() / 5 - 10, xBase * 8, yBase * 3);

        if (!tecs[0].equals("No hay teclados creados.")) {
            lista.setFocusable(false);
            lista.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        String tecladoSeleccionado = lista.getSelectedValue();
                        if (!tecladoSeleccionado.equals(null) && !tecladoSeleccionado.equals("No hay teclados creados."))
                            cm.muestraTeclado(tecladoSeleccionado);
                    }
                }
            });
        }

        add(listaTeclados);

        // borrar teclados:
        borrarTec.setBounds(xBase, getHeight() / 8 - 10 + listaTeclados.getHeight(), getWidth() / 4, getHeight() / 13);
        add(borrarTec);

        borrarBtn.setBounds(250, 440, 100, 25);
        add(borrarBtn);

        if (tecs[0].equals("No hay teclados creados.")) {
            desplegableTec = new JComboBox<>();

        } 
        else {
            desplegableTec = new JComboBox<>(elementos);
            desplegableTec.setSelectedIndex(-1);
            desplegableTec.setBounds(140, 440, 100, 25);
        
            desplegableTec.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectedOption = (String) desplegableTec.getSelectedItem();
                    selectedTec = selectedOption;
                }
            });

            add(desplegableTec);
        }
        

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        PaginaPrincipalView.this,
                        "Para introducir una frecuencia de palabras el formato debe ser: " +
                                "palabra frequencia, palabra frequencia...",
                        "Formato para frecuencia de palabras:",
                        JOptionPane.INFORMATION_MESSAGE);
                cm.vistaCrearTeclado();
            }
        });

        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.vistaPerfilUsuario();
            }
        });

        borrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedTec != null) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                            PaginaPrincipalView.this,
                            "¿Seguro que quieres eliminar este teclado?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        cm.borrarTeclado(selectedTec);
                        actualizaTecs();
                        JOptionPane.showMessageDialog(
                                PaginaPrincipalView.this,
                                "Teclado eliminado con éxito",
                                "Teclado eliminado",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            PaginaPrincipalView.this, // Cambiado 'this' a 'PaginaPrincipalView.this'
                            "No hay teclados creados",
                            "No se puede borrar el teclado",
                            JOptionPane.ERROR_MESSAGE);
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 636);
        setLayout(null);
        setLocationRelativeTo(null);
    }

    /**
     * Funcio utilitzada per personalitzar com es mostren els elements del JList com
     * a botons
     * 
     * @author Ona
     * 
     */
    static class BotonRenderer extends JButton implements
            ListCellRenderer<Object> {
        public BotonRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected,
                boolean cellHasFocus) {
            if (value != null) {
                setText(value.toString());
            }

            if (isSelected) {
                setBackground(list.getSelectionBackground());

            } else {
                setBackground(list.getBackground());

            }
            return this;
        }
    }

    /**
     * Funcio que actualitza la llista de teclats que te l'usuari actual
     * 
     * @author Ona
     * 
     */
    public void actualizaTecs() {

        desplegableTec.removeAllItems();

        tecs = cm.obtenerTeclados();

        for (int i = 0; i < tecs.length; ++i) {
            if (!tecs[0].equals("No hay teclados creados."))
                desplegableTec.addItem(tecs[i]);
        }
        desplegableTec.repaint();
        lista.setListData(tecs);
    }

    /**
     * Funcio que fa el resize dels components del JFrame
     * 
     * @author Ona
     */
    private void adjustComponentSizesAndPositions() {

        int xBase = getWidth() / 10;
        int yBase = getHeight() / 5;

        texto.setBounds(xBase * 3 + 30, yBase / 6, getWidth(), getHeight() / 10);
        addButton.setBounds(xBase, yBase * 4, xBase * 5, yBase / 2);
        perfilButton.setBounds(xBase + addButton.getWidth() + 30, yBase * 4, xBase * 2, yBase / 2);
        listaTeclados.setBounds(xBase, getHeight() / 8 - 10, xBase * 8, yBase * 3);
        borrarTec.setBounds(xBase, getHeight() / 8 - 10 + listaTeclados.getHeight(), getWidth() / 4, getHeight() / 13);
        desplegableTec.setBounds(xBase + borrarTec.getWidth(), getHeight() / 8 + listaTeclados.getHeight(),
                getWidth() / 4, getHeight() / 25);
        borrarBtn.setBounds(xBase + borrarTec.getWidth() + desplegableTec.getWidth() + 14,
                getHeight() / 8 + listaTeclados.getHeight(), getWidth() / 4, getHeight() / 25);

    }
}