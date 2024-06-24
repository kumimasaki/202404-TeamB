package teamb.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import teamb.com.models.entity.Lesson;

@Repository
@Transactional
public interface LessonDao extends JpaRepository<Lesson, Long> {
//保存処理と更新処理
	Lesson save(Lesson lesson);
	
	//list表示させる時使う
	List<Lesson>findAll();
	
	//同じ商品が登録できない
	Lesson findByLessonName(String lessonName);
	
	//編集のlessonIdを使う
	Lesson findByLessonId(Long lessonId);
	
	//削除する時に使う
	void deleteByLessonId(Long lessonId);
	
	@Query(value="select c.* from transaction_history a join transaction_item b on a.history_id=b.history_id join lesson c on b.lesson_id=c.lesson_id where user_id=?1",nativeQuery = true)
	List<Lesson>findByUserId(Long userId);
}
