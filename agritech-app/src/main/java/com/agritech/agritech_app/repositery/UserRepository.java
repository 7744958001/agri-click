package com.agritech.agritech_app.repositery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agritech.agritech_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}