package member.model.service;

import member.model.dao.MemberDAO;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

import java.sql.Connection;

public class MemberService {

	public Member loginMember(Member m) {
		//커넥션 생성 자원반환 
		Connection conn = getConnection();
		
		Member loginUser =  new MemberDAO().loginMember(m, conn);
		close(conn);
		
		return loginUser;
	}

	public int insertMember(Member member) {
		
		Connection conn = getConnection();
		
		int result = new MemberDAO().insertMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int idCheck(String userId) {
		
		Connection conn = getConnection();
		
		int result = new MemberDAO().idCheck(conn, userId);
		close(conn);
		
		return result;
		
	}

	public Member selectMember(String loginUserId) {
		
		Connection conn = getConnection();
		
		Member member = new MemberDAO().selectMember(conn, loginUserId);
		close(conn);
		
		return member;
	}

	public int updateMember(Member member) {
		
		Connection conn = getConnection();
		
		int result = new MemberDAO().updateMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	
}
