//09：ユーザ登録に関するリクエストを処理するコントローラ

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
import model.logic.PostRegisterAccountLogic;

//import model.GetRegisterAccountLogic;
//import model.Account;


@WebServlet("/RegisterAccount")
public class RegisterAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//top.jspから呼び出される
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//フォワード：ユーザー登録画面
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
		dispatcher.forward(request, response);	
		
	}
	

	//registerAccount.jspから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの取得：registerAccount.jspのユーザー登録フォーム
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
		
		//ユーザー名相違
		boolean nameCheck = name.matches("^[a-zA-Z]+$");
		
		if (name.length() != 0 && ( name.length() > 10 || nameCheck == false )) {
			errorMsg += ("アカウント名は半角英字10文字以下で入力してください。<br>");
		}
				
		//パスワード相違
		boolean passCheck = pass.matches("^[a-zA-Z0-9]+$");
		
		if (pass.length() != 0 && ( pass.length() > 20 || passCheck == false )) {
			errorMsg += ("パスワードは半角英数20文字以下で入力してください。<br>");
		}
		
		
		//追加判定
		String msg1 = null;
		String msg2 = null;
		
			//追加不可
			if(errorMsg.length() != 0) {
				msg1 = errorMsg;
			} else {
			//ユーザー追加
			Account account = new Account(name, pass);	
			PostRegisterAccountLogic getRegisterAccountLogic = new PostRegisterAccountLogic();
			boolean isTrue = getRegisterAccountLogic.execute(account);
			
			
					
				if(isTrue == true) {
					msg1 =  "登録が完了しました。";
					msg1 += "<br>あなたのログインアカウント名は『" + account.getName() + account.getUserId() + "』です。";
					msg1 +=	"<br>トップページからログインしてください。";
					msg2 = "true";
					
					
					//ユーザー情報の保存：セッションスコープ
					HttpSession session = request.getSession();
					session.setAttribute("topAccount", account);

				} else {
					msg1 = "登録に失敗しました。<br>再度登録作業をお願いします。";
				}			
			}	
		
		request.setAttribute("msg1", msg1);
		request.setAttribute("msg2", msg2);

		
		
		//フォワード：ユーザー登録画面
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/registerAccount.jsp");
		dispatcher.forward(request, response);
		
	}

}
