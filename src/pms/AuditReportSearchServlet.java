package pms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuditReportSearchServlet
 */
@WebServlet("/AuditReportSearchServlet")
public class AuditReportSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hteml;charset=UTF-8");
		String auditName = request.getParameter("auditName");
		response.getWriter().write(getAuditReportJSON(auditName));
	}
	
	// [ 특수문자 처리가 필요함..
	public String getAuditReportJSON(String auditName) {
		if(auditName == null) auditName="";
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\" : [");
		AuditReportDAO auditReportDAO = new AuditReportDAO();
		ArrayList<AuditReport> auditReportList = auditReportDAO.search(auditName);
		for(int i=0; i<auditReportList.size(); i++) {
			result.append("[{\"value\": \"" +auditReportList.get(i).getSeq() + "\"},");
			//result.append("{\"value\":\"" +auditReportList.get(i).getAuditreportid() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getAuditname() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getPlaceauditdate() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getAuditors() + "\"},");		
			result.append("{\"value\": \"" +auditReportList.get(i).getAuditfield() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getContractauditdate() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getMainclient() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getDevelopcompany() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getAuditcost() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getDevelopcost() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getDevelopmethod() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getBizoverview() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getBizscope() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getBizperiod() + "\"},");
			result.append("{\"value\": \"" +auditReportList.get(i).getMaintechnology() + "\"}],");
			
		}
		result.append("]}");
		System.out.println(result.toString());
		return result.toString();
					
		}
		
}
