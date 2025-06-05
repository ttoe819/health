//02：ログインに関する処理を行うモデル

package model.logic;

import dao.AccountDAO;
import model.data.Account;

public class TopLogic {
	
	//ログイン情報が一致しているか確認
		public boolean execute(Account account) {
			
			
			//accountテーブルよりアカウント情報取得
			AccountDAO dao = new AccountDAO();
			boolean isLogin = dao.searchAccount(account);
			
			return isLogin;

		}

}
