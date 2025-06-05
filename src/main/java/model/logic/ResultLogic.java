package model.logic;

import java.util.List;

import dao.HealthDAO;
import model.data.HealthResult;

public class ResultLogic {

	// retrieve data and calculate averages for a given week
	//　ユーザが選んだ日にちの一週間分のデータをDAOクラスから取得して平均を計算
	public HealthResult weekResults(String userID, String week) {
		
		
		HealthDAO dao = new HealthDAO();

	    	List<HealthResult> weekList = null;
		    // pass String week to weekselect method
		 	// 文字列型のweekをDAOに渡す
		   
			try {
				weekList = (List<HealthResult>) dao.weekselect(userID, week);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

	        	
	    // 変数を0に初期化
		int[] moodScore = new int[6];
		Integer totalMeals = 0;
	    Integer totalWater = 0;
	    Integer totalSteps = 0;
	    String avgStepString= "";
	    Integer avgMeals = 0;
	    Integer avgWater = 0;
	
	        	
	    // 平均の計算用の変数を初期化
	    // set days to calculate averages
	    Integer moodDays = 0;
	    Integer mealDays = 0;
	    Integer waterDays = 0;
	    Integer stepDays = 0;
	    Integer weekLength = 0;
		
		// for loop で各日のデータを取得
	    for (HealthResult h : weekList) {
	    	if (weekList != null) {
	    		weekLength++;
	    	}
	    	// 気分は1から5のインデックスから数える
	        Integer mood = h.getrMood();
	        
	        if (mood >= 1 && mood <= 5) {
	            moodScore[mood]++;
	            moodDays++;
	        }
	        
	        // convert steps to int and calculate
	        // 歩数は文字列からint型に変換して計算
	        String stepString = h.getrSteps();
	        if (stepString != null && !stepString.isEmpty() && !stepString.equals("null")) {
	        	try { 
	        		Integer steps = Integer.parseInt(stepString);
	        		totalSteps += steps;
	        		stepDays++;
	        	} catch (NumberFormatException e) {
	        		e.printStackTrace();
	        	}
	        }
	       
	        // calculate totals first
	        // 食事と水分の合計と入力された日数を数える
	        // 8はヌルってことなのでそれ以外のとき　（０－３の時だけ日数が増える)
	        Integer meals = h.getrMeals();
	        if (meals != 8) {
	            totalMeals += meals;
	            mealDays++;
	        }
	        
	        if (h.getrWater() != 0) {
		        totalWater += h.getrWater();
		        waterDays++;
	        } 
	    }
	    
        // 一番多い気分を探すループ
	    Integer mostFrequentMood = 0;
	    Integer maxCount = moodScore[0];
	    for (Integer i = 0; i < moodScore.length; i++) {
	        if (moodScore[i] > maxCount) {
	            maxCount = moodScore[i];
	            mostFrequentMood = i;
	        }
	    }
	    
	    // calculate average meals/water for the month
    	//　食事と水の合計と日数で平均を計算
	    if (mealDays > 0) {
	    	avgMeals = totalMeals / mealDays;
	    }
	    if (waterDays > 0 ) {
	    	totalWater = totalWater * 200;
	    	avgWater = totalWater / waterDays;
	    }
    	// compute average steps
	    // save as int for jsp calculations too
    	if (stepDays > 0) {
    	  avgStepString = String.valueOf(totalSteps / stepDays);
    	}
    	
    	
    	
    	
    	HealthResult averageWeekHealth = new HealthResult();
	    averageWeekHealth.setrMood(mostFrequentMood);
	    averageWeekHealth.setrMeals(avgMeals);
	    averageWeekHealth.setrWater(avgWater);
	    averageWeekHealth.setrSteps(avgStepString);
	    averageWeekHealth.setMoodDays(moodDays);
	    averageWeekHealth.setMealDays(mealDays);
	    averageWeekHealth.setWaterDays(waterDays);
	    averageWeekHealth.setStepDays(stepDays);
	    averageWeekHealth.setWeekLength(weekLength);
	    
	    return averageWeekHealth;
	}
	


	// retrieve data and calculate averages for a given month
	// ユーザが選んだ日にちの一か月分のデータをDAOクラスから取得して平均を計算
	public HealthResult monthResults(String userId, String month) {
		
			HealthDAO dao = new HealthDAO();
		
				List<HealthResult> monthList = null;
		
				try {
					monthList = (List<HealthResult>) dao.monthselect(userId, month);
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
						e1.printStackTrace();
				}
		
			    // 変数を0に初期化
				int[] moodScore = new int[6];
				Integer totalMeals = 0;
			    Integer totalWater = 0;
			    Integer totalSteps = 0;
			    String avgStepString= "";
			    Integer avgMeals = 0;
			    Integer avgWater = 0;
			
			        	
			    // 平均の計算用の変数を初期化
			    // set days to calculate averages
			    Integer moodDays = 0;
			    Integer mealDays = 0;
			    Integer waterDays = 0;
			    Integer stepDays = 0;
			    Integer monthLength = 0;
				
				// for loop で各日のデータを取得
			    for (HealthResult h : monthList) {
			    	if (monthList != null) {
			    		monthLength++;
			    	}
			    	// 気分は1から5のインデックスから数える
			        Integer mood = h.getrMood();
			        
			        if (mood >= 1 && mood <= 5) {
			            moodScore[mood]++;
			            moodDays++;
			        }
			        
			        // convert steps to int and calculate
			        // 歩数は文字列からint型に変換して計算
			        String stepString = h.getrSteps();
			        if (stepString != null && !stepString.isEmpty() && !stepString.equals("null")) {
			        	try { 
			        		Integer steps = Integer.parseInt(stepString);
			        		totalSteps += steps;
			        		stepDays++;
			        	} catch (NumberFormatException e) {
			        		e.printStackTrace();
			        	}
			        }
			        
			        // calculate totals first
			        // 食事と水分の合計と入力された日数を数える
			        // 8はヌルってことなのでそれ以外のとき　（０－３の時だけ日数が増える)
			        if (h.getrMeals() != 8) {
			            totalMeals += h.getrMeals();
			            mealDays++;
			        }
			        if (h.getrWater() != 0) {
				        totalWater += h.getrWater();
				        waterDays++;
			        } 
			    }
			    
		        // 一番多い気分を探すループ
			    Integer mostFrequentMood = 0;
			    Integer maxCount = moodScore[0];
			    for (Integer i = 0; i < moodScore.length; i++) {
			        if (moodScore[i] > maxCount) {
			            maxCount = moodScore[i];
			            mostFrequentMood = i;
			        }
			    }
			    
			    // calculate average meals/water for the month
		    	//　食事と水の合計と日数で平均を計算
			    double d = 0 ;
			    if (mealDays > 0) {
			    	avgMeals = totalMeals / mealDays;
			    	d = totalMeals / mealDays;
			    }
			    if (waterDays > 0 ) {
			    	totalWater = totalWater * 200;
			    	avgWater = totalWater / waterDays;
			    }
		    	// calculate average steps
			    //歩数の平均を計算
		    	if (stepDays > 0) {
		    	  avgStepString = String.valueOf(totalSteps / stepDays);
		    	}
		    	
		    		    	
		    	HealthResult averageMonthHealth = new HealthResult();
			    averageMonthHealth.setrMood(mostFrequentMood);
			    averageMonthHealth.setrMeals(avgMeals);
			    averageMonthHealth.setrWater(avgWater);
			    averageMonthHealth.setrSteps(avgStepString);
			    averageMonthHealth.setMoodDays(moodDays);
			    averageMonthHealth.setMealDays(mealDays);
			    averageMonthHealth.setWaterDays(waterDays);
			    averageMonthHealth.setStepDays(stepDays);
			    averageMonthHealth.setMonthLength(monthLength);
			    
			    averageMonthHealth.setdMeals(d);
			    
			    return averageMonthHealth;
						
	}

}
