//08：ユーザー登録に関する処理を行うモデル

package model.logic;

import dao.AccountDAO;
import model.data.Account;

public class PostRegisterAccountLogic {
	
	//accoountテーブルに追加
	public boolean execute(Account account) {
		AccountDAO dao = new AccountDAO();		
		return dao.addAccount(account);
		
	}
}
