package arquitecture.ecommerce.infrastructure.controllers;

import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import arquitecture.ecommerce.application.usecases.UserService;
import arquitecture.ecommerce.domain.models.User;
import arquitecture.ecommerce.infrastructure.security.PasswordUtils;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.addUser(user);
            return "registerSuccess";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Correo electronico ya utilizado, ingrese otro correo.");
            return "registerUser";
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado. Por favor intentelo más tarde.");
            return "registerUser";
        }
    }

    @GetMapping("/loginView")
    public String loginView(Model model) {
        model.addAttribute("user", new User());
        return "loginUser";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        try {
            User userLogged = userService.loginUser(user.getEmail(), user.getPassword());
            if (PasswordUtils.checkPassword(user.getPassword(), userLogged.getPassword())) {
                session.setAttribute("user", userLogged);
                return "redirect:/";
            } else {
                model.addAttribute("error", "Contraseña incorrecta");
                model.addAttribute("email", user.getEmail());
                return "loginUser";
            }
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "Correo no registrado");
            return "loginUser";
        } catch (Exception e) {
            model.addAttribute("error", "Error inesperado. Por favor intentelo más tarde.");
            return "loginUser";
        }
    }

    @GetMapping("/logoutUser")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
