package application.controllers;

import application.entity.CoffeeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.repository.ShopRepository;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee_shop")
public class ShopController {
    private final ShopRepository shopRepository;
    @GetMapping("/login")
    public ModelAndView getLoginPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
//        model.addAttribute("user", new CoffeeUser());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute CoffeeUser user){
        ModelAndView modelAndView = new ModelAndView();
        if(shopRepository.findCoffeeUserByEmailAndPassword
                (user.getEmail(), user.getPassword()) != null){
            modelAndView.setViewName("success");
        }else{
            modelAndView.setViewName("redirect:/coffee_shop/login");
        }
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView getRegisterPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerUserPage");
        model.addAttribute("user", new CoffeeUser());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registerUser(@ModelAttribute CoffeeUser user, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        shopRepository.save(user);
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(@ModelAttribute CoffeeUser user, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/coffee_shop/login");
        return modelAndView;
    }
}
