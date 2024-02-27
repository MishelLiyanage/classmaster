package classmaster.ui.Staff;

import classmaster.models.Attendance;
import classmaster.models.Course;
import classmaster.models.CourseAssignmentDto;
import classmaster.models.Student;
import classmaster.models.StudentCourseAttendance;
import classmaster.repository.AttendanceRepository;
import classmaster.repository.Component;
import classmaster.repository.ComponentRegistry;
import classmaster.repository.CourseRepository;
import classmaster.repository.StudentRepository;
import classmaster.utils.Page;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class MarkAttendence extends javax.swing.JFrame {
    
    private Page page;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private List<CourseAssignmentDto> studentCourse;
    private AttendanceRepository attendanceRepository;
    private List<StudentCourseAttendance> studentAttendance;
    
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    
    public MarkAttendence(Page page) {
        initComponents();
        this.page = page;
        getContentPane().setBackground(new Color(0, 51, 102));
        tblClasses.fixTable(jScrollPane3);
        
        courseDatePanel.setVisible(false);
        tblPanel.setVisible(false);
        footerPanel.setVisible(false);
        btnCancel.setVisible(false);
        
        Component Component = ComponentRegistry.getInstance()
                .getComponent("AuthRepository");
        
        Component studentComponent = ComponentRegistry.getInstance().getComponent("StudentRepository");
        if (studentComponent instanceof StudentRepository) {
            studentRepository = (StudentRepository) studentComponent;
        }
        
        Component courseComponent = ComponentRegistry.getInstance()
                .getComponent("CourseRepository");
        if (courseComponent instanceof CourseRepository) {
            this.courseRepository = (CourseRepository) courseComponent;
        }
        
        Component attendanceComponent = ComponentRegistry.getInstance()
                .getComponent("AttendanceRepository");
        if (attendanceComponent instanceof AttendanceRepository) {
            this.attendanceRepository = (AttendanceRepository) attendanceComponent;
        }
        
        onSelect();
        
    }
    
    private void onSelect() {
        cbClasses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (cbClasses.getSelectedIndex() == -1) {
                    return;
                }
                modifyCourseInfo();

                getStudentAttendance(String.valueOf(cbClasses.getSelectedItem()));
            }
        });
    }
    
    private void modifyCourseInfo() {
        CourseAssignmentDto dto = studentCourse.get(cbClasses.getSelectedIndex());
        lblCourseInfo.setText("Every " + dto.getDay() + " Start at " + dto.getFromTime().format(dateTimeFormatter));
    }
    
    private void getStudentAttendance(String selectedCourse) {
        try {
            CourseAssignmentDto selectedCourseDto = getSelectedCourseDto(selectedCourse);
            int studentId = Integer.valueOf(txtFieldStudentId.getText());
            
            if (selectedCourseDto == null) {
                System.out.println("----- invalid course  ------");
                return;
            }
            
            studentAttendance = this.attendanceRepository
                    .findStudentCourseAttendance(studentId, selectedCourseDto.getCourseId());
            updateTable(studentAttendance);
            
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTable(List<StudentCourseAttendance> studentAttendance) {
        
        DefaultTableModel model = (DefaultTableModel) tblClasses.getModel();
        model.setRowCount(0);
        
        for (StudentCourseAttendance dto : studentAttendance) {
            model.addRow(new Object[]{dto.getAttendDate(), dto.getAttendTime(), dto.getCourseStartTime(),
                dto.getAttendTime().isAfter(dto.getCourseStartTime()) ? "Yes" : "No"
            });
        }
        
        if (!tblPanel.isVisible()) {
            tblPanel.setVisible(true);
        }
        
        footerPanel.setVisible(studentAttendance.size() > 0);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFieldStudentId = new javax.swing.JTextField();
        btnStuSearch = new javax.swing.JButton();
        courseDatePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbClasses = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        dateTimePicker = new com.github.lgooddatepicker.components.DateTimePicker();
        btnMark = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNameInfo = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        lblCourseInfo = new javax.swing.JLabel();
        btnCancel1 = new javax.swing.JButton();
        tblPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClasses = new classmaster.ui.component.darktable.TableDark();
        jLabel7 = new javax.swing.JLabel();
        footerPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClassMaster");
        setBackground(new java.awt.Color(0, 51, 153));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mark Attendence");

        jPanel4.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Id");

        txtFieldStudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldStudentIdActionPerformed(evt);
            }
        });

        btnStuSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnStuSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnStuSearch.setText("Search");
        btnStuSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStuSearchActionPerformed(evt);
            }
        });

        courseDatePanel.setBackground(new java.awt.Color(0, 51, 101));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Course");

        cbClasses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Date");

        btnMark.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMark.setText("Mark");
        btnMark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 51, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Info");

        jLabel6.setBackground(new java.awt.Color(0, 51, 102));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Name");

        lblNameInfo.setBackground(new java.awt.Color(0, 51, 102));
        lblNameInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblNameInfo.setText("Name");

        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblCourseInfo.setBackground(new java.awt.Color(0, 51, 102));
        lblCourseInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblCourseInfo.setText("Name");

        javax.swing.GroupLayout courseDatePanelLayout = new javax.swing.GroupLayout(courseDatePanel);
        courseDatePanel.setLayout(courseDatePanelLayout);
        courseDatePanelLayout.setHorizontalGroup(
            courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(courseDatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courseDatePanelLayout.createSequentialGroup()
                            .addComponent(btnCancel)
                            .addGap(18, 18, 18)
                            .addComponent(btnMark))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courseDatePanelLayout.createSequentialGroup()
                            .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCourseInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbClasses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dateTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        courseDatePanelLayout.setVerticalGroup(
            courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courseDatePanelLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblNameInfo))
                .addGap(18, 18, 18)
                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbClasses, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCourseInfo)
                .addGap(27, 27, 27)
                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(courseDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMark)
                    .addComponent(btnCancel))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        btnCancel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel1.setText("Back");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFieldStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnStuSearch))
                    .addComponent(courseDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStuSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(courseDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(btnCancel1)
                .addGap(15, 15, 15))
        );

        tblPanel.setBackground(new java.awt.Color(0, 51, 102));

        tblClasses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Time", "Class Started Time", "Late"
            }
        ));
        jScrollPane3.setViewportView(tblClasses);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Attendance History");

        javax.swing.GroupLayout tblPanelLayout = new javax.swing.GroupLayout(tblPanel);
        tblPanel.setLayout(tblPanelLayout);
        tblPanelLayout.setHorizontalGroup(
            tblPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(tblPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        tblPanelLayout.setVerticalGroup(
            tblPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tblPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        footerPanel.setBackground(new java.awt.Color(0, 51, 102));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerPanelLayout.createSequentialGroup()
                .addGroup(footerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnEdit))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(footerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tblPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(footerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFieldStudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldStudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldStudentIdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:

            if (tblClasses.getSelectedRow() < 0) {
                System.out.println("select a row before delete");
                return;
            }
            
            if (txtFieldStudentId.getText() == null || txtFieldStudentId.getText().isBlank()) {
                System.out.println("Student id is null or empty");
                return;
            }
            
            int studentId = Integer.valueOf(txtFieldStudentId.getText());
            
            String selectedCourse = String.valueOf(cbClasses.getSelectedItem());
            
            if (selectedCourse == null) {
                System.out.println("select a course before delete");
                return;
            }
            CourseAssignmentDto selectedCourseDto = getSelectedCourseDto(selectedCourse);
            
            String strDate = String.valueOf(tblClasses.getModel().getValueAt(tblClasses.getSelectedRow(), 0));
            LocalDate date = LocalDate.parse(strDate);
            
            System.out.println(" student id : " + studentId
                    + " selectd course " + selectedCourseDto.getCourseId()
                    + " date : " + date
            );
            
            int status = this.attendanceRepository.deleteAttendace(studentId, selectedCourseDto.getCourseId(), date);
            if (status == 1) {
                System.out.println("successfully deleted attendance");
                getStudentAttendance(selectedCourse);
                clean();
            }
            
        } catch (SQLException ex) {
            System.out.println("failed to delete attendance");
            ex.printStackTrace();
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnStuSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStuSearchActionPerformed
        try {
            // TODO add your handling code here:

            int studentId = Integer.valueOf(txtFieldStudentId.getText());
            
            Student st = studentRepository.getStudentById(studentId);
            if (st == null) {
                System.out.println("cannot find student for id " + studentId);
                return;
            }
            
            lblNameInfo.setText(st.getFirstName() + " " + st.getLastName());
            
            studentCourse = courseRepository.getAllStudentCourses(studentId);
            displayClassesComboBox(studentCourse);
            
            courseDatePanel.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MarkAttendence.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnStuSearchActionPerformed
    
    private CourseAssignmentDto getSelectedCourseDto(String selectedCourse) {
        CourseAssignmentDto selectedCourseDto = null;
        for (CourseAssignmentDto dto : studentCourse) {
            if (selectedCourse.equals(dto.getCourseName())) {
                selectedCourseDto = dto;
                break;
            }
        }
        
        return selectedCourseDto;
    }

    private void btnMarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkActionPerformed
        
        try {
            int studentId = Integer.parseInt(txtFieldStudentId.getText());
            
            String selectedCourse = String.valueOf(cbClasses.getSelectedItem());
            
            CourseAssignmentDto selectedCourseDto = getSelectedCourseDto(selectedCourse);
            
            if (selectedCourseDto == null) {
                System.out.println("----- invalid course  ------");
                return;
            }
            
            Attendance attendance = new Attendance();
            attendance.setStudentId(studentId);
            attendance.setClassId(selectedCourseDto.getCourseId());
            
            LocalDateTime dateTime = dateTimePicker.getDateTimePermissive();
            attendance.setAttendDate(dateTime.toLocalDate());
            attendance.setAttendTime(dateTime.toLocalTime());
            
            int status = this.attendanceRepository.markAttendance(attendance);
            
            System.out.println("successfully marked attendance : " + status);
            getStudentAttendance(selectedCourse);
            clean();

            // TODO add your handling code here:
        } catch (SQLException ex) {
            System.out.println("--- failed to mark attendance ----");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnMarkActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if (tblClasses.getSelectedRow() < 0) {
            System.out.println("select a row before delete");
            return;
        }
        
        String stStr = String.valueOf(tblClasses.getModel().getValueAt(tblClasses.getSelectedRow(), 0));
        LocalDate selectedRowLocalDate = LocalDate.parse(stStr);
        
        StudentCourseAttendance selectedAttendance = null;
        for (StudentCourseAttendance sca : studentAttendance) {
            if (sca.getAttendDate().isEqual(selectedRowLocalDate)) {
                selectedAttendance = sca;
                break;
            }
        }
        
        if (selectedAttendance == null) {
            System.out.println("-- selected attendance cannot found ---");
            return;
        }
        
        btnCancel.setVisible(true);
        tblClasses.setEnabled(false);
        cbClasses.setEnabled(false);
        txtFieldStudentId.setEnabled(false);
        
        dateTimePicker.setDateTimePermissive(selectedAttendance.getAttendDate().atTime(selectedAttendance.getAttendTime()));
        

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clean();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        this.page.onChildPageClose();
        this.dispose();

    }//GEN-LAST:event_btnCancel1ActionPerformed
    
    private void clean() {
        btnCancel.setVisible(false);
        tblClasses.setEnabled(true);
        tblClasses.clearSelection();
        cbClasses.setEnabled(true);
        txtFieldStudentId.setEnabled(true);
        dateTimePicker.clear();
    }
    
    private void displayClassesComboBox(List<CourseAssignmentDto> courses) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbClasses.getModel();
        model.removeAllElements();
        for (CourseAssignmentDto dto : courses) {
            model.addElement(dto.getCourseName());
        }
        cbClasses.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnMark;
    private javax.swing.JButton btnStuSearch;
    private javax.swing.JComboBox<String> cbClasses;
    private javax.swing.JPanel courseDatePanel;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCourseInfo;
    private javax.swing.JLabel lblNameInfo;
    private classmaster.ui.component.darktable.TableDark tblClasses;
    private javax.swing.JPanel tblPanel;
    private javax.swing.JTextField txtFieldStudentId;
    // End of variables declaration//GEN-END:variables

}
