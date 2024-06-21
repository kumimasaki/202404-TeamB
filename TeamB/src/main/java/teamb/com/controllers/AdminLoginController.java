package teamb.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.services.AdminService;

@Controller
public class AdminLoginController {
 @Autowired
 private AdminService adminService;
 
 @Autowired
 private HttpSession session;
 
 //ログイン画面の表示
 @GetMapping("/admin/login")
 public String getAdminLoginPage() {
	 return "adminLogin.html";
 }
 
 //ログイン処理
 @PostMapping("/admin/login/process")
 public String adminLoginProcess(@RequestParam String email,@RequestParam String password) {
	//loginCheckメソッドを呼び出した結果をadminに格納する
	 Admin admin = adminService.loginCheck(email, password);
	//もし、admin==null、adminLogin.htmlへ行く
	//そうでない
	//　"/admin/course"へ行く
	 if(admin == null) {
		 return "adminLogin.html";
	 }else {
		 session.setAttribute("loginAdminInfo", admin);
		 return "redirect:/admin/course";
	 }
 }
}