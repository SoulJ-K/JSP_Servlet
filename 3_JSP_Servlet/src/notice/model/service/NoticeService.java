package notice.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDAO().selectList(conn);
		close(conn);
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDAO().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Notice NoticeDetailView(int nno) {
		Connection conn = getConnection();
		
		Notice result = new NoticeDAO().NoticeDetailView(conn, nno);
		
		close(conn);
		
		return result;
	}

}
