package com.lottery.project.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lottery.project.entity.Lottery;
import com.lottery.project.entity.User;
import com.lottery.project.model.LotteryModel;
import com.lottery.project.service.LotterService;
import com.lottery.project.service.UserService;
import com.lottery.project.utility.Mapping;

@Controller
@RestController
@SpringBootApplication
public class DashboardCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LotterService lotterService;
	
	protected Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(method = RequestMethod.POST, value = Mapping.CONTROLLER.DASHBOARD)
	 public ModelAndView dashboard(HttpServletRequest request,HttpSession session) { 
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.DASHBOARD);
		logger.info("Dashboard...");
         LotteryModel obj = lotterService.findDashboard();
		
		modelAndView.addObject("sumTopThree",obj.getSumTopThreePrice());
		modelAndView.addObject("sumTod",obj.getSumTodPrice());
		modelAndView.addObject("sumTopTwo",obj.getSumTopTwoPrice());
		modelAndView.addObject("sumUnderTwo",obj.getSumUnderTwoPrice());
		modelAndView.addObject("sumlottery",obj.getSumPrice());
		
		List<User> listUser = new ArrayList<User>();
    	System.out.println("--- Start Find list User ... ");
    	listUser = userService.findAll();
    	System.out.println("--- End Find list User. ");
    	
    	modelAndView.addObject("listUser",listUser);
		return modelAndView; 
	}
	
	@GetMapping("/home")
	public ModelAndView greeting(String name, Model model) {
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.DASHBOARD);
		System.out.println("### Start Home Dashboard...");
		
		System.out.println("-- Home Dashboard : Sum data From Table history");
		LotteryModel obj = lotterService.findDashboard();
		System.out.println("---------- End Sum data From Table history ----------");
		
		modelAndView.addObject("sumTopThree",obj.getSumTopThreePrice());
		modelAndView.addObject("sumTod",obj.getSumTodPrice());
		modelAndView.addObject("sumTopTwo",obj.getSumTopTwoPrice());
		modelAndView.addObject("sumUnderTwo",obj.getSumUnderTwoPrice());
		modelAndView.addObject("sumlottery",obj.getSumPrice());
		
		List<User> listUser = new ArrayList<User>();
    	System.out.println("--- Start Find list User ... ");
    	listUser = userService.findAll();
    	System.out.println("--- End Find list User. ");
    	
    	modelAndView.addObject("listUser",listUser);
		
		System.out.println("### End Home Dashboard.");
		return modelAndView; 
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(String name, Model model) {
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.LOGIN);
		logger.info("Log out...");
		return modelAndView; 
	}
	
}
