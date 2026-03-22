package libraryapp;
import java.sql.*;
	import java.util.Scanner;

	public class Libraryapp{
	    static final String DB_URL = "jdbc:mysql://localhost:3306/kavyadb";
	    static final String USER = "root";
	    static final String PASS = "kavya";

	    static Scanner sc = new Scanner(System.in);
	    static Connection conn;
	    public static void main(String[] args) {
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            System.out.println("✅ Connected to Library Database!");

	            int choice;
	            do {
	                System.out.println("\n--- Library Menu ---");
	                System.out.println("1. Add Book");
	                System.out.println("2. Borrow Book");
	                System.out.println("3. Return Book");
	                System.out.println("4. View Books");
	                System.out.println("5. Exit");
	                System.out.print("Enter choice: ");
	                choice = sc.nextInt(); sc.nextLine();

	                switch(choice) {
	                    case 1: addBook(); break;
	                    case 2: borrowBook(); break;
	                    case 3: returnBook(); break;
	                    case 4: viewBooks(); break;
	                    case 5: System.out.println("Exiting..."); break;
	                    default: System.out.println("Invalid choice!");
	                }
	            } while(choice != 5);

	            conn.close();

	        } catch(Exception e) {
	            System.out.println("❌ Error: " + e);
	        }
	    }

	    // Add Book
	    static void addBook() throws SQLException {
	        System.out.print("Enter title: ");
	        String title = sc.nextLine();

	        System.out.print("Enter author: ");
	        String author = sc.nextLine();

	        System.out.print("Enter number of copies: ");
	        int copies = sc.nextInt(); sc.nextLine();

	        String sql = "INSERT INTO books(title,author,copies) VALUES(?,?,?)";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, title);
	        pst.setString(2, author);
	        pst.setInt(3, copies);
	        pst.executeUpdate();

	        System.out.println("✅ Book added successfully!");
	    }

	    // Borrow Book
	    static void borrowBook() throws SQLException {
	        viewBooks();

	        System.out.print("Enter Book ID to borrow: ");
	        int id = sc.nextInt(); sc.nextLine();

	        String checkSql = "SELECT copies FROM books WHERE book_id=?";
	        PreparedStatement pst = conn.prepareStatement(checkSql);
	        pst.setInt(1, id);
	        ResultSet rs = pst.executeQuery();

	        if(rs.next() && rs.getInt("copies") > 0) {
	            String updateSql = "UPDATE books SET copies=copies-1 WHERE book_id=?";
	            PreparedStatement pst2 = conn.prepareStatement(updateSql);
	            pst2.setInt(1, id);
	            pst2.executeUpdate();

	            System.out.println("✅ Book borrowed successfully!");
	        } else {
	            System.out.println("❌ Book not available!");
	        }
	    }

	    // Return Book
	    static void returnBook() throws SQLException {
	        System.out.print("Enter Book ID to return: ");
	        int id = sc.nextInt(); sc.nextLine();

	        String updateSql = "UPDATE books SET copies=copies+1 WHERE book_id=?";
	        PreparedStatement pst = conn.prepareStatement(updateSql);
	        pst.setInt(1, id);
	        pst.executeUpdate();

	        System.out.println("✅ Book returned successfully!");
	    }

	    // View Books
	    static void viewBooks() throws SQLException {
	        String sql = "SELECT * FROM books";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        System.out.println("\nID | Title | Author | Copies");

	        while(rs.next()) {
	            System.out.println(
	                rs.getInt("book_id") + " | " +
	                rs.getString("title") + " | " +
	                rs.getString("author") + " | " +
	                rs.getInt("copies")
	            );
	        }
	    }
	}
