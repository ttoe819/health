package model.logic;

public class Form {

	public String form(String str) {
		
		// 書式変換
		String year = str.substring(0, 4);
		String month = str.substring(5, 7);
		String day = str.substring(8);
		
		
		String fDiaryId = String.format("%s年 %s月 %s日", year, month, day);
		return fDiaryId;
	}
}
