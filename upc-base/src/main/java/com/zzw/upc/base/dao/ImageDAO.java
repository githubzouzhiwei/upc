package com.zzw.upc.base.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zzw.upc.base.entity.Image;

@Mapper
public interface ImageDAO {

	@Select("select * from m_image where id=#{id}")
	Image findById(@Param("id") Long id);

	@Insert("insert into m_image(group_name, remote_filename, raw_filename) values(#{groupName}, #{remoteFilename}, #{rawFilename})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void save(Image image);

}
