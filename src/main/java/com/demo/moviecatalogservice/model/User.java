package com.demo.moviecatalogservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
@ApiModel(description = "UserDetails model")
public class User {
	
	@Id
	@Column(name="userid")
	@ApiModelProperty(notes = "User ID of the user", name = "userid", required = true, value = "1")
	private String userid;
	
	@Column(name="username")
	@ApiModelProperty(notes = "User name of the user", name = "username", required = true, value = "name")
	private String username;
	
	@Column(name="user_password")
	@ApiModelProperty(notes = "User password of the user", name = "user_password", required = true, value = "abcde")
	private String user_password;

}
