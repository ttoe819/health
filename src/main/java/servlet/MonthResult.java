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
import model.data.HealthResult;
import model.logic.GetHealthEntrylogic;
import model.logic.ResultLogic;


@WebServlet("/MonthResult")
public class MonthResult extends HttpServlet {
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


	        // Get selected month from request parameter
		    // ユーザが選んだ月を取得
	        String month = request.getParameter("month");
	        
	        health.setDiaryId(month);
	        
	        GetHealthEntrylogic getHealthEntryLogic = new GetHealthEntrylogic();
	        boolean result = getHealthEntryLogic.monthCheck(health);
	        
	        String forwardPass = null;
	        
	        String y = month.substring(0, 4);
	        String m = month.substring(5, 7);
	        
	        String selectMonth = String.format("%s年 %s月", y, m);
	        
	        if (result) {
		        
		        request.setAttribute("selectedMonth", selectMonth);

		        // Call logic class to retrieve month result
		        // ロジッククラス（そしてDAO）を呼ぶ
		        ResultLogic getResultLogic = new ResultLogic();
		        
				HealthResult monthHealth = getResultLogic.monthResults(userId, month);
				// Save result in request scope
				//　リクエストスコープに保存
				request.setAttribute("monthHealth", monthHealth);
				
				forwardPass = "WEB-INF/jsp/monthResult.jsp";
				
	        } else {
	        	
	        	String message = selectMonth + "にデータが登録されていません";
	        	request.setAttribute("message", message);
	        	forwardPass = "WEB-INF/jsp/home.jsp";
	        	
	        }
	        


	        // Forward to JSP
	        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPass);
	        dispatcher.forward(request, response);

	}

}
