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
	JSONObject[] jObject = new JSONObject[11];
	int i=0; 
	
    try{
		//싱글톤 방식으로 자바 클래스를 불러옵니다.w3%
		ConnectDB tmp = ConnectDB.getInstance();
		String identifier = request.getParameter("identifier");
		Integer pages = Integer.valueOf(request.getParameter("page"));
			
		List<String>list= new ArrayList<>();
		list.addAll(tmp.category(identifier, pages));
		if(!list.isEmpty()){
			for (String obj : list) {
				
				//HashMapObject = new HashMap<String, Object>();
				if(i%6==0)	{
					jObject[i/6] = new JSONObject();
					jObject[i/6].put("store_jidx", obj);
				
				}
				else if(i%6==1)	jObject[i/6].put("store_jname",  obj);
				else if(i%6==2)	jObject[i/6].put("store_jcategory",  obj);
				else if(i%6==4)	jObject[i/6].put("store_jstar",  obj);
				else if(i%6==5)	jObject[i/6].put("store_jadress",  obj);

				else	{
					jObject[i/6].put("store_jreview",  obj);	
					jsonArray.add(jObject[i/6]);
					
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
		jsonMain.put("store_info",jsonArray);
		out.println(jsonMain.toJSONString()); 
	}catch(Exception ex){
		application.log("[Select_Time_info.jsp-로그] ex="+ex.getMessage());
	}finally{
		
	}
   
    
%>