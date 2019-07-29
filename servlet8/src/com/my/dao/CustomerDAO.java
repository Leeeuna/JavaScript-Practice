package com.my.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.Customer;
import com.my.vo.Post;

public class CustomerDAO {
	//고객정보 : id,pwd,name,buildingno,addr
	
	public Customer selectById(String id) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##ORACLE_USER";
			String password = "dmsk";
			con = DriverManager.getConnection(url, user, password);
			String loginSQL = "SELECT * FROM customer WHERE id=?";
			pstmt = con.prepareStatement(loginSQL);
			pstmt.setString(1, id);
			rs  = pstmt.executeQuery();
			if(rs.next()) {  //if(rs.next()==true)
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setPwd(rs.getString("pass"));
				c.setName(rs.getString("name"));
				Post p = new Post();
				p.setBuildingno(rs.getString("buildingno"));
				c.setPost(p);
				c.setAddr(rs.getString("addr2"));
				return c;
			}
			throw new NotFoundException("아이디가 존재하지 않습니다.");
		}catch(Exception e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) 
				try {
					rs.close();
				}catch(SQLException e) {}
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}
		
		
	}
	public int InsertCustomer(String id,String pwd,String name,String buildingno,String addr) throws NotFoundException{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "C##ORACLE_USER";
				String password = "dmsk";
	
				con = DriverManager.getConnection(url, user, password);
				String selectsubmitSQL = "insert into customer values(?,?,?,?,?)";
				pstmt = con.prepareStatement(selectsubmitSQL);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, name);
				pstmt.setString(4, buildingno);
				pstmt.setString(5, addr);
				rs  = pstmt.executeUpdate();

				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
//			if(rs != 0) 
//				try {
//					rs.close();
//				}catch(SQLException e) {}
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}return rs;
		
	
}

//				if(rs.next()) { 
//					String id = rs.getString("id");
//					String pwd = rs.getString("pwd");
//					String name = rs.getString("name");
//					String buildingno = rs.getString("buildingno");
//					String addr2 = rs.getString("addr2");
//					result += "{\"id\":\""+ id +"\",\"pwd\":\""+ pwd+"\",\"name\":\"" + name + "\", \"buildingno\":\""+ buildingno+"\",\"addr2\":\"" + addr2 + "\"}";
//				}
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
////				if(rs != 0) 
////					try {
////						rs.close();
////					}catch(SQLException e) {}
//				if(pstmt != null) 
//					try {
//					pstmt.close();
//					}catch(SQLException e) {}
//				if(con != null) 
//					try {
//						con.close();
//					}catch(SQLException e) {}
//			}return rs;
//			
//		
//	}
//	
//	//void insert(Customer c){
//	//	 	throws AddException{
//	//	try{
//	//
//	//	}catch(ClassNotFoundException e){
//	//	throw new AddException(e.getMessage());
//	//	}catch(SQLException e){
//	//	throw new AddException(e.getMessage());
//	//	}finally{
//	//DB연결닫기
//	//	}
//	//}
//	
//	public ArrayList<Map<String,String>> selectByDoro(String doro) throws NotFoundException{
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "C##ORACLE_USER";
//			String password = "dmsk";
//			con = DriverManager.getConnection(url, user, password);
//			
//			String selectDoroSQL = "SELECT zipcode,  \r\n" + 
//			        "buildingno, "+
//					"sido||' ' \r\n" + 
//					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
//					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
//					"|| doro ||' ' \r\n" + 
//					"|| building1\r\n" + 
//					"|| DECODE(building2,'0', '', '-'||building2) ||' ' \r\n" + 
//					"|| '('|| dong || ri || DECODE(building, '', '', ',' ||building) ||')'\r\n" + 
//					" AS addrdoro" + //"||chr(13)||chr(10) \r\n" +
//					","+
//					"sido ||' ' \r\n" + 
//					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
//					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
//					"|| dong || ri ||' ' ||  zibun1 || DECODE(zibun2, '0', '',  '-'|| zibun2)    ||' ' || DECODE(building, '', '', '(' ||building ||')')     "
//					+ " AS addrzibun  \r\n" + 
//					
//					"FROM post\r\n" + 
//					"WHERE (doro || ' ' || building1 || DECODE(building2,'0', '', '-'||building2)) LIKE ? ";
//			
//			pstmt = con.prepareStatement(selectDoroSQL);
//			pstmt.setString(1, "%" + doro+ "%");
//			rs  = pstmt.executeQuery();
//			
//			HashMap map = new HashMap();
//			ArrayList> list = (ArrayList>) cmanager.getList();
//
//
//
//			
//			ArrayList<Map<String, String>> list  = new ArrayList<Map<String, String>>();
//			while (rs.next()) {
//				HashMap<String,String> map = new HashMap<String,String>();
//				map.put("zipcode", rs.getString(1));
//				map.put("addrdoro", rs.getString(2));
//				map.put("addrzibun", rs.getString(3));
//				map.put("buildingno", rs.getString(4));
//				list.add(map);
//				}
//			return list;
//
//
//			
//			int cnt = 0;
//			while(rs.next()) { 
//				Customer c = new Customer();
//				Post p = new Post();
//				p.setZipcode(rs.getString("zipcode"));
//				p.setZipcode(rs.getString("addrdoro"));
//				p.setZipcode(rs.getString("addrzibun"));
//				p.setZipcode(rs.getString("buildingno"));
//		
//				if(cnt != 0) {
//					result += ",";
//				}
//				
//				result += "{\"zipcode\":\""+ zipcode +"\",\"addrdoro\":\""+ addrdoro+"\",\"addrzibun\":\"" + addrzibun + "\", \"buildingno\":\""+ buildingno+"\"}";
//				
//			}
//			
//			throw new NotFoundException("아이디가 존재하지 않습니다.");
//		}catch(Exception e) {
//			throw new NotFoundException(e.getMessage());
//		}finally {
//				try {
//					rs.close();
//				}catch(SQLException e) {}
//				try {
//				pstmt.close();
//				}catch(SQLException e) {}
//				try {
//					con.close();
//				}catch(SQLException e) {}
//		}		
//	}
 
	
	
}
