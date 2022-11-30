package com.shopifyapp.login;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currentSessionId;
	private Integer customerId;
	private String uuid;
	private LocalDateTime dateTime;
	
	public CurrentUserSession(Integer customerId, String uuid, LocalDateTime dateTime) {
		super();
		this.customerId = customerId;
		this.uuid = uuid;
		this.dateTime = dateTime;
	}
}
