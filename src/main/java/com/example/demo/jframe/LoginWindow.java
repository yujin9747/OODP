package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.*;
import com.example.demo.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;

public class LoginWindow extends JFrame {

    private final MemberService memberService;
    private final LibraryService libraryService;
    private final BookService bookService;

    private final RentalInfoService rentalInfoService;

    private final ReservationInfoService reservationInfoService;


    Button studentBTN;
    Button adminBTN;
    Button backBTN;
    JTextField studentIdField = new JTextField("22000630", 20);
    JTextField passwordField = new JTextField("slsddbwls4421", 20);
    int loginOrRegister;
    Role loginOrRegisterRole;

    Optional<Library> loginLibrary;   // login하려는 도서관 -> 한동대학교로 상정하고 개발함

    public LoginWindow(int loginOrRegister) { //생성자를 만든다.
        this.memberService = BeanUtil.get(MemberService.class);
        this.libraryService = BeanUtil.get(LibraryService.class);
        this.bookService = BeanUtil.get(BookService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
        this.reservationInfoService = BeanUtil.get(ReservationInfoService.class);

        loginLibrary = libraryService.findOne(1L);

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

        studentBTN = (loginOrRegister == 0) ? new Button("학생으로 Login") : new Button("학생으로 Register");
        studentBTN.setBounds(20, 5, 70, 30);

        adminBTN = (loginOrRegister == 0) ? new Button("관리자로 Login") : new Button("관리자로 Register");
        adminBTN.setBounds(20, 5, 70, 30);

        backBTN = new Button("<");

        studentBTN.addActionListener(new ActionListener());
        adminBTN.addActionListener(new ActionListener());
        backBTN.addActionListener(new ActionListener());

        add(backBTN);
        add(studentIdField);
        add(passwordField);
        add(studentBTN);
        add(adminBTN);


        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기

    }

    private class ActionListener implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            String id = studentIdField.getText();
            String password = passwordField.getText();

            if(command.equals("학생으로 Login") || command.equals("학생으로 Register")) {
                loginOrRegisterRole = Role.STUDENT;
            }
            else if(command.equals("관리자로 Login") || command.equals("관리자로 Register")){
                loginOrRegisterRole = Role.ADMIN;
            }
            else if(command.equals("<")){
                new MainWindow(null);
                setVisible(false);
            }

            boolean isRegistered = false;
            if ((loginOrRegister == 0)) {
                System.out.println("-------- 로그인 시도 유저 정보 -------- Role : " + loginOrRegisterRole.toString() );
            } else {
                System.out.println("-------- 회원가입 시도 유저 정보 -------- Role : " + loginOrRegisterRole.toString());
            }
            System.out.println("ID : " + id);
            System.out.println("Password : " + password);
            System.out.println();

            if((command.equals("학생으로 Login") || command.equals("관리자로 Login")) && !checkExistMember(loginOrRegisterRole, id, password)){
                JOptionPane.showMessageDialog(null, "로그인 실패 : 회원가입된 유저가 아닙니다.");
                System.out.println("실패 - 회원가입 필요");
                studentIdField.setText("");
                passwordField.setText("");
            }
            else if((command.equals("학생으로 Register") || command.equals("관리자로 Register"))){
                if(checkExistMember(loginOrRegisterRole, id, password)){
                    JOptionPane.showMessageDialog(null, "이미 가입된 회원입니다.(해당 ID로 가입된 정보 존재)");
                    System.out.println("실패 - 이미 가입된 ID");
                    studentIdField.setText("");
                    passwordField.setText("");
                }
                else{
                    if(command.equals("학생으로 Register")) {
                        Student student = new Student("yujin", loginOrRegisterRole, password, loginLibrary.get(), id);
                        memberService.saveMember(student);
                    }
                    else if(command.equals("관리자로 Register")){
                        Admin admin = new Admin.AdminBuilder().name("yujin").role(loginOrRegisterRole).pw(password).library(loginLibrary.get()).adminId(id).weekTotalHours(0).build();
                        //Admin admin = new Admin("yujin", loginOrRegisterRole, password, loginLibrary.get(), id, 0);
                        memberService.saveMember(admin);
                    }
                    JOptionPane.showMessageDialog(null, "회원가입 성공");
                    System.out.println("성공 - 새로운 Id로 회원가입 완료");
                    new LoginWindow(0);
                    setVisible(false);
                }
            }
        }

        private boolean checkExistMember(Role role, String id, String password){
            boolean isRegistered = false;

            if(role == Role.STUDENT){
                List<Student> studentList = memberService.findStudents();

                for(int i=0; i<studentList.size(); i++){
                System.out.println("------ Repository에 저장되어 있는 Student 유저 정보 -------");
                System.out.println("Student in Repository - ID : " + studentList.get(i).getStudentId());
                System.out.println("Student in Repository - Password : " + studentList.get(i).getPassword());
                    Library library = studentList.get(i).getLibrary();
                    System.out.println("Student in Repository - Library : " + libraryService.findOne(library.getId()));

                    if(studentList.get(i).getStudentId().compareTo(id) == 0) {
                        isRegistered = true;

                        if(loginOrRegister == 0){
                            if (studentList.get(i).getPassword().equals(password)) {
                                new MainWindow(studentList.get(i));
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
            }
            else if(role == Role.ADMIN){
                List<Admin> adminList = memberService.findAdmins();

                for(int i=0; i<adminList.size(); i++){
                    System.out.println("------ Repository에 저장되어 있는 Admin 유저 정보 -------");
                    System.out.println("Admin in Repository - ID : " + adminList.get(i).getAdminId());
                    System.out.println("Admin in Repository - Password : " + adminList.get(i).getPassword());
                    Library library = adminList.get(i).getLibrary();
                    System.out.println("Admin in Repository - Library : " + libraryService.findOne(library.getId()));

                    if(adminList.get(i).getAdminId().compareTo(id) == 0) {
                        isRegistered = true;

                        if(loginOrRegister == 0){
                            if (adminList.get(i).getPassword().equals(password)) {
                                new MainWindow(adminList.get(i));
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
            }

            return isRegistered;
        }
    }

}

