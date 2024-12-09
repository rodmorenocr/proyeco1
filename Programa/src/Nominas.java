
import java.awt.Color;


public class Nominas extends javax.swing.JDialog {

    // Constructor de la clase Nominas
    public Nominas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setTitle("Nominas");
    }

    // Método para inicializar los componentes del formulario
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
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
        jButton6.setBackground(new java.awt.Color(102, 102, 102));
        jButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton6.setText("Mi horario");

        jButton7.setForeground(Color.white);
        jButton7.setBackground(new java.awt.Color(102, 102, 102));
        jButton7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton7.setText("Mis vacaciones");

        jButton8.setForeground(Color.white);
        jButton8.setBackground(new java.awt.Color(102, 102, 102));
        jButton8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton8.setText("Mis cursos");

        jButton9.setForeground(Color.white);
        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton9.setText("Noticias ");

        jButton10.setForeground(Color.white);
        jButton10.setBackground(new java.awt.Color(102, 102, 102));
        jButton10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton10.setText("Cerrar sesión");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Foto_de_persona.png")));

        // Configuración del layout del panel secundario
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel3.setText("Calendario");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel4.setText("Recibo de nóminas");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Foto_Cabezera.png")));

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(jTextField2.getFont());
        jTextField2.setText("Buscar");

        // Configuración del árbol de nóminas
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("WorkSpace Marina");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Año 2024");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Recibos de nómina:");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Noviembre");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Octubre");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Septiembre");
        treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Agosto");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Julio");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Junio");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Mayo");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Abril");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Marzo");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Febrero");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Enero");
treeNode2.add(treeNode3);
treeNode1.add(treeNode2);

treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Año 2023");
treeNode1.add(treeNode2);
treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Recibos de nómina:");
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Noviembre");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Octubre");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Septiembre");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Agosto");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Julio");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Junio");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Mayo");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Abril");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Marzo");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Febrero");
treeNode2.add(treeNode3);
treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Recibo de nómina de Enero");
treeNode2.add(treeNode3);
treeNode1.add(treeNode2);

jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
jScrollPane1.setViewportView(jTree1);

// Configuración del layout del panel principal
javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(jLabel5)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))))
);
jPanel1Layout.setVerticalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
pack();
}
// Acción del botón 2
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}

// Acción del botón 4
private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code aquí:
}

/**
 * @param args the command line arguments
 */


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

    // Variables declaration - do not modify                     
    
    // End of variables declaration                   

//jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Captura de pantalla 2024-11-10 182825.png")));
/**
    jButton17.setBackground(new java.awt.Color(153, 153, 255));//Color del fondo
        jButton17.setFont(new java.awt.Font("Arial", 1, 14)); // Ajustes de tamaño y color de la fuente de texto
        jButton17.setForeground(new java.awt.Color(0, 102, 153));//Color al pulsar
        jButton17.setText("Cerrar sesión");
        jButton17.addActionListener(new java.awt.event.ActionListener() {//Evento al ser pulsado
            public void actionPerformed(java.awt.event.ActionEvent evt) {//Clase que lanza
                jButton17ActionPerformed(evt);
            } 
 */

