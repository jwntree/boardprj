<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글</title>
<%@ include file="../include/header.jsp" %>
<style>
	ul{
    display: inline-block;
	}
	li {
		list-style: none;
		float: left;
		padding: 10px;
	}
    
</style>

<script type="text/javascript">
$(document).ready(function(){
	listReply(1);
	$("#btnUpdete").click(function(){
		location.href = "/board/updateView.do?bno=${dto.bno}";

	})
	
	$("#btnList").click(function(){
		location.href = "/board/list.do";
	})
	
    $("#btnReply").click(function(){
    	writeReply();
    });    
})

function deletePost() {
	location.href = "/board/deleteView.do?bno=${dto.bno}";
}

//댓글목록
function listReply(num){
    $.ajax({
        type: "post",
        url: "${path}/board/reply/list.do",
        data : {bno: ${dto.bno},
        		curReplyPage: num},
        success: function(result){
            replys = result.replys;
            pages = result.pages;
            //lyply count
            $("#replycnt").html(result.replyCount);
        	//replies
            let output = ""
            for(var i in replys){
            	output += "<div id=" +"\"comment_" + replys[i].rno +"\">"
                output += '<div class="comment"><span>'+ replys[i].writer +"</span>";
                console.log(replys[i].writerId)
                if(replys[i].writerId){
                	output += '<a href="/member/info.do?userId=' + replys[i].writerId+ '">*</a>';
                }
                output += "<span>" + "("+DateFormat(replys[i].updateDate)+")</span>";
                output += "<p id=r_text_" + replys[i].rno +">"+ replys[i].content.replaceAll("\n","<br>")+"</p>";
                
                output +='<input type="hidden" id="mine" value="' +replys[i].mine+ '">'
                output +='<input type="hidden" id="deleted" value="' +replys[i].deleted+ '">'
                output +='<input type="hidden" id="loginUser" value="' +replys[i].loginUser+ '">'

                if(replys[i].mine == true || replys[i].loginUser == false){
                	output += '<button type="button" class="btnShowReplyUpdate" onclick="showReplyModify(' +replys[i].rno+')">수정</button>'
                	output += '<button type="button" class="btnShowReplyDelete" onclick="showReplyDelete(' +replys[i].rno+')">삭제</button>'
                }
                output += "</div></div>"
            }
            $("#listReply").html(output);
            //pages
            let out = ""
            out += '<div><ul>'
       		 if(pages.prev){
       			out += '<li><a href="javascript:listReply(' + (pages.startPage-1) +')">이전</a></li>'
       		 }
       		 for (let i = pages.startPage; i < pages.endPage; i++) {
       			out += '<li><a href="javascript:listReply('+ i +')">' + i +'</a></li>'
       		}
       		 if(pages.next && pages.endPage > 0){
       			out += '<li><a href="javascript:listReply(' + (pages.endPage+1) +')">다음</a></li>'
       		 }
       		 out+="</ul></div>"
       		 $("#ReplyPages").html(out);

        }
    });
}

function writeReply(){
    var replytext=$("#replytext").val();
    var ReplyWriter=$("#ReplyWriter").val();
    var ReplyPassword=$("#ReplyPassword").val();

	var param = { 'bno' : ${dto.bno}, 'content': replytext };
    if(ReplyWriter == ""){
        alert("이름을 입력하세요");
        document.boardForm.ReplyWriter.focus();
        return;
    }
    if(replytext == ""){
        alert("내용을 입력하세요");
        document.boardForm.replytext.focus();
        return;
    }
    if(ReplyPassword == ""){
        alert("비밀번호를 입력하세요");
        document.boardForm.ReplyPassword.focus();
        return;
    }
	if(ReplyWriter){
    	param['writer'] = ReplyWriter
	}
	if(ReplyPassword){
		var regx = /^[A-Za-z0-9]{4,12}$/; 
		if(!regx.test(ReplyPassword)){
			alert("비밀번호에 영숫자 4~10글자를 입력해주세요 ");
			return;
		}
    	param['password'] = ReplyPassword
    }

    $.ajax({
        type: "post",
        url: "${path}/board/reply/insert.do",
        data : param,
        success: function(data){
			if($.trim(data) == "false") {
				alert('댓글등록에 실패했습니다.');
			} 
			else if($.trim(data) == "true") {
			   alert('댓글이 등록되었습니다.');
            }
            listReply(1);
        }
    });
}

// **댓글 수정화면 생성 함수
function showReplyModify(rno){
		let r_text = $("#r_text_"+rno).text()
		let deleted = $("#comment_" + rno +" > .comment > #deleted").val()
		let loginUser = $("#comment_" +rno +" > .comment > #loginUser").val()
		let mine = $("#comment_" +rno +" > comment > #mine")
		if((mine == false && loginUser == true) || deleted == "Y"){
			alert("수정할 수 없는 댓글입니다.")
			return
		}
		let output = ""
		output+= '<div class="ReplyModify">'
	    if(mine == false && loginUser == false){
			output+='<label for="password">패스워드</label><input name="password" type="password" id="password"><p>'
	    }
		output+='<textarea id="detailReplytext" rows="5" cols="82">' + r_text +'</textarea><p>'
			output+='<button type="button" class="btnReplyUpdate" onclick="updateReply(' +rno + ')" >수정</button>'
		output+='<button type="button" class="btnReplyClose" onclick="ReplyClose()" >취소</button><p></div>'
		ReplyClose()
		$(("#comment_"+rno)+':not(:has(.ReplyModify))').append(output);
}
//**댓글 삭제화면 생성 함수
function showReplyDelete(rno){
		let deleted = $("#comment_" + rno +" > .comment > #deleted").val()
		let loginUser = $("#comment_" +rno +" > .comment > #loginUser").val()
		let mine = $("#comment_" +rno +" > comment > #mine")
		if((mine == false && loginUser == true) || deleted == "Y"){
			alert("수정할 수 없는 댓글입니다.")
			return
		}
		let output = ""
		output+= '<div class="ReplyDelete">'
		output+='댓글을 삭제하시겠습니까?<p>'
	    if(mine == false && loginUser == false){
			output+='<label for="password">패스워드</label><input name="password" type="password" id="password"><p>'
	    }
		output+='<button type="button" class="btnReplyDelete" onclick="deleteReply(' +rno + ')" >삭제</button>'
		output+='<button type="button" class="btnReplyClose" onclick="ReplyClose" onclick="ReplyClose()">취소</button><p></div>'
		ReplyClose()
		$(("#comment_"+rno)+':not(:has(.ReplyModify))').append(output);
}

function ReplyClose(){
	$('.ReplyModify').remove()
	$('.ReplyDelete').remove()
}

function deleteReply(rno){
	var param = { 'rno' : rno };
	password = $(".ReplyDelete > #password").val();
    if(password == ""){
        alert("비밀번호를 입력하세요");
        document.boardForm.writer.focus();
        return;
    }
	if(password){
    	param['password'] = password
    }
    $.ajax({
        type: "post",
        url: "${path}/board/reply/delete.do",
        data : param,
        success: function(data){
			if($.trim(data) == "false") {
				alert('댓글삭제에 실패했습니다.');
			} 
			else if($.trim(data) == "true") {
			   alert('댓글이 삭제되었습니다.');
	            listReply(1);
            }
        }
    });
}
function updateReply(rno){
    var replytext=$("#detailReplytext").val();
	password = $(".ReplyModify > #password").val();
    if(replytext == ""){
        alert("내용을 입력하세요");
        return;
    }
    if(password == ""){
        alert("비밀번호를 입력하세요");
        return;
    }
	var param = { 'rno' : rno, content: replytext };
	if(password){
    	param['password'] = password
    }
    $.ajax({
        type: "post",
        url: "${path}/board/reply/update.do",
        data : param,
        success: function(data){
			if($.trim(data) == "false") {
				alert('댓글 업데이트에 실패했습니다.');
			} 
			else if($.trim(data) == "true") {
			   alert('댓글이 업데이트되었습니다.');
	            listReply(1);
            }
        }
    });
}

Number.prototype.padLeft = function(base,chr){
	   var  len = (String(base || 10).length - String(this).length)+1;
	   return len > 0? new Array(len).join(chr || '0')+this : this;
}
	
function DateFormat(date){
    date = new Date(date);
    year = date.getFullYear();
    month = date.getMonth().padLeft();
    day = date.getDate().padLeft();
    hour = date.getHours().padLeft();
    minute = date.getMinutes().padLeft();
    second = date.getSeconds().padLeft();
    strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    return strDate;
}

</script>

<%-- 
<script type="text/javascript">
/*
function deletePost() {
	
  if (confirm('게시글을 삭제할까요?')){
	  const formHtml = `
	      <form id="deleteForm" action="/board/delete.do" method="post">
	          <input type="hidden" id="bno" name="bno" value="${dto.bno}" />
	      </form>
	  `;
	  const doc = new DOMParser().parseFromString(formHtml, 'text/html');
	  const form = doc.body.firstChild;
	  document.body.append(form);
	  document.getElementById('deleteForm').submit();
	  }
}
*/
</script>
--%>

</head>
<body>
    <h2>게시글 조회</h2>
    <div id="title">${dto.title}</div>
    <div>
    <span>${dto.writer}<c:if test="${dto.writerId != null}"><a href="/member/info.do?userId=${dto.writerId}">*</a></c:if></span>|
    <span><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span> |
    <span>${dto.viewcnt}</span>
    </div>
    <div id="content">
    	<div>${dto.content}</div>
    </div>


	<div id="replybox" style="border: thin">
		<p>
			댓글<span>&nbsp;</span><span id="replycnt">0</span>
		</p>
		<!-- **댓글 목록 출력할 위치 -->
		<div id="listReply"></div>
		<div class="pagenation" id="ReplyPages"></div>
		<form>
		<div id="replyWriteBox">
			<c:if test="${sessionScope.userId == null}">
				<div>
					<label for="ReplyWriter">이름</label> <input name="ReplyWriter" id="ReplyWriter">
				</div>
				<div>
					<label for="ReplyPassword">비밀번호</label> <input type="password" name="ReplyPassword"
						id="ReplyPassword">
				</div>
			</c:if>
			<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
			<p>
				<button type="button" id="btnReply">댓글 작성</button>
		</div>
		</form>
	</div>


	<%-- 
    <c:if test="${sessionScope.userId == dto.writerId}">
    <button type="button" id="btnUpdete">수정</button>
    <button type="button" id="btnDelete" onclick="deletePost()">삭제</button>
     <button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    </c:if>
    --%>
    <c:if test="${dto.writerId == null || (sessionScope.userId != null && sessionScope.userId == dto.writerId)}">
    <button type="button" id="btnUpdete">수정</button>
    <button type="button" id="btnDelete" onclick="deletePost()">삭제</button>
    </c:if>
    <button type="button" id="btnList">목록</button>
    <button type="button" id="btnWrite" onclick="location.href='${path}/board/write.do'">글쓰기</button>
    
    
        
</body>
</html>