package teamb.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teamb.com.models.dao.LessonDao;
import teamb.com.models.entity.Lesson;

@Service
public class LessonService {
	@Autowired
	private LessonDao lessonDao;
	
	//lessonListのチェック
	public List<Lesson>selectAllLessonList(Long adminId){
		if(adminId==null) {
			return null;
		}else {
			return lessonDao.findAll();
		}
	}
	
	//lessonの登録処理のチェック
	public boolean createLesson(String startTime, 
			String finishTime, 
			String lessonName, 
			String lessonDetail, 
			String lessonFee,
			String imageName, 
			String registerDate, 
			Long adminId) {
		if(lessonDao.findByLessonName(lessonName)==null) {
			lessonDao.save(new Lesson(startTime,finishTime,lessonName,lessonDetail,lessonFee,imageName,registerDate,adminId));
			return true;
		}else {
			return false;
		}
	}
	
	//編集画面の表示のチェック
	public Lesson lessonEditCheck(Long lessonId) {
		if(lessonId==null) {
			return null;
		}else {
			return lessonDao.findByLessonId(lessonId);
		}
	}
	
	//更新処理のチェック
	public boolean lessonUpdate(Long lessonId,
			String startTime,
			String finishTime, 
			String lessonName, 
			String lessonDetail, 
			String lessonFee,
			String imageName, 
			String registerDate, 
			Long adminId) {
		if(lessonId==null) {
			return false;
		}else {
			Lesson lesson = lessonDao.findByLessonId(lessonId);
			lesson.setStartTime(startTime);
			lesson.setFinishTime(finishTime);
			lesson.setLessonName(lessonName);
			lesson.setLessonDetail(lessonDetail);
			lesson.setLessonFee(lessonFee);
			lesson.setImageName(imageName);
			lesson.setRegisterDate(registerDate);
			lesson.setAdminId(adminId);
			lessonDao.save(lesson);
			return true;
		}
	}
	
	//削除処理
	public boolean deleteLesson(Long lessonId) {
		if(lessonId==null) {
			return false;
		}else {
			lessonDao.deleteByLessonId(lessonId);
			return true;
		}
	}
}
