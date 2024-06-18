package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.Users;
import teamb.com.services.LessonService;

@Controller
public class UserBuyTheLessonController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonService lessonService;
	//購入機能
	@PostMapping()
	public String BuyLesson(Model model,@RequestParam Long lessonId) {
		Users user = (Users) session.getAttribute("loginUserInfo");
		if(user==null) {
			return "redirect:/user/login";
		}else {
			List<Lesson> cartList = (List<Lesson>) session.getAttribute("cart");
			//商品の情報を取得
			Lesson lesson = lessonService.lessonEditCheck(lessonId);
			cartList.add(lesson);
			model.addAttribute("cartList",cartList);
			return"user_Request.html";
			
		}
	} 
}
