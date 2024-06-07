package teamb.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionItem {

	//item_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long itemId;
	
	//lesson_idの設定
	private Long lessonId;
	
	//history_idの設定
	private Long historyId;

	//空のコンストラクタ
	public TransactionItem() {
	}

	//コンストラクタ(no item_id)
	public TransactionItem(Long lessonId, Long historyId) {
		this.lessonId = lessonId;
		this.historyId = historyId;
	}

	//全てのgetter setter
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}
	
	
	
	
	
}
