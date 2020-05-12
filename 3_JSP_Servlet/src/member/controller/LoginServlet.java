package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/login.me", name="LoginServlet")		//어노테이션 url 맵핑 해주고 있음
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**  두개가 만들어진 이유 : jsp에서 설정한 방식으로 하되 post방식으로 지정하면 post에서 get을 호출해서 사용하도록 만들어짐
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 기능 짜야함
		//아이디 비밀번호 필요함
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		/* System.out.println(userId + ", " + userPwd); */
		
		//데이터베이스에 있는 아이디 비번과 비교해야함
		Member m = new Member(userId, userPwd);
		
		Member loginUser =  new MemberService().loginMember(m);
		
		if(loginUser != null) {
			/*
			 * request.setAttribute("loginUser", loginUser); RequestDispatcher view =
			 * request.getRequestDispatcher("index.jsp"); view.forward(request, response);
			 */
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(600);  //10분동안 session 유지
			
			//response.sendRedirect("index.jsp");
			response.sendRedirect(request.getContextPath()); //url에 표시되지 않음
			
		} else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
