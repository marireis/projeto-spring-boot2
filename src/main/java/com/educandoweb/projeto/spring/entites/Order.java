package com.educandoweb.projeto.spring.entites;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.educandoweb.projeto.spring.entites.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {//pedido
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	
	//relacionamento de muitos pedidos para 1 cliente
	
	@ManyToOne
	@JoinColumn(name="client-id")//nome da chave estrangeira no BD
	private User client;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);//no construtor o atributo é do tipo order status, mas na declarão é do tipo inteiro 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);//converte para inteiro
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null)
			this.orderStatus = orderStatus.getCode();//converte para OrderStatus
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
