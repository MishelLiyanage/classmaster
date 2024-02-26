/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classmaster.ui.Staff;

import classmaster.models.Course;
import classmaster.models.CourseAssignment;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.Student;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.CourseRepository;
import classmaster.repository.StudentRepository;
import classmaster.shared.CenterCellRenderer;
import classmaster.ui.component.customtablebutton.TableActionCellEditor;
import classmaster.ui.component.customtablebutton.TableActionCellRender;
import classmaster.ui.component.customtablebutton.TableActionEvent;
import classmaster.utils.Page;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Mishel Fernando
 */
public class AssignClass extends javax.swing.JFrame {

    /**
     * Creates new form AssignStudentsToClass
     */
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private List<Course> courses;
    private List<CourseAssignmentDto> studentCourses;
    private Page page;
    

    private TableActionEvent event = new TableActionEvent() {
        @Override
        public void onClick(int row) {
            if (tblCourses.isEditing()) {
                tblCourses.getCellEditor().stopCellEditing();
            }
            System.out.println("--- clicked row : ----- " + row);
            int deletedCourse = (int) tblCourses.getModel().getValueAt(row, 0);
            removeCourseAssignment(deletedCourse);

        }
    };

    public AssignClass(Page page) {
        this.page = page;
        initComponents();

        modifyTableConfigurations();

        Component courseComponent = ComponentRegistry.getInstance()
                .getComponent("CourseRepository");
        if (courseComponent instanceof CourseRepository) {
            this.courseRepository = (CourseRepository) courseComponent;
        }

        Component studentComponent = ComponentRegistry.getInstance().getComponent("StudentRepository");
        if (studentComponent instanceof StudentRepository) {
            studentRepository = (StudentRepository) studentComponent;
        }

        tblCourses.setVisible(true);
        cbClasses.setVisible(false);
        lblClasses.setVisible(false);
        btnCreate.setVisible(false);

    }

    private void loadAllCourses() throws SQLException {

        List<Course> unAssingedCourses = new ArrayList<>();
        for (Course cr : this.courses) {
            boolean assigned = false;
            for (CourseAssignmentDto cad : this.studentCourses) {
                if (cr.getId() == cad.getCourseId()) {
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                unAssingedCourses.add(cr);
            }
        }

        displayClassesComboBox(unAssingedCourses);

    }

    private void displayClassesComboBox(List<Course> courses) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbClasses.getModel();
        model.removeAllElements();
        for (Course crs : courses) {
            model.addElement(crs.getName());
        }
        cbClasses.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFieldStudentId = new javax.swing.JTextField();
        lblClasses = new javax.swing.JLabel();
        cbClasses = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Assign Students To Classes");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Assign Class");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Student ID");

        lblClasses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblClasses.setText("Class");

        cbClasses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCreate.setText("Assign");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        tblCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Id", "Class Name", "Joined Date", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCourses.setRowHeight(40);
        tblCourses.setSelectionBackground(new java.awt.Color(153, 204, 255));
        tblCourses.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCourses);
        if (tblCourses.getColumnModel().getColumnCount() > 0) {
            tblCourses.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(0, 0, 102));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(cbClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFieldStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCreate)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldStudentId)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbClasses)
                        .addComponent(btnCreate)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            // TODO add your handling code here:

            String selectedClassName = String.valueOf(cbClasses.getSelectedItem());
            Course selectedCourse = null;
            for (Course c : courses) {
                if (c.getName().equalsIgnoreCase(selectedClassName)) {
                    selectedCourse = c;
                    break;
                }
            }
            if (selectedCourse == null) {
                System.out.println("-- Course cannot find ---");
                return;
            }

            int studentId = Integer.parseInt(txtFieldStudentId.getText());

            Student st = studentRepository.getStudentById(studentId);
            if (st == null) {
                System.out.println("cannot find student for id " + studentId);
                return;
            }

            CourseAssignment assignment = new CourseAssignment();
            assignment.setClassId(selectedCourse.getId());
            assignment.setSudentId(st.getId());
            assignment.setComplete(false);
            assignment.setJoinedDate(LocalDate.now());

            int status = courseRepository.assignCourse(assignment);
            if (status == 1) {
                System.out.println("successfully assigned to a course");
                loadStudentCourse(studentId);
                loadAllCourses();
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignClass.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        try {

            int studentId = Integer.parseInt(txtFieldStudentId.getText());

            loadStudentCourse(studentId);

            if (studentCourses.size() > 0) {
                tblCourses.setVisible(true);
            }

            this.courses = this.courseRepository.getAllCourse();
            loadAllCourses();

            if (courses.size() > 0) {
                lblClasses.setVisible(true);
                cbClasses.setVisible(true);
                btnCreate.setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.page.onChildPageClose();
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    public void loadStudentCourse(int studentId) throws SQLException {
        studentCourses = this.courseRepository.getAllStudentCourses(studentId);
        DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
        model.setRowCount(0);

        for (CourseAssignmentDto dto : studentCourses) {
            model.addRow(new Object[]{dto.getCourseId(), dto.getCourseName(), dto.getJoinedDate().toString()});
        }
    }

    private void removeCourseAssignment(int deletedCourse) {
        int studentId = Integer.parseInt(txtFieldStudentId.getText());
        try {
            int status = this.courseRepository.removeStudentCourse(studentId, deletedCourse);
            if (status == 1) {
                System.out.println("Successfully removed course assignment");
                loadStudentCourse(studentId);
                loadAllCourses();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            java.util.logging.Logger.getLogger(AssignClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AssignClass().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbClasses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClasses;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTextField txtFieldStudentId;
    // End of variables declaration//GEN-END:variables

    private void modifyTableConfigurations() {

        JTableHeader th = tblCourses.getTableHeader();
        th.setFont(new Font("Serif", Font.BOLD, 15));

        CenterCellRenderer centerRenderer = new CenterCellRenderer();

        tblCourses.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCourses.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblCourses.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tblCourses.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender("./delete.png", event));
        tblCourses.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor("./delete.png", event));
    }
}
