package com.example.demo.iterator;

import com.example.demo.domain.RentalInfo;

import java.util.List;

public class RentalInfoIterator implements Iterator {
    private List<RentalInfo> rentalInfoList;

    int index = 0;

    public RentalInfoIterator(List<RentalInfo> rentalInfoList) {
        this.rentalInfoList = rentalInfoList;
    }

    public void remove() {
        this.rentalInfoList.remove(index);
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
