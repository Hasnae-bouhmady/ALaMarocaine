package com.bridgelabz.alamarocaine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.alamarocaine.entity.Order;
import com.bridgelabz.alamarocaine.response.Response;
import com.bridgelabz.alamarocaine.service.IOrderServices;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	private IOrderServices orderService;

//	@Autowired
//	OrderServiceImp orderServiceimpl;

	@PostMapping("alamarocaine/placeOrder")
	public ResponseEntity<Response> placeOrder(@RequestHeader String token, @RequestParam Long platId,
			@RequestParam Long addressId) throws Exception {
		Order orderdetails = orderService.placeOrder(token, platId, addressId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(" Commande enregistr�e!", 200, orderdetails));
	}

	@GetMapping(value = "alamarocaine/confirmPlat/{platId}")
	public ResponseEntity<Response> confirmPlattoOrder(@RequestHeader(name = "token") String token,
			@PathVariable("platId") long platId) throws Exception {
		boolean userdetails = orderService.confirmPlattoOrder(token, platId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("la commande est pass�e", 200, userdetails));

	}

	@GetMapping(value = "/plats/{token}")
	public ResponseEntity<Response> getOrderlist(@PathVariable("token") String token) throws Exception {

		List<Order> orderdetails = orderService.getOrderList(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("liste de commande pass�e", 200, orderdetails));

	}

	@GetMapping(value = "/plats_count/{token}")
	public ResponseEntity<Response> getPlatsCount(@PathVariable("token") String token) throws Exception {

		int userdetails = orderService.getCountOfPlats(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("nombre de plats", 200, userdetails));

	}

	@ApiOperation(value = "Change Order Status by seller ")
	// danger @ApiOperation(value = "Change Order Status by admin ")
	// danger @PutMapping(value = "alamarocaine/orderStatusByAdmin")
	@PutMapping(value = "alamarocaine/orderStatusBySeller")

	/**
	 * DANGER public ResponseEntity<Response> changeOrderStausByAdmin(@RequestParam
	 * String status, @RequestParam long orderId, @RequestHeader("token") String
	 * token) throws Exception {
	 * 
	 * int orderStatusResult = orderService.changeOrderStatus(status, orderId);
	 * System.out.println("orderStatusResult :" + orderStatusResult); return
	 * ResponseEntity.status(HttpStatus.OK) .body(new Response(orderId + " order
	 * status updated ", 200, orderStatusResult));
	 * 
	 * }
	 **/

	/////////////////////////// DANGER
	public ResponseEntity<Response> changeOrderStausBySeller(@RequestParam String status, @RequestParam long orderId,
			@RequestHeader("token") String token) throws Exception {

		int orderStatusResult = orderService.changeOrderStatus(status, orderId);
		System.out.println("orderStatusResult :" + orderStatusResult);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(orderId + " �tat de la commande mis � jour ", 200, orderStatusResult));

	}
	///////////////////

	// danger @ApiOperation(value = "get allorder detrails for admin")
	@ApiOperation(value = "get allorder detrails for seller")
	// danger @GetMapping(value = "alamarocaine/getOrdersByAdmin")
	@GetMapping(value = "alamarocaine/getOrdersBySeller")
	public ResponseEntity<Response> getallOrders() throws Exception {

		List<Order> orderinfo = orderService.getallOrders();
		System.out.println("order ids: " + orderinfo);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(" liste des commandes ", 200, orderinfo));

	}

	@ApiOperation(value = "get In progress order detrails for seller")
	@GetMapping(value = "alamarocaine/getOrdersByseller")
	public ResponseEntity<Response> getInProgressOrders() throws Exception {
		System.out.println("------------Commande vendeur");
		List<Order> orderinfo = orderService.getInProgressOrders();
		System.out.println("vendeur ------ ID de commande : " + orderinfo);
		return ResponseEntity.status(200).body(new Response(" liste des commandes ", 200, orderinfo));

	}
}