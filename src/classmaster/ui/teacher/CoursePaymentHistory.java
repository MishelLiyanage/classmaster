/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classmaster.ui.teacher;

import classmaster.models.ClassPaidStudentsDto;
import classmaster.models.Course;
import classmaster.models.TeacherClassPaymentSummaryDto;
import classmaster.repository.AuthRepository;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.CourseRepository;
import classmaster.repository.CourseStudentPaymentRepository;
import classmaster.repository.TeacherRepository;
import classmaster.utils.Page;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Mishel Fernando
 */
public class CoursePaymentHistory extends javax.swing.JFrame {

    private Page page;
    private TeacherRepository teacherRepository;
    private AuthRepository authRepository;
    private List<Course> teacherCourses;
    private int teacherId;
    private CourseRepository courseRepository;
    private CourseStudentPaymentRepository paymentRepository;

    /**
     * Creates new form PaymentHistory
     */
    public CoursePaymentHistory(Page page) {
        this.page = page;

        Component teacherComponent = ComponentRegistry.getInstance()
                .getComponent("TeacherRepository");
        if (teacherComponent instanceof TeacherRepository) {
            this.teacherRepository = (TeacherRepository) teacherComponent;
        }

        Component Component = ComponentRegistry.getInstance()
                .getComponent("AuthRepository");
        if (Component instanceof AuthRepository) {
            this.authRepository = (AuthRepository) Component;
        }

        Component courseComponent = ComponentRegistry.getInstance()
                .getComponent("CourseRepository");
        if (courseComponent instanceof CourseRepository) {
            this.courseRepository = (CourseRepository) courseComponent;
        }

        Component paymentComponent = ComponentRegistry.getInstance()
                .getComponent("CourseStudentPaymentRepository");
        if (paymentComponent instanceof CourseStudentPaymentRepository) {
            this.paymentRepository = (CourseStudentPaymentRepository) paymentComponent;
        }

       teacherId = this.authRepository.getCurrentAccount().getId();
//         teacherId = 5;

        initComponents();
        loadStudentCourses();
    }

    private void loadStudentCourses() {

        try {
            teacherCourses = this.courseRepository.getTeacherCourses(teacherId);

            DefaultComboBoxModel model = (DefaultComboBoxModel) cbTchCourses.getModel();
            model.removeAllElements();
            for (Course crs : teacherCourses) {
                model.addElement(crs.getName());
            }
            cbTchCourses.setModel(model);

        } catch (SQLException ex) {
            System.out.println("failed load payment hostory ");
            ex.printStackTrace();
        }

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
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbTchCourses = new javax.swing.JComboBox<>();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        yearChooser = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStuPayment = new classmaster.ui.component.darktable.TableDark();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDark1 = new classmaster.ui.component.darktable.TableDark();
        panelCharts = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClassMaster");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Class Payment History");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Class");

        cbTchCourses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Year");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Month");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTchCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(218, 218, 218)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(yearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(216, 216, 216))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTchCourses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblStuPayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Student Id", "Name", "Paid", "Paid Date", "Paid Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStuPayment);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Payments", jPanel2);

        tableDark1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Summary", "Value"
            }
        ));
        jScrollPane2.setViewportView(tableDark1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Summary", jPanel3);

        panelCharts.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Charts", panelCharts);

        jButton2.setBackground(new java.awt.Color(0, 0, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.page.onChildPageClose();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            // TODO add your handling code here:

            int selectedCourseName = cbTchCourses.getSelectedIndex();
            int selectedCourseId = teacherCourses.get(selectedCourseName).getId();
            System.out.println(" index : " + selectedCourseName);
            System.out.println(teacherCourses.get(selectedCourseName).getName());

            int year = yearChooser.getYear();
            int month = monthChooser.getMonth() + 1;

            System.out.println("year : " + year);
            System.out.println("month : " + month);

            List<ClassPaidStudentsDto> paidStudents = this.paymentRepository.getClassStudentPayment(selectedCourseId, year, month);

            TeacherClassPaymentSummaryDto summary = this.paymentRepository.getTeacherClassPayment(selectedCourseId, year, month);

            updateTable(paidStudents);
            updateSummaryTable(summary);
            drawChart(summary);

        } catch (SQLException ex) {
            System.out.println("failed to get paid student for class");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void updateTable(List<ClassPaidStudentsDto> paidStudents) {

        DefaultTableModel model = (DefaultTableModel) tblStuPayment.getModel();
        model.setRowCount(0);

        for (ClassPaidStudentsDto dto : paidStudents) {
            String name = dto.getFirstName() + " " + dto.getLastName();

            String paidMsg = dto.getPaidDate() == null ? "Not Paid" : dto.getPaidDate().toString();

            model.addRow(new Object[]{
                dto.getStudentId(),
                name,
                dto.getPaid(),
                dto.getPaidAmount(),
                paidMsg
            });
        }
    }

    private void drawChart(TeacherClassPaymentSummaryDto summary) {
        CategoryDataset dataset = createDataset(summary.getTotalPaidStudents(), summary.getTotalStudents() - summary.getTotalPaidStudents());

        //Create chart  
        JFreeChart chart = ChartFactory.createBarChart(
                "Class Payment Chart", //Chart Title  
                "Type", // Category axis  
                "Student Class Fees Payment", // Value axis  
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel panel = new ChartPanel(chart);
        panelCharts.removeAll();
        panelCharts.add(panel, BorderLayout.CENTER);
        panelCharts.validate();

    }

    private CategoryDataset createDataset(int paidCount, int notPaidCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(paidCount, "", "Paid");
        dataset.addValue(notPaidCount, "", "Not Paid");

        return dataset;
    }

    private void updateSummaryTable(TeacherClassPaymentSummaryDto summary) {
        DefaultTableModel model = (DefaultTableModel) tableDark1.getModel();
        model.setRowCount(0);

        model.addRow(new Object[]{"Course Id", summary.getCourseId()});
        model.addRow(new Object[]{"Course Fee", summary.getCourseFee()});
        model.addRow(new Object[]{"Total Income", summary.getTotalIncome()});
        model.addRow(new Object[]{"Total Students", summary.getTotalStudents()});
        model.addRow(new Object[]{"Total Paid Student", summary.getTotalPaidStudents()});
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbTchCourses;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JPanel panelCharts;
    private classmaster.ui.component.darktable.TableDark tableDark1;
    private classmaster.ui.component.darktable.TableDark tblStuPayment;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}