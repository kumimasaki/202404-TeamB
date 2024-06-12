package teamb.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCourseController {

	// ログイン画面の表示
	@GetMapping("/user/course/all")
	public String getAdminLoginPage() {
		return "user_list.html";
	}
}
