package teamb.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teamb.com.models.entity.Users;

@Repository
public interface UsersDao extends JpaRepository<Users , Long>{
	// 保存処理と更新処理 insert と update
	Users save(Users users);
	
	// SELECT * FROM users WHERE userEmail= ?
	// 用途、管理者の登録処理をする時に、同じメールアドレスがあったならば登録させないようにする
	// 1行しかレコードは取得できない
	Users findByUserEmail(String userEmail);
	
	// SELECT +FROM users WHERE userEmail = ? AND userpassword = ?
	// 用途、ログイン処理に使用。入力したメールアドレスとパスワードが一致いているときだけデータを取得
	Users findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
}
