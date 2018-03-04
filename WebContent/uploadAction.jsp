<%@ page import="file.FileDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>

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

//String directory = application.getRealPath("/upload/");// 레이스 컨디션 보안 취약점 있음
//String directory = application.getRealPath("/images/");// 레이스 컨디션 보안 취약점 있음
String directory = "C:\\JSP\\projects\\auditPms\\WebContent\\userImages\\";
int maxSize = 1024*1024*100;
String encoding ="UTF-8";

MultipartRequest multipartRequest = new MultipartRequest(request,directory,maxSize,encoding,
		new DefaultFileRenamePolicy());

Enumeration fileNames = multipartRequest.getFileNames();

while(fileNames.hasMoreElements()){
	
	String parameter = (String) fileNames.nextElement();
	String fileName = multipartRequest.getOriginalFileName(parameter);
	String fileRealName = multipartRequest.getFilesystemName(parameter);

	if(fileName == null) continue;//중간에 문서를 올리지 않은 항목에 대한 처리 
	
	if(!fileName.endsWith(".doc") && !fileName.endsWith(".hwp") && !fileName.endsWith(".pdf") && !fileName.endsWith(".xls")  && !fileName.endsWith(".ppt") && !fileName.endsWith(".jpg"))
	{ // 시큐어 코딩 적용( 업로드 확장자 지정)
		File file = new File(directory+fileRealName);
		file.delete();
		out.write("올릴수 없는 파일입니다.");
		
	} else {

		new FileDAO().upload(fileName,fileRealName);
		out.write("fileNAme="+ fileName +", fileRealName="+fileRealName);
		
	}
	
	
}




%>
</body>
</html>