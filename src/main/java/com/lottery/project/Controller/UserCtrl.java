package com.lottery.project.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lottery.project.entity.User;
import com.lottery.project.service.UserService;
import com.lottery.project.utility.Constant;
import com.lottery.project.utility.Mapping;


@Controller
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	 private static final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
	
	@GetMapping(Mapping.GET.GET_MAP_USER)
    public ModelAndView user(HttpSession session) {
    	ModelAndView modelview = new ModelAndView();
    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_USER);
    	System.out.println("Start get user ...");
	
		modelview.addObject("listUser",this.getUser(modelview));
		System.out.println("End get user ...");
        return modelview;
   }
	
	@RequestMapping(value = Mapping.POST.POST_MAP_USER ,method = {RequestMethod.POST})
	public ModelAndView InsertUser(User user, HttpSession session) throws Exception{
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_USER);
		System.out.println("### Start save user ...");
		
		List<User> list = userService.findAll();
		System.out.println("-- Name : "+user.getName());
		System.out.println("-- INSERT TABLE UserLottery");
		User userSave = new User();
		userSave.setUserId(list.size()+1);
		userSave.setName(user.getName());
		userSave.setCreateDate(new Date());
		userService.save(userSave);
		
		this.getUser(modelview);
		System.out.println("### End save user.");
		return modelview; // view
	}
	
	@GetMapping("/deleteUser/{id}")
	public ModelAndView DeleteUser(@PathVariable(name = "id", required = false) Long id,
			Model model, HttpSession session) throws Exception {
		System.out.println("Start delete user ...");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_USER);
		System.out.println("userId : "+ id);
		
		//Select User_id From id user
		long userId = userService.findAllById(id);
		System.out.println("user_Id = "+userId);
		
		User user = new User();
		user.setId(id);
		userService.delete(user,userId);
		
		this.getUser(modelview);
		System.out.println("End delete user ...");
		return modelview; // view
	}
	
	private List<User> getUser(ModelAndView modelview) {
		String startDate = sdf_ddMMyyyy.format(new Date());
		String endDate = sdf_ddMMyyyy.format(new Date());
		
		List<User> listUser = userService.findAllDesc(startDate,endDate);
		modelview.addObject("listUser",listUser);
		return listUser;
	}

}
