package lk.ijse.BackeryManagement.view.tm;

public class PaymentTm {
    private String paymentId;
    private String Date;
    private String Description;
    private Double Price;
    private String Id;

    public PaymentTm() {
    }

    public PaymentTm(String paymentId, String date, String description, Double price, String id) {
        this.paymentId = paymentId;
        Date = date;
        Description = description;
        Price = price;
        Id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
