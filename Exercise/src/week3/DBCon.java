package week3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class DBCon {
	private Connection con;
	private String jdbcName;
	private String query;
	private ResultSet rs;
	private PreparedStatement pstmt;
	public DBCon() {
		con = null;
		jdbcName = "com.mysql.cj.jdbc.Driver";
		query = "SELECT * FROM test";
		pstmt = null;
		rs = null;
	}
	
	/**
	 * 
	 */
	public void connect() {
		try {
			Class.forName(jdbcName); //載入jdbc驅動程式
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?" + "user=107590026&password=zaq78963"); //驅動程式管理器，取得mysql連線
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery("SELECT * FROM test");
			while (rs.next()) {
				System.out.print(rs.getString("msg"));
				/*System.out.print(", "+rs.getString("name"));
				System.out.print(", "+rs.getString("title"));
				System.out.println(", "+rs.getString("price"));*/
			}
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con!= null) {
					con.close();
					con = null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
