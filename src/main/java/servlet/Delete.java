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
import model.logic.DeleteLogic;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("topAccount");
		Health health =(Health)session.getAttribute("topHealth");


		request.setCharacterEncoding("UTF-8");
		
		
		DeleteLogic deleteLogic = new DeleteLogic();
		int delCount = deleteLogic.executeDelete(account.getUserId(), health.getDiaryId());
		
		
		request.setAttribute("delCount", delCount);
		
		
		// フォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("WEB-INF/jsp/delete.jsp");
		dispatcher.forward(request, response);

	}
}
