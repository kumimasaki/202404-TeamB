package teamb.com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.Users;
import teamb.com.services.LessonService;

@Controller
public class UserCartController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonService lessonService;
	
	// カート画面の表示
	@GetMapping("/user/cart")
	public String getUsercartlistPage() {
		return "user_cart.html";
	}	
	
	//カートの追加機能
	@PostMapping("/user/cart/process")
	public String getCartPage(Model model,@RequestParam Long lessonId) {
		Users user = (Users) session.getAttribute("loginUserInfo");
		if(user==null) {
			return "redirect:/user/login";
		}else {	
			if(session.getAttribute("cart")==null) {
				List<Lesson> cartList = new ArrayList<Lesson>();
				//商品の情報を取得
				Lesson lesson = lessonService.lessonEditCheck(lessonId);
				cartList.add(lesson);
				session.setAttribute("cart", cartList);
				model.addAttribute("cartList",cartList);
				return "user_cart.html";
			}else {
				//２回目の処理
				//sessionからカートの情報を取得する
				List<Lesson> cartList = (List<Lesson>) session.getAttribute("cart");
				//商品の情報を取得
				Lesson lesson = lessonService.lessonEditCheck(lessonId);
				cartList.add(lesson);
				model.addAttribute("cartList",cartList);
				return"user_cart.html";
			}
					
		}
	}
	
}
