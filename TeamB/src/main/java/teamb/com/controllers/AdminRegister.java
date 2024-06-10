package teamb.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import teamb.com.services.AdminService;

@Controller
public class AdminRegister {
	
	@Autowired
	private AdminService adminService;
	
	//登録画面の表示
	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {
		return "adminRegister.html";
	}
	
	//登録処理
	@PostMapping("/admin/register/process")
	public String adminRegisterProcess(@RequestParam String userName,@RequestParam String password,@RequestParam String email) {
		//もし、createAdminがtrueなら、adminLogin.htmlに移行する
		//そうでない場合、adminRegister.htmlに止まり.
		if(adminService.createAdmin(userName, email, password)) {
			return "adminLogin.html";
		}else {
			return "adminRegister.html";
		}
	}
}
