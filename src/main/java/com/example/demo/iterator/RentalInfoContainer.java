package com.example.demo.iterator;

import com.example.demo.domain.RentalInfo;
import com.example.demo.iterator.RentalInfoIterator;

import java.util.List;

public class RentalInfoContainer implements Container {
    private List<RentalInfo> rentalInfoList;
    public RentalInfoContainer(List<RentalInfo> rentalInfoList) {
        this.rentalInfoList = rentalInfoList;
    }

    public Iterator getIterator() {
        return new RentalInfoIterator(this.rentalInfoList);
    }
}
