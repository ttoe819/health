<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--msg取得：リクエストスコープ --%>
<%
//エラーメッセージ取得：リクエストスコープ
String msg1 = (String)request.getAttribute("msg1");
String msg2 = (String)request.getAttribute("msg2");
if (msg1 == null) {
	msg1 = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>健康第一</title>
</head>
<body>
<p style="text-align: right"><font color = "#000000"><a href="Top">トップページ</a></font>

<h1>
	<p style="background-color:#A9D08E">
    <font color = "#000000">アカウント登録</font></p>
</h1>    

<%= msg1 %>
<br><br>
<form action="RegisterAccount" method="post">
<table>
<tr>
<th align="right">
アカウント名：
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
<br><br>
<% if (msg2 == null) { %>
<input type="submit" value="登録">
<% } else {%>
<font color = "#000000"><a href="Top">トップページ</a></font></p>
<% } %>
</form>
<br><br>

<style>
.radius-table{
    border-radius: 10px;
    border-spacing: 0;
    border: none;
    border-left: 1px solid #999;
    border-top: 1px solid #999;
}
.radius-table tr>*{
    padding: 5px 10px;
    border: none;
    border-right: 1px solid #999;
    border-bottom: 1px solid #999;
}
.radius-table tr:first-child>*:first-child{
    border-radius: 10px 0 0 0;
}
.radius-table tr:first-child>*:last-child{
    border-radius: 0 10px 0 0;
}
.radius-table tr:last-child>*:first-child{
    border-radius: 0 0 0 10px;
}
.radius-table tr:last-child>*:last-child{
    border-radius: 0 0 10px 0;
}
</style>
<h3>登録ガイド</h3>
使用できる文字数と文字の種類は以下の通りです。
<table border="1" class="radius-table" >
<tr>
<td align="center">
アカウント名
</td>
<td align="left">
・文字数１～１０文字<br>
・半角英小文字(a～z)・半角英大文字（A～Z）<br>
<font color="red">※ログインアカウントには当サイト指定の数字が付与されます。</font>
</td>
</tr>
<tr>
<td align="center">
パスワード
</td>
<td align="left">
・文字数１～２０文字<br>
・半角英小文字(a～z)・半角英大文字（A～Z）・半角数字（0～9）
</td>
</tr>
</table>
</body>
</html>