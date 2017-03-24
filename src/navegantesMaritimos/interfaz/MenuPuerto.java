package navegantesMaritimos.interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import lineales.lista.Lista;
import navegantesMaritimos.NavegantesMaritimos;
import navegantesMaritimos.interfaz.otros.*;

/**
 * Este menu muestra los datos de un puerto y los barcos que hay en las darsenas
 * y en la cola de espera.
 */
public class MenuPuerto extends javax.swing.JFrame {

    private final NavegantesMaritimos nav;
    private final JFrame framePadre;
    private final MenuPrincipal menuPadre;
    private final String[] datosPuerto;

    public MenuPuerto(NavegantesMaritimos nav, JFrame framePadre, MenuPrincipal menuPadre, String nombrePuerto) {
        this.nav = nav;
        this.framePadre = framePadre;
        this.menuPadre = menuPadre;
        datosPuerto = nav.arregloDatosPuerto(nombrePuerto);
        initComponents();
        inicializarLabels();
        actualizarPanelBarcosEspera();
        actualizarPanelDarsenas();
        this.setLocationRelativeTo(null);
    }

    public void inicializarLabels() {
        labelCampoNombre.setText(datosPuerto[0]);
        labelCampoCantDarsenas.setText(datosPuerto[2]);
        labelBandera.setIcon(new ImageIcon(getClass().getResource("../recursos/banderas/" + Paises.conseguirAlias(datosPuerto[1]) + ".png")));
        labelBandera.setToolTipText(datosPuerto[1]);
    }

    public void actualizarPanelDarsenas() {
        panelDarsenas.setPreferredSize(new Dimension(390, 64));
        panelDarsenas.removeAll();
        String[] barcos = nav.arregloDarsenas(datosPuerto[0]);
        if (barcos != null) {
            int fila = 0;
            int col = 0;
            for (int i = 0; i < barcos.length; i++) {
                String[] barco = nav.arregloDatosBarco(barcos[i]);
                agregarBarcoAPanelDarsena(barco, col, fila);
                if ((i + 1) % 6 == 0) {
                    fila++;
                    col -= 6;
                    double preferedWidth = panelDarsenas.getPreferredSize().getWidth();
                    double preferedHeight = panelDarsenas.getPreferredSize().getHeight();
                    panelDarsenas.setPreferredSize(new Dimension((int) preferedWidth, (int) preferedHeight + 64));
                }
                col++;
            }
        }
        panelDarsenas.repaint();
    }

    public void agregarBarcoAPanelDarsena(final String[] barco, int col, int fila) {
        final JLabel labelBarco = new JLabel();
        labelBarco.setBounds(64 * col, 64 * fila, 64, 64);
        Border border;
        if (barco != null) {
            labelBarco.setText(barco[0]);
            labelBarco.setToolTipText(
                    "<html>"
                    + "<p>" + barco[0] + "</p>"
                    + "<p>" + barco[1] + "</p>"
                    + "<p>" + barco[2] + "</p>"
                    + "</html>");
            switch (barco[1]) {
                case "Pesquero":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/pesquero.png")));
                    break;
                case "Pasajeros":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/pasajeros.png")));
                    break;
                case "Carga":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/carga.png")));
                    break;
            }
            labelBarco.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labelBarcoMouseClicked(barco[0]);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    labelBarco.setBorder(LineBorder.createBlackLineBorder());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    labelBarco.setBorder(null);
                }
            });
            JLabel labelEnviarBarco = new JLabel();
            labelEnviarBarco.setBounds((64 * col) + 48, (64 * fila), 16, 16);
            labelEnviarBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/enviarBarco.png")));
            labelEnviarBarco.setToolTipText("Enviar barco a...");
            panelDarsenas.add(labelEnviarBarco);
            labelEnviarBarco.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labelEnviarBarcoMouseClicked(barco[0]);
                }
            });
            JLabel labelPais = new JLabel();
            labelPais.setBounds((64 * col) + 34, (64 * fila) + 44, 30, 20);
            labelPais.setIcon(new ImageIcon(getClass().getResource("../recursos/banderas/" + Paises.conseguirAlias(barco[2]) + ".png")));
            panelDarsenas.add(labelPais);
        } else {
            labelBarco.setToolTipText("Darsena vacia");
            labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/darsena.png")));
            labelBarco.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    labelBarco.setBorder(LineBorder.createGrayLineBorder());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    labelBarco.setBorder(null);
                }
            });
        }
        panelDarsenas.add(labelBarco);
    }

    public void actualizarPanelBarcosEspera() {
        panelBarcos.setPreferredSize(new Dimension(390, 64));
        panelBarcos.removeAll();
        Lista<String> barcos = nav.listarBarcosEspera(datosPuerto[0]);
        if (barcos != null) {
            int fila = 0;
            int col = 0;
            for (int i = 1; i <= barcos.longitud(); i++) {
                String[] barco = nav.arregloDatosBarco(barcos.recuperar(i));
                agregarBarcoAPanelEspera(barco, col, fila);
                if (i % 6 == 0) {
                    fila++;
                    col -= 6;
                    double preferedWidth = panelBarcos.getPreferredSize().getWidth();
                    double preferedHeight = panelBarcos.getPreferredSize().getHeight();
                    panelBarcos.setPreferredSize(new Dimension((int) preferedWidth, (int) preferedHeight + 64));
                }
                col++;
            }
        }
        panelBarcos.repaint();
    }

    public void agregarBarcoAPanelEspera(final String[] barco, int col, int fila) {
        if (barco != null) {
            final JLabel labelBarco = new JLabel();
            labelBarco.setBounds(64 * col, 64 * fila, 64, 64);
            labelBarco.setText(barco[0]);
            labelBarco.setToolTipText(
                    "<html>"
                    + "<p>" + barco[0] + "</p>"
                    + "<p>" + barco[1] + "</p>"
                    + "<p>" + barco[2] + "</p>"
                    + "</html>");
            switch (barco[1]) {
                case "Pesquero":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/pesquero.png")));
                    break;
                case "Pasajeros":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/pasajeros.png")));
                    break;
                case "Carga":
                    labelBarco.setIcon(new ImageIcon(getClass().getResource("../recursos/barcos/carga.png")));
                    break;
            }
            labelBarco.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labelBarcoMouseClicked(barco[0]);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Border border = LineBorder.createBlackLineBorder();
                    labelBarco.setBorder(border);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    labelBarco.setBorder(null);
                }
            });
            JLabel labelPais = new JLabel();
            labelPais.setBounds((64 * col) + 34, (64 * fila) + 44, 30, 20);
            labelPais.setIcon(new ImageIcon(getClass().getResource("../recursos/banderas/" + Paises.conseguirAlias(barco[2]) + ".png")));
            panelBarcos.add(labelPais);
            panelBarcos.add(labelBarco);
        }
    }

    private void labelBarcoMouseClicked(String barco) {
        this.setEnabled(false);
        this.setVisible(false);
        MenuBarco menuAux = new MenuBarco(nav, this, barco);
        menuAux.setVisible(true);
    }

    private void labelEnviarBarcoMouseClicked(String barco) {
        this.setEnabled(false);
        this.setVisible(false);
        MenuEnviarBarco menuAux = new MenuEnviarBarco(nav, this, this, datosPuerto[0], barco);
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

        labelImagenPuerto = new javax.swing.JLabel();
        panelDatosPuerto = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        labelBandera = new javax.swing.JLabel();
        labelCampoNombre = new javax.swing.JLabel();
        buttonAltaBarco = new javax.swing.JButton();
        buttonActualizarDarsenas = new javax.swing.JButton();
        labelCantDarsenas = new javax.swing.JLabel();
        labelCampoCantDarsenas = new javax.swing.JLabel();
        tabbedBarcos = new javax.swing.JTabbedPane();
        scrollDarsenas = new javax.swing.JScrollPane();
        panelDarsenas = new javax.swing.JPanel();
        scrollBarcos = new javax.swing.JScrollPane();
        panelBarcos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Puerto");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        labelImagenPuerto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/navegantesMaritimos/recursos/puertos/puertoPerfil.png"))); // NOI18N
        labelImagenPuerto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelDatosPuerto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Puerto"));

        labelNombre.setText("Nombre:");

        labelBandera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/navegantesMaritimos/recursos/banderas/null.png"))); // NOI18N
        labelBandera.setToolTipText("");

        buttonAltaBarco.setText("Alta de barco");
        buttonAltaBarco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAltaBarcoActionPerformed(evt);
            }
        });

        buttonActualizarDarsenas.setText("Actualizar darsenas");
        buttonActualizarDarsenas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActualizarDarsenasActionPerformed(evt);
            }
        });

        labelCantDarsenas.setText("Cantidad de darsenas:");

        javax.swing.GroupLayout panelDatosPuertoLayout = new javax.swing.GroupLayout(panelDatosPuerto);
        panelDatosPuerto.setLayout(panelDatosPuertoLayout);
        panelDatosPuertoLayout.setHorizontalGroup(
            panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPuertoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosPuertoLayout.createSequentialGroup()
                        .addComponent(labelNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCampoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBandera, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDatosPuertoLayout.createSequentialGroup()
                        .addComponent(buttonAltaBarco)
                        .addGap(18, 18, 18)
                        .addComponent(buttonActualizarDarsenas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDatosPuertoLayout.createSequentialGroup()
                        .addComponent(labelCantDarsenas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCampoCantDarsenas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDatosPuertoLayout.setVerticalGroup(
            panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosPuertoLayout.createSequentialGroup()
                .addGroup(panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBandera, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelNombre)
                        .addComponent(labelCampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCantDarsenas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCampoCantDarsenas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(panelDatosPuertoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAltaBarco)
                    .addComponent(buttonActualizarDarsenas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedBarcos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        scrollDarsenas.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDarsenas.setHorizontalScrollBar(null);

        panelDarsenas.setBackground(new java.awt.Color(204, 204, 204));
        panelDarsenas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelDarsenas.setPreferredSize(new java.awt.Dimension(390, 64));

        javax.swing.GroupLayout panelDarsenasLayout = new javax.swing.GroupLayout(panelDarsenas);
        panelDarsenas.setLayout(panelDarsenasLayout);
        panelDarsenasLayout.setHorizontalGroup(
            panelDarsenasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        panelDarsenasLayout.setVerticalGroup(
            panelDarsenasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        scrollDarsenas.setViewportView(panelDarsenas);

        tabbedBarcos.addTab("Darsenas", scrollDarsenas);

        scrollBarcos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBarcos.setHorizontalScrollBar(null);

        panelBarcos.setBackground(new java.awt.Color(102, 102, 102));
        panelBarcos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBarcos.setPreferredSize(new java.awt.Dimension(390, 64));

        javax.swing.GroupLayout panelBarcosLayout = new javax.swing.GroupLayout(panelBarcos);
        panelBarcos.setLayout(panelBarcosLayout);
        panelBarcosLayout.setHorizontalGroup(
            panelBarcosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        panelBarcosLayout.setVerticalGroup(
            panelBarcosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        scrollBarcos.setViewportView(panelBarcos);

        tabbedBarcos.addTab("Cola de espera", scrollBarcos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedBarcos, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelImagenPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDatosPuerto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDatosPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelImagenPuerto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedBarcos, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAltaBarcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAltaBarcoActionPerformed
        this.setEnabled(false);
        this.setVisible(false);
        MenuAltaBarco menuAux = new MenuAltaBarco(nav, this, this, datosPuerto[0]);
        menuAux.setVisible(true);
    }//GEN-LAST:event_buttonAltaBarcoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        framePadre.setEnabled(true);
        framePadre.setVisible(true);
        framePadre.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void buttonActualizarDarsenasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActualizarDarsenasActionPerformed
        nav.actualizarDarsenas(datosPuerto[0]);
        actualizarPanelDarsenas();
        actualizarPanelBarcosEspera();
    }//GEN-LAST:event_buttonActualizarDarsenasActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPuerto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPuerto(null, null, null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActualizarDarsenas;
    private javax.swing.JButton buttonAltaBarco;
    private javax.swing.JLabel labelBandera;
    private javax.swing.JLabel labelCampoCantDarsenas;
    private javax.swing.JLabel labelCampoNombre;
    private javax.swing.JLabel labelCantDarsenas;
    private javax.swing.JLabel labelImagenPuerto;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JPanel panelBarcos;
    private javax.swing.JPanel panelDarsenas;
    private javax.swing.JPanel panelDatosPuerto;
    private javax.swing.JScrollPane scrollBarcos;
    private javax.swing.JScrollPane scrollDarsenas;
    private javax.swing.JTabbedPane tabbedBarcos;
    // End of variables declaration//GEN-END:variables
}
