package teamb.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.services.LessonService;

@Controller
public class AdminCourseRegisterController {

	@Autowired
	private LessonService lessonService;

	@Autowired
	private HttpSession session;

	// 商品画面の表示
	@GetMapping("/admin/course/register")
	public String getCourseRegisterPage(Model model) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin == null ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		}
		// そうでない場合は、ログインしている人の名前を画面に渡す
		else {
			model.addAttribute("adminName", admin.getAdminName());
			// 商品登録のhtmlを表示する
			return "course_register.html";
		}
	}
	
	//商品の登録処理
	@PostMapping("/admin/course/register/process")
	public String courseRegisterProcess(@RequestParam String lessonName,@RequestParam String startTime,@RequestParam String finishTime,@RequestParam String lessonFee,@RequestParam MultipartFile imageName,@RequestParam String lessonDetail) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		
		//もし、admin == nullだったら、ログイン画面にリダイレクトする
		//そうでない場合、画像のファイル名を取得
		//画像のアップロード
		//もし、同じファイルの名前がなかったら保存
		//商品一覧画面にリダイレクトする
		//そうでない場合、商品登録画面に止まります
		
		if(admin == null) {
			return "redirect:/admin/login";
		}
		else {
			//ファイルの名前を取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ imageName.getOriginalFilename();
			
			//ファイルの保存処理
			try {
				Files.copy(imageName.getInputStream(),Path.of("src/main/resources/static/course-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(lessonService.createLesson(startTime, finishTime, lessonName, lessonDetail, lessonFee, fileName, admin.getAdminId())) {
				//return "redirect:/admin/course";
				return "adminRegisterSuccess.html";
			}else {
				return "course_register.html";
			}
		}
	}
}
