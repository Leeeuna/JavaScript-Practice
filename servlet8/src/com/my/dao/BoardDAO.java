package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.my.exception.NotFoundException;
import com.my.service.BoardService;
import com.my.vo.Board;

public class BoardDAO {
	public int count() throws NotFoundException{
		//select count(*) from board
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##ORACLE_USER";
			String password = "dmsk";
			con = DriverManager.getConnection(url, user, password);
			String selectSQL = "select count(*) from board";				
			pstmt = con.prepareStatement(selectSQL);//sql구문 송신
			rs = pstmt.executeQuery();//sql구문 실행
			rs.next();
			return rs.getInt(1);
		}catch(ClassNotFoundException| SQLException e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException e) {}
			}
			if(pstmt != null) {
				try {
				pstmt.close();
				}catch(SQLException e) {}
			}	
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {}
			}
		}
		
	}
	
	
	
	
	public List<Board> select(int startRow, int endRow) throws NotFoundException{
		List<Board> list = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##ORACLE_USER";
			String password = "dmsk";
			con = DriverManager.getConnection(url, user, password);
			
			String selectSQL = "select a.*\r\n" + 
					" from\r\n" + 
					"    (select rownum rn, level, board.*\r\n" + 
					"    from board\r\n" + 
					"    start with parent_no is Null\r\n" + 
					"    connect by prior board_no = parent_no\r\n" + 
					"    order siblings by board_no desc) a\r\n" +
					"where a.rn between ? and ?";
					
			pstmt = con.prepareStatement(selectSQL);//sql구문 송신
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs  = pstmt.executeQuery();//sql구문 실행
			while(rs.next()) { 
				int board_no = rs.getInt("board_no");
				int parent_no = rs.getInt("parent_no");
				int level = rs.getInt("level");
				String board_subject = rs.getString("board_subject");
				String board_writer = rs.getString("board_writer");
				String board_content = rs.getString("board_content");
				Date board_time = rs.getDate("board_time");
				String board_pwd = rs.getString("board_pwd");
				Board board = new Board(board_no,parent_no,level,board_subject,board_writer,board_content,board_time,board_pwd);
				list.add(board);
			}
			if(list.size()==0) {
				throw new NotFoundException("게시목록이 존재하지 않습니다.");
			}
		}catch(ClassNotFoundException| SQLException e) {
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
		return list;
	}
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();
		try {
			System.out.println(dao.count()); //7
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
	
