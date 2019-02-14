package com.zzw.upc.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zzw.upc.api.form.ImageRequestForm;
import com.zzw.upc.api.service.ImageService;
import com.zzw.upc.base.entity.Image;
import com.zzw.upc.base.utils.FastDFSUtil;

@Controller
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Value("${fdfs.server:http://dev-my.pclady.com.cn}")
	private String fdfsServer;

	@RequestMapping("/upload.do")
	@ResponseBody
	public String upload(ImageRequestForm form, HttpServletResponse response) throws Exception {
		String returnData = null;
		MultipartFile file = form.getFile();
		String filename = file.getOriginalFilename();
		String suffix = file.getOriginalFilename().substring(filename.lastIndexOf(".") + 1);
		String[] fileInfos = FastDFSUtil.upload(file.getBytes(), suffix);
		if (fileInfos == null || fileInfos.length == 0) {
			throw new Exception();
		}
		// 记录图片
		Image entity = new Image();
		entity.setGroupName(fileInfos[0]);
		entity.setRemoteFilename(fileInfos[1]);
		entity.setRawFilename(filename);
		imageService.save(entity);
		// 返回图片信息
		JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("url", fdfsServer + "/" + fileInfos[0] + "/" + fileInfos[1]);
		if (form.getWindowName() != null && form.getWindowName()) {
			returnData = "<script>window.name='" + json.toJSONString() + "';</script>";
		} else {
			returnData = json.toJSONString();
		}
		return returnData;
	}

}
