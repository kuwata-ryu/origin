package schoo.form;

import lombok.Data;
/**
 * イベント情報のFormクラスです
 */
@Data
public class EventForm {
	// タスク名
	private String eventName;
	// タスク開催日
	private String eventStartDate;
	// タスク開催日
	private String eventEndDate;
	// タスク開催日
	private String filePath;
	// タスク開催日
	private int userId;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}