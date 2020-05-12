package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.th")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		/* String title = request.getParameter("title"); */
		/* System.out.println(title); */
		
		//내가 지금 받아온 리퀘스트(enctype)의 인코딩 타입이 multipart/form-data로 전송되었는지 확인하는 메소드
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;	// 10MByte로 전송파일 용량 제한
			String root = request.getSession().getServletContext().getRealPath("/");	//웹 서버 컨테이너 경로 추출
			System.out.println(request.getSession());
			System.out.println(request.getSession().getServletContext());
			System.out.println(request.getSession().getServletContext().getRealPath("/"));
			String savePath = root + "thumbnail_uploadFiles/";
			
			//System.out.println(savePath);
			
			// new DefaultFileRenamePolicy()  - 파일 이름 변경하는 정책 / 기존에 있는 정책
			/*
			 * MultipartRequest multiRequest = 
			 * new MultipartRequest(request, savePath, maxSize, new DefaultFileRenamePolicy());
			 */
			
			/*
			 * aaa.zip  ---- > aaa1.zip 으로 변환
			 * aaa.zip  ---- > aaa2.zip으로 변환
			 * */
			MultipartRequest multiRequest 
			 = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			
			ArrayList<String> saveFiles = new ArrayList<String>();		//바뀐 파일의 이름을 저장
			ArrayList<String> originFiles = new ArrayList<String>();	//원본 파일의 이름을 저장
			
			
		
			Enumeration<String> files = multiRequest.getFileNames(); //폼에서 전송된 파일 리스트들의 name반환
			while(files.hasMoreElements()) {
				String name = files.nextElement(); //전송 순서 역순으로 가져옴
				//System.out.println(name);
				
				//getFilesystemName(); // MyRenameFilePolicy정책의 rename 메소드에서 작성한대로 rename된 파일명 
				if(multiRequest.getFilesystemName(name) != null) {
					
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));	
					 //getOriginalFileName() : 실제 사용자가 업로드할 때 파일명
				}
			}
			
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String bWriter = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
			
			//받아올 데이터 받아온 작업
			//========================================================================================
			//내가 DB로 보낼거 넣고 있는 작업
			Board b = new Board();
			b.setbTitle(title);
			b.setbContent(content);
			b.setbWriter(bWriter);
			
			/*
			 * System.out.println(saveFiles); System.out.println(originFiles);
			 */
			
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				Attachment at = new Attachment();
				at.setFilePath(savePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				if(i == originFiles.size() - 1) {
					at.setFileLevel(0);
				}else {
					at.setFileLevel(1);
				}
				
				fileList.add(at);
			}
			
			int result =  new BoardService().insertTumbnail(b, fileList);
			
			if(result > 0) {
				response.sendRedirect("list.th");
			}else {
				
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "사진 게시판 등록에 실패하였습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
