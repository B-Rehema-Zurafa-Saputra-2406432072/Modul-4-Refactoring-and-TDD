package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testValidVoucherCodePayment() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-1", "VOUCHER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testInvalidVoucherCodePayment() {
        paymentData.put("voucherCode", "ESHOP123ABC"); // Too short, not enough numbers
        Payment payment = new Payment("pay-2", "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testValidBankTransferPayment() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF999111");
        Payment payment = new Payment("pay-3", "BANK_TRANSFER", paymentData);
        assertEquals("SUCCESS", payment.getStatus()); // Assuming default success for valid bank data
    }

    @Test
    void testInvalidBankTransferPaymentEmptyData() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", null);
        Payment payment = new Payment("pay-4", "BANK_TRANSFER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
