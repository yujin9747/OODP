package com.example.demo.actionListener;

import com.example.demo.builder.builder.LoginWindowBuilder;
import com.example.demo.builder.concreteLoginBuilder.LoginWindowLoginBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowAdminBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowNullBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowUserBuilder;
import com.example.demo.builder.director.LoginWindowDirector;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Library;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.jframe.LoginWindow;
import com.example.demo.jframe.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginActionListener implements ActionListener {

    private LoginWindow loginWindow;
    private int loginOrRegister;
    public LoginActionListener(LoginWindow loginWindow, int loginOrRegister) {
        this.loginWindow = loginWindow;
        this.loginOrRegister = loginOrRegister;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        String id = loginWindow.getStudentIdField().getText();
        String password = loginWindow.getPasswordField().getText();
        if (command.equals("<")) {
            MainWindowNullBuilder builder = new MainWindowNullBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, null);
            director.constructMainWindow();
            loginWindow.setVisible(false);
        } else {
            if (command.equals("학생으로 Login") || command.equals("학생으로 Register")) {
                loginWindow.setLoginOrRegisterRole(Role.STUDENT);
            } else if (command.equals("관리자로 Login") || command.equals("관리자로 Register")) {
                loginWindow.setLoginOrRegisterRole(Role.ADMIN);
            }

            boolean isRegistered = false;

            if ((loginOrRegister == 0)) {
                System.out.println("-------- 로그인 시도 유저 정보 -------- Role : " + loginWindow.getLoginOrRegisterRole().toString());
            } else {
                System.out.println("-------- 회원가입 시도 유저 정보 -------- Role : " + loginWindow.getLoginOrRegisterRole().toString());
            }
            System.out.println("ID : " + id);
            System.out.println("Password : " + password);
            System.out.println();

            if ((command.equals("학생으로 Login") || command.equals("관리자로 Login")) && !checkExistMember(loginWindow.getLoginOrRegisterRole(), id, password)) {
                JOptionPane.showMessageDialog(null, "로그인 실패 : 회원가입된 유저가 아닙니다.");
                System.out.println("실패 - 회원가입 필요");
                loginWindow.getStudentIdField().setText("");
                loginWindow.getPasswordField().setText("");
            } else if ((command.equals("학생으로 Register") || command.equals("관리자로 Register"))) {
                if (checkExistMember(loginWindow.getLoginOrRegisterRole(), id, password)) {
                    JOptionPane.showMessageDialog(null, "이미 가입된 회원입니다.(해당 ID로 가입된 정보 존재)");
                    System.out.println("실패 - 이미 가입된 ID");
                    loginWindow.getStudentIdField().setText("");
                    loginWindow.getPasswordField().setText("");
                } else {
                    if (command.equals("학생으로 Register")) {
                        Student student = new Student.StudentBuilder()
                                .name("yujin")
                                .role(loginWindow.getLoginOrRegisterRole())
                                .pw(password)
                                .library(loginWindow.getLoginLibrary().get())
                                .sid(id)
                                .build();
                        //("yujin", loginOrRegisterRole, password, loginLibrary.get(), id);
                        loginWindow.getMemberService().saveMember(student);
                    } else if (command.equals("관리자로 Register")) {
                        Admin admin = new Admin.AdminBuilder()
                                .name("yujin")
                                .role(loginWindow.getLoginOrRegisterRole())
                                .pw(password)
                                .library(loginWindow.getLoginLibrary().get())
                                .adminId(id)
                                .weekTotalHours(0)
                                .build();
                        //Admin admin = new Admin("yujin", loginOrRegisterRole, password, loginLibrary.get(), id, 0);
                        loginWindow.getMemberService().saveMember(admin);
                    }
                    JOptionPane.showMessageDialog(null, "회원가입 성공");
                    System.out.println("성공 - 새로운 Id로 회원가입 완료");
                    LoginWindowBuilder loginWindowBuilder = new LoginWindowLoginBuilder();
                    LoginWindowDirector loginWindowDirector = new LoginWindowDirector(loginWindowBuilder);
                    loginWindowDirector.constructLoginWindow();
                    loginWindow.setVisible(false);
                }
            }
        }
    }

    private boolean checkExistMember(Role role, String id, String password){
        boolean isRegistered = false;

        if(role == Role.STUDENT){
            List<Student> studentList = loginWindow.getMemberService().findStudents();

            for(int i=0; i<studentList.size(); i++){
                System.out.println("------ Repository에 저장되어 있는 Student 유저 정보 -------");
                System.out.println("Student in Repository - ID : " + studentList.get(i).getStudentId());
                System.out.println("Student in Repository - Password : " + studentList.get(i).getPassword());
                Library library = studentList.get(i).getLibrary();
                System.out.println("Student in Repository - Library : " + loginWindow.getLibraryService().findOne(library.getId()));

                if(studentList.get(i).getStudentId().compareTo(id) == 0) {
                    isRegistered = true;

                    if(loginOrRegister == 0){
                        if (studentList.get(i).getPassword().equals(password)) {
                            MainWindowUserBuilder builder = new MainWindowUserBuilder();
                            MainWindowDirector director = new MainWindowDirector(builder, studentList.get(i));
                            director.constructMainWindow();
                            loginWindow.setVisible(false);

                            JOptionPane.showMessageDialog(null, "로그인 성공");
                            System.out.println("성공");
                        } else {
                            JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호를 다시 입력하세요.");
                            System.out.println("실패 - 비밀번호 틀림");
                            loginWindow.getStudentIdField().setText("");
                            loginWindow.getPasswordField().setText("");
                        }
                    }
                    break;
                }
            }
        }
        else if(role == Role.ADMIN){
            List<Admin> adminList = loginWindow.getMemberService().findAdmins();

            for(int i=0; i<adminList.size(); i++){
                System.out.println("------ Repository에 저장되어 있는 Admin 유저 정보 -------");
                System.out.println("Admin in Repository - ID : " + adminList.get(i).getAdminId());
                System.out.println("Admin in Repository - Password : " + adminList.get(i).getPassword());
                Library library = adminList.get(i).getLibrary();
                System.out.println("Admin in Repository - Library : " + loginWindow.getLibraryService().findOne(library.getId()));

                if(adminList.get(i).getAdminId().compareTo(id) == 0) {
                    isRegistered = true;

                    if(loginOrRegister == 0){
                        if (adminList.get(i).getPassword().equals(password)) {
                            MainWindowAdminBuilder builder = new MainWindowAdminBuilder();
                            MainWindowDirector director = new MainWindowDirector(builder, adminList.get(i));
                            director.constructMainWindow();
                            loginWindow.setVisible(false);

                            JOptionPane.showMessageDialog(null, "로그인 성공");
                            System.out.println("성공");
                        } else {
                            JOptionPane.showMessageDialog(null, "로그인 실패 : 비밀번호를 다시 입력하세요.");
                            System.out.println("실패 - 비밀번호 틀림");
                            loginWindow.getStudentIdField().setText("");
                            loginWindow.getPasswordField().setText("");
                        }
                    }
                    break;
                }
            }
        }

        return isRegistered;
    }
}
