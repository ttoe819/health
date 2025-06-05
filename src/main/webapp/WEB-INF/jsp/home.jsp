<%@page import="model.data.Account"%>
<%@page import="java.util.Date"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// セッションスコープからインスタンスを取得
Account account = (Account)session.getAttribute("topAccount");
String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
//日付表示設定
Calendar c = Calendar.getInstance();
Date d1 = new Date();
//今日
SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
String today= f.format(d1);
//1週間前
SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
c.setTime(d1);
c.add(Calendar.DAY_OF_MONTH, -6);
Date d2 = c.getTime();
String oneWeekAgo = f2.format(d2);
//今月
SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM");
String thisMonth = f3.format(d1);
%>
<title>ホーム</title>
</head>
<body>
<p style="text-align: right">
 <font color = "#000000"><a href="ConfirmLogout">ログアウト</a></font></p>
<h1>
	<p style="background-color:#A9D08E">
    <font color = "#000000">『健康第一』へようこそ！</font></p>
</h1>  
<% if (message != null) { %>
<%= message %>
<% } %>
<h3><%= account.getName() %>さんがログイン中</h3>
<form action="?" method="get">
	<p>日付を選択してください</p>
	<input type="date" name="diaryId" value = "<%= today %>">
	<button type="submit" formaction="DayResult">表示</button>
	<button type="submit" formaction="HealthEntry">入力・更新</button>
	<button type="submit" formaction="ConfirmDelete">削除</button>
</form>

<form action="WeekResult" method="get">
	<p>1週間の記録：最初の日を選択してください</p>
		<input type="date" name="week" value = "<%= oneWeekAgo %>"">
		<input type="submit" value="表示">
</form>

<form action="MonthResult" method="get">
	<p>1ヵ月の記録：月を選択してください</p>
	<input type="month" name="month" value = "<%= thisMonth %>">
	<input type="submit" value="表示">
</form>
<br><br>
</body>
</html>