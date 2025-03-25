package com.example.samplefundtransferservice.service;

import com.example.samplefundtransferservice.model.dto.FundTransferDTO;

import java.util.List;

public interface FundTransferService {

    FundTransferDTO getFundTransferById(Long id);
    List<FundTransferDTO> getFundTransfersByCustomerId(Long id);
    FundTransferDTO save(FundTransferDTO fundTransferDTO);
    void deleteFundTransfer(Long id);

}
