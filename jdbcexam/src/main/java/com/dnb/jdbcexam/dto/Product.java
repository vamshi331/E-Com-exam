package com.dnb.jdbcexam.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.jdbcexam.utils.customProductIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Product {
    // Product ID
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="product_seq")
	@GenericGenerator(name = "product_seq" , strategy= "com.dnb.jdbcexam.utils.customProductIdGenerator",
			parameters = {@Parameter(name=customProductIdGenerator.INCREMENT_PARAM,value="5"),
					@Parameter(name= customProductIdGenerator.VALUE_PREFIX_PARAMETER, value="PROD_"),
					@Parameter(name=customProductIdGenerator.NUMBER_FORMAT_PARAMETER, value="%05d")} )

	 private String productId;
	@NotBlank
	 private String productName;
	 @Min(value=0)
	 private float productPrice;
	 @NotBlank
	 private String productDescription;
	 private LocalDate productExpire;
	 private String productCategory;
	
}