package com.example.samplefundtransferservice.repository;

import com.example.samplefundtransferservice.TestConstants;
import com.example.samplefundtransferservice.exception.FundTransferNotFoundException;
import com.example.samplefundtransferservice.model.entity.FundTransfer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class FundTransferRepositoryTest {

    @Autowired
    FundTransferRepository fundTransferRepository;

    FundTransfer testTransfer;

    long testTransferId;

    @BeforeEach
    void setup() {
        testTransfer = FundTransfer.builder().customerId(TestConstants.TEST_CUSTOMER_ID)
                .fromAccount(TestConstants.TEST_FROM_ACCOUNT)
                .toAccount(TestConstants.TEST_TO_ACCOUNT)
                .amount(TestConstants.VALID_TEST_AMOUNT)
                .description(TestConstants.TEST_DESCRIPTION).build();
        FundTransfer saved = fundTransferRepository.save(testTransfer);
        testTransferId = saved.getId();
    }

    @AfterEach
    void teardown() {
        fundTransferRepository.delete(testTransfer);
    }


    @Test
    void get_tansfer_by_id_successful() {
        Optional<FundTransfer> transferOptional = fundTransferRepository.findById(testTransferId);
        Assertions.assertTrue(transferOptional.isPresent());
        FundTransfer transfer = transferOptional.get();
        Assertions.assertEquals(TestConstants.TEST_CUSTOMER_ID, transfer.getCustomerId());
        Assertions.assertEquals(TestConstants.TEST_FROM_ACCOUNT, transfer.getFromAccount());
        Assertions.assertEquals(TestConstants.TEST_TO_ACCOUNT, transfer.getToAccount());
        Assertions.assertEquals(TestConstants.TEST_DESCRIPTION, transfer.getDescription());
    }

    @Test
    void get_transfer_by_customer_id_successful() {
        List<FundTransfer> transfers = fundTransferRepository.findByCustomerId(TestConstants.TEST_CUSTOMER_ID);
        Assertions.assertFalse(transfers.isEmpty());
        Assertions.assertEquals(1, transfers.size());
    }

    @Test
    void save_transfer_successful() {
        long fromAcc = 345314L;
        long toAcc = 7654466L;
        double amount = 300.0;
        String desc2 = "Test transfer 2";
        FundTransfer newTransfer = FundTransfer.builder().customerId(TestConstants.TEST_CUSTOMER_ID)
                .fromAccount(fromAcc)
                .toAccount(toAcc)
                .amount(amount)
                .description(desc2).build();
        FundTransfer saved = fundTransferRepository.save(newTransfer);
        Assertions.assertNotNull(saved);
        Assertions.assertEquals(TestConstants.TEST_CUSTOMER_ID, saved.getCustomerId());
        Assertions.assertEquals(fromAcc, saved.getFromAccount());
        Assertions.assertEquals(toAcc, saved.getToAccount());
        Assertions.assertEquals(amount, saved.getAmount());
        Assertions.assertEquals(desc2, saved.getDescription());
    }

    @Test
    void delete_successful() {
        FundTransfer transfer = fundTransferRepository.findById(testTransferId)
                .orElseThrow(() -> new FundTransferNotFoundException("id", testTransferId));
        fundTransferRepository.delete(transfer);
        Optional<FundTransfer> transferOptional = fundTransferRepository.findById(testTransferId);
        Assertions.assertTrue(transferOptional.isEmpty());
    }

}
