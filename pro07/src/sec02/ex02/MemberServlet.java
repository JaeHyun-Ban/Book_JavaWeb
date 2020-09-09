package sec02.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet(name = "MemberServlet3", urlPatterns = { "/member3" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest requset, HttpServletResponse response)
			throws ServletException, IOException {
		
		requset.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		
		//command 값을 받아온다.
		String command = requset.getParameter("command");
		
		//회원 가입창에서 전송된 command가 addMember이면 전송된 값들을 받아온다.
		if(command != null && command.equals("addMember")) {
			//회원 가입창에서 전송된 값들을 얻어와 MemberVO에 저장한 후 SQL문을 이용해 전달한다.
			String id = requset.getParameter("id");
			String pwd = requset.getParameter("pwd");
			String name = requset.getParameter("name");
			String email = requset.getParameter("email");
			
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setName(name);
			vo.setEmail(email);
			
			dao.addMember(vo);
		//삭제 명령이 들어왔을 때
		}else if(command != null && command.equals("delMember")) {
			String id = requset.getParameter("id");
			dao.delMember(id);
		}
		
		List<MemberVO> list = dao.listMembers();
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td>"
				+ "<td>이메일</td><td>가입일</td><td>삭제</td></tr>");
		
		//list에 들어가있는 회원 정보를 출력
		for(int i = 0; i < list.size(); i++) {
			
		}
		
	}

}





























