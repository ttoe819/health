<%@page import="model.data.Health"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// セッションスコープからインスタンスを取得
Health health = (Health)session.getAttribute("topHealth");
String message = (String)request.getAttribute("message");
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
    <font color = "#000000">新規エントリ</font>
    <font color = "#000000">/</font>
    <font color = "#000000">更新エントリ</font>
    </p>
</h1>
<form action="EntryResult" method="POST">
<% if (message != null) { %>
<%= message %><br>
<input type="hidden" name="message" value="<%= message %>">
<% } %>
<br>
<!-- 日付を表示 -->
エントリ日付:
<input type="date" name="diaryId" value="<%= health.getDiaryId() != null ? health.getDiaryId() : "" %>" readonly><br><br> 
<!-- readonlyにしてユーザーが変更不可 -->

 <p style="background-color:#A9D08E; color:#000000;">今日はどんな気分？</p>
<input type="radio" name="mood" value="5">&#x1f606; &emsp;&emsp;&emsp;&emsp;
<input type="radio" name="mood" value="4">&#x1f600; &emsp;&emsp;&emsp;&emsp;
<input type="radio" name="mood" value="3">&#x1f642; &emsp;&emsp;&emsp;&emsp;
<input type="radio" name="mood" value="2">&#x1f610; &emsp;&emsp;&emsp;&emsp;
<input type="radio" name="mood" value="1">&#x1f622;<br><br>

<p style="background-color:#A9D08E; color:#000000;">ご飯食べた？</p>
&#x1f304;朝<br> 


<!-- 朝のラジオボタン -->
   		  	  <input type="radio" name="meals_morning" value="1">食べた
    &emsp;<input type="radio" name="meals_morning" value="0">食べてない
    &emsp;<input type="radio" name="meals_morning" value="8" checked>未選択

    <br><br>
&#x2600;昼<br> 
    <!-- 昼のラジオボタン -->
   			   <input type="radio" name="meals_noon" value="1">食べた
    &emsp;<input type="radio" name="meals_noon" value="0">食べてない
    &emsp;<input type="radio" name="meals_noon" value="8" checked>未選択

    <br><br>
&#x1f319;夜 <br>
    <!-- 夜のラジオボタン -->
   			  <input type="radio" name="meals_night" value="1">食べた
    &emsp;<input type="radio" name="meals_night" value="0">食べてない
    &emsp;<input type="radio" name="meals_night" value="8" checked>未選択


<p style="background-color:#A9D08E; color:#000000;">水何杯飲んだ？</p>
<select name="water">
<option value="0">選択</option>
<option value="1">200ml(1杯)</option>
<option value="2">400ml(2杯)</option>
<option value="3">600ml(3杯)</option>
<option value="4">800ml(4杯)</option>
<option value="5">1L(5杯)</option>
<option value="6">1.2L(6杯)</option>
<option value="7">1.4L(7杯)</option>
<option value="8">1.6L(8杯)</option>
<option value="9">1.8L(9杯)</option>
<option value="10">2L(10杯)</option>
<option value="11">2L(10杯↑)以上</option>
</select>

<p style="background-color:#A9D08E; color:#000000;">どのくらい歩いた？</p>
<input type="text" name="steps">歩
<p style="background-color:#A9D08E; color:#000000;">ひとこと日記</p>
<style>
.diary{
    display: inline-block;
    width: 100%;
    padding: 1em 0.5em;
    line-height: 3;
    border: 1px solid #999;
    box-sizing: border-box;
    background: #FFFFFF;
    margin: 0.5em 0;
}
</style>
<input type="text" name="diary" class="diary">
<br><br>
<input type="submit" value="登録" class="button">
<style type="text/css">
.button {
  display       : inline-block;
  border-radius : 20%;          /* 角丸       */
  font-size     : 12pt;        /* 文字サイズ */
  text-align    : center;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 12px 12px;   /* 余白       */
  background    : #a9d08e;     /* 背景色     */
  color         : #000000;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  transition    : .3s;         /* なめらか変化 */
  box-shadow    : 6px 6px 3px #666666;  /* 影の設定 */
  border        : 2px solid #a9d08e;    /* 枠の指定 */
}
.button:hover {
  box-shadow    : none;        /* カーソル時の影消去 */
  color         : #a9d08e;     /* 背景色     */
  background    : #000000;     /* 文字色     */
}
</style>
</form>
</body>
</html>
