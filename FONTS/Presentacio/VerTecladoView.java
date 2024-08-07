package Presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControladorsDePresentacio.*;

public class VerTecladoView extends JFrame {
    
    JLabel nombreTeclado = new JLabel();
    JButton[] botones;
    JButton swapTeclas = new JButton("Swap");
    JLabel tec1 = new JLabel("Tecla 1: ");
    JLabel tec2 = new JLabel("Tecla 2: ");
    JLabel swap1 = new JLabel("");
    JLabel swap2 = new JLabel("");
    JTextField pruebaTec = new JTextField();
    JButton clearPrueba = new JButton("Clear");
    boolean swapmodificado = false;
    JButton guardarTec = new JButton("Guardar");
    JButton modificarTec = new JButton("Editar Teclado");
    CtrlTecladoView ct;
    String pruebaTxt = "";
    String alfb;
    int filasTec, columnasTec;
    JButton vistaAnterior = new JButton("<-");

    /**
     * Constructora que ubica i defineix els elements que es necesiten al JFrame per mostrar el teclat del que rep els atributs,
     * i crea els ActionListeners necesaris per cada boto per executar les funcions de cada un.
     *
     * @param cto Controlador per fer crides a altres funcions i gestionar els altres JFrames
     * @param alfabeto String que correspon a l'alfabet del teclat que es vol mostrar
     * @param filas int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param nombreTec String que correspon al nom del teclat
     * @param alg int que defineix l'algorisme amb que es va crear el teclat
     *
     * @author Hossam
     */
    public VerTecladoView(CtrlTecladoView cto, String alfabeto, int filas, int columnas, String nombreTec, int alg) {

        ct = cto;
        filasTec = filas;
        columnasTec = columnas;
        alfb = alfabeto;

        setSize(414, 636);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        Font nFont = new Font("Arial", Font.PLAIN, 20);
        nombreTeclado.setFont(nFont);
        nombreTeclado.setText(nombreTec);
        

        //Teclado
        char[] alf = alfabeto.toCharArray();
        botones = new JButton[alf.length];
        int numButton = 0;

        int espacioWidth, ultima, derecha, izquierda, tecla = 0;

        int yBase = (getHeight()-75)/2;
        int heightTecla,widthTecla = (getWidth()-(getWidth()/10))/columnas;
        if (filas*columnas < alf.length) ultima = 0;
        else ultima = alf.length - (filas-1)*columnas;

        if (ultima == 0){
            derecha = izquierda = 0;
            espacioWidth = widthTecla*columnas;
            heightTecla = (yBase-50)/(filas+1);
        }
        else {
            derecha = izquierda = ultima/2;
            espacioWidth = (columnas - (ultima-1)) * widthTecla;
            heightTecla = (yBase-50)/filas;
        }

        int xBase = getWidth()/20;

        if (filas*columnas < alf.length){
            for (int i = 0; i < filas; ++i) {
                for (int j = 0; j < columnas; ++j) {
                    if (tecla < alf.length) {
                        JButton boton = new JButton(String.valueOf(alf[tecla]));
                        boton.setBounds(xBase + widthTecla * j, yBase + heightTecla * i, widthTecla, heightTecla);
                        boton.setFocusable(false);
                        add(boton);
                        ++tecla;
                        botones[numButton] = boton;
                        ++numButton;
                    }
                }
            }

            if (tecla < alf.length) {
                JButton boton = new JButton(String.valueOf(alf[tecla]));
                boton.setBounds(xBase + widthTecla*izquierda, yBase + heightTecla * filas, espacioWidth, heightTecla);
                boton.setFocusable(false);
                ++tecla;
                add(boton);
                botones[numButton] = boton;
                ++numButton;
            }

            modificarTec.setBounds(xBase, yBase + heightTecla*(filas+1) + getHeight()/40, (getWidth()*2)/4, getHeight()/14);
            guardarTec.setBounds(getWidth()/2 + xBase*4, yBase + heightTecla*(filas+1) + getHeight()/40,getWidth()/4, getHeight()/14);

        }
        else {
            // Todo menos ultima fila.
            for (int i = 0; i < filas-1; ++i) {
                for (int j = 0; j < columnas; ++j) {
                    if (tecla < alf.length) {
                        JButton boton = new JButton(String.valueOf(alf[tecla]));
                        boton.setBounds(xBase + widthTecla * j, yBase + heightTecla * i, widthTecla, heightTecla);
                        boton.setFocusable(false);
                        add(boton);
                        ++tecla;
                        botones[numButton] = boton;
                        ++numButton;
                    }
                }
            }

            for (int i = 0; i < izquierda; ++i) {
                if (tecla < alf.length) {
                    JButton boton = new JButton(String.valueOf(alf[tecla]));
                    boton.setBounds(xBase + widthTecla * i, yBase + heightTecla * (filas - 1), widthTecla, heightTecla);
                    boton.setFocusable(false);
                    add(boton);
                    ++tecla;
                    botones[numButton] = boton;
                    ++numButton;
                }
            }

            if (tecla < alf.length) {
                JButton boton = new JButton(String.valueOf(alf[tecla]));
                boton.setBounds(xBase + widthTecla*izquierda, yBase + heightTecla * (filas-1), espacioWidth, heightTecla);
                boton.setFocusable(false);
                ++tecla;
                add(boton);
                botones[numButton] = boton;
                ++numButton;
            }

            for (int i = 0; i < derecha; ++i) {
                if (tecla < alf.length) {
                    JButton boton = new JButton(String.valueOf(alf[tecla]));
                    boton.setBounds(xBase + espacioWidth + widthTecla* izquierda + widthTecla*i, yBase + heightTecla * (filas - 1), widthTecla, heightTecla);
                    boton.setFocusable(false);
                    add(boton);
                    ++tecla;
                    botones[numButton] = boton;
                    ++numButton;
                }
            }

            modificarTec.setBounds(xBase, yBase + heightTecla*filas + getHeight()/40, (getWidth()*2)/4, getHeight()/14);
            guardarTec.setBounds(getWidth()/2 + xBase*4, yBase + heightTecla*filas + getHeight()/40,getWidth()/4, getHeight()/14);
        }

        vistaAnterior.setBounds(xBase/2, xBase/2, getWidth()/10, getWidth()/12);

        swapTeclas.setBounds(xBase, yBase-(getHeight()*2)/7, (getWidth()*2)/5, getHeight()/8);
        tec1.setBounds(swapTeclas.getWidth() + xBase*2, yBase-(getHeight()*2)/7, getWidth()/6, getHeight()/16);
        tec2.setBounds(swapTeclas.getWidth() + xBase*2, yBase-(getHeight()*2)/9, getWidth()/6, getHeight()/16);
        swap1.setBounds((xBase*4)+swapTeclas.getWidth()+tec1.getWidth(), yBase-(getHeight()*2)/7, getWidth()/6, getHeight()/16);
        swap2.setBounds((xBase*4)+swapTeclas.getWidth()+tec1.getWidth(),yBase-(getHeight()*2)/9, getWidth()/6, getHeight()/16);

        pruebaTec.setBounds(xBase, yBase - getHeight()/8, (getWidth()*2)/3, getHeight()/10);
        clearPrueba.setBounds(xBase + pruebaTec.getWidth(), yBase - getHeight()/8, getWidth()-xBase*2-pruebaTec.getWidth(), getHeight()/10);

        Font tecsFont = new Font("Arial", Font.PLAIN, 18);

        tec1.setFont(tecsFont);
        swap1.setFont(tecsFont);
        swap2.setFont(tecsFont);
        tec2.setFont(tecsFont);

        pruebaTec.setEditable(false);
        
        vistaAnterior.setFocusable(false);
        swapTeclas.setFocusable(false);
        guardarTec.setFocusable(false);
        clearPrueba.setFocusable(false);
        modificarTec.setFocusable(false);
        vistaAnterior.setFocusable(false);

        add(nombreTeclado);
        add(vistaAnterior);
        add(modificarTec);
        add(guardarTec);
        add(clearPrueba);
        add(pruebaTec);
        add(tec2);
        add(swap1);
        add(tec1);
        add(swap2);
        add(swapTeclas);

        for(int i = 0; i < botones.length; ++i){
            if (botones[i] != null) botones[i].addActionListener(new TecladoActionListener());
        }

        vistaAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ct.vistaAnterior("principal", "");
            }
        });

        swapTeclas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!swap1.getText().equals("") && !swap2.getText().equals("")){
                for (int i = 0; i < botones.length; ++i){
                    if (botones[i].getText().equals(swap1.getText())) botones[i].setText(swap2.getText());
                    else if (botones[i].getText().equals(swap2.getText())) botones[i].setText(swap1.getText());
                }
                swap1.setText("");
                swap2.setText("");
                }
            }
        });

        clearPrueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pruebaTxt = " ";
                pruebaTec.setText(" ");
            }
        });

        modificarTec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ct.muestraVistaEditarTeclado(nombreTeclado.getText(), alfb, filasTec, columnasTec, alg);
            }
        });

        guardarTec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String alfabeto = "";
            for (int i = 0; i < botones.length; ++i)alfabeto = alfabeto+botones[i].getText();
            
            ct.modificaDistribucionTec(nombreTeclado.getText(), filasTec, columnasTec, alfabeto);

            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ajusta el tamaño y la posición de los componentes cuando la ventana se redimensiona
                adjustComponentSizesAndPositions(filas, columnas, botones, alf.length);
            }
        });
    }

    private class TecladoActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton teclaPulsada = (JButton) e.getSource();
            String teclaTxt = teclaPulsada.getText();
            
            pruebaTxt = pruebaTxt + teclaTxt;
            pruebaTec.setText(pruebaTxt);

            if (swap1.getText().equals("")) swap1.setText(teclaTxt);
            else if (swap2.getText().equals("")) swap2.setText(teclaTxt);
            else if (!swapmodificado){
                swapmodificado = true;
                swap1.setText(teclaTxt);
            }
            else {
                swapmodificado = false;
                swap2.setText(teclaTxt);
            }
        }
    }

    /**
     * Funcio que fa el resize dels components del JFrame
     *
     * @param filas int que correspon a les files del teclat
     * @param columnas int que correspon a les columnes del teclat
     * @param botones Array de botons que formen el teclat
     * @param tamAlf int tamany del alfabet del teclat
     * 
     * @author Hossam
     */
    private void adjustComponentSizesAndPositions(int filas, int columnas, JButton[] botones, int tamAlf) {
 
        int espacioWidth;
        int ultima;

        if (filas*columnas < tamAlf) ultima = 0;
        else ultima = tamAlf - (filas-1)*columnas;

        int derecha, izquierda;
        int yBase = (getHeight()-75)/2;
        int heightTecla,widthTecla = (getWidth()-(getWidth()/10))/columnas;

        if (ultima == 0){
            derecha = izquierda = 0;
            espacioWidth = widthTecla*columnas;
            heightTecla = (yBase-50)/(filas+1);
        }
        else {
            derecha = izquierda = ultima/2;
            espacioWidth = (columnas - (ultima-1)) * widthTecla;
            heightTecla = (yBase-50)/filas;
        }

        int xBase = getWidth()/20;

        int numButton = 0;

        if (filas*columnas < tamAlf){
            for (int i = 0; i < filas; ++i) {
                for (int j = 0; j < columnas; ++j) {
                        botones[numButton].setBounds(xBase + widthTecla * j, yBase + heightTecla * i, widthTecla, heightTecla);
                        ++numButton;
                    }
            }

            if (numButton < tamAlf) {
                botones[numButton].setBounds(xBase + widthTecla*izquierda, yBase + heightTecla * filas, espacioWidth, heightTecla);
                ++numButton;
            }

            for (int i = 0; i < derecha; ++i) {
                    botones[numButton].setBounds(xBase + espacioWidth + widthTecla * izquierda, yBase + heightTecla * (filas - 1), widthTecla, heightTecla);
                    ++numButton;
                }

            modificarTec.setBounds(xBase, yBase + heightTecla*(filas+1) + getHeight()/40, (getWidth()*2)/4, getHeight()/14);
            guardarTec.setBounds(getWidth()/2 + xBase*4, yBase + heightTecla*(filas+1) + getHeight()/40,getWidth()/4, getHeight()/14);


        }
        else {
            // Todo menos ultima fila.
            for (int i = 0; i < filas-1; ++i) {
                for (int j = 0; j < columnas; ++j) {
                        botones[numButton].setBounds(xBase + widthTecla * j, yBase + heightTecla * i, widthTecla, heightTecla);
                        ++numButton;
                    }
                }

            for (int i = 0; i < izquierda; ++i) {
                    botones[numButton].setBounds(xBase + widthTecla * i, yBase + heightTecla * (filas - 1), widthTecla, heightTecla);
                    ++numButton;
                }

            if (numButton < tamAlf) {
                botones[numButton].setBounds(xBase + widthTecla*izquierda, yBase + heightTecla * (filas-1), espacioWidth, heightTecla);
                ++numButton;
            }

            for (int i = 0; i < derecha; ++i) {
                botones[numButton].setBounds(xBase + espacioWidth + widthTecla* izquierda + widthTecla*i, yBase + heightTecla * (filas - 1), widthTecla, heightTecla);
                ++numButton;
            }

            if (ultima == 0) {
                    botones[numButton].setBounds(xBase, yBase + heightTecla * filas, espacioWidth, heightTecla);
            }
            modificarTec.setBounds(xBase, yBase + heightTecla*filas + getHeight()/40, (getWidth()*2)/4, getHeight()/14);
            guardarTec.setBounds(getWidth()/2 + xBase*4, yBase + heightTecla*filas + getHeight()/40,getWidth()/4, getHeight()/14);
        }

        nombreTeclado.setBounds(xBase, getHeight()/20, getWidth()/2, getHeight()/10);
        vistaAnterior.setBounds(xBase/2, xBase/2, getWidth()/10, getWidth()/12);

        swapTeclas.setBounds(xBase, yBase-(getHeight()*2)/7, (getWidth()*2)/5, getHeight()/8);
        tec1.setBounds(swapTeclas.getWidth() + xBase*2, yBase-(getHeight()*2)/7, getWidth()/6, getHeight()/16);
        tec2.setBounds(swapTeclas.getWidth() + xBase*2, yBase-(getHeight()*2)/9, getWidth()/6, getHeight()/16);
        swap1.setBounds((xBase*4)+swapTeclas.getWidth()+tec1.getWidth(), yBase-(getHeight()*2)/7, getWidth()/6, getHeight()/16);
        swap2.setBounds((xBase*4)+swapTeclas.getWidth()+tec1.getWidth(),yBase-(getHeight()*2)/9, getWidth()/6, getHeight()/16);
        
        pruebaTec.setBounds(xBase, yBase - getHeight()/8, (getWidth()*2)/3, getHeight()/10);
        clearPrueba.setBounds(xBase + pruebaTec.getWidth(), yBase - getHeight()/8, getWidth()-xBase*2-pruebaTec.getWidth(), getHeight()/10);

    }
}
