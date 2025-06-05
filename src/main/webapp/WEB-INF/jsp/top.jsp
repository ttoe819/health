<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//エラーメッセージ取得：リクエストスコープ
String msg = (String)request.getAttribute("msg");
if (msg == null) {
	msg = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>健康第一</title>
</head>
<body>
<p><br></p>
<h1>
	<p style="background-color:#A9D08E">
    <font color = "#000000">『健康第一』へようこそ！</font></p>
</h1>    
<%= msg %>
<form action="Top" method="post">
<table>
<tr>
<th align="right">
ログインアカウント名：
</th>
<td>
<input type="text" name="name">
</td>
</tr>
<tr>
<th align="right">
パスワード：
</th>
<td>
<input type="password" name="pass">
</td>
</tr>
</table>
<br>
<input type="submit" value="ログイン">
</form>
<br>
<a href="RegisterAccount">アカウント登録はこちら</a>
</body>
</html>