package com.example.demo.repository;

import com.example.demo.domain.ReservationInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservationInfoRepository {
    List<ReservationInfo> reservationInfoList = new ArrayList<>();
}
