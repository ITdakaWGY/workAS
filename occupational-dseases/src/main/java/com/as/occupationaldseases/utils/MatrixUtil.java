package com.as.occupationaldseases.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

public class MatrixUtil {

    private static final String CHARSET = "utf-8";
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /** 条形码宽度 */
    private static final int WIDTH = 137;

    /** 条形码高度 */
    private static final int HEIGHT = 38;

    /** 加文字 条形码 */
    private static final int WORDHEIGHT = 54;

    /**
     * 禁止生成实例，生成实例也没有意义。
     */
    private MatrixUtil () {
    }

    /**
     * 生成矩阵，是一个简单的函数，参数固定，更多的是使用示范。
     * @param text
     * @return
     */
    public static BitMatrix toQRCodeMatrix(String text, Integer width,
                                           Integer height) {
        if (width == null || width < 300) {
            width = 300;
        }

        if (height == null || height < 300) {
            height = 300;
        }
        // 二维码的图片格式
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text,
                    BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitMatrix;
    }

    /**
     * 将指定的字符串生成二维码图片。简单的使用示例。
     * @param text
     * @param file
     * @param format
     * @return
     */
    public boolean toQrcodeFile(String text, File file, String format, String words) {
        BitMatrix matrix = toQRCodeMatrix(text, null, null);
        if (matrix != null) {
            try {
                writeToFile(matrix, format, file, words);
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 根据点矩阵生成黑白图。
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix,String words) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        if (StringUtil.nonNullRequired(words)) {

            BufferedImage outImage = new BufferedImage(WIDTH, WORDHEIGHT, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = outImage.createGraphics();
            // 抗锯齿
            setGraphics2D(g2d);
            // 设置白色
            setColorWhite(g2d);
            // 画条形码到新的面板
            g2d.drawImage(image,0, 2, image.getWidth(), image.getHeight(), null);
            // 画文字到新的面板
            Color color=new Color(0, 0, 0);
            g2d.setColor(color);
            // 字体、字型、字号
            g2d.setFont(new Font("微软雅黑", Font.PLAIN, 9));
            //文字长度
            int strWidth = g2d.getFontMetrics().stringWidth(words);
            //总长度减去文字长度的一半  （居中显示）
            int wordStartX=(WIDTH - strWidth) / 2;
            //height + (outImage.getHeight() - height) / 2 + 12
            int wordStartY=HEIGHT+12;

            // 画文字
            g2d.drawString(words, wordStartX, wordStartY);
            g2d.dispose();
            outImage.flush();
            return outImage;
        }

        return image;

    }


    /**
     * 将字符串编成一维条码的矩阵
     * @param str

     * @return
     */
    public static BitMatrix toBarCodeMatrix(String str) {

        try {
            // 文字编码
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, CHARSET);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(str,
                    BarcodeFormat.CODE_128, WIDTH, HEIGHT, hints);

            return bitMatrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据矩阵、图片格式，生成文件。
     */
    public static void writeToFile(BitMatrix matrix, String format, File file,String words)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix,words);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /**
     * 将矩阵写入到输出流中。
     */
    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream,String words) throws IOException {
        BufferedImage image = toBufferedImage(matrix, words);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 设置 Graphics2D 属性  （抗锯齿）
     * @param g2d  Graphics2D提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
     */
    private static void setGraphics2D(Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(s);
    }

    /**
     * 设置背景为白色
     * @param g2d Graphics2D提供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制
     */
    private static void setColorWhite(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        //填充整个屏幕
        g2d.fillRect(0,0,600,600);
        //设置笔刷
        g2d.setColor(Color.BLACK);
    }

}
