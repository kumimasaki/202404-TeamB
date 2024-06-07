package teamb.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

	//admin_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long adminId;
	
	//admin_name
	private String adminName;
	
	//admin_email
	private String adminEmail;
	
	//admin_password
	private String adminPassword;

	//空のコンストラクタ
	public Admin() {
	}

	//コンストラクタ(no adminId)
	public Admin(String adminName, String adminEmail, String adminPassword) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}

	//全てのgetterとsetter
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	

	
}
