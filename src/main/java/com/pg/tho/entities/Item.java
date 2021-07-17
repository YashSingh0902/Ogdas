package com.pg.tho.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table (name="item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	
	//@NotEmpty(message = "Itemname is Empty -> ENTER ITEMNAME")
	//@Size(min=3,max = 25)
	private String itemName;
	//@NotEmpty(message = "Quantity is Empty -> ENTER QUANTITY")
	//@Size(min=1)
	private int quantity;
	//@NotEmpty(message = "Cost is Empty -> ENTER COST")
	private double cost;

	@ManyToOne
	@JoinColumn(name = "cat_id", referencedColumnName = "catId")
	private Category category;

	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	public Item() {
		super();
	}

	public Item(String itemName, int quantity, double cost, Category category, Section section) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
		this.category = category;
		this.section = section;
	}

	public Item(int itemId, String itemName, int quantity, double cost, Category category, Section section) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
		this.category = category;
		this.section = section;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", quantity=" + quantity + ", cost=" + cost
				+ ", category=" + category + ", section=" + section + "]";
	}

}
