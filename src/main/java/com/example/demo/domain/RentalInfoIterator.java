package com.example.demo.domain;

import java.util.Iterator;
import java.util.List;

public class RentalInfoIterator implements Iterator {
    private List<RentalInfo> rentalInfoList;

    int index = 0;

    public RentalInfoIterator(List<RentalInfo> rentalInfoList) {
        this.rentalInfoList = rentalInfoList;
    }

    @Override
    public boolean hasNext() {
        return index < rentalInfoList.size();
    }

    @Override
    public  Object next() {
        if (hasNext()) {
            return rentalInfoList.get(index++);
        }
        return null;
    }
}
