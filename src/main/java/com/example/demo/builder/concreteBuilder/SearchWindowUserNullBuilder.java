package com.example.demo.builder.concreteBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;
import java.awt.*;

public class SearchWindowUserNullBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        super.buildBackButtonBuilder();
    }

    @Override
    public void buildFunctionButton() {}

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
