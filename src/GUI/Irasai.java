package GUI;

import Biudzeto_skaiciuokle.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Irasai extends javax.swing.JFrame {
    Islaidos islaidos;
    Pajamos pajamos;
    PagrindinisLangas main;
    KoreguotiIšlaidas koreguotiIslaidas = new KoreguotiIšlaidas();
    KoreguotiPajamas koreguotiPajamas = new KoreguotiPajamas(); 
    TOP3 islaidosTOP = new TOP3();
    int table = 1;
    int i = 0;
    int id;
    public TableModel model;
    public String suma;
    String kategorija;
    Date data;
    String komentaras;

    public Irasai() {
        initComponents();
        islaiduTable();
        recordsName.setText("Išlaidų istorija");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(130);
        jRadioButton1.setSelected(true);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jButton1.setEnabled(false);
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem3.setSelected(false);
        icon();
    }
    
    private void icon(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/papildomai/icon.jpg")));
    }

    public void setMain(PagrindinisLangas main) {
        this.main = main;
    }

    public String getSuma() {
        return suma;
    }    
    
    public void islaiduTable(){
        ArrayList<Islaidos> islaiduList = islaidos.islaidosList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int  i = 0; i < islaiduList.size(); i++){
            row[0] = islaiduList.get(i).getId();
            row[1] = islaiduList.get(i).getSuma();
            row[2] = islaiduList.get(i).getIslaidu_kategorija();
            row[3] = islaiduList.get(i).getIslaidu_data();
            if(islaiduList.get(i).getKomentaras() == null){
                row[4] = "";
            }
            else
            row[4] = islaiduList.get(i).getKomentaras();
            model.addRow(row);
        }
    }    

    public void pajamuTable(){
        ArrayList<Pajamos> pajamosList = pajamos.pajamosList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int  i = 0; i < pajamosList.size(); i++){
            row[0] = pajamosList.get(i).getId();
            row[1] = pajamosList.get(i).getSuma();
            row[2] = pajamosList.get(i).getKategorija();
            row[3] = pajamosList.get(i).getData();
            if(pajamosList.get(i).getKomentaras() == null){
                row[4] = "";
            }
            else
            row[4] = pajamosList.get(i).getKomentaras();
            model.addRow(row);
        }
    }
    
    public void islaiduFiltredTable(){
        try{
            if(jDateChooser1.getDate() != null && jDateChooser2.getDate() != null && jDateChooser1.getDate().compareTo(jDateChooser2.getDate()) > 0){
                JOptionPane.showMessageDialog(this, "Pradžios data didesnė už pabaigos", "Klaida", JOptionPane.ERROR_MESSAGE);
            }
            else{
                ArrayList<Islaidos> islaiduList = islaidos.filtredIslaidosList(jDateChooser1.getDate(), jDateChooser2.getDate());
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                for(int  i = 0; i < islaiduList.size(); i++){
                    row[0] = islaiduList.get(i).getId();
                    row[1] = islaiduList.get(i).getSuma();
                    row[2] = islaiduList.get(i).getIslaidu_kategorija();
                    row[3] = islaiduList.get(i).getIslaidu_data();
                    if(islaiduList.get(i).getKomentaras() == null){
                        row[4] = "";
                    }
                    else
                    row[4] = islaiduList.get(i).getKomentaras();
                    model.addRow(row);
                }
            }
        }catch (Exception ex) {     
            JOptionPane.showMessageDialog(this, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        }        
    }   

    public void pajamuFiltredTable(){
        try{
            if(jDateChooser1.getDate() != null && jDateChooser2.getDate() != null && jDateChooser1.getDate().compareTo(jDateChooser2.getDate()) > 0){
                JOptionPane.showMessageDialog(this, "Pradžios data didesnė už pabaigos", "Klaida", JOptionPane.ERROR_MESSAGE);
            }
            else{
                System.out.println(jDateChooser1.getDate().compareTo(jDateChooser2.getDate()));
                ArrayList<Pajamos> pajamuList = pajamos.filtredPajamosList(jDateChooser1.getDate(), jDateChooser2.getDate());
                DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
                model.setRowCount(0);
                Object[] row = new Object[5];
                for(int  i = 0; i < pajamuList.size(); i++){
                    row[0] = pajamuList.get(i).getId();
                    row[1] = pajamuList.get(i).getSuma();
                    row[2] = pajamuList.get(i).getKategorija();
                    row[3] = pajamuList.get(i).getData();
                    if(pajamuList.get(i).getKomentaras() == null){
                        row[4] = "";
                    }
                    else
                    row[4] = pajamuList.get(i).getKomentaras();
                    model.addRow(row);
                }
            }
        }catch (Exception ex) {        
            JOptionPane.showMessageDialog(this, "Netinkamai įvesta (arba neįvesta) data!" + "\n" + "Datos formatas: YYYY-MM-DD", "Klaida", JOptionPane.ERROR_MESSAGE);
        } 
    }      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        recordsName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Suma", "Kategorija", "Data", "Komentaras"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(0, 102, 0));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        recordsName.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        recordsName.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel1.setText("Paspaudus ant įrašo jį galima koreguoti arba ištrinti");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel2.setText("Data nuo:");

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel3.setText("Data iki:");

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Filtruoti");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jRadioButton1.setText("Visi įrašai");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jRadioButton3.setText("Pasirinkti laikotarpį");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 0));
        jButton2.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("TOP 3 išlaidos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 0));
        jButton3.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Atgal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("Įrašų rušis");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Išlaidos");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("Pajamos");
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(recordsName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(recordsName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)))))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        main.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        i = jTable1.getSelectedRow();
        model = jTable1.getModel();           
        if(table == 1){
            id = (Integer)model.getValueAt(i, 0);
            suma = model.getValueAt(i, 1).toString();
            kategorija = model.getValueAt(i, 2).toString();
            data = (Date)model.getValueAt(i, 3);
            komentaras = model.getValueAt(i, 4).toString();
            KoreguotiIšlaidas.jTextField1.setText(suma);
            KoreguotiIšlaidas.jComboBox1.setSelectedItem(kategorija);
            KoreguotiIšlaidas.jDateChooser1.setDate(data);
            KoreguotiIšlaidas.jTextField2.setText(komentaras);
            KoreguotiIšlaidas.ID = id;
            
            koreguotiIslaidas.setVisible(true);
            koreguotiIslaidas.setIrasai(this);
            this.setVisible(false);
        }        
        if(table == 2){
            id = (Integer)model.getValueAt(i, 0);
            suma = model.getValueAt(i, 1).toString();
            kategorija = model.getValueAt(i, 2).toString();
            data = (Date)model.getValueAt(i, 3);
            komentaras = model.getValueAt(i, 4).toString();
            KoreguotiPajamas.jTextField1.setText(suma);
            KoreguotiPajamas.jComboBox1.setSelectedItem(kategorija);
            KoreguotiPajamas.jDateChooser1.setDate(data);
            KoreguotiPajamas.jTextField2.setText(komentaras);
            KoreguotiPajamas.ID = id;
            
            koreguotiPajamas.setVisible(true);
            koreguotiPajamas.setIrasai(this);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            if(table == 1){
                islaiduFiltredTable(); 
            }
            if(table == 2){
                pajamuFiltredTable();
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton1.setSelected(true);
        jRadioButton3.setSelected(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jButton1.setEnabled(false);
        if(table == 1){
           islaiduTable();
        }
        if(table == 2){
            pajamuTable();
        }
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jRadioButton1.setSelected(false);
        jRadioButton3.setSelected(true);
        jLabel2.setEnabled(true);
        jLabel3.setEnabled(true);
        jDateChooser1.setEnabled(true);
        jDateChooser2.setEnabled(true);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        islaidosTOP.setVisible(true);
        islaidosTOP.setIrasai(this);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem3.setSelected(false);
        table = 1;
        islaiduTable();
        recordsName.setText("Išlaidų istorija");
        jRadioButton1.setSelected(true);
        jRadioButton3.setSelected(false);
        jButton2.setVisible(true);
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
        jCheckBoxMenuItem2.setSelected(false);
        jCheckBoxMenuItem3.setSelected(true);
        table = 2;
        pajamuTable();
        recordsName.setText("Pajamų istorija");
        jRadioButton1.setSelected(true);
        jRadioButton3.setSelected(false);
        jButton2.setVisible(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        jButton1.setEnabled(false);
    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed
  
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Irasai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Irasai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Irasai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Irasai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Irasai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel recordsName;
    // End of variables declaration//GEN-END:variables
}
