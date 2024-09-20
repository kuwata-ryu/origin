package schoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoo.entity.UserList;
import schoo.form.UserListForm;
import schoo.service.UserListService;

/**
 * Springで作成する簡単なDB出力のサンプルクラスです.
 */
@Controller
public class SchooController {

	@Autowired
	UserListService userListService;

	/**
	 * トップページへのリクエスト
	 * 
	 * @return output.htmlのパス
	 */
	@GetMapping("/")
	public String index(Model model) {
		return "login";
	}

	// POSTメソッド
	/*
	 * @PostMapping("/login") private String init(Model model,UserListForm
	 * userListForm) { String pass = userListForm.getPass(); String name =
	 * userListForm.getName(); UserList userList =
	 * userListService.findByUserList(name,pass); model.addAttribute("userList",
	 * userList); return "login_ok";
	 * 
	 * }
	 */

	@PostMapping("/login")
	public String login(Model model, UserListForm form) {
		var userInfo = userListService.searchUserById(form.getName());
		var isCorrectUserAuth = userInfo.isPresent() 
				&& form.getPass().equals(userInfo.get().getPass());
		if(isCorrectUserAuth) {
			System.out.println(form.getName());
			model.addAttribute("userInfo", form.getName());
			return "login_ok";
		}else {
			model.addAttribute("errorMsg","名前とPASSの組み合わせが間違っています。");
			return "login";
		}
	}

	@PostMapping("add")
	public String addUser(Model model,@Validated UserListForm userListForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "validated";
		}
		String pass = userListForm.getPass();
		String pass2 = userListForm.getPass2();
		if (pass.equals(pass2)) {
			userListService.save(userListForm.getName(), pass);
			model.addAttribute("addMsg","新規登録が完了しました。");
			return "login";
		} else {
			return "ng";
		}
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "redirect:/";
	}
	
	@GetMapping("/output")
	public String output(Model model) {
		return "output";
	}

	/*
	 * @GetMapping("delete") public String deleteUser(@RequestParam Integer id) { //
	 * 指定されたIDを引数に削除処理へ userListService.delete(id); return "redirect:/"; }
	 */
}