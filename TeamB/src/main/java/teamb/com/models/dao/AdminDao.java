package teamb.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teamb.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {

//	保存処理と更新処理
	Admin save(Admin admin);
	
//	管理者の登録処理をする時、同じEmailなら登録させない
	Admin findByAdminEmail(String adminEmail);
	
//	ログイン処理に使用、EmailとPassが一致することを確認
	Admin findByAdminEmailAndAdminPassword(String adminEmail , String adminPassword);
}
