package com.my.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.my.dao.CustomerDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

public class CustomerService {
	private CustomerDAO dao;
	public CustomerService() {
		dao = new CustomerDAO();
	}
	public String login(String id, String pwd) {

		int state = -1;
		try {
			Customer c = dao.selectById(id);
			if(c.getPwd().equals(pwd)) {
				state = 1;
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String str = "{\"status\":" + state + ",\"id\": \"" + id +"\"}";
		//json-simple lib 활용
		
		JSONParser parser = new JSONParser();
		String str = "{\"status\":" + state + ",\"id\": \"" + id +"\"}";
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			long a = (Long)jsonObj.get("status");
			long b = (Long)jsonObj.get("id");
			System.out.println("status=" + a + "id=" + b);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		
		return str;
	}
	public String dupchk(String id) {
		int status = -1;
		try 
		{ 
			dao.selectById(id); 
			status =1;
		  
		}catch(NotFoundException e) { 
			e.printStackTrace(); 
		}
		//String str = "{\"status\":" + status +"}";
		JSONParser parser = new JSONParser();
		String str = "{\"status\":" + status +"}";
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			long a = (Long)jsonObj.get("status");
			System.out.println("status=" + a);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		
		return str;
	}
	
	public String join(String id,String pwd,String name, String buildingno, String addr) {
		int rs = 0;
		try 
		{ 
			rs=dao.InsertCustomer(id, pwd, name, buildingno, addr); 
			
			
		}catch(NotFoundException e) { 
			e.printStackTrace(); 
		}
		//String str = "{\"rs\":" + rs +"}";
		JSONParser parser = new JSONParser();
		String str = "{\"rs\":" + rs +"}";
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			long a = (Long)jsonObj.get("rs");
			System.out.println("rs=" + a);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		return str;
	}
	
	
	public String searchZip(String doro) {
		int status = -1;
		try 
		{ 
			dao.selectById(doro); 
			status =1;
		  
		}catch(NotFoundException e) { 
			e.printStackTrace(); 
		}
		//String str = "{\"status\":" + status +"}";
		JSONParser parser = new JSONParser();
		String str = "{\"status\":" + status +"}";
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			long a = (Long)jsonObj.get("status");
			System.out.println("status=" + a);
		} catch (ParseException e) {
		e.printStackTrace();
		}
		
		return str;
	}

}
