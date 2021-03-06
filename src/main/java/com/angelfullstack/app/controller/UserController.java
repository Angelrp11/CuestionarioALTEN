package com.angelfullstack.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.angelfullstack.app.entity.User;
import com.angelfullstack.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;


  // Create a new user

  @PostMapping
  public ResponseEntity<?> create(@RequestBody User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }

  @GetMapping("/all")
  public Page<User> readOrderByName(Pageable pageable) {
    pageable = PageRequest.of(0, 3);
    Page<User> userPag = userService.findAll(pageable);

    return userPag;

  }

  // Read an user
  // Si la variable se llama igual la del parametro en la funcion y de la url, se pone
  // @PathVariable(value="par_url)
  @GetMapping("/{id}")
  public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {
    Optional<User> oUser = userService.findById(userId);

    if (!oUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(oUser);
  }


  // Update

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@RequestBody User userDetails,
      @PathVariable(value = "id") Long userId) {
    Optional<User> oUser = userService.findById(userId);

    if (!oUser.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    BeanUtils.copyProperties(userDetails, oUser.get(), "id");

    // oUser.get().setName(userDetails.getName());
    // oUser.get().setSurname(userDetails.getSurname());
    // oUser.get().setEmail(userDetails.getEmail());
    // oUser.get().setEnabled(userDetails.getEnabled());

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
  }



  // Delete

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {

    if (!userService.findById(userId).isPresent()) {
      return ResponseEntity.notFound().build();
    }

    userService.deleteById(userId);
    return ResponseEntity.ok().build();

  }


  // Read all

  @GetMapping
  public List<User> readAll() {

    return StreamSupport.stream(userService.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("/enabled/{name}")
  public List<User> readEnabledUsers(@PathVariable(value = "name") String userName) {
    return StreamSupport.stream(userService.findByNameStartingWith(userName).spliterator(), false)
        .collect(Collectors.toList());

  }



}
