package jp.co.ca.dao;

import java.util.List;

import jp.co.ca.entity.User;


/**
 * ユーザ管理(CRUD)を提供するDAOインターフェースです。
 * 
 * @author t.matsumoto
 *
 */
public interface UserDao {
	
	/**
	 * 指定されたidをもつユーザを削除します。
	 * 
	 * @param id 削除するid
	 * @return 削除した件数
	 */
	public int delete(int id);

	/**
	 * ユーザを全件取得します。
	 * 1件もデータが存在しない場合はサイズ0のListを返します。
	 * 
	 * @return 全件データ
	 */
	public List<User> findAll();

	/**
	 * idを元に一致するユーザを返します。
	 * idに一致するユーザが存在しなかった場合nullを返します。
	 * 
	 * @param id ユーザのid
	 * @return idが一致したユーザ または null
	 */
	public User findById(int id);
	
	/**
	 * idを元に一致するユーザを返します。
	 * idに一致するユーザが存在しなかった場合nullを返します。
	 * 
	 * @param id ユーザのid
	 * @return idが一致したユーザ または null
	 */
	public User findByName(String name);

	/**
	 * 指定されたユーザを登録します。
	 * また登録成功時に自動で設定されたidをセットします。
	 * 
	 * @param user 登録するUser
	 * @return 登録した件数
	 * @throws NullPointerException userがnullの場合
	 */
	public int insert(User user);

	/**
	 * 指定されたユーザをidに基づいて更新します。
	 * 
	 * @param user 更新するユーザ
	 * @return 更新した件数
	 * @throws NullPointerException userがnullの場合
	 */
	public int update(User user);
	
}
