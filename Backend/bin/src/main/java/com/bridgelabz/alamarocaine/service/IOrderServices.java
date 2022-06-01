package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Order;

public interface IOrderServices {

	boolean confirmPlattoOrder(String token, Long platId);

	Order placeOrder(String token, Long bookId, Long addressId);

	int getCountOfPlats(String token);

	List<Order> getOrderList(String token);

	List<Order> getallOrders();

	int changeOrderStatus(String status, long orderId);

	List<Order> getInProgressOrders();

}