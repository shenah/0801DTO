import java.sql.Connection;
import java.sql.DriverManager;


public class OracleMain {

	public static void main(String[] args) {
		Connection con = null;
		try {
			//사용하는 데이터베이스 마다 로드하는 클래스 이름은 결정되어 있습니다. 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//로드 확인 메세지. 메세지가 안나오면 클래스이름과 driver를 referenced libraries에 추가했는지 확인
			System.out.println("오라클 드라이버 로드 성공");
			//데이터베이스에 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			//연결 확인 메세지 . 메세지가 안나오면 데이터베이스 실행 여부, 데이터베이스 주소, 계정등 확인 
			System.out.println("데이터베이스 연결 성공");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if (con != null) con.close();
				} catch (Exception e) {}
			
		}
		

	}

}
