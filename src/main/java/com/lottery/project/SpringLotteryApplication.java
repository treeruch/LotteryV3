package com.lottery.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lottery.project.utility.Mapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@RestController
@SpringBootApplication
public class SpringLotteryApplication{
	protected Log logger = LogFactory.getLog(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringLotteryApplication.class, args);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Mapping.CONTROLLER.LOGIN)
	 public ModelAndView Welcome(HttpServletRequest request,HttpSession session) { 
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.LOGIN);
		logger.info("Start Project...");
		
		modelAndView.addObject("test","");
		return modelAndView; 
	}
}
