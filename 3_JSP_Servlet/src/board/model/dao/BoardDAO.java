package board.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.Reply;

public class BoardDAO {

		private Properties prop = new Properties();
		
		public BoardDAO() {
			String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public int getListCount(Connection conn) {
			// select count(*) from board where status='Y' and btype=1
			Statement stmt = null;
			ResultSet rset = null;
			int result = 0;
			
			String query = prop.getProperty("getListCount");
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					result = rset.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(stmt);
			}
			
			return result;
		}

		public ArrayList<Board> selectList(Connection conn, int currentPage, int boardLimit) {
			/*SELECT RNUM, BID, BTYPE, CNAME, BTITLE, BCONTENT, NICKNAME, BCOUNT, CREATE_DATE, MODIFY_DATE, STATUS
			  FROM(SELECT ROWNUM RNUM, BID, BTYPE, CNAME, BTITLE, BCONTENT, NICKNAME, BCOUNT, CREATE_DATE, MODIFY_DATE, STATUS
			        FROM(SELECT BID, BTYPE, CNAME, BTITLE, BCONTENT, NICKNAME, BCOUNT, CREATE_DATE, B.MODIFY_DATE, B.STATUS
			                FROM BOARD B
			                  JOIN member ON (BWRITER = USER_ID)
			                  JOIN category USING(CID)
			                WHERE B.STATUS = 'Y'
			                ORDER BY BID DESC))
			  WHERE BTYPE=1 AND RNUM BETWEEN ? AND ?; */
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Board> list = null;
			
			String query = prop.getProperty("selectList");
			
			int startRow = (currentPage - 1) * boardLimit + 1;
			int endRow = startRow + boardLimit - 1;
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				list = new ArrayList<Board>();
				
				while(rset.next()) {
					Board b = new Board(rset.getInt("bid"),
										rset.getInt("btype"),
										rset.getString("cname"),
										rset.getString("btitle"),
										rset.getString("bcontent"),
										rset.getString("nickname"),
										rset.getInt("bcount"),
										rset.getDate("create_date"),
										rset.getDate("modify_date"),
										rset.getString("status"));
					list.add(b);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

		public ArrayList selectBList(Connection conn) {
			//select * from blist where btype=2
			Statement stmt = null;
			ResultSet rset = null;
			ArrayList<Board> list = null;
			
			String query = prop.getProperty("selectBList");
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				list = new ArrayList<Board>();
				
				while(rset.next()) {
					list.add(new Board(rset.getInt("bid"),
										rset.getInt("btype"),
										rset.getString("cname"),
										rset.getString("btitle"),
										rset.getString("bcontent"),
										rset.getString("nickname"),
										rset.getInt("bcount"),
										rset.getDate("create_date"),
										rset.getDate("modify_date"),
										rset.getString("status")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(stmt);
			}
			
			return list;
		}

		public ArrayList selectFList(Connection conn) {
			//섬네일에 나올 사진 
			//select * from attachment where file_level=0 and status='Y'
			Statement stmt = null;
			ResultSet rset = null;
			ArrayList<Attachment> list = null;
			
			String query = prop.getProperty("selectFList");
			
			try {
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query);
				
				list = new ArrayList<Attachment>();
				
				while(rset.next()) {
					list.add(new Attachment(rset.getInt("bid"),
											rset.getString("change_name")));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(stmt);
			}
			return list;
		}

		public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
			
			//insert into attachment values(seq_fid.nextval, seq_bid.currval, ?, ?, ?, sysdate, ?, default, default)
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertAttachment");
			
			try {
				for(int i = 0; i < fileList.size(); i++) {
					Attachment at = fileList.get(i);
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, at.getOriginName());
					pstmt.setString(2, at.getChangeName());
					pstmt.setString(3, at.getFilePath());
					pstmt.setInt(4, at.getFileLevel());
					
					result += pstmt.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int insertThBoard(Connection conn, Board b) {
			
			//insert into board values(seq_bid.nextval, 2, 10, ?, ?, ?, default, sysdate, sysdate, default)
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertThBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, b.getbTitle());
				pstmt.setString(2, b.getbContent());
				pstmt.setString(3, b.getbWriter());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
			
			return result;
		}

		public ArrayList<Attachment> selectThumbnail(Connection conn, int bid) {

			//select * from attachment where bid = ? and status='Y' order by fid
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Attachment> list = null;
			
			String query = prop.getProperty("selectThumbnail");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bid);
				
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Attachment>();
				
				while(rset.next()) {
					Attachment at = new Attachment();
					at.setfId(rset.getInt("fId"));
					at.setOriginName(rset.getString("origin_name"));
					at.setChangeName(rset.getString("change_name"));
					at.setFilePath(rset.getString("file_path"));
					at.setUploadDate(rset.getDate("upload_date"));
					
					list.add(at);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

		public int insertBoard(Connection conn, Board b) {
			//insert into board values(seq_bid.nextval, 1, ?, ?, ?, ?, default, sysdate, sysdate, default)
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(b.getCategory()));
				pstmt.setString(2, b.getbTitle());
				pstmt.setString(3, b.getbContent());
				pstmt.setString(4, b.getbWriter());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}

		public ArrayList<Reply> selectReplyList(Connection conn, int bid) {
			//select * from rlist where ref_bid=?
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Reply> list = null;
			
			String query = prop.getProperty("selectReplyList");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bid);
				
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Reply>();
				
				while(rset.next()) {
					list.add(new Reply(rset.getInt("rid"),
										rset.getString("rcontent"),
										rset.getInt("ref_bid"),
										rset.getString("nickname"),
										rset.getDate("create_date"),
										rset.getDate("modify_date"),
										rset.getString("status")));		
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

		public int insertReply(Connection conn, Reply r) {
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertReply");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, r.getrContent());
				pstmt.setInt(2, r.getRefBid());
				pstmt.setString(3, r.getrWriter());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		
		
}
