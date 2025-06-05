package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.data.Health;
import model.data.HealthNullData;
import model.data.HealthResult;

public class HealthDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/health";
    private final String USER = "postgres";
    private final String PASSWORD = "test";   


    // コンストラクタ
	public HealthDAO() {
		/* JDBCドライバの準備 */
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public boolean exCheck(Health health) {
		
	        boolean exCheck = false;		// 既存記録有無


			/* 1) SQL文の準備 */

			String sql = "";
			sql  = "SELECT EXISTS ";
			sql += "(SELECT * ";
			sql += "FROM health ";
			sql += "WHERE user_id = ? ";
			sql += "AND diary_id = ?) ";
			sql += "AS health_check;";			
			
			
			/* 2) PostgreSQLへの接続 */
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
				
				//日付設定
				Date sqlDiaryId= Date.valueOf(health.getDiaryId());

		        
				/* 3) SQL文の?部分を置き換え */		
				st.setString(1, health.getUserId());
				st.setDate	(2, sqlDiaryId);


				/* 4) SQL文の実行 */
				ResultSet rs = st.executeQuery();
				
				 if (rs.next()) {
					 exCheck = rs.getBoolean("health_check");  // 結果取得
			      }
				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}

			return exCheck;
	    }


	public int insert(Health health) {
        int insCnt = 0;		// 更新件数

		/* 1) SQL文の準備 */
		String sql = "";
		sql = "INSERT INTO health(user_id, diary_id, mood, meals, water, steps, diary) ";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?);";

		/* 2) PostgreSQLへの接続 *///
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement st = con.prepareStatement(sql);) {
			
			
			//日付設定
			Date sqlDiaryId= Date.valueOf(health.getDiaryId());

			
			/* 3) SQL文の?部分を置き換え */
			st.setString(1, health.getUserId());
			st.setDate	(2, sqlDiaryId);
			st.setObject(3, health.getMood());
			st.setObject(4, health.getMeals());
			st.setObject(5, health.getWater());
			st.setString(6, health.getSteps());
			st.setString(7, health.getDiary());


			/* 4) SQL文の実行 */
			insCnt = st.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return insCnt;
	}


	public int update(Health health, HealthNullData healthNullData) {
        int insCount = 0;		// 更新件数

		/* 1) SQL文の準備 */
		String sql = "";
		sql =  "UPDATE health  ";
		sql += "SET  mood = ?, ";
		sql += "    meals = ?, ";
		sql += "    water = ?, ";
		sql += "    steps = ?, ";
		sql += "    diary = ?  ";
		sql += "WHERE user_id = ? ";
		sql += "AND  diary_id = ?;";

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement st = con.prepareStatement(sql);) {


			//日付設定
			Date sqlDiaryId= Date.valueOf(health.getDiaryId());

			
			/* 3) SQL文の?部分を置き換え */
			if (healthNullData.getNullMood() != null) {
				st.setNull   (1, java.sql.Types.INTEGER);
			} else {
				st.setInt   (1, health.getMood());
			}
			
			if (healthNullData.getNullMeals() != null) {
				st.setNull   (2, java.sql.Types.INTEGER);
			} else {
				st.setInt   (2, health.getMeals());
			}
			
			if (healthNullData.getNullWater() != null) {
				st.setNull   (3, java.sql.Types.INTEGER);
			} else {
				st.setInt   (3, health.getWater());
			}
			
			if (healthNullData.getNullSteps() != null) {
				st.setNull   (4, java.sql.Types.VARCHAR);
			} else {
				st.setString   (4, health.getSteps());
			}
			
			if (healthNullData.getNullDiary() != null) {
				st.setNull   (5, java.sql.Types.VARCHAR);
			} else {
				st.setString   (5, health.getDiary());
			}
//			st.setInt   (2, health.getMeals());
//			st.setInt   (3, health.getWater());
//			st.setString(4, health.getSteps());
//			st.setString(5, health.getDiary());
			st.setString(6, health.getUserId());
			st.setDate	(7, sqlDiaryId);


			/* 4) SQL文の実行 */
			insCount = st.executeUpdate();

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return insCount;
    }


	public int delete(String userId, String diaryId) {
	        int delCount = 0;		// 削除件数


			/* 1) SQL文の準備 */

			String sql = "";
			sql =  "DELETE FROM Health ";
			sql += "WHERE user_id = ? ";
			sql += " AND diary_id = ?;";

			/* 2) PostgreSQLへの接続 */
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
				
				//日付設定
				Date sqlDiaryId= Date.valueOf(diaryId);

		        
				/* 3) SQL文の?部分を置き換え */
				st.setString(1, userId);
				st.setDate	(2, sqlDiaryId);


				/* 4) SQL文の実行 */
				delCount = st.executeUpdate();

			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}

			return delCount;
	    }


	public List<Health> select(String userId, String diaryId) {
        List<Health> healthList = null;
        
        String sql = "SELECT * ";
		sql += "FROM Health ";
		sql += "WHERE user_id = ? ";
		sql += "AND  diary_id = ?; ";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			//日付設定
			Date sqlDiaryId= Date.valueOf(diaryId);
			
			
				/* 3) SQL文の?部分を置き換え */
				st.setString(1, userId);
				st.setDate	(2, sqlDiaryId);
				/* 4) SQL文の実行 */
				ResultSet rs = st.executeQuery();

				/* 5) 結果をリストに移し替える */
				healthList = makeHealthList(rs);

			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		return healthList;
		}


	public List<HealthResult> weekselect(String userId, String week) throws Exception{
        List<HealthResult> healthList = null;
        
         String str = week;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sf.parse(str);

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DAY_OF_MONTH, 6);
        java.util.Date future = c.getTime();

        
        String sql = "SELECT * ";
		sql += "FROM Health ";
		sql += "WHERE user_id = ? ";
		sql += "AND diary_id >= ? ";
		sql += "AND diary_id <= ? ;";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

				/* 3) SQL文の?部分を置き換え */
				st.setString(1, userId);
				st.setDate(2, new java.sql.Date(d.getTime()));
				st.setDate(3, new java.sql.Date(future.getTime()));

				/* 4) SQL文の実行 */
				ResultSet rs = st.executeQuery();

				/* 5) 結果をリストに移し替える */
				healthList = makeHealthResultList(rs);
				

			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		return healthList;
		}


	public List<HealthResult> monthselect(String userId, String month) throws Exception{
		List<HealthResult> healthList = null;
	 
		String str = month;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			java.util.Date d = sf.parse(str);
			
			
			Calendar c = Calendar.getInstance();
	        c.setTime(d);
	        c.add(Calendar.MONTH, 1);
	        java.util.Date d1 = c.getTime();
	
	
		String sql = "SELECT * ";
		sql += "FROM Health ";
		sql += "WHERE user_id = ? ";
		sql += "AND diary_id >= ? ";
		sql += "AND diary_id <   ?; ";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			
				/* 3) SQL文の?部分を置き換え */
				st.setString(1, userId);
				st.setDate(2, new java.sql.Date(d.getTime()));							
				st.setDate(3, new java.sql.Date(d1.getTime()));		
			
				/* 4) SQL文の実行 */
				ResultSet rs = st.executeQuery();

				/* 5) 結果をリストに移し替える */
				healthList = makeHealthResultList(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}	
		
	return healthList;
	
}


	// 週・月の結果用のリスト
	public ArrayList<HealthResult> makeHealthResultList(ResultSet rs) throws Exception {

		// 全検索結果を格納するリストを準備
		ArrayList<HealthResult> healthList = new ArrayList<HealthResult>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String userId = rs.getString("user_id");
			String diaryId = rs.getString("diary_id");
			int mood = rs.getInt("mood");
			int meals = rs.getInt("meals");
			int water = rs.getInt("water");
			String steps = rs.getString("steps");
			
			
			// 1行分のデータを格納するインスタンス
			HealthResult health = new HealthResult(userId,
										diaryId,
										mood,
										meals,
										water,
										steps);

			// リストに1行分のデータを追加する
			healthList.add(health);
		}
		// テストメッセージ
		
		return healthList;
	}

	
	public ArrayList<Health> makeHealthList(ResultSet rs) throws Exception {


		// 結果を格納するリストを準備
		ArrayList<Health> healthList = new ArrayList<Health>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String  userId   = rs.getString("user_id");
			String  diaryId  = rs.getString("diary_id");
			Integer mood    = rs.getInt("mood");
			Integer meals   = rs.getInt("meals");
			Integer water   = rs.getInt("water");
			String  steps    = rs.getString("steps");
			String  diary    = rs.getString("diary");
			
//			if (rs.wasNull()) { 
//			    mood = 0; // or some default value
//			    meals = 0;
//			    water = 0;
//			    steps = "0"; // or some default value
//			    diary = "情報ありません"; // or some default text
//			}
			
			
			// 1行分のデータを格納するインスタンス
			Health health = new Health(userId,
									                 diaryId,
									                 mood,
									                 meals,
									                 water,
									                 steps,
									                 diary);

			// リストに1行分のデータを追加する
			healthList.add(health);
		}
				return healthList;
	}
	
		
	public boolean weekCheck(Health health) {
		
        boolean exCheck = false;		// 既存記録有無


		/* 1) SQL文の準備 */

		String sql = "";
		sql  = "SELECT EXISTS ";
		sql += "(SELECT * ";
		sql += "FROM health ";
		sql += "WHERE user_id = ? ";
		sql += "AND diary_id >= ? ";
		sql += "AND diary_id <= ?) ";
		sql += "AS health_check;";			
		
		
		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement st = con.prepareStatement(sql);) {
			
			// 週初めの日付設定
			Date sqlDiaryId= Date.valueOf(health.getDiaryId());
			
			// 週終わりの日付設定
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sf.parse(health.getDiaryId());
			
			Calendar c = Calendar.getInstance();
	        c.setTime(d);
//	        int day = c.get(Calendar.DAY_OF_MONTH);
	        c.add(Calendar.DAY_OF_MONTH, 6);
	        java.util.Date future = c.getTime();
	        
	        Date end = Date.valueOf(sf.format(future));
	        

	        
			/* 3) SQL文の?部分を置き換え */		
			st.setString(1, health.getUserId());
			st.setDate	(2, sqlDiaryId);
			st.setDate  (3, end);


			/* 4) SQL文の実行 */
			ResultSet rs = st.executeQuery();
			
			 if (rs.next()) {
				 exCheck = rs.getBoolean("health_check");  // 結果取得
		      }
			
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return exCheck;
    }
	 
	public boolean monthCheck(Health health) {
		
        boolean exCheck = false;		// 既存記録有無


		/* 1) SQL文の準備 */

		String sql = "";
		sql  = "SELECT EXISTS ";
		sql += "(SELECT * ";
		sql += "FROM health ";
		sql += "WHERE user_id = ? ";
		sql += "AND diary_id >= ? ";
		sql += "AND diary_id < ?) ";
		sql += "AS health_check;";			
		
		
		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement st = con.prepareStatement(sql);) {
			
			// 月初めの日付設定
//			Date sqlDiaryId= Date.valueOf(health.getDiaryId());
			
			// 次の月初めの日付設定
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			java.util.Date startMonth = sf.parse(health.getDiaryId());
			
			Calendar c = Calendar.getInstance();
	        c.setTime(startMonth);
	        c.add(Calendar.MONTH, 1);
	        
	        
	        java.util.Date endMonth = c.getTime();
	        
//	        Date end = Date.valueOf(sf.format(future));
	        

	        
			/* 3) SQL文の?部分を置き換え */		
			st.setString(1, health.getUserId());
			st.setDate	(2, new java.sql.Date(startMonth.getTime()));
			st.setDate  (3, new java.sql.Date(endMonth.getTime()));


			/* 4) SQL文の実行 */
			ResultSet rs = st.executeQuery();
			
			 if (rs.next()) {
				 exCheck = rs.getBoolean("health_check");  // 結果取得
		      }
			
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return exCheck;
    }
}