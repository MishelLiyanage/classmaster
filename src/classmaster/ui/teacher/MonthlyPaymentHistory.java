/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classmaster.ui.teacher;

import classmaster.models.TeacherClassPaymentSummaryDto;
import classmaster.repository.AuthRepository;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.CourseStudentPaymentRepository;
import classmaster.utils.Page;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author bhagy
 */
public class MonthlyPaymentHistory extends javax.swing.JFrame {

    private Page page;
    private CourseStudentPaymentRepository paymentRepository;
    private AuthRepository authRepository;
    private int teacherId;
    private List<TeacherClassPaymentSummaryDto> summary;
    private List<TeacherClassPaymentSummaryDto> annualSummary;

    /**
     * Creates new form MonthlyPaymentHistory
     */
    public MonthlyPaymentHistory(Page page) {
        initComponents();
        this.page = page;

        Component paymentComponent = ComponentRegistry.getInstance()
                .getComponent("CourseStudentPaymentRepository");
        if (paymentComponent instanceof CourseStudentPaymentRepository) {
            this.paymentRepository = (CourseStudentPaymentRepository) paymentComponent;
        }

        Component Component = ComponentRegistry.getInstance()
                .getComponent("AuthRepository");
        if (Component instanceof AuthRepository) {
            this.authRepository = (AuthRepository) Component;
        }

        summary = new ArrayList<>();
        annualSummary = new ArrayList<>();

//        teacherId = this.authRepository.getCurrentAccount().getId();
        teacherId = 5;
        cbChartType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadChart();
            }
        });
        
                cbChartAnnual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAnnualChart();
            }
        });

    }

    public void loadSummary() throws SQLException {

        int year = yearChooser.getYear();
        int month = monthChooser.getMonth() + 1;

        summary = this.paymentRepository.getTeacherMonthlyPaymentSummary(teacherId, year, month);
        updateTable(summary);
        loadChart();

    }

    public void loadAnnalSummary() throws SQLException {
        int year = yearChooser.getYear();
        annualSummary = this.paymentRepository.getTeacherAnnualPaymentSummary(teacherId, year);
        loadAnnualChart();

    }

    private void updateTable(List<TeacherClassPaymentSummaryDto> summary) {

        DefaultTableModel model = (DefaultTableModel) tblMoPaySummary.getModel();
        model.setRowCount(0);

        for (TeacherClassPaymentSummaryDto dto : summary) {
            model.addRow(new Object[]{
                dto.getCourseName(),
                dto.getTotalIncome(),
                dto.getTotalStudents(),
                dto.getTotalPaidStudents()
            });
        }
    }

    public void loadChart() {

        if (summary.size() == 0) {
            System.out.println("No data to show");
            return;
        }

        if (String.valueOf(cbChartType.getSelectedItem()).equalsIgnoreCase("salary")) {
            CategoryDataset dataset = createSalaryDataset();

            JFreeChart chart = ChartFactory.createBarChart(
                    "Income for Month : " + Month.of(monthChooser.getMonth() + 1).name(), //Chart Title  
                    "Month", // Category axis  
                    "Income", // Value axis  
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
            );

            drawChart(dataset, chart, panelCharts);
        } else if (String.valueOf(cbChartType.getSelectedItem()).equalsIgnoreCase("student count")) {
            CategoryDataset dataset = createStudentCount();

            JFreeChart chart = ChartFactory.createBarChart(
                    "Total Students for Month : " + Month.of(monthChooser.getMonth() + 1).name(), //Chart Title  
                    "Month", // Category axis  
                    "Total Students", // Value axis  
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
            );

            drawChart(dataset, chart, panelCharts);
        }

    }

    private void loadAnnualChart() {

        if (annualSummary.size() == 0) {
            System.out.println("No annual data to show");
            return;
        }

        if (String.valueOf(cbChartAnnual.getSelectedItem()).equalsIgnoreCase("salary")) {
            CategoryDataset dataset = createAnnualCourseIncomeDataSet();

            JFreeChart chart = ChartFactory.createLineChart(
                    "Income SUmmary for " + yearChooser.getYear(), //Chart Title  
                    "Year", // Category axis  
                    "Total Income", // Value axis  
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
            );
            drawChart(dataset, chart, panelAnnualReport);
        } else if (String.valueOf(cbChartAnnual.getSelectedItem()).equalsIgnoreCase("student count")) {
            CategoryDataset dataset = createAnnualCourseStudentCountDataSet();

            JFreeChart chart = ChartFactory.createLineChart(
                    "Total Paid Students for Year " + yearChooser.getYear(), //Chart Title  
                    "Year", // Category axis  
                    "Total Paid Students", // Value axis  
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
            );

            drawChart(dataset, chart, panelAnnualReport);
        }
    }

    private void drawChart(CategoryDataset dataset, JFreeChart chart, javax.swing.JPanel displayPanel) {
        ChartPanel panel = new ChartPanel(chart);

        displayPanel.removeAll();
        displayPanel.add(panel, BorderLayout.CENTER);
        displayPanel.validate();

//        panelCharts.removeAll();
//        panelCharts.add(panel, BorderLayout.CENTER);
//        panelCharts.validate();
    }

    private CategoryDataset createSalaryDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (TeacherClassPaymentSummaryDto dto : summary) {
            dataset.addValue(dto.getTotalIncome(), "", dto.getCourseName());
        }

        return dataset;
    }

    private CategoryDataset createStudentCount() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (TeacherClassPaymentSummaryDto dto : summary) {
            dataset.addValue(dto.getTotalStudents(), "", dto.getCourseName());
        }

        return dataset;
    }

    private DefaultCategoryDataset createAnnualCourseIncomeDataSet() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TeacherClassPaymentSummaryDto dto : annualSummary) {
            if(dto.getMonth() > 0){
                 dataset.addValue(dto.getTotalIncome(), dto.getCourseName(),
                    Month.of(dto.getMonth()).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            }
           
        }
        return dataset;
    }

    private DefaultCategoryDataset createAnnualCourseStudentCountDataSet() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TeacherClassPaymentSummaryDto dto : annualSummary) {
            dataset.addValue(dto.getTotalStudents(), dto.getCourseName(),
                    Month.of(dto.getMonth()).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        }
        return dataset;
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
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        yearChooser = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMoPaySummary = new classmaster.ui.component.darktable.TableDark();
        jPanel2 = new javax.swing.JPanel();
        panelChartMenu = new javax.swing.JPanel();
        cbChartType = new javax.swing.JComboBox<>();
        panelCharts = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelChartMenu1 = new javax.swing.JPanel();
        cbChartAnnual = new javax.swing.JComboBox<>();
        panelAnnualReport = new javax.swing.JPanel();
        btnSearch1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Monthly Payment History");

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
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(monthChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(625, 625, 625))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(yearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(147, 147, 147)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(yearChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tblMoPaySummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Course Name", "Total Income", "Total Students", "Paid Students"
            }
        ));
        jScrollPane2.setViewportView(tblMoPaySummary);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Summary", jPanel3);

        cbChartType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salary", "Student Count" }));

        javax.swing.GroupLayout panelChartMenuLayout = new javax.swing.GroupLayout(panelChartMenu);
        panelChartMenu.setLayout(panelChartMenuLayout);
        panelChartMenuLayout.setHorizontalGroup(
            panelChartMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChartMenuLayout.createSequentialGroup()
                .addContainerGap(772, Short.MAX_VALUE)
                .addComponent(cbChartType, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panelChartMenuLayout.setVerticalGroup(
            panelChartMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartMenuLayout.createSequentialGroup()
                .addComponent(cbChartType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelCharts.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChartMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCharts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panelChartMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCharts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 376, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Charts", jPanel2);

        cbChartAnnual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salary", "Student Count" }));

        javax.swing.GroupLayout panelChartMenu1Layout = new javax.swing.GroupLayout(panelChartMenu1);
        panelChartMenu1.setLayout(panelChartMenu1Layout);
        panelChartMenu1Layout.setHorizontalGroup(
            panelChartMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChartMenu1Layout.createSequentialGroup()
                .addContainerGap(772, Short.MAX_VALUE)
                .addComponent(cbChartAnnual, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panelChartMenu1Layout.setVerticalGroup(
            panelChartMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartMenu1Layout.createSequentialGroup()
                .addComponent(cbChartAnnual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelAnnualReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChartMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelAnnualReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(panelChartMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelAnnualReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Annual Report", jPanel4);

        btnSearch1.setBackground(new java.awt.Color(0, 0, 102));
        btnSearch1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSearch1.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch1.setText("Back");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            // TODO add your handling code here:
            loadSummary();
            loadAnnalSummary();

        } catch (SQLException ex) {
            System.out.println("failed to get paid student for class");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
        this.page.onChildPageClose();
        this.dispose();
    }//GEN-LAST:event_btnSearch1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JComboBox<String> cbChartAnnual;
    private javax.swing.JComboBox<String> cbChartType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JPanel panelAnnualReport;
    private javax.swing.JPanel panelChartMenu;
    private javax.swing.JPanel panelChartMenu1;
    private javax.swing.JPanel panelCharts;
    private classmaster.ui.component.darktable.TableDark tblMoPaySummary;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
}
