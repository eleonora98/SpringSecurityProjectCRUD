package com.uni.fmi.task.bakery.comment;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.uni.fmi.task.bakery.constants.DbTables;
import com.uni.fmi.task.bakery.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DbTables.TABLE_COMMENTS)
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "date_uploaded", nullable = false)
	private LocalDate date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
