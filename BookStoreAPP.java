package BookStore;

import java.util.Scanner;

public class BookStoreAPP {
    public static void main(String[] args) {
        BookListADT<Book> books = new BookListADT<>();
        OrderQueueADT<Order> orders = new OrderQueueADT<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Book Management Application");
        System.out.println("----------------------------");
        int choice = 0;

        // Mockup data
        books.add(new Book( "Doraemon", "Manga", "Fujiko Fujio", 24.99, 20));
        books.add(new Book("Weathering with you","Light novel","Shikaimakoto",43.00,50));
        books.add(new Book( "Conan", "Manga", "Fujiko Fujio", 23.99, 40));
        books.add(new Book( "5cm/s","Light novel","Shikaimakoto",45.00,30));


        orders.offer(new Order(books.get(0), 2, "Alixe", "Nguyen Van Linh, Da Nang"));
        orders.offer(new Order( books.get(2), 1, "Davic", "Le Hong Phong, Ho Chi Minh"));
        orders.offer(new Order( books.get(1), 3, "Max", "Nguyen Van Linh, Da Nang"));

        while (choice !=9){
            displayMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Add a new book");
                    addBook(books, scanner);
                    break;
                case 2:
                    System.out.println("Displaying all books");
                    displayBooks(books);
                    break;
                case 3:
                    System.out.println("Searching for a book");
                    searchBook(books, scanner);
                    break;
                case 4:
                    System.out.println("Sort books");
                    sortBook(books, scanner);
                    break;
                case 5:
                    System.out.println("Place order");
                    placeOrder(books,orders,scanner);
                    break;
                case 6:
                    System.out.println("Display order");
                    displayOrder(orders);
                    break;
                case 7:
                    System.out.println("Search order");
                    searchOrder(orders,scanner);
                    break;
                case 8:
                    System.out.println("Process order");
                    processOrder(orders);
                    break;
                case 9:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1. Add a book");
        System.out.println("2. Display all books");
        System.out.println("3. Search for a book");
        System.out.println("4. Sort books");
        System.out.println("5. Place order");
        System.out.println("6. Display order");
        System.out.println("7. Search order");
        System.out.println("8. Process order");
        System.out.println("9. Exit");
        System.out.print("Please enter a choice: ");
    }


    private static void addBook(BookListADT<Book> books, Scanner scanner){
        System.out.print("Please enter the book's title: ");
        String title = scanner.nextLine();
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return;
        }

        System.out.print("Please enter the book's genre: ");
        String genre = scanner.nextLine();

        System.out.print("Please enter the book's author: ");
        String author = scanner.nextLine();

        System.out.print("Please enter the book's price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Please enter the book's quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        books.add(new Book(title, genre, author, price, quantity));

    }

    private static void displayBooks(BookListADT<Book> books){
        System.out.println("Books in the library:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println( "\t" + (i + 1) + ": " + book.toString());
        }
    }

    private static void searchBook(BookListADT<Book> books, Scanner scanner) {
        System.out.print("Enter the name, genre, or author of the book to search: ");
        String searchTerm = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                    book.getGenre().toLowerCase().contains(searchTerm) ||
                    book.getAuthor().toLowerCase().contains(searchTerm)) {
                System.out.println("\t" + (i + 1) + ": " + book.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with the given search term.");
        }
    }

    private static void sortBook(BookListADT<Book> books, Scanner scanner) {
        System.out.println("Select sorting criteria:");
        System.out.println("1. By Title");
        System.out.println("2. By Genre");
        System.out.println("3. By Author");
        System.out.println("4. By Price");
        System.out.print("Enter your choice: ");

        Integer choice = null;
        while (choice == null) {
            try {
                String choiceInput = scanner.nextLine();
                if (choiceInput == null || choiceInput.trim().isEmpty()) {
                    System.out.print("Invalid choice. Please enter a valid option:");
                    continue;
                }
                choice = Integer.parseInt(choiceInput);
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please enter a valid option.");
                    choice = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        switch (choice) {
            case 1:
                books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
                break;
            case 2:
                books.sort((b1, b2) -> b1.getGenre().compareToIgnoreCase(b2.getGenre()));
                break;
            case 3:
                books.sort((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()));
                break;
            case 4:
                books.sort((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));
                break;
            default:
                System.out.println("Invalid choice. Sorting by Name.");
                books.sort((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
                break;
        }
        System.out.println("Books sorted successfully.");
        displayBooks(books);
    }

    private static void placeOrder(BookListADT<Book> books, OrderQueueADT<Order> orders, Scanner scanner) {
        System.out.print("Enter the title of the book to order: ");
        String bookTitle = scanner.nextLine();

        // Find the book in the book list
        Book bookToOrder = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(bookTitle)) {
                bookToOrder = books.get(i);
                break;
            }
        }

        if (bookToOrder == null) {
            System.out.println("Book not found. Please check the title and try again.");
            return;
        }

        System.out.print("Enter the quantity to order: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (bookToOrder.getQuantity() < quantity) {
            System.out.println("Not enough stock available for this book. Available quantity: " + bookToOrder.getQuantity());
            return;
        }


        System.out.print("Enter the customer's name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter the customer's address: ");
        String customerAddress = scanner.nextLine();

        // Create the order and add it to the order queue
        Order newOrder = new Order(bookToOrder, quantity, customerName, customerAddress);
        orders.offer(newOrder);
        bookToOrder.setQuantity(bookToOrder.getQuantity() - quantity);
        System.out.println("Order placed successfully!");
    }

    private static void displayOrder(OrderQueueADT<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("List of Orders:");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-7s | %-20s | %-30s%n", "Order ID", "Book Title", "Quantity", "Customer Name", "Customer Address");
        System.out.println("----------------------------------------------------------------------------------------------");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.poll();
            System.out.printf("%-12s | %-20s | %-7d | %-20s | %-30s%n",
                    order.getOrderId(), order.getBookTitle(), order.getQuantity(), order.getCustomerName(), order.getShippingAddress());
            orders.offer(order); // Add the order back to the queue after displaying
        }
    }

    private static void searchOrder(OrderQueueADT<Order> orders, Scanner scanner) {
        System.out.println("Select search criteria:");
        System.out.println("1. Order ID");
        System.out.println("2. Customer Name");
        System.out.println("3. Shipping Address");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                searchOrderByOrderId(orders, scanner);
                break;
            case 2:
                searchOrderByCustomerName(orders, scanner);
                break;
            case 3:
                searchOrderByShippingAddress(orders, scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void searchOrderByOrderId(OrderQueueADT<Order> orders, Scanner scanner) {
        System.out.print("Enter the Order ID to search: ");
        String orderId = scanner.nextLine();
        searchAndDisplayOrders(orders, "Order ID", orderId);
    }

    private static void searchOrderByCustomerName(OrderQueueADT<Order> orders, Scanner scanner) {
        System.out.print("Enter the Customer Name to search: ");
        String customerName = scanner.nextLine();
        searchAndDisplayOrders(orders, "Customer Name", customerName);
    }

    private static void searchOrderByShippingAddress(OrderQueueADT<Order> orders, Scanner scanner) {
        System.out.print("Enter the Shipping Address to search: ");
        String shippingAddress = scanner.nextLine();
        searchAndDisplayOrders(orders, "Shipping Address", shippingAddress);
    }

    private static void searchAndDisplayOrders(OrderQueueADT<Order> orders, String searchType, String searchValue) {
        boolean found = false;

        System.out.println("Orders found:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s | %-7s | %-20s | %-30s%n", "Book Title", "Quantity", "Customer Name", "Customer Address");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.poll();
            String valueToCheck = "";

            switch (searchType) {
                case "Order ID":
                    valueToCheck = order.getOrderId();
                    break;
                case "Customer Name":
                    valueToCheck = order.getCustomerName();
                    break;
                case "Shipping Address":
                    valueToCheck = order.getShippingAddress();
                    break;
            }

            if (valueToCheck.equalsIgnoreCase(searchValue)) {
                found = true;
                System.out.printf("%-20s | %-7d | %-20s | %-30s%n", order.getBookTitle(), order.getQuantity(), order.getCustomerName(), order.getShippingAddress());
            }

            orders.offer(order); // Add the order back to the queue
        }

        if (!found) {
            System.out.println("No orders found for the provided " + searchType + ".");
        }
    }

    private static void processOrder(OrderQueueADT<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }

        Order processedOrder = orders.poll(); // Retrieve and remove the first order from the queue

        System.out.println("Order processed and removed from the queue:");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-12s | %-20s | %-7s | %-20s | %-30s%n", "Order ID", "Book Title", "Quantity", "Customer Name", "Customer Address");
        System.out.println("-------------------------------------------------");

        System.out.printf("%-12s | %-20s | %-7d | %-20s | %-30s%n", processedOrder.getOrderId(), processedOrder.getBookTitle(), processedOrder.getQuantity(), processedOrder.getCustomerName(), processedOrder.getShippingAddress());
    }
    
}
