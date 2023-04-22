package com.example.demo.jframe;

import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginWindow extends JFrame {

    static StudentRepository studentRepository = new StudentRepository();

    Button loginBTN;
    JTextField studentIdField = new JTextField("22000630", 20);
    JTextField passwordField = new JTextField("slsddbwls4421", 20);

    public LoginWindow() { //생성자를 만든다.

        setTitle("Login"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(studentIdField);
        c.add(passwordField);

        loginBTN = new Button("Login");
        loginBTN.setBounds(20, 5, 70, 30);



        loginBTN.addActionListener(new LoginActionListener());

        add(loginBTN);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기

    }

    private class LoginActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            int studentId = Integer.parseInt(studentIdField.getText());
            String password = passwordField.getText();

            boolean isRegistered = false;
            System.out.println("-------- 로그인 시도 유저 정보 --------");
            System.out.println("ID : " + studentId);
            System.out.println("Password : " + password);
            System.out.println();

            if(command.equals("Login")){
                List<Student> studentList = studentRepository.getStudentList();
                for(int i=0; i<studentList.size(); i++){
                    System.out.println("------ Repository에 저장되어 있는 유저 정보 -------");
                    System.out.println("Student in Repository - ID : " + studentList.get(i).getStudentId());
                    System.out.println("Student in Repository - Password : " + studentList.get(i).getPassword());

                    if(studentList.get(i).getStudentId() == studentId) {
                        isRegistered = true;

                        if (studentList.get(i).getPassword().equals(password)) {
                            //Todo : login 성공 팝업 띄우기 -> 확인 -> search 페이지로 이동
                            JOptionPane.showMessageDialog(null, "로그인 성공");
                            System.out.println("성공");
                        } else {
                            //Todo : login 실패(비밀번호 틀림) 팝업 띄우기 -> 확인 -> login 페이지 유지
                            JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호를 다시 입력하세요.");
                            System.out.println("실패 - 비밀번호 틀림");
                            studentIdField.setText("");
                            passwordField.setText("");
                        }
                        break;
                    }
                }
            }

            if(isRegistered == false){
                //Todo : login 실패(회원가입 필요) 팝업 띄우기 -> 확인 -> login 페이지 유지
                JOptionPane.showMessageDialog(null, "로그인 실패 : 회원가입된 유저가 아닙니다.");
                System.out.println("실패 - 회원가입 필요");
                studentIdField.setText("");
                passwordField.setText("");
            }
        }
    }


    public static void main(String[] args) {
        Student student = new Student(1L, "yujin", Role.STUDENT, "slsddbwls4421", 1L, 22000630);
        studentRepository.getStudentList().add(student);
        new LoginWindow(); //생성자 호출
    }
}

