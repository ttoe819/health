package model.logic;

import dao.HealthDAO;
import model.data.Health;
import model.data.HealthNullData;


public class PostHealthEntryLogic {

	/* 商品の追加処理。
	 * このメソッドは結果に応じて以下の戻り値を返す。
	 * 追加成功					：true
	 * 追加失敗（id重複など）	：false
	 */
	
	
	
	
	
	public boolean add (  String  userId,
							String  diaryId,
							String  mood,
							Integer meals,
							String  water,
							String  steps,
							String  diary) {
		
//		System.out.println(userId);
//		if (!"".equals(mood))
		
		HealthNullData healthNullData = new HealthNullData();
		
		//mood
		Integer repMood = null;
		if(mood == null) {
			healthNullData.setNullMood("NULL");;
		}else {
			repMood = Integer.parseInt(mood); 
		}
		
		//meals
		Integer repMeals = null;
		if(meals == null) {
			healthNullData.setNullMeals("NULL");
		}else {
			repMeals = meals; 
		}
		
		
		//water
		Integer repWater = null;
		if(water == null) {
			healthNullData.setNullWater("NULL");
		}else if (water.equals("0")) {
			healthNullData.setNullWater("NULL");
		} else {			
			repWater = Integer.parseInt(water); 
		}
		
		
		//steps
		String repSteps = null;
		if(steps == "") {	
			healthNullData.setNullSteps("NULL");
		}else {
			repSteps = steps; 
		}
		
		
		//diary
		String repDiary = null;
		if(diary == "") {
			healthNullData.setNullDiary("NULL");
		}else {
			repDiary = diary; 
		}
		
		
		
		Health h1 = new Health(userId,
									diaryId,
									repMood,
									repMeals,
									repWater,
									repSteps,
									repDiary);
		
		

		// 追加処理
		boolean exCheck = false;
		HealthDAO dao = new HealthDAO();
		
		exCheck = dao.exCheck(h1);
		
		int count;
		
		if (exCheck == true) {
//			DeleteLogic delete = new DeleteLogic();
//			delete.executeDelete(userId, diaryId);
//			count = dao.insert(h1);
			count = dao.update(h1, healthNullData);
		} else {
			
			count = dao.insert(h1);
			
		}
		
		
		// 追加結果を戻り値で返す
		if (count == 0) {
		return false;
		} else {
		return true;
		}
				
	}
	
	
}
