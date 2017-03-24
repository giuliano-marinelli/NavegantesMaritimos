package navegantesMaritimos.interfaz;

import especificos.diccionario.Diccionario;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import lineales.lista.Lista;
import navegantesMaritimos.NavegantesMaritimos;
import navegantesMaritimos.interfaz.otros.*;

/**
 * Este es el menu inicial, desde este se llaman a todas las opciones de
 * alta/baja y los metodos especiales, tambien grafica el grafo de rutas
 * maritimas y lista los barcos existentes.
 */
public class MenuPrincipal extends JFrame {

    private final NavegantesMaritimos nav = new NavegantesMaritimos();

    public MenuPrincipal() {
        initComponents();
        actualizarPanelPuertos();
        this.setLocationRelativeTo(null);
    }

    public void actualizarPanelPuertos() {
        panelPuertos.setPreferredSize(new Dimension(256, 64));
        panelPuertos.removeAll();
        Lista<String> puertos = nav.listarPuertos();
        if (puertos != null) {
            int fila = 0;
            int col = 0;
            for (int i = 1; i <= puertos.longitud(); i++) {
                String[] puerto = nav.arregloDatosPuerto(puertos.recuperar(i));
                agregarPuertoAPanel(puerto, col, fila);
                if (i % 4 == 0) {
                    fila++;
                    col -= 4;
                    double preferedWidth = panelPuertos.getPreferredSize().getWidth();
                    double preferedHeight = panelPuertos.getPreferredSize().getHeight();
                    panelPuertos.setPreferredSize(new Dimension((int) preferedWidth, (int) preferedHeight + 64));
                }
                col++;
            }
        }
        panelPuertos.repaint();
    }

    public void agregarPuertoAPanel(final String[] puerto, int col, int fila) {
        final JLabel labelPuerto = new JLabel();
        labelPuerto.setBounds(64 * col, 64 * fila, 64, 64);
        labelPuerto.setText(puerto[0]);
        labelPuerto.setToolTipText(
                "<html>"
                + "<p>" + puerto[0] + "</p>"
                + "<p>" + puerto[1] + "</p>"
                + "<p>" + puerto[2] + " darsenas</p>"
                + "</html>");
        labelPuerto.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/puerto.png")));
        JLabel labelPais = new JLabel();
        labelPais.setBounds((64 * col) + 34, (64 * fila) + 44, 30, 20);
        labelPais.setIcon(new ImageIcon(getClass().getResource("../recursos/banderas/" + Paises.conseguirAlias(puerto[1]) + ".png")));
        labelPuerto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                labelPuertoMouseClicked(evt, puerto[0]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = LineBorder.createGrayLineBorder();
                labelPuerto.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelPuerto.setBorder(null);
            }
        });
        final JLabel labelEliminarPuerto = new JLabel();
        labelEliminarPuerto.setBounds((64 * col) + 48, (64 * fila), 16, 16);
        labelEliminarPuerto.setToolTipText("Eliminar puerto");
        labelEliminarPuerto.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminar.png")));
        labelEliminarPuerto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                labelEliminarPuertoMouseClicked(evt, puerto[0]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelEliminarPuerto.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminarOver.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelEliminarPuerto.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminar.png")));
            }
        });
        panelPuertos.add(labelEliminarPuerto);
        panelPuertos.add(labelPais);
        panelPuertos.add(labelPuerto);
    }

    public void actualizarPanelRutasMaritimas() {
        panelRutasMaritimas.setPreferredSize(new Dimension(256, 64));
        panelRutasMaritimas.removeAll();
        Lista<String[]> rutasMaritimas = nav.listarRutasMaritimas();
        if (rutasMaritimas != null) {
            int fila = 0;
            int col = 0;
            for (int i = 1; i <= rutasMaritimas.longitud(); i++) {
                String[] rutaMaritima = rutasMaritimas.recuperar(i);
                agregarRutaMaritimaAPanel(rutaMaritima, col, fila);
                if (i % 4 == 0) {
                    fila++;
                    col -= 4;
                    double preferedWidth = panelRutasMaritimas.getPreferredSize().getWidth();
                    double preferedHeight = panelRutasMaritimas.getPreferredSize().getHeight();
                    panelRutasMaritimas.setPreferredSize(new Dimension((int) preferedWidth, (int) preferedHeight + 64));
                }
                col++;
            }
        }
        panelRutasMaritimas.repaint();
    }

    public void agregarRutaMaritimaAPanel(final String[] rutaMaritima, int col, int fila) {
        final JLabel labelRutaMaritima = new JLabel();
        labelRutaMaritima.setBounds(64 * col, 64 * fila, 64, 64);
        labelRutaMaritima.setToolTipText(
                "<html>"
                + "<p>Ruta desde " + rutaMaritima[0] + " hasta " + rutaMaritima[1] + "</p>"
                + "<p>Distancia: " + rutaMaritima[2] + " millas</p>"
                + "</html>");
        labelRutaMaritima.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/rutaMaritima.png")));
        labelRutaMaritima.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border border = LineBorder.createGrayLineBorder();
                labelRutaMaritima.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelRutaMaritima.setBorder(null);
            }
        });
        final JLabel labelEliminarRutaMaritima = new JLabel();
        labelEliminarRutaMaritima.setBounds((64 * col) + 48, (64 * fila), 16, 16);
        labelEliminarRutaMaritima.setToolTipText("Eliminar ruta maritima");
        labelEliminarRutaMaritima.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminar.png")));
        labelEliminarRutaMaritima.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                labelEliminarRutaMaritimaMouseClicked(evt, rutaMaritima[0], rutaMaritima[1]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelEliminarRutaMaritima.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminarOver.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelEliminarRutaMaritima.setIcon(new ImageIcon(getClass().getResource("../recursos/puertos/eliminar.png")));
            }
        });
        panelRutasMaritimas.add(labelEliminarRutaMaritima);
        panelRutasMaritimas.add(labelRutaMaritima);
    }

    private void labelEliminarRutaMaritimaMouseClicked(MouseEvent evt, String origen, String destino) {
        int respuesta = JOptionPane.showConfirmDialog(panelBotones, "¿Quiere dar de baja la ruta maritima que va desde el puerto " + origen + " a el puerto " + destino + "?", "Confirmacion", 1);
        if (respuesta == 0) {
            nav.bajaRutaMaritima(origen, destino);
            actualizarPanelRutasMaritimas();
        }
    }

    private void labelEliminarPuertoMouseClicked(MouseEvent evt, String nombrePuerto) {
        int respuesta = JOptionPane.showConfirmDialog(panelBotones, "¿Quiere dar de baja el puerto " + nombrePuerto + "?", "Confirmacion", 1);
        if (respuesta == 0) {
            nav.bajaPuerto(nombrePuerto);
            actualizarPanelPuertos();
        }
    }

    private void labelPuertoMouseClicked(MouseEvent evt, String nombrePuerto) {
        this.setEnabled(false);
        this.setVisible(false);
        MenuPuerto menuAux = new MenuPuerto(nav, this, this, nombrePuerto);
        menuAux.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        buttonOtrasOpciones = new javax.swing.JButton();
        buttonPrecargarDatos = new javax.swing.JButton();
        panelOpcPuertos = new javax.swing.JPanel();
        buttonAltaPuertos = new javax.swing.JButton();
        panelOpcRutasMaritimas = new javax.swing.JPanel();
        buttonAltaRutasMaritimas = new javax.swing.JButton();
        buttonDibujarAVL = new javax.swing.JButton();
        scrollPuertos = new javax.swing.JScrollPane();
        panelPuertos = new javax.swing.JPanel();
        scrollRutasMaritimas = new javax.swing.JScrollPane();
        panelRutasMaritimas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Navegantes Maritimos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        panelBotones.setBackground(new java.awt.Color(255, 255, 255));
        panelBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonOtrasOpciones.setText("Otras opciones");
        buttonOtrasOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOtrasOpcionesActionPerformed(evt);
            }
        });

        buttonPrecargarDatos.setText("Precargar datos");
        buttonPrecargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrecargarDatosActionPerformed(evt);
            }
        });

        panelOpcPuertos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Puertos"));

        buttonAltaPuertos.setText("Alta");
        buttonAltaPuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAltaPuertosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcPuertosLayout = new javax.swing.GroupLayout(panelOpcPuertos);
        panelOpcPuertos.setLayout(panelOpcPuertosLayout);
        panelOpcPuertosLayout.setHorizontalGroup(
            panelOpcPuertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcPuertosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAltaPuertos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelOpcPuertosLayout.setVerticalGroup(
            panelOpcPuertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcPuertosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAltaPuertos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOpcRutasMaritimas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Rutas maritimas"));

        buttonAltaRutasMaritimas.setText("Alta");
        buttonAltaRutasMaritimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAltaRutasMaritimasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcRutasMaritimasLayout = new javax.swing.GroupLayout(panelOpcRutasMaritimas);
        panelOpcRutasMaritimas.setLayout(panelOpcRutasMaritimasLayout);
        panelOpcRutasMaritimasLayout.setHorizontalGroup(
            panelOpcRutasMaritimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcRutasMaritimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAltaRutasMaritimas, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelOpcRutasMaritimasLayout.setVerticalGroup(
            panelOpcRutasMaritimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcRutasMaritimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAltaRutasMaritimas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonDibujarAVL.setText("Dibujar AVL");
        buttonDibujarAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDibujarAVLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOpcPuertos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonOtrasOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOpcRutasMaritimas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBotonesLayout.createSequentialGroup()
                        .addComponent(buttonPrecargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDibujarAVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOpcPuertos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOpcRutasMaritimas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOtrasOpciones)
                    .addComponent(buttonPrecargarDatos)
                    .addComponent(buttonDibujarAVL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPuertos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPuertos.setAutoscrolls(true);
        scrollPuertos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollPuertos.setHorizontalScrollBar(null);

        panelPuertos.setBackground(new java.awt.Color(102, 102, 102));
        panelPuertos.setMinimumSize(new java.awt.Dimension(100, 100));
        panelPuertos.setPreferredSize(new java.awt.Dimension(256, 64));

        javax.swing.GroupLayout panelPuertosLayout = new javax.swing.GroupLayout(panelPuertos);
        panelPuertos.setLayout(panelPuertosLayout);
        panelPuertosLayout.setHorizontalGroup(
            panelPuertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        panelPuertosLayout.setVerticalGroup(
            panelPuertosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scrollPuertos.setViewportView(panelPuertos);

        scrollRutasMaritimas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRutasMaritimas.setAutoscrolls(true);
        scrollRutasMaritimas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollRutasMaritimas.setHorizontalScrollBar(null);

        panelRutasMaritimas.setBackground(new java.awt.Color(102, 102, 102));
        panelRutasMaritimas.setPreferredSize(new java.awt.Dimension(256, 64));

        javax.swing.GroupLayout panelRutasMaritimasLayout = new javax.swing.GroupLayout(panelRutasMaritimas);
        panelRutasMaritimas.setLayout(panelRutasMaritimasLayout);
        panelRutasMaritimasLayout.setHorizontalGroup(
            panelRutasMaritimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        panelRutasMaritimasLayout.setVerticalGroup(
            panelRutasMaritimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );

        scrollRutasMaritimas.setViewportView(panelRutasMaritimas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollRutasMaritimas, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPuertos, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(scrollRutasMaritimas))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrecargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrecargarDatosActionPerformed
        buttonPrecargarDatos.setEnabled(false);
        nav.altaPuerto("P0", "Argentina", 10);
        nav.altaPuerto("P1", "Chile", 10);
        nav.altaPuerto("P2", "Perú", 10);
        nav.altaPuerto("P3", "Ecuador", 10);
        nav.altaPuerto("P4", "Venezuela", 10);
        nav.altaPuerto("P5", "Bolivia", 10);
        nav.altaPuerto("P6", "Brasil", 10);
        nav.altaPuerto("P7", "México", 10);
        nav.altaPuerto("P8", "Dinamarca", 10);
        nav.altaPuerto("P9", "Corea", 10);
        nav.altaPuerto("P10", "Colombia", 10);
        nav.altaPuerto("P11", "China", 10);
        nav.altaPuerto("P12", "Canadá", 10);
        nav.altaPuerto("P13", "Belice", 10);
        nav.altaPuerto("P14", "Ghana", 10);
        nav.altaRutaMaritima("P0", "P1", 100);
        nav.altaRutaMaritima("P0", "P4", 58);
        nav.altaRutaMaritima("P0", "P11", 46);
        nav.altaRutaMaritima("P1", "P5", 215);
        nav.altaRutaMaritima("P2", "P3", 408);
        nav.altaRutaMaritima("P2", "P6", 315);
        nav.altaRutaMaritima("P3", "P2", 408);
        nav.altaRutaMaritima("P3", "P6", 12);
        nav.altaRutaMaritima("P3", "P7", 504);
        nav.altaRutaMaritima("P4", "P8", 99);
        nav.altaRutaMaritima("P4", "P0", 58);
        nav.altaRutaMaritima("P5", "P1", 215);
        nav.altaRutaMaritima("P5", "P8", 33);
        nav.altaRutaMaritima("P5", "P6", 93);
        nav.altaRutaMaritima("P6", "P5", 93);
        nav.altaRutaMaritima("P6", "P2", 315);
        nav.altaRutaMaritima("P6", "P3", 12);
        nav.altaRutaMaritima("P6", "P7", 27);
        nav.altaRutaMaritima("P6", "P10", 15);
        nav.altaRutaMaritima("P6", "P9", 200);
        nav.altaRutaMaritima("P6", "P13", 463);
        nav.altaRutaMaritima("P7", "P3", 504);
        nav.altaRutaMaritima("P7", "P6", 27);
        nav.altaRutaMaritima("P7", "P14", 80);
        nav.altaRutaMaritima("P7", "P10", 8);
        nav.altaRutaMaritima("P8", "P4", 99);
        nav.altaRutaMaritima("P8", "P5", 33);
        nav.altaRutaMaritima("P8", "P11", 103);
        nav.altaRutaMaritima("P8", "P12", 127);
        nav.altaRutaMaritima("P8", "P9", 122);
        nav.altaRutaMaritima("P9", "P8", 122);
        nav.altaRutaMaritima("P9", "P6", 200);
        nav.altaRutaMaritima("P9", "P12", 500);
        nav.altaRutaMaritima("P9", "P13", 400);
        nav.altaRutaMaritima("P10", "P13", 8);
        nav.altaRutaMaritima("P10", "P6", 15);
        nav.altaRutaMaritima("P10", "P14", 8);
        nav.altaRutaMaritima("P10", "P7", 8);
        nav.altaRutaMaritima("P11", "P0", 46);
        nav.altaRutaMaritima("P11", "P8", 103);
        nav.altaRutaMaritima("P11", "P12", 1012);
        nav.altaRutaMaritima("P12", "P8", 127);
        nav.altaRutaMaritima("P12", "P1", 1012);
        nav.altaRutaMaritima("P12", "P9", 500);
        nav.altaRutaMaritima("P13", "P9", 400);
        nav.altaRutaMaritima("P13", "P6", 463);
        nav.altaRutaMaritima("P13", "P10", 8);
        nav.altaRutaMaritima("P13", "P14", 10);
        nav.altaRutaMaritima("P14", "P13", 10);
        nav.altaRutaMaritima("P14", "P10", 8);
        nav.altaRutaMaritima("P14", "P7", 80);
        nav.altaBarco("B1", "Pasajeros", "Argentina");
        nav.altaBarco("B2", "Pesquero", "Chile");
        nav.altaBarco("B3", "Carga", "Venezuela");
        nav.altaBarco("B4", "Pesquero", "Brasil");
        nav.altaBarco("B5", "Pasajeros", "Ecuador");
        nav.ponerBarco("B1", "P0");
        nav.ponerBarco("B2", "P0");
        nav.ponerBarco("B3", "P0");
        nav.ponerBarco("B4", "P0");
        nav.ponerBarco("B5", "P0");
        actualizarPanelPuertos();
        actualizarPanelRutasMaritimas();
        //nav.altaBarco("B1", , null)
        /*for (int i = 0; i < 15; i++) {
         nav.altaPuerto("P" + i, Paises.getPaisRandom(), (int) Math.floor(Math.random() * (1 - 200 + 1) + 200));
         for (int j = 0; j < 5; j++) {
         int tipoRandom = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
         String tipo;
         switch (tipoRandom) {
         case 1:
         tipo = "Pesquero";
         break;
         case 2:
         tipo = "Pasajeros";
         break;
         case 3:
         tipo = "Carga";
         break;
         default:
         tipo = "Pesquero";
         break;
         }
         nav.altaBarco("B" + i + j, tipo, Paises.getPaisRandom());
         nav.ponerBarco("B" + i + j, "P" + i);
         }
         }
         for (int i = 0; i < 15; i++) {
         for (int j = 0; j < 5; j++) {
         nav.altaRutaMaritima("P" + i, "P" + (int) Math.floor(Math.random() * (1 - 32 + 1) + 32), (int) Math.floor(Math.random() * (1 - 10000 + 1) + 10000));
         }
         }
         actualizarPanelPuertos();
         actualizarPanelRutasMaritimas();
         buttonPrecargarDatos.setEnabled(false);*/
    }//GEN-LAST:event_buttonPrecargarDatosActionPerformed

    private void buttonAltaPuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAltaPuertosActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        MenuAltaPuerto menuAux = new MenuAltaPuerto(nav, this, this);
        menuAux.setVisible(true);
    }//GEN-LAST:event_buttonAltaPuertosActionPerformed

    private void buttonAltaRutasMaritimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAltaRutasMaritimasActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        MenuAltaRutaMaritima menuAux = new MenuAltaRutaMaritima(nav, this, this);
        menuAux.setVisible(true);
    }//GEN-LAST:event_buttonAltaRutasMaritimasActionPerformed

    private void buttonOtrasOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOtrasOpcionesActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        MenuOtrasOpciones menuAux = new MenuOtrasOpciones(nav, this, this);
        menuAux.setVisible(true);
    }//GEN-LAST:event_buttonOtrasOpcionesActionPerformed

    private void buttonDibujarAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDibujarAVLActionPerformed
        nav.printTablaPuertos();
    }//GEN-LAST:event_buttonDibujarAVLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAltaPuertos;
    private javax.swing.JButton buttonAltaRutasMaritimas;
    private javax.swing.JButton buttonDibujarAVL;
    private javax.swing.JButton buttonOtrasOpciones;
    private javax.swing.JButton buttonPrecargarDatos;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelOpcPuertos;
    private javax.swing.JPanel panelOpcRutasMaritimas;
    private javax.swing.JPanel panelPuertos;
    private javax.swing.JPanel panelRutasMaritimas;
    private javax.swing.JScrollPane scrollPuertos;
    private javax.swing.JScrollPane scrollRutasMaritimas;
    // End of variables declaration//GEN-END:variables
}
