package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	String jdbcUrl = "jdbc:mysql://49.50.172.215:3307/shareonfoot?autoReconnect=true&characterEncoding=euc_kr"; 
																						// MySQL 계정
	String dbId = "root"; // MySQL 계정
	String dbPw = "Cch951753!"; // 비밀번호
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "a";

	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	private ConnectDB() {

		try {
			System.out.println("연결");

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

		} catch (SQLException e) {

			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();

		}
	}

	public List<String> recommend(Float lng, Float lat, int day) {
		List<String> list = new ArrayList<>();
		try {

			
			  switch(day) { 
				  case 1: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM incheon_st "
						  		+ "WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
							  		+ "UNION "
							  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
							  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
							  		+ "GROUP BY lng, lat "
							  		+ "HAVING distance < 5) "
							  		+ "ORDER BY distance LIMIT 0 , 5"; 
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4, "커피점/카페");
					  pstmt.setString(5, "제과제빵떡케익"); 
					  pstmt.setString(5, "음/식료품소매");
					  pstmt.setString(7, "커피점/카페"); 
					  pstmt.setString(8, "제과제빵떡케익");
					  pstmt.setString(9, "음/식료품소매"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "커피점/카페");
					  pstmt.setString(14, "제과제빵떡케익"); 
					  pstmt.setString(15, "음/식료품소매");
					  pstmt.setString(16, "커피점/카페"); 
					  pstmt.setString(17, "제과제빵떡케익");
					  pstmt.setString(18, "음/식료품소매"); 
					  break; 
					  
				  case 2: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM incheon_st "
						  		+ "WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
							  		+ "UNION "
							  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
							  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
							  		+ "GROUP BY lng, lat "
							  		+ "HAVING distance < 5) "
							  		+ "ORDER BY distance LIMIT 0 , 5"; 
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4, "한식");
					  pstmt.setString(5, "분식"); 
					  pstmt.setString(6, "중식"); 
					  pstmt.setString(7, "일식/수산물"); 
					  pstmt.setString(8, "별식/퓨전요리"); 
					  pstmt.setString(9, "닭/오리요리");
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "한식");
					  pstmt.setString(14, "분식"); 
					  pstmt.setString(15, "중식"); 
					  pstmt.setString(16, "일식/수산물"); 
					  pstmt.setString(17, "별식/퓨전요리"); 
					  pstmt.setString(18, "닭/오리요리");
					  break; 
				  
				  case 3: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
					  		+ "FROM incheon_st "
					  		+ "WHERE category IN (?,?,?,?,?,?) "
					  		+ "GROUP BY lng, lat "
					  		+ "HAVING distance < 5) "
						  		+ "UNION "
						  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
						  		+ "ORDER BY distance LIMIT 0 , 5"; 
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4,"학원-예능취미체육"); 
					  pstmt.setString(5, "스포츠/운동"); 
					  pstmt.setString(6, "실외운동시설");
					  pstmt.setString(7, "실내운동시설"); 
					  pstmt.setString(8, "실내운동시설");
					  pstmt.setString(9, "실내운동시설"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13,"학원-예능취미체육"); 
					  pstmt.setString(14, "스포츠/운동"); 
					  pstmt.setString(15, "실외운동시설");
					  pstmt.setString(16, "실내운동시설"); 
					  pstmt.setString(17, "실내운동시설");
					  pstmt.setString(18, "실내운동시설"); 
					  break; 
					  
				  case 4: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM incheon_st "
						  		+ "WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
							  		+ "UNION "
							  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
							  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
							  		+ "GROUP BY lng, lat "
							  		+ "HAVING distance < 5) "
							  		+ "ORDER BY distance LIMIT 0 , 5"; 
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4, "학원-음악미술무용"); 
					  pstmt.setString(5, "연극/영화/극장"); 
					  pstmt.setString(6, "책/서적/도서");
					  pstmt.setString(7, "도서관/독서실"); 
					  pstmt.setString(8, "도서관/독서실");
					  pstmt.setString(9, "도서관/독서실");
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "학원-음악미술무용"); 
					  pstmt.setString(14, "연극/영화/극장"); 
					  pstmt.setString(15, "책/서적/도서");
					  pstmt.setString(16, "도서관/독서실"); 
					  pstmt.setString(17, "도서관/독서실");
					  pstmt.setString(18, "도서관/독서실"); 
					  break; 
					  
				  case 5: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM incheon_st "
						  		+ "WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
							  		+ "UNION "
							  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
							  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
							  		+ "GROUP BY lng, lat "
							  		+ "HAVING distance < 5) "
							  		+ "ORDER BY distance LIMIT 0 , 5"; 
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4, "유흥주점");
					  pstmt.setString(5, "유흥주점"); 
					  pstmt.setString(6, "유흥주점"); 
					  pstmt.setString(7, "유흥주점"); 
					  pstmt.setString(8, "유흥주점"); 
					  pstmt.setString(9, "유흥주점"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "유흥주점");
					  pstmt.setString(14, "유흥주점"); 
					  pstmt.setString(15, "유흥주점"); 
					  pstmt.setString(16, "유흥주점"); 
					  pstmt.setString(17, "유흥주점"); 
					  pstmt.setString(18, "유흥주점"); 
					  break; 

				  case 6: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
						  		+ "FROM incheon_st "
						  		+ "WHERE category IN (?,?,?,?,?,?) "
						  		+ "GROUP BY lng, lat "
						  		+ "HAVING distance < 5) "
							  		+ "UNION "
							  		+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
							  		+ "FROM siheung_st WHERE category IN (?,?,?,?,?,?) "
							  		+ "GROUP BY lng, lat "
							  		+ "HAVING distance < 5) "
							  		+ "ORDER BY distance LIMIT 0 , 5";
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat); 
					  pstmt.setString(4, "PC/오락/당구/볼링등");
					  pstmt.setString(5, "취미/오락관련소매");
					  pstmt.setString(6, "놀이/여가/취미"); 
					  pstmt.setString(7, "놀이/여가/취미"); 
					  pstmt.setString(8, "놀이/여가/취미");
					  pstmt.setString(9, "놀이/여가/취미"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "PC/오락/당구/볼링등");
					  pstmt.setString(14, "취미/오락관련소매");
					  pstmt.setString(15, "놀이/여가/취미"); 
					  pstmt.setString(16, "놀이/여가/취미"); 
					  pstmt.setString(17, "놀이/여가/취미");
					  pstmt.setString(18, "놀이/여가/취미"); 
					  break; 
					  
				  default: 
					  sql = "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
					  		+ "FROM incheon_st "
					  		+ "HAVING distance < 5) "
								+ "UNION "
								+ "(SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance "
								+ "FROM siheung_st "
							  	+ "GROUP BY lng, lat "
							  	+ "HAVING distance < 5) "
							  	+ "ORDER BY distance LIMIT 0 , 5";
					  
					  pstmt = conn.prepareStatement(sql); 
					  pstmt.setFloat(1, lat);
					  pstmt.setFloat(2, lng); 
					  pstmt.setFloat(3, lat);
					  pstmt.setFloat(4, lat);
					  pstmt.setFloat(5, lng); 
					  pstmt.setFloat(6, lat);
					  break; 
			}
			 
			/*
			 * sql =
			 * "SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance FROM incheon_st HAVING distance < 5 ORDER BY distance LIMIT 0 , 5"
			 * ; pstmt = conn.prepareStatement(sql); pstmt.setFloat(1, lat);
			 * pstmt.setFloat(2, lng); pstmt.setFloat(3, lat); rs = pstmt.executeQuery();
			 */
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("name"));
				list.add(rs.getString("lng"));
				list.add(rs.getString("lat"));
				list.add(rs.getString("distance"));
			}
			// conn.close();
		} catch (SQLException e) {

			System.out.println("조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}
		
		return list;

	}
	
	public List<String> category(String category,  int page) {
		List<String> list = new ArrayList<>();
		try {

			page*=15;
			 switch (category) {
             case "share": //모든 옷 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "카페&디저트": //카테고리 top 조회
            	  sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (커피점/카페,제과제빵떡케익,음/식료품소매) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "음식": //카테고리 bottom 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (한식,분식,중식,일식/수산물,별식/퓨전요리,닭/오리요리) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "스포츠": //카테고리 suit 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (학원-예능취미체육,스포츠/운동,실외운동시설,실내운동시설) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "독서&연극": //카테고리 outer 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (학원-음악미술무용,연극/영화/극장,책/서적/도서,도서관/독서실) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break;
				  
             case "포차": //카테고리 shoes 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (유흥주점) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break;
				
             case "놀거리": //카테고리 bag 조회
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (PC/오락/당구/볼링등,취미/오락관련소매,놀이/여가/취미) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break;
			 }
			  
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("idx"));
				list.add(rs.getString("name"));
				list.add(rs.getString("category"));
				list.add(rs.getString("star"));
				list.add(rs.getString("adress"));
				list.add(rs.getString("review"));

			}
			// conn.close();
		} catch (SQLException e) {

			System.out.println("조회에 실패했습니다.");
			e.printStackTrace();

		} finally {

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}
		
		return list;

	}
}
