package com.example.demo.builder.director;

import com.example.demo.builder.builder.LoginWindowBuilder;
import com.example.demo.domain.Library;
import com.example.demo.jframe.LoginWindow;

public class LoginWindowDirector {
    private final LoginWindowBuilder loginWindowBuilder;
    public LoginWindowDirector(LoginWindowBuilder loginWindowBuilder) {
        this.loginWindowBuilder = loginWindowBuilder;
    }
    public LoginWindow getLoginWindow() { return loginWindowBuilder.getLoginWindow();}

    public void constructLoginWindow(){
        loginWindowBuilder.createNewLoginWindowProduct();
        loginWindowBuilder.buildLoginLibrary();
        loginWindowBuilder.buildWindowTitle();
        loginWindowBuilder.buildWindowDefaultSetting();
        loginWindowBuilder.buildContainer();
        loginWindowBuilder.buildBackButton();
        loginWindowBuilder.buildJTextField();
        loginWindowBuilder.buildFunctionButton();
        loginWindowBuilder.buildFinished();
    }
}
