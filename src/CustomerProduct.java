import java.time.LocalDate;

public class CustomerProduct implements Record {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    public static String reverseDate(String Date)
    {
        String[] tokens = Date.split("-");
        String revDate = tokens[2]+"-"+tokens[1]+"-"+tokens[0];
        return revDate;
    }
    @Override
    public String lineRepresentation() {
        return customerSSN+","+productID+","+reverseDate(purchaseDate.toString())+","+paid;
    }

    @Override
    public String getSearchKey() {
        return customerSSN+","+productID+","+reverseDate(purchaseDate.toString());
    }
}
