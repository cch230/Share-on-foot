package shareonfoot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	

	String jdbcUrl = "jdbc:mysql://49.50.172.215:3307/shareonfoot"; // MySQL 계정
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

			conn = DriverManager.getConnection(
					jdbcUrl, dbId,
					dbPw);

		} catch (SQLException e) {

			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("클래스가 없습니다.");
			e.printStackTrace();

		}
	}

	
	 
	
	
	public List<Location> recommend(Float lng, Float lat) {
		List<Location> list = new ArrayList<Location>();
		try {
			
			sql = "SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance FROM TEST_TABLE HAVING distance < 5 ORDER BY distance LIMIT 0 , 5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, lat);
			pstmt.setFloat(2, lng);
			pstmt.setFloat(3, lat);
			rs = pstmt.executeQuery();
			Location location = new Location();
			while (rs.next()) {
				location.setname(rs.getString("name"));
				location.setlng(rs.getString("lng"));
				location.setlat(rs.getString("lat"));
				list.add(location);
			} 
			conn.close();
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
