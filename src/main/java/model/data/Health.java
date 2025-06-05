package model.data;

import java.io.Serializable;

public class Health implements Serializable {
	
	private String userId;
	private String diaryId;
	private Integer mood;
	private Integer meals;
	private Integer water;
	private String steps;
	private String diary;
	
	public Health() {}
	
	public Health(String userId) {		
		this.userId = userId;		
	}

//	public Health(String diaryId) {		
//		this.diaryId = diaryId;		
//	}

	public Health(String userId, String diaryId, Integer mood, Integer meals, Integer water, String steps, String diary) {
		this.userId = userId;
		this.diaryId = diaryId;
		this.mood = mood;
		this.meals = meals;
		this.water = water;
		this.steps = steps;
		this.diary = diary;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public Integer getMood() {
		return mood;
	}

	public void setMood(Integer mood) {
		this.mood = mood;
	}

	public Integer getMeals() {
		return meals;
	}

	public void setMeals(Integer meals) {
		this.meals = meals;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getDiary() {
		return diary;
	}

	public void setDiary(String diary) {
		this.diary = diary;
	}
	
}
