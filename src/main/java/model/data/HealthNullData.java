package model.data;

import java.io.Serializable;

public class HealthNullData implements Serializable {

	private String nullMood;
	private String nullMeals;
	private String nullWater;
	private String nullSteps;
	private String nullDiary;
	
	public HealthNullData() {}
	
	
	
	public HealthNullData(String nullMood, 
						  String nullMeals, 
						  String nullWater, 
						  String nullSteps, 
						  String nullDiary) {
		
		this.nullMood = nullMood;
		this.nullMeals = nullMeals;
		this.nullWater = nullWater;
		this.nullSteps = nullSteps;
		this.nullDiary = nullDiary;
	}



	public String getNullMood() {
		return nullMood;
	}



	public void setNullMood(String nullMood) {
		this.nullMood = nullMood;
	}



	public String getNullMeals() {
		return nullMeals;
	}



	public void setNullMeals(String nullMeals) {
		this.nullMeals = nullMeals;
	}



	public String getNullWater() {
		return nullWater;
	}



	public void setNullWater(String nullWater) {
		this.nullWater = nullWater;
	}



	public String getNullSteps() {
		return nullSteps;
	}



	public void setNullSteps(String nullSteps) {
		this.nullSteps = nullSteps;
	}



	public String getNullDiary() {
		return nullDiary;
	}



	public void setNullDiary(String nullDiary) {
		this.nullDiary = nullDiary;
	}
	
	
}
