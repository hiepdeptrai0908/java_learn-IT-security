package com.hiep.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class DeleteVocabularyModel {
	
	@Id
    @GeneratedValue
    private Long id;

}
