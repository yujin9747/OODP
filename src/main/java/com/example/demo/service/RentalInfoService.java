package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalInfoService {

    private final RentalInfoRepository rentalInfoRepository;

    @Transactional
    public void saveRentalInfo(RentalInfo rentalInfo){
        rentalInfoRepository.save(rentalInfo);
    }
}
