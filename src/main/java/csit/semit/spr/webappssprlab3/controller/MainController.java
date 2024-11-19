package csit.semit.spr.webappssprlab3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String showLoginPage(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails != null ? "redirect:/main" : "Login";
    }

    @GetMapping("/login")
    public String loginPage(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam(required = false) String logout,
                            Model model) {
        if (userDetails != null) {
            return "redirect:/main";
        }
        if (logout != null) {
            model.addAttribute("message", "You have been successfully logged out.");
        }
        return "Login";
    }
    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("showDot", "h");
        return "Welcome";
    }

}