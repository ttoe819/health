package model.logic;

/*
 * 画面入力値のチェックを行うモデル
 */
public class ValueCheck {

	/*
	 * 登録処理時の入力値チェック。
	 * このメソッドはチェック結果に応じて以下の内容を返す。
	 * 問題なし：空文字
	 * 問題あり：エラーメッセージ
	 */
	public static String checkAdd(String  intuserIds,
								   String  diaryId,
								   String  mood,
							       Integer meals,
							       String  water,
								   String  steps,
								   String  diary) {
		
		String errMessage = "";


		// 文字数チェック
		
		if(steps == null) {	
		}else if (steps.length() > 5) {
			errMessage += "歩数あってますか？<br>";
		}
		
		if(diary == null) {	
		}else if (diary.length() > 141) {
			errMessage += "日記の文字数は140文字までとなります<br>";
		}

		return errMessage;

	}

}
