package teamb.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lesson {

	//lesson_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long lessonId;
	
	//start_timeの設定
	private String startTime;
	
	//finish_timeの設定
	private String finishTime;
	
	//lesson_nameの設定
	private String lessonName;
	
	//lesson_detailの設定
	private String lessonDetail;
	
	//lesson_feeの設定
	private String lessonFee;
	
	//image_nameの設定
	private String imageName;	
	//admin_idの設定(FK)
	private Long adminId;

	//空のコンストラクタ
	public Lesson() {
	}

	//コンストラクタ(no lesson_id)
	public Lesson(String startTime, String finishTime, String lessonName, String lessonDetail, String lessonFee,
			String imageName, Long adminId) {
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.lessonName = lessonName;
		this.lessonDetail = lessonDetail;
		this.lessonFee = lessonFee;
		this.imageName = imageName;
		this.adminId = adminId;
	}

	//全てのgetter setter
	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getLessonDetail() {
		return lessonDetail;
	}

	public void setLessonDetail(String lessonDetail) {
		this.lessonDetail = lessonDetail;
	}

	public String getLessonFee() {
		return lessonFee;
	}

	public void setLessonFee(String lessonFee) {
		this.lessonFee = lessonFee;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	
	
	
}