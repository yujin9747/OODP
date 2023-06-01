package com.example.demo.actionListener;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.UserManageWindowBuilder;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteUserManageBuilder.UserManageWindowDefaultBuilder;
import com.example.demo.builder.concreteUserManageBuilder.UserManageWindowPermitBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.UserManageWindowDirector;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Student;
import com.example.demo.jframe.UserManageWindow;
import com.example.demo.service.MemberService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManageActionListener implements ActionListener {
    DefaultListModel model = new DefaultListModel<>();	//JList에 보이는 실제 데이터

    UserManageWindow userManageWindow;

    public UserManageActionListener(UserManageWindow userManageWindow){
        this.userManageWindow = userManageWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        MemberService memberService = BeanUtil.get(MemberService.class);
        UserManageWindowDirector director = new UserManageWindowDirector(userManageWindow.getLoginedMember(), userManageWindow.getSelectedIdx(), userManageWindow.getSelectedMember());

        // 학생 리스트 전체 조회
        if (command.equals("학생 조회")) {
            List<Student> students = memberService.findStudents();
            for (int i = 0; i < students.size(); i++) {
                addItem(students.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 학생 전체 조회 결과 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("관리자 조회")) {
            List<Admin> admins = memberService.findAdmins();
            for (int i = 0; i < admins.size(); i++) {
                addItem(admins.get(i).getAdminId());
            }
            director = settingDefaultDirector("< 관리자 전체 조회 결과 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("연기된 학생 조회")) {
            List<Student> overdueStudents = memberService.findOverdueStudents();
            for (int i = 0; i < overdueStudents.size(); i++) {
                addItem(overdueStudents.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 반납 연기된 학생 조회 결과 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("외부 도서관 허가 학생 조회")) {
            List<Student> permittedStudents = memberService.findPermittedStudents();
            for (int i = 0; i < permittedStudents.size(); i++) {
                addItem(permittedStudents.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 외부 도서관 이용 허가된 학생 조회 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("외부 도서관 이용 불가 학생 조회")) {
            List<Student> notPermittedStudents = memberService.findNotPermittedStudents();
            for (int i = 0; i < notPermittedStudents.size(); i++) {
                addItem(notPermittedStudents.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 외부 도서관 이용 불가능한 학생 조회 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("불가 -> 허가 변경")) {
            BeanUtil.get(MemberService.class).permitExternalLibrary(userManageWindow.getSelectedMember().getId());
            List<Student> notPermittedStudents = memberService.findNotPermittedStudents();
            for (int i = 0; i < notPermittedStudents.size(); i++) {
                addItem(notPermittedStudents.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 외부 도서관 이용 불가능한 학생 조회 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("허가 -> 불가 변경")) {
            BeanUtil.get(MemberService.class).banExternalLibrary(userManageWindow.getSelectedMember().getId());
            List<Student> permittedStudents = memberService.findPermittedStudents();
            for (int i = 0; i < permittedStudents.size(); i++) {
                addItem(permittedStudents.get(i).getStudentId());
            }
            director = settingDefaultDirector("< 외부 도서관 이용 허가된 학생 조회 >", director);
            director.constructUserManageWindow();
        } else if (command.equals("<")) {
            AdminManagementDefaultBuilder adminBuilder = new AdminManagementDefaultBuilder();
            AdminManagementWindowDirector adminDirector = new AdminManagementWindowDirector(adminBuilder, userManageWindow.getLoginedMember(), null, null);
            adminDirector.constructAdminManagementWindow();
        }
        userManageWindow.setVisible(false);
    }

    private void addItem(String studentId) {
        model.addElement(studentId);
    }

    private UserManageWindowDirector settingDefaultDirector(String description, UserManageWindowDirector director){
        UserManageWindowBuilder builder = new UserManageWindowDefaultBuilder();
        director.setDescription(description);
        director.setBuilder(builder);
        director.setModel(model);
        return director;
    }

    private UserManageWindowDirector settingPermitDirector(String description, UserManageWindowDirector director){
        UserManageWindowBuilder builder = new UserManageWindowPermitBuilder();
        director.setDescription(description);
        director.setBuilder(builder);
        director.setModel(model);
        return director;
    }
}
