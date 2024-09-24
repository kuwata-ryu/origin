package schoo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * user_entryテーブルのEntityクラスです
 */
@Entity
@Table(name="user_entry")
public class UserEntry {
	// イベント参加ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_entry_id")
	private Integer userEntryId;
	public Integer getUserEntryId() {
		return userEntryId;
	}
	public void setUserEntryId(Integer userEntryId) {
		this.userEntryId = userEntryId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	// イベントID
	@Column(name = "event_id")
	private Integer eventId;
	// イベント参加者ID
	@Column(name = "user_id")
	private Integer userId;
}