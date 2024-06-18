package teamb.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import teamb.com.models.entity.Lesson;

@Controller
public class UserCartDeleteController {
	@Autowired
	private HttpSession session;

	@PostMapping("/user/cart/process/delete")
	public String cartDelent(Model model, @RequestParam Long lessonId) {
		// セッションからログインしている人の情報をuserという変数に格納
		User user = (User) session.getAttribute("loginUserInfo");
		// もし user == null ログイン画面に戻る
		if (user == null) {
			return "redirect:/user/login";
		} else {
			// 順番で検索する
			int index = 0;
			// sessionからカートの情報を取得する
			List<Lesson> cartList = (List<Lesson>) session.getAttribute("cart");
			for (Lesson a : cartList) {
				if (a.getLessonId().equals(lessonId)) {
					cartList.remove(index);
					break;
				}
				index++;
			}
			return "redirect：/user/cart/process";
		}
	}
}