package sec01.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDAO {
	//데이터베이스와 연동을 하는 클래스
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	
	private Connection con;
	private PreparedStatement pstmt;	//pstmt생성 -> stmt를 pstmt로 사용한다
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		
		try {
			connDB(); //네 가지 정보로 데이터베이스를 연결한다.
			String query = "select * from t_member ";
			System.out.println("prepareStatement: " + query);
			
			pstmt = con.prepareStatement(query); //prepareStatement에 메서드 SQL을 전달 후 PrepareState 객체를 생성
			ResultSet rs = pstmt.executeQuery(); //executeQuery 메서드를 호출해 미리 설정한 SQL문을 실행한다.
			
			while(rs.next()) { //SQL문으로 회원 정보를 조회합니다.
				
				// 조회한 레코드의 각 컬럼 값을 받아온다.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO(); //각 컬럼값을 다시 MemberVO 객체의 속성에 설정합니다.
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo); //설정된 MemberVO객체를 다시 ArrayList에 저장합니다.
			}
			rs.close();
			pstmt.close(); //pstmt 꺼주기
			con.close();
		} catch (Exception e) {
			System.out.println("listMembers에서 에러 발생!");
			e.printStackTrace();
		}
		//조회한 레코드의 개수만큼 MemberVO 객체를 저장한 ArrayList를 반환합니다.
		return list; 
	}
	
	private void connDB() {
		
		try {
			Class.forName(driver); //오라클 드라이버 실행
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd); //커넥션 로그인
			System.out.println("Connection 생성 성공");
			
		} catch (Exception e) {
			System.out.println("connDB에서 예외 발생!");
			e.printStackTrace();
		}
		
	}
	
	
}
































