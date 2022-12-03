package com.lottery.project.Controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lottery.project.entity.Customer;
import com.lottery.project.entity.History;
import com.lottery.project.entity.Lottery;
import com.lottery.project.entity.ReceiptItem;
import com.lottery.project.entity.User;
import com.lottery.project.model.HistoryModel;
import com.lottery.project.model.LotteryModel;
import com.lottery.project.service.ExportPdfService;
import com.lottery.project.service.HistoryServcie;
import com.lottery.project.service.LotterService;
import com.lottery.project.service.UserService;
import com.lottery.project.service.UtilService;
import com.lottery.project.utility.Mapping;



@Controller
public class LotteryCtrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private HistoryServcie historyService;
	
	@Autowired
	private LotterService lotterService;
	
	@Autowired
	private ExportPdfService exportPdfService;
	
	
	private static final SimpleDateFormat sdf_ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy",Locale.US);
	
	String startDate = sdf_ddMMyyyy.format(new Date());
	
	
	// GET Top Three
	@GetMapping(Mapping.GET.GET_MAP_LOTTERY_TOP_THREE)
    public ModelAndView lotteryTopThree(HttpSession session) {
		System.out.println("### Start GET TopThree ... ");
    	ModelAndView modelview = new ModelAndView();
    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOP_THREE);
    	
    	System.out.println("-- GET TopThree : get data From Table lottery");
    	List<LotteryModel> list = lotterService.findLotteryTopThree(0);
    	System.out.println("---------- End get data From Table lottery ----------");
    	modelview.addObject("list",list);
    	System.out.println("### End GET TopThree. ");
        return modelview;
   }
	
	// POST Top Three
	@RequestMapping(value = Mapping.POST.POST_MAP_LOTTERY_TOP_THREE,method = {RequestMethod.POST})
	public ModelAndView lotteryTopThree(HttpServletRequest request,LotteryModel lotteryModel, HttpSession session) throws Exception{
		System.out.println("### Start GET TopThree Risk ... ");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOP_THREE);
		
		List<LotteryModel> listRisk = lotterService.FindRisk(lotteryModel);
		
		if(lotteryModel.getRiskSearch() > 0 && lotteryModel.getRiskSearch() <= 100) {
			modelview.addObject("riskSearch",lotteryModel.getRiskSearch());
		}
    	modelview.addObject("list",listRisk);
    	System.out.println("### End GET TopThree Risk. ");
		return modelview; 
	}
	
	// GET Tod
		@GetMapping(Mapping.GET.GET_MAP_LOTTERY_TOD)
	    public ModelAndView lotteryTod(HttpSession session) {
			System.out.println("### Start GET Tod ... ");
	    	ModelAndView modelview = new ModelAndView();
	    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOD);
	    	
	    	List<Lottery> listTod = new ArrayList<>();
	    	List<LotteryModel> listTodStr = new ArrayList<>();
			
	    	List<LotteryModel> list = lotterService.findLotteryTod(0);
	    	List<String> listBack = new ArrayList<>();
	    	List<String> listStrTod = new ArrayList<>();
	    	for(int i = 0 ; i< list.size() ; i++) {
	    		if(!listBack.contains(list.get(i).getTod())) {
	    			String tod = list.get(i).getTod();
		    		utilService.random6(listBack,tod);
		    		listStrTod.add(list.get(i).getTod());
	    		} 
	    		
	    	}
	    	
	    	for(String str:listStrTod) {
    		   Object[] obj = new Object[6];
    		   utilService.random6Back(obj,str);
    		   listTod = lotterService.getListBack(obj,0);
	    		
				   for(int i = 0 ; i<listTod.size() ; i++) {
					   LotteryModel objlottery = new LotteryModel();
					   if(i==0) {
						   objlottery.setTod(listTod.get(i).getTod());
						   objlottery.setTodBack(listTod.get(i).getTod());
						   objlottery.setTodPrice(String.valueOf(listTod.get(i).getTodPrice()));
					   } else {
						   objlottery.setTod("");
						   objlottery.setTodBack(listTod.get(i).getTod());
						   objlottery.setTodPrice(String.valueOf(listTod.get(i).getTodPrice()));
					   }
					        objlottery.setRisk("0 %");
					        objlottery.setRecive("0");
					        objlottery.setSend(utilService.formatPrice(listTod.get(i).getTodPrice()));
					       objlottery.setSumTodPrice(utilService.formatPrice(listTod.get(i).getTodPrice()));
					   
					   listTodStr.add(objlottery);
			    	}
	    	}
	    	
	    	modelview.addObject("list",listTodStr);
	    	System.out.println("### End GET Tod. ");
	        return modelview;
	   }
		
	   // POST Tod
		@RequestMapping(value = Mapping.POST.POST_MAP_LOTTERY_TOD,method = {RequestMethod.POST})
		public ModelAndView lotteryTod(HttpServletRequest request,LotteryModel lotteryModel, HttpSession session) throws Exception{
			System.out.println("### Start GET Tod Risk ... ");
			ModelAndView modelview = new ModelAndView();
			modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOD);
			
			int risk = lotteryModel.getRiskSearch();
			
			List<Lottery> listTod = new ArrayList<>();
	    	List<LotteryModel> listTodStr = new ArrayList<>();
			
	    	List<LotteryModel> list = lotterService.findLotteryTod(0);
	    	List<String> listBack = new ArrayList<>();
	    	List<String> listStrTod = new ArrayList<>();
	    	for(int i = 0 ; i< list.size() ; i++) {
	    		if(!listBack.contains(list.get(i).getTod())) {
	    			String tod = list.get(i).getTod();
		    		utilService.random6(listBack,tod);
		    		listStrTod.add(list.get(i).getTod());
	    		} 
	    		
	    	}
	    	
	    	for(String str:listStrTod) {
    		   Object[] obj = new Object[6];
    		   utilService.random6Back(obj,str);
    		   listTod = lotterService.getListBack(obj,0);
	    		
				   for(int i = 0 ; i<listTod.size() ; i++) {
					   LotteryModel objlottery = new LotteryModel();
					   if(i==0) {
						   objlottery.setTod(listTod.get(i).getTod());
						   objlottery.setTodBack(listTod.get(i).getTod());
						   objlottery.setTodPrice(String.valueOf(listTod.get(i).getTodPrice()));
					   } else {
						   objlottery.setTod("");
						   objlottery.setTodBack(listTod.get(i).getTod());
						   objlottery.setTodPrice(String.valueOf(listTod.get(i).getTodPrice()));
					   }
					        objlottery.setRisk(String.valueOf(risk +" %"));
					        objlottery.setRecive(String.valueOf((float) ((float) (risk * Integer.valueOf(listTod.get(i).getTodPrice())) / 100) ));
					        objlottery.setSend(String.valueOf( (float) (( (float) (( 100 - risk) * Integer.valueOf(listTod.get(i).getTodPrice())) / 100))));
					       objlottery.setSumTodPrice(utilService.formatPrice(listTod.get(i).getTodPrice()));
					   
					   listTodStr.add(objlottery);
			    	}
	    	}
			
			if(lotteryModel.getRiskSearch() > 0 && lotteryModel.getRiskSearch() <= 100) {
				modelview.addObject("riskSearch",lotteryModel.getRiskSearch());
			}
	    	modelview.addObject("list",listTodStr);
	    	System.out.println("### End GET Tod Risk. ");
			return modelview; // view
		}

	
	// GET Top Two
		@GetMapping(Mapping.GET.GET_MAP_LOTTERY_TOP_TWO)
	    public ModelAndView lotteryTopTwo(HttpSession session) {
			System.out.println("### Start GET TopTwo ... ");
	    	ModelAndView modelview = new ModelAndView();
	    	//modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
	    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOP_TWO);
			
	    	List<LotteryModel> list = lotterService.findLotteryTopTwo(0);
	    	modelview.addObject("list",list);
	    	System.out.println("### End GET TopTwo. ");
	        return modelview;
	   }
		
	// POST Top Two
		@RequestMapping(value = Mapping.POST.POST_MAP_LOTTERY_TOP_TWO,method = {RequestMethod.POST})
		public ModelAndView lotteryTopTwo(HttpServletRequest request,LotteryModel lotteryModel, HttpSession session) throws Exception{
			System.out.println("### Start GET TopTwo Risk ... ");
			ModelAndView modelview = new ModelAndView();
			modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_TOP_TWO);
			
			List<LotteryModel> listRisk = lotterService.FindRiskTopTwo(lotteryModel);
			
			if(lotteryModel.getRiskSearch() > 0 && lotteryModel.getRiskSearch() <= 100) {
				modelview.addObject("riskSearch",lotteryModel.getRiskSearch());
			}
	    	modelview.addObject("list",listRisk);
	    	System.out.println("### End GET TopTwo Risk. ");
			return modelview; // view
		}
		
		// GET Under Two
		@GetMapping(Mapping.GET.GET_MAP_LOTTERY_UNDER_TWO)
	    public ModelAndView lotteryUnderTwo(HttpSession session) {
			System.out.println("### Start GET UnderTwo ... ");
	    	ModelAndView modelview = new ModelAndView();
	    	//modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
	    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_UNDER_TWO);
			
	    	List<LotteryModel> list = lotterService.findLotteryUnderTwo(0);
	    	modelview.addObject("list",list);
	    	System.out.println("### End GET UnderTwo. ");
	        return modelview;
	   }
		
	   // POST Under Two Risk
		@RequestMapping(value = Mapping.POST.POST_MAP_LOTTERY_UNDER_TWO,method = {RequestMethod.POST})
		public ModelAndView lotteryUnderTwo(HttpServletRequest request,LotteryModel lotteryModel, HttpSession session) throws Exception{
			System.out.println("### Start GET UnderTwo Risk ... ");
			ModelAndView modelview = new ModelAndView();
			modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_UNDER_TWO);
			
			List<LotteryModel> listRisk = lotterService.FindRiskUnderTwo(lotteryModel);
			
			if(lotteryModel.getRiskSearch() > 0 && lotteryModel.getRiskSearch() <= 100) {
				modelview.addObject("riskSearch",lotteryModel.getRiskSearch());
			}
	    	modelview.addObject("list",listRisk);
	    	System.out.println("### End GET UnderTwo Risk. ");
			return modelview; // view
		}

	@SuppressWarnings("unchecked")
	@GetMapping(Mapping.GET.GET_MAP_LOTTERY_ADD)
    public ModelAndView lotteryAdd(HttpSession session) {
		System.out.println("### Start GET Add Lottery ... ");
    	ModelAndView modelview = new ModelAndView();
    	//modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
		
    	List<User> listUser = new ArrayList<User>();
    	System.out.println("--- Start Find list User ... ");
    	listUser = userService.findAll();
    	System.out.println("--- End Find list User. ");
    	
    	modelview.addObject("listUser",listUser);
		session.setAttribute("listUser", listUser);
		
		// back page Edit
		if(null != session.getAttribute("userIdBackAdd")) {
			System.out.println("---  Start Edit Lottery ... ");
			String userHidden = (String) session.getAttribute("userIdBackAdd");
			System.out.println("--  User Edit Lottery : "+userHidden);
			
			List<LotteryModel> listThree = (List<LotteryModel>) session.getAttribute("listThree");
			List<LotteryModel> listTwo = (List<LotteryModel>) session.getAttribute("listTwo");
    				
    		modelview.addObject("userHidden",userHidden);
    		
    		String topThree = ""; 
    		String topThreePrice = ""; 
    		String topTodPrice = ""; 
    		String topTwo = ""; 
    		String topTwoPrice = ""; 
    		String underTwoPrice = ""; 
    		for(int i = 0 ; i < listThree.size() ; i++) {
    			LotteryModel obj = listThree.get(i);
    			if(i == 0) {
    				topThree = obj.getThree();
        			topThreePrice = obj.getTopThreePrice();
        			topTodPrice = obj.getTodPrice();
    			} else {
    				topThree = topThree + ":" +obj.getThree();
    				topThreePrice = topThreePrice + ":" + obj.getTopThreePrice();
    				topTodPrice = topTodPrice + ":" + obj.getTodPrice();
    			}
    		}
    		
    		for(int j = 0 ; j < listTwo.size() ; j++) {
    			LotteryModel obj = listTwo.get(j);
    			if(j == 0) {
        			topTwo = obj.getTwo();
        			topTwoPrice = obj.getTopTwoPrice();
        			underTwoPrice = obj.getUnderTwoPrice();
    			} else {
    				topTwo = topTwo + ":" +obj.getTwo();
    				topTwoPrice = topTwoPrice + ":" + obj.getTopTwoPrice();
        			underTwoPrice = underTwoPrice + ":" + obj.getUnderTwoPrice();
    			}
    			
    		}
    		
    		modelview.addObject("listThree",topThree);
    		modelview.addObject("listThreePrice",topThreePrice.replace(",", ""));
    		modelview.addObject("topTodPrice",topTodPrice.replace(",", ""));
    		
    		modelview.addObject("listTwo",topTwo);
    		modelview.addObject("listTwoPrice",topTwoPrice.replace(",", ""));
    		modelview.addObject("listUnderTwoPrice",underTwoPrice.replace(",", ""));
    		System.out.println("---  End Edit Lottery. ");
    	}
		
		System.out.println("### End GET Add Lottery. ");
        return modelview;
   }
	
	@RequestMapping(value = Mapping.POST.POST_MAP_LOTTERY_ADD,method = {RequestMethod.POST})
	public ModelAndView LotteryAdd(LotteryModel lotteryModel, HttpSession session) throws Exception{
		System.out.println("### Start Post Add Lottery ... ");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_SHOW);
		try {
			int sumThree = 0;
			int sumTod = 0;;
			int sumTwo = 0;
			int sumUnder = 0;
			int totalList = 0;
			
			List<LotteryModel> listTwo = new ArrayList<>();
			List<LotteryModel> listThree = new ArrayList<>();
			
			listThree = utilService.setThree(lotteryModel,sumThree,sumTod,session,totalList);
			listTwo = utilService.setTwo(lotteryModel,sumTwo,sumUnder,session);
			
			int sumTopThreePrice = (int) session.getAttribute("sumThree");
			int sumTodPrice = (int) session.getAttribute("sumTod"); 
			int sumTopTwoPrice = (int) session.getAttribute("sumTwo"); 
			int sumUnderTwoPrice = (int) session.getAttribute("sumUnder"); 
			
			modelview.addObject("sumTopThreePrice", utilService.formatPrice(sumTopThreePrice));
			modelview.addObject("sumTodPrice",utilService.formatPrice(sumTodPrice));
			modelview.addObject("listThree",listThree);
			
			modelview.addObject("sumTopTwoPrice", utilService.formatPrice(sumTopTwoPrice));
			modelview.addObject("sumUnderTwoPrice",utilService.formatPrice(sumUnderTwoPrice));
			modelview.addObject("listTwo",listTwo);
			
			int totalPrice = sumTopThreePrice+sumTodPrice+sumTopTwoPrice+sumUnderTwoPrice;
			modelview.addObject("totalList",utilService.formatPrice((int) session.getAttribute("totalList")));
			modelview.addObject("totalPrice",utilService.formatPrice(totalPrice));
			
			this.setSession(session,listThree,listTwo,sumTopThreePrice,sumTodPrice,sumTopTwoPrice,sumUnderTwoPrice,totalPrice);
			String userName = utilService.getUserName(session,lotteryModel);
			session.setAttribute("userName", userName);
			session.setAttribute("userId", lotteryModel.getUserId());
			System.out.println("-- User name : "+userName);
			System.out.println("-- Page Add to Page Confirm ");
			
			// back page Edit
			session.setAttribute("userIdBackAdd", String.valueOf(lotteryModel.getUserId()));
			session.setAttribute("listThree", listThree);
			session.setAttribute("listTwo", listTwo);
			modelview.addObject("userName",userName);
			System.out.println("### End Post Add Lottery. ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelview; // view
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = Mapping.POST.POST_MAP_INSERT_LOTTERY,method = {RequestMethod.POST})
	public ModelAndView insertLottery(LotteryModel userModel, HttpSession session) throws Exception{
		System.out.println("### Start Post Insert Lottery ... ");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_LIST);
		
			List<LotteryModel> listThree = (List<LotteryModel>) session.getAttribute("listThreeSave");
			List<LotteryModel> listTwo = (List<LotteryModel>) session.getAttribute("listTwoSave");
			int sumTopThreePrice = (int) session.getAttribute("sumTopThreePrice");
			int sumTodPrice = (int) session.getAttribute("sumTodPrice");
			int sumTopTwoPrice = (int) session.getAttribute("sumTopTwoPrice");
			int sumUnderTwoPrice = (int) session.getAttribute("sumUnderTwoPrice");
			int totalPrice = (int) session.getAttribute("totalPrice");
			
			System.out.println("--- Start FindAll Group lottery ... ");
			List<History> listhis = historyService.findAll();
			int groupLottery = listhis.size()+1;
			System.out.println("--- End FindAll Group lottery ... ");
			// History
			
			History history = new History();
			history.setCreateBy((String) session.getAttribute("userName"));
			history.setUserId((int) session.getAttribute("userId"));
			history.setGroupLottery(groupLottery);
			history.setSumTopThreePrice(sumTopThreePrice);
			history.setSumTodPrice(sumTodPrice);
			history.setSumTopTwoPrice(sumTopTwoPrice);
			history.setSumUnderTwoPrice(sumUnderTwoPrice);
			history.setSumPrice(totalPrice);
			history.setCreateDate(new Date());
			
			System.out.println("=== Start INSERT TABLE lottery  &&  TABLE history ... ");
			historyService.save(history,listThree,listTwo,groupLottery,session);
			System.out.println("=== End INSERT TABLE lottery  && TABLE history.");
			
			List<HistoryModel> list = historyService.findAllDesc();
			modelview.addObject("listHistory",list);
			
			// clear data
			session.setAttribute("userIdBackAdd", null);
			System.out.println("### End Post Insert Lottery. ");
		return modelview; // view
	}
	
	@GetMapping(Mapping.GET.GET_MAP_LOTTERY_LIST)
    public ModelAndView lotteryList(HttpSession session) {
		System.out.println("### Start lottery list ...");
    	ModelAndView modelview = new ModelAndView();
    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_LIST);
    	
	
    	List<HistoryModel> list = historyService.findAllDesc();
		modelview.addObject("listHistory",list);
		System.out.println("### End lottery list.");
        return modelview;
   }
	
	@GetMapping("/editHistory/{id}")
	public ModelAndView editHistory(@PathVariable(name = "id", required = false) Long id,
			Model model, HttpSession session) throws Exception {
		System.out.println("Start view ...");
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_VIEW);
		System.out.println("Group id : "+ id);

		List<Lottery> list = lotterService.findViewDetail(id);
		List<LotteryModel> listThree = new ArrayList<>();
		List<LotteryModel> listTwo = new ArrayList<>();
		for(Lottery ls : list) {
			LotteryModel three = new LotteryModel();
			LotteryModel two = new LotteryModel();
			
			if(ls.getTopThree() != null) {
				three.setThree(ls.getTopThree());
				three.setTopThreePrice(utilService.formatPrice(ls.getTopThreePrice()));
				three.setTod(ls.getTod());
				three.setTodPrice(utilService.formatPrice(ls.getTodPrice()));
				
			}
			
			if(ls.getTopTwo() != null) {
				two.setTwo(ls.getTopTwo());
				two.setTopTwoPrice(utilService.formatPrice(ls.getTopTwoPrice()));
				two.setUnderTwo(ls.getUnderTwo());
				two.setUnderTwoPrice(utilService.formatPrice(ls.getUnderTwoPrice()));
			}
			
			if(three.getThree() != null || three.getTod() != null) {
				listThree.add(three);
			}
			if(two.getTwo() != null || two.getUnderTwo() != null) {
				listTwo.add(two);
			}
			
		}
		
		List<History> listHistory = historyService.findViewDetail(id);
		for (History history : listHistory) {
			modelview.addObject("userName",history.getCreateBy());
			modelview.addObject("totalPrice",utilService.formatPrice(history.getSumPrice()));
			modelview.addObject("sumTopThreePrice",utilService.formatPrice(history.getSumTopThreePrice()));
			modelview.addObject("sumTodPrice",utilService.formatPrice(history.getSumTodPrice()));
			modelview.addObject("sumTopTwoPrice",utilService.formatPrice(history.getSumTopTwoPrice()));
			modelview.addObject("sumUnderTwoPrice",utilService.formatPrice(history.getSumUnderTwoPrice()));
		}
		
		modelview.addObject("listThree",listThree);
		modelview.addObject("listTwo",listTwo);
		System.out.println("End view.");
		return modelview; // view
	}

	private void setSession(HttpSession session, List<LotteryModel> listThree, List<LotteryModel> listTwo, int sumTopThreePrice,
			int sumTodPrice, int sumTopTwoPrice, int sumUnderTwoPrice, int totalPrice) {
		session.setAttribute("listThree", listThree);
		session.setAttribute("listTwo", listTwo);
		session.setAttribute("sumTopThreePrice", sumTopThreePrice);
		session.setAttribute("sumTodPrice", sumTodPrice);
		session.setAttribute("sumTopTwoPrice", sumTopTwoPrice);
		session.setAttribute("sumUnderTwoPrice", sumUnderTwoPrice);
		session.setAttribute("totalPrice", totalPrice);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "resource" })
	@RequestMapping(value = Mapping.GET.EXPORT_LOTTERY , method = {RequestMethod.GET})
	@ResponseBody
	public byte[] ExportLotteryTopThree(HttpServletRequest request,LotteryModel lottery, 
			HttpSession session, HttpServletResponse response) throws Exception{
		
		String nameFile = "Lottery_"+sdf.format(new Date());;
		XSSFWorkbook workbook = new XSSFWorkbook();
		response.setHeader("Content-Disposition", "attachment;filename="+nameFile+".xlsx");
		String header = "สามตัวบน, ราคา / บาท, '', โต๊ด , เลข 6 กลับ , ราคา / บาท, '',สองตัวบน, ราคา / บาท, '', สองตัวล่าง, ราคา / บาท"; 
		
		/* Sum */
		workbook = utilService.getExcelLottery(header,startDate);
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	   try {
		   workbook.write(byteArrayOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				byteArrayOutputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return byteArrayOutputStream.toByteArray(); 
	}

	@GetMapping("backToAdd")
    public ModelAndView backToAdd(HttpSession session) {
    	ModelAndView modelview = new ModelAndView();
    	modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
    
    	int userId = (int) session.getAttribute("userId");
    	
    	modelview.addObject("userHidden",userId);
        return modelview;
   }
	
	
	// GET Download pdf
		@GetMapping(Mapping.GET.GET_DOWNLOAD_PDF)
	    public ModelAndView downloadPDF(HttpSession session) {
			System.out.println("### Start GET downloadPDF ... ");
	    	ModelAndView modelview = new ModelAndView();
	    	//modelview.setViewName(Mapping.VIEW.VIEW_MAP_LOTTERY_ADD);
	    	modelview.setViewName(Mapping.VIEW.VIEW_DOWNLOAD_PDF);
			
	    	List<User> listUser = new ArrayList<User>();
	    	System.out.println("--- Start Find list User ... ");
	    	listUser = userService.findAll();
	    	System.out.println("--- End Find list User. ");
	    	
	    	modelview.addObject("listUser",listUser);
	    	
	    	System.out.println("### End GET downloadPDF. ");
	        return modelview;
	   }
		
		
		@GetMapping("/downloadReceipt")
	    public void downloadReceipt(HttpServletResponse response) throws IOException {
	        Map<String, Object> data = createTestData();
	        ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("receipt", data);
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");
	        IOUtils.copy(exportedData, response.getOutputStream());
	    }

	    private Map<String, Object> createTestData() {
	        Map<String, Object> data = new HashMap<>();
	        Customer customer = new Customer();
	        customer.setCompanyName("Simple Solution");
	        customer.setContactName("John Doe");
	        customer.setAddress("123, Simple Street");
	        customer.setEmail("contact@simplesolution.dev");
	        customer.setPhone("123 456 789");
	        data.put("customer", customer);

	        List<ReceiptItem> receiptItems = new ArrayList<>();
	        ReceiptItem receiptItem1 = new ReceiptItem();
	        receiptItem1.setDescription("Test Item 1");
	        receiptItem1.setQuantity(1);
	        receiptItem1.setUnitPrice(100.0);
	        receiptItem1.setTotal(100.0);
	        receiptItems.add(receiptItem1);

	        ReceiptItem receiptItem2 = new ReceiptItem();
	        receiptItem2.setDescription("Test Item 2");
	        receiptItem2.setQuantity(4);
	        receiptItem2.setUnitPrice(500.0);
	        receiptItem2.setTotal(2000.0);
	        receiptItems.add(receiptItem2);

	        ReceiptItem receiptItem3 = new ReceiptItem();
	        receiptItem3.setDescription("Test Item 3");
	        receiptItem3.setQuantity(2);
	        receiptItem3.setUnitPrice(200.0);
	        receiptItem3.setTotal(400.0);
	        receiptItems.add(receiptItem3);

	        data.put("receiptItems", receiptItems);
	        return data;
	    }

}
