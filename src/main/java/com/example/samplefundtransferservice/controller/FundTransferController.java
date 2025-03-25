package com.example.samplefundtransferservice.controller;

import com.example.samplefundtransferservice.mapper.FundTransferMapper;
import com.example.samplefundtransferservice.model.dto.FundTransferDTO;
import com.example.samplefundtransferservice.service.FundTransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fund_transfer")
public class FundTransferController {

    private FundTransferService fundTransferService;

    @Autowired
    public FundTransferController(FundTransferService fundTransferService) {
        this.fundTransferService = fundTransferService;
    }

    @PostMapping
    public ResponseEntity<FundTransferDTO> createFundTransfer(@Valid @RequestBody FundTransferDTO fundTransferDTO) {
        FundTransferDTO savedFundTransfer = fundTransferService.save(fundTransferDTO);
        return new ResponseEntity<>(savedFundTransfer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<FundTransferDTO> getFundTransferById(@PathVariable("id") Long id) {
        FundTransferDTO fundTransfer = fundTransferService.getFundTransferById(id);
        return new ResponseEntity<>(fundTransfer, HttpStatus.OK);
    }

    @GetMapping("customer/{customer_id}")
    public ResponseEntity<List<FundTransferDTO>> getFundTransferByCustomerId(@PathVariable("customer_id") Long customerId) {
        List<FundTransferDTO> fundTransfers = fundTransferService.getFundTransfersByCustomerId(customerId);
        return new ResponseEntity<>(fundTransfers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        fundTransferService.deleteFundTransfer(id);
        return new ResponseEntity<>("Fund transfer deleted successfully.", HttpStatus.OK);
    }

}
