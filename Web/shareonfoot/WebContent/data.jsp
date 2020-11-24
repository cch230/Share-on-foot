<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
		//�̱��� ������� �ڹ� Ŭ������ �ҷ��ɴϴ�.
		ConnectDB connectDB = ConnectDB.getInstance();
		Float lng = Float.valueOf(request.getParameter("lng"));
		Float lat = Float.valueOf(request.getParameter("lat"));
		List<Location>list  = connectDB.recommend(lng, lat);
		if(!list.isEmpty()){
			for (Location location : list) {
				HashMapObject = new HashMap<String, Object>();
				HashMapObject.put("store_name", location.getname());
				HashMapObject.put("store_lng", location.getlng());
				HashMapObject.put("store_lat", location.getlat());
				HashMapObject.put("store_dst", location.getdst());	
				jObject = new JSONObject(HashMapObject);
			    // ������ ���� ������ ��ü�� �ϳ��� �迭 ���·� ����
			    HashMapArray = new ArrayList<JSONObject>();
			  	HashMapArray.add(jObject);
			    jArray=(JSONArray)HashMapArray.clone();	
			    // ���������� �迭�� �ϳ��� ����
			}
		}
		HashMapObject = new HashMap<String, Object>();
		HashMapObject.put("store_data", jArray);
		jObject = new JSONObject(HashMapObject);
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-�α�] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>