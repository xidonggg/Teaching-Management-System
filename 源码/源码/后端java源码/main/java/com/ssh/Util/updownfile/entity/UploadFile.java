package com.ssh.Util.updownfile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="updownload_file")
public class UploadFile {
	private String id;
	/**
	 *文件名称
	 */
	private String fileName;
	/**
	 * 文件下载路径
	 */
	private String filePath;
	/**
	 * 相关联的id，这里关联到UserLogin表的pinyin
	 */
	private String relationID;
	/**
	 * 上传时间
	 */
	private String uploadTime;
	/**
	 * 文件相对路径"/upload/部门id/文件夹1名称/文件夹1.1名称"
	 */
	private String relativePath;
	
	public UploadFile() {
		super();
	}
	public UploadFile(String id, String fileName, String filePath, String relationID, String uploadTime) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.filePath = filePath;
		this.relationID = relationID;
		this.uploadTime = uploadTime;
	}
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "fileName", nullable = false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name = "filePath", nullable = false)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name = "relationID")
	public String getRelationID() {
		return relationID;
	}
	public void setRelationID(String relationID) {
		this.relationID = relationID;
	}
	@Column(name = "uploadTime")
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name = "relativePath")
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

}
