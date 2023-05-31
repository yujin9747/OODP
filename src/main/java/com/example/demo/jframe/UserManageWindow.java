package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Member;
import com.example.demo.domain.Student;
import com.example.demo.service.MemberService;

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
    private Button findPermittedStudentsBTN;
    private Button findNotPermittedStudentsBTN;
    private Button backBTN;


    private MemberService memberService;


    private JList<Student> studentList;
    private Member loginedMember;
    private int selectedIdx;

    public UserManageWindow(DefaultListModel listModel, String description, Member loginedMember, Integer selectedIdx){
        this.memberService = BeanUtil.get(MemberService.class);
        this.loginedMember = loginedMember;
        this.selectedIdx = selectedIdx;
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
        backBTN = new Button("<");
        backBTN.setBounds(20, 5, 70, 30);
        backBTN.addActionListener(new ActionListener());
        buttonPanel.add(backBTN);

        findStudentsBTN = new Button("학생 조회");
        findStudentsBTN.setBounds(20, 5, 70, 30);
        findStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findStudentsBTN);

        findAdminsBTN = new Button("관리자 조회");
        findAdminsBTN.setBounds(20, 5, 70, 30);
        findAdminsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findAdminsBTN);

        findOverduedStudentsBTN = new Button("연기된 학생 조회");
        findOverduedStudentsBTN.setBounds(20, 5, 70, 30);
        findOverduedStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findOverduedStudentsBTN);

        findPermittedStudentsBTN = new Button("외부 도서관 허가 학생 조회");
        findPermittedStudentsBTN.setBounds(20, 5, 70, 30);
        findPermittedStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findPermittedStudentsBTN);

        findNotPermittedStudentsBTN = new Button("외부 도서관 이용 불가 학생 조회");
        findNotPermittedStudentsBTN.setBounds(20, 5, 70, 30);
        findNotPermittedStudentsBTN.addActionListener(new ActionListener());
        buttonPanel.add(findNotPermittedStudentsBTN);

        JLabel desc = new JLabel(description);

        northPanel.add(buttonPanel);
        northPanel.add(desc);

        add(northPanel, "North");
        add(scrolled, "Center");
        setSize(900, 600); //창 사이즈

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
                new UserManageWindow(model, "< 학생 전체 조회 결과 >", loginedMember, null);
                setVisible(false);
            }
            else if(command.equals("관리자 조회")){
                List<Admin> admins = memberService.findAdmins();
                for (int i = 0; i < admins.size(); i++) {
                    addItem(admins.get(i).getAdminId());
                }
                new UserManageWindow(model, "< 관리자 전체 조회 결과 >", loginedMember, null);
                setVisible(false);
            }
            else if(command.equals("연기된 학생 조회")){
                List<Student> overdueStudents = memberService.findOverdueStudents();
                for (int i = 0; i < overdueStudents.size(); i++) {
                    addItem(overdueStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 반납 연기된 학생 조회 결과 >", loginedMember, null);
                setVisible(false);
            }
            else if(command.equals("외부 도서관 허가 학생 조회")){
                List<Student> permittedStudents = memberService.findPermittedStudents();
                for(int i=0; i<permittedStudents.size(); i++){
                    addItem(permittedStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 외부 도서관 이용 허가된 학생 조회 >", loginedMember, null);
                setVisible(false);
            }
            else if(command.equals("외부 도서관 이용 불가 학생 조회")){
                List<Student> notPermittedStudents = memberService.findNotPermittedStudents();
                for(int i=0; i<notPermittedStudents.size(); i++){
                    addItem(notPermittedStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 외부 도서관 이용 불가능한 학생 조회 >", loginedMember, null);
                setVisible(false);
            }
            else if(command.equals("<")){
                new AdminManagement(loginedMember, null, null);
                setVisible(false);
            }
        }

        public void addItem(String studentId) {
            model.addElement(studentId);
        }


    }
}
