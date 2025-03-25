package com.example.samplefundtransferservice.service.impl;

import com.example.samplefundtransferservice.exception.ActiveFundTransferLimitExceededException;
import com.example.samplefundtransferservice.exception.FundTransferNotFoundException;
import com.example.samplefundtransferservice.mapper.FundTransferMapper;
import com.example.samplefundtransferservice.model.dto.FundTransferDTO;
import com.example.samplefundtransferservice.model.entity.FundTransfer;
import com.example.samplefundtransferservice.repository.FundTransferRepository;
import com.example.samplefundtransferservice.service.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    private static final int MAX_ACTIVE_FUND_TRANSFER = 10;

    private final FundTransferRepository fundTransferRepository;


    @Autowired
    public FundTransferServiceImpl(FundTransferRepository fundTransferRepository) {
        this.fundTransferRepository = fundTransferRepository;
    }

    @Override
    public FundTransferDTO getFundTransferById(Long id) {
        FundTransfer fundTransfer = fundTransferRepository.findById(id).orElseThrow(
                () -> new FundTransferNotFoundException("id", id)
        );

        return FundTransferMapper.MAPPER.mapToFundTransferDTO(fundTransfer);
    }

    @Override
    public List<FundTransferDTO> getFundTransfersByCustomerId(Long id) {
        List<FundTransfer> fundTransferList = fundTransferRepository.findByCustomerId(id);
        return fundTransferList.stream().map(
                FundTransferMapper.MAPPER::mapToFundTransferDTO
        ).toList();
    }

    @Override
    public FundTransferDTO save(FundTransferDTO fundTransferDTO) {
        long customerId = fundTransferDTO.getCustomerId();
        List<FundTransfer> existingFundTransfers = fundTransferRepository.findByCustomerId(customerId);
        if (existingFundTransfers.size() >= MAX_ACTIVE_FUND_TRANSFER) {
            throw new ActiveFundTransferLimitExceededException(customerId);
        }
        FundTransfer newFundTransfer = FundTransferMapper.MAPPER.mapToFundTransfer(fundTransferDTO);
        fundTransferRepository.save(newFundTransfer);
        return FundTransferMapper.MAPPER.mapToFundTransferDTO(newFundTransfer);
    }

    @Override
    public void deleteFundTransfer(Long id) {
        FundTransfer fundTransfer = fundTransferRepository.findById(id)
                .orElseThrow(() -> new FundTransferNotFoundException("id", id));
        fundTransferRepository.delete(fundTransfer);
    }
}
