import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class SalesService {

    public static void createSale() {
        try (Connection con = DBConnection.getConnection()) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Customer ID: ");
            int customerId = sc.nextInt();

            System.out.print("Product ID: ");
            int productId = sc.nextInt();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();

            CallableStatement cs =
                    con.prepareCall("{ call create_sale(?, ?, ?) }");

            cs.setInt(1, customerId);
            cs.setInt(2, productId);
            cs.setInt(3, qty);
            cs.execute();

            System.out.println("Sale created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewSales() {
        try (Connection con = DBConnection.getConnection()) {

            var st = con.createStatement();
            var rs = st.executeQuery(
                    "SELECT s.sale_id, c.customer_name, p.product_name, s.quantity, s.total_amount " +
                            "FROM sales s " +
                            "JOIN customers c ON s.customer_id = c.customer_id " +
                            "JOIN products p ON s.product_id = p.product_id"
            );

            boolean hasRows = false;

            while (rs.next()) {
                System.out.println(
                        rs.getInt("sale_id") + " | " +
                                rs.getString("customer_name") + " | " +
                                rs.getString("product_name") + " | " +
                                rs.getInt("quantity") + " | " +
                                rs.getDouble("total_amount")
                );
            }

            if (!hasRows){
                System.out.println("No sales records found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
