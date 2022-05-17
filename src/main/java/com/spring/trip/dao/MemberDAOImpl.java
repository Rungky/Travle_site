package com.spring.trip.dao;


public class MemberDAOImpl implements MemberDAO{
//	private DataSource dataFactory;
//	private Connection con;
//	private PreparedStatement pstmt;
//
//	public MemberDAOImpl() {
//		try {
//			Context ctx = new InitialContext();
//			Context envContext = (Context) ctx.lookup("java:comp/env");
//			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	  //회원가입
//	  public void join(MemberDTO memberDTO){
//	      
//	      try {
//	    	  con = dataFactory.getConnection();
//	    	  
//	    	  System.out.println("커넥션풀 성공");
//	    	  
//	    	  String query = "";
//		      query += "insert into tb_member(member_id, member_pw, member_names, member_tel)";
//		      query += "values (?,?,?,?)";
//	    	  
//		      pstmt = new LoggableStatement(con, query);
//		      System.out.println(((LoggableStatement)pstmt).getQueryString());
//
//		      pstmt = con.prepareStatement(query);
//	          pstmt.setString(1, memberDTO.getMember_id());
//	          pstmt.setString(2, memberDTO.getMember_pw());
//	          pstmt.setString(3, memberDTO.getMember_name());
//	          System.out.println("이름부문" + memberDTO.getMember_name());
//	          pstmt.setString(4, memberDTO.getMember_tel());
//	          
//	          int result = pstmt.executeUpdate();
//	          System.out.println("join 성공: "+ result);
//	          
//	      } catch (SQLException e) {
//	          e.printStackTrace();
//	      }finally {
//				try {
//					
//					if (con != null) con.close(); 
//					if (pstmt != null) pstmt.close();
//					
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//	      }
//	  }
//	  
//	  //로그인 기능 
//	  public MemberDTO login(MemberDTO memberDTO){
//		  MemberDTO m = new MemberDTO();
//		  
//		  try {
//	    	  con = dataFactory.getConnection();
//	    	  System.out.println("커넥션풀 성공");
//	    	  
//	    	  String query = "";
//		      query += " select * from tb_member ";
//		      query += " where member_id = ? and member_pw = ? ";
//		      System.out.println(query);
//		      
//		      pstmt = new LoggableStatement(con, query);
//		      pstmt.setString(1, memberDTO.getMember_id());
//		      pstmt.setString(2, memberDTO.getMember_pw());
//		      
//		      System.out.println("DAO id : "+ memberDTO.getMember_id());
//		      System.out.println("DAO pw : "+memberDTO.getMember_pw());
//
//		      System.out.println(((LoggableStatement)pstmt).getQueryString());
//	    	  
//	          ResultSet rs = pstmt.executeQuery();
//	          
//	          if(rs.next()) {
//	        	 String id = rs.getString("member_id");
//	        	 String pw = rs.getString("member_pw");
//	        	 System.out.println("if문 id : "+id);
//	        	 System.out.println("if문 pw : "+pw);
//
////	        	 memberDTO = new MemberDTO();
//	        	 m.setMember_id(id);
//	        	 m.setMember_pw(pw);
//	          
//	          } if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(con != null) {
//					con.close();
//				}
//				
//			}catch (Exception e) {
//				e.printStackTrace();
//			}	
//		  
//		  return m;
//	  }
//	  
//	  
//	  
//	  
//	  
//	  
//	  
//	  
//		//  세션에서 멤버 아이디를 받아 내정보 표시 (마이페이지)
//		public MemberDTO selectMember(String member_id ) {
//		MemberDTO memberDTO = new MemberDTO();
//			try {
//				con = dataFactory.getConnection();
//				System.out.println("커넥션풀 성공 => member 테이블");
//				 String query = "";
//				 query +="select member_id, rpad(substr(member_pw, 1, 2), 5, '*') as member_pw, ";
//				 query +=" member_names, member_tel ";
//				 query +=" from tb_member ";
//				 query +=" WHERE member_id=? ";
//				 query +=" ";
//					
//				System.out.println(query );
//				pstmt = con.prepareStatement(query);
//				pstmt.setString(1, member_id);
//				ResultSet rs = pstmt.executeQuery();
//				
//				while(rs.next()) {
//					String member_pw = rs.getString("member_pw");
//					String member_name = rs.getString("member_names");
//					String member_tel= rs.getString("member_tel");
//
//					memberDTO.setMember_id(member_id);
//					memberDTO.setMember_pw(member_pw);
//					memberDTO.setMember_name(member_name);
//					memberDTO.setMember_tel(member_tel);
//				}	if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (con != null) {
//					con.close();
//				}
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return memberDTO;
//		}
//		
//		
//	// 내 닉네임 정보 수정 (update)
//
//		public void modifyMemberName(String member_id, String member_names) {
//			
//			try {
//				con = dataFactory.getConnection();
//				System.out.println("커넥션 풀 성공 = >  내 닉네임 변경");
//				
//				String query ="";
//				query += " UPDATE TB_MEMBER ";
//				query += " SET MEMBER_NAMES= ? ";
//				query += " WHERE MEMBER_ID = ?";
//				query += " ";
//				System.out.println(query);
//				
//				pstmt = con.prepareStatement(query);
//				pstmt.setString(1, member_names);
//				pstmt.setString(2, member_id);
//				
//				ResultSet rs = pstmt.executeQuery();
//				
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (con != null) {
//					con.close();
//				}
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	// 내 비밀번호 정보 수정 (update)
//	public void modifyMemberPw(String member_id, String member_pw) {
//			
//			try {
//				con = dataFactory.getConnection();
//				System.out.println("커넥션풀 성공 => member수정");
//				String query ="";
//				query += " UPDATE TB_MEMBER ";
//				query += " SET MEMBER_PW= ? ";
//				query += " WHERE MEMBER_ID = ?";
//				query += " ";
//		
//				pstmt = con.prepareStatement(query);
//				pstmt.setString(1, member_pw);
//				pstmt.setString(2, member_id);
//				
//				ResultSet rs = pstmt.executeQuery();
//				
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (con != null) {
//					con.close();
//				}
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//
//	//================회원탈퇴하기=====================================
//
//	//회원 탈퇴하기 1-1. 리뷰댓글 삭제
//	public void removeComment(String member_id) {
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeComment");
//			String query=" ";
//			query+="DELETE FROM TB_COMMENT ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	//회원 탈퇴하기 1-2. 리뷰 삭제 
//	public void removeReview(String member_id) {
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeReview");
//			String query=" ";
//			query+="DELETE FROM TB_REVIEW ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//	//회원 탈퇴하기 1-3. 예약 목록 삭제
//	public void removeReservation(String member_id) {
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeReservation");
//			String query=" ";
//			query+="DELETE FROM TB_RESERVATION ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//	//회원 탈퇴하기 1-4. 좋아요 목록 삭제
//	public void removeLike(String member_id) {
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeLike");
//			String query=" ";
//			query+="DELETE FROM TB_LIKE ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//	//회원 탈퇴하기 1-5. 문의 목록 삭제
//	public void removeQuestion(String member_id) {
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeQuestion");
//			String query=" ";
//			query+="DELETE FROM TB_QUESTION ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//	//회원 탈퇴하기 1-6.  드디어 회원 삭제 
//	public void removeMember(String member_id) {
//		
//		try {
//			con = dataFactory.getConnection();
//			System.out.println("커넥션풀 성공 : => removeMember");
//			String query=" ";
//			query+="DELETE FROM TB_MEMBER ";
//			query += "WHERE member_id=? ";
//			query += "";
//
//			System.out.println(query);
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, member_id);
//		
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//		
//	//======메인인데 일단 여기다 할게여=====================================================================
//
//	public List<DormDTO> selectDormList(){
//		List<DormDTO> dormList = new ArrayList(); 
//		try {
//			con = dataFactory.getConnection();
//			String query = "";
//			query += "SELECT * ";
//			query +=  "FROM TB_DORM ";
//			query +=  "ORDER BY LIKE_CNT DESC ";
//			pstmt = con.prepareStatement(query);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				DormDTO dormDTO = new DormDTO(); 
//				dormDTO.setDorm_no(rs.getInt("dorm_no"));
//				dormDTO.setDorm_name(rs.getString("dorm_name"));
//				dormDTO.setDorm_contents(rs.getString("dorm_contents"));
//				dormDTO.setDorm_addr(rs.getString("dorm_addr"));
//				dormDTO.setDorm_picture(rs.getString("dorm_picture"));
//				dormDTO.setLike_cnt(rs.getInt("like_cnt"));
//				dormDTO.setOpt_wifi(rs.getInt("opt_wifi"));
//				dormDTO.setOpt_parking(rs.getInt("opt_parking"));
//				dormDTO.setOpt_aircon(rs.getInt("opt_aircon"));
//				dormDTO.setOpt_dryer(rs.getInt("opt_dryer"));
//				dormDTO.setOpt_wifi(rs.getInt("opt_wifi"));
//				dormDTO.setOpt_port(rs.getInt("opt_port"));
//				dormDTO.setDorm_category_no(rs.getInt("dorm_category_no"));
//				
//				dormList.add(dormDTO);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return dormList;
//	}
//
//
//
//	  
//	  
//	  
//	  
//	  
//	  
	
}