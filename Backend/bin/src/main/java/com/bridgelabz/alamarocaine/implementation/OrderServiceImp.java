package com.bridgelabz.alamarocaine.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.alamarocaine.entity.CartItem;
import com.bridgelabz.alamarocaine.entity.Order;
import com.bridgelabz.alamarocaine.entity.Plat;
import com.bridgelabz.alamarocaine.entity.Quantity;
import com.bridgelabz.alamarocaine.entity.Users;
import com.bridgelabz.alamarocaine.exception.UserException;
import com.bridgelabz.alamarocaine.repository.OrderRepository;
import com.bridgelabz.alamarocaine.repository.PlatImple;
import com.bridgelabz.alamarocaine.repository.UserRepository;
import com.bridgelabz.alamarocaine.response.EmailData;
import com.bridgelabz.alamarocaine.service.ICartService;
import com.bridgelabz.alamarocaine.service.IOrderServices;
import com.bridgelabz.alamarocaine.util.EmailProviderService;
import com.bridgelabz.alamarocaine.util.JwtGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImp implements IOrderServices {
	@Autowired
	private JwtGenerator generate;

	@Autowired
	private ICartService cartservice;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PlatImple platRepository;

	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	@Autowired
	OrderRepository orderRepository;

	public Order placeOrder(String token, Long platId, Long addressId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).get();
		if (userdetails != null) {
			Order orderDetails = new Order();
			Random random = new Random();
			ArrayList<Plat> list = new ArrayList<>();
			ArrayList<Quantity> quantitydetails = new ArrayList<>();
			ArrayList<String> details = new ArrayList<>();
			/*
			 * getting user cartplats to order as a list userdetail.getcatplat return type
			 * is List
			 */
			List<CartItem> cartplat = userdetails.getCartPlats();
			// log.info("------------------------------cartPlat------1--"+cartPlat);
			/*
			 * now iterating specific cart Plat
			 */
			List<Plat> userCartplats = null;
			for (CartItem userCart : cartplat) {
				// select specific cart plat to order
				// log.info("-----------------------usercart-------------2--"+userCart);
				userCartplats = userCart.getPlatsList();// getting plats from cart
				for (Plat plat : userCartplats) {
					// log.info("-----------------------plat---------------"+plat);
					if (plat.getPlatId().equals(platId))// specific plat to order @path taking input of this api
					{
						/*
						 * decreasing the quantity of plat
						 */
						long orderId;
						for (Quantity platquantity : userCart.getQuantityOfPlat()) {
							// List q=userCart.getQuantityOfplat();
							// log.info("-----------------------QUantity-------------2--"+platquantity);
							Long noOfPlats = plat.getNoOfPlats() - platquantity.getQuantityOfPlat();
							plat.setNoOfPlats(noOfPlats);
							Plat bb = platRepository.save(plat);
							try {
								list.add(bb);
								orderId = random.nextInt(1000000);
								if (orderId < 0) {
									orderId = orderId * -1;
								}
								double totalprice = plat.getPrice() * (platquantity.getQuantityOfPlat());
								// log.info("-----------------------QUantity-------------2--"+platquantity.getQuantityOfplat());
								orderDetails.setTotalPrice(totalprice);
								quantitydetails.add(platquantity);
								orderDetails.setOrderId(orderId);
								orderDetails.setQuantityOfPlats(quantitydetails);
								orderDetails.setOrderPlacedTime(LocalDateTime.now());
								orderDetails.setOrderStatus("en attente");
								orderDetails.setAddressId(addressId);
								orderDetails.setPlatsList(list);
								details.add("Id de la commande:" + orderId + "<html><br></html>" + "Nom du plat:"
										+ plat.getPlatName() + "<html><br></html>" + "Quantit� command�e:"
										+ platquantity.getQuantityOfPlat() + "<html><br></html>" + "Prix total:"
										+ platquantity.getTotalprice());
							} catch (Exception e) {
								throw new UserException("�chec de la commande");
							}
						} // quantity for
					} // if condition checks id of the plats

				} // plat iteration--for
			} // cart iterate--for
			userdetails.getOrderPlatDetails().add(orderDetails);
			String data = "";
			for (String dt : details) {
				data = data + dt;
				// log.info("\n "+dt);
			}

			Plat plat = platRepository.findById(platId).orElse(null);
			String body = "<html> \n"

					+ "<h3 ; style=\"background-color:#00302e;color:#ffffff;\" >\n "
					+ "<center>A LA MAROCAINE</center> " + "</h3>\n " + "<body  style=\"background-color:#b6d7a8;\">\n"// +
					// "<img src=\"E:\\git merge ideation\\final
					// front\\PlatStoreFrontend\\src\\assets\\platimage/"
					// +plat.getImage()+ "\" alt=\"platImage\">"

					+ userdetails.getEmail() + " <br>" + "D�tails de la commande: <br>" + " \n" + data + "\n"
					+ "<br>Veuillez nous �valuer en cliquant sur le lien ci-dessous:<br>" + "\n"
					+ "http://localhost:54670/plats/ratingreview<br>"

					+ "</body>" + " </html>";
			emailData.setEmail(userdetails.getEmail());

			emailData.setSubject("Votre commande est pass�e avec succ�s");

			emailData.setBody(body);

			em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			// em.sendMail(emailData.getEmail(), emailData.getSubject(),
			// emailData.getBody());

			System.out.println("emailData.getEmail() " + emailData.getEmail());
			System.out.println("emailData.getSubject() " + emailData.getSubject());
			System.out.println("emailData.getBody() " + emailData.getBody());
			em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			System.out.println("tarif envoi de courrier apr�s la commande");
			/*
			 * remove specific plat from the cart........
			 */

			if (cartservice.removePlatsFromCart(token, platId)) {
				userRepo.save(userdetails);
				return orderDetails;
			}
		}

		throw new UserException("utilisateur introuvable");
	}

	@Override
	public boolean confirmPlattoOrder(String token, Long platId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);

		for (Order order : userdetails.getOrderPlatDetails()) {
			boolean notExist = order.getPlatsList().stream().noneMatch(plats -> plats.getPlatId().equals(platId));

			if (!notExist) {
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public int getCountOfPlats(String token) {
		Long id = generate.parseJWT(token);
		int countOfPlats = 0;
		Users userdetails = userRepo.findById(id).orElseThrow(null);

		List<Order> plats = userdetails.getOrderPlatDetails();
		for (Order cart : plats) {
			if (!cart.getPlatsList().isEmpty()) {

				countOfPlats++;
			}
		}
		return countOfPlats;
	}

	@Transactional
	@Override
	public List<Order> getOrderList(String token) {
		long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);

		return userdetails.getOrderPlatDetails();

	}

	@Transactional
	@Override
	public int changeOrderStatus(String status, long orderId) {

		int changedOrderStatus = orderRepository.OrderStatusdefault(status, orderId);
		return changedOrderStatus;
	}

	public String getstatusresult() {
		return null;

	}

	public List<Order> getallOrders() {

		List<Order> orderIds = orderRepository.getorder();
		return orderIds;
	}

	@Override
	public List<Order> getInProgressOrders() {
		List<Order> inProgressOrder = orderRepository.getInProgressOrder();
		return inProgressOrder;
	}
}