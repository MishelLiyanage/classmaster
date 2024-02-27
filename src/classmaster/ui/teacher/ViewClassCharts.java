/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classmaster.ui.teacher;

import classmaster.models.CourseNoOfStudentsDto;
import classmaster.repository.AuthRepository;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.TeacherRepository;
import classmaster.utils.Page;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author bhagy
 */
public class ViewClassCharts extends javax.swing.JFrame {

    private TeacherRepository teacherRepository;
    private AuthRepository authRepository;
    private List<CourseNoOfStudentsDto> courseNoOfStudents;
    private Page page;

    public ViewClassCharts(Page page) {

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
        
        this.page = page;

        initComponents();
//        getContentPane().setBackground(new Color(30, 30, 30));
//        jTabbedPane1.setBackground(new Color(30, 30, 30));
//        tblClasses.fixTable(scrolpane);
        
        
        loadCourseNoOfStudents();
        displayAsChart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        scrolpane = new javax.swing.JScrollPane();
        tblClasses = new classmaster.ui.component.darktable.TableDark();
        jPanel4 = new javax.swing.JPanel();
        panelChart = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Counts");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classmaster/images/logo/teacher.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        scrolpane.setBackground(new java.awt.Color(255, 255, 255));
        scrolpane.setForeground(new java.awt.Color(255, 255, 255));

        tblClasses.setBackground(new java.awt.Color(51, 51, 51));
        tblClasses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class Id", "Name", "Student Count"
            }
        ));
        scrolpane.setViewportView(tblClasses);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrolpane, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrolpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Table", jPanel2);

        panelChart.setBackground(new java.awt.Color(255, 255, 255));
        panelChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chart", jPanel4);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.page.onChildPageClose();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void displayAsChart() {
        PieDataset dataset = createDataset(courseNoOfStudents);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, "Class student count");
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);

        panelChart.removeAll();
        panelChart.add(chartPanel, BorderLayout.CENTER);
        panelChart.validate();

    }

    private PieDataset createDataset(List<CourseNoOfStudentsDto> courseNoOfStudents) {
        DefaultPieDataset result = new DefaultPieDataset();

        int sum = 0;
        for (CourseNoOfStudentsDto dto : courseNoOfStudents) {
            sum += dto.getNoOfStudents();
        }

        for (CourseNoOfStudentsDto dto : courseNoOfStudents) {

            double percentage = ((double) dto.getNoOfStudents() / sum) * 100.0;
            System.out.println("key : " + dto.getCourseName() + " percentage : " + percentage);
            result.setValue(dto.getCourseName(), percentage);
        }

        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title, // chart title
                dataset, // data
                false, // include legend
                false,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }

    public void loadCourseNoOfStudents() {
        int teacherID = this.authRepository.getCurrentAccount().getId();


        try {
            courseNoOfStudents = this.teacherRepository.getAllCourseNoOfStudents(teacherID);
        } catch (SQLException ex) {
            Logger.getLogger(ViewClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) tblClasses.getModel();
        model.setRowCount(0);

        for (CourseNoOfStudentsDto dto : courseNoOfStudents) {
            System.out.println(dto);
            model.addRow(new Object[]{dto.getCourseID(), dto.getCourseName(), dto.getNoOfStudents(), dto.getDay()});
        }

        if (courseNoOfStudents.size() > 0) {
            scrolpane.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelChart;
    private javax.swing.JScrollPane scrolpane;
    private classmaster.ui.component.darktable.TableDark tblClasses;
    // End of variables declaration//GEN-END:variables
}
