package com.example.samplefundtransferservice.mapper;

import com.example.samplefundtransferservice.model.dto.FundTransferDTO;
import com.example.samplefundtransferservice.model.entity.FundTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FundTransferMapper {

    FundTransferMapper MAPPER = Mappers.getMapper(FundTransferMapper.class);

    FundTransferDTO mapToFundTransferDTO(FundTransfer fundTransfer);

    FundTransfer mapToFundTransfer(FundTransferDTO fundTransferDTO);
}
