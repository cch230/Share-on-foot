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
		//�̱��� ������� �ڹ� Ŭ������ �ҷ��ɴϴ�.
		ConnectDB connectDB = ConnectDB.getInstance();
		Float lng = Float.valueOf(request.getParameter("lng"));
		Float lat = Float.valueOf(request.getParameter("lat"));
		List<Location>list  = connectDB.recommend(lng, lat);
		if(!list.isEmpty()){
			for (Location location : list) {
				jObject.put("store_name", location.getname());
				jObject.put("store_lng", location.getlng());
				jObject.put("store_lat", location.getlat());		
			    // ������ ���� ������ ��ü�� �ϳ��� �迭 ���·� ����
			    jArray.add(0, jObject);   			
			    // ���������� �迭�� �ϳ��� ����
			}
		}else{
			jObject=null;
			jArray.add(0, jObject);
		}
		jsonMain.put("store_data", jArray);   
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-�α�] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>