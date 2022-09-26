package kr.co.triptrip.user;

public class UserAccount {
	private String id;
	private String name;
	private String pw;
	private String salt;
	
	public UserAccount(String id, String name, String pw, String salt) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.salt = salt;
	}
	
	
	public UserAccount(String id, String name, String pw) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
	}
	
	public UserAccount(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public boolean matchPassword(String pwd) {
		return pw.equals(pwd);
	}
}
