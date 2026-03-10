package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        boolean isValid = false;
        if ("VOUCHER".equals(method)) {
            isValid = validateVoucher(paymentData);
        } else if ("BANK_TRANSFER".equals(method)) {
            isValid = validateBankTransfer(paymentData);
        }

        this.status = isValid ? PaymentStatus.SUCCESS.getValue() : PaymentStatus.REJECTED.getValue();
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this(id, method, paymentData);
        this.order = order;
    }

    private boolean validateVoucher(Map<String, String> data) {
        String code = data.get("voucherCode");
        if (code == null || code.length() != 16 || !code.startsWith("ESHOP")) {
            return false;
        }
        long numCount = code.chars().filter(Character::isDigit).count();
        return numCount == 8;
    }

    private boolean validateBankTransfer(Map<String, String> data) {
        String bankName = data.get("bankName");
        String refCode = data.get("referenceCode");
        return bankName != null && !bankName.trim().isEmpty() &&
                refCode != null && !refCode.trim().isEmpty();
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }
}