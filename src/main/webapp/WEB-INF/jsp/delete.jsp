<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// セッションスコープからインスタンスを取得
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
	<h2><p>『<%= fDiaryId %>の記録』を削除しました</p></h2>
<br>
</body>
</html>