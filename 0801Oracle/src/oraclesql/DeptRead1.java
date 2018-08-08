package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeptRead1 {

	public static void main(String[] args) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("deptno") + " ");
				System.out.print(rs.getString("dname") + " ");
				System.out.print(rs.getString("loc") + "\n");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {}
			
		}
		

	}

}
