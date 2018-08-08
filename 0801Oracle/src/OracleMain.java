import java.sql.Connection;
import java.sql.DriverManager;


public class OracleMain {

	public static void main(String[] args) {
		Connection con = null;
		try {
			//����ϴ� �����ͺ��̽� ���� �ε��ϴ� Ŭ���� �̸��� �����Ǿ� �ֽ��ϴ�. 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//�ε� Ȯ�� �޼���. �޼����� �ȳ����� Ŭ�����̸��� driver�� referenced libraries�� �߰��ߴ��� Ȯ��
			System.out.println("����Ŭ ����̹� �ε� ����");
			//�����ͺ��̽��� ����
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			//���� Ȯ�� �޼��� . �޼����� �ȳ����� �����ͺ��̽� ���� ����, �����ͺ��̽� �ּ�, ������ Ȯ�� 
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if (con != null) con.close();
				} catch (Exception e) {}
			
		}
		

	}

}
