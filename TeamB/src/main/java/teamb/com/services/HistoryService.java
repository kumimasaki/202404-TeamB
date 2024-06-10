package teamb.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamb.com.models.dao.HistoryDao;
import teamb.com.models.entity.TransactionHistory;

@Service
public class HistoryService {
	@Autowired
	private HistoryDao historyDao;
	
	//商品一覧のチェック
	//もし、userId==null 戻り値としてnull
	//findAll内容をコント―ローラークラスに渡す
	public List<TransactionHistory>selectAllHistoryList(Long historyId){
		if(historyId == null) {
			return null;
		}else {
			return historyDao.findAll();
		}
	}
	
	//商品の登録処理チェック
	//もし、findByHistoryIdが==nullだったら、
	//保存処理
	//true
	//そうでない場合false
	public boolean createHistory(Long historyId ,
			Long userId ,
			String amount ,
			String historyDate) {
		if(historyDao.findByHistoryId(historyId)==null) {
			historyDao.save(new TransactionHistory(userId , amount , historyDate));
			return true;
		}else {
			return false;
		}
	}
	
	
	//削除処理のチェック
	//もし、コントローラーから貰ったhistoryIdがnullだったらfalse
	//そうでない場合deleteByHistoryIdを使用して商品の削除
	public boolean deleteHistory(Long historyId) {
		if(historyId == null) {
			return false;
		}else {
			historyDao.deleteByHistoryId(historyId);
			return true;
		}
	}
}
