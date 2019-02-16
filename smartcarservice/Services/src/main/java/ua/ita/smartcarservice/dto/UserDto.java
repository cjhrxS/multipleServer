package ua.ita.smartcarservice.dto;

import lombok.Data;
import ua.ita.smartcarservice.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {


    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String numberPhone;

    private Set<RoleEntity> roles = new HashSet<RoleEntity>();


    public UserDto() {

    }


    public UserDto(Long id, String username, String password, String email, String fullName, String numberPhone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}