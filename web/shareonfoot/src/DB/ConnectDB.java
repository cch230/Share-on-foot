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
																						// MySQL ����
	String dbId = "root"; // MySQL ����
	String dbPw = "Cch951753!"; // ��й�ȣ
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "a";

	// �����ͺ��̽��� ����ϱ� ���� �ڵ尡 ����ִ� �޼���
	private ConnectDB() {

		try {
			System.out.println("����");

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);

		} catch (SQLException e) {

			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ������ �����ϴ�.");
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
					  pstmt.setString(4, "Ŀ����/ī��");
					  pstmt.setString(5, "��������������"); 
					  pstmt.setString(5, "��/�ķ�ǰ�Ҹ�");
					  pstmt.setString(7, "Ŀ����/ī��"); 
					  pstmt.setString(8, "��������������");
					  pstmt.setString(9, "��/�ķ�ǰ�Ҹ�"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "Ŀ����/ī��");
					  pstmt.setString(14, "��������������"); 
					  pstmt.setString(15, "��/�ķ�ǰ�Ҹ�");
					  pstmt.setString(16, "Ŀ����/ī��"); 
					  pstmt.setString(17, "��������������");
					  pstmt.setString(18, "��/�ķ�ǰ�Ҹ�"); 
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
					  pstmt.setString(4, "�ѽ�");
					  pstmt.setString(5, "�н�"); 
					  pstmt.setString(6, "�߽�"); 
					  pstmt.setString(7, "�Ͻ�/���깰"); 
					  pstmt.setString(8, "����/ǻ���丮"); 
					  pstmt.setString(9, "��/�����丮");
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "�ѽ�");
					  pstmt.setString(14, "�н�"); 
					  pstmt.setString(15, "�߽�"); 
					  pstmt.setString(16, "�Ͻ�/���깰"); 
					  pstmt.setString(17, "����/ǻ���丮"); 
					  pstmt.setString(18, "��/�����丮");
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
					  pstmt.setString(4,"�п�-�������ü��"); 
					  pstmt.setString(5, "������/�"); 
					  pstmt.setString(6, "�ǿܿ�ü�");
					  pstmt.setString(7, "�ǳ���ü�"); 
					  pstmt.setString(8, "�ǳ���ü�");
					  pstmt.setString(9, "�ǳ���ü�"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13,"�п�-�������ü��"); 
					  pstmt.setString(14, "������/�"); 
					  pstmt.setString(15, "�ǿܿ�ü�");
					  pstmt.setString(16, "�ǳ���ü�"); 
					  pstmt.setString(17, "�ǳ���ü�");
					  pstmt.setString(18, "�ǳ���ü�"); 
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
					  pstmt.setString(4, "�п�-���ǹ̼�����"); 
					  pstmt.setString(5, "����/��ȭ/����"); 
					  pstmt.setString(6, "å/����/����");
					  pstmt.setString(7, "������/������"); 
					  pstmt.setString(8, "������/������");
					  pstmt.setString(9, "������/������");
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "�п�-���ǹ̼�����"); 
					  pstmt.setString(14, "����/��ȭ/����"); 
					  pstmt.setString(15, "å/����/����");
					  pstmt.setString(16, "������/������"); 
					  pstmt.setString(17, "������/������");
					  pstmt.setString(18, "������/������"); 
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
					  pstmt.setString(4, "��������");
					  pstmt.setString(5, "��������"); 
					  pstmt.setString(6, "��������"); 
					  pstmt.setString(7, "��������"); 
					  pstmt.setString(8, "��������"); 
					  pstmt.setString(9, "��������"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "��������");
					  pstmt.setString(14, "��������"); 
					  pstmt.setString(15, "��������"); 
					  pstmt.setString(16, "��������"); 
					  pstmt.setString(17, "��������"); 
					  pstmt.setString(18, "��������"); 
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
					  pstmt.setString(4, "PC/����/�籸/������");
					  pstmt.setString(5, "���/�������üҸ�");
					  pstmt.setString(6, "����/����/���"); 
					  pstmt.setString(7, "����/����/���"); 
					  pstmt.setString(8, "����/����/���");
					  pstmt.setString(9, "����/����/���"); 
					  pstmt.setFloat(10, lat);
					  pstmt.setFloat(11, lng); 
					  pstmt.setFloat(12, lat); 
					  pstmt.setString(13, "PC/����/�籸/������");
					  pstmt.setString(14, "���/�������üҸ�");
					  pstmt.setString(15, "����/����/���"); 
					  pstmt.setString(16, "����/����/���"); 
					  pstmt.setString(17, "����/����/���");
					  pstmt.setString(18, "����/����/���"); 
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

			System.out.println("��ȸ�� �����߽��ϴ�.");
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
             case "share": //��� �� ��ȸ
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
				  
             case "ī��&����Ʈ": //ī�װ� top ��ȸ
            	  sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (Ŀ����/ī��,��������������,��/�ķ�ǰ�Ҹ�) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "����": //ī�װ� bottom ��ȸ
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (�ѽ�,�н�,�߽�,�Ͻ�/���깰,����/ǻ���丮,��/�����丮) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "������": //ī�װ� suit ��ȸ
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (�п�-�������ü��,������/�,�ǿܿ�ü�,�ǳ���ü�) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break; 
				  
             case "����&����": //ī�װ� outer ��ȸ
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (�п�-���ǹ̼�����,����/��ȭ/����,å/����/����,������/������) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break;
				  
             case "����": //ī�װ� shoes ��ȸ
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (��������) "
						  +"ORDER BY star) a, (select @num:=0) b "
						  +"ORDER BY idx "
						  +"LIMIT ?,10";
				  pstmt = conn.prepareStatement(sql); 
				  pstmt.setInt(1, page);
				  break;
				
             case "��Ÿ�": //ī�װ� bag ��ȸ
            	 sql =	"SELECT " 
						  +"@num:=@num+1 AS idx, "
						  +"NAME, category, star, adress, review "  
						  +"FROM (SELECT * FROM review_join " 
						  +"WHERE category " 
						  +"IN (PC/����/�籸/������,���/�������üҸ�,����/����/���) "
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

			System.out.println("��ȸ�� �����߽��ϴ�.");
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
