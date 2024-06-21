package teamb.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamb.com.models.dao.ItemDao;
import teamb.com.models.entity.TransactionItem;
import teamb.com.models.entity.Users;

@Service
public class ItemService {
	@Autowired
	private ItemDao itemDao;
	

	
	
	//商品一覧のチェック
	//もし、userId==null 戻り値としてnull
	//findAll内容をコント―ローラークラスに渡す
	public List<TransactionItem>selectAllItemList(Long userId){
		if(userId == null) {
			return null;
		}else {
			return itemDao.findAll();
		}
	}
	
	
}
