import java.sql.*;
import java.util.Scanner;

public class ProductService {

    public static void addProduct() {
        try (Connection con = DBConnection.getConnection()) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Product Name: ");
            String name = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO products(product_name, price) VALUES (?, ?)");

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();

            System.out.println("Product added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewProducts() {
        try (Connection con = DBConnection.getConnection()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("product_id") + " | " +
                                rs.getString("product_name") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
