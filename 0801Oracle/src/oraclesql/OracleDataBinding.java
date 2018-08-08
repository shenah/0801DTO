package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleDataBinding {

	public static void main(String[] args) {
		// �����͸� �Է¹޾Ƽ� ���̺� �ִ� �۾�

		// �����ͺ��̽� ���� ���� ����
		Connection con = null;
		// sql ���� ���� ����
		PreparedStatement pstmt = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("�μ���ȣ:");
		int deptno = sc.nextInt();
		// enter�� �����ϴ� �۾�.
		sc.nextLine();
		System.out.println("�μ��̸�:");
		String dname = sc.nextLine();
		System.out.println("����:");
		String loc = sc.nextLine();
		sc.close();

		try {
			// ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// �����ͺ��̽� ����
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//autocommit ����
			con.setAutoCommit(false);
			
			// ���� ��Ĵ�� �ϸ� ������ ���ε��� ���������.
			// String sql = "insert into dept(deptno, dname, loc) values("+deptno+",
			// '"+dname+"', '"+loc+"')";
			String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			// ������ ���ε�
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);

			// sql ����
			int r = pstmt.executeUpdate();

			// �����ϸ� ������� ���� ������ ����
			// 0�϶��� �����Ѱ��� �ƴ϶� ���ǿ� ���� �ʾƼ��̴�.
			if (r > 0) {
				System.out.println("������ ���Լ���");
			} else
				System.out.println("���ǿ� �´� �����Ͱ� �����ϴ�");
			// �����ϸ� ���ܰ� �߻��ϹǷ� catch�� ���ϴ�.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();// ���߿� ������ ���� ���� �ݴ´�.
				if (con != null)
					con.close();

			} catch (Exception e) {
			}

		}

	}

}
