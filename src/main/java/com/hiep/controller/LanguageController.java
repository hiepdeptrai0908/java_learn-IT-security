package com.hiep.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hiep.entity.LanguageEntity;
import com.hiep.mapper.LanguageMapper;
import com.hiep.model.AddVocabularyModel;
import com.hiep.model.DeleteVocabularyModel;
import com.hiep.model.EditVocabularyModel;
import com.hiep.model.SearchVocabularyModel;

@Controller
public class LanguageController {

	@Autowired
	private LanguageMapper languageMapper;

	// Home ページ
	@GetMapping("home")
	public String home(Model model) {
		List<LanguageEntity> getListVocabulary = languageMapper.getListVocabulary();
		model.addAttribute("listVocabulary", getListVocabulary);
		model.addAttribute("deleteVocabularyModel", new DeleteVocabularyModel());
		model.addAttribute("searchVocabularyModel", new SearchVocabularyModel());
		return "home";
	}
	
	// 単語のページを表示する
	@GetMapping("add-vocabulary")
	public String addVocabulary(@ModelAttribute AddVocabularyModel addVocabularyModel) {
		
		return "add-vocabulary";
	}
	
	// 単語を追加する処理
	@PostMapping("do-add-vocabulary")
	public String doAddVocabulary(AddVocabularyModel addVocabularyModel) {

		LocalDate date = LocalDate.now();
//		LocalTime time = LocalTime.now();

		addVocabularyModel.setCreate_date(date);
		languageMapper.addVocabulary(addVocabularyModel);

		return "redirect:/home";
	}
	
	// Delete
	@PostMapping("delete-vocabulary")
	public String deleteVocabulary(DeleteVocabularyModel deleteVocabularyModel) {
		languageMapper.deleteVocabularyById(deleteVocabularyModel);
		return "redirect:/home";
	}
	
	// Edit
	@PostMapping("edit-vocabulary")
	public String editVocabulary(@ModelAttribute EditVocabularyModel editVocabularyModel) {
		return "edit-vocabulary";
	}
	
	// 編集の処理
	@PostMapping("do-edit-vocabulary")
	public String doEditVocabulary(EditVocabularyModel editVocabularyModel) {
		languageMapper.editVocabulary(editVocabularyModel);
		return "redirect:/home";
	}
	
	// Search (検索)
	@GetMapping("search-vocalulary")
	public String searchVocabulary(String searchValue, Model model) {
		List<LanguageEntity> resultList = languageMapper.searchVocabulary(searchValue);
		model.addAttribute("listVocabulary", resultList);
		model.addAttribute("deleteVocabularyModel", new DeleteVocabularyModel());
		model.addAttribute("searchVocabularyModel", new SearchVocabularyModel());
		return "home";
	}
}
