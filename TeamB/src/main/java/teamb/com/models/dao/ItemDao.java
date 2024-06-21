package teamb.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import teamb.com.models.entity.TransactionItem;

@Repository
public interface ItemDao extends JpaRepository<TransactionItem , Long>{
	//保存処理と更新処理
	TransactionItem save(TransactionItem transactionItem);
	
	@Query(value = "SELECT l.lesson_id "
				 + "FROM transaction_item i "
				 + "LEFT JOIN lesson l ON l.lesson_id = i.lesson_id "
				 + "WHERE l.lesson_id = i.lesson_id "
				 + "ORDER BY l.lesson_id ",
				 nativeQuery = true)
	
	//list表示させる時使う
	//SELECT * FROM TransactionItem
	List<TransactionItem>findAll();
	
}
