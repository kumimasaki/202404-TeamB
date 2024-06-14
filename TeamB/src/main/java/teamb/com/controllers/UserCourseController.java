package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.dao.LessonDao;
import teamb.com.models.entity.Lesson;
import teamb.com.models.entity.Users;

@Controller
public class UserCourseController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LessonDao lessonDao;
	
	@GetMapping("/user/course/all")
	public String getUserCourseAll(Model model) {
		
		Users user = (Users) session.getAttribute("loginUserInfo");
		
		List<Lesson> lessonList = lessonDao.findAll();
		model.addAttribute("lessonList", lessonList);
		
		if(user == null) {
			model.addAttribute("userName", null);	
			return "user_list.html";
		} else {
			model.addAttribute("userName", user.getUserName());
			
			return "user_list.html";
		}
	}
	
	@GetMapping("/user/course/{lessonId}")
	public String getUserCourse(Model model) {
		
		Users user = (Users) session.getAttribute("loginUserInfo");
		
		List<Lesson> lessonList = lessonDao.findAll();
		model.addAttribute("lessonList", lessonList);
		
		if(user == null) {
			model.addAttribute("userName", null);
			
			return "user_course.html";
		} else {
			model.addAttribute("userName", user.getUserName());
			
			return "user_course.html";
		}
	}
}
