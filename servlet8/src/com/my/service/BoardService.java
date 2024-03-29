package com.my.service;

import java.util.List;

import com.my.dao.BoardDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		dao = new BoardDAO();
	}
	public com.my.vo.PageBean<Board>  boardList() throws NotFoundException{
		return boardList(1);
	}
	public com.my.vo.PageBean<Board> boardList(int currentPage) 
	                    throws NotFoundException{
		int cntPerPage = 3; //한페이지별 보여줄 목록수 
		int cntPerPageGroup = 4;//페이지그룹에서 보여줄 페이지수
		int startRow = ((currentPage-1) * cntPerPage)+1;
		int endRow = currentPage * cntPerPage;
		List<Board> list = dao.select(startRow, endRow);
		
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		//페이지그룹의 시작페이지,끝페이지 계산
		int startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup +1;
		int endPage = startPage + cntPerPageGroup -1;
		if(endPage > maxPage) {
			endPage= maxPage;
		}
		
		System.out.println(currentPage+"="+startPage + endPage);
		
		PageBean<Board> pb = 
				new PageBean<>();
		pb.setCurrentPage(currentPage);//현재페이지
		pb.setCntPerPage(cntPerPage);//페이지별 목록수
		pb.setList(list); //목록
		pb.setTotalCnt(totalCnt); //총건수
		pb.setMaxPage(maxPage);	//최대페이지수
		return pb;
	}	
}
