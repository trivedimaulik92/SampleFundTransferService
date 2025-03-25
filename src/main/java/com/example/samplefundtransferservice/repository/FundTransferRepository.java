package com.example.samplefundtransferservice.repository;

import com.example.samplefundtransferservice.model.entity.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {

    List<FundTransfer> findByCustomerId(Long customerId);
}
