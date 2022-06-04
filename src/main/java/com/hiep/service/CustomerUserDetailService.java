package com.hiep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiep.entity.AppUserEntity;
import com.hiep.mapper.UserMapper;
import com.hiep.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel userModel = new UserModel();
		userModel.setEmail(email);
		
		List<AppUserEntity> listUser = userMapper.selectByEmail(userModel);
		log.info("listUser:{}",listUser);
		if(listUser.size() > 0) {
			AppUserEntity userEntity = listUser.get(0);
			
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
			
			grantList.add(authority);
			
			UserDetails userDetails = new User(userEntity.getEmail(),userEntity.getPassword() , grantList);
			
			return userDetails;
			
		}else {
			new UsernameNotFoundException("Login Fail !");
		}
		
		return null;
	}

}
