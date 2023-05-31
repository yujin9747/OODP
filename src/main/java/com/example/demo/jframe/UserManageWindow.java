package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Member;
import com.example.demo.domain.Student;
import com.example.demo.service.MemberService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class UserManageWindow extends JFrame {

    private JList list;				//리스트
    private JScrollPane scrolled;
    private Button findStudentsBTN;   // 전체 학생 조회
    private Button findAdminsBTN;   // 전체 관리자 조회
    private Button findOverduedStudentsBTN;   // 전체 관리자 조회
    private Button findPermittedStudentsBTN;
    private Button findNotPermittedStudentsBTN;
    private Button externalPermitBTN;
    private Button backBTN;


    private MemberService memberService;


    private JList<Student> studentList;
    private Member loginedMember;
    private Integer selectedIdx;
    private Member selectedMember;
    private DefaultListModel model = new DefaultListModel<>();

    public UserManageWindow(DefaultListModel listModel, String description, Member loginedMember, Integer selectedIdx, Member selectedMember){
        this.memberService = BeanUtil.get(MemberService.class);
        this.memberService = BeanUtil.get(MemberService.class);
        this.loginedMember = loginedMember;
        this.selectedIdx = selectedIdx;
        this.selectedMember = selectedMember;
        this.model = listModel;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Container c = getContentPane();

        class ColoredListCellRenderer extends DefaultListCellRenderer{
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // 기본 스타일을 사용하기 위해 상위 클래스의 구현을 호출
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // 특정 인덱스에 따라 색상을 설정
                if (selectedIdx != null && index == selectedIdx) { // 인덱스 2에 색상을 적용
                    renderer.setBackground(Color.BLUE);
                    renderer.setForeground(Color.WHITE);
                } else {
                    renderer.setBackground(list.getBackground());
                    renderer.setForeground(list.getForeground());
                }

                return renderer;
            }
        }

        list = new JList<>(listModel);
        list.setCellRenderer(new ColoredListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        list.addListSelectionListener(e -> {
            String selectedUserName = list.getSelectedValue().toString();
            Student selectedMember1 = memberService.findByStudentId(selectedUserName).get(0);
            System.out.println("selectedMember : " + selectedMember1.getStudentId());

            new UserManageWindow(model, description, loginedMember, list.getSelectedIndex(), selectedMember1);
            setVisible(false);
        });

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

        if(selectedIdx != null){
            externalPermitBTN = new Button();
            if(description.compareTo("< 외부 도서관 이용 허가된 학생 조회 >") == 0){
                externalPermitBTN.setLabel("허가 -> 불가 변경");
            }
            else if(description.compareTo("< 외부 도서관 이용 불가능한 학생 조회 >") == 0){
                externalPermitBTN.setLabel("불가 -> 허가 변경");
            }
            externalPermitBTN.setBounds(20, 5, 70, 30);
            externalPermitBTN.addActionListener(new ActionListener());
            buttonPanel.add(externalPermitBTN);
        }

        JLabel desc = new JLabel(description);

        northPanel.add(buttonPanel);
        northPanel.add(desc);

        add(northPanel, "North");
        add(scrolled, "Center");
        setSize(1000, 600); //창 사이즈

        setVisible(true); //보이기
    }

    private class ActionListener implements java.awt.event.ActionListener{
        DefaultListModel model = new DefaultListModel<>();	//JList에 보이는 실제 데이터
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // 학생 리스트 전체 조회
            if(command.equals("학생 조회")){
                List<Student> students = memberService.findStudents();
                for (int i = 0; i < students.size(); i++) {
                    addItem(students.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 학생 전체 조회 결과 >", loginedMember, selectedIdx, selectedMember);
                setVisible(false);
            }
            else if(command.equals("관리자 조회")){
                List<Admin> admins = memberService.findAdmins();
                for (int i = 0; i < admins.size(); i++) {
                    addItem(admins.get(i).getAdminId());
                }
                new UserManageWindow(model, "< 관리자 전체 조회 결과 >", loginedMember, selectedIdx, selectedMember);
                setVisible(false);
            }
            else if(command.equals("연기된 학생 조회")){
                List<Student> overdueStudents = memberService.findOverdueStudents();
                for (int i = 0; i < overdueStudents.size(); i++) {
                    addItem(overdueStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 반납 연기된 학생 조회 결과 >", loginedMember, selectedIdx, selectedMember);
                setVisible(false);
            }
            else if(command.equals("외부 도서관 허가 학생 조회")){
                List<Student> permittedStudents = memberService.findPermittedStudents();
                for(int i=0; i<permittedStudents.size(); i++){
                    addItem(permittedStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 외부 도서관 이용 허가된 학생 조회 >", loginedMember, selectedIdx, selectedMember);
                setVisible(false);
            }
            else if(command.equals("외부 도서관 이용 불가 학생 조회")){
                List<Student> notPermittedStudents = memberService.findNotPermittedStudents();
                for(int i=0; i<notPermittedStudents.size(); i++){
                    addItem(notPermittedStudents.get(i).getStudentId());
                }
                new UserManageWindow(model, "< 외부 도서관 이용 불가능한 학생 조회 >", loginedMember, selectedIdx, selectedMember);
                setVisible(false);
            }
            else if(command.equals("불가 -> 허가 변경")){
                memberService.permitExternalLibrary(selectedMember.getId());
                new UserManageWindow(model, "< 외부 도서관 이용 불가능한 학생 조회 >", loginedMember, null, null);
                setVisible(false);
            }
            else if(command.equals("허가 -> 불가 변경")){
                memberService.banExternalLibrary(selectedMember.getId());
                new UserManageWindow(model, "< 외부 도서관 이용 허가된 학생 조회 >", loginedMember, null, null);
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
