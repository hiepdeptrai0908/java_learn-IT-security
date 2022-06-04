package com.hiep.model;

import lombok.Data;

@Data
public class UserModel {

	private Long id;

	private String email;

	private String password;

	private String zipcode;

	private String address1;

	private String address2;

}
