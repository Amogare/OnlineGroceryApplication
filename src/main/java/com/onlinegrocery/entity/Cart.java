package com.onlinegrocery.entity;



import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int productCount;
	private double totalPrice;
	
	@OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
	private List<Product> products;

	
}
