package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    private final AdminService adminService; //管理者登録はAdminServiceにまかせる

  
    public AdminAuthController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @GetMapping("/signup")
    public String signupForm(Model model) {
    	model.addAttribute("adminForm", new AdminForm());
    	return "admin/signup";
    }
    
    @PostMapping("/signup")
    public String signup(
            @Validated @ModelAttribute("adminForm") AdminForm adminForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "admin/signup";
        }
        adminService.register(adminForm);
        return "redirect:/admin/signin";
    }
    
    @GetMapping("/signin")
    public String signin() {
      return "admin/signin";
    }
    
}
