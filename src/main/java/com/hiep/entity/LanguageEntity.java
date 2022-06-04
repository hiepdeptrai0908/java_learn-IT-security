package com.hiep.entity;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LanguageEntity {
	
	@Id
    @GeneratedValue
    private Long id;

    private String japan;

	private String read;

	private String vietnam;

	private String english;

	private String example;
	
	private Timestamp create_date;

}
