package com.hiep.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiep.entity.AppUserEntity;
import com.hiep.model.UserModel;

@Mapper
public interface UserMapper {

	List<AppUserEntity> selectByEmail(UserModel userModel);

	int createUser(UserModel userModel);

	int checkEmail(String getEmail);

}
