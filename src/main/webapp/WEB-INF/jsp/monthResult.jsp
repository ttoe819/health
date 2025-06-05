<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>  
<%@ page import="model.data.HealthResult" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%  %>
    <title><%= request.getAttribute("selectedMonth")  %>の結果</title>
</head>
<body>
	<p style="text-align: right"><font color = "#000000"><a href="Home">ホーム</a></font>
	 &ensp;
	 <font color = "#000000"><a href="ConfirmLogout">ログアウト</a></font></p>
	 <h1>
	 <%-- ユーザーが選択した月分 --%>
	 <p style="background-color:#A9D08E"><font color="#000000"><%= request.getAttribute("selectedMonth")  %>の結果</font></p>
	 </h1>

	<%-- サーブレットで保存したmonthHealthを取得 --%>
	<%
	HealthResult monthHealth = (HealthResult)request.getAttribute("monthHealth");
	%>

	<%-- monthHealthがヌルじゃない場合はデータを取り出す --%>
	<% if (monthHealth != null) { %>

	<%-- データのある日日の数を出力 --%>
	<% Integer monthLength = monthHealth.getMonthLength(); %>
		<p><h2>この月は<%= monthLength %>日分記録があるよ！</h2></p>

    <!-- 気分 -->
    	<p style="background-color:#A9D08E; color:#000000;">1番多かった気分は？
  	  （記録：<%= monthHealth.getMoodDays() %>日分）
  	  	</p>
    <%-- 気分を入力した日の数と入力してない日の数 --%>
  	  <%
  		  int mood = monthHealth.getrMood(); 
  		  if (mood != 0) { %>
    	
	<% } %>	
	</p>
	
    <%       
    switch (mood) {
    case 5: 
%> <p><table >
				<tr>
					<td align="center" width="200" >
						 <img src="images/5.jpg" align="center" width="85" height="85" alt="顔文字画像5">
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						 <p>今月は元気だね！</p>
					</td>
				</tr>
			</table><% break;

    case 4: 
  %> <p><table >
				<tr>
					<td align="center" width="200" >
						 <img src="images/4.jpg" align="center" width="85" height="85" alt="顔文字画像4"> 
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						 <p>今月は良い感じだね！</p>
					</td>
				</tr>
			</table> <% break;

    case 3: 
    %> <p><table >
				<tr>
					<td align="center" width="200" >
						 <img src="images/3.jpg" align="center" width="85" height="85" alt="顔文字画像3">
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						 <p>今月はまあまあだったね。</p>
					</td>
				</tr>
			</table><% break;
			
    case 2: 
    %> <p><table >
				<tr>
					<td align="center" width="200" >
						 <img src="images/2.jpg" align="center" width="85" height="85" alt="顔文字画像2">
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						 <p>今月はあんまりだったね。</p>
					</td>
				</tr>
			</table><% break;
			
    case 1: 
    %> <p><table >
				<tr>
					<td align="center" width="200" >
						 <img src="images/1.jpg" align="center" width="85" height="85" alt="顔文字画像1"> 
					</td>
				</tr>
				<tr>
					<td align="center" width="200">
						 <p>今月はちょっと大変だったね。</p>
					</td>
				</tr>
			</table><% break;
					
            default: %> <p>情報ありません。</p>
    <% } %>
    

    <!-- 食事 -->
    <p style="background-color:#A9D08E; color:#000000;">1日の食事の平均は？
    （記録：<%= monthHealth.getMealDays() %>日分）
    </p>
    
     <%
     int avgMeals = monthHealth.getrMeals();
     int mealDays = monthHealth.getMealDays();
     if (mealDays == 0) { %> <p>情報ありません</p> <% }
     else {
    if ( avgMeals >= 0) { %>
 		<%-- 食事を入力した日の数と入力してない日の数 --%>
 		
 		<!-- 食事の画像 -->
    	<table >
			<tr>
				<td>
					<img src="images/meal.jpeg" align="center" width="85" height="60" alt="食事の写真">
				</td>
				<td>
				 &ensp; &ensp;
				</td>
				<td align="left" width="145">
					 <p>一日平均<%= monthHealth.getdMeals() %> 回</p>
				</td>
			</tr>
		</table>
	    <p></p>   
	       <%   	
   		switch (avgMeals) {
	  	 	case 1: %> <p> 今月あんまり食べてなかったね。大丈夫？</p><% break;
	  	 	case 2: %> <p> 今月ご飯足りた?　しっかり食べようね！</p><% break;
	   		case 3:	%> <p> 今月はよく食べたね！その調子</p> <% break;
	   		default: %> <p>情報ありません。</p>
		<% } %>
		<% } %>
 <% } %>  	



     <!-- 水 -->
    <p style="background-color:#A9D08E; color:#000000;">1日に飲んだ水の平均は？
    <%-- 水分量を入力した日の数 --%>
    （記録：<%= monthHealth.getWaterDays() %>日分）
    </p>
    
    <%-- 1日の平均 --%>
   <% int avgWater = monthHealth.getrWater(); %>
    
  	 <% if (avgWater != 0) { %>
	   
	   <!-- コップの画像 -->
   		<table>
			<tr>
				<td>
					<img src="images/glass.png" align="center" width="70" height="55" alt="glassの写真">
				</td>
				<td align="center" width="30">
					 ×
				</td>
				<td align="center" width="60">
					 <%= monthHealth.getrWater() / 200 %>杯分
				</td>
				<td align="left" width="200">
				
   		 		<p>= &ensp; &ensp;<%= monthHealth.getrWater() %> mL</p>  		 	
				</td>
			</tr>
		</table>
	   
	   				<% if (avgWater < 1000) { %>
									<p>ちょっと足りないかな...</p>
					<%	} else if (avgWater < 2000)	{	%> 
									<p>足りてるよ</p>
					<%	} else if(avgWater >= 2000) {  	%>
									<p>いっぱい飲んだね！</p>
					<% } %>    
    	 <%	} else { %>
				<p>情報がありません</p>					
	<%  } %>
				<p><font size="2"><b>※2l以上飲んだ日は、1日分2.2lで計算されています。</b></font></p>
  

    <!-- 歩数 -->
    <p style="background-color:#A9D08E; color:#000000;">1日の歩数の平均は？
    
    <%-- 歩数を入力した日の数と入力してない日の数 --%>
    <% String steps = monthHealth.getrSteps(); %>
    <% int avgSteps = 0; %>
      
    （記録：<%= monthHealth.getStepDays() %>日分）
    
    </p>
		    
		        
   	<% 	if (monthHealth.getrSteps().equals("") || steps == null) { %>
			<p>情報ありません</p>
	<% } else { %>
			<table >
			<tr>
				<td>
					<img src="images/walking.jpg" align="center" width="125" height="90" alt="徒歩写真">
				</td>
				<td>
				 &ensp; &ensp;
				</td>
				<td align="left" width="200">
					 <p>1日平均 <%= monthHealth.getrSteps() %> 歩</p>
				</td>
			</tr>
		</table>
		    <%	   avgSteps = Integer.parseInt(monthHealth.getrSteps());
		   	 if (avgSteps != 0) {
		    	if (avgSteps < 6000 ) { %> 
		    		<p>もうすこし頑張ろう！</p> <% 
		     	} else if (avgSteps < 10000) { %> 
		     	 	<p>うん、その調子！</p> <%
		    	} else if (avgSteps < 20000) { %> 
		     	 	<p>たくさん歩いてる！すごい！</p> <% 
		     	}  
		    	
		    %>	
		  <% } %>
	<% } %>
	
	   
   	
<%-- monthHealth がヌルの場合 --%>
<% } else { %>
    <p>データが見つかりませんでした。</p>
<% } %>

</body>
</html>