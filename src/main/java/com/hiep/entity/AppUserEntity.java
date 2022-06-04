package com.hiep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AppUserEntity {

	@Id
    @GeneratedValue
	private Long id;

	private String email;

	private String password;

	private String zipcode;

	private String address1;

	private String address2;

}
