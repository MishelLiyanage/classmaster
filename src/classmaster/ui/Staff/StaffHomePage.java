package classmaster.ui.Staff;

import classmaster.models.Account;
import classmaster.repository.AuthRepository;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.ui.ChangePassword;
import classmaster.utils.Page;

public class StaffHomePage extends javax.swing.JFrame implements Page {
    private AuthRepository authRepository;
    private Account currentUser;

    public StaffHomePage() {
        Component Component = ComponentRegistry.getInstance()
                .getComponent("AuthRepository");
        if (Component instanceof AuthRepository) {
            this.authRepository = (AuthRepository) Component;
        }
        
        this.currentUser = this.authRepository.getCurrentAccount();
        
        initComponents();
        
        this.lblGoodDay.setText("Good Day " + this.currentUser.getFirstName() + " " + this.currentUser.getLastName()+"!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        lblGoodDay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMnuItmAddStudent = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMnuItmMarkAttendence = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMnuItmStudentPayment = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home Page");
        setResizable(false);
        setSize(new java.awt.Dimension(1145, 765));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lblGoodDay.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblGoodDay.setForeground(new java.awt.Color(255, 255, 255));
        lblGoodDay.setText("jLabel1");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classmaster/images/logo/staffHome.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGoodDay, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblGoodDay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenu1.setText("Student");

        jMnuItmAddStudent.setText("Add Student");
        jMnuItmAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmAddStudentActionPerformed(evt);
            }
        });
        jMenu1.add(jMnuItmAddStudent);

        jMenuItem1.setText("Assign Student to Class");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Attendence");

        jMnuItmMarkAttendence.setText("Mark the Attendence");
        jMnuItmMarkAttendence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmMarkAttendenceActionPerformed(evt);
            }
        });
        jMenu2.add(jMnuItmMarkAttendence);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Payment");

        jMnuItmStudentPayment.setText("Student Payment");
        jMnuItmStudentPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnuItmStudentPaymentActionPerformed(evt);
            }
        });
        jMenu3.add(jMnuItmStudentPayment);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Edit");

        jMenuItem4.setText("Change Password");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnuItmAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmAddStudentActionPerformed
        new AddStudent(this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMnuItmAddStudentActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new AssignClass(this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMnuItmMarkAttendenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmMarkAttendenceActionPerformed
        new MarkAttendence(this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMnuItmMarkAttendenceActionPerformed

    private void jMnuItmStudentPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnuItmStudentPaymentActionPerformed
        new StudentPayment(this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMnuItmStudentPaymentActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new ChangePassword(this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(StaffHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMnuItmAddStudent;
    private javax.swing.JMenuItem jMnuItmMarkAttendence;
    private javax.swing.JMenuItem jMnuItmStudentPayment;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGoodDay;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChildPageClose() {
        this.setVisible(true);
    }
}
