<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사적모임 | Document</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>

<body>
    <%@include file="header.jsp" %>
    <nav>
    	<div class="nav">
	        <div class="tap">
	            <div data-tap="st0" class="tap_list">내 정보</div>
                <div data-tap="st1" class="tap_list tap_state">회원 관리</div>
	            <div data-tap="st2" class="tap_list tap_state">숙소 관리</div>
<!-- 	            <div data-tap="st3" class="tap_list tap_state">문의 관리</div> -->
	        </div>
    	</div>
    </nav>
    <div id="wrap_admin">
    <div id="hidden_bg" class="nodisplay"></div>
    <div id="hidden_contents" class="nodisplay"></div>
    <div id="insert_type" data-type="insert"></div>
    <section>
        <div class="fl_center">
        <div id="st0">
                    <div class="my_info">
                        <div>
                            <img src="${contextPath }/resources/image/main2.jpg" style="width: 500px">
                        </div>

                        <div id="info_contents" >
                            <div>
                                <h3>관리자 페이지</h3>
                                <p>환영합니다. ${memberDTO.member_names} 님</p>
                            </div>
                            <div>
                                아이디 &nbsp;&nbsp;<span class="mem_info">${memberDTO.member_id}</span> <br>
                                닉네임&nbsp;&nbsp;<span class="mem_info"> ${memberDTO.member_names} </span><br>
                                전화번호<span class="mem_info">${memberDTO.member_tel} </span><br>
                            </div>
                             <div id="admin_form">
                                <form id="logout_form" action="${contextPath}/trip/logoutCheck.do">
                                    <input id="btn_logout" type="button" value="로그아웃">
                                </form>
                                <form id="withdraw_form" method="post" action="${contextPath}/trip/removeMember.do">
                                    <input id="btn_withdraw" type="button" value="회원탈퇴">
                                </form>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
                <!---->
            <div id="st1" class="nodisplay">
            	<div class="nodisplay" style="text-align:center;"><button data-type="mem" class="insert_bt" type="button">추가</button></div>
                <table>
                    <thead>
                        <tr>
                            <th>아이디</th>
                            <th>비밀번호</th>
                            <th>닉네임</th>
                            <th>전화번호</th>
                            <th>권한</th>
                            <th>수정</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="member" items="${membersList}">
                        <tr>
                            <td>${member.member_id}</td>
                            <input type="hidden" id="id" name="id" value="${member.member_id}">
                            <td><input type="text" id="pw" name="pw" readonly value="${member.member_pw}"></td>
                            <td><input type="text" id="name" name="name" readonly value="${member.member_names}"></td>
                            <td><input type="text" id="tel" name="tel" readonly value="${member.member_tel}"></td>
                            <td><input type="text" id="authority" name="authority" readonly value="${member.member_authority}"></td>
                            <td style="text-align: center;"><button class="bt" name="type" value="mem">수정하기</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="st2" class="nodisplay">
            	<div style="text-align:right;"><button data-type="dorm" class="insert_bt" type="button">숙소추가</button></div>
                <table> 
                    <thead>
                        <tr>
                            <th>숙소번호</th>
                            <th>카테고리</th>
                            <th>이름</th>
                            <th>소개</th>
                            <th>주소</th>
                            <th>그림 파일</th>
                            <th colspan="5">옵션</th>
                            <th>수정</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="dorm" items="${dormsList}">
                        <tr>
                            <input type="hidden" id="dormno" class="td_dorm" name="dormno" value="${dorm.dorm_no}">
                            <td>${dorm.dorm_no}</td>
                            <td><input type="text" id="category" class="td_dorm" name="category" readonly value="${dorm.dorm_category_no}"></td>
                            <td><input type="text" id="name" name="name" readonly value="${dorm.dorm_name}"></td>
                            <td><button type="button" class="contents_bt" name="content" value="${dorm.dorm_contents}">내용 보기</button></td>
                            <input type="hidden" id="contents_val" name="contents" value="${dorm.dorm_contents}">
                            <td><input type="text" id="addr" name="addr" readonly value="${dorm.dorm_addr}"></td>
                            <td><input type="text" id="picture" name="picture" readonly value="${dorm.dorm_picture}"></td>
                            <td>
                            <c:if test="${dorm.opt_wifi==1}">
                                wifi<input type="checkbox" id="wifi" name="wifi"  onClick="return false;" value="1" checked>
                            </c:if>
                            <c:if test="${dorm.opt_wifi!=1}">
                                wifi<input type="checkbox" id="wifi" name="wifi"  onClick="return false;" value="1">
                            </c:if>
                            </td>
                            <td>
                            <c:if test="${dorm.opt_parking==1}">
                                parking<input type="checkbox" id="parking" name="parking" onClick="return false;" value="1" checked>
                            </c:if>
                            <c:if test="${dorm.opt_parking!=1}">
                                parking<input type="checkbox" id="parking" name="parking" onClick="return false;" value="1">
                            </c:if>
                            </td>
                            <td>
                            <c:if test="${dorm.opt_aircon==1}">
                                aircon<input type="checkbox" id="aircon" name="aircon" onClick="return false;" value="1" checked>
                            </c:if>
                            <c:if test="${dorm.opt_aircon!=1}">
                                aircon<input type="checkbox" id="aircon" name="aircon" onClick="return false;" value="1">
                            </c:if>
                            </td>
                            <td>
                            <c:if test="${dorm.opt_dryer==1}">
                                dryer<input type="checkbox" id="dryer" name="dryer" onClick="return false;" value="1" checked>
                            </c:if>
                            <c:if test="${dorm.opt_dryer!=1}">
                                dryer<input type="checkbox" id="dryer" name="dryer" onClick="return false;" value="1">
                            </c:if>
                            </td>
                            <td>
                            <c:if test="${dorm.opt_port==1}">
                                port<input type="checkbox" id="port" name="port" onClick="return false;" value="1" checked>
                            </c:if>
                            <c:if test="${dorm.opt_port!=1}">
                                port<input type="checkbox" id="port" name="port" onClick="return false;" value="1">
                            </c:if>
                            </td>
                            <td style="text-align: center;"><button class="bt" name="type" value="dorm">수정하기</button></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="st3" class="nodisplay">
                <table>
                    <thead>
                        <tr>
                            <th>문의 번호</th>
                            <th>제목</th>
                            <th>내용</th>
                            <th>게시 날짜</th>
                            <th>수정</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td><input type="text" name="id" readonly value="값"></td>
                            <td><input type="text" name="id" readonly value="값"></td>
                            <td><input type="text" name="id" readonly value="값"></td>
                            <td style="text-align: center;"><button class="bt" type="button" name="mod_text" value=''>수정하기</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <input type="hidden" id="nextpage" value="">
        </div>
    </section>

    <script>
        let modify_text;
        $(".tap_list").off("click").on("click", function () {
            let tap_name = $(this).attr("data-tap");
            $("section > div > div").addClass("nodisplay");
            $(".tap_list").addClass("tap_state");
            $("#" + tap_name).removeClass("nodisplay");
            $(this).removeClass("tap_state");
        });
        
        $(".bt").off("click").on("click", function () {
      
            modify_text = ($(this).closest("div").find("thead").html());
            modify_text += ($(this).parent().parent().html());
            $("#nextpage").attr("value", modify_text);
            console.log($("#nextpage").html(modify_text));

            var _width = '1400';
            var _height = '250';
            var _left = Math.ceil(( window.screen.width - _width )/2);
            var _top = Math.ceil(( window.screen.height - _height )/2); 
            var _window = window.open("modify_admin.do", "modify", "width="+_width+",height="+_height+", left="+_left+",top="+_top);
        });
        
        $(".contents_bt").off("click").on("click", function () {
        	$("#hidden_bg").addClass("contents_bg_on");
        	$("#hidden_bg").removeClass("nodisplay");
        	$("#hidden_contents").removeClass("nodisplay");
        	$("#hidden_contents").html($(this).val());
        });
        
        $("#hidden_bg").off("click").on("click", function () {
        	$("#hidden_bg").addClass("nodisplay");
        	$("#hidden_bg").removeClass("contents_bg_on");
        	$("#hidden_contents").addClass("nodisplay");
        });
        
        $(".insert_bt").off("click").on("click", function () {
            $("#insert_type").attr("data-type", $(this).attr("data-type"));
           	insert_text = ($(this).closest("div").find("thead").html());
            let temp_text = $(this).parent().parent().html();

            var _width = '500';
            var _height = '1000';
            var _left = Math.ceil(( window.screen.width - _width )/2);
            var _top = Math.ceil(( window.screen.height - _height )/2); 
            var _window = window.open("insert_admin.do", "insert", "width="+_width+",height="+_height+", left="+_left+",top="+_top);
        });
        
        /*-----*/

        $("#btn_logout").off("click").on("click", function(){
            if(confirm("로그아웃하시겠습니까?")==true){
                $("#logout_form").submit();
            }else{
                return;
            }
        })
        $("#btn_withdraw").off("click").on("click", function(){
            if(confirm("탈퇴하시겠습니까?탈퇴한 회원은 복구되지 않습니다.")==true){
                $("#withdraw_form").submit();
            }else{
                return;
            }
        })
       
    </script>
</body>

</html>