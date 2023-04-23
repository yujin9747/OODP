package com.example.demo.jframe;

import com.example.demo.domain.*;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminMangement extends JFrame {
    //static AdminMangement studentRepository = new AdminMangement();
    Button adminbutton;
    JTextField bookName = new JTextField("The wondering new world", 30);
    //JTextField passwordField = new JTextField("slsddbwls4421", 20);

    public AdminMangement() { //생성자

        setTitle("AdminMangement"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(bookName);
        //c.add(passwordField);

        adminbutton = new Button("Select");
        adminbutton.setBounds(20, 5, 70, 30);


        //adminbutton.addActionListener(new LoginWindow.LoginActionListener());

        add(adminbutton);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기

    }
//    private class LoginActionListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String command = e.getActionCommand();
//
//            int studentId = Integer.parseInt(studentIdField.getText());
//            String password = passwordField.getText();
//
//            boolean isRegistered = false;
//            System.out.println("-------- 로그인 시도 유저 정보 --------");
//            System.out.println("ID : " + studentId);
//            System.out.println("Password : " + password);
//            System.out.println();
//
//            if(command.equals("Login")){
//                List<Student> studentList = studentRepository.getStudentList();
//                for(int i=0; i<studentList.size(); i++){
//                    System.out.println("------ Repository에 저장되어 있는 유저 정보 -------");
//                    System.out.println("Student in Repository - ID : " + studentList.get(i).getStudentId());
//                    System.out.println("Student in Repository - Password : " + studentList.get(i).getPassword());
//                    System.out.println("Student in Repository - LibraryID : " + studentList.get(i).getLibraryId());
//
//                    if(studentList.get(i).getStudentId() == studentId) {
//                        isRegistered = true;
//
//                        if (studentList.get(i).getPassword().equals(password)) {
//                            //Todo : search 페이지로 이동
//                            JOptionPane.showMessageDialog(null, "로그인 성공");
//                            System.out.println("성공");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호를 다시 입력하세요.");
//                            System.out.println("실패 - 비밀번호 틀림");
//                            studentIdField.setText("");
//                            passwordField.setText("");
//                        }
//                        break;
//                    }
//                }
//            }
//
//            if(isRegistered == false){
//                JOptionPane.showMessageDialog(null, "로그인 실패 : 회원가입된 유저가 아닙니다.");
//                System.out.println("실패 - 회원가입 필요");
//                studentIdField.setText("");
//                passwordField.setText("");
//            }
//        }
//    }
    public static void main(String[] args) {
        Library library = new Library(1L, "Handong Global University Library", 200);
        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
        Student student = new Student(1L, "yujin", Role.STUDENT, "slsddbwls4421", library.getLibraryId(), 22000630);
        //studentRepository.getStudentList().add(student);
        new AdminMangement(); //생성자 호출
    }
}
