package shareonfoot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.Location;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	

	String jdbcUrl = "jdbc:mysql://49.50.172.215:3307/shareonfoot"; // MySQL ����
	String dbId = "root"; // MySQL ����
	String dbPw = "Cch951753!"; // ��й�ȣ
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "a";
	String returns2 = "";

	// �����ͺ��̽��� ����ϱ� ���� �ڵ尡 ����ִ� �޼���
	private ConnectDB() {

		try {
			System.out.println("����");

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(
					jdbcUrl, dbId,
					dbPw);
			
		} catch (SQLException e) {

			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ������ �����ϴ�.");
			e.printStackTrace();

		}
	}

	
	 
	
	
//	public List<Location> recommend(Float lng, Float lat) {
//		List<Location> list = new ArrayList<Location>();
//		try {
//			
//			sql = "SELECT name,lng,lat, ( 6371 * acos( cos( radians(?) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(?) ) + sin( radians(?) ) * sin( radians( lat ) ) ) ) AS distance FROM TEST_TABLE HAVING distance < 5 ORDER BY distance LIMIT 0 , 5";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setFloat(1, lat);
//			pstmt.setFloat(2, lng);
//			pstmt.setFloat(3, lat);
//			rs = pstmt.executeQuery();
//			Location location = new Location();
//			while (rs.next()) {
//				location.setname(rs.getString("name"));
//				location.setlng(rs.getString("lng"));
//				location.setlat(rs.getString("lat"));
//				location.setdst(rs.getString("dst"));
//
//				list.add(location);
//			} 
//			conn.close();
//		} catch (SQLException e) {
//
//			System.out.println("��ȸ�� �����߽��ϴ�.");
//			e.printStackTrace();
//
//		} finally {
//
//			if (pstmt != null)
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				
//				}
//			if (pstmt2 != null)
//				try {
//					pstmt2.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//		}
//		return list;
//
//	}
			
	 public String InsertMember(String id, String password, String nickname, String gender, String birth) {
		 
		 sql="INSERT into member(id, password, name, gender, birth) VALUES(?,?,?,?,?)";
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			 
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1, id);
			 pstmt.setString(2, password);
			 pstmt.setString(3, nickname);
			 pstmt.setString(4, gender);
			 pstmt.setString(5, birth);
			 pstmt.executeUpdate();
			 
			 returns = "ok";
			 			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 } finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		 
		 return returns;
	 }

	public String LoginMember(String id, String password) {
		
		 sql="SELECT id, password from member where id=? and password=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("id").equals(id) && rs.getString("password").equals(password)) {
					returns2 = "true";// 로그인 가능
				} else {
					returns2 = "false"; // 로그인 실패
				}
			} else {
				returns2 = "noId"; // 아이디 또는 비밀번호 존재 X
			}
	
		} catch (Exception e) {
	
		} finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		return returns2;
		}
	}
