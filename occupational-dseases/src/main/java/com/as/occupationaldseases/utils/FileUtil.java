package com.as.occupationaldseases.utils;

import sun.misc.BASE64Decoder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

/**
 * File操作类
 * @author Douxueliang
 *
 */
public class FileUtil {
	
	/**
	 * 获得文件
	 * @param realPath（文件路径）
	 * @return
	 */
	public static File getFileBy(String realPath) {		
		File file = new File(realPath);
		
		return file;
	}
	
	/**
	 * 获得文件
	 * @param url（文件下载链接）
	 * @param savePath（文件保存地址，路径+文件名）
	 * @return
	 */
    public static File getFileBy(String url, String savePath) {
		try {			
			URL linkHref = new URL(url);
			URLConnection urlConnection = linkHref.openConnection();
			
			InputStream inputStream = urlConnection.getInputStream();
			OutputStream outputStream = new FileOutputStream(savePath);
		
		    ioExport(inputStream, outputStream);
		    ioClose(inputStream, outputStream);	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = new File(savePath);
		
		return file;		
    }
	
	/**
	 * 获取文件扩展名
	 */
	public static String getFileSuffix(File file) {
		String fileName = file.getName();
	    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    return suffix;
	}
	
	/**
	 * 获取文件扩展名
	 */
	public static String getFileSuffix(String fileName) {
	    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    return suffix;
	}
	
    /**
     * 创建目录
     */
    public static File createFolder(File directory) {
		if (!directory.isDirectory()) {
			directory.mkdirs();
		}
		
		return directory;
    }
    
    /**
     * 创建文件
     */    
    public static File createFile(File file) {
    	if (!file.exists()) {
    		try {
    			file.createNewFile();    		
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return file;
    }
    
    /**
     * 删除文件或文件夹
     */
    public static void deleteFileOrDir(File file) {
    	if (!file.exists()) {
    		return;  
    	}
    	if (file.isFile()) {
    		file.delete();
    		return;
    	}
    	if (file.isDirectory()) {    		
        	File[] files = file.listFiles();
        	for (int i=0; i<files.length; i++) {
        		deleteFileOrDir(files[i]);  
            }
        	file.delete();    		
    	}
    }
    
	/**
	 * 写入数据
	 */
    public static void writeTxt(File file, String content) {
    	try {
    		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
    		randomAccessFile.writeBytes(content);
    		randomAccessFile.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}    	
    }
    
    /**
     * 数据流输出
     */
    public static void ioExport(InputStream inputStream, OutputStream outputStream) {
    	// 1024byte的数据缓冲
        byte[] buffer = new byte[1024];
        
        // 读取到的数据长度
        int length = 0;
        
        // 循环读取
        try {
        	while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
    }
    
	/**
	 * 关闭io
	 */
	public static void ioClose(InputStream inputStream, OutputStream outputStream) {
		if (null != inputStream) {
			try {
				inputStream.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		if (null != outputStream) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 下载文件
	 * @param httpServletResponse（HttpServletResponse对象）
	 * @param file（文件流）
	 */
	public static void doDownLoad(HttpServletResponse httpServletResponse, File file) {
		// 定义输出类型（下载）
		httpServletResponse.setContentType("application/force-download");
		// 设置字符集
		httpServletResponse.setCharacterEncoding("utf-8");
		// 定义输出文件头
		httpServletResponse.setHeader("Location", file.getName());
		httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		
		try {
			InputStream inputStream = new FileInputStream(file.getPath());
			OutputStream outputStream = httpServletResponse.getOutputStream();
			
			ioExport(inputStream, outputStream);
			ioClose(inputStream, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * @param file（文件流）
	 * @param savePath（文件保存地址，路径+文件名）
	 * @param sleepTime（线程睡眠时间，1秒=1000）
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void doUpLoad(File file, String savePath, int sleepTime) throws IOException, InterruptedException {
		try {
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = new FileOutputStream(savePath);
			
			ioExport(inputStream, outputStream);
			ioClose(inputStream, outputStream);
			
			Thread.sleep(sleepTime);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			
			throw e;
		}
	}
	
	/**
	 * 获得（源图宽度）
	 * @param image
	 * @return
	 */
	public static int getImageWidth(File image) {
		int width = 0;
		
		try {						
			InputStream inputStream = new FileInputStream(image);
			BufferedImage src = javax.imageio.ImageIO.read(inputStream);
			width = src.getWidth(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return width;
	}
	
	/**
	 * 获得（源图高度）
	 * @param image
	 * @return
	 */
	public static int getImageHeight(File image) {
		int height = 0;
		
		try {
			InputStream inputStream = new FileInputStream(image);
			BufferedImage src = javax.imageio.ImageIO.read(inputStream);
			height = src.getHeight(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return height;
	}


	/**
	 *
	 * @param uploadUrl 上传临时文件路径
	 * @param imgStr 文件的base64代码
	 * @return
	 */

	public static String getBase64(String uploadUrl,String imgStr){
		BASE64Decoder decoder = new BASE64Decoder();
		//转换前端数据
		imgStr = imgStr.replaceAll(" ", "+");
		//去除多余部分
		imgStr=imgStr.replace("data:image/png;base64,", "");
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; i++) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			File file = new File(uploadUrl);
			createFolder(file);
			String dofile = System.currentTimeMillis() + "." + "jpg";
			// 上传文件路径
			String uploadFilePath = uploadUrl + dofile;
			//写入保存成jpeg文件
			File uploadFolder = getFileBy(uploadUrl);
			createFolder(uploadFolder);
			FileOutputStream fos = new FileOutputStream (uploadFilePath);
			fos.write(b);
			fos.flush();
			fos.close();

		return uploadFilePath;

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}


	}


	/**
	 * 上传文件
	 * @param filename 临时文件
	 * @param newPath 文件新地址
	 * @param string 标志
	 * @return
	 * @throws IOException
	 */
	public static String copeFile(String filename,String newPath,String string) throws IOException {
		//获取文件名称
		File file = new File(filename);
		File file2 = new File(newPath);
		if(!file2.exists()){
			file2.mkdir();
		}
		//上传路径
		String str = newPath+string+"_"+file.getName();
		//获取文件夹名称
		String folder = file2.getName();
		//访问路径
		String iconPath = folder + "/"+ string+"_"+file.getName();
		// 1.创建流对象
		// 1.1 指定数据源
		FileInputStream fis = new FileInputStream(filename);
		// 1.2 指定目的地
		FileOutputStream fos = new FileOutputStream(str);
		// 2.读写数据
		// 2.1 定义数组
		byte[] b = new byte[1024];
		// 2.2 定义长度
		int len;
		// 2.3 循环读取
		while ((len = fis.read(b))!=-1) {
			// 2.4 写出数据
			fos.write(b, 0 , len);
		}
		// 3.关闭资源
		fos.close();
		fis.close();
		return iconPath;
	}

    /**
     *
     * @param base 上传根路径
     * @param newstr 一维码需要生成的值(条码号)
     * @param string 标志
     * @return
     */
	public static String tmhBarCode(String base,String newstr,String string, String words){
        File file2 = new File(base);
        if(!file2.exists()){
            file2.mkdir();
        }
        String folder = file2.getName();

        String format = "jpg";
        String uuid = UUID.randomUUID().toString();
        //上传路径
        String str = base+string+"_"+uuid+".jpg";
        //生成条形码
        File outputFile = new File(str);
        //访问路径
        String iconPath = folder + "/"+ outputFile.getName();
        try {
            MatrixUtil.writeToFile(MatrixUtil.toBarCodeMatrix(newstr), "jpg", outputFile, words);
            return iconPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
