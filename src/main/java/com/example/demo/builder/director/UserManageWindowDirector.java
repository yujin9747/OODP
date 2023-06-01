package com.example.demo.builder.director;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.builder.builder.UserManageWindowBuilder;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.UserManageWindow;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.swing.*;

public class UserManageWindowDirector {

    private UserManageWindowBuilder userManageWindowBuilder;
    private final Member loginedMember;
    private final Integer selectedIdx;
    private final Member selectedMember;
    private DefaultListModel model;
    private String description;

    public UserManageWindowDirector(Member loginedMember, Integer selectedIdx, Member selectedMember) {
        this.loginedMember = loginedMember;
        this.selectedIdx = selectedIdx;
        this.selectedMember = selectedMember;
    }

    public void setBuilder(UserManageWindowBuilder builder){
        this.userManageWindowBuilder = builder;
    }

    public void setModel(DefaultListModel model){
        this.model = model;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public UserManageWindow getUserManageWindow(){
        return userManageWindowBuilder.getUserManageWindow();
    }

    public void constructUserManageWindow(){
        userManageWindowBuilder.createNewSearchWindowProduct();
        userManageWindowBuilder.buildLoginedMember(loginedMember);
        userManageWindowBuilder.buildSelectedIdx(selectedIdx);
        userManageWindowBuilder.buildSelectedMember(selectedMember);
        userManageWindowBuilder.buildDescription(description);
        userManageWindowBuilder.buildListModel(model);
        userManageWindowBuilder.buildLayoutSetting();
        userManageWindowBuilder.buildDefaultButton();
        userManageWindowBuilder.buildNorthPanelWithDesc(description);
        userManageWindowBuilder.buildPermitOrBanButton(description);
        userManageWindowBuilder.buildFinished();
    }
}
