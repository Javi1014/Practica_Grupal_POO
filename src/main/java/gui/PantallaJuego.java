/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.poo.Casilla;
import com.mycompany.poo.Conejo;
import com.mycompany.poo.Coordenada;
import com.mycompany.poo.Humano;
import com.mycompany.poo.Tablero;
import com.mycompany.poo.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author nieto
 */
public class PantallaJuego extends JFrame {

    private JButton[][] botones;
    private int size;
    private JTextArea eventTextArea;
    private JPanel panelControles;
    private JMenuBar menuBar;
    private ArrayList<Humano> listaHumanos = new ArrayList<>();
    private ArrayList<Zombie> listaZombies = new ArrayList<>();

    public PantallaJuego(int numeroJugadores) {
        // Determinar el tamaño del tablero basado en el atributo
        switch (numeroJugadores) {
            case 1:
                size = 7;
                break;
            case 2:
                size = 8;
                break;
            case 3:
                size = 9;
                break;
            case 4:
                size = 10;
                break;
            default:
                throw new IllegalArgumentException("Atributo debe estar entre 1 y 4");
        }
        //************************** JFRAME******************************************************************************************
        // Configurar el JFrame
        setTitle("FESTINFORMATICO ZOMBI");
        setSize(1500, 844);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/images.png")).getImage());
        setLayout(new BorderLayout()); // Establecer el layout del JFrame
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        //************************** JFRAME******************************************************************************************

        //************************** BARRA MENU SUPERIOR******************************************************************************************
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(1500, 50));
        setJMenuBar(menuBar);

        JMenu menuArchivo = new javax.swing.JMenu();
        JMenuItem itemGuardar = new javax.swing.JMenuItem();
        JMenuItem itemSalir = new javax.swing.JMenuItem();

        menuArchivo.setText("Archivo");

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloGuardar.png"))); // NOI18N
        itemGuardar.setText("Guardar");
        itemGuardar.setPreferredSize(new java.awt.Dimension(141, 32));
        /*itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });*/
        menuArchivo.add(itemGuardar);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloSalir.png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.setPreferredSize(new java.awt.Dimension(121, 32));
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        menuBar.add(menuArchivo);

        //************************** BARRA MENU SUPERIOR******************************************************************************************
        //**************************  MENU VISUALIZAR ******************************************************************************************
        JMenu menuVisualizar = new javax.swing.JMenu();
        JMenuItem itemHumano = new javax.swing.JMenuItem();
        JMenuItem itemZombi = new javax.swing.JMenuItem();
        menuVisualizar.setText("Visualizar");

        itemHumano.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemHumano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloHumano.png"))); // NOI18N
        itemHumano.setText("Humano");
        itemHumano.setPreferredSize(new java.awt.Dimension(141, 32));
        itemHumano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHumanoActionPerformed(evt);
            }
        });
        menuVisualizar.add(itemHumano);

        itemZombi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemZombi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SimboloZombi.png"))); // NOI18N
        itemZombi.setText("Zombi");
        itemZombi.setPreferredSize(new java.awt.Dimension(141, 32));

        itemZombi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemZombiActionPerformed(evt);
            }
        });

        menuVisualizar.add(itemZombi);

        menuBar.add(menuVisualizar);
        //**************************  MENU VISUALIZAR ******************************************************************************************

        //************************** PANEL TABLERO******************************************************************************************
        JPanel panelTablero = new JPanel(new GridLayout(size, size));
        // Calcular el tamaño preferido del panel basado en el tamaño del tablero
        int panelTamano = getHeight() - menuBar.getHeight(); // Deseamos que el panel ocupe toda la altura del JFrame (1280x720)
        panelTablero.setPreferredSize(new Dimension(panelTamano, panelTamano));

        // Añadir los botones al panel
        botones = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setToolTipText("{" + "x=" + i + ", y=" + j + '}');
                panelTablero.add(botones[i][j]);
            }
        }

        // Añadir el panel al JFrame en la posición deseada
        add(panelTablero, BorderLayout.WEST);
        //************************** PANEL TABLERO******************************************************************************************

        //************************** PANEL DERECHO ( CONTROLES Y TEXTO ) ******************************************************************************************
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
        //************************** PANEL CONTROLES ******************************************************************************************
        panelControles = new JPanel(new GridLayout(1, 1));
        //AQUI DEBEMOS HACER DESDE FUERA EL SET PARA ESTABLECER LA PANTALLA QUE QUEREMOS
        //panelControles.add(new PanelMoverse());

        panelDerecho.add(panelControles);
        //************************** PANEL CONTROLES ******************************************************************************************

        //************************** PANEL TEXTO ******************************************************************************************
        JPanel panelTexto = new JPanel(new BorderLayout());
        eventTextArea = new JTextArea();
        eventTextArea.setEditable(false);
        eventTextArea.setLineWrap(true);
        eventTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(eventTextArea);
        panelTexto.add(scrollPane, BorderLayout.CENTER);
        panelTexto.setBorder(BorderFactory.createTitledBorder("Registro de Eventos"));
        panelDerecho.add(panelTexto);
        //************************** PANEL TEXTO ******************************************************************************************

        add(panelDerecho, BorderLayout.CENTER);
        //************************** PANEL DERECHO ( CONTROLES Y TEXTO ) ******************************************************************************************
        setVisible(true);
    }

    public void actualizarTablero(Juego juego) {
        listaHumanos = new ArrayList(juego.getListaHumanos());
        listaZombies = new ArrayList(juego.getListaJugadores());
        Font fontSmall = new Font("Arial", Font.PLAIN, 10); // Aquí ajusta el tamaño de 12 según sea necesario
        Tablero tablero = juego.getTablero();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Casilla casilla = tablero.getCasilla(new Coordenada(i, j));
                //String texto = "";
                StringBuilder texto = new StringBuilder("<html>");

                // Añadir información sobre zombies
                if (!casilla.getNumZombie().isEmpty()) {
                    for (Zombie zombie : casilla.getNumZombie()) {
                        //texto += "Z:" + zombie.getNombre() + " ";
                        texto.append("Z:").append(zombie.getNombre()).append("<br>");
                    }
                }
                // Añadir información sobre humanos
                if (!casilla.getNumHumano().isEmpty()) {
                    for (Humano humano : casilla.getNumHumano()) {
                        //texto += "H:" + humano.getClass().getSimpleName() + " ";
                        texto.append("H:").append(humano.getClass().getSimpleName()).append("<br>");
                    }
                }
                // Añadir información sobre conejos
                if (!casilla.getNumConejos().isEmpty()) {
                    for (Conejo conejo : casilla.getNumConejos()) {
                        //texto += "C:" + conejo.getNombre() + " ";
                        texto.append("C:").append(conejo.getNombre()).append("<br>");
                    }
                }
                texto.append("</html>");//NO ESTABA
                // Actualizar el texto del botón
                botones[i][j].setText(texto.toString());//sin toString
                botones[i][j].setFont(fontSmall);

            }
        }
        botones[tablero.getFilas() - 1][tablero.getColumnas() - 1].setText("<html>OBJETIVO</html>");
    }

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {
        //AdvertenciaSalir advert = new AdvertenciaSalir(this, true);
        //advert.setLocationRelativeTo(this);
        //advert.setVisible(true);
        AdvertenciaSalir dialog = new AdvertenciaSalir(null, true); // Crear instancia del JDialog

        dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true); // Mostrar el JDialog de manera modal

        // Después de cerrar el JDialog, verificar la opción seleccionada
        if (dialog.getOpcionSeleccionada() == 1) {
            System.exit(0); // Salir del programa
        }
    }

    private void itemHumanoActionPerformed(java.awt.event.ActionEvent evt) {
        //AdvertenciaSalir advert = new AdvertenciaSalir(this, true);
        //advert.setLocationRelativeTo(this);
        //advert.setVisible(true);
        DialogVisualizarHumano dialog = new DialogVisualizarHumano(null, listaHumanos); // Crear instancia del JDialog

    }

    private void itemZombiActionPerformed(java.awt.event.ActionEvent evt) {
        //AdvertenciaSalir advert = new AdvertenciaSalir(this, true);
        //advert.setLocationRelativeTo(this);
        //advert.setVisible(true);
        DialogVisualizarZombi dialogZ = new DialogVisualizarZombi(null, listaZombies); // Crear instancia del JDialog

    }

    public void agregarEvento(String evento) {
        eventTextArea.append(evento + "\n");
        eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
    }

    public void setPanelControles(javax.swing.JPanel panel) {
        panelControles.removeAll();
        panelControles.add(panel);
        panelControles.revalidate();
        panelControles.repaint();
    }

    public void anadirFinalPartida() {
        JMenu menuRegistros = new javax.swing.JMenu();
        JMenuItem itemHeridas = new javax.swing.JMenuItem();
        JMenuItem itemEliminaciones = new javax.swing.JMenuItem();
        menuRegistros.setText("Registros");

        itemHeridas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemHeridas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Heridas.png"))); // NOI18N
        itemHeridas.setText("Heridas");
        itemHeridas.setPreferredSize(new java.awt.Dimension(141, 32));

        itemHeridas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHeridasActionPerformed(evt);
            }
        });

        menuRegistros.add(itemHeridas);

        itemEliminaciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemEliminaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Eliminaciones.png"))); // NOI18N
        itemEliminaciones.setText("Bajas");
        itemEliminaciones.setPreferredSize(new java.awt.Dimension(141, 32));

        itemEliminaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminacionesActionPerformed(evt);
            }
        });

        menuRegistros.add(itemEliminaciones);

        menuBar.add(menuRegistros);

    }

    private void itemHeridasActionPerformed(java.awt.event.ActionEvent evt) {

        DialogVisualizarHeridas dialogH = new DialogVisualizarHeridas(null, listaZombies); // Crear instancia del JDialog

    }
    private void itemEliminacionesActionPerformed(java.awt.event.ActionEvent evt) {

        DialogVisualizarBajas dialogB = new DialogVisualizarBajas(null, listaZombies); // Crear instancia del JDialog

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego(2).setVisible(true);
            }
        });
    }
}
