package schoo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ログイン用Form
 */
public class UserListForm {
	private int userId;
	@NotBlank(message = "{0}は入力してください")
	private String name;
	@NotBlank(message = "{0}は入力してください")
	@Size(max = 20, message = "{0}は20字以内で入力してください")
	private String pass;
	@NotBlank(message = "{0}は入力してください")
	@Size(max = 20, message = "{0}は20字以内で入力してください")
	private String pass2;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
}