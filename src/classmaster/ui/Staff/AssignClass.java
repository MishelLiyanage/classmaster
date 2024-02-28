/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classmaster.ui.Staff;

import classmaster.models.Course;
import classmaster.models.CourseAssignment;
import classmaster.models.CourseAssignmentDto;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.CourseRepository;
import classmaster.repository.StudentRepository;
import classmaster.utils.Page;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    private List<Course> unAssingedCourses;

//    private TableActionEvent event = new TableActionEvent() {
//        @Override
//        public void onClick(int row) {
//            if (tblCourses.isEditing()) {
//                tblCourses.getCellEditor().stopCellEditing();
//            }
//            System.out.println("--- clicked row : ----- " + row);
//            int deletedCourse = (int) tblCourses.getModel().getValueAt(row, 0);
//            removeCourseAssignment(deletedCourse);
//
//        }
//    };
    public AssignClass(Page page) {
        this.page = page;
        initComponents();
        this.courses = new ArrayList<>();
        unAssingedCourses = new ArrayList<>();

//        modifyTableConfigurations();
        Component courseComponent = ComponentRegistry.getInstance()
                .getComponent("CourseRepository");
        if (courseComponent instanceof CourseRepository) {
            this.courseRepository = (CourseRepository) courseComponent;
        }

        Component studentComponent = ComponentRegistry.getInstance().getComponent("StudentRepository");
        if (studentComponent instanceof StudentRepository) {
            studentRepository = (StudentRepository) studentComponent;
        }
        init();

    }

    private void init() {
        btnDelete.setVisible(false);
        lblDelete.setVisible(false);
       
        try {
            this.courses = this.courseRepository.getAllCourse();
        } catch (SQLException ex) {
            System.out.println("Failed to load all courses");
            ex.printStackTrace();
        }
    }

    private void loadAllCourses() throws SQLException {

        unAssingedCourses = new ArrayList<>();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFieldStudentId = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblClasses = new javax.swing.JLabel();
        cbClasses = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblDelete = new javax.swing.JLabel();
        panelScrl = new javax.swing.JScrollPane();
        tblCourseAssignment = new classmaster.ui.component.darktable.TableDark();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Assign Students To Classes");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Assign Classes");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classmaster/images/logo/teacher.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(241, 254, 241));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Student Id");

        txtFieldStudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldStudentIdActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 153, 153));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblClasses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblClasses.setText("Class");

        cbClasses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCreate.setBackground(new java.awt.Color(0, 153, 153));
        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setText("Assign");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(153, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Assignment");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDelete.setText("Select the assignment from table : ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFieldStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearch)
                            .addComponent(btnCreate)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFieldStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClasses, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(cbClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDelete)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        tblCourseAssignment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Id", "Course Name", "Joined Date"
            }
        ));
        panelScrl.setViewportView(tblCourseAssignment);

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(0, 153, 153));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(panelScrl, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelScrl, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.page.onChildPageClose();
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            // TODO add your handling code here:
            String strStudentID = txtFieldStudentId.getText();
            if (strStudentID == null || strStudentID.isBlank()) {
                System.out.println(" -- pls addd a student id first --");
                JOptionPane.showMessageDialog(rootPane, "You musct search for a student first!");
                return;
            }

            int studentId = Integer.parseInt(strStudentID);

            int courseId = unAssingedCourses.get(cbClasses.getSelectedIndex()).getId();

            CourseAssignment courseAssignment = new CourseAssignment();
            courseAssignment.setClassId(courseId);
            courseAssignment.setSudentId(studentId);
            courseAssignment.setJoinedDate(LocalDate.now());
            courseAssignment.setComplete(false);

            this.courseRepository.assignCourse(courseAssignment);
            System.out.println("successfully assigned student to course");
            loadStudentCourse(studentId);
            loadAllCourses();

        } catch (SQLException ex) {
            System.out.println("failed to assign student to a class");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String strStudentID = txtFieldStudentId.getText();
        if (strStudentID == null || strStudentID.isBlank()) {
            System.out.println(" -- pls addd a student id first --");
            return;
        }
        try {
            // TODO add your handling code here:
            int studentId = Integer.valueOf(txtFieldStudentId.getText());
            loadStudentCourse(studentId);
        } catch (SQLException ex) {
            Logger.getLogger(AssignClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            // TODO add your handling code here:

            if (tblCourseAssignment.getSelectedRow() < 0) {
                System.out.println("-- please select a row before delete --");
                return;
            }

            btnSearch.setEnabled(false);
            txtFieldStudentId.setEnabled(false);
            btnCreate.setEnabled(false);
            cbClasses.setEnabled(false);

            String strStudentID = txtFieldStudentId.getText();
            if (strStudentID == null || strStudentID.isBlank()) {
                System.out.println(" -- pls addd a student id first --");
                return;
            }

            int studentId = Integer.valueOf(txtFieldStudentId.getText());
            CourseAssignmentDto dto = studentCourses.get(tblCourseAssignment.getSelectedRow());
            
            boolean alreadyPaid = hasAlreadyPaid(studentId, dto.getCourseId());
            if(alreadyPaid){
                JOptionPane.showMessageDialog(null, "This account has already paid for this course");
                return;
            }
            
            System.out.println("student id : " + strStudentID + " course id " + dto.getCourseId());
            this.courseRepository.removeStudentCourse(studentId, dto.getCourseId());

            System.out.println("successfully removed student course");

            btnSearch.setEnabled(true);
            txtFieldStudentId.setEnabled(true);
            btnCreate.setEnabled(true);
            cbClasses.setEnabled(true);
            tblCourseAssignment.clearSelection();

            loadStudentCourse(studentId);
            loadAllCourses();

        } catch (SQLException ex) {
            Logger.getLogger(AssignClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtFieldStudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldStudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldStudentIdActionPerformed

    public void loadStudentCourse(int studentId) throws SQLException {
        studentCourses = this.courseRepository.getAllStudentCourses(studentId);
        loadAllCourses();



        DefaultTableModel model = (DefaultTableModel) tblCourseAssignment.getModel();
        model.setRowCount(0);

        for (CourseAssignmentDto dto : studentCourses) {
            model.addRow(new Object[]{dto.getCourseId(), dto.getCourseName(), dto.getJoinedDate().toString()});
        }

        if (studentCourses.size() > 0) {
            btnDelete.setVisible(true);
            lblDelete.setVisible(true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbClasses;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblClasses;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JScrollPane panelScrl;
    private classmaster.ui.component.darktable.TableDark tblCourseAssignment;
    private javax.swing.JTextField txtFieldStudentId;
    // End of variables declaration//GEN-END:variables

    private boolean hasAlreadyPaid(int studentId, int courseId) throws SQLException {
        return this.courseRepository.hasStudentPaidForCourse(studentId, courseId);
    }


}
