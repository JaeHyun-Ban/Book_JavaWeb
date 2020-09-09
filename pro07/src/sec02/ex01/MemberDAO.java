package sec02.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	//데이터베이스와 연동을 하는 클래스
	
	/*
	 * 톰캣에서 미리 값을 처리했으므로 주석처리
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	*/
	
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory; //DataSource작성한 것을 불러온다
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext(); //JNDI에 접근하기 위해 기본 경로 (java:/comp/env)를 지정합니다.
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			//톰캣 context.xml에 설정한 name값인 jdbc/oracle을 이용해 톰캣이 미리 연결한  DataSource를 받아 옵니다.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("MemberDAO 메서드 오류 발생!");
			e.printStackTrace();
		}
	}
	
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		
		try {
//			connDB(); 
			con = dataFactory.getConnection(); //DataSource를 이용해 데이터베이스에 연결한다.
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
	
	/*
	 * DAO에서 직접 데이터베이스를 연결하는 기능은 주석 처리해준다
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
	*/
	
}
































