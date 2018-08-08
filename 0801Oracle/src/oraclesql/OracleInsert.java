package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleInsert {

	public static void main(String[] args) {
		//데이터베이스 연결 변수 선언
		Connection con = null;
		//sql 실행 변수 선언
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//SQL 객체 생성 -데이터 삽입 삭제 변경
			pstmt = con.prepareStatement("insert into dept(deptno, dname, loc) values(60, '영업', '부산')");
			//pstmt = con.prepareStatement("delete from dept where deptno = 60");
			//pstmt = con.prepareStatement("update dept set dname = '회계' where deptno = 50");
			
			//SQL 실행
			int r = pstmt.executeUpdate();
			
			//성공하면 영향받은 행의 개수를 리턴 
			//0일때는 실패한것이 아니라 조건에 맞지 않아서이다. 
			if(r>0) {
				System.out.println("데이터 업데이트성공");
			}
			else System.out.println("조건에 맞는 데이터가 없습니다");
			
			//실패하면 예외가 발생하므로 catch로 갑니다. 
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
