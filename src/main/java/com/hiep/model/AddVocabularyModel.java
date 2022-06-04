package com.hiep.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class AddVocabularyModel {
	
	@Id
    @GeneratedValue
    private Long id;

    private String japan;

	private String read;

	private String vietnam;

	private String english;

	private String example;
	
	private LocalDate create_date;

}
