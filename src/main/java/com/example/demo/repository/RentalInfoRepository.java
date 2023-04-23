package com.example.demo.repository;

import com.example.demo.domain.RentalInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RentalInfoRepository {
    List<RentalInfo> rentalInfoList = new ArrayList<>();
}
