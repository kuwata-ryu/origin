package schoo.form;

import lombok.Data;
/**
 * イベント参加情報のFormクラスです
 */
@Data
public class UserEntryForm {
	// イベントID
	private Integer userId;
	// イベント参加者名
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}