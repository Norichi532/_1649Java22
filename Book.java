package BookStore;

class Book {
    private String title;
    private String genre;
    private String author;
    private double price;
    private int quantity;

    public Book(String title, String genre, String author, double price, int quantity) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }
    public String getTitle() {
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Title: " + title +", Genre: " + genre + ", Author: " + author + ", Price: " + price + ", Quantity: " + quantity;
    }
}
