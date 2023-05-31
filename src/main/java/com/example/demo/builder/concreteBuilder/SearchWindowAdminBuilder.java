package com.example.demo.builder.concreteBuilder;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

public class SearchWindowAdminBuilder extends SearchWindowBuilder {

    @Override
    public void buildDependencyInjection() {
        searchWindow.setMemberService(BeanUtil.get(MemberService.class));
        searchWindow.setBookService(BeanUtil.get(BookService.class));
        searchWindow.setRentalInfoService(BeanUtil.get(RentalInfoService.class));
        searchWindow.setReservationInfoService(BeanUtil.get(ReservationInfoService.class));
    }
}
