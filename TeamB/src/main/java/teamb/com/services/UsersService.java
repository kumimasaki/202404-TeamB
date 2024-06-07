package teamb.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamb.com.models.dao.UsersDao;
import teamb.com.models.entity.Users;

@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	
	// 保存処理(登録処理)
	// もし、findByUserEmailが＝＝nullだったら登録処理
	// saveメソッドを使用して登録処理をする
	// 保存ができたらtrue
	// そうでない場合保存処理失敗 fase
	public boolean createUser(String userEmail, String userName, String userPassword , String registerDate) {
		if (usersDao.findByUserEmail(userEmail) == null) {
			usersDao.save(new Users(userEmail, userName, userPassword , registerDate));
			return true;
		} else {
			return false;
		}
	}	
	
	// ログイン処理
	// もし、email,passwordがfindByUserEmailAndUserPasswordを使用して存在しなかった場合==nullの場合
	// その場合は存在しないnullであることをコントロールクラスに知らせる
	// そうでない場合ログインしている人の情報をコントローラに渡す
	public Users loginCheck(String userEmail, String userPassword) {
		Users users = usersDao.findByUserEmailAndUserPassword(userEmail, userPassword);
		if (users == null) {
			return null;
		} else {
			return users;
		}

	}	
	
}
