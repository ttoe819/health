package model.data;

import java.io.Serializable;

public class HealthResult implements Serializable {
	
	private String  rUserId;
	private String  rDiaryId;
	private Integer rMood;
	private Integer rMeals;
	private Integer rWater;
	private String  rSteps;
	
	// 情報ある日日用の変数
	private Integer moodDays;
	private Integer mealDays;
	private Integer waterDays;
	private Integer stepDays;
	private Integer weekLength;
	private Integer monthLength;
	
	// 食事平均
	private double dMeals;
	
	public HealthResult() {}
	
	public HealthResult(String userId) {		
		this.rUserId = userId;		
	}

	public HealthResult(String userId, String diaryId, Integer mood, Integer meals, Integer water, String steps) {
		this.rUserId = userId;
		this.rDiaryId = diaryId;
		this.rMood = mood;
		this.rMeals = meals;
		this.rWater = water;
		this.rSteps = steps;
	}
	
	

	public String getrUserId() {
		return rUserId;
	}

	public void setrUserId(String rUserId) {
		this.rUserId = rUserId;
	}

	public String getrDiaryId() {
		return rDiaryId;
	}

	public void setrDiaryId(String rDiaryId) {
		this.rDiaryId = rDiaryId;
	}

	public Integer getrMood() {
		return rMood;
	}

	public void setrMood(Integer rMood) {
		this.rMood = rMood;
	}

	public Integer getrMeals() {
		return rMeals;
	}

	public void setrMeals(Integer rMeals) {
		this.rMeals = rMeals;
	}

	public Integer getrWater() {
		return rWater;
	}

	public void setrWater(Integer rWater) {
		this.rWater = rWater;
	}

	public String getrSteps() {
		return rSteps;
	}

	public void setrSteps(String rSteps) {
		this.rSteps = rSteps;
	}

	
	
	// 
	public void setMoodDays(Integer moodDays) {
		this.moodDays = moodDays;
	}
	
	public Integer getMoodDays() {
		return moodDays;
	}
	
	public void setMealDays(Integer mealDays) {
		this.mealDays = mealDays;
	}
	
	public Integer getMealDays() {
		return mealDays;
	}
	
	public void setWaterDays(Integer waterDays) {
		this.waterDays = waterDays;
	}
	
	public Integer getWaterDays() {
		return waterDays;
	}
	
	public void setStepDays(Integer stepDays) {
		this.stepDays = stepDays;
	}
	
	public Integer getStepDays() {
		return stepDays;
	}
	
	public void setWeekLength(Integer weekLength) {
		this.weekLength = weekLength;
	}
	
	public Integer getWeekLength() {
		return weekLength;
	}

	public Integer getMonthLength() {
		return monthLength;
	}

	public void setMonthLength(Integer monthLength) {
		this.monthLength = monthLength;
	}

	public double getdMeals() {
		return dMeals;
	}

	public void setdMeals(double dMeals) {
		this.dMeals = dMeals;
	}
	
	
}


