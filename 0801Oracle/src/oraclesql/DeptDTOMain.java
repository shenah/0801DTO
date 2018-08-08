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
		// �����ͺ��̽��� �����͸� ���α׷��� �����ϱ� - DTO�� List

		// �о�� ������ �����ϱ� ���� �ڷᱸ��
		ArrayList<DeptSaveDTO> list = new ArrayList<>();

		// �ʿ��� �������� ��ü ����
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
			System.out.printf("%10s", "�μ���ȣ");
			System.out.printf("%10s", "�μ��̸�");
			System.out.printf("%10s", "�μ���ġ");
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