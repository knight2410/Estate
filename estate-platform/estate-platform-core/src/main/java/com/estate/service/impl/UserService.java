package com.estate.service.impl;

import com.estate.builder.UserBuilder;
import com.estate.constant.SystemConstant;
import com.estate.controller.utils.RandomGenerator;
import com.estate.controller.utils.Md5Utils;
import com.estate.converter.RoleConverter;
import com.estate.converter.UserConverter;
import com.estate.dto.UserDTO;
import com.estate.entity.BuildingEntity;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.repository.ContractRepository;
import com.estate.repository.RoleRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IRoleService;
import com.estate.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.estate.exception.MyException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity item : userEntities) {
            UserDTO userDTO = userConverter.convertToDto(item);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        List<UserDTO> result = new ArrayList<>();
        Page<UserEntity> usersPage = null;
        if (searchValue != null) {
            usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
        } else {
            usersPage = userRepository.findAll(pageable);
        }
        for (UserEntity item : usersPage.getContent()) {
            if (item.getStatus() != 0) {
                UserDTO userDTO = userConverter.convertToDto(item);
                result.add(userDTO);
            }
        }
        return result;
    }

    @Override
    public List<UserDTO> getUsers(UserBuilder userBuilder) {
        if (StringUtils.isNotBlank(userBuilder.getRole()) && userBuilder.getBuildingId() != null) {
            return userRepository.findByStatusAndRoleList_Code(1, userBuilder.getRole())
                    .stream().map(item -> {
                        UserDTO userDTO = userConverter.convertToDto(item);
                        if (userRepository.existsByIdAndBuildings_Id(item.getId(), userBuilder.getBuildingId())) {
                            userDTO.setChecked("checked");
                        }
                        return userDTO;
                    }).collect(Collectors.toList());
        } else if (StringUtils.isNotBlank(userBuilder.getRole()) && userBuilder.getCustomerId() != null) {
            return userRepository.findByStatusAndRoleList_Code(1, userBuilder.getRole())
                    .stream().map(item -> {
                        UserDTO userDTO = userConverter.convertToDto(item);
                        if (userRepository.existsByIdAndCustomers_Id(item.getId(), userBuilder.getCustomerId())) {
                            userDTO.setChecked("checked");
                        }
                        return userDTO;
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        int totalItemDelete = (int) userRepository.countByStatus(0);
        if (searchValue != null) {
            totalItem = (int) userRepository.countByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue) - totalItemDelete;
        } else {
            totalItem = (int) userRepository.count() - totalItemDelete;
        }
        if (totalItem < 0) {
            return 0;
        }
        return totalItem;
    }

    @Override
    public UserDTO insert(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setRoleList(roleRepository.findOneByCode(userDTO.getRoleCode()));
        //String pass = RandomGenerator.generateRandom(8);
       // String pass = "123456";
       // userEntity.setPassword(Md5Utils.convertToMd5(pass));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public UserDTO update(UserDTO updateUser, long id) {
        UserEntity oldUser = userRepository.findOne(id);
        oldUser.setUserName(updateUser.getUserName());
        oldUser.setFullName(updateUser.getFullName());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setPhone(updateUser.getPhone());
        oldUser.setPassword(passwordEncoder.encode(updateUser.getPassWord()));
        oldUser.setRoleList(roleRepository.findOneByCode("USER"));
//        oldUser.setRoleList(roleRepository.findOneByCode(updateUser.getRoleCode()));
        oldUser = userRepository.save(oldUser);
        return userConverter.convertToDto(oldUser);
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findOne(id);
        UserDTO dto = userConverter.convertToDto(entity);
        dto.setRoleCode(entity.getRoleList().get(0).getCode());
        dto.setRoles(roleService.getRoles());
        return dto;
    }

    @Override
    public void deleteUser(long[] ids) {
        for (long item : ids) {
            UserEntity entity = userRepository.findOne(item);
            entity.setStatus(0);
            userRepository.save(entity);
        }
    }

    @Override
    public void checkUserNameOrEmailExist(String userName, String email, Long id) throws MyException {
        if (id == 0 && userRepository.existsByUserNameOrEmail(userName, email)) {
            throw new MyException("Username hoặc email đã tồn tại");
        } else {
            //custom method check using JPA
        }
    }

    @Override
    public Map<String, String> getStaffs() {
        Map<String, String> users = new HashMap<>();
        //user
        List<UserEntity> entities = userRepository.findByRoleList_id(2);
        entities.forEach(item -> {
            users.put(item.getUserName(), item.getFullName());
        });
        return users;
    }

    @Override
    public List<UserDTO> getUsersByBuilding(long buildingId) {
        List<UserEntity> entities = userRepository.findByBuildings_Id(buildingId);
        List<UserDTO> dtos = new ArrayList<>();
        for (UserEntity item : entities) {
            UserDTO dto = userConverter.convertToDto(item);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Map<Long, String> findBuildingByStaffUser(Long idUser) {
        List<BuildingEntity> buildingEntities = userRepository.findOne(idUser).getBuildings();
        Map<Long, String> getBuildingByUser = new HashMap<>();
        for (BuildingEntity item : buildingEntities) {
            if (!contractRepository.existsByIdBuilding(item.getId())) {
                getBuildingByUser.put(item.getId(),item.getName());
            }
        }
        return getBuildingByUser;
    }

}
