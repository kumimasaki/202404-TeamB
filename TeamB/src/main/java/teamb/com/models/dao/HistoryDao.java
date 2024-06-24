package teamb.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import teamb.com.models.entity.TransactionHistory;

public interface HistoryDao extends JpaRepository<TransactionHistory , Long>{
	//保存処理と更新処理
	TransactionHistory save(TransactionHistory transactionHistory);
	
	//list表示させる時使う
	//SELECT * FROM TransactionItem
	List<TransactionHistory>findAll();
	
	// 用途、商品の登録チェックｎ使用（同じ商品が登録されないようにチェック）
	@Query(value="select * from transaction_history where user_id =?1 order by history_id desc limit 1",
			nativeQuery = true)
	TransactionHistory findByUserId(Long userId);

	// 用途、削除に使用
	void deleteByHistoryId(Long historyId);

}
