package com.spring.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.trip.dao.AdminDAO;
import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public List<MemberDTO> allMembers() {
		List<MemberDTO> list = adminDAO.allMembers();
		return list;
	}

	@Override
	public void adminMember(MemberDTO dto) {
		 adminDAO.adminMember(dto);
	}

	@Override
	public List<DormDTO> allDormsList() {
		List<DormDTO> list = adminDAO.allDormsList();
		return list;
	}

	@Override
	public void adminDorm(DormDTO dto) {
		adminDAO.adminDorm(dto);
	}

	@Override
	public int checkDormno(int dormno) {
		return adminDAO.checkDormno(dormno);
	}

	@Override
	public void adminDormInsert(DormDTO dto) {
		adminDAO.adminDormInsert(dto);
	}

	@Override
	public void adminDelDorm(int dormno) {
		adminDAO.adminDelDorm(dormno);
	}
	

}
