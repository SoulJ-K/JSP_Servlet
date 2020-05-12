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
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/myPage.me")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원정보 수정을 하고 내정보페이지에서 수정이 잘 되었는지 확인
		//세션에 저장되어있는 로그인 정보를 가져오면 
		//수정한 값이 db에 갔다가 돌아올 때 db에서 수정한 값은 바뀌지 않고 수정 전의 값만  보임.
		
		HttpSession session = request.getSession();
		Member sessionMember = (Member)session.getAttribute("loginUser");
		String loginUserId = sessionMember.getUserId();
		
		Member member = new MemberService().selectMember(loginUserId);
		
		String page = null;
		if(member != null) {
			page = "views/member/memberView.jsp";
			request.setAttribute("member", member);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "회원조회에 실패했습니다.");
		}
				
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
