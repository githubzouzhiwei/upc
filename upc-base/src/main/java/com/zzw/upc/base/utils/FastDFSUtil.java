package com.zzw.upc.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSUtil {
	private static TrackerClient trackerClient;
	private static TrackerServer trackerServer;
	private static StorageServer storageServer;
	private static StorageClient storageClient;
	static {
		try {
			Properties props = new Properties();
			InputStream is = FastDFSUtil.class.getClassLoader().getResourceAsStream("fdfs_client.properties");
			props.load(is);
			ClientGlobal.initByProperties(props);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param bytes
	 *            文件字节
	 * @param suffix
	 *            文件后缀
	 * @return 文件信息
	 */
	public static String[] upload(byte[] bytes, String suffix) {
		try {
			String[] fileInfos = storageClient.upload_file(bytes, suffix, null);
			return fileInfos;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载文件
	 * 
	 * @param groupName
	 *            组名
	 * @param remoteFilename
	 *            文件名
	 * @return
	 */
	public static byte[] download(String groupName, String remoteFilename) {
		byte[] content = null;
		try {
			content = storageClient.download_file(groupName, remoteFilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 删除文件
	 * 
	 * @param groupName
	 *            组名
	 * @param remoteFilename
	 *            文件名
	 * @return 0则成功, 不为0则失败
	 * 
	 */
	public static int delete(String groupName, String remoteFilename) {
		int statusCode = -1;
		try {
			statusCode = storageClient.delete_file(groupName, remoteFilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusCode;
	}
}
