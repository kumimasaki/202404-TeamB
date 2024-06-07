package teamb.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionHistory {

	//history_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long historyId;
	
	//user_Idの設定
	private Long userId;
	
	//amountの設定
	private String amount;
	
	//history_dateの設定
	private String historyDate;

	//空のコンストラクタ
	public TransactionHistory() {
	}

	//コンストラクタ(no history_id)
	public TransactionHistory(Long userId, String amount, String historyDate) {
		this.userId = userId;
		this.amount = amount;
		this.historyDate = historyDate;
	}

	//全てのgetter setter
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getHistoryDate() {
		return historyDate;
	}

	public void setHistoryDate(String historyDate) {
		this.historyDate = historyDate;
	}
	
	
	
	
}
