package net.example.usermanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Logs")
public class Logs implements Serializable {

	@Id
	@Column(name = "LogID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer logId;
	
	@Column(name = "type")
	public String type;
	
	@Column(name = "activity")
	public String activity;
	
	@Column(name = "target")
	public String target;
	
	@ManyToOne
	@JoinColumn(name="id", nullable = false)
	public User user;
	
	// Default constructor needed by hibernate / jsp to create entity
	public Logs() {
		
	}
	
	public Logs(String type, String activity, String target, User user) {
		super();
		this.type = type;
		this.activity = activity;
		this.target = target;
		this.user = user;
	}

	public Logs(int logId, String type, String activity, String target, User user) {
		super();
		this.logId = logId;
		this.type = type;
		this.activity = activity;
		this.target = target;
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getLogId() {
		return logId;
	}

	public String getType() {
		return type;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setLogId(int logId) {
		this.logId = logId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return "Logs [logId=" + logId + ", type=" + type + ", activity=" + activity + ", target=" + target + ", user="
				+ user + "]";
	}
}