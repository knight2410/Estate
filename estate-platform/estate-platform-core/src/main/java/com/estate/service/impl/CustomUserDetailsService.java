package com.estate.service.impl;

import com.estate.converter.UserConverter;
import com.estate.dto.MyUserDetail;
import com.estate.dto.RoleDTO;
import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserName(username);
		UserDTO userDTO = userConverter.convertToDto(userEntity);

		if(userDTO == null) {
			log.error("user not found");
			throw new UsernameNotFoundException("Username not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (RoleDTO role: userDTO.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

		MyUserDetail myUserDetail = new MyUserDetail(username, userDTO.getPassWord(), true, true, true, true, authorities);
		BeanUtils.copyProperties(userDTO, myUserDetail);
		return myUserDetail;
	}
}
