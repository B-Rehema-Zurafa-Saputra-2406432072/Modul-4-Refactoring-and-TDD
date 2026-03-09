package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment(method, paymentData) ? "SUCCESS" : "REJECTED";
    }

    private boolean validatePayment(String method, Map<String, String> data) {
        if ("VOUCHER".equals(method)) {
            String code = data.get("voucherCode");
            if (code == null || code.length() != 16 || !code.startsWith("ESHOP")) {
                return false;
            }
            // Count numerical characters
            long numCount = code.chars().filter(Character::isDigit).count();
            return numCount == 8;
        }
        else if ("BANK_TRANSFER".equals(method)) {
            String bankName = data.get("bankName");
            String refCode = data.get("referenceCode");
            return bankName != null && !bankName.trim().isEmpty() &&
                    refCode != null && !refCode.trim().isEmpty();
        }
        return false;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}