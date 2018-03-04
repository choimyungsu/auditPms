package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AuditReportDAO {
	
private Connection conn;
	
	private ResultSet rs;
	
	public AuditReportDAO() {
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
		String SQL = "SELECT auditReportID from auditReport ORDER BY auditReportID DESC";
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
		String SQL = "INSERT INTO auditReport VALUES (? ,?, ?, ?, ?, ?)";
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
	public ArrayList<AuditReport> getList(int pageNumber){
		
		String SQL = "SELECT * FROM auditReport WHERE auditReportID < ?  ORDER BY auditReportID DESC LIMIT 20";
		ArrayList<AuditReport> list = new ArrayList<AuditReport>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 20);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditReport auditReport = new AuditReport();
				
				auditReport.setAuditreportid(rs.getInt(1));
				auditReport.setProjectid(rs.getInt(2));
				auditReport.setSeq(rs.getString(3));
				auditReport.setAuditname(rs.getString(4));
				auditReport.setPlaceauditdate(rs.getString(5));
				auditReport.setAuditors(rs.getString(6));
				auditReport.setAuditfield(rs.getString(7));
				auditReport.setContractauditdate(rs.getString(8));
				auditReport.setMainclient(rs.getString(9));
				auditReport.setDevelopcompany(rs.getString(10));
				auditReport.setAuditcost(rs.getString(11));
				auditReport.setDevelopcost(rs.getString(12));
				auditReport.setDevelopmethod(rs.getString(13));
				auditReport.setBizoverview(rs.getString(14));
				auditReport.setBizscope(rs.getString(15));
				auditReport.setBizperiod(rs.getString(16));
				auditReport.setMaintechnology(rs.getString(17));
				auditReport.setAuditavailable(rs.getInt(18));
				auditReport.setAuditstartdate(rs.getTimestamp(19));
				auditReport.setAuditenddate(rs.getTimestamp(20));
				auditReport.setMainauditor(rs.getString(21));
				auditReport.setAuditstep(rs.getString(22));
				auditReport.setCreatedate(rs.getTimestamp(23));
				auditReport.setUpdatedate(rs.getTimestamp(24));
				
				list.add(auditReport);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}
	
	
public ArrayList<AuditReport> search(String  auditName){
		
		String SQL = "SELECT * FROM auditReport WHERE auditName like ?  ORDER BY auditReportID DESC LIMIT 20";
		ArrayList<AuditReport> list = new ArrayList<AuditReport>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + auditName + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditReport auditReport = new AuditReport();
				
				auditReport.setAuditreportid(rs.getInt(1));
				auditReport.setProjectid(rs.getInt(2));
				auditReport.setSeq(rs.getString(3));
				auditReport.setAuditname(rs.getString(4));
				auditReport.setPlaceauditdate(rs.getString(5));
				auditReport.setAuditors(rs.getString(6));
				auditReport.setAuditfield(rs.getString(7));
				auditReport.setContractauditdate(rs.getString(8));
				auditReport.setMainclient(rs.getString(9));
				auditReport.setDevelopcompany(rs.getString(10));
				auditReport.setAuditcost(rs.getString(11));
				auditReport.setDevelopcost(rs.getString(12));
				auditReport.setDevelopmethod(rs.getString(13));
				auditReport.setBizoverview(rs.getString(14));
				auditReport.setBizscope(rs.getString(15));
				auditReport.setBizperiod(rs.getString(16));
				auditReport.setMaintechnology(rs.getString(17));
				auditReport.setAuditavailable(rs.getInt(18));
				auditReport.setAuditstartdate(rs.getTimestamp(19));
				auditReport.setAuditenddate(rs.getTimestamp(20));
				auditReport.setMainauditor(rs.getString(21));
				auditReport.setAuditstep(rs.getString(22));
				auditReport.setCreatedate(rs.getTimestamp(23));
				auditReport.setUpdatedate(rs.getTimestamp(24));
				
				list.add(auditReport);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}
	
	//페이징 처리를 위한 함수
	public boolean nextPage(int pageNumber) {
		String SQL = "SELECT * FROM auditReport WHERE auditReportID < ?  ORDER BY auditReportID DESC LIMIT 20";
		
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
	
	// 한건 가져오기
	public AuditReport getAuditReport(int auditReportID) {
		
		String SQL = "SELECT * FROM auditReport WHERE auditReportID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, auditReportID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AuditReport auditReport = new AuditReport();
				
				auditReport.setAuditreportid(rs.getInt(1));
				auditReport.setProjectid(rs.getInt(2));
				auditReport.setSeq(rs.getString(3));
				auditReport.setAuditname(rs.getString(4));
				auditReport.setPlaceauditdate(rs.getString(5));
				auditReport.setAuditors(rs.getString(6));
				auditReport.setAuditfield(rs.getString(7));
				auditReport.setContractauditdate(rs.getString(8));
				auditReport.setMainclient(rs.getString(9));
				auditReport.setDevelopcompany(rs.getString(10));
				auditReport.setAuditcost(rs.getString(11));
				auditReport.setDevelopcost(rs.getString(12));
				auditReport.setDevelopmethod(rs.getString(13));
				auditReport.setBizoverview(rs.getString(14));
				auditReport.setBizscope(rs.getString(15));
				auditReport.setBizperiod(rs.getString(16));
				auditReport.setMaintechnology(rs.getString(17));
				auditReport.setAuditavailable(rs.getInt(18));
				auditReport.setAuditstartdate(rs.getTimestamp(19));
				auditReport.setAuditenddate(rs.getTimestamp(20));
				auditReport.setMainauditor(rs.getString(21));
				auditReport.setAuditstep(rs.getString(22));
				auditReport.setCreatedate(rs.getTimestamp(23));
				auditReport.setUpdatedate(rs.getTimestamp(24));
				
				return auditReport;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;//
	}
	
	
	public int update(int auditReportID, String bbsTitle, String bbsContents) {
		
		String SQL = "UPDATE auditReport SET bbsTitle = ? , bbsContent = ? WHERE auditReportID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContents);
			pstmt.setInt(3, auditReportID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}
	
	
	public int delete(int auditReportID) {
		
		String SQL = "UPDATE auditReport SET bbsAvailable = 0  WHERE auditReportID = ?";
				
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, auditReportID);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//데이터베이스 오류
		
	}

}
