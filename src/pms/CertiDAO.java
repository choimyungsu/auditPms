package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CertiDAO {

private Connection conn;
	
	private ResultSet rs;
	
	public CertiDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs";
			String dbID ="root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";//데이터베이스 오류
	}
	public int getNext() {
		String SQL = "SELECT CertiID from certi ORDER BY CertiID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;// 첫번째 게시물인 경우
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
	}	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO certi VALUES (? ,?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
	}
	public ArrayList<Certi> getList(int pageNumber, String userid){
		
		String SQL = "SELECT * FROM Certi WHERE certiID < ? and userid = ? ORDER BY certiID ASC LIMIT 20";
		ArrayList<Certi> list = new ArrayList<Certi>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 20);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Certi certi = new Certi();
				
				certi.setCertiid(rs.getInt(1));
				certi.setUserid(rs.getString(2));
				certi.setCertiname(rs.getString(3));
				certi.setIssuer(rs.getString(4));
				certi.setCertidivision(rs.getString(5));
				certi.setCertifield(rs.getString(6));
				
				list.add(certi);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}
	//페이징 처리를 위한 함수
	public boolean nextPage(int pageNumber,String userid) {
		String SQL = "SELECT * FROM certi WHERE certiID < ? and userID = ? ORDER BY certiID ASC LIMIT 20";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			int aaa = getNext() - (pageNumber -1) * 20;
			System.out.println("nextPage: 인자값은" + aaa);
			pstmt.setInt(1, aaa);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;//
	}
	
	public Certi getCerti(int certiID) {
		
		String SQL = "SELECT * FROM certi WHERE certiID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, certiID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Certi certi = new Certi();
				
				certi.setCertiid(rs.getInt(1));
				certi.setUserid(rs.getString(2));
				certi.setCertiname(rs.getString(3));
				certi.setIssuer(rs.getString(4));
				certi.setCertidivision(rs.getString(5));
				certi.setCertifield(rs.getString(6));
				
				return certi;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;//
	}
	
	public int update(int certiID, String bbsTitle, String bbsContents) {
		
		String SQL = "UPDATE certi SET bbsTitle = ? , bbsContent = ? WHERE certiID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContents);
			pstmt.setInt(3, certiID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
	
	public int delete(int certiID) {
		
		String SQL = "UPDATE certi SET bbsAvailable = 0  WHERE certiID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, certiID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
}
