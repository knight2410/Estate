package com.estate.dto;

import java.util.List;
import java.util.Map;

public class UserDTO extends AbstractDTO<UserDTO> {

    private static final long serialVersionUID = 9197625705725278793L;

    private String userName;
    private String passWord;
    private String email;
    private String phone;
    private String fullName;
    private String roleCode;
    private List<RoleDTO> roleList;
    private Map<String, String> roles;
    private List<BuildingDTO> buildings;
    private String checked;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }

    public String getChecked() {
        return checked != null ? checked : "";
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
