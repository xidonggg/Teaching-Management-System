package com.ssh.Util.updownfile.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssh.Util.remoteTree.RemoteTreeEntity;
import com.ssh.Util.timeFormat.TimeFormat;
import com.ssh.Util.tokenToUserName.TokenToUser;
import com.ssh.Util.updownfile.entity.Folder;
import com.ssh.Util.updownfile.entity.UploadFile;
import com.ssh.Util.updownfile.service.UploadFileService;
import com.ssh.answer.CommonRsp;
import com.ssh.answer.Result;
import com.ssh.authorityannotation.AuthFilter;
import com.ssh.modules.course.entity.Course;

@RequestMapping(value = "/updownFile")
@Controller
public class UploadFileController {

	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private TokenToUser tokenToUser;
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/saveFolder
	 * @return
	 */
	@AuthFilter(needAuth = "修改文件权限")
	@PostMapping(value = "/saveFolder")
	@ResponseBody
	public CommonRsp<Folder> save(@RequestBody Folder folder, @RequestHeader("token")String token) {
		folder.setEatablishPerson(tokenToUser.tokenToLoginUser(token).getPinyin());
		folder.setEatablishTime(TimeFormat.stampToTime(new Date()));
		uploadFileService.addFolder(folder);
	    CommonRsp<Folder> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(folder);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/saveFolder2
	 * @return
	 */
	@AuthFilter(needAuth = "修改文件权限")
	@PostMapping(value = "/saveFolder2")
	@ResponseBody
	public CommonRsp<Folder> save2(@RequestBody Folder folder, @RequestHeader("token")String token) {
		folder.setEatablishPerson(tokenToUser.tokenToLoginUser(token).getPinyin());
		folder.setEatablishTime(TimeFormat.stampToTime(new Date()));
		String relativePath = folder.getRelativePath();
		folder.setParentId(uploadFileService.getFolderIdByPath(relativePath));
		relativePath += "/"+folder.getName();
		folder.setRelativePath(relativePath);
		folder.setIsdelete(false);
		
		uploadFileService.addFolder(folder);
	    CommonRsp<Folder> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setData(folder);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/deleteFolder
	 * @return
	 */
	@AuthFilter(needAuth = "修改文件权限")
	@PostMapping(value = "/deleteFolder")
	@ResponseBody
	public CommonRsp<String> deleteFolder(String relativePath, @RequestHeader("token")String token) {
	    CommonRsp<String> rsp = new CommonRsp<>();
	    Result r = new Result();
		if(relativePath.matches("^/upload/[0-9]+$")) {
			r.setRet(1);
			r.setMsg("根目录不能删除");
		}else {
			uploadFileService.deleteFolderByPath(relativePath);
		    rsp.setData("success");
		}
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/getFolderTree
	 * @return
	 */
	@AuthFilter(needAuth = "查看文件权限")
	@PostMapping(value = "/getFolderTree")
	@ResponseBody
	public CommonRsp<RemoteTreeEntity<Folder>>getFolderTree(String departmentPath,@RequestHeader("token")String token){
		Folder folder = new Folder();
		folder.setEatablishPerson(tokenToUser.tokenToLoginUser(token).getPinyin());
		folder.setEatablishTime(TimeFormat.stampToTime(new Date()));
		folder.setRelativePath("/upload"+departmentPath);
		folder.setParentId(1);
		folder.setIsdelete(false);
		folder.setName(departmentPath.substring(1));
		
	    CommonRsp<RemoteTreeEntity<Folder>> rsp = new CommonRsp<>();
	    Result r = new Result();
		if(departmentPath == null || departmentPath.isEmpty() ||departmentPath.equals("/null"))
		{
			rsp.setData(null);
		    rsp.setType("data");
		    rsp.setResult(r);
			return rsp;
		}
		RemoteTreeEntity<Folder> ans =uploadFileService.getRemoteTree(departmentPath,folder);
	    rsp.setData(ans);
	    rsp.setType("data");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/getFileByFolder
	 * @return
	 */
	@PostMapping(value = "/getFileByFolder")
	@ResponseBody
	public CommonRsp<UploadFile>getFileByFolder(String relativePath){
		List<UploadFile> ans =uploadFileService.getFilesByFolder(relativePath);
	    CommonRsp<UploadFile> rsp = new CommonRsp<>();
	    Result r = new Result();
	    rsp.setList(ans);
	    rsp.setType("list");
	    rsp.setResult(r);
		return rsp;
	}
	/**
	 * http://localhost:8081/springMVC-spring-hibernate/updownFile/userAddFile
	 * @return
	 */
    @RequestMapping(value="userAddFile",produces = {"application/json;charset=UTF-8"},method=RequestMethod.POST)
    @ResponseBody
    public CommonRsp<Map<String,Object>> addFile(@RequestHeader("token")String token,
    		@RequestParam(value="file",required=true) MultipartFile [] uploadFile,
    		@RequestParam(value="relativePath",required=true)String realivePath,
            HttpServletRequest request){    
    	
    	String userpinyin = uploadFileService.tokenToPinyin(token);
	    CommonRsp<Map<String,Object>> rsp = new CommonRsp<>();
    	Result r = new Result();
    	
 //       JSONObject jsonRusult=new JSONObject();
        Map<String,Object> jsonRusult = new HashMap<String,Object>();
        String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        boolean flag=false;
        List<Object> fileList=null;
        //保存表单数据
        try {
            if(null!=userpinyin){
                System.out.println("上传的用户："+userpinyin);
                flag=true;
            }
            if(flag){
                //上传附件
                fileList=uploadFileService.uploadFile1(userpinyin, date, uploadFile, request,realivePath);
            }
            if(null!=fileList && fileList.size()!=0){
//                rStatus=new ReturnStatus("0000", "文件上传成功！");
                jsonRusult.put("fileInfo", fileList);
            }else{
//                rStatus=new ReturnStatus("0003", "文件上传失败！");
                jsonRusult.put("fileInfo", Collections.EMPTY_MAP);
            }
            
        } catch (IllegalStateException e) {
        	System.out.println(e);
        } catch (IOException e) {
           System.out.println(e);
        }

	    rsp.setType("data");
	    rsp.setResult(r);
	    rsp.setData(jsonRusult);
        return 	 rsp;
    }
    
    /**
     * 目前还有问题，下载和删除文件
     */
//    @RequestMapping(value="userDownFile")
//    @ResponseBody
//    public ResponseEntity<byte[]> getStreamByPath(@RequestHeader String filePath,HttpServletRequest request,
//            HttpServletResponse response,@RequestHeader String fileName) throws UnsupportedEncodingException {
//				return uploadFileService.getStreamByPath(filePath,request,response,fileName);
//    	
//    }
}
