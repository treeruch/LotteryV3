package com.lottery.project.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lottery.project.Repository.UserRepositoryJPA;
import com.lottery.project.entity.Customer;
import com.lottery.project.entity.Lottery;
import com.lottery.project.entity.ReceiptItem;
import com.lottery.project.entity.User;
import com.lottery.project.model.LotteryModel;
import com.lottery.project.model.UserModel;

@Service
public class UtilService {
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private LotterService lotterService;
	
	
	@Autowired
	private UserRepositoryJPA userRepositoryJPA;
	

	public List<LotteryModel> setThree(LotteryModel lotteryModel, int sumThree, int sumTod, HttpSession session, int totalList) {
		List<LotteryModel> listThree = new ArrayList<>();
		List<LotteryModel> listThreeSave = new ArrayList<>();
		List<String> dupThree = new ArrayList<>();
		
		 String[] arrayThree = lotteryModel.getThreeArray();
		 String[] topThreePriceArray = lotteryModel.getTopThreePriceArray();
		 String[] todPriceArray = lotteryModel.getTodPriceArray();
		  for(int i = 0 ; i < arrayThree.length ; i++) {
  			LotteryModel three = new LotteryModel();
  			LotteryModel threeSave = new LotteryModel();
  			if(arrayThree[i] != "" && arrayThree[i].length() == 3) {
  				
  				if(!dupThree.contains(arrayThree[i])) {   // Dup
  					three.setThree(arrayThree[i]);
  					threeSave.setThree(arrayThree[i]);
  	  				if(topThreePriceArray[i] != "") {
  	  					three.setTopThreePrice(this.formatPrice(Integer.valueOf(topThreePriceArray[i])));
  	  				    threeSave.setTopThreePrice(topThreePriceArray[i]);
  	  					sumThree = sumThree + Integer.valueOf(topThreePriceArray[i]);
  	  				} else {three.setTopThreePrice("0"); }
  	                 
  	  				if(todPriceArray[i] != "") {
  	                  	three.setTodPrice(this.formatPrice(Integer.valueOf(todPriceArray[i])));
  	                    threeSave.setTodPrice(todPriceArray[i]);
  	                  	sumTod = sumTod +  Integer.valueOf(todPriceArray[i]);
  	  				} else{three.setTodPrice("0");}
  	                  
  	                  if(!three.getTodPrice().equals("0") || !three.getTopThreePrice().equals("0")) {
  	                	listThree.add(three);
  	                	listThreeSave.add(threeSave);
  	  	    			totalList++;
  	  	    		    dupThree.add(arrayThree[i]);
  	                }
  				}
                  
  			  }
		  }
		  session.setAttribute("sumThree", sumThree);
		  session.setAttribute("sumTod", sumTod);
		  session.setAttribute("totalList", totalList);
		  session.setAttribute("listThreeSave", listThreeSave);
		  
		return listThree;
	}

	public List<LotteryModel> setTwo(LotteryModel lotteryModel, int sumTwo, int sumUnder, HttpSession session) {
		List<String> dupTwo = new ArrayList<>();
		int totalList = (int) session.getAttribute("totalList");
		List<LotteryModel> listTwo = new ArrayList<>();
		List<LotteryModel> listTwoSave = new ArrayList<>();
		String[] arrayTwo = lotteryModel.getTwoArray();
		 String[] topTwoPriceArray = lotteryModel.getTopTwoPriceArray();
		 String[] underTwoPriceArray = lotteryModel.getUnderTwoPriceArray();
		 
		 for(int j = 0 ; j< arrayTwo.length ; j++) {
	    		LotteryModel two = new LotteryModel();
	    		LotteryModel twoSave = new LotteryModel();
	    			if(arrayTwo[j] != "" && arrayTwo[j].length() == 2) {
	    				
	    				if(!dupTwo.contains(arrayTwo[j])) {
	    					two.setTwo(arrayTwo[j]);
	    					twoSave.setTwo(arrayTwo[j]);
			    			if(topTwoPriceArray[j] != "") {
			    				two.setTopTwoPrice(this.formatPrice(Integer.valueOf(topTwoPriceArray[j])));
			    				twoSave.setTopTwoPrice(topTwoPriceArray[j]);
			    				sumTwo = sumTwo + Integer.valueOf(topTwoPriceArray[j]);
			    			} else {two.setTopTwoPrice("0"); }
			    			if(underTwoPriceArray[j] != "") {
			    				two.setUnderTwoPrice(this.formatPrice(Integer.valueOf(underTwoPriceArray[j])));
			    				twoSave.setUnderTwoPrice(underTwoPriceArray[j]);
			    				sumUnder = sumUnder + Integer.valueOf(underTwoPriceArray[j]);
			    			} else {two.setUnderTwoPrice("0");}
			    			
				    			if(!two.getTopTwoPrice().equals("0") || !two.getUnderTwoPrice().equals("0")) {
				    				listTwo.add(two);
				    				listTwoSave.add(twoSave);
					    			totalList++;
					    			dupTwo.add(arrayTwo[j]);
				    			}
	    				}
	    		}
		  }
		 
		  session.setAttribute("sumTwo", sumTwo);
		  session.setAttribute("sumUnder", sumUnder);
		  session.setAttribute("totalList", totalList);
		  session.setAttribute("listTwoSave", listTwoSave);
		
		return listTwo;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserName(HttpSession session, LotteryModel userModel) {
		List<User> listUser = (List<User>) session.getAttribute("listUser");
		User obj = new User();
		String userName = "";
		for(int i = 0 ; i < listUser.size() ; i++) {
			int user = listUser.get(i).getUserId();
			if(user == userModel.getUserId()) {
				obj = listUser.get(i);
				userName = obj.getName();
			}
		}
		return userName;
	}
	
	public String formatPrice(int sumTopThree) {
		String number = String.valueOf(sumTopThree);
		double amount = Double.parseDouble(number);
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amount);
	}
	
	public void random6(List<String> listBack, String tod) {
		if(tod.length() == 3) {
			String sub1 = "";
			String sub2 = "";
			String sub3 = "";

			sub1 = tod.substring(0, 1);
			sub2 = tod.substring(1, 2);
			sub3 = tod.substring(2, 3);

			listBack.add(tod);
			if(!listBack.contains(sub1 + sub3 + sub2)) {
				listBack.add(sub1 + sub3 + sub2);
			}
			
			if(!listBack.contains(sub2 + sub1 + sub3)) {
				listBack.add(sub2 + sub1 + sub3);
			}
			
			if(!listBack.contains(sub2 + sub3 + sub1)) {
				listBack.add(sub2 + sub3 + sub1);
			}
			
			if(!listBack.contains(sub3 + sub1 + sub2)) {
				listBack.add(sub3 + sub1 + sub2);
			}
			
			if(!listBack.contains(sub3 + sub2 + sub1)) {
				listBack.add(sub3 + sub2 + sub1);
			}
			
		}
	}
	
	public String random6Back(Object[] obj, String tod) {
		String str = "";
		if(tod.length() == 3) {
			String sub1 = "";
			String sub2 = "";
			String sub3 = "";

			sub1 = tod.substring(0, 1);
			sub2 = tod.substring(1, 2);
			sub3 = tod.substring(2, 3);

			obj[0] = tod;
			obj[1] = sub1 + sub3 + sub2;
			obj[2] = sub2 + sub1 + sub3;
			obj[3] = sub2 + sub3 + sub1;
			obj[4] = sub3 + sub1 + sub2;
			obj[5] = sub3 + sub2 + sub1;
			System.out.println(obj[0] + " , " + obj[1] + " , " + obj[2] + " , " + obj[3] + " , " + obj[5] + " , " + obj[5]);
			str = obj[0] + " , " + obj[1] + " , " + obj[2] + " , " + obj[3] + " , " + obj[5] + " , " + obj[5];
		}
		return str;
	}
	
	public XSSFWorkbook getExcelLottery(String headerStr, String startDateEndDate) {
		
		List<LotteryModel> listTopThree = lotterService.findLotteryTopThree(0);
		List<LotteryModel> listTod = this.findLotteryTod(0);
		List<LotteryModel> listTopTwo = lotterService.findLotteryTopTwo(0);
		List<LotteryModel> listUnderTwo = lotterService.findLotteryUnderTwo(0);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("รวมทั้งหมด");
		this.exportExcell( workbook,sheet,startDateEndDate,headerStr,listTopThree,listTod,listTopTwo,listUnderTwo);
		
        List<User> list =  userRepositoryJPA.findAll();
		for(User user : list) {
			
			 listTopThree = lotterService.findLotteryTopThree(user.getUserId()); 
			 listTod =  this.findLotteryTod(user.getUserId()); 
			 listTopTwo = lotterService.findLotteryTopTwo(user.getUserId()); 
			 listUnderTwo = lotterService.findLotteryUnderTwo(user.getUserId());
			 
			 sheet = workbook.createSheet(user.getName());
		     this.exportExcell(workbook,sheet,startDateEndDate,headerStr,listTopThree,listTod,listTopTwo,listUnderTwo);
		}
		return workbook;
	}
	
	
	
	private void exportExcell(XSSFWorkbook workbook, XSSFSheet sheet, String startDateEndDate, String headerStr, List<LotteryModel> listTopThree, List<LotteryModel> listTod,
			List<LotteryModel> listTopTwo, List<LotteryModel> listUnderTwo) {

		/* Draw header */
		int indexRow = 0;
		Row rowHeaderl = sheet.createRow(indexRow);
		Cell cellHeaderl;
		cellHeaderl = rowHeaderl.createCell(0);
		cellHeaderl.setCellValue(startDateEndDate);
		indexRow++;
		
		String[] header = headerStr.toString().split(",");
		int[] headerCell = { 4000, 4000, 0, 4000, 4000, 4000, 0, 4000, 4000, 0, 4000, 4000 };
		rowHeaderl = sheet.createRow(indexRow);
		
		for (int i = 0; i < header.length; i++) {
			if(i == 2 || i == 6 || i == 9) {
				continue;
			}
			
			sheet.setColumnWidth(i, headerCell[i]);
			cellHeaderl = rowHeaderl.createCell(i);
			cellHeaderl.setCellStyle(UtilService.setBorderMediumCenterVerticalCenter(workbook));
			cellHeaderl.setCellValue(header[i]);
			if (i == 0 || i == 3 || i == 4 || i == 7 || i == 10) {
				this.setCellHeaderCenter(cellHeaderl, workbook);
			} else {
				this.setCellHeaderLeft(cellHeaderl, workbook);
			}
		}
		indexRow++;
		/* data */
		
		/* Top Three */
		for (int j = 0 ; j < listTopThree.size() ; j++) {
			
			rowHeaderl = sheet.createRow(indexRow);
			cellHeaderl = rowHeaderl.createCell(0);
			cellHeaderl.setCellValue(listTopThree.get(j).getTopThree());
			this.setCellDataCenter(cellHeaderl, workbook);
			
			cellHeaderl = rowHeaderl.createCell(1);
			cellHeaderl.setCellValue(listTopThree.get(j).getSumTopThreePrice());
			this.setCellDataLeft(cellHeaderl, workbook);
			indexRow++;
		}
		
		/* Tod */
		indexRow = 2;
       for (int k = 0 ; k < listTod.size() ; k++) {
    	    XSSFRow sheetrow = sheet.getRow(indexRow);
    	    if(sheetrow == null) {
     		   sheetrow = sheet.createRow(indexRow);
     	   }
    	   
    	    cellHeaderl = sheetrow.getCell(3);
    	    if(cellHeaderl == null){
    	    	cellHeaderl = sheetrow.createCell(3);
            }
			cellHeaderl.setCellValue(listTod.get(k).getTod());
			this.setCellDataCenter(cellHeaderl, workbook);
			
			cellHeaderl = sheet.getRow(indexRow).getCell(4);
			if(cellHeaderl == null){
    	    	cellHeaderl = sheetrow.createCell(4);
            }
			cellHeaderl.setCellValue(listTod.get(k).getTodBack());
			this.setCellDataCenter(cellHeaderl, workbook);
			
			cellHeaderl = sheet.getRow(indexRow).getCell(5);
			if(cellHeaderl == null){
    	    	cellHeaderl = sheetrow.createCell(5);
            }
			cellHeaderl.setCellValue(listTod.get(k).getTodPrice());
			this.setCellDataLeft(cellHeaderl, workbook);
			indexRow++;
		}
       
       /* Top two */
       indexRow = 2;
       for (int l = 0 ; l < listTopTwo.size() ; l++) {
    	   XSSFRow sheetrow = sheet.getRow(indexRow);
    	   if(sheetrow == null) {
    		   sheetrow = sheet.createRow(indexRow);
    	   }
    	   
    	   cellHeaderl = sheetrow.getCell(7);
   	       if(cellHeaderl == null){
   	    	 cellHeaderl = sheetrow.createCell(7);
           }
			cellHeaderl.setCellValue(listTopTwo.get(l).getTopTwo());
			this.setCellDataCenter(cellHeaderl, workbook);
			
			cellHeaderl = sheet.getRow(indexRow).getCell(8);
			if(cellHeaderl == null){
    	    	cellHeaderl = sheetrow.createCell(8);
            }
			cellHeaderl.setCellValue(listTopTwo.get(l).getSumTopTwoPrice());
			this.setCellDataLeft(cellHeaderl, workbook);
			indexRow++;
       }
       
       /* Under two */
       indexRow = 2;
       for (int l = 0 ; l < listUnderTwo.size() ; l++) {
    	   XSSFRow sheetrow = sheet.getRow(indexRow);
    	   if(sheetrow == null) {
    		   sheetrow = sheet.createRow(indexRow);
    	   }
    	   
    	   cellHeaderl = sheetrow.getCell(10);
   	       if(cellHeaderl == null){
   	    	cellHeaderl = sheetrow.createCell(10);
           }
			cellHeaderl.setCellValue(listUnderTwo.get(l).getUnderTwo());
			this.setCellDataCenter(cellHeaderl, workbook);
			
			cellHeaderl = sheet.getRow(indexRow).getCell(11);
			if(cellHeaderl == null){
    	    	cellHeaderl = sheetrow.createCell(11);
            }
			cellHeaderl.setCellValue(listUnderTwo.get(l).getSumUnderTwoPrice());
			this.setCellDataLeft(cellHeaderl, workbook);
			indexRow++;
       }
		
	}

	private void setCellDataLeft(Cell cell, XSSFWorkbook workbook) {
		XSSFCellStyle styleHeader = workbook.createCellStyle();

		styleHeader.setAlignment(HorizontalAlignment.LEFT);
		cell.setCellStyle(styleHeader);

	}

	private void setCellDataCenter(Cell cell, XSSFWorkbook workbook) {
		XSSFCellStyle styleHeader = workbook.createCellStyle();
		DataFormat fmt = workbook.createDataFormat();

		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setDataFormat(fmt.getFormat("@"));
		cell.setCellStyle(styleHeader);

		Font font = workbook.createFont();
		font.setBold(true);
		styleHeader.setFont(font);

	}

	private void setCellHeaderLeft(Cell cell, XSSFWorkbook workbook) {
		XSSFCellStyle styleHeader = workbook.createCellStyle();

		styleHeader.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.LEFT);
		cell.setCellStyle(styleHeader);

		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);
		styleHeader.setFont(font);
	}

	private void setCellHeaderCenter(Cell cell, XSSFWorkbook workbook) {
		XSSFCellStyle styleHeader = workbook.createCellStyle();
		DataFormat fmt = workbook.createDataFormat();

		styleHeader.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleHeader.setAlignment(HorizontalAlignment.CENTER);
		styleHeader.setDataFormat(fmt.getFormat("@"));
		cell.setCellStyle(styleHeader);

		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBold(true);
		styleHeader.setFont(font);

	}

	public static XSSFCellStyle setBorderMediumCenterVerticalCenter(XSSFWorkbook workbook) {
		XSSFCellStyle cellstyle = workbook.createCellStyle();
		cellstyle.setBorderTop(BorderStyle.MEDIUM);
		cellstyle.setBorderRight(BorderStyle.MEDIUM);
		cellstyle.setBorderBottom(BorderStyle.MEDIUM);
		cellstyle.setBorderLeft(BorderStyle.MEDIUM);
		cellstyle.setAlignment(HorizontalAlignment.CENTER);
		cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellstyle.setWrapText(true);

		return cellstyle;
	}
	
	private List<LotteryModel> findLotteryTod(int userId) {
		List<Lottery> listTod = new ArrayList<>();
		List<LotteryModel> listTodStr = new ArrayList<>();
		
    	List<LotteryModel> list = lotterService.findLotteryTod(userId);
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
		   listTod = lotterService.getListBack(obj,userId);
    		
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
		return listTodStr;
	}

	public Map<String, Object> getPdfLottery(String startDate, Long id) {
		Map<String, Object> data = new HashMap<>();

		List<LotteryModel> listTopThree = lotterService.findLotteryTopThree(0);
		List<LotteryModel> listTod = this.findLotteryTod(0);
		List<LotteryModel> listTopTwo = lotterService.findLotteryTopTwo(0);
		List<LotteryModel> listUnderTwo = lotterService.findLotteryUnderTwo(0);

        List<User> list =  userRepositoryJPA.findAll();
		for(User user : list) {
			
			if(id == user.getUserId()) {
				 listTopThree = lotterService.findLotteryTopThree(user.getUserId()); 
				 listTod =  this.findLotteryTod(user.getUserId()); 
				 listTopTwo = lotterService.findLotteryTopTwo(user.getUserId()); 
				 listUnderTwo = lotterService.findLotteryUnderTwo(user.getUserId());
 
				UserModel userModel = new UserModel();
				userModel.setName(user.getName());
				userModel.setStartDate(startDate);
		        data.put("user", userModel);
		        
		        List<ReceiptItem> receiptItems = new ArrayList<>();
		        ReceiptItem receiptItem1 = new ReceiptItem();
		        receiptItem1.setDescription("Test Item 1");
		        receiptItem1.setQuantity(1);
		        receiptItem1.setUnitPrice(100.0);
		        receiptItem1.setTotal(100.0);
		        receiptItems.add(receiptItem1);     
		        data.put("receiptItems", receiptItems);
			}
			
		    // this.exportExcell(workbook,sheet,startDateEndDate,headerStr,listTopThree,listTod,listTopTwo,listUnderTwo);
		}

			return data;
	}
}
