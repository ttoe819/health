//03:ログインに関するリクエストを処理するコントローラ

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.Account;
import model.data.Health;
import model.logic.TopLogic;




@WebServlet("/Top")
public class Top extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//フォワード：ログイン結果画面
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得：top.jspのログインフォーム
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		
		//入力値チェック：フォームのテキスト入力有無
		//表示するエラーメッセージ
		String errorMsg ="";
		
		//ユーザー名未入力
		if (name == null || name.length() == 0) {
			errorMsg += ("アカウント名は必須項目です。<br>");
		}
		
		
		//パスワード未入力
		if (pass == null || pass.length() == 0) {
			errorMsg += ("パスワードは必須項目です。<br>");
		}
		
		
		//アカウント名相違
		boolean nameCheck = name.matches("^[a-zA-Z]+[0-9]+$");
				
		if (name.length() != 0 && ( name.length() > 10 || nameCheck == false )) {
			errorMsg += ("入力された情報が間違っています。<br>");
		}
		
		
		//パスワード相違
		boolean passCheck = pass.matches("^[a-zA-Z0-9]+$");
		if (pass.length() != 0 && ( pass.length() > 20 || passCheck == false )) {
			errorMsg += ("入力された情報が間違っています。<br>");
		}
		
		
		//ログイン認証
		String msg;
		
		if (errorMsg.length() != 0) {
			msg = errorMsg;
			
			request.setAttribute("msg", msg);
				
			//フォワード：top画面
			RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
			dispatcher.forward(request, response);	
				
		} else {
			//Acccountインスタンスの生成：ユーザー情報
			
			Account account = new Account(name, pass);
			
			
			
			//ログイン確認処理：true or false
			TopLogic topLogic = new TopLogic();
			boolean isLogin = topLogic.execute(account);


			//ログイン処理
			if (isLogin) {	
				//パスが一致：trueの場合				
					
				//ユーザー情報の保存：セッションスコープ
				HttpSession session = request.getSession();
				session.setAttribute("topAccount", account);
				
				//日記情報の保存：セッションスコープ
				//healthインスタンス生成
				Health  health  = new Health (account.getUserId());
				session.setAttribute("topHealth", health);
				
				
				msg = account.getName() + "さんがログイン中";
				
				request.setAttribute("msg", msg);

				//フォワード：home画面
				RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
				dispatcher.forward(request, response);
				
			}  else {
				//パスが不一致：falseの場合
					
				msg = "入力された情報が間違っています。";
				
				request.setAttribute("msg", msg);
				
				//フォワード：top画面
				RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
				dispatcher.forward(request, response);	
								
			}
		}
	}

}
