package com.booking.app.dto;

public class BookingRequestDTO {
	
	private Integer userId;
	private Integer trainId;
	private int ticketsQuantity;
	private double totalPrice;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTrainId() {
		return trainId;
	}
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public int getTicketsQuantity() {
		return ticketsQuantity;
	}
	public void setTicketsQuantity(int ticketsQuantity) {
		this.ticketsQuantity = ticketsQuantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
