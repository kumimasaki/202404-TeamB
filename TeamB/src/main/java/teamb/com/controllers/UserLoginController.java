package teamb.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Users;
import teamb.com.services.UsersService;

@Controller
public class UserLoginController {

	@Autowired
	private UsersService userService;

	// Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

	// ログイン画面の表示
	@GetMapping("/user/login")
	public String getAdminLoginPage() {
		return "user_login.html";
	}

	// ログイン処理
	@PostMapping("/user/login/process")
	public String adminLoginProcess(@RequestParam String userEmail, @RequestParam String userPassword) {

		// loginCheck メソッドを呼び出してその結果を admin という変数に格納
		Users user = userService.loginCheck(userEmail, userPassword);

		// もし、user == null ログイン画面にとどまる
		// そうでない場合は、session にログイン情報に保存
		// 講座一覧画面にリダイレクト /user/course/all
		if (user == null) {

			return "user_login.html";
		} else {

			session.setAttribute("loginAdminInfo", user);
			return "redirect:/user/course/all";
		}
	}
}
