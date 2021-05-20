package net.example.usermanagement.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User implements Serializable {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(name = "name")
	protected String name;
	
	@Column(name = "email")
	protected String email;
	
	@Column(name = "password")
	protected String password;
	
	@Column(name = "role")
	protected String role;
	
	@Column(name = "status")
	protected String status;
	
	@Column(name = "JoiningDate")
	protected LocalDateTime joiningDateTime;

	@Column(name = "LeavingDate")
	protected LocalDateTime leavingDateTime;
	
	//protected List<Logs> logs;

	public User() {
	}
	
	public User(String name, String email, String password, String role, String status, LocalDateTime joiningDateTime) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
		this.joiningDateTime = joiningDateTime;
	}

	public User(int id, String name, String email, String password, String role, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	
	public User(int id, String name, String email, String role, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.status = status;
	}
	
	public User(int id, String password, String newPassword) {
		super();
		this.id = id;
		this.password = password;
		this.password = newPassword;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getJoiningDateTime() {
		return joiningDateTime;
	}

	public LocalDateTime getLeavingDateTime() {
		return leavingDateTime;
	}

	public void setJoiningDateTime(LocalDateTime joiningDateTime) {
		this.joiningDateTime = joiningDateTime;
	}

	public void setLeavingDateTime(LocalDateTime leavingDateTime) {
		this.leavingDateTime = leavingDateTime;
	}
}