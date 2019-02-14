package com.zzw.upc.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzw.upc.base.dao.ImageDAO;
import com.zzw.upc.base.entity.Image;

@Service
public class ImageService {

	@Autowired
	private ImageDAO imageDAO;

	public Image findById(long id) {
		return imageDAO.findById(id);
	}

}
