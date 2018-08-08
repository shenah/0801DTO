package oraclesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class OracleDataBinding {

	public static void main(String[] args) {
		// 데이터를 입력받아서 테이블에 넣는 작업

		// 데이터베이스 연결 변수 선언
		Connection con = null;
		// sql 실행 변수 선언
		PreparedStatement pstmt = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("부서번호:");
		int deptno = sc.nextInt();
		// enter를 제거하는 작업.
		sc.nextLine();
		System.out.println("부서이름:");
		String dname = sc.nextLine();
		System.out.println("지역:");
		String loc = sc.nextLine();
		sc.close();

		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			
			//autocommit 해제
			con.setAutoCommit(false);
			
			// 원래 방식대로 하면 데이터 바인딩이 힘들어진다.
			// String sql = "insert into dept(deptno, dname, loc) values("+deptno+",
			// '"+dname+"', '"+loc+"')";
			String sql = "insert into dept(deptno, dname, loc) values(?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			// 데이터 바인딩
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);

			// sql 실행
			int r = pstmt.executeUpdate();

			// 성공하면 영향받은 행의 개수를 리턴
			// 0일때는 실패한것이 아니라 조건에 맞지 않아서이다.
			if (r > 0) {
				System.out.println("데이터 삽입성공");
			} else
				System.out.println("조건에 맞는 데이터가 없습니다");
			// 실패하면 예외가 발생하므로 catch로 갑니다.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();// 나중에 생성된 것을 먼저 닫는다.
				if (con != null)
					con.close();

			} catch (Exception e) {
			}

		}

	}

}
