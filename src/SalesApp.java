import java.util.Scanner;

public class SalesApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== SALES MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Add Product");
            System.out.println("4. View Products");
            System.out.println("5. Create Sale");
            System.out.println("6. View Sales");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> CustomerService.addCustomer();
                case 2 -> CustomerService.viewCustomers();
                case 3 -> ProductService.addProduct();
                case 4 -> ProductService.viewProducts();
                case 5 -> SalesService.createSale();
                case 6 -> SalesService.viewSales();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}
