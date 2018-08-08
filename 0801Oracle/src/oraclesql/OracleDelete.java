package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleDelete {

	public static void main(String[] args) {
		//입력받은 데이터를 테이블에서 삭제하기
		
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 부서번호(정수):");
		int deptno = sc.nextInt();
		//사용 후 반드시 close하기
		sc.close();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//autocommit 해제
			con.setAutoCommit(false);
			
			//sql 작성하고 실객체 생성
			//String sql = "delete from dept where deptno = "+deptno+"";
			String sql = "delete from dept where deptno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			//sql 실행
			int r = pstmt.executeUpdate();
			
			//성공하면 영향받은 행의 개수를 리턴 
			//0일때는 실패한것이 아니라 조건에 맞지 않아서이다. 
			if(r>0) {
				System.out.println("데이터 삭제성공");
			}
			else System.out.println("조건에 맞는 데이터가 없습니다");
			
			//실패하면 예외가 발생하므로 catch로 갑니다. 
		} catch (Exception e) {
			//예외의 내용을 출력
			System.out.println(e.getMessage());
			//예외가 발생한 지점을 역추적
			e.printStackTrace();
		}finally {
				try {
					if(pstmt != null) pstmt.close();//나중에 생성된 것을 먼저 닫는다. 
					if(con != null) con.close();
					
				} catch (Exception e) {}
		}
		

	}

}
