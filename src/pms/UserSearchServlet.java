package pms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;
import user.UserDAO;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hteml;charset=UTF-8");
		String userName = request.getParameter("userName");
		response.getWriter().write(getUserJSON(userName));
	}
	
	// [ 특수문자 처리가 필요함..
	public String getUserJSON(String userName) {
		if(userName == null) userName="";
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\" : [");
		UserDAO userDAO = new UserDAO();
		ArrayList<User> userList = userDAO.search(userName);
		for(int i=0; i<userList.size(); i++) {
			result.append("[{\"value\": \"" +userList.get(i).getUserID() + "\"},");
			result.append("{\"value\": \"" +userList.get(i).getUserName() + "\"},");
			result.append("{\"value\": \"" +userList.get(i).getUserGender() + "\"},");
			result.append("{\"value\": \"" +userList.get(i).getUserEmail() + "\"}],");
			//result.append("{\"value\": \" <a href=\"auditorHistory.jsp?searchUserID=" +userList.get(i).getUserID() + "\"보기</a>}],");
			
		}
		result.append("]}");
		//System.out.println("result ="+ result.toString());
		return result.toString();
					
		}
}
