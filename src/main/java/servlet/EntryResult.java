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
import model.logic.PostHealthEntryLogic;
import model.logic.ValueCheck;


@WebServlet("/EntryResult")
public class EntryResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	    
		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("topAccount");
	    Health health = (Health)session.getAttribute("topHealth");
	    String diaryId = request.getParameter("diaryId");
	    
		//healthにdiaryIdをセット
		health.setDiaryId(diaryId);


	    String  iDiaryId   = diaryId;
		String  iMood      = request.getParameter("mood");
		String  strMorning = request.getParameter("meals_morning");
		String  strNoon    = request.getParameter("meals_noon");
		String  strNight   = request.getParameter("meals_night");		
		String  iWater     = request.getParameter("water");
		String  iSteps     = request.getParameter("steps");
		String  iDiary     = request.getParameter("diary");


		//meals
		Integer iMeals   = 0;
		
		if (!(strMorning.equals("8"))) {
			iMeals += Integer.parseInt(strMorning);
		}
				
		if (!(strNoon.equals("8"))) {
			iMeals += Integer.parseInt(strNoon);
		}
			
		if (!(strNight.equals("8"))) {
			iMeals += Integer.parseInt(strNight);
		} 

		if (strMorning.equals("8") && strNoon.equals("8") && strNight.equals("8")) {
			iMeals = 8;
			} 


		String message = "";	// レスポンス後に画面に表示する結果メッセージ

			// 入力値のチェック
			String errMsg = ValueCheck.checkAdd(health.getUserId(),
												iDiaryId,
												iMood,
												iMeals,
												iWater,
												iSteps,
												iDiary);

		// 入力値チェックに問題がなければ記録追加処理を行う
		if ("".equals(errMsg)) {
			// healthテーブルへ追加処理を行う
			PostHealthEntryLogic logic = new PostHealthEntryLogic();
			boolean result = logic.add(health.getUserId(), iDiaryId, iMood, iMeals, iWater, iSteps, iDiary);
			
			// 結果メッセージを設定
			if (result) {
				message = "登録が完了しました";
			} else {
				message = "登録処理時に問題が発生しました";
			}
		} else {
			// 入力値チェックに問題がある場合は、メッセージにエラーメッセージを設定
			message = errMsg;
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/healthEntry.jsp");
			dispatcher.forward(request, response);
		}

		// 結果messageをリクエストスコープに保存
		request.setAttribute("diaryId", diaryId);
		request.setAttribute("message", message);

		// エントリ結果画面へフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/entryResult.jsp");
		dispatcher.forward(request, response);
		
	}

}
