
import java.awt.Color;
import javax.lang.model.util.ElementScanner14;

public class Nominas extends javax.swing.JDialog {
    static String nombreUsuario = "";
    private boolean arbol_expandido = false;
    private int arbol_seleccionado = 0;

    public Nominas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Nominas");
    }

    

    private void initComponents() {
        nombreUsuario = Bienvenido.nombreUsuario;
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();       
        jButton1 = new Menu().crearJButton1();
        jButton2 = new Menu().crearJButton2();
        jButton3 = new Menu().crearJButton3();
        jButton4 = new Menu().crearJButton4();
        jButton5 = new Menu().crearJButton5();
        jButton6 = new Menu().crearJButton6();
        jButton7 = new Menu().crearJButton7();
        jButton8 = new Menu().crearJButton8();
        jButton9 = new Menu().crearJButton9();
        jButton10 = new Menu().crearJButton10();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(getIconImages());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBackground(new java.awt.Color(157, 153, 155));

      /* *jButton1.setForeground(Color.white);
        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Arial", 0, 16));
        jButton1.setText("Inicio");

        jButton2.setForeground(Color.white);
        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Arial", 0, 14));
        jButton2.setText("Mail");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        

        jButton3.setForeground(Color.white);
        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Arial", 0, 12));
        jButton3.setText("Datos personales");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setForeground(Color.white);
        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12));
        jButton4.setText("Seguro médico");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setFont(new java.awt.Font("Arial", 0, 12));
        jButton5.setText("Mis nóminas");

        jButton6.setForeground(Color.white);
        jButton6.setBackground(new java.awt.Color(153, 204, 255));
        jButton6.setFont(new java.awt.Font("Arial", 0, 12));
        jButton6.setText("Descargar");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton7.setForeground(Color.white);
        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12));
        jButton7.setText("Mis vacaciones");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jButton8.setForeground(Color.white);
        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Arial", 0, 12));
        jButton8.setText("Mis cursos");
        jButton8.addActionListener(this::jButton8ActionPerformed);

        jButton9.setForeground(Color.white);
        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.setFont(new java.awt.Font("Arial", 0, 12));
        jButton9.setText("Noticias ");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        jButton10.setForeground(Color.white);
        jButton10.setBackground(new java.awt.Color(102, 102, 102));
        jButton10.setFont(new java.awt.Font("Arial", 0, 14));
        jButton10.setText("Salir");
        jButton10.addActionListener(this::jButton10ActionPerformed);*/

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton8)
                    .addGap(5, 5, 5)
                    .addComponent(jButton4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton10)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        switch (nombreUsuario) {
            case "dani", "danjimfra" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/dani/1.jpg")));
            case "jose", "jospedlop" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/jose/1.jpg")));
            case "marorthat" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/marorthat/1.jpg")));
            case "rodmorcru" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/rodmorcru/1.jpg")));
            default -> throw new IllegalArgumentException("Unexpected value: " + nombreUsuario);
        }

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24));
        jLabel4.setText("Recibo de nóminas");
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nominas/Foto_Cabezera.png")));
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setText("Buscar");

        configurarArbol();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(12, Short.MAX_VALUE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        pack();
    }

    private void configurarArbol() {
        jTree1.setBackground(new java.awt.Color(250, 250, 250));
        javax.swing.tree.DefaultMutableTreeNode root = new javax.swing.tree.DefaultMutableTreeNode("WorkSpace");

        javax.swing.tree.DefaultMutableTreeNode dic = new javax.swing.tree.DefaultMutableTreeNode("Nómina Diciembre");
        dic.add(new javax.swing.tree.DefaultMutableTreeNode("01/12/2024-31/12/2024"));
        root.add(dic);

        javax.swing.tree.DefaultMutableTreeNode nov = new javax.swing.tree.DefaultMutableTreeNode("Nómina Noviembre");
        nov.add(new javax.swing.tree.DefaultMutableTreeNode("01/11/2024-30/11/2024"));
        root.add(nov);

        javax.swing.tree.DefaultMutableTreeNode oct = new javax.swing.tree.DefaultMutableTreeNode("Nómina Octubre");
        oct.add(new javax.swing.tree.DefaultMutableTreeNode("01/10/2024-31/10/2024"));
        root.add(oct);

        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(root));
        jTree1.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            @Override
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
                arbol_expandido = false;
                jLabel3.setText("<html><div style='text-align: center;'>" + nombreUsuario + " seleccione una nómina para visualizar.</div></html>");
            }

            @Override
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
                // Handle tree collapsed event if needed
            }
        });
        jTree1.addTreeSelectionListener(this::jTree1ValueChanged);
        jScrollPane1.setViewportView(jTree1);
    }

    private void guardarImagenEnEscritorio(String nombreArchivo) {
        try {
            var image = ((javax.swing.ImageIcon) jLabel3.getIcon()).getImage();
            var bufferedImage = new java.awt.image.BufferedImage(image.getWidth(null), image.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_ARGB);
            var g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();
            var outputfile = new java.io.File(System.getProperty("user.home") + "/Desktop/" + nombreArchivo);
            javax.imageio.ImageIO.write(bufferedImage, "png", outputfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarVentanaDescarga(String mensaje) {
        var ventana = new Descarga_archivos(mensaje);
        ventana.setBounds(0, 0, 290, 200);
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        UserProfile perfil = new UserProfile((java.awt.Frame) null, false);
        perfil.setVisible(true);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        String msg = switch (arbol_seleccionado) {
            case 1 -> {
                guardarImagenEnEscritorio("nomina_diciembre.png");
                yield "Nómina de Diciembre descargada en el escritorio";
            }
            case 2 -> {
                guardarImagenEnEscritorio("nomina_octubre.png");
                yield "Nómina de Octubre descargada en el escritorio";
            }
            case 3 -> {
                guardarImagenEnEscritorio("nomina_noviembre.png");
                yield "Nómina de Noviembre descargada en el escritorio";
            }
            default -> "No hay ninguna nómina seleccionada para la descarga.";
        };
        jLabel3.setText("<html><div style='text-align: center;'>" + msg + "</div></html>");
        if (arbol_seleccionado > 0) mostrarVentanaDescarga(msg);
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        JCalendar1 vacaciones = new JCalendar1();
        vacaciones.setVisible(true);
    }
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {}
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        dispose();
    }

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {
        var node = (javax.swing.tree.DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        if (node == null) return;
        arbol_expandido = true;
        switch (node.toString()) {
            case "Nómina Diciembre" -> {
                arbol_seleccionado = 1;
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/" + nombreUsuario + "/Nomina_Daniel1.png")));
            }
            case "Nómina Octubre" -> {
                arbol_seleccionado = 2;
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/" + nombreUsuario + "/Nomina_Daniel2.png")));
            }
            case "Nómina Noviembre" -> {
                arbol_seleccionado = 3;
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/" + nombreUsuario + "/Nomina_Daniel3.png")));
            }
        }
    }

    // Variables declaration
    private javax.swing.JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9, jButton10;
    private javax.swing.JLabel jLabel2, jLabel3, jLabel4, jLabel5;
    private javax.swing.JPanel jPanel1, jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTree jTree1;
}

/* 
import java.awt.Color;
import javax.lang.model.util.ElementScanner14;


public class Nominas extends javax.swing.JDialog {
    // Variables de la clase Nominas
    static String nombreUsuario = "";
    private boolean arbol_expandido = false;
    private int arbol_seleccionado = 0;
    

    // Constructor de la clase Nominas
    public Nominas(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Nominas");
    }

    // Método para inicializar los componentes del formulario
    private void initComponents() {
        boolean arbol_expandido = false;
        int arbol_seleccionado = 0;
        
        nombreUsuario = Bienvenido.nombreUsuario;
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();        jButton1 = new Menu().crearJButton1();
        jButton2 = new Menu().crearJButton2();
        jButton3 = new Menu().crearJButton3();
        jButton4 = new Menu().crearJButton4();
        jButton5 = new Menu().crearJButton5();
        jButton6 = new Menu().crearJButton6();
        jButton7 = new Menu().crearJButton7();
        jButton8 = new Menu().crearJButton8();
        jButton9 = new Menu().crearJButton9();
        jButton10 = new Menu().crearJButton10();
jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        // Configuración de la ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(getIconImages());
       // setLocation(getLocationOnScreen());

        // Configuración del panel principal
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        // Configuración del panel secundario
        jPanel2.setBackground(new java.awt.Color(157, 153, 155));

        // Configuración de los botones
        jButton1.setForeground(Color.white);
        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jButton1.setText("Inicio");

        jButton2.setForeground(Color.white);
        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("Mail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setForeground(Color.white);
        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton3.setText("Datos personales");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setForeground(Color.white);
        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton4.setText("Seguro médico");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton5.setText("Mis nóminas");

        jButton6.setForeground(Color.white);
        jButton6.setBackground(new java.awt.Color(153, 204, 255)); // Light blue color
        jButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton6.setText("Descargar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setForeground(Color.white);
        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton7.setText("Mis vacaciones");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setForeground(Color.white);
        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton8.setText("Mis cursos");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setForeground(Color.white);
        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton9.setText("Noticias ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setForeground(Color.white);
        jButton10.setBackground(new java.awt.Color(102, 102, 102));
        jButton10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton10.setText("Salir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        
        

        // Configuración del layout del panel secundario
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(5, 5, 5)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //jLabel3.setIcon(null);
        switch (nombreUsuario) {
            case "dani", "danjimfra" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/dani/1.jpg")));
            case "jose", "jospedlop" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/jose/1.jpg")));
            case "marorthat" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/marorthat/1.jpg")));
            case "rodmorcru" -> jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/rodmorcru/1.jpg")));
            default -> throw new IllegalArgumentException("Unexpected value: " + nombreUsuario);
        }

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setText("Recibo de nóminas");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nominas/Foto_Cabezera.png")));

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(jTextField2.getFont());
        jTextField2.setText("Buscar");

        // Configuración del árbol de nóminas
        jTree1.setBackground(new java.awt.Color(250, 250, 250));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("WorkSpace");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Año 2024");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Nómina Diciembre");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("01/12/2024-31/12/2024");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Nómina Noviembre");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("01/12/2024-30/12/2024");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Nómina Octubre");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("01/12/2024-31/12/2024");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
                jLabel3.setIcon(null);
            }
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
                jTree1TreeExpanded(evt);
            }
        });
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

    

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );


getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
pack();
}
// Acción del botón 2
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}
private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    UserProfile UserProfileApp = new UserProfile((java.awt.Frame) null, false);
    UserProfileApp.setVisible(true);
}
private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    
}
private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}
private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
    String message = "Nomina descargada en el escritorio";
    
        if( !arbol_expandido){// Si no hay un nodo seleccionado
            message = "No hay ninguna nomina seleccionada para la descarga.";
            jLabel3.setText("<html><div style='text-align: center;'>" + message + "</div></html>");
           
            
        }else if(arbol_seleccionado==1){// Si hay un nodo seleccionado
            message = "Nomina de Diciembre descargada en el escritorio";
            java.awt.Image image = ((javax.swing.ImageIcon) jLabel3.getIcon()).getImage();
            java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(image.getWidth(null), image.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();
            try {
                java.io.File outputfile = new java.io.File(System.getProperty("%USERNAME%") + "/Desktop/nomina_diciembre.png");
                javax.imageio.ImageIO.write(bufferedImage, "png", outputfile);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            Descarga_archivos ventanaSolicitud = new Descarga_archivos(message);// Create a new instance of Descarga_archivos
            ventanaSolicitud.setBounds(0, 0, 290, 200); // Tamaño de la ventana
            ventanaSolicitud.setVisible(true); // Hacer visible la ventana
            ventanaSolicitud.setResizable(false); // No se puede redimensionar la ventana
            ventanaSolicitud.setLocationRelativeTo(null); // Centrar la ventana
            this.setVisible(false); // Ocultar la ventana actual
        }else if(arbol_seleccionado==2){// Si hay un nodo seleccionado
            java.awt.Image image = ((javax.swing.ImageIcon) jLabel3.getIcon()).getImage();
            java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(image.getWidth(null), image.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();
            try {
                java.io.File outputfile = new java.io.File(System.getProperty("user.home") + "/Desktop/nomina_octubre.png");
                javax.imageio.ImageIO.write(bufferedImage, "png", outputfile);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            message = "Nomina de Octubre descargada en el escritorio";
            Descarga_archivos ventanaSolicitud = new Descarga_archivos(message);// Create a new instance of Descarga_archivos
            ventanaSolicitud.setBounds(0, 0, 290, 200); // Tamaño de la ventana
            ventanaSolicitud.setVisible(true); // Hacer visible la ventana
            ventanaSolicitud.setResizable(false); // No se puede redimensionar la ventana
            ventanaSolicitud.setLocationRelativeTo(null); // Centrar la ventana
            this.setVisible(false); // Ocultar la ventana actual}
        
        }else if(arbol_seleccionado==3){// Si hay un nodo seleccionado
            java.awt.Image image = ((javax.swing.ImageIcon) jLabel3.getIcon()).getImage();
            java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(image.getWidth(null), image.getHeight(null), java.awt.image.BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, 0, 0, null);
            g2.dispose();
            try {
                java.io.File outputfile = new java.io.File(System.getProperty("user.home") + "/Desktop/nomina_octubre.png");
                javax.imageio.ImageIO.write(bufferedImage, "png", outputfile);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            message = "Nomina de Noviembre descargada en el escritorio";
            Descarga_archivos ventanaSolicitud = new Descarga_archivos(message);// Create a new instance of Descarga_archivos
            ventanaSolicitud.setBounds(0, 0, 290, 200); // Tamaño de la ventana
            ventanaSolicitud.setVisible(true); // Hacer visible la ventana
            ventanaSolicitud.setResizable(false); // No se puede redimensionar la ventana
            ventanaSolicitud.setLocationRelativeTo(null); // Centrar la ventana
            this.setVisible(false); // Ocultar la ventana actual}
        }
        

    
}
private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
    JCalendar1 vacacionesApp = new JCalendar1();
        vacacionesApp.setVisible(true);
}
private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}
private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}
private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
    setVisible(false);
        dispose(); 
}
private void jTree1TreeExpanded(javax.swing.event.TreeExpansionEvent evt) {                                    
    // TODO add your handling code here:
    nombreUsuario = Bienvenido.nombreUsuario;
     arbol_expandido = false;
    jLabel3.setText("<html><div style='text-align: center;'>" +nombreUsuario+" seleccione una nómina para visualizar."+"</div></html>");

}                                   

private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {
    // Obtén el nodo seleccionado
    javax.swing.tree.DefaultMutableTreeNode selectedNode = 
        (javax.swing.tree.DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        nombreUsuario = Bienvenido.nombreUsuario;
    if (selectedNode != null) {
    arbol_expandido = true;
        // Verifica el nombre del nodo o cualquier otra propiedad
        String nodeName = selectedNode.toString();
        if (nodeName.equals("Nómina Diciembre")) {
        arbol_seleccionado = 1;

            switch (nombreUsuario) {
                case "dani":
                case "danjimfra":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/dani/Nomina_Daniel1.png")));
                    break;
                case "jose":
                case "jospedlop":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/jose/Nomina_Jose1.png")));
                    break;
                case "marorthat":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/marorthat/Nomina_Maria1.png")));
                    break;
                case "rodmorcru":
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/rodmorcru/Nomina.png")));// Add appropriate action for "rodmorcru" if needed
                    break;               
            }
         }else if(nodeName.equals("Nómina Octubre")){
            arbol_seleccionado = 2;
            // Puedes manejar otros nodos aquí
            switch (nombreUsuario) {
                case "dani":
                case "danjimfra":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/dani/Nomina_Daniel2.png")));
                    break;
                case "jose":
                case "jospedlop":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/jose/Nomina_Jose2.png")));
                    break;
                case "marorthat":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/marorthat/Nomina_Maria2.png")));
                    break;
                case "rodmorcru":
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/rodmorcru/Nomina1.png")));// Add appropriate action for "rodmorcru" if needed
                    break;               
            }
        }else if(nodeName.equals("Nómina Noviembre")){
            arbol_seleccionado = 3;
            switch (nombreUsuario) {
                case "dani":
                case "danjimfra":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/dani/Nomina_Daniel3.png")));
                    break;
                case "jose":
                case "jospedlop":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/jose/Nomina_Jose3.png")));
                    break;
                case "marorthat":
                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/marorthat/Nomina_Maria3.png")));
                    break;
                case "rodmorcru":
                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuarios/rodmorcru/Nomina2.png")));// Add appropriate action for "rodmorcru" if needed
                    break;               
            }
        }
    }
}     


/**
 * @param args the command line arguments
 


// Variables declaration - do not modify
private javax.swing.JButton jButton1;
private javax.swing.JButton jButton10;
private javax.swing.JButton jButton2;
private javax.swing.JButton jButton3;
private javax.swing.JButton jButton4;
private javax.swing.JButton jButton5;
private javax.swing.JButton jButton6;
private javax.swing.JButton jButton7;
private javax.swing.JButton jButton8;
private javax.swing.JButton jButton9;
private javax.swing.JLabel jLabel2;
private javax.swing.JLabel jLabel3;
private javax.swing.JLabel jLabel4;
private javax.swing.JPanel jPanel1;
private javax.swing.JPanel jPanel2;
private javax.swing.JScrollPane jScrollPane1;
private javax.swing.JLabel jLabel5;
private javax.swing.JTextField jTextField2;
private javax.swing.JTree jTree1;
// End of variables declaration
}
*/

