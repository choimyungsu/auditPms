package file;

public class FileDTO {
	int fileID;
	String fileName;
	String fileRealName;
	int downloadCount;
	String filePath;
	String objectLink;
	String objectLinkPK;
	
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getObjectLink() {
		return objectLink;
	}
	public void setObjectLink(String objectLink) {
		this.objectLink = objectLink;
	}
	public String getObjectLinkPK() {
		return objectLinkPK;
	}
	public void setObjectLinkPK(String objectLinkPK) {
		this.objectLinkPK = objectLinkPK;
	}
	public FileDTO(Integer fileID, String fileName, String fileRealName, int downloadCount, String filePath, String objectLink,
			String objectLinkPK) {
		super();
		this.fileID = fileID;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
		this.filePath = filePath;
		this.objectLink = objectLink;
		this.objectLinkPK = objectLinkPK;
	}

	
}
