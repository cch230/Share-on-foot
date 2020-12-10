<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="DB.ConnectDB" %>
    <%@ page import="DB.Location"%>
     <%@ page import= "java.util.*"%>
    <%@page import="org.json.simple.*"%>
	<%@page import="java.sql.SQLException"%>
	
	
<%
	@SuppressWarnings("rawtypes")
	JSONObject jsonMain = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	JSONObject[] jObject = new JSONObject[5];
	int i=0; 
	
    try{
		//싱글톤 방식으로 자바 클래스를 불러옵니다.w3%
		ConnectDB tmp = ConnectDB.getInstance();
		Float lng = Float.valueOf(request.getParameter("lng"));
		Float lat = Float.valueOf(request.getParameter("lat"));
		Integer day = Integer.valueOf(request.getParameter("day"));
			
		List<String>list= new ArrayList<>();
		list.addAll(tmp.recommend(lng, lat, day));
		if(!list.isEmpty()){
			for (String obj : list) {
				
				//HashMapObject = new HashMap<String, Object>();
				if(i%4==0)	{
					jObject[i/4] = new JSONObject();
					jObject[i/4].put("store_name", obj);
				
				}
				else if(i%4==1)	jObject[i/4].put("store_lng",  obj);
				else if(i%4==2)	jObject[i/4].put("store_lat",  obj);
				else	{
					jObject[i/4].put("store_dst",  obj);	
					jsonArray.add(jObject[i/4]);
					
				}
				
				//out.println("store_name"+i+location.getname()); 
				//jsonArray.put("store_"+i,HashMapArray);
				//Object = new JSONObject(HashMapObject);
			    // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬
			    ///HashMapArray = new ArrayList<JSONObject>();
			  	//HashMapArray.add(jObject);
			    i++;
			   
			}
		}
	    // 최종적으로 배열을 하나로 묶음
		//HashMapObject = new HashMap<String, Object>();
		//HashMapObject.put("store_data", HashMapArray);
		//jsonMain = new JSONObject(HasArray);
		//jsonMain=(JSONObject)HasArray.clone();	
		jsonMain.put("store_data",jsonArray);
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-로그] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>