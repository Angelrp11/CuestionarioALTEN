package com.angelfullstack.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.angelfullstack.app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByName(String name);



}
