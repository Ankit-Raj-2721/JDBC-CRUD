//importing the sql package
import java.sql.*;
import java.util.*;

public class JDBCFirstProgram {

	public static void main(String[] args) {
		
		String url="jdbc:mysql://localhost:3306/mydb3";
		String user="root";
		String password="system";
		
		try {
			Scanner scan = new Scanner(System.in);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection is established");
			while(true) {
				System.out.println("Enter 1->Insert, 2->Update, 3->Delete, 4->Fetch Data");
				int choice = scan.nextInt();
				switch(choice) {
				case 1: insert(con,scan);
				break;
				case 2: update(con,scan);
				break;
				case 3: delete(con,scan);
				break;
				case 4: fetchData(con,scan);
				break;
				default: System.out.println("Invalid choice");
				}
			}
		}
			
			catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void insert(Connection con, Scanner scan) {
		
		try {
			String query = "INSERT into Course values(?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			System.out.println("Enter Course ID");
			int c_id = scan.nextInt();
			System.out.println("Enter Course Name");
			String c_name = scan.next();
			System.out.println("Enter Course Trainer");
			String c_trainer = scan.next();
			System.out.println("Enter Course Cost");
			int c_cost = scan.nextInt();
			pstmt.setInt(1, c_id);
			pstmt.setString(2, c_name);
			pstmt.setString(3, c_trainer);
			pstmt.setInt(4, c_cost);
			pstmt.execute();
			System.out.println("Data is inserted");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(Connection con, Scanner scan) {
		
		try {
			String query = "Update course set c_cost=1400 where c_id=1";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Data is updated");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(Connection con, Scanner scan) {
		
		try {
			String query = "Delete from course where c_id=1";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Data is deleted");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void fetchData(Connection con, Scanner scan) {
		
		try {
			String query = "Select * from course";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getString(3) +" "+ rs.getInt(4));
			}
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
