package com.estate.service.impl;

import com.estate.entity.RoleEntity;
import com.estate.repository.RoleRepository;
import com.estate.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Map<String, String> getRoles() {
        Map<String, String> roles = new HashMap<>();
        List<RoleEntity> entities = roleRepository.findAll();
        entities.forEach(item -> {
            roles.put(item.getCode(), item.getName());
        });
        return roles;
    }
}
