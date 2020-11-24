<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="shareonfoot.ConnectDB" %>
    <%@ page import="shareonfoot.Location"%>
    <%@ page import= "java.util.*"%>
    <%@page import="org.json.simple.*"%>
	<%@page import="java.sql.SQLException"%>
	
<%
	HashMap<String, Object> HashMapObject = new HashMap<String, Object>();
	ArrayList<JSONObject> HashMapArray = new ArrayList<JSONObject>();
	JSONObject jsonMain = new JSONObject();
	JSONArray jArray = new JSONArray();
	JSONObject jObject = new JSONObject();
    try{
		//싱글톤 방식으로 자바 클래스를 불러옵니다.
		ConnectDB tmp = ConnectDB.getInstance();
		Float lng = Float.valueOf(request.getParameter("lng"));
		Float lat = Float.valueOf(request.getParameter("lat"));
		List<Location>list= new ArrayList<Location>();
		list.addAll(tmp.recommend(lng, lat));
		if(!list.isEmpty()){
			for (Location location : list) {
				HashMapObject = new HashMap<String, Object>();
				HashMapObject.put("store_name", location.getname());
				HashMapObject.put("store_lng", location.getlng());
				HashMapObject.put("store_lat", location.getlat());
				HashMapObject.put("store_dst", location.getdst());	
				jObject = new JSONObject(HashMapObject);
			    // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
			    HashMapArray = new ArrayList<JSONObject>();
			  	HashMapArray.add(jObject);
			    jArray=(JSONArray)HashMapArray.clone();	
			    // 최종적으로 배열을 하나로 묶음
			}
		}
		HashMapObject = new HashMap<String, Object>();
		HashMapObject.put("store_data", jArray);
		jObject = new JSONObject(HashMapObject);
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-로그] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>