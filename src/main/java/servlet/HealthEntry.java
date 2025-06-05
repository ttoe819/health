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
import model.logic.GetHealthEntrylogic;


@WebServlet("/HealthEntry")
public class HealthEntry extends HttpServlet {
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
		
		
		//既存記録があるか確認
		GetHealthEntrylogic getHealthEntrylogic = new GetHealthEntrylogic();
		boolean exCheck = getHealthEntrylogic.exCheck(health);
		
		
		session.setAttribute("exCheck", exCheck);
		
		String message = "";
		
		if (exCheck == true) {
			
			message = "登録済です。<br> 登録をクリックするとすべて最新のデータに更新されます。";
			
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/healthEntry.jsp");
		dispatcher.forward(request, response);
			
		
	}

}	
	