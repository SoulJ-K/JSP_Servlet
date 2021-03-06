package board.model.vo;

import java.sql.Date;

public class Attachment {
	private int fId;  //파일에 대한 아이디
	private int bId;  //게시판 번호
	private String originName;	//파일 원본 이름
	private String changeName;	//파일 바꾼 이름
	private String filePath;	//파일 저장된 경로
	private Date uploadDate;	
	private int fileLevel;		//각 사진마다 들어가는 위치에 따른 번호 제공
	private int downloadCount;
	private String status;
	
	public Attachment() {}

	public Attachment(int bId, String changeName) {
		super();
		this.bId = bId;
		this.changeName = changeName;
	}

	public Attachment(int fId, int bId, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status) {
		super();
		this.fId = fId;
		this.bId = bId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [fId=" + fId + ", bId=" + bId + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", downloadCount=" + downloadCount + ", status=" + status + "]";
	}
	


}
