package com.zzw.upc.api.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ImageRequestForm {

	@NotNull
	private MultipartFile file;
	private Boolean windowName;// 是否以window.name返回

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Boolean getWindowName() {
		return windowName;
	}

	public void setWindowName(Boolean windowName) {
		this.windowName = windowName;
	}

}
