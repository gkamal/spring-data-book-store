package tenx.spring.data.repository.mongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import tenx.spring.data.domain.Book;
import tenx.spring.data.domain.Order;
import tenx.spring.data.repository.jpa.OrderRepository;
import tenx.spring.data.repository.mongo.BookRepository;

@ContextConfiguration("classpath:META-INF/spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderRepository;
	

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Before
	public void setup() {
		orderRepository.save(createNewOrder(350, 1, "kamal"));
		orderRepository.flush();
		entityManager.clear();
	}

	private Order createNewOrder(int price, int quantity, String user) {
		
		Order order = new Order();
		order.setQuantity(quantity);
		order.setPrice(price);
		order.setUser(user);
		return order;
	}

	@Test
	public void findAll() {
		List<Order> orders = orderRepository.findAll();
		assertEquals(1, orders.size());
	}
	

	@Test
	public void findPendingOrders() {
		Page<Order> orders = orderRepository.findPendingOrders(new PageRequest(0,10, Direction.ASC, "date"));
		assertEquals(1, orders.getTotalElements());
		
		Order order = orders.getContent().get(0);
		order.setStatus(Order.Status.FULL_FILLED);
		
		orders = orderRepository.findPendingOrders(new PageRequest(0,10, Direction.ASC, "date"));
		assertEquals(0, orders.getTotalElements());
	}
	
	
}
