package notice.model.service;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import notice.model.vo.Notice;

public class NoticeDAO {

	private Properties prop = new Properties();
	
	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public ArrayList<Notice> selectList(Connection conn) {
		
		Statement stmt = null;	
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Notice>();
			
			Notice notice = null;
			
			while(rset.next()) {
				int nNo = rset.getInt("nNo");
				String nTitle = rset.getString("nTitle");
				String nContent = rset.getString("nContent");
				String nWriter = rset.getString("nWriter");
				int nCount = rset.getInt("nCount");
				Date nDate = rset.getDate("nDate");
				
				notice = new Notice(nNo, nTitle, nContent, nWriter, nCount, nDate);
				
				list.add(notice);
			}
			/*
			 * while(rset.next()) { Notice notice = new Notice(rset.getInt("nno"),
			 * rset.getString("nTitle"), rset.getString("nContent"),
			 * rset.getString("nWriter"), rset.getInt("nCount"), rset.getDate("nDate"));
			 * nList.add(notice); }
			 */
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int insertNotice(Connection conn, Notice n) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try { 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setString(3, n.getnWriter());
			pstmt.setDate(4, n.getnDate());
			
			result = pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice NoticeDetailView(Connection conn, int nno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Notice notice = null;
		
		String query = prop.getProperty("findNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice(rset.getString("nTitle"),
									rset.getString("ncontent"),
									rset.getString("nwriter"),
									rset.getDate("ndate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

}
