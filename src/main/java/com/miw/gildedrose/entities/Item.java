package com.miw.gildedrose.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "item_details")
@SequenceGenerator(name = "item_details_id_seq", allocationSize = 1)
@Getter
@Setter
public class Item implements Serializable {

	private static final long serialVersionUID = 2085296390518494845L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_details_id_seq")
	private Long id;
	@Column(name = "item_no")
	private String itemNo;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private Double price;
	@Column(name = "status")
	private String status;
	@Column(name = "no_of_views")
	private Integer noOfViews;
	@Column(name = "no_of_items_in_stock")
	private Integer noOfitemsInStock;
}
