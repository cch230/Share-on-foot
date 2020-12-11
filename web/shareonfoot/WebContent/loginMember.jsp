<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shareonfoot.ConnectDB" %>

<%

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		//싱글톤 방식으로 자바 클래스를 불러옵니다.
		ConnectDB connectDB = ConnectDB.getInstance();
		String returns = connectDB.LoginMember(id, password);
		out.print(returns);

%>