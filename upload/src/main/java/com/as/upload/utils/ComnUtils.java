/*
package com.as.upload.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;

import org.apache.sanselan.ImageReadException;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ComnUtils {

	private static ComnUtils instance = null;

	private Map<String, Object> map;

	private ComnUtils() {

	}

	public static ComnUtils getInstance() {
		if (instance == null) {
			synchronized (ComnUtils.class) {
				if (instance == null) {
					instance = new ComnUtils();
				}
			}
		}
		return instance;
	}

	*/
/**
	 * 锁对象，可以为任意对象
	 *//*

	private static Object lockObj = "lockerOrder";
	*/
/**
	 * 订单号生成计数器
	 *//*

	private static long orderNumCount = 0L;
	private static ComnUtils obj;
	*/
/**
	 * 每毫秒生成订单号数量最大值
	 *//*

	private int maxPerMSECSize = 1000;

	private String product = "Dysmsapi";

	private String domain = "dysmsapi.aliyuncs.com";
	private String accessKeyId = "LTAIZD3ubkj8hTaA";
	private String accessKeySecret = "LtItxI8yUObwM5WSqd03SHs3shuLjw";

	*/
/**
	 * 生成非重复订单号，理论上限1毫秒1000个，可扩展
	 * 
	 * @param tname
	 *            测试用
	 *//*


	public String makeOrderNum() {
		try {
			// 最终生成的订单号
			String finOrderNum = "";
			synchronized (lockObj) {
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒
				long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
				if (orderNumCount >= maxPerMSECSize) {
					orderNumCount = 0L;
				}
				// 组装订单号
				String countStr = maxPerMSECSize + orderNumCount + "";
				finOrderNum = nowLong + countStr.substring(1);
				orderNumCount++;
				System.out.println(finOrderNum + "--" + Thread.currentThread().getName());
				// Thread.sleep(1000);
				return finOrderNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 随机六位数
	public static int getRandNum() {
		int randNum = new Random().nextInt(999999);
		if (randNum < 100000) {
			randNum += 100000;
		}
		return randNum;
	}

	// 生成百家云的进入地址
	public static String getBjyUrl(Map<String, Object> pram, String host) {
		String pr = null;
		for (String key : pram.keySet()) {
			// pram.get(key)
			if (null == pr) {
				pr = key + "=" + pram.get(key);

			} else {

				pr += "&" + key + "=" + pram.get(key);
			}

		}
		try {
			URL u = new URL(pr);
			return host + "?" + u.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return host + "?" + pr;

	}

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	// 二维码生成
	public void Encode_QR_CODE(String content, String format, File file, Integer width, Integer height)
			throws IOException, WriterException, ImageReadException {
		if (null == width)
			width = 430; // 二维码图片宽度 300
		if (null == height)
			height = 430; // 二维码图片高度300
		if (null == format) {
			format = "gif";
		}
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 内容所使用字符集编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		// hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
		// hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
		hints.put(EncodeHintType.MARGIN, 1);// 设置二维码边的空度，非负数

		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, // 要编码的内容
				// 编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
				// Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
				// MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
				// 1D,UPC/EAN extension,UPC_EAN_EXTENSION
				BarcodeFormat.QR_CODE, width, // 条形码的宽度
				height, // 条形码的高度
				hints);// 生成条形码时的一些配置,此项可选
		setLogo(bitMatrix, format, file);

	}

	public Boolean sendMessage(String mobile, String code) throws Exception {
		if (null == mobile) {
			return false;
		}

		try {
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest requestMs = new SendSmsRequest();
			// 必填:待发送手机号
			requestMs.setPhoneNumbers(mobile);
			// 必填:短信签名-可在短信控制台中找到
			requestMs.setSignName("北京千禾学堂");
			// 必填:短信模板-可在短信控制台中找到 SMS_105140076
			requestMs.setTemplateCode("SMS_105605087");
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			Map<String, String> sem = new HashMap<String, String>();
			sem.put("code", code + "");

			requestMs.setTemplateParam(JSON.toJSONString(sem));
			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");

			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			requestMs.setOutId("yourOutId");
			
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(requestMs);
			map.put("code", code);
			map.put("msg", sendSmsResponse.getMessage());
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;

	}

	public void setLogo(BitMatrix matrix, String format, File loginFile) throws IOException, ImageReadException {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, (matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF));

			}
		}
		// 设置logo图标
		LogoConfig logoConfig = new LogoConfig();
		image = logoConfig.LogoMatrix(image, loginFile);

		if (!ImageIO.write(image, format, loginFile)) {
			throw new IOException("Could not write an image of format " + format + " to " + loginFile);
		} else {
			System.out.println("图片生成成功！");
		}
		// FileUnloadUtil.getInstance().delFile(loginFile.getAbsolutePath());
	}

	public Map<String, Object> initMap() {
		if (null == map) {
			map = new HashMap<String, Object>();
		} else {
			map.clear();

		}
		map.put("status", 200);
		map.put("msg", "success");
		return map;
	}

	public String getExtensionName(String filename) {
		if (filename != null && filename.length() > 0) {
			int dot = filename.lastIndexOf('.');
			if (dot > -1 && dot < filename.length() - 1) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	*/
/**
	 * @MethodName httpConverBytes
	 * @Description http路径文件内容获取
	 * 
	 * @param path
	 * @return
	 *//*

	public byte[] httpConverBytes(String path) {
		BufferedInputStream in = null;
		ByteArrayOutputStream out = null;
		URLConnection conn = null;

		try {
			URL url = new URL(path);
			conn = url.openConnection();

			in = new BufferedInputStream(conn.getInputStream());

			out = new ByteArrayOutputStream(1024);
			byte[] temp = new byte[1024];
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			byte[] content = out.toByteArray();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}*/
