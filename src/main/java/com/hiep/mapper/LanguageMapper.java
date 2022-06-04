package com.hiep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hiep.entity.LanguageEntity;
import com.hiep.model.AddVocabularyModel;
import com.hiep.model.DeleteVocabularyModel;
import com.hiep.model.EditVocabularyModel;

@Mapper
public interface LanguageMapper {

	int addVocabulary(AddVocabularyModel addVocabularyModel);

	List<LanguageEntity> getListVocabulary();

	int deleteVocabularyById(DeleteVocabularyModel deleteVocabularyModel);

	int editVocabulary(EditVocabularyModel editVocabularyModel);

	List<LanguageEntity> searchVocabulary(@Param("searchValue") String searchValue);

}
