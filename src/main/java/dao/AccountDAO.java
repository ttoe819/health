//06：accountテーブルDAO
//GetRegisterAccountLogicから呼び出し

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.data.Account;

public class AccountDAO {
	
	//データベース接続情報
	private final String URL = "jdbc:postgresql://localhost:5432/health";
	private final String USER = "postgres";
	private final String PASS = "test";

	
	//コンストラクタ
	public AccountDAO() {		
		//JDBCドライバの準備
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		  }
		}


	//ユーザ登録：user_idはSERIAL(自動採番)
	public boolean addAccount(Account account) {
	
		String userId = null;


		//INSERT文準備	
		String sql = "";
		sql = "INSERT INTO account(name, pass) ";
		sql += "VALUES(?, ?) returning user_id;";


		//データベース接続
		try (Connection conn = DriverManager.getConnection(URL,USER,PASS)) {
			PreparedStatement pStmt = conn.prepareStatement(sql);
				
			//INSERT文中「?」の使用する値を設定してSQL文を完成
			pStmt.setString(1, account.getName());
			pStmt.setString(2, account.getPass());


		//INSERT文実行・user_idを取得
			ResultSet rs = pStmt.executeQuery();
		
				rs.next();
				userId = rs.getString("user_id");
				
				account.setUserId(userId);
		
					
			if (userId == null) {
					return false;
			}
				} catch (SQLException e) {
						e.printStackTrace();
						return false;
				}
						return true;						
	}	
	
	
	//アカウント情報取得
	public boolean searchAccount(Account account) {
		
		String userId = null;
		String name = null;
		String pass = null;
		String actTotal = null;

		
		String strName = account.getName().replaceAll("[0-9]", "");
		Integer intuserId = Integer.parseInt(account.getName().replaceAll("[^0-9]", ""));
		String actLogin = account.getName() + account.getPass();


		//SELECT文準備
		String sql = "";
		sql += "SELECT * FROM account ";
		sql += "WHERE  user_id = ? ";
		sql += "AND name = ? AND pass = ? ;";			
				
	
		//データベース接続
		try (Connection conn = DriverManager.getConnection(URL,USER,PASS)) {			
				PreparedStatement pStmt = conn.prepareStatement(sql);
					
				
				//SELECT文中「?」の使用する値を設定してSQL文を完成
				pStmt.setInt(1, intuserId);
				pStmt.setString(2, strName);
				pStmt.setString(3, account.getPass());
						
						
				//SELECT文実行・結果を取得
				ResultSet rs = pStmt.executeQuery();
				
		
				while (rs.next()) {	
					userId = rs.getString("user_id");
					name = rs.getString("name");
					pass = rs.getString("pass");
									
					actTotal = name + userId + pass;
					
				}
									
					}catch (SQLException e) {
						e.printStackTrace();
						return false;
					}	
		
					if (actLogin.equals(actTotal)) {
					//		account.setUserId(userId);
							return true;
														
					} else {
							return false;
					}						
		}	
}


