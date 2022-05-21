package com.spring.trip.dao;

import java.util.List;

import com.spring.trip.dto.DormDTO;
import com.spring.trip.dto.MemberDTO;

public interface AdminDAO {
	public List<MemberDTO> allMembers();
	public void adminMember(MemberDTO dto);
	public List<DormDTO> allDormsList();
	public void adminDorm(DormDTO dto);
	public int checkDormno(int dormno);
	public void adminDormInsert(DormDTO dto);
	public void adminDelDorm(int dormno);
}
