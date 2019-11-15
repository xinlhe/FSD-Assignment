package com.ibm.springframework.assignment.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ibm.springframework.assignment.entity.User;
import com.ibm.springframework.assignment.service.UserService;
import com.ibm.springframework.assignment.utils.GenerateUUidUtil;
import com.ibm.springframework.assignment.utils.VarifyCaptchaUtil;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "v2.0/new/{teamname}", method = RequestMethod.GET)
	@ResponseBody public JSONPObject foo5(@PathVariable String teamname,String callback, HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
       
        User user = new User();//userService.getUserInfoById(id);
        
        return new JSONPObject(callback, user);
	}
	
	@RequestMapping("/tologin")
	public ModelAndView toLogin(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ModelAndView andView = new ModelAndView();

		andView.setViewName("login");
		
		return andView;
	}
	
	@RequestMapping("/userLogin")
	public ModelAndView userLogin(User user, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, HttpSession session) {
		
		ModelAndView andView = new ModelAndView();
		
		User userSession = (User) session.getAttribute("userInfo");
		
		if(null != userSession) {
			
			if(userSession.getName().equalsIgnoreCase("admin")) {
				
				andView.addObject("userId", userSession.getId());
				
				andView.setViewName("menu_items_admin");
				
				andView.addObject("user_info", "You are administer !!!");
			}
			else {
				
				andView.addObject("userId", userSession.getId());
				
				andView.setViewName("menu_items_user");
				
				andView.addObject("user_info", "You are common user !!!");
			}
		}
		else {
			
			boolean varifyFlag = VarifyCaptchaUtil.varifyCaptcha(httpServletRequest, httpServletRequest.getParameter("vrifyCode"));

			if(varifyFlag) {
				
				User userInfo = userService.getUserInfo(user);
				
				if(null != userInfo) {
					
					session.setAttribute("userInfo", userInfo);
					
					if(userInfo.getName().equalsIgnoreCase("admin")) {
						
						andView.addObject("userId", userInfo.getId());
						
						andView.setViewName("menu_items_admin");
						
						andView.addObject("user_info", "You are administer !!!");
					}
					else {
						
						andView.addObject("userId", userInfo.getId());
						
						andView.setViewName("menu_items_user");
						
						andView.addObject("user_info", "You are common user !!!");
					}
				}
				else {
					
					andView.addObject("info", "You are not registered !!!");
					
					andView.setViewName("login");
				}
			}
			else {
				
				andView.addObject("info", "Captcha Code is Wrong !!!");
				
				andView.setViewName("login");
			}
			
		}
		
		return andView;
	}
	
	@RequestMapping("/toregister")
	public ModelAndView toRegister(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ModelAndView andView = new ModelAndView();

		andView.setViewName("register");
		
		return andView;
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, HttpSession session) {
		
		boolean varifyFlag = VarifyCaptchaUtil.varifyCaptcha(httpServletRequest, httpServletRequest.getParameter("vrifyCode"));
		ModelAndView andView = new ModelAndView();
		
		if(varifyFlag) {
			
			User user = new User();
			user.setId(GenerateUUidUtil.getUUid());
			user.setName(httpServletRequest.getParameter("name"));
			user.setEmail(httpServletRequest.getParameter("email"));
			user.setUsername(httpServletRequest.getParameter("username"));
			user.setPassword(httpServletRequest.getParameter("password"));
			
			userService.addUserInfo(user);
			
			session.setAttribute("userInfo", user);
			
			return "redirect:/user/userLogin";
		}
		else {
			
			andView.addObject("info", "Captcha Code is Wrong.");
			
			return "login";
		}
	}
	
	@RequestMapping("/toAccountUpdate")
	public String toAccountUpdate(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		User userinfo = new User();
		
		userinfo.setId(httpServletRequest.getParameter("userId"));
		
		userinfo = userService.getUserInfoById(userinfo.getId());
		
		model.addAttribute("userinfo", userinfo);
		
		return "account_update";
	}
	
	@RequestMapping("/accountUpdate")
	public String accountUpdate(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, HttpSession session, Model model) {
		
		boolean varifyFlag = VarifyCaptchaUtil.varifyCaptcha(httpServletRequest, httpServletRequest.getParameter("vrifyCode"));
		
		if(varifyFlag) {
			
			User user = new User();
			user.setId(httpServletRequest.getParameter("id"));
			user.setName(httpServletRequest.getParameter("name"));
			user.setEmail(httpServletRequest.getParameter("email"));
			user.setUsername(httpServletRequest.getParameter("username"));
			user.setPassword(httpServletRequest.getParameter("password"));
			
			userService.addUserInfo(user);
			
			session.setAttribute("userInfo", user);
			
			return "redirect:/user/userLogin";
		}
		else {
			
			model.addAttribute("info", "Captcha Code is Wrong.");
			
			return "account_update";
		}
	}
	
	@RequestMapping("/jumpToTutorialsPage")
	public String jumpToTutorialsPage(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model, HttpSession session) {
		
		User userinfo = (User) session.getAttribute("userInfo");
		
		model.addAttribute("name", userinfo.getName());
		
		return "menu_items_user_tutorials";
	}
	
	@RequestMapping("/jumpToAdminPage")
	public String jumpToAdminPage(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model, HttpSession session) {
		
		User userinfo = (User) session.getAttribute("userInfo");
		
		model.addAttribute("name", userinfo.getName());
		
		return "menu_items_user_admin";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model, HttpSession session) {
		
		session.invalidate();		
		
		return "login";
	}

}
