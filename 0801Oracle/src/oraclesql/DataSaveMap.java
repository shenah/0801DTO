package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataSaveMap {

	public static void main(String[] args) {
		//�����ͺ��̽��� �����͸� ���α׷��� �����ϱ� - Map�� List
		
		//�о�� ������ �����ϱ� ���� �ڷᱸ�� 
		ArrayList <Map<String, Object>> list = new ArrayList<>();
		
		//�ʿ��� �������� ��ü ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			pstmt = con.prepareStatement("select deptno, dname, loc from dept");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap();
				map.put("deptno", rs.getInt("deptno"));
				map.put("dname", rs.getString("dname"));
				map.put("loc", rs.getString("loc"));
				list.add(map);
			}
			System.out.printf("%10s", "�μ���ȣ");
			System.out.printf("%10s", "�μ��̸�");
			System.out.printf("%10s", "�μ���ġ");
			System.out.print("\n");
			
			for(Map m : list) {
				System.out.printf("%10s", m.get("deptno"));
				System.out.printf("%10s", m.get("dname"));
				System.out.printf("%10s", m.get("loc"));
				System.out.print("\n");
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
