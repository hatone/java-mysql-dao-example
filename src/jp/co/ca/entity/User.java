package jp.co.ca.entity;

/**
 * Userテーブルに対応したエンティティです。
 * @author t.matsumoto
 *
 */
public class User {

	private int id;
	private String name;
	private String tel;
	private String passwd;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}