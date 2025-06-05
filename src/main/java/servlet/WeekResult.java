package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.Account;
import model.data.Health;
import model.data.HealthResult;
import model.logic.Form;
import model.logic.GetHealthEntrylogic;
import model.logic.ResultLogic;


@WebServlet("/WeekResult")
public class WeekResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Get session and Account
		//　スコープからアカウントを取得
		// retrieve userID from session Account object
		// UserId を取得
		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("topAccount");
	    Health health = (Health)session.getAttribute("topHealth");
	    String userId = account.getUserId();


		// retrieve selected week from request parameter
		// ユーザが選んだ週を取得
		String week = request.getParameter("week");
		
		health.setDiaryId(week);
		
		GetHealthEntrylogic getHealthEntryLogic = new GetHealthEntrylogic();
		boolean result = getHealthEntryLogic.weekCheck(health);
		
		String forwardPass = null;
		
		// 週初め書式整形
		Form f = new Form();
		String selectedWeek = f.form(week);
		
		// 週終わりを出す
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			 d = sf.parse(week);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, 6);
        java.util.Date future = c.getTime();
        String endWeek = f.form(sf.format(future));    
        
		
		if (result) {
			
			request.setAttribute("selectedWeek", selectedWeek);

			// Call the logic to retrieve week result
			// ロジッククラス（そしてDAO）を呼ぶ
			ResultLogic getResultLogic = new ResultLogic();

			HealthResult weekHealth = getResultLogic.weekResults(userId, week);
			// Save result in request scope
			//　リクエストスコープに保存
			request.setAttribute("weekHealth", weekHealth);
			request.setAttribute("endWeek", endWeek);
			
			forwardPass = "WEB-INF/jsp/weekResult.jsp";
			
		} else {
			
			String message = selectedWeek + "～" + endWeek + "にデータが登録されていません";
			request.setAttribute("message", message);
			forwardPass = "WEB-INF/jsp/home.jsp";
			
		}
		
					// ビューにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPass);
					dispatcher.forward(request, response);

	}

}
