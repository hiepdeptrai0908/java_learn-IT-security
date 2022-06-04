package com.hiep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hiep.mapper.UserMapper;
import com.hiep.model.UserModel;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@GetMapping("/")
	public String redirect() {
		return "redirect:/home";
	}

	// Login ページ
	@GetMapping("login")
	public String login() {
		
		return "login";
	}

	// ログインの失敗の場合
	@GetMapping("login-fail")
	public String loginFail(Model model) {
		model.addAttribute("errorMessengeLogin", "メールアドレスまたはパスワードが正しくありません！");
		return "login";
	}

	// 新規ユーザーの作成のページを表示する
	@GetMapping("create-user")
	private String createUser(@ModelAttribute UserModel userModel) {
		return "create-user";
	}

	// 新規ユーザーの作成を処理する
	@PostMapping("do-create-user")
	private String doCreateUser(UserModel userModel, Model model) {

		String getEmail = userModel.getEmail();
		String getPassword = userModel.getPassword();

		int result = userMapper.checkEmail(getEmail);

		// Regular Expression
		String regexEmail = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";
		String regexPassword = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/";

		// Check Format Email
		if (!(getEmail.equals(regexEmail))) {
			model.addAttribute("userModel", new UserModel());
			model.addAttribute("errorMessengeEmail", "メールの定型フォーマットが正しくない！");

			// Check Password
			if (!(getPassword.equals(regexPassword))) {
				model.addAttribute("errorMessengePassword1", "パスワードの定型フォーマットが正しくない！");
				model.addAttribute("errorMessengePassword2", "(8桁以上の大文字と小文字と数字を入力してください！)");
			}
			return "create-user";
		} else {

			// メールを復唱するかどうかのチェック！
			if (result > 0) {
				model.addAttribute("userModel", new UserModel());
				model.addAttribute("errorMessenge", "このメールアドレスはすでに使いました！");
				return "create-user";
			} else {
				UserModel user = new UserModel();

				user.setEmail(userModel.getEmail());
				user.setPassword(passwordEncoder.encode(userModel.getPassword()));
				user.setZipcode(userModel.getZipcode());
				user.setAddress1(userModel.getAddress1());
				user.setAddress2(userModel.getAddress2());

				// データベースにINSERTする
				userMapper.createUser(user);

				return "redirect:/login";
			}
		}
	}
}
