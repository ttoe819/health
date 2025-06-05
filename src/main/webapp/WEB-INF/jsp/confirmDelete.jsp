<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//セッションスコープからインスタンスを取得
String fDiaryId = (String)session.getAttribute("fDiaryId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>健康第一</title>
</head>
<body>
<p style="text-align: right"><font color = "#000000"><a href="Home">ホーム</a></font>
 &nbsp;
 <font color = "#000000"><a href="ConfirmLogout">ログアウト</a></font></p>
<h1>
	<p style="background-color:#A9D08E">
    <font color = "#000000">『健康第一』</font></p>
</h1>

	<h2>次の記録を削除しますか？</h2>
	<h3><p><%= fDiaryId %>の記録</p></h3>	
		
	<table>
	<tr>
	<td>
	<form action="Delete" method="post">
	<input type="submit" value="はい"></form>
	</td>
	<td>
	</td>
	<td>
	<form action="Home" method="get">
	<input type="submit" value="いいえ"></form>
	</td>
	</tr>
	</table>	
</body>
</html>