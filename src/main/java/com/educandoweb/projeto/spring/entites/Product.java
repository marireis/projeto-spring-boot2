package com.educandoweb.projeto.spring.entites;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private double price;
	private String imgUml;
	
	//relacionamento 1 produto com varias categoria - coleção
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),//mapeamento para transformar as tabelas de associação
	inverseJoinColumns = @JoinColumn(name = "category_id"))//colocar a coluna inversa do relacionamento
	private Set<Category> categories = new HashSet<>(); //Set - representa um conjunto, garante que o mesmo produto so tenha 1 categoria

	//coleção de orderItem como feito na classe Order
	@OneToMany(mappedBy = "id.product")//mapeado no BD - pega o id na classe OrderItem e o product pega na classe OrdemItemPK
	private Set<OrderItem> items = new HashSet<>();//SET - informa ao JPA que não pode haver repetições do mesmo item
	
	
	public Product() {
	}

	//coleção não entra no construtor
	public Product(Long id, String name, String description, double price, String imgUml) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUml = imgUml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgUml() {
		return imgUml;
	}

	public void setImgUml(String imgUml) {
		this.imgUml = imgUml;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	@JsonIgnore //para evitar o loop
	public Set<Order>getOrders(){//orders como no diagrama uml
		Set<Order> set = new HashSet<>();
		for(OrderItem x: items) {//percorre a coleção items(coleção do tipo order item associada ao produto), para cada elemento(x) adiciona ao conjunto x.getOrder(assim pega o objeto order associado ao OrderItem) 
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}	
	

	
}
