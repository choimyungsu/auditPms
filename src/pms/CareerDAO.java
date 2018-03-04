package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CareerDAO {

private Connection conn;
	
	private ResultSet rs;
	
	public CareerDAO() {
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
		String SQL = "SELECT careerID from career ORDER BY careerID DESC";
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
		String SQL = "INSERT INTO career VALUES (? ,?, ?, ?, ?, ?)";
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
	public ArrayList<Career> getList(int pageNumber, String userid){
		
		String SQL = "SELECT * FROM career WHERE careerID < ? and userid = ? ORDER BY careerID ASC LIMIT 20";
		ArrayList<Career> list = new ArrayList<Career>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 20);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Career career = new Career();
				
				career.setCareerid(rs.getInt(1));
				career.setUserid(rs.getString(2));
				career.setPeriod(rs.getString(3));
				career.setCareerdesc(rs.getString(4));
				career.setTask(rs.getString(5));
				career.setSimilarcareer(rs.getString(6));
				
				list.add(career);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}
	//페이징 처리를 위한 함수
	public boolean nextPage(int pageNumber, String userID) {
		String SQL = "SELECT * FROM career WHERE careerID < ? and userid = ?  ORDER BY careerID ASC LIMIT 10";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			int aaa = getNext() - (pageNumber -1) * 10;
			System.out.println("nextPage: 인자값은" + aaa);
			pstmt.setInt(1, aaa);
			pstmt.setString(2, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;//
	}
	
	public Career getCareer(int careerID) {
		
		String SQL = "SELECT * FROM career WHERE careerID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, careerID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Career career = new Career();
				
				career.setCareerid(rs.getInt(1));
				career.setUserid(rs.getString(2));
				career.setPeriod(rs.getString(3));
				career.setCareerdesc(rs.getString(4));
				career.setTask(rs.getString(5));
				career.setSimilarcareer(rs.getString(6));
				
				return career;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;//
	}
	
	public int update(int careerID, String bbsTitle, String bbsContents) {
		
		String SQL = "UPDATE career SET bbsTitle = ? , bbsContent = ? WHERE careerID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContents);
			pstmt.setInt(3, careerID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
	
	public int delete(int bbsID) {
		
		String SQL = "UPDATE BBS SET bbsAvailable = 0  WHERE bbsID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, bbsID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
}
