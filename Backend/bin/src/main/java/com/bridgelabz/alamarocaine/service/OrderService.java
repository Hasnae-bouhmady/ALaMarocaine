package com.bridgelabz.alamarocaine.service;

import java.util.List;

import com.bridgelabz.alamarocaine.entity.Order;

public interface OrderService {

	List<Order> getOrderList(String token);

	Order getOrderConfrim(String token);

	int getCountOfPlats(String token);
}
