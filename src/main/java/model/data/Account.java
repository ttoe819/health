//05:ユーザーに関する情報JavaBeans

package model.data;

import java.io.Serializable;

public class Account implements Serializable{
	
	
	//フィールド：ログイン情報（ユーザー名・パス）
	private String userId;
	private String name;
	private String pass;
	
	
	//コンストラクタ
	public Account() {}
	public Account(String name, String pass) {
		
		this.userId = name.replaceAll("[^0-9]", "");
		this.name = name;
		this.pass = pass;

	}
	
	
	//getter
	public String getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}
		
	public String getPass() {
		return pass;
	}
	
	
	//setter
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public void setPass(String pass) {
		this.pass = pass;		
	}
	
}
