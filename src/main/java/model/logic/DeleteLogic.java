package model.logic;

import dao.HealthDAO;

public class DeleteLogic {

	public int executeDelete(String userId,String diaryId) {
		HealthDAO dao = new HealthDAO();
		return dao.delete(userId, diaryId);
	}
}
