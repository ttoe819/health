package model.logic;

import java.util.List;

import dao.HealthDAO;
import model.data.Health;

public class GetHealthEntrylogic {
	
	public List<Health> select(String userId, String diaryId) {
		HealthDAO dao = new HealthDAO();
		List<Health> healthList =dao.select(userId, diaryId);
	
		return healthList;
	}
	
	
	public boolean exCheck(Health health) {
		HealthDAO dao = new HealthDAO();
		boolean exCheck = dao.exCheck(health);

		return exCheck;

	}
	
	public boolean weekCheck(Health health) {
		HealthDAO dao = new HealthDAO();
		boolean weekCheck = dao.weekCheck(health);

		return weekCheck;

	}
	
	public boolean monthCheck(Health health) {
		HealthDAO dao = new HealthDAO();
		boolean monthCheck = dao.monthCheck(health);
		
		return monthCheck;
	}
}