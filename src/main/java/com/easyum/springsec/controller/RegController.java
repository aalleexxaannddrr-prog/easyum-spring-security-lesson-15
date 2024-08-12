package com.easyum.springsec.controller;

import com.easyum.springsec.entity.UserEntity;
import com.easyum.springsec.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/reg")
public class RegController {

    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;

    public RegController(final UserRepo userRepo, final PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getReg() {
        return "reg";
    }


    @PostMapping
    public String postReg(String username, String password, String repeat, Map<String, Object> model) {
        if (!password.equals(repeat)) {
            model.put("message", "Password is bad");
            return "reg";
        }

        final UserEntity userWithSameName = userRepo.findByUsername(username);
        if (userWithSameName != null) {
            model.put("message", "Such user already exists");
            return "reg";
        }

        String encoded = passwordEncoder.encode(password);
        System.out.println(encoded);
        userRepo.save(new UserEntity(username, encoded));

        return "redirect:/hello";
    }


}
