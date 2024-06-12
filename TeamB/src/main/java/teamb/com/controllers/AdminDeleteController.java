package teamb.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.services.LessonService;

@Controller
public class AdminDeleteController {
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/admin/course/delete/process")
	public String lessonDelete(Long lessonId) {
		 Admin admin = (Admin)session.getAttribute("loginAdminInfo");
		 if(admin==null) {
			 return "redirect:/admin/login";
		 }else {
			//もし、deleteLesson==true、削除する
				//管理者講座削除確認画面へ行く
				//そうでない
				//lessonEditへ行く 
			 if(lessonService.deleteLesson(lessonId)) {
				 return "redirect:/admin/course";
			 }else {
				 return "redirect:/admin/course/edit"+lessonId;
			 }
		 }
	}
}
