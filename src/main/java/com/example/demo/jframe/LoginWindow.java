package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginWindow extends JFrame {

    private final MemberService memberService;

    Button BTN;
    JTextField studentIdField = new JTextField("22000630", 20);
    JTextField passwordField = new JTextField("slsddbwls4421", 20);
    int loginOrRegister;

    public LoginWindow(int loginOrRegister) { //생성자를 만든다.
        this.memberService = BeanUtil.get(MemberService.class);
        this.loginOrRegister = loginOrRegister;

        if ((loginOrRegister == 0)) {
            setTitle("Login");
        } else {
            setTitle("Register");
        }//창 제목
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(studentIdField);
        c.add(passwordField);

        BTN = (loginOrRegister == 0) ? new Button("Login") : new Button("Register");
        BTN.setBounds(20, 5, 70, 30);

        BTN.addActionListener(new ActionListener());

        add(BTN);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기

    }

    private class ActionListener implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            int studentId = Integer.parseInt(studentIdField.getText());
            String password = passwordField.getText();

            boolean isRegistered = false;
            if ((loginOrRegister == 0)) {
                System.out.println("-------- 로그인 시도 유저 정보 --------");
            } else {
                System.out.println("-------- 회원가입 시도 유저 정보 --------");
            }
            System.out.println("ID : " + studentId);
            System.out.println("Password : " + password);
            System.out.println();

            List<Student> studentList = memberService.findStudents();

            if(command.equals("Login")){
                if(!checkExistMember(studentList, studentId, password)){
                    JOptionPane.showMessageDialog(null, "로그인 실패 : 회원가입된 유저가 아닙니다.");
                    System.out.println("실패 - 회원가입 필요");
                    studentIdField.setText("");
                    passwordField.setText("");
                }
            }
            else if(command.equals("Register")){
                if(checkExistMember(studentList, studentId, password)){
                    JOptionPane.showMessageDialog(null, "이미 가입된 회원입니다.(학생 ID로 가입된 정보 존재)");
                    System.out.println("실패 - 이미 가입된 ID");
                    studentIdField.setText("");
                    passwordField.setText("");
                }
                else{
                    Student member = new Student("yujin", Role.STUDENT, password, 1L, studentId);
                    memberService.saveMember(member);
                    JOptionPane.showMessageDialog(null, "회원가입 성공");
                    System.out.println("성공 - 새로운 StudentId로 회원가입 완료");
                    new LoginWindow(0);
                    setVisible(false);
                }
            }
        }

        private boolean checkExistMember(List<Student> memberList, int studentId, String password){
            boolean isRegistered = false;

            for(int i=0; i<memberList.size(); i++){
                System.out.println("------ Repository에 저장되어 있는 유저 정보 -------");
                System.out.println("Student in Repository - ID : " + memberList.get(i).getStudentId());
                System.out.println("Student in Repository - Password : " + memberList.get(i).getPassword());
                System.out.println("Student in Repository - LibraryID : " + memberList.get(i).getLibraryId());

                if(memberList.get(i).getStudentId() == studentId) {
                    isRegistered = true;

                    if(loginOrRegister == 0){
                        if (memberList.get(i).getPassword().equals(password)) {
                            //new MainWindow(memberService, bookService, libraryService, rentalInfoService, reservationInfoService);
                            setVisible(false);

                            JOptionPane.showMessageDialog(null, "로그인 성공");
                            System.out.println("성공");
                        } else {
                            JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호를 다시 입력하세요.");
                            System.out.println("실패 - 비밀번호 틀림");
                            studentIdField.setText("");
                            passwordField.setText("");
                        }
                    }
                    break;
                }
            }

            return isRegistered;
        }
    }

}

