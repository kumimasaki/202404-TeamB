package teamb.com.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.TransactionHistory;
import teamb.com.models.entity.Users;
import teamb.com.services.HistoryService;
import teamb.com.services.ItemService;
import teamb.com.services.LessonService;

@Controller
public class UserBuyTheLessonController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private HistoryService historyService;
	
	
	//購入機能
	@PostMapping("/user/cart/buyLesson/process")
	public String BuyLesson(Model model,@RequestParam Long lessonId) {
		Users user = (Users) session.getAttribute("loginUserInfo");
		if(user==null) {
			return "redirect:/user/login";
		}else {
			List<Lesson> cartList = (List<Lesson>) session.getAttribute("cart");
			//商品の情報を取得
			Lesson lesson = lessonService.lessonEditCheck(lessonId);
			boolean result = false;
			for (Lesson a : cartList) {
				if (a.getLessonId().equals(lessonId)) {
					result=true;
					break;
				} 
			}
			if(!result) {
				cartList.add(lesson);	
			}
			model.addAttribute("cartList", cartList);
			return"user_Request.html";
		}
	}
	
	//購入確認機能
	@GetMapping("/user/request/confirm/process")
	public String ConfirmationOfPurchase(Model model, @RequestParam Long lessonId) {
		Users user = (Users) session.getAttribute("loginUserInfo");
		if(user==null) {
			return "redirect:/user/login";
		}else {
			
			List<Lesson> cartList = (List<Lesson>) session.getAttribute("cart");

			LocalDateTime transactionDate = LocalDateTime.now();
			
			historyService.createHistory(user.getUserId(), "0", "2024");
			
			TransactionHistory history = historyService.selectHistoryId(user.getUserId());
			
			for(Lesson lesson : cartList) {
				itemService.createItem(lesson.getLessonId(), history.getHistoryId());
			}
			cartList.clear();
			
			return"user_RequestSuccess.html";
		}
	}
}
