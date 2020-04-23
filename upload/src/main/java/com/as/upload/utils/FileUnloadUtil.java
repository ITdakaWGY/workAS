package com.as.upload.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUnloadUtil {

    private static FileUnloadUtil fu;

    private FileUnloadUtil() {

    };

    public static FileUnloadUtil getInstance() {

        if (null == fu) {
            fu = new FileUnloadUtil();
        }
        return fu;

    }

    // 配置上传参数
    public String FileUpload(MultipartFile file, String basePath, String place) throws ServletException, IOException {
        // 将文件传到服务器的绝对路径

        try {

            File files = new File(basePath + place);
            if (!files.isDirectory()) {
                files.mkdirs();
            }

            // 处理不在表单的数据
            if (!file.isEmpty()) {
                // 源文件文件名
                String orName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
                        file.getOriginalFilename().length());
                // 上传的绝对路径
                long fileName = System.currentTimeMillis();
                String filePath = basePath + place + File.separator + fileName + orName;
                File storePath = new File(filePath);
                // 保存文件到硬盘
                file.transferTo(storePath);
                String unloadPath = "/" + place + "/" + fileName + orName;
                return unloadPath;

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 配置上传参数
    public String FileUpload(List<MultipartFile> files, String basePath, String place)
            throws ServletException, IOException {
        // 将文件传到服务器的绝对路径

        try {

            File file = new File(basePath + place);
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            if (files != null && files.size() > 0) {
                // 迭代表单数据
                List<String> list = new ArrayList<String>();
                for (MultipartFile item : files) {
                    // 处理不在表单的数据
                    if (!item.isEmpty()) {
                        // 源文件文件名
                        String orName = item.getOriginalFilename().substring(
                                item.getOriginalFilename().lastIndexOf("."), item.getOriginalFilename().length());
                        // 上传的绝对路径
                        long fileName = System.currentTimeMillis();
                        String filePath = basePath + place + File.separator + fileName + orName;
                        File storePath = new File(filePath);
                        // 保存文件到硬盘
                        item.transferTo(storePath);
                        String unloadPath = "/" + place + "/" + fileName + orName;
                        list.add(unloadPath);
                    }
                }
                if(StringUtils.isEmpty(StringUtils.deleteAny(list.toString(), "[]"))) {
                    return null;
                }else {
                    return StringUtils.deleteAny(list.toString(), "[]");
                }

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /*public String RQEncodeUpload(List<MultipartFile> files, String basePath, String place, String format, int width,
                                 int height, String codeContent) throws ServletException, IOException {
        // 将文件传到服务器的绝对路径

        try {

            File file = new File(basePath + place);
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            if (files != null && files.size() > 0) {
                // 迭代表单数据
                List<String> list = new ArrayList<String>();
                for (MultipartFile item : files) {
                    // 处理不在表单的数据
                    if (!item.isEmpty()) {
                        // 源文件文件名
                        String orName = item.getOriginalFilename().substring(
                                item.getOriginalFilename().lastIndexOf("."), item.getOriginalFilename().length());
                        // 上传的绝对路径
                        long fileName = System.currentTimeMillis();
                        String filePath = basePath + place + File.separator + fileName + orName;
                        File storePath = new File(filePath);
                        // 保存文件到硬盘
                        item.transferTo(storePath);
                        String unloadPath = "/" + place + "/" + fileName + orName;
                        list.add(unloadPath);
                        // File logoFile =
                        // FileUnloadUtil.getInstance().copyFileUsingFileChannels(storePath, new File(
                        // basePath + place + File.separator + fileName+"temp" + orName));
                        ComnUtils.getInstance().Encode_QR_CODE(codeContent, "png", storePath, 500, 500);
                    }
                }

                return StringUtils.deleteAny(list.toString(), "[]");

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }*/

    /*public String RQEncodeUpload(File fileLogo, String basePath, String place, String format, int width, int height,
                                 String codeContent) throws ServletException, IOException {

        // 将文件传到服务器的绝对路径

        try {
            File dirFile = new File(basePath + place);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            String filePath = basePath + place + File.separator + System.currentTimeMillis() + "."
                    + fileLogo.getName().substring(fileLogo.getName().lastIndexOf(".") + 1);
            File file = new File(filePath);
            FileUtils.copyFile(fileLogo, file);
//            System.out.println(file.length());
//            ComnUtils.getInstance().Encode_QR_CODE(codeContent, "png", file, 500, 500);

            return place + file.getName();

        } catch (Exception e) {
            return null;
        }

    }*/

    public void delFile(String aPath) {

        File file = new File(aPath);
        if (file.isFile()) {
            file.delete();
        }
    }

    public static File copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
            return dest;
        }
    }

}