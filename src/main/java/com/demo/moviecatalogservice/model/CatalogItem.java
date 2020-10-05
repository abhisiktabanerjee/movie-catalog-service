package com.demo.moviecatalogservice.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CatalogItem {
	
	private String name;
	private String desc;
	private double rating;

}
