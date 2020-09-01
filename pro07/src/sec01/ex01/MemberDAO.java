package sec01.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class MemberDAO {
	//데이터베이스와 연동을 하는 클래스
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";

	private Connection con;
	private Statement stmt;
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			connDB();	//네 가지 정보로 데이터베이스를 연결한다.
			String query = "select * from t_member ";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query); //SQL문으로 회원정보를 조회합니다.
			
			while(rs.next()) {
				// 조회한 레코드의 각 컬럼값을 받아옵니다.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate  = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				
				
			}
			
		} catch (Exception e) {
			System.out.println("listMembers에서 에러 발생!");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return list;
	}
	
	
	private void connDB() { // 네 가지 정보로 데이터베이스를 연결합니다
		
		try {
			Class.forName(driver);	//드라이버 주소 가져옴
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공!");
			stmt = con.createStatement();
			System.out.println("Statement 생성 성공!");
			
		} catch (Exception e) {
			System.out.println("connDB 에러 발생 !!");
			e.printStackTrace();
		}
		
		
		
	}
	
	
}



























