package com.educandoweb.projeto.spring.entites.enums;import com.educandoweb.projeto.spring.entites.Order;

public enum OrderStatus {
	WAITING_PAYMENT(1),//AGUARDANDO PAGAMENTO 
	PAID(2),//PAGO 
	SHIPPED(3),//ENVIADO 
	DELIVERED(4),//ENTREGUE 
	CANCELED(5);//CANCELADO 
	
	private int code;//codigo do tipo enumerado
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {//metodo publico para retornar o codigo
		return code;
	}
	
	public static OrderStatus valueOf(int code) {//passa o codigo e retorna o status
		
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Order Status Code - Código Inválido");
	}
}
