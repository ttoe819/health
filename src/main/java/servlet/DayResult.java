package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.Account;
import model.data.Health;
import model.logic.Form;
import model.logic.GetHealthEntrylogic;


@WebServlet("/DayResult")
public class DayResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("topAccount");
	    Health health = (Health)session.getAttribute("topHealth");	
		String diaryId = request.getParameter("diaryId");
		
		//healthにdiaryIdをセット
		health.setDiaryId(diaryId);
		
		GetHealthEntrylogic getHealthEntryLogic = new GetHealthEntrylogic();
		boolean result = getHealthEntryLogic.exCheck(health);
		
		String forwardPass = null;
		
		Form f = new Form();
		String day = f.form(diaryId);
		
		if (result) {
			
			
			
			
			List<Health> healthList = getHealthEntryLogic.select(account.getUserId(), diaryId);

			Health checkHealth = null;
			for (Health h : healthList) {
				checkHealth = h;
			}
			
			request.setAttribute("day", day);
			request.setAttribute("checkHealth", checkHealth);
			forwardPass = "WEB-INF/jsp/dayResult.jsp";
			
		} else {		
			
			String message = day + "にデータが登録されていません";
			request.setAttribute("message", message);
			forwardPass = "WEB-INF/jsp/home.jsp";
		
		}	
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(forwardPass);
			dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
