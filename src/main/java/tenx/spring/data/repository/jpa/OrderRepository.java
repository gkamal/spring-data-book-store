package tenx.spring.data.repository.jpa;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import tenx.spring.data.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Page<Order> findPendingOrders(Pageable pageable);
	
	Page<Order> findOrdersForPeriod(@Param("start") Date start,@Param("start") Date end, 
			Pageable pageable);
}
