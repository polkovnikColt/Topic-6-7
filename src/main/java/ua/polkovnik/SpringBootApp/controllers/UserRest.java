package ua.polkovnik.SpringBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.polkovnik.SpringBootApp.models.CommonUser;
import ua.polkovnik.SpringBootApp.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UserRest {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/registration")
    public ResponseEntity<CommonUser> addUser(@RequestBody CommonUser user) {
        if (userService.addUser(user)) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity<CommonUser> login(@RequestBody CommonUser user) {
           Optional<CommonUser> candidate = userService.loadUserByUsername(user.getUsername());
           if(candidate.isPresent()){
               if(candidate.get().getPassword().equals(user.getPassword())){
                   return ResponseEntity.ok(candidate.get());
               }
           }
        return ResponseEntity.notFound().build();
    }

}
