package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleInsert {

	public static void main(String[] args) {
		//�����ͺ��̽� ���� ���� ����
		Connection con = null;
		//sql ���� ���� ����
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//SQL ��ü ���� -������ ���� ���� ����
			pstmt = con.prepareStatement("insert into dept(deptno, dname, loc) values(60, '����', '�λ�')");
			//pstmt = con.prepareStatement("delete from dept where deptno = 60");
			//pstmt = con.prepareStatement("update dept set dname = 'ȸ��' where deptno = 50");
			
			//SQL ����
			int r = pstmt.executeUpdate();
			
			//�����ϸ� ������� ���� ������ ���� 
			//0�϶��� �����Ѱ��� �ƴ϶� ���ǿ� ���� �ʾƼ��̴�. 
			if(r>0) {
				System.out.println("������ ������Ʈ����");
			}
			else System.out.println("���ǿ� �´� �����Ͱ� �����ϴ�");
			
			//�����ϸ� ���ܰ� �߻��ϹǷ� catch�� ���ϴ�. 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();	
				} catch (Exception e) {}

		}
		

	}

}
