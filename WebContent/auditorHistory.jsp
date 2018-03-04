<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="pms.AuditHistoryDAO" %>
<%@ page import="pms.AuditHistory" %>
<%@ page import="pms.CareerDAO" %>
<%@ page import="pms.Career" %>
<%@ page import="pms.CertiDAO" %>
<%@ page import="pms.Certi" %>
<%@ page import="file.FileDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="user.User" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">

<link rel="stylesheet" href="ttps://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">

<title>감리인 감리 이력 확인</title>

<jsp:include page="header.jsp" flush="true" />

<% 

	int pageNumber = 1;
	if(request.getParameter("pageNumber") !=null){
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}
	

	
	String searchUserID = null;
	if(request.getParameter("searchUserID") !=null){
		searchUserID = request.getParameter("searchUserID");
	}else{
		searchUserID = (String) session.getAttribute("userID"); // 찾는 사용자가 없을 경우 세션아이디로 찾음
	}
	
	if((String) session.getAttribute("userID") == null){ // 세션아이디가 없으면 메인페이지로 돌려보냄.
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert(' 로그인을 하세요.')");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}
	
	UserDAO userDAO = new UserDAO();
	User user = new User();
	user = userDAO.userDetail(searchUserID);
	String userName =user.getUserName();// user명 가져오기
	String userDept =user.getUserDept(); // 부서
	String userLevel = user.getUserLevel();// 직급
	//user 테이블에서 해당 사용자 이미지 가져오기
	FileDTO fileDTO = userDAO.getFileInformation("user",searchUserID);//
	String userImg = "default.gif"; //default img
	if(fileDTO == null){
		userImg = "default.gif";
	}else{
		userImg = fileDTO.getFileRealName();
	}

%>

    <script type="text/javascript">
      
      
      google.charts.load("current", {packages:['corechart']});
      google.charts.setOnLoadCallback(drawChart2);
      function drawChart2() {
        var data = google.visualization.arrayToDataTable([
/*           ["Element", "Density", { role: "style" } ],
          ["Copper", 8.94, "#b87333"],
          ["Silver", 10.49, "silver"],
          ["Gold", 19.30, "gold"],
          ["Platinum", 21.45, "color: #e5e4e2"] */
<%
		AuditHistoryDAO auditHistoryDAO = new AuditHistoryDAO();
		String auditFieldGroupJSON ="";
		auditFieldGroupJSON = auditHistoryDAO.getAuditFieldGroupJSON(searchUserID);
%>          
         
["Element", "Density", { role: "style" } ],
<%= auditFieldGroupJSON%>


        ]);

        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
                         { calc: "stringify",
                           sourceColumn: 1,
                           type: "string",
                           role: "annotation" },
                         2]);

        var options = {
          title: "분야별 감리 횟수",
          width: 900,
          height: 200,
          bar: {groupWidth: "90%"},
          legend: { position: "none" },
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
        chart.draw(view, options);
    }
      
    </script>
</head>
<body>




<div class="container">



	<a href="#demo1" class="btn btn-primary btn-pull" data-toggle="collapse" data-parent="#accordion"  aria-controls="demo1">감리원 인적사항 보기</a> &nbsp;
<!-- 	<a href="#demo2" class="btn btn-primary btn-pull" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="demo2">감리원 이력사항</a> &nbsp;
	<a href="#demo3" class="btn btn-primary btn-pull" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="demo3">감리원 사업유관경험</a> &nbsp;
	<a href="#demo4" class="btn btn-primary btn-pull" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="demo4">감리원 보유자격</a> &nbsp; -->
	<hr>

	<div id="demo1" class="collapse">
		<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<span class="glyphicon glyphicon-tags"></span>
								&nbsp;&nbsp; 감리원 인적사항
							</h3>	
						</div>
						<div class="panel-body" height="300">
							<div class="media">
								<div class="media-left">
									<a href="#"><img class="media-object" src="userImages/<%= userImg %>" width="100" height="120" alt="감리인 사진"></a>
								</div>
								<div class="media-body">
									<div class="col-sm-2"><h4 class="media-heading"><%= userName %></h4><br>
									<%= userDept %><br> <%= userLevel %></div>
									<div class="col-sm-8">
										<div id="columnchart_values" ></div>
									</div>
									
								</div>
							</div>
						</div>					
					</div>
				</div>
			</div>
		</div>
		
		<!-- 감리원 이력사항  -->
		<div id="demo2" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-pencil"></span>
						&nbsp;&nbsp; 감리원 이력사항
					</h3>	
				</div>
				<div class="panel-body">
<!-- class="table table-striped table-bordered" -->
						<table  class="display" style="text-align: center; border: 1px solid #dddddd" id="tableData1">
							<thead>
								<tr>
									<th style="background-color: #eeeeee; text-align: center;">번호</th>
									<th style="background-color: #eeeeee; text-align: center;">연도</th>
									<th style="background-color: #eeeeee; text-align: center;">사업명</th>
									<th style="background-color: #eeeeee; text-align: center;">주관기관</th>
									<th style="background-color: #eeeeee; text-align: center;">공공/민간</th>
									<th style="background-color: #eeeeee; text-align: center;">담당분야</th>
									<th style="background-color: #eeeeee; text-align: center;">역할</th>
									<th style="background-color: #eeeeee; text-align: center;">참여율</th>
								</tr>
							</thead>
							<tbody>
					<%
						//AuditHistoryDAO auditHistoryDAO2 = new AuditHistoryDAO();
						ArrayList<AuditHistory> list = auditHistoryDAO.getList(pageNumber, searchUserID);
						for(int i =0 ; i < list.size(); i++){
							//int j = i +1 ;
					%>
								<tr>
									<td><%= i+1 %></td>
									<td><%= list.get(i).getAudityearmonth() %></td>
									<td><%= list.get(i).getAuditname() %></td>
									<td><%= list.get(i).getMainclient() %></td>
									<td><%= list.get(i).getMaindivision() %></td>
									<td><%= list.get(i).getAuditfield() %></td>
									<td><%= list.get(i).getAuditrole() %></td>
									<td><%= list.get(i).getJoinrate() %></td>
								</tr>
						<%
						
						}
							
						%>
							</tbody>
							</table>
	
<!-- 					<a href = "#" class="btn btn-success btn-arraw-left">이전</a>
					<a href = "#" class="btn btn-success btn-arraw-left">다음</a> -->
	
					<a href="write.jsp" class="btn btn-primary pull-right">추가</a>
					<button id='DLtoExcel-1'  class="btn btn-danger">Excel 저장</button>

				</div>
			</div>
		</div>
		<script>
		 var $btnDLtoExcel = $('#DLtoExcel-1');
		    $btnDLtoExcel.on('click', function () {
		        $("#tableData1").excelexportjs({
		            containerid: "tableData1"
		            , datatype: 'table'
		        });

		    });
		    
		    $(document).ready(function() {
		        $('#tableData1').DataTable();
		    } );

		</script>
		<!-- 사업유관경험  -->
		<div id="demo3" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-tags"></span>
						&nbsp;&nbsp; 감리원 사업유관경험
					</h3>	
				</div>
				<div class="panel-body">
						<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd" id="tableData2">
							<thead>
								<tr>
									<th style="background-color: #eeeeee; text-align: center;">번호</th>
									<th style="background-color: #eeeeee; text-align: center;">사업개요</th>
									<th style="background-color: #eeeeee; text-align: center;">참여기간</th>
									<th style="background-color: #eeeeee; text-align: center;">담당업무</th>
									<th style="background-color: #eeeeee; text-align: center;">유사경력근거</th>
								</tr>
							</thead>
							<tbody>
					<%
						CareerDAO careerDAO = new CareerDAO();
						ArrayList<Career> list2 = careerDAO.getList(pageNumber, searchUserID);
						for(int i =0 ; i < list2.size(); i++){
							//int j = i +1 ;
					%>							
								<tr>
									
									<td><%= i+1 %></td>
									<td><%= list2.get(i).getCareerdesc() %></td>
									<td><%= list2.get(i).getPeriod() %></td>
									<td><%= list2.get(i).getTask() %></td>
									<td><%= list2.get(i).getSimilarcareer() %></td>

								</tr>
					<%
						
						}
							
					%>			

							</tbody>
							</table>
	
					<a href = "#" class="btn btn-success btn-arraw-left">이전</a>
	
					<a href = "#" class="btn btn-success btn-arraw-left">다음</a>
	
					<a href="write.jsp" class="btn btn-primary pull-right">추가</a>
					<button id='DLtoExcel-2'  class="btn btn-danger">Excel 저장</button>
					
				</div>
			</div>
		</div>
		<script>
		 var $btnDLtoExcel = $('#DLtoExcel-2');
		    $btnDLtoExcel.on('click', function () {
		        $("#tableData2").excelexportjs({
		            containerid: "tableData2"
		            , datatype: 'table'
		        });

		    });

		</script>
		<!-- 보유자격  -->
		<div id="demo4" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-tags"></span>
						&nbsp;&nbsp; 감리원 보유자격				</h3>	
				</div>
				<div class="panel-body">
						<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd" id="tableData3">
							<thead>
								<tr>
									<th style="background-color: #eeeeee; text-align: center;">번호</th>
									<th style="background-color: #eeeeee; text-align: center;">자격증명</th>
									<th style="background-color: #eeeeee; text-align: center;">발급처</th>
									<th style="background-color: #eeeeee; text-align: center;">구분</th>
									<th style="background-color: #eeeeee; text-align: center;">관련분야</th>
								</tr>
							</thead>
							<tbody>
					<%
						CertiDAO certiDAO = new CertiDAO();
						ArrayList<Certi> list3 = certiDAO.getList(pageNumber, searchUserID);
						for(int i =0 ; i < list3.size(); i++){
							//int j = i +1 ;
					%>								
								<tr>
									<td><%= i+1 %></td>														
									<td><%= list3.get(i).getCertiname() %></td>
									<td><%= list3.get(i).getIssuer() %></td>
									<td><%= list3.get(i).getCertidivision() %></td>
									<td><%= list3.get(i).getCertifield() %></td>
									
								</tr>

					<%
						
						}
							
					%>									

							</tbody>
							</table>
	
					<a href = "#" class="btn btn-success btn-arraw-left">이전</a>
	
					<a href = "#" class="btn btn-success btn-arraw-left">다음</a>
	
					<a href="write.jsp" class="btn btn-primary pull-right">추가</a>
					<button id='DLtoExcel-3'  class="btn btn-danger">Excel 저장</button>
					
				</div>
			</div>
		</div>
		<script>
		 var $btnDLtoExcel = $('#DLtoExcel-3');
		    $btnDLtoExcel.on('click', function () {
		        $("#tableData3").excelexportjs({
		            containerid: "tableData3"
		            , datatype: 'table'
		        });

		    });

		</script>
	
	</div>

<jsp:include page="footer.jsp" flush="true" />
</body>
</html>