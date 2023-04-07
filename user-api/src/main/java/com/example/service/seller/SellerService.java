package com.example.service.seller;


import com.example.domain.entity.Customer;
import com.example.domain.entity.Seller;
import com.example.domain.repository.SellerRepository;
import com.example.exception.seller.SellerNotExistException;
import com.example.exception.seller.SellerNotVerifiedException;
import com.example.exception.seller.SellerPasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller findByValidCustomer(String email, String password) {
        Seller seller = this.sellerRepository.findByEmail(email)
                                             .orElseThrow(() -> new SellerNotExistException("일치하는 판매자가 존재하지 않습니다."));


        if (!seller.isVerified()) {
            throw new SellerNotVerifiedException("해당 판매자는 인증이 이루어지지 않았습니다.");
        }

        if (!seller.getPassword().equals(password)) {
            throw new SellerPasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        return seller;
    }

    public Seller findByIdAndEmail(Long id, String email) {
        return this.sellerRepository.findByIdAndEmail(id, email)
                                    .orElseThrow(() -> new SellerNotExistException("일치하는 판매자가 존재하지 않습니다."));
    }
}
