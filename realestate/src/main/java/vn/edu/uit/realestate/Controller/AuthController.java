package vn.edu.uit.realestate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.Config.UserService;

import static vn.edu.uit.realestate.Constants.homeUrl;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/custom-login")
    public String loadLoginPage(){
        return "login";
    }

    @PostMapping("/signup")
    public String login(@ModelAttribute("signup") User user){
        String token = userService.signUp(user);
        return UriComponentsBuilder.fromUriString(homeUrl)
                .queryParam("auth_token", token)
                .build().toUriString();
    }
}
