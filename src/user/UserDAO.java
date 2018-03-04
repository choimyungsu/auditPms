package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import file.FileDTO;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
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
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				 if(rs.getString(1).equals(userPassword)) {
					 return 1;//로그인 성공
				 }else{
					 return 0;//비밀번호 불일치
				 }
			}
			return -1;//아이디가 없음
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -2;// 데이터 베이스 오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getUserDept());
			pstmt.setString(7, user.getUserLevel());
			pstmt.setInt(8, 0);//setString(7, user.getUserLevel());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1 ; // 데이텁이스 오류
	}
	
public ArrayList<User> search(String  userName){
		
		String SQL = "SELECT * FROM user WHERE userName like ?  ";
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + userName + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				
				user.setUserID(rs.getString(1));
				user.setUserPassword(rs.getString(2));
				user.setUserName(rs.getString(3));
				user.setUserGender(rs.getString(4));
				user.setUserEmail(rs.getString(5));
				user.setUserDept(rs.getString(6));
				user.setUserLevel(rs.getString(7));
				user.setAvailable(rs.getInt(8));

				list.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;//
	}


public User userDetail(String  userID){
	
	String SQL = "SELECT * FROM user WHERE userID = ?  ";
	User user = new User();
	
	try {
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, userID);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			
			user.setUserID(rs.getString(1));
			user.setUserPassword(rs.getString(2));
			user.setUserName(rs.getString(3));
			user.setUserGender(rs.getString(4));
			user.setUserEmail(rs.getString(5));
			user.setUserDept(rs.getString(6));
			user.setUserLevel(rs.getString(7));
			user.setAvailable(rs.getInt(8));

		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return user;//
}


//한건 가져오기
	public FileDTO getFileInformation(String objectLink, String objectLinkPK) {
		
		String SQL = " SELECT * FROM File WHERE objectLink = ?  and objectLinkPK = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, objectLink);
			pstmt.setString(2, objectLinkPK);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FileDTO fileDTO = new FileDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7));
				
				/*fileDTO.setFileID(rs.getInt(1));
				fileDTO.setFileName(rs.getString(2));
				fileDTO.setFileRealName(rs.getString(3));
				fileDTO.setDownloadCount(rs.getInt(4));
				fileDTO.setFilePath(rs.getString(5));
				fileDTO.setObjectLink(rs.getString(6));
				fileDTO.setObjectLinkPK(rs.getString(7));
		*/
				
				return fileDTO;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;//
	}

	
}