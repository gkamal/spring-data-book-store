package tenx.spring.data.respository.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import tenx.spring.data.domain.User;
import tenx.spring.data.repository.jpa.UserRepository;

@ContextConfiguration("classpath:META-INF/spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserRepositoryRest {
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUserName("kamal");
		user.setEmail("kamal@springpeople.com");
		user.setPassword("abc123");
		userRepository.save(user);
		
		List<User> users = userRepository.findAll();
		assertEquals(1,users.size());
		
		Page<User> result = userRepository.findAll(new PageRequest(0,10,Direction.ASC,"userName"));
		assertEquals(1,result.getTotalElements());
		
		User loadedUser = userRepository.findOneByUserNameLike("kam");
		assertNotNull(loadedUser);
		
		Page<User> loadedUsers = userRepository.findByUserNameOrEmail("gkamal","kamal@springpeople.com",new PageRequest(0,10));
		assertEquals(1,loadedUsers.getTotalElements());
		
		loadedUser = userRepository.findUserSomeComplexWay("KAMAL");
		assertNotNull(loadedUser);
		
		
	}

}















