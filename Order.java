package BookStore;

public class Order {
    private String orderId;
    private Book booktitle;
    private int quantity;
    private String customerName;
    private String shippingAddress;

    private static int orderCount = 1;
    public Order(Book booktitle,int quantity,String customerName,String shippingAddress){
        this.booktitle = booktitle;
        this.quantity = quantity;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        updateBookQuantity();
        createOrderId();
    }

    private void createOrderId(){
        orderId = "O" + orderCount++;
    }
    private void updateBookQuantity(){
        if (booktitle == null){
            System.out.println("Book object is not initialized.");
            return;
        }
        if (quantity <= 0){
            System.out.println("Invalid quantity.");
            return;
        }
    }

    public String getOrderId(){

        return orderId;
    }
    public String getBookTitle(){
        return booktitle.getTitle();
    }

    public int getQuantity() {

        return quantity;
    }

    public String getCustomerName() {

        return customerName;
    }

    public String getShippingAddress() {

        return shippingAddress;
    }
    @Override
    public String toString(){
        return String.format("OrederID: ," + this.orderId + "Book Title: ," + this.booktitle + "Quantity: ," + this.quantity + "Customer name: ," + this.customerName + "Shiping address: ." + this.shippingAddress);
    }
}
