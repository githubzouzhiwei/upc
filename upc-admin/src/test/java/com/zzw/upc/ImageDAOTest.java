package com.zzw.upc;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zzw.upc.base.dao.ImageDAO;
import com.zzw.upc.base.entity.Image;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ImageDAOTest {

	@Autowired
	private ImageDAO imageDAO;

	@Ignore
	@Test
	public void testFindById() {
		Image image = imageDAO.findById(1L);
		System.out.println(image);
	}

}
