package com.example.demo.builder.concreteUserManageBuilder;

import com.example.demo.actionListener.UserManageActionListener;
import com.example.demo.builder.builder.UserManageWindowBuilder;

import java.awt.*;

public class UserManageWindowPermitBuilder extends UserManageWindowBuilder {
    @Override
    public void buildPermitOrBanButton(String description) {
        if(userManageWindow.getSelectedIdx() != null){
            userManageWindow.setExternalPermitBTN(new Button());
            Button externalPermitBTN = userManageWindow.getExternalPermitBTN();
            if(description.compareTo("< 외부 도서관 이용 허가된 학생 조회 >") == 0){
                externalPermitBTN.setLabel("허가 -> 불가 변경");
            }
            else if(description.compareTo("< 외부 도서관 이용 불가능한 학생 조회 >") == 0){
                externalPermitBTN.setLabel("불가 -> 허가 변경");
            }
            externalPermitBTN.setBounds(20, 5, 70, 30);
            externalPermitBTN.addActionListener(new UserManageActionListener(userManageWindow));
            userManageWindow.getButtonPanel().add(externalPermitBTN);
        }
    }
}
