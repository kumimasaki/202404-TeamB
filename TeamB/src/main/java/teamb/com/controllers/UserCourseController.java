package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.models.entity.Lesson;
import teamb.com.services.LessonService;

@Controller
public class UserCourseController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("/user/course/all")
	public String getUserCourseList(Model model) {
		//セッションからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		
		//もし、admin==null ログイン画面ににリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
		//そうでない場合
		//ログインしている人の名前の情報を画面に渡して講座一覧のhtmlを表示
			//講座の情報を取得する
			List<Lesson> lessonList = lessonService.selectAllLessonList(admin.getAdminId());
			
			model.addAttribute("adminName", admin.getAdminName());
			model.addAttribute("lessonList", lessonList);
			return "user_list.html";
		}
	}
}
