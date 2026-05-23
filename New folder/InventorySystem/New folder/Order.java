package model;

import java.sql.Timestamp;

public class Order {

	private int orderId;
	private int accountId;
	private int statusId;
	private int deliveryCompanyId;
	private double totalPrice;
	private Timestamp createdAt;

	public Order() {
	}

	public Order(int orderId, int accountId, int statusId, int deliveryCompanyId, double totalPrice,
			Timestamp createdAt) {
		super();
		this.orderId = orderId;
		this.accountId = accountId;
		this.statusId = statusId;
		this.deliveryCompanyId = deliveryCompanyId;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	public void setDeliveryCompanyId(int deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", accountId=" + accountId + ", statusId=" + statusId
				+ ", deliveryCompanyId=" + deliveryCompanyId + ", totalPrice=" + totalPrice + ", createdAt=" + createdAt
				+ "]";
	}
}