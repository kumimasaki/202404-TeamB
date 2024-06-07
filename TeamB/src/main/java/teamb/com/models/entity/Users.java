package teamb.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

	//user_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	//user_nameの設定
	private String userName;
	
	//user_emailの設定
	private String userEmail;
	
	//user_passwordの設定
	private String userPassword;
	
	//register_dateの設定
	private String registerDate;

	//空のコンストラクタ
	public Users() {
	}

	//コンストラクタ(no user_id)
	public Users(String userName, String userEmail, String userPassword, String registerDate) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.registerDate = registerDate;
	}

	//全てのgetter setter
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
	
}
