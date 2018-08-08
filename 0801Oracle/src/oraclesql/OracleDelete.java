package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleDelete {

	public static void main(String[] args) {
		//�Է¹��� �����͸� ���̺��� �����ϱ�
		
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �μ���ȣ(����):");
		int deptno = sc.nextInt();
		//��� �� �ݵ�� close�ϱ�
		sc.close();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//autocommit ����
			con.setAutoCommit(false);
			
			//sql �ۼ��ϰ� �ǰ�ü ����
			//String sql = "delete from dept where deptno = "+deptno+"";
			String sql = "delete from dept where deptno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			//sql ����
			int r = pstmt.executeUpdate();
			
			//�����ϸ� ������� ���� ������ ���� 
			//0�϶��� �����Ѱ��� �ƴ϶� ���ǿ� ���� �ʾƼ��̴�. 
			if(r>0) {
				System.out.println("������ ��������");
			}
			else System.out.println("���ǿ� �´� �����Ͱ� �����ϴ�");
			
			//�����ϸ� ���ܰ� �߻��ϹǷ� catch�� ���ϴ�. 
		} catch (Exception e) {
			//������ ������ ���
			System.out.println(e.getMessage());
			//���ܰ� �߻��� ������ ������
			e.printStackTrace();
		}finally {
				try {
					if(pstmt != null) pstmt.close();//���߿� ������ ���� ���� �ݴ´�. 
					if(con != null) con.close();
					
				} catch (Exception e) {}
		}
		

	}

}
