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
import com.hiep.message.ErMessage;
import com.hiep.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
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
	private String doCreateUser(UserModel userModel, ErMessage erMessage) {

		String getEmail = userModel.getEmail();
		String getPassword = userModel.getPassword();

		int result = userMapper.checkEmail(getEmail);

		// Regular Expression
		String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		String regexPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
		Boolean formatEmail = getEmail.matches(regexEmail);
		Boolean formatPassword = getPassword.matches(regexPassword);
		
		log.info("format email:{}", formatEmail);
		log.info("format password:{}", formatPassword);
		

		// Check Format Email
		if (formatEmail == false) {
			erMessage.err.add(ErMessage.erEmailHs);

			// Check Password
			if (formatPassword == false) {
				erMessage.err.add(ErMessage.erPwHs);
				erMessage.err.add(ErMessage.erPwLen);
			}
			return "create-user";
		} else {

			// メールを復唱するかどうかのチェック！
			if (result > 0) {
				if (formatPassword == false) {
					erMessage.err.add(ErMessage.erPwHs);
					erMessage.err.add(ErMessage.erPwLen);
				}
				erMessage.err.add(ErMessage.erEmailDbCheck);
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