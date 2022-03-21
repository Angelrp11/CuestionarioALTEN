package com.angelfullstack.app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.angelfullstack.app.entity.User;
import com.angelfullstack.app.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public Iterable<User> findAll() {

    return userRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  @Transactional
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> findByNameStartingWith(String name) {
    return userRepository.findByNameStartingWith(name);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> findByOrderByNameDesc(int page, Pageable pageable) {
    return userRepository.findByOrderByNameDesc(page, pageable);
  }


}
