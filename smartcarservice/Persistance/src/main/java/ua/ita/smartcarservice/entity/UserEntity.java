package ua.ita.smartcarservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false, unique = true)
	private String username;
	@Column(length = 100, nullable = false)
	private String password;
	@Column(length = 30, nullable = true)
	private String email;
	@Column(length = 50, nullable = false)
	private String fullName;
	@Column(length = 15, unique = true)
	private String numberPhone;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

	@JsonIgnore
	@OneToOne(mappedBy = "userId")
	private UserTechnicalService userTechnicalService;

	@JsonIgnore
	@OneToOne(mappedBy = "workerId", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private WorkersSkill workersSkill;

	@JsonIgnore
	@OneToMany(mappedBy = "worker")
	Set<WorkTime> workTimes;
/*
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	Set<Car> cars;
	*/
	public UserEntity() {
		
	}

	    public UserEntity(String username, String password, String email, String fullName, String numberPhone) {
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

	public UserTechnicalService getUserTechnicalService() {
		return userTechnicalService;
	}

	public void setUserTechnicalService(UserTechnicalService userTechnicalService) {
		this.userTechnicalService = userTechnicalService;
	}

	public WorkersSkill getWorkersSkill() {
		return workersSkill;
	}

	public void setWorkersSkill(WorkersSkill workersSkill) {
		this.workersSkill = workersSkill;
	}

	public Set<WorkTime> getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(Set<WorkTime> workTimes) {
		this.workTimes = workTimes;
	}
/*
	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	*/
}
