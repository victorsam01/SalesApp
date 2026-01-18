import java.sql.*;
import java.util.Scanner;

public class CustomerService {

    public static void addCustomer() {
        try (Connection con = DBConnection.getConnection()) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Phone: ");
            String phone = sc.nextLine();

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO customers(customer_name, phone) VALUES (?, ?)");

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

            System.out.println("Customer added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewCustomers() {
        try (Connection con = DBConnection.getConnection()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customers");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("customer_id") + " | " +
                                rs.getString("customer_name") + " | " +
                                rs.getString("phone")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
