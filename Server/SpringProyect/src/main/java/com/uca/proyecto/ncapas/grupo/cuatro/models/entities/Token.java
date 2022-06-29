package com.uca.proyecto.ncapas.grupo.cuatro.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import java.sql.Timestamp;

@Entity(name="token")
public class Token {
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name="token_id_seq", sequenceName = "token_id_Seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_seq" )
	private long id;

	@Column(name = "content")
	private String content;
	
	@Column(name = "active", insertable = false)
	private Boolean active;
	
	@Column(name = "timestamp", insertable = false, updatable = false)
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;

	public Token(String content, User user) {
		super();
		this.content = content;
		this.user = user;
	}

	protected Token() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}	
