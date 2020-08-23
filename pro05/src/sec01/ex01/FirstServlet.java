package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	//웹 브라우저에서 요청시 호출됨
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메서드 호출");
		//새로고침을 할 시 doget메서드가 반복해서 호출된다
	}
	
	//서블렛이 종료시 호출됨
	@Override
	public void destroy() { 
		System.out.println("destroy 메서드 호출");
	}
	
	//서블렛이 실행시 호출됨
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	} 

	
	
	
}