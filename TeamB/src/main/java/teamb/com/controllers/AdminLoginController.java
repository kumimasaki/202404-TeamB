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
 public String adminLoginProcess(@RequestParam String adminEamil,@RequestParam String adminPassword) {
	 Admin admin = adminService.loginCheck(adminEamil, adminPassword);
	 if(admin == null) {
		 return "adminLogin.html";
	 }else {
		 session.setAttribute("loginUserInfo", admin);
		 return "redirect:/admin/course";
	 }
 }
}
