package com.zzw.upc.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzw.upc.base.dao.ImageDAO;
import com.zzw.upc.base.entity.Image;

@Service
public class ImageService {

	@Autowired
	private ImageDAO pictureDAO;

	public void save(Image entity) {
		pictureDAO.save(entity);
	}

}
