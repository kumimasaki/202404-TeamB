package teamb.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamb.com.services.UsersService;

@Controller
public class UserRegisterController {

	@Autowired
	private UsersService userService;
	
	//登録画面の表示
	@GetMapping("/user/register")
	public String getUserRegisterPage() {
		return "user_register.html";
	}
	
	//登録処理
	@PostMapping("/user/register/process")
	public String userRegisterProcess(@RequestParam String userName,
									  @RequestParam String userEmail,
									  @RequestParam String userPassword,
									  @RequestParam String registerDate)
	{
		
		//もし、createAdminがtrueなら、adminLogin.htmlに移行する
		//そうでない場合、adminRegister.htmlに止まり.
		if(userService.createUser(userName, userEmail, userPassword, registerDate)) {
			return "user_login.html";
		}else {
			return "user_register.html";
		}
	}
}
