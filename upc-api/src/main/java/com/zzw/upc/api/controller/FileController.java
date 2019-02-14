package com.zzw.upc.api.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zzw.upc.base.utils.FastDFSUtil;

@Controller
@RequestMapping("/files")
public class FileController {

	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(String fileUrl, String specifyFilename) throws Exception {
		if (fileUrl == null || "".equals(fileUrl) || fileUrl.indexOf("group") == -1) {
			throw new Exception();
		}
		if (specifyFilename == null || "".equals(specifyFilename)) {
			specifyFilename = UUID.randomUUID().toString();
		}
		String subStr = fileUrl.substring(fileUrl.indexOf("group"));
		byte[] content = FastDFSUtil.download(subStr.split("/")[0], subStr.substring(subStr.indexOf("/") + 1));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", new String(specifyFilename.getBytes("UTF-8"), "iso-8859-1")
				+ subStr.substring(subStr.lastIndexOf(".")));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
	}

	@ResponseBody
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(String fileUrl) throws Exception {
		if (fileUrl == null || "".equals(fileUrl) || fileUrl.indexOf("group") == -1) {
			throw new Exception();
		}
		String subStr = fileUrl.substring(fileUrl.indexOf("group"));
		int statusCode = FastDFSUtil.delete(subStr.split("/")[0], subStr.substring(subStr.indexOf("/") + 1));
		JSONObject json = new JSONObject();
		json.put("statusCode", statusCode);
		return json.toJSONString();
	}

}
