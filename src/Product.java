public class Product implements Record{
    private String productID;
    private String productName; 
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    public Product(String productID, String productName, String manufacturerName, String 
            supplierName, int quantity, float price)
    {
        this.productID=productID;
        this.productName=productName;
        this.manufacturerName=manufacturerName;
        this.supplierName=supplierName;
        this.quantity=quantity;
        this.price=price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        if(quantity<0){
            System.out.println("Invalid Quantity. Please Enter a number greater or equal to 0");return;
        }
        this.quantity=quantity;
    }
    @Override
    public String lineRepresentation() {
        return productID+","+productName+","+manufacturerName+","+supplierName+","+quantity+","+price;
    }
    @Override
    public String getSearchKey() {
        return productID;
    }
}
