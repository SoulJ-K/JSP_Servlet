package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이징처리도 함께 작성
		//1. 내가 가지고 있는 게시글의 총 개수
		//2. 각 페이지의 게시글 목록
		
		BoardService service = new BoardService();
		
		//게시글 총 개수
		int listCount = service.getListCount();
		
		/*
		 * ㅡㅡㅡㅡㅡㅡㅡ게시글 1ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * ㅡㅡㅡㅡㅡㅡㅡ게시글 2ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * ㅡㅡㅡㅡㅡㅡㅡ게시글 3ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * ㅡㅡㅡㅡㅡㅡㅡ게시글 4ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * ㅡㅡㅡㅡㅡㅡㅡ게시글 5ㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 	  < 1 [2] 3 4 5 6 7 8 9 10 >
		 * 	  <11 12 13 14 15 16 17 18 19 20>
		 * 					............... 41page
		 * 
		 * 내가 현재 보고있는 페이지 : currentPage ([2])
		 * 
		 * 한 페이지 안에서 보여지는 게시글 수 : boardLimit
		 * 
		 * 한 페이지에서 보이는 페이징 수 <1 ~ > : pageLimit
		 * 
		 * 현재 페이지에서 보이는 페이징버튼의 시작 : startPage(1)
		 * 현재 페이지에서 보이는 마지막 페이징 버튼 : endPage(10)
		 * 
		 * 게시글의 마지막 페이지 : maxPage(ex. 41)
		 * 
		 * */
		
		int currentPage;		//현재 페이지
		int pageLimit = 10;			//한 페이지에 표시된 페이징 수
		int maxPage;			//전체 페이지 수의 마지막 페이지
		int startPage;			//페이징 된 페이지 중 시작 페이지
		int endPage;			//페이징 된 페이지 중 마지막 페이지
		int boardLimit = 10;			//한 페이지 안에서 보여지는 게시글의 수
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		maxPage = (int)((double) listCount / boardLimit + 0.9);
		
		startPage =(((int)((double)currentPage / pageLimit + 0.9)) -1) * pageLimit + 1;
		
		endPage = pageLimit + startPage - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
		
		ArrayList<Board> list =  service.selectList(currentPage, boardLimit);
		
		String page = null;
		if(list != null) {
			page = "views/board/boardListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
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
