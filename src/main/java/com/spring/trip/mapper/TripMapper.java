package com.spring.trip.mapper;

import java.sql.Date;
import java.util.List;

import com.spring.trip.dto.CheckDTO;
import com.spring.trip.dto.MemberDTO;
import com.spring.trip.dto.ReservationDTO;

public interface TripMapper {

	void insertReservation(String member, Date reserve_checkin, Date reserve_checkout, int reserve_pay,
			int room_no, int dorm_no);
	
	
	int reserDelete(int reserve_no);
	
	
	List<ReservationDTO> selectReservationsList(String member);
	
	
	MemberDTO memberDto(String memer_id);
	
	
	
	
}
