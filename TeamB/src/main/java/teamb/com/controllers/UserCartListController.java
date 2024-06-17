package teamb.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCartListController {
	
	// ログイン画面の表示
	@GetMapping("/user/cart")
	public String getUsercartlistPage() {
		return "user_cart.html";
	}

}
