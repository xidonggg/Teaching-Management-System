package com.ssh.Util.updownfile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;

import com.ssh.Util.remoteTree.RemoteTreeEntity;
import com.ssh.Util.updownfile.dao.FolderDAO;
import com.ssh.Util.updownfile.dao.UploadFileDAO;
import com.ssh.Util.updownfile.entity.Folder;
import com.ssh.Util.updownfile.entity.UploadFile;
import com.ssh.exception.BusinessException;
import com.ssh.login.dao.UserLoginDAO;

@Transactional
@Service
public class UploadFileService {
	@Autowired
	private UserLoginDAO userLoginDAO;
	@Autowired
	private UploadFileDAO uploadFileDAO;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FolderDAO folderDAO;
	
	public String tokenToPinyin(String token) {
		//---------根据token获得用户标识pinyin----------
    	String[] arr1 = token.split("_");
    	if(arr1.length != 2) {
    		throw new BusinessException("用户token错误");
    	}
    	String userloginId = arr1[0];
    	String userpinyin = userLoginDAO.getUserLoginById(userloginId).getPinyin();
    	return userpinyin;
	}
	public List<UploadFile> getFilesByFolder(String relativePath){
		return uploadFileDAO.getUploadFilesByrelativePath(relativePath);
	}
	private void createDepartmentFilePath(Folder folder) {
		String rootPath = folder.getRelativePath();
		File fileS=new File(rootPath);
        if(!fileS.exists()){
            fileS.mkdirs();
            folderDAO.addFolder(folder);
        }
	}
	
	@SuppressWarnings("unused")
	public  List<Object> uploadFile1(String relationID,String date, MultipartFile [] uploadFile,
			HttpServletRequest request,String relativePath) 
	        throws IllegalStateException, IOException{

	    List<Object> fileList=new ArrayList<>();
	    UploadFile fileEntity=null;
	    for(int i=0;i<uploadFile.length;i++){
	        String filePath= servletContext.getRealPath(relativePath);
	        System.out.println("文件存放磁盘路径："+filePath);
	        String rePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+relativePath;
	        System.out.println("取文件路径："+rePath);
	        MultipartFile file=uploadFile[i];
	        String fileName=file.getOriginalFilename();
	        String fileNameS="";
	        String fileType=fileName.split("\\.")[fileName.split("\\.").length-1];
	            fileNameS=UUID.randomUUID()+"."+fileType;
	            if(!file.isEmpty()){
	            	filePath += "\\"+fileNameS;
	            	rePath += "/"+fileNameS;
	                System.out.println("文件路径filePath："+filePath);
	                System.out.println("文件路径rePath："+rePath);
	                File fileS=new File(filePath);
	                if(!fileS.getParentFile().exists()){
	                    fileS.getParentFile().mkdirs();
	                }
	                file.transferTo(fileS);
	                

	                fileEntity=saveFileInfo(fileName,filePath,relationID,date,relativePath);
	                if(null!=fileEntity){
	                    fileEntity.setFilePath(rePath);
	                    fileList.add(fileEntity);
	                }
	                
	            }else{
	                fileList.add("文件不存在");;
	            }
	        }
	    return fileList;
	}
	
//	@SuppressWarnings("unused")
//	public  List<Object> uploadFile1(String relationID,String date, MultipartFile [] uploadFile,
//			HttpServletRequest request,String realivePath) 
//	        throws IllegalStateException, IOException{
//
//	    List<Object> fileList=new ArrayList<>();
//	    UploadFile fileEntity=null;
//	    for(int i=0;i<uploadFile.length;i++){
//	        String filePath= servletContext.getRealPath("/upload/qwe");
//	        System.out.println("文件存放磁盘路径："+filePath);
//	        String rePath=request.getScheme()+"://"+request.getServerName()+":"+
//	                request.getServerPort()+request.getContextPath()+"/upload/qwe";
//	        System.out.println("取文件路径："+rePath);
//	        MultipartFile file=uploadFile[i];
//	        String fileName=file.getOriginalFilename();
//	        String fileNameS="";
//	        String fileType=fileName.split("\\.")[fileName.split("\\.").length-1];
////	        if(StringUtil.isNull(fileName,fileType)){
//	            fileNameS=UUID.randomUUID()+"."+fileType;
//	            if(!file.isEmpty()){
//	                if(fileType.contains("jpg") || fileType.contains("png") || fileType.contains("gif")){
//	                    filePath+="\\image\\"+fileNameS;
//	                    rePath+="/image/"+fileNameS;
//	                }else{
//	                    filePath+="\\file\\"+fileNameS;
//	                    rePath+="/file/"+fileNameS;
//	                }
//	                System.out.println("文件路径filePath："+filePath);
//	                System.out.println("文件路径rePath："+rePath);
//	                File fileS=new File(filePath);
//	                if(!fileS.getParentFile().exists()){
//	                    fileS.getParentFile().mkdirs();
//	                }
//	                file.transferTo(fileS);
//	                
//	                fileEntity=saveFileInfo(fileName,filePath,relationID,date,realivePath);
//	                if(null!=fileEntity){
//	                    fileEntity.setFilePath(rePath);
//	                    fileList.add(fileEntity);
//	                }
//	                
//	            }else{
//	                fileList.add("文件不存在");;
//	            }
//	        }
//	    return fileList;
//	}
		/**
		 * 保存文件信息
		 * @param fileName
		 * @param filePath
		 */
		private UploadFile saveFileInfo(String fileName,String filePath,String relationID, String date,String relativePath) {
		    
		    UploadFile fileEntity=new UploadFile();
		    fileEntity.setFileName(fileName);
		    fileEntity.setFilePath(filePath);
		    fileEntity.setRelationID(relationID);
		    fileEntity.setUploadTime(date);
		    fileEntity.setRelativePath(relativePath);
		    uploadFileDAO.addUploadFile(fileEntity);
		    return fileEntity;
		}
		
		public int getFolderIdByPath(String path) {
			return folderDAO.getFolderByPath(path).getId();
		}
		
		public void deleteFolderByPath(String path) {
			folderDAO.deleteFolderByPath(path);
		}

		/**
		 * 获得文件夹组织树
		 * @return
		 */
		Map<Integer,List<Folder>> tempTree = new HashMap<Integer,List<Folder>>();
		public RemoteTreeEntity<Folder> getRemoteTree(String departmentPath,Folder tempfolder){
			String rootPath = tempfolder.getRelativePath();
			createDepartmentFilePath(tempfolder);
			Folder root = folderDAO.getFolderByPath(rootPath);
			if(root == null)
				throw new BusinessException("没有根目录");
			tempTree.clear();
			List<Folder> folders = folderDAO.getFoldersByPath(rootPath);
			//制作Map列表
			for(int i = 0;i < folders.size(); i++) {
				Folder folder = folders.get(i);
				if(tempTree.get(folder.getParentId()) != null){
					List<Folder> temp = tempTree.get(folder.getParentId());
					temp.add(folder);
					tempTree.put(folder.getParentId(), temp);
				}else {
					List<Folder> aa = new ArrayList<Folder>();
					aa.add(folder);
					tempTree.put(folder.getParentId(), aa);
				}
			}
			//根据Map列表制作树,根节点id为1,parentId为-1
//			Folder root = null;
			List<Folder> rootChildren = tempTree.get(root.getId());
//			for(int i = 0; i < folders.size(); i++) {
//				if(folders.get(i).getId() == rootfolder.getId()) {
//					root = folders.get(i);
//					break;
//				}
//			}
//			if(root == null)
//				throw new BusinessException("没有根目录");
			Queue<RemoteTreeEntity<Folder>> queue = new LinkedList<RemoteTreeEntity<Folder>>();
			//初始化根节点
//			queue.add(root);
			RemoteTreeEntity<Folder> folderTree = new RemoteTreeEntity<Folder>();
			folderTree.setKey(root.getRelativePath());
			if(rootChildren == null) {
				return mapListToRemoteTreeList(Arrays.asList(root)).get(0);
			}
			ArrayList<RemoteTreeEntity<Folder>> ch = mapListToRemoteTreeList(rootChildren);
			folderTree.setChildren(ch);
			folderTree.setExpanded(true);
			folderTree.setIsLeaf(false);
			folderTree.setT(root);
			folderTree.setTitle(root.getName());
			folderTree.setIcon("anticon anticon-book-o");

			queue.addAll(folderTree.getChildren());
			//填满节点的子节点
			while(queue != null && !queue.isEmpty()) {
				RemoteTreeEntity<Folder> tempFolder = queue.poll();
				if(tempTree.get(tempFolder.getT().getId()) == null)
					continue;
				else {
					ArrayList<RemoteTreeEntity<Folder>> children = mapListToRemoteTreeList(tempTree.get(tempFolder.getT().getId()));
					tempFolder.setChildren(children);
					queue.addAll(children);
				}
				
			}				
			return folderTree;
		}
		public void addFolder(Folder folder) {
			folderDAO.addFolder(folder);
		}
		
		/**
		 * List<Folder> 转为ArrayList<RemoteTreeEntity<Folder>>
		 * @param list
		 * @return
		 */
		private ArrayList<RemoteTreeEntity<Folder>> mapListToRemoteTreeList(List<Folder> list){
			ArrayList<RemoteTreeEntity<Folder>> ans = new ArrayList<RemoteTreeEntity<Folder>>();
			for(int i = 0; i < list.size(); i++) {
				Folder a = list.get(i);
				RemoteTreeEntity<Folder> folderTree = new RemoteTreeEntity<Folder>();
				folderTree.setKey(a.getRelativePath());
				folderTree.setChildren(new ArrayList<RemoteTreeEntity<Folder>>());
				folderTree.setExpanded(true);
				if(tempTree.get(a.getId())==null)
					folderTree.setIsLeaf(true);
				else
					folderTree.setIsLeaf(false);
				folderTree.setT(a);
				folderTree.setTitle(a.getName());
				folderTree.setIcon("anticon anticon-book-o");
				ans.add(folderTree);
			}
			
			return ans;	
		}
		
		
		
		
		
		
		
		
		
		
	
		
		//直接访问filePath就能下载文件，目前还未测试
		
		/**
	     * 返回下载流的二进制
	     * @param path
	     * @return
	     * @throws UnsupportedEncodingException 
	     */
	    public ResponseEntity<byte[]> getStreamByPath(String filePath,HttpServletRequest request,
	            HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
	        
	         response.setCharacterEncoding("utf-8");
	         response.setContentType( "application/x-msdownload");
	         response.addHeader("Content-Disposition","attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
	         System.out.println("fdsfdsfsdf:"+filePath);
	        URL url=null;
	        ResponseEntity<byte[]> entity=null;
	        InputStream is =null;
	        try {
//	            url=new URL(filePath);
	            
//	            System.out.println(url.toString());
//	            File file=new File(url.getPath());
	            File file=new File(filePath);
	            if(file.exists()){
	                String mimeType = URLConnection.guessContentTypeFromName(fileName);
	                if(mimeType==null){
	                    mimeType = "application/octet-stream";
	                }
	                response.setContentType(mimeType);
	                byte[] body = null;
	                is = new FileInputStream(file);
	                body = new byte[is.available()];
	                is.read(body);
	                HttpHeaders headers = new HttpHeaders();
	                headers.add("Content-Disposition", "attchement;filename="+ URLEncoder.encode(fileName, "UTF-8"));
	                HttpStatus statusCode = HttpStatus.OK;
	                headers.setContentDispositionFormData("attachment", file.getName());     
	                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	                entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            if(is!=null){
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	         return entity;
	    }
	    
	    //目前还未测试
	    
	    /**
	     * 文件删除
	     * @param path
	     * @return
	     * @throws UnsupportedEncodingException 
	     */
	    public static boolean deleteFile(String filePath,HttpServletRequest request,
	            HttpServletResponse response,String fileName) throws UnsupportedEncodingException {
	        boolean flag=false;
//	        if(StringUtil.isNull(fileName,filePath)){
	            File file=new File(filePath);
	            if(file.exists()){
	                flag=file.delete();
	            }
//	        }
	        
	        return flag;
	        
	    }
}
