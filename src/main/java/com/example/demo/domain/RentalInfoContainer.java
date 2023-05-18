package com.example.demo.domain;

import java.util.List;
import java.util.Iterator;

public class RentalInfoContainer {
    private List<RentalInfo> rentalInfoList;
    public RentalInfoContainer(List<RentalInfo> rentalInfoList) {
        this.rentalInfoList = rentalInfoList;
    }

    public Iterator getIterator() {
        return new RentalInfoIterator(this.rentalInfoList);
    }
}
