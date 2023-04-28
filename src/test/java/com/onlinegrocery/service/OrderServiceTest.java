package com.onlinegrocery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.entity.Order;
import com.onlinegrocery.exceptions.OrderNotFoundException;
import com.onlinegrocery.repo.AddressRepo;
import com.onlinegrocery.repo.AppUserRepo;
import com.onlinegrocery.repo.OrderRepo;
import com.onlinegrocery.repo.PaymentRepo;
import com.onlinegrocery.serviceImpl.OrderServiceImpl;

@SpringBootTest
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	OrderServiceImpl impl = new OrderServiceImpl();

	@Mock
	private OrderRepo orderRepo;

	@Mock
	private AppUserRepo appUserRepository;

	@Mock
	private PaymentRepo paymentRepository;

	@Mock
	private AddressRepo addressRepository;

	// View All Orders
	@Test
	public void viewOrderTest() {
		List<Order> requests = orderServiceImpl.viewOrder();
		assertNotNull(requests);
	}

	// Cancel Order
	@Test
	void cancelOrder_orderDoesNotExist_throwsOrderNotFoundException() {
		Long orderId = 100L;
		Assertions.assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.cancelOrder(orderId));
	}

	// Get Order By Id
	@Test
	public void GetOrderByidTest() {
		AppUser user = new AppUser();
		user.setUserid(123);
		Order order1 = new Order();
		order1.setOrderId((long) 1);
		order1.setUserId(user);
		Order order2 = new Order();
		order2.setOrderId((long) 2);
		order2.setUserId(user);
		List<Order> expectedOrders = Arrays.asList(order1, order2);
		when(orderRepo.findByUserId(user)).thenReturn(expectedOrders);
		List<Order> actualOrders = orderServiceImpl.getOrderByUserId(user);
		assertEquals(expectedOrders, actualOrders);
		verify(orderRepo).findByUserId(user);
	}

}