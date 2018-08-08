package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeptDTOMain {

	public static void main(String[] args) {
		// 데이터베이스의 데이터를 프로그램에 저장하기 - DTO의 List

		// 읽어온 데이터 저장하기 위한 자료구조
		ArrayList<DeptSaveDTO> list = new ArrayList<>();

		// 필요한 여러가지 객체 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DeptSaveDTO dept = new DeptSaveDTO();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				
				list.add(dept);
			}
			System.out.printf("%10s", "부서번호");
			System.out.printf("%10s", "부서이름");
			System.out.printf("%10s", "부서위치");
			System.out.print("\n");

			for (DeptSaveDTO d : list) {
				System.out.printf("%10s", d.getDeptno());
				System.out.printf("%10s", d.getDname());
				System.out.printf("%10s", d.getLoc());
				System.out.print("\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}

	}
}