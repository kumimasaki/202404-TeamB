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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Admin;
import teamb.com.models.entity.Lesson;
import teamb.com.services.LessonService;

@Controller
public class AdminCourseEditController {

	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private HttpSession session;
	
	//編集画面の表示
	@GetMapping("/admin/course/edit/{lessonId}")
	public String getCourseEditPage(@PathVariable Long lessonId, Model model) {
		//セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし admin==nul1 ログイン画面にリダイレクトする
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//編集画面に表示させる情報を変数に格納 lesson
			Lesson lesson = lessonService.lessonEditCheck(lessonId);
			//もしlesson==nullだったら、講座一覧ペ-ジこリリダイレクトする
			if(lesson == null) {
				return "redirect:/admin/course";
			} else {
				//そうでない場合、編集画面に編集する内容を渡す
				//編集画面を表示
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("lesson", lesson);
				return "course_edit.html";
				
			}

		}
	}
	
	//更新処理
	@PostMapping("/admin/course/edit/process")
	public String lessonUpdate(
								@RequestParam String courseTime, 
								@RequestParam String courseName,
								@RequestParam String courseDetails,
								@RequestParam String coursePrice,
								@RequestParam MultipartFile courseImage,
								@RequestParam Long lessonId) {
		//セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし、admin == nullだったら、ログイン画面ににリダイレクトする	
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			//そうでない場合、
			/*
			* 現在の日時情報を元に、フアイル名を作成しています。simpleDateFormatクラスを使用して、日時のフオ-マットを指定している
			*具体的(は、"yyyy-MM-dd-HH-mm-ss-"の形式でフオ-マットされた文字列を取得している
			*その後、blogImageオプジエクトから元のフアイル名を取得し、フオ-マットされた日時文字列と連結して、fileName变数に代入
			*/
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ courseImage.getOriginalFilename();
			//フアイルの保存
			try {
				Files.copy(courseImage.getInputStream(), Path.of("src/main/resources/static/lesson-img/"+ fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//もし、lessonUpdateの結果がtrueの場合は、講座一覧にリダイレクト
			if(lessonService.lessonUpdate(lessonId, courseTime, courseTime, courseName, courseDetails, coursePrice, fileName, coursePrice, admin.getAdminId())) {
				return "redirect:/admin/course";
			} else {
				//そうでない場合、講座編集画面ににリダイレクトする
				return "redirect:/admin/course/edit"+lessonId;
			}
		}

	}
	
}
