package com.example.service.seller;


import com.example.domain.entity.Seller;
import com.example.domain.repository.SellerRepository;
import com.example.dto.SignUpForm;

import com.example.exception.common.ExceedVerificationExpiredAtException;
import com.example.exception.common.WrongVerificationCodeException;
import com.example.exception.seller.SellerAlreadyVerifiedException;
import com.example.exception.seller.SellerNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerSignUpService {

    private final SellerRepository sellerRepository;

    @Transactional
    public Seller signup(SignUpForm request) {
        return this.sellerRepository.save(
                Seller.toEntity(request)
        );
    }

    public boolean isEmailExist(String email) {
        return this.sellerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                                    .isPresent();
    }

    @Transactional
    public LocalDateTime changeSellerValidateEmail(Long sellerId, String verificationCode) {

        Seller seller = this.sellerRepository.findById(sellerId)
                                             .orElseThrow(() -> new SellerNotExistException("일치하는 판매자가 존재하지 않습니다."));

        if (seller.isVerified()) {
            throw new SellerAlreadyVerifiedException("해당 판매자는 이미 인증이 완료되었습니다.");
        }

        seller.setVerificationCode(verificationCode);
        seller.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
        return seller.getVerifyExpiredAt();
    }

    @Transactional
    public void verifyEmail(String email, String code) {

        Seller seller = this.sellerRepository.findByEmail(email)
                .orElseThrow(() -> new SellerNotExistException("일치하는 판매자는 존재하지 않습니다."));

        if(seller.isVerified()) {
            throw new SellerAlreadyVerifiedException("해당 판매자는 이미 인증이 완료되었습니다.");
        }

        if(!seller.getVerificationCode().equals(code)) {
            throw new WrongVerificationCodeException("잘못된 인증 시도입니다.");
        }

        if(seller.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExceedVerificationExpiredAtException("인증 시간이 만료되었습니다.");
        }

        seller.setVerified();
    }
}
