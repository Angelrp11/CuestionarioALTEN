package com.angelfullstack.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.angelfullstack.app.entity.User;

public interface UserService {

  public Iterable<User> findAll();

  public Page<User> findAll(Pageable pageable);

  public Optional<User> findById(Long id);

  public User save(User user);

  public void deleteById(Long id);

  public List<User> findByNameStartingWith(String name);

  public Page<User> findByOrderByNameDesc(int page, Pageable pageable);

}
