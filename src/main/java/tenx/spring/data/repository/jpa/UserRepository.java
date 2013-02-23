package tenx.spring.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import tenx.spring.data.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findOneByUserNameLike(String userName);
	
	Page<User> findByUserNameOrEmail(String userName,String email,Pageable pageable);

	User findUserSomeComplexWay(@Param("userName")String userName);
}
