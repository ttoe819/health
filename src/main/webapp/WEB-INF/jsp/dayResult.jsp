<%@page import="model.data.Health"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//セッションスコープからインスタンスを取得
Health health = (Health)request.getAttribute("checkHealth");
//メッセージ取得：リクエストスコープ
String day = (String)request.getAttribute("day");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= day %>の結果</title>
</head>
<body>
<p style="text-align: right"><font color = "#000000"><a href="Home">ホーム</a></font>
 &ensp;
 <font color = "#000000"><a href="ConfirmLogout">ログアウト</a></font></p>
 <h1>
 <p style="background-color:#A9D08E"><font color="#000000"><%= day %>の結果</font></p>
 </h1>
 <br>
    <!-- 何か書くか -->
<br>

    <!-- 今日の気分 -->
    <p style="background-color:#A9D08E; color:#000000;">今日の気分は？</p>
	<% if (health.getMood() != null) { %>
    	<% if (health.getMood() == 5) { %>
    	    <img src="images/5.jpg" align="center" width="85" height="85" alt="😆">
   		 <% } else if (health.getMood() == 4) { %>
        		<img src="images/4.jpg" align="center" width="85" height="85" alt="😀">
   		 <% } else if (health.getMood() == 3) { %>
        <img src="images/3.jpg" align="center" width="85" height="85" alt="🙂">
    <% } else if (health.getMood() == 2) { %>
        <img src="images/2.jpg" align="center" width="85" height="85" alt="😐">
    <% } else if (health.getMood() == 1) { %>
        <img src="images/1.jpg" align="center" width="85" height="85" alt="😢">
    <% } else { %>
        記録なし
    <% } %>
<% } %>
    

    <!-- 食べたご飯の回数 -->
    <p style="background-color:#A9D08E; color:#000000;"> 食べたご飯の回数は？</p>
    	
    	<table >
			<tr>
				<td>
					<img src="images/meal.jpeg" align="center" width="85" height="60" alt="食事の写真">
				</td>
				<td>
				 &ensp; &ensp;
				</td>
				<td align="left" width="250">
					 <%= (health.getMeals() != null) ?
    		(health.getMeals() == 0 ? "この日は食事をされませんでした" :
    		(health.getMeals() == 1 ? "この日は１食たべました" :
    		(health.getMeals() == 2 ? "この日は２食たべました" :
    		(health.getMeals() == 3 ? "この日は３食たべました" : "記録なし")))) : "記録なし" %>
				</td>
			</tr>
		</table>
    	
    	
    	

    <!-- 水何杯飲んだか -->
    <p style="background-color:#A9D08E; color:#000000;">水何杯飲んだ？</p>
     		<table>
			<tr>
				<td>
					<img src="images/glass.png" align="center" width="85" height="55" alt="glassの写真">
				</td>
				<td align="center" width="30">
					 ×
				</td>
				<td align="center" width="60">
					<%= health.getWater() %> 杯分
				</td>
				<td align="left" width="200">
   		 		＝
       			 <%= (health.getWater() != null) ? 
        			 (health.getWater() == 0 ? "記録なし" :
          			 (health.getWater() == 1 ? "200mL" :
            		 (health.getWater() == 2 ? "400mL" :
            		 (health.getWater() == 3 ? "600mL" :
            		 (health.getWater() == 4 ? "800mL" :
           			 (health.getWater() == 5 ? "1L" :
            		 (health.getWater() == 6 ? "1.2L" :
            		 (health.getWater() == 7 ? "1.4L" :
            		 (health.getWater() == 8 ? "1.6L" :
           			 (health.getWater() == 9 ? "1.8L" :
            		 (health.getWater() == 10 ? "2L" :
            		 (health.getWater() == 11 ? "2L以上" : "記録なし")))))))))))) : "記録なし" %> 		 	
				</td>
			</tr>
		</table>
    
    
    
    
    

    <!-- 歩いた歩数 -->
    <p style="background-color:#A9D08E; color:#000000;">歩いた歩数は？</p>
        

	<table >
			<tr>
				<td>
					<img src="images/walking.jpg" align="center" width="125" height="90" alt="徒歩写真">
				</td>
				<td>
				 &ensp; &ensp;
				</td>
				<td align="left" width="200">
					 <%= (health.getSteps() != null && !health.getSteps().isEmpty()) ? health.getSteps() : "記録なし" %>
				</td>
			</tr>
		</table>



    <!-- ひとこと日記 -->
    <p style="background-color:#A9D08E; color:#000000;">ひとこと日記</p>
        <%= (health.getDiary() != null && !health.getDiary().isEmpty()) ? health.getDiary() : "記録なし" %>
<br><br><br><br>
</body>
</html>
