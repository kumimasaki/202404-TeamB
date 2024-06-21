package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.dao.ItemDao;
import teamb.com.models.dao.LessonDao;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.TransactionItem;
import teamb.com.models.entity.Users;
import teamb.com.services.ItemService;
import teamb.com.services.LessonService;

@Controller
public class UserHistoryController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonDao lessonDao;
	
	@GetMapping("/user/history")
	public String getUserHistory(Model model) {
		
		Users user = (Users) session.getAttribute("loginUserInfo");
		
		if(user==null) {
			return "redirect:/user/login";
		}else {
//			Lesson lesson = lessonDao.findByLessonId(null);
			List<Lesson> lessonList = lessonDao.findAll();
			model.addAttribute("lessonList", lessonList);			
			model.addAttribute("userName", user.getUserName());
			return"user_course_history.html";
		}
	}
}
