package com.company.dto;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private List<UserSkillDTO> skills;

    public UserDTO() {
    }

    public UserDTO(User u){
        id=u.getId();
        name=u.getName();
        surname=u.getSurname();
        password=u.getPassword();
        email=u.getEmail();

        List<UserSkillDTO> list=new ArrayList<>();

        List<UserSkill> userSkills=u.getSkills(); //getSkills() return UserSkillList
        for(int i=0; i< userSkills.size();i++){
            UserSkill uSkill=userSkills.get(i);
            list.add(new UserSkillDTO(uSkill));
        }

        skills=list;
    }

    public UserDTO(int id, String name, String surname, String password,String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password=password;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkillDTO> skills) {
        this.skills = skills;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
