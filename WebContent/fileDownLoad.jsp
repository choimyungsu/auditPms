
<%@page import="file.FileDTO"%>
<%@page import="file.FileDAO"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP file upload</title>
</head>
<body>
<%
/*	
//String directory = application.getRealPath("/upload/");// 취약점 존재
	String directory = "C:/JSP/upload";
	String files[] = new File(directory).list();
	
	for(String file : files){
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file="+java.net.URLEncoder.encode(file, "UTF-8")+ "\">" +file+ "</a><br>");
	}
*/
//데이터 베이스에서 가져오기.
 	ArrayList<FileDTO> fileList = new FileDAO().getList();

    for (FileDTO file : fileList){
    	out.write("<a href=\""+ request.getContextPath() + "/downloadAction?file=" + 
    			java.net.URLEncoder.encode(file.getFileRealName(),"UTF-8") + "\">" +
    			file.getFileName() + "(다운로드 횟수: " + file.getDownloadCount() + ")</a><br>");
    }
%>


</body>
</html>