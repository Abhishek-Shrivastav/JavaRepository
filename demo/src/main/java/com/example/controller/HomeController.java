package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.facade.UserService;
import com.example.validator.UserValidator;

@Controller
public class HomeController
{
	@Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String welcomePage() {
		
		return "index";
	}

	@RequestMapping(value = {"/homePage"}, method = RequestMethod.GET)
	public String homePage() {
		
		return "homePage";
	}
	
	@RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
	public String userPage() {
		
		return "userPage";
	}
	
	@RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
	public String adminPage() {
		
		return "adminPage";
	}
	
	@RequestMapping(value = {"/loginPage"}, method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error",required = false) String error,@RequestParam(value = "logout",	required = false) String logout,Model model)
	{
		if(error != null)
		{
			model.addAttribute("error", "Invalid Credentials provided.");
		}

		if(logout != null)
		{
			model.addAttribute("message", "Logged out from JournalDEV successfully.");
		}

		return "loginPage";
	}
	
    /*@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model)
    {
    	userValidator.validate(userForm, bindingResult);
    	
        if(bindingResult.hasErrors())
        {
        	return "registration";
        }
        
        userService.save(userForm);
        
        return "redirect:/loginPage";
    }*/
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model)
    {
        model.addAttribute("userForm", new UserDto());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Model model)
    {
    	userValidator.validate(userForm, bindingResult);
    	
        if(bindingResult.hasErrors())
        {
        	return "registration";
        }
        
        User user = new User();
        
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setPasswordConfirm(userForm.getPasswordConfirm());
        user.setUsername(userForm.getUsername());
        userForm.getRoles();
        
        userService.save(user,userForm.getRoles());
        
        return "redirect:/loginPage";
    }
}
