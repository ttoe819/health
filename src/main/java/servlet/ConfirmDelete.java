package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.Health;
import model.logic.Form;
import model.logic.GetHealthEntrylogic;

@WebServlet("/ConfirmDelete")
public class ConfirmDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
	    Health health = (Health)session.getAttribute("topHealth");
		String diaryId = request.getParameter("diaryId");
		
		//healthにdiaryIdをセット
		health.setDiaryId(diaryId);
		
		GetHealthEntrylogic getHealthEntryLogic = new GetHealthEntrylogic();
		boolean result = getHealthEntryLogic.exCheck(health);
		
		String forwardPass = null;
		
		Form f = new Form();
		String fDiaryId = f.form(health.getDiaryId());
		
		if (result) {
			
			
			session.setAttribute("fDiaryId", fDiaryId);
			forwardPass = "WEB-INF/jsp/confirmDelete.jsp";
		
		} else {
			String message = fDiaryId + "にデータが登録されていません";
			request.setAttribute("message", message);
			forwardPass = "WEB-INF/jsp/home.jsp";
		}

		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(forwardPass);
		dispatcher.forward(request, response);

	}
}
