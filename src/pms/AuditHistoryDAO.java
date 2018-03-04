package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AuditHistoryDAO {
	
	private Connection conn;
	
	private ResultSet rs;
	
	public AuditHistoryDAO() {
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
		String SQL = "SELECT auditHistoryID from audithistory ORDER BY auditHistoryID DESC";
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
		String SQL = "INSERT INTO audithistory VALUES (? ,?, ?, ?, ?, ?)";
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
	
	public ArrayList<AuditHistory> getList(int pageNumber, String userid){
		
		String SQL = "SELECT * FROM audithistory WHERE auditHistoryID < ? and userid = ? ORDER BY auditHistoryID DESC LIMIT 30";
		ArrayList<AuditHistory> list = new ArrayList<AuditHistory>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 30);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditHistory auditHistory = new AuditHistory();
				auditHistory.setAudithistoryid(rs.getInt(1));
				auditHistory.setUserid(rs.getString(2));
				auditHistory.setProjectid(rs.getInt(3));
				auditHistory.setAudityearmonth(rs.getString(4));
				auditHistory.setAuditname(rs.getString(5));
				auditHistory.setMainclient(rs.getString(6));
				auditHistory.setMaindivision(rs.getString(7));
				auditHistory.setAuditfield(rs.getString(8));
				auditHistory.setAuditrole(rs.getString(9));
				auditHistory.setJoinrate(rs.getString(10));
				list.add(auditHistory);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}
	//페이징 처리를 위한 함수
	public boolean nextPage(int pageNumber, String userid) {
		String SQL = "SELECT * FROM BBS WHERE auditHistoryID < ?  ORDER BY bbsID DESC LIMIT 30";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			int aaa = getNext() - (pageNumber -1) * 30;
			System.out.println("nextPage: 인자값은" + aaa);
			
			pstmt.setInt(1, aaa);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;//
	}
	
	public AuditHistory getAuditHistory(int auditHistoryID) {
		
		String SQL = "SELECT * FROM audithistory WHERE auditHistoryID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, auditHistoryID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditHistory bbs = new AuditHistory();
				/*
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				*/
				return bbs;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;//
	}
	
	public int update(int auditHistoryID, String bbsTitle, String bbsContents) {
		
		String SQL = "UPDATE audithistory SET bbsTitle = ? , bbsContent = ? WHERE auditHistoryID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContents);
			pstmt.setInt(3, auditHistoryID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
	
	public int delete(int auditHistoryID) {
		
		String SQL = "UPDATE audithistory SET bbsAvailable = 0  WHERE auditHistoryID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, auditHistoryID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
	
public ArrayList<AuditGroupCount> getAuditFieldGroupList(String userid){
		
		String SQL = " select auditField, count(auditHistoryID) as count from audithistory "
				+ " where userID = ? "
				+ " group BY auditField " ;
				
		ArrayList<AuditGroupCount> list = new ArrayList<AuditGroupCount>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditGroupCount  auditGroupCount = new AuditGroupCount();
				auditGroupCount.setAuditField(rs.getString(1));
				auditGroupCount.setCount(rs.getString(2));

				list.add(auditGroupCount);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}


// [ 특수문자 처리가 필요함..
public String getAuditFieldGroupJSON(String userid) {
	if(userid == null) userid="";
	StringBuffer result = new StringBuffer("");
	//result.append("[");
	//AuditReportDAO auditReportDAO = new AuditReportDAO();
	ArrayList<AuditGroupCount> auditGroupCount = this.getAuditFieldGroupList(userid);
	for(int i=0; i<auditGroupCount.size(); i++) {
		result.append("[\"" +auditGroupCount.get(i).getAuditField() + "\",");
		result.append( auditGroupCount.get(i).getCount() + ",\"white blue\"],");
	}
	//result.append("]");
	//System.out.println(result.toString());
	return result.toString();
		

	
	}

	

}
