package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.Users;
import teamb.com.services.LessonService;

@Controller
public class UserHistoryController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("/user/history")
	public String getUserCourseAll(Model model) {
		
		Users user = (Users) session.getAttribute("loginUserInfo");
		
		if(user==null) {
			return "redirect:/user/login";
		}else {

			List<Lesson> historyList = lessonService.selectAllLessonList(null);
			model.addAttribute("historyList", historyList);
			return"user_course_history.html";
		}
	}
}
