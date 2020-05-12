package com.kh.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet4 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//post방식은 바로 인코딩 해야함. post방식만 반드시 인코딩 필요
		
		String name =  req.getParameter("name");
		
		String gender = req.getParameter("gender");
		
		String age = req.getParameter("age");
		
		String city = req.getParameter("city");
		
		String height = req.getParameter("height");
				
		String[] food = req.getParameterValues("food");
		String recommendation = "";
		
		switch(age) {
		case "10대 미만" : recommendation = "뽀로로"; break;
		case "10대" : recommendation = "펭수"; break;
		case "20대" : recommendation = "둘리"; break;
		case "30대" : recommendation = "핑구"; break;
		case "40대" : recommendation = "현금"; break;
		case "50대" : recommendation = "집"; break;
		}

		//jsp는 html과는 다르게 데이터를 받아와서 출력 가능
		//서블릿에서 뭔가를 보내야 받을 수 있음
		//화면으로 데이터를 보내줄 작업을 해야 화면에서 받을 수 있음
		
		req.setAttribute("name", name);    //(보내는 값을 담아둘 변수, 보낼 값)
		req.setAttribute("gender", gender);
		req.setAttribute("age", age);
		req.setAttribute("city", city);
		req.setAttribute("height", height);
		String foods = String.join(" ", food);
		req.setAttribute("foods", foods);
		req.setAttribute("recommendation", recommendation);
		
		//데이터를 보낼 주소
		RequestDispatcher view = req.getRequestDispatcher("servlet/testServlet4End.jsp");   //(주소)
		
		//붙이는 작업
		view.forward(req, resp);
	}
	
}
