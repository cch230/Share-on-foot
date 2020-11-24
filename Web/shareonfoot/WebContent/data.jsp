<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="shareonfoot.ConnectDB" %>
    <%@ page import="shareonfoot.Location"%>
    <%@ page import= "java.util.*"%>
    <%@page import="org.json.simple.*"%>
	<%@page import="java.sql.SQLException"%>
	
<%
	
	JSONObject jsonMain = new JSONObject();
	JSONArray jArray = new JSONArray();
	JSONObject jObject = new JSONObject();
    try{
		//싱글톤 방식으로 자바 클래스를 불러옵니다.
		ConnectDB connectDB = ConnectDB.getInstance();
		Float lng = Float.valueOf(request.getParameter("lng"));
		Float lat = Float.valueOf(request.getParameter("lat"));
		List<Location>list  = connectDB.recommend(lng, lat);
		if(!list.isEmpty()){
			for (Location location : list) {
				jObject.put("store_name", location.getname());
				jObject.put("store_lng", location.getlng());
				jObject.put("store_lat", location.getlat());		
			    // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
			    jArray.add(0, jObject);   			
			    // 최종적으로 배열을 하나로 묶음
			}
		}else{
			jObject=null;
			jArray.add(0, jObject);
		}
		jsonMain.put("store_data", jArray);   
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-로그] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>