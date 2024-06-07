package teamb.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamb.com.models.dao.AdminDao;
import teamb.com.models.entity.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	//保存処理（登録処理）
	public boolean createAdmin(String adminName,String adminEmail,String adminPassword) {
		//もしfindByAdminEmail == null　、登録処理します
		if(adminDao.findByAdminEmail(adminEmail) == null) {
			//saveメソッドを使用して登録処理します
			adminDao.save(new Admin(adminName,adminEmail,adminPassword));
			//保存処理ができたらtrue
			return true;
		}else {
			//そうじゃない場合、false
			return false;
		}
	}
	
	//ログイン処理
	public Admin loginCheck(String adminEmail,String adminPassword) {
		//もしfindByAdminEmailAndAdminPassword　== nullの場合
		Admin admin = adminDao.findByAdminEmailAndAdminPassword(adminEmail,adminPassword);
		if(admin == null) {
			//nullのことをコントローラーに渡す
			return null;
		}else {
			//ログインしている人の情報をコントローラーに渡す
			return admin;
		}
	}
}
