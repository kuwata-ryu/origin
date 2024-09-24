package schoo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import schoo.entity.Event;
import schoo.entity.ImageEntity;
import schoo.entity.UserList;
import schoo.form.EventForm;
import schoo.form.UserListForm;
import schoo.repository.ImageRepository;
import schoo.service.UserEntryService;
import schoo.service.EventService;
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
			List<Event> eventList = eventService.findAll();
			model.addAttribute("eventList", eventList);
			model.addAttribute("userInfo", form.getName());
			return "eventList";
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
	
	//以下はイベント管理ツールから引用
	@Autowired
	EventService eventService;
	@Autowired
	UserEntryService userEntryService;

	/**
	 * トップページ（イベント一覧画面）を表示します.
	 * @param model
	 * @return イベント一覧画面のパス
	 */
	@GetMapping("eventList")
	public String eventList(Model model) {
		List<Event> eventList = eventService.findAll();
		model.addAttribute("eventList", eventList);
		return "eventList";
	}

	/**
	 * イベントの登録画面を表示します.
	 * @param model
	 * @return イベント登録画面のパス
	 */
	@GetMapping("regist")
	public String regist(Model model) {
		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "regist";
	}

	/**
	 * 指定されたイベントIDの詳細画面を表示します.
	 * @param eventId イベントID
	 * @param model
	 * @return イベント詳細画面のパス
	 */
	@GetMapping("entry/{userId}")
	public String entry(@PathVariable("userId") Integer userId,
						Model model) {
		// 引数で受け取ったイベントIDからイベントの情報を取得
		// イベントの情報は1件の前提で処理
		List<UserList> user = userListService.findByUserId(userId);

		// modelに結果をセット
		model.addAttribute("user", user.get(0));

		// 引数で受け取ったイベントIDからイベントの参加者情報を取得
		List<Event> eventList =
			userEntryService.findByUserId(userId);

		// modelに結果をセット
		model.addAttribute("eventList", eventList);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "userdetail";
	}

	/**
	 * イベントの登録処理です.
	 * @param eventForm イベント情報
	 * @return	イベント一覧画面のパス（output.htmlへのリダイレクト）
	 */
	@Autowired
    private ImageRepository imageRepository;

    private static final String UPLOAD_DIR = "/Users/桑田 龍/eclipse-workspace/ProgressManagement/src/main/resources/static/image"; // アップロード先のディレクトリ
	@PostMapping("eventregist")
	public String registrationEvent(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,@ModelAttribute EventForm eventForm) {
		
		
		try {
            // アップロードディレクトリが存在しない場合、作成
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 画像ファイルの保存先パス
            String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();

            // 画像ファイルをディスクに保存
            Path destination = new File(filePath).toPath();
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            // データベースにファイルメタデータを保存
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(file.getOriginalFilename());
            imageEntity.setFilePath(filePath);
    		eventForm.setFilePath(file.getOriginalFilename());
            imageRepository.save(imageEntity);
         // フォームから値を取得してデータベース登録処理へ
    		eventService.save(eventForm);

            redirectAttributes.addFlashAttribute("message", "File uploaded successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("uperror", "Failed to upload file: " + e.getMessage());
        }

		// 次に表示する画面のパス（リダイレクト先のページ）を返却
		return "redirect:eventList";
	}
}