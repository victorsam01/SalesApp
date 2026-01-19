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

    public static void deleteCustomer() {
        try (Connection con = DBConnection.getConnection()) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Do you want to Remove / Inactive the customer? ");
            System.out.print("Remove - (R) Inactive - (I) : ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("R")){
                System.out.print("Please enter the customer name : ");
                String csname = sc.nextLine();
                System.out.print("Please enter the customer ID : ");
                int csid = sc.nextInt();

                PreparedStatement st = con.prepareStatement("SELECT * FROM customers where customer_name = ? AND customer_id = ? AND customer_status = 'A'");
                st.setString(1, csname);
                st.setInt(2, csid);
                ResultSet rs = st.executeQuery();

                boolean hasRows = false;

                if (rs.next()) {
                    hasRows = true;
                }

                if (!hasRows){
                    System.out.println("No customer/No active customer found under the name **"+csname+"** with ID **"+csid+"**");
                }
                else {
                    PreparedStatement ps = con.prepareStatement("DELETE customers WHERE customer_name = ? AND customer_id = ? AND customer_status = 'A'");
                    ps.setString(1, csname);
                    ps.setInt(2, csid);
                    ps.executeUpdate();

                    System.out.println("Customer "+csname+" delete successfully");
                }
            } else if (choice.equalsIgnoreCase("I")) {
                System.out.print("Please enter the customer name : ");
                String csname = sc.nextLine();
                System.out.print("Please enter the customer ID : ");
                int csid = sc.nextInt();

                PreparedStatement st = con.prepareStatement("SELECT * FROM customers where customer_name = ? AND customer_id = ? AND customer_status = 'A'");
                st.setString(1, csname);
                st.setInt(2, csid);
                ResultSet rs = st.executeQuery();

                boolean hasRows = false;

                if (rs.next()){
                    hasRows = true;
                }

                if (!hasRows){
                    System.out.println("No customer/No active customer found under the name **"+csname+"** with ID **"+csid+"**");
                }
                else {
                    PreparedStatement ps = con.prepareStatement("UPDATE customers SET customer_status = 'I' WHERE customer_name = ? AND customer_id = ? AND customer_status = 'A'");
                    ps.setString(1, csname);
                    ps.setInt(2, csid);
                    ps.executeUpdate();

                    System.out.println("Customer "+csname+" deactivated successfully");
                }
            }

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
