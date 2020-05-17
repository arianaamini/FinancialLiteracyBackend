package Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Application.entity.User;
import Application.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "register", method = RequestMethod.POST)
  public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password,
                                    @RequestParam(required = false) String email) {

    if(userRepository.findByUsername(username) != null){

      return new ResponseEntity<String>(HttpStatus.CONFLICT);
    }
    userRepository.save(User.builder().username(username).password(password).email(email).isAdmin(false).build());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "login", method = RequestMethod.GET)
  public HttpEntity<?> login(@RequestParam String username, @RequestParam String password) {

    User user = userRepository.findByUsernameAndPassword(username, password);
    if(user == null){

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user,HttpStatus.OK);
  }

}
