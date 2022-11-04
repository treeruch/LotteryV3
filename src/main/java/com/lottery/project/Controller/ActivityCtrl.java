package com.lottery.project.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lottery.project.utility.Mapping;

@Controller
public class ActivityCtrl {
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(method = RequestMethod.POST, value = Mapping.CONTROLLER.ACTIVITY)
	 public ModelAndView addActivity(HttpServletRequest request,HttpSession session) { 
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.ACTIVITY);
		logger.info("Add Activity...");
		return modelAndView; 
	}

	
	@ResponseBody
	@RequestMapping(value = "/saveActivity",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 public ModelAndView saveActivity(HttpServletRequest request,HttpSession session,@RequestParam("arrayAccRevenue[]") String[] arrAccRevenue,
			 @RequestParam("arrayRevenueAmount[]") String[] arrRevenueAmount,
			 @RequestParam("arrayNoteRevenue[]") String[] arrNoteRevenue,
			 @RequestParam("arrayAccExpenditure[]") String[] arrAccExpenditure,
			 @RequestParam("arrayExpenditureAmount[]") String[] arrExpenditureAmount,
			 @RequestParam("arrayNoteExpenditure[]") String[] arrNoteExpenditure) { 
		ModelAndView modelAndView = new ModelAndView(Mapping.PAGE.ACTIVITY);
		logger.info("Save Activity...");
		
		//Revenue
		String[] arrayAccRevenue = arrAccRevenue;
		String[] arrayRevenueAmount = arrRevenueAmount;
		String[] arrayNoteRevenue = arrNoteRevenue;
		for(int i = 0 ; i < arrayAccRevenue.length ; i++) {
			System.out.println(arrayAccRevenue[i]);
			System.out.println(arrayRevenueAmount[i]);
			System.out.println(arrayNoteRevenue[i]);
		}
		
		//Expenditure
		String[] arrayAccExpenditure = arrAccExpenditure;
		String[] arrayExpenditureAmount = arrExpenditureAmount;
		String[] arrayNoteExpenditure = arrNoteExpenditure;
		for(int i = 0 ; i < arrayAccExpenditure.length ; i++) {
			System.out.println(arrayAccExpenditure[i]);
			System.out.println(arrayExpenditureAmount[i]);
			System.out.println(arrayNoteExpenditure[i]);
		}
		
		return modelAndView; 
	}
	
}
