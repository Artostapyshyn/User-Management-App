package com.artostapyshyn.usermanagementapp.controller;

import com.artostapyshyn.usermanagementapp.entity.User;
import com.artostapyshyn.usermanagementapp.service.UserService;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        String email = userService.getCurrentUserEmail();
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            User currentUser = user.get();
            model.addAttribute("name", currentUser.getName());
            model.addAttribute("email", email);
            model.addAttribute("address", currentUser.getAddress());
        }

        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") @Valid User updatedUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Error updating profile. Try again");
            return "profile";
        }

        String currentEmail = userService.getCurrentUserEmail();
        Optional<User> user = userService.findByEmail(currentEmail);

        if (user.isPresent()) {
            User currentUser = user.get();
            currentUser.setName(updatedUser.getName());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setAddress(updatedUser.getAddress());
            userService.updateUser(currentUser);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!currentEmail.equals(user.get().getEmail())) {
            authentication.setAuthenticated(false);
            return "redirect:/logout";
        }

        return "redirect:/profile";
    }
}
