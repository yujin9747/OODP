package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserManageWindow extends JFrame {

    private JList list;				//리스트
    private JScrollPane scrolled;
    private Button findStudentsBTN;   // 전체 학생 조회
    private Button findAdminsBTN;   // 전체 관리자 조회
    private Button findOverduedStudentsBTN;   // 전체 관리자 조회


    private MemberService memberService;


    private JList<Student> studentList;

    public UserManageWindow(DefaultListModel listModel, String description){
        this.memberService = BeanUtil.get(MemberService.class);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Container c = getContentPane();

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        scrolled=new JScrollPane(list);
        scrolled.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        scrolled.setSize(600, 500);

        c.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        findStudentsBTN = new Button("학생 조회");
        findStudentsBTN.setBounds(20, 5, 70, 30);
        findStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findStudentsBTN);

        findAdminsBTN = new Button("관리자 조회");
        findAdminsBTN.setBounds(20, 5, 70, 30);
        findAdminsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findAdminsBTN);

        findOverduedStudentsBTN = new Button("반납 연기된 학생 조회");
        findOverduedStudentsBTN.setBounds(20, 5, 70, 30);
        findOverduedStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findOverduedStudentsBTN);

        JLabel desc = new JLabel(description);

        northPanel.add(buttonPanel);
        northPanel.add(desc);

        add(northPanel, "North");
        add(scrolled, "Center");
        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기
    }

    private class ActionListener implements java.awt.event.ActionListener{
        private DefaultListModel model = new DefaultListModel<>();	//JList에 보이는 실제 데이터
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // 학생 리스트 전체 조회
            if(command.equals("학생 조회")){
                List<Student> students = memberService.findStudents();
                for (int i = 0; i < students.size(); i++) {
                    addItem(students.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 학생 전체 조회 결과 >");
                setVisible(false);
            }
            else if(command.equals("관리자 조회")){
                List<Admin> admins = memberService.findAdmins();
                for (int i = 0; i < admins.size(); i++) {
                    addItem(admins.get(i).getAdminId());
                }
                new UserManageWindow(model, "< 관리자 전체 조회 결과 >");
                setVisible(false);
            }
            else if(command.equals("반납 연기된 학생 조회")){
                List<Student> overdueStudents = memberService.findOverdueStudents();
                for (int i = 0; i < overdueStudents.size(); i++) {
                    addItem(overdueStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 반납 연기된 학생 조회 결과 >");
                setVisible(false);
            }
        }

        public void addItem(String studentId) {
            model.addElement(studentId);
        }


    }
}
