<%@page import="model.data.Health"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//セッションスコープからインスタンスを取得
Health health = (Health)session.getAttribute("topHealth");
//メッセージ取得：リクエストスコープ
String message = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
    <title>エントリ結果</title>
</head>
<body>
<p style="text-align: right"><font color = "#000000"><a href="Home">ホーム</a></font>
 &ensp;
 <font color = "#000000"><a href="ConfirmLogout">ログアウト</a></font></p>
 <h1>
 <p style="background-color:#A9D08E"><font color="#000000">登録結果</font></p>
 </h1>
 <br>
<%= message %>
<br>
    <!-- 日付を表示 -->
    登録された日付: <%= health.getDiaryId() %><br>

    <!-- 今日の気分 -->
    <p style="background-color:#A9D08E; color:#000000;">今日の気分は？</p>
        <%= (request.getParameter("mood") != null) ? 
            (request.getParameter("mood").equals("5") ? "😆" :
            (request.getParameter("mood").equals("4") ? "😀" :
            (request.getParameter("mood").equals("3") ? "🙂" :
            (request.getParameter("mood").equals("2") ? "😐" :
            (request.getParameter("mood").equals("1") ? "😢" : "未選択"))))) : "未選択" %>
    

    <!-- 食べたご飯の回数 -->
    <p style="background-color:#A9D08E; color:#000000;"> 食べたご飯の回数は？</p>
        <%= (request.getParameter("meals_morning") != null) ? 
           	    (request.getParameter("meals_morning").equals("1") ? "朝食: 食べた" : 
                (request.getParameter("meals_morning").equals("0") ? "朝食: 食べてない" : 
            	(request.getParameter("meals_morning").equals("8") ? "朝食: 未選択" :	""))) : "未選択" %>
        <%= (request.getParameter("meals_noon") != null) ? 
           	    (request.getParameter("meals_noon").equals("1") ? "<br>昼食: 食べた" : 
                (request.getParameter("meals_noon").equals("0") ? "<br>昼食: 食べてない" : 
                (request.getParameter("meals_noon").equals("8") ? "<br>昼食: 未選択" :	""))) : "未選択" %>
        <%= (request.getParameter("meals_night") != null) ? 
          	    (request.getParameter("meals_night").equals("1") ? "<br>夕食: 食べた" : 
                (request.getParameter("meals_night").equals("0") ? "<br>夕食: 食べてない" : 
                (request.getParameter("meals_night").equals("8") ? "<br>夕食: 未選択" :	""))) : "未選択" %>
    
    
    <!-- 水何杯飲んだか -->
    <p style="background-color:#A9D08E; color:#000000;">水何杯飲んだ？</p>
        <%= (request.getParameter("water") != null) ? 
        	(request.getParameter("water").equals("0") ? "未選択" :
            (request.getParameter("water").equals("1") ? "200ml(1杯)" :
            (request.getParameter("water").equals("2") ? "400ml(2杯)" :
            (request.getParameter("water").equals("3") ? "600ml(3杯)" :
            (request.getParameter("water").equals("4") ? "800ml(4杯)" :
            (request.getParameter("water").equals("5") ? "1L(5杯)" :
            (request.getParameter("water").equals("6") ? "1.2L(6杯)" :
            (request.getParameter("water").equals("7") ? "1.4L(7杯)" :
            (request.getParameter("water").equals("8") ? "1.6L(8杯)" :
            (request.getParameter("water").equals("9") ? "1.8L(9杯)" :
            (request.getParameter("water").equals("10") ? "2L(10杯)" :
            (request.getParameter("water").equals("11") ? "2L(10杯↑)以上" : "未選択")))))))))))) : "未選択" %>


    <!-- 歩いた歩数 -->
    <p style="background-color:#A9D08E; color:#000000;">歩いた歩数は？</p>
        <%= (request.getParameter("steps") != null && !request.getParameter("steps").isEmpty()) ? request.getParameter("steps") : "未入力" %>


    <!-- ひとこと日記 -->
    <p style="background-color:#A9D08E; color:#000000;">ひとこと日記</p>
        <%= (request.getParameter("diary") != null && !request.getParameter("diary").isEmpty()) ? request.getParameter("diary") : "未入力" %>

</body>
</html>
