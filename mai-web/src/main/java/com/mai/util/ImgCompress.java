package com.mai.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

	
	/** 
	 * 图片压缩处理 
	 * @author bruce
	 */  
	public class ImgCompress {  
	    public Image img;  
	    public int width;  
	    public int height;  
	   
	    /** 
	     * 构造函数 
	     */  
	    public ImgCompress(String fileName) throws IOException {  
	        File file = new File(fileName);// 读入文件  
	        img = ImageIO.read(file);      // 构造Image对象  
	        width = img.getWidth(null);    // 得到源图宽  
	        height = img.getHeight(null);  // 得到源图长  
	    }  
	    /** 
	     * 按照宽度还是高度进行压缩 
	     * @param w int 最大宽度 
	     * @param h int 最大高度 
	     */  
	    public void resizeFix(int w, int h) throws IOException {  
	        if (width / height > w / h) {  
	            resizeByWidth(w);  
	        } else {  
	            resizeByHeight(h);  
	        }  
	    }  
	    /** 
	     * 以宽度为基准，等比例放缩图片 
	     * @param w int 新宽度 
	     */  
	    public void resizeByWidth(int w) throws IOException {  
	        int h = (int) (height * w / width);  
	        resize(w, h);  
	    }  
	    /** 
	     * 以高度为基准，等比例缩放图片 
	     * @param h int 新高度 
	     */  
	    public void resizeByHeight(int h) throws IOException {  
	        int w = (int) (width * h / height);  
	        resize(w, h);  
	    }  
	    /** 
	     * 强制压缩/放大图片到固定的大小 
	     * @param w int 新宽度 
	     * @param h int 新高度 
	     * @return 
	     */  
	    public void resize(int w, int h) throws IOException {  
	        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
	        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
	        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
	        File destFile = new File("//Users//zhangyang//Downloads//222.jpg");  
	        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流 
	        // 可以正常实现bmp、png、gif转jpg  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        encoder.encode(image); // JPEG编码  
	        out.close();  
	    }  
	
	
	  /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     * @return 
     */  
    public static byte[] resizeByte(MultipartFile file , String type) throws IOException {  
    	CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
    	Image img = ImageIO.read(f);      // 构造Image对象  
	    int w = img.getWidth(null);    // 得到源图宽  
	    int h = img.getHeight(null);  // 得到源图长  
    	byte[] b = null;
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	ImageIO.write(image, type , out);
        	b = out.toByteArray();
        } catch (IOException e) {
        	e.printStackTrace();
        }finally{
        	out.close();
        }
        	return b;
    }
    public static Map<String, String>  getWidth(MultipartFile file ) throws IOException {  
    	CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
    	Image img = ImageIO.read(f);      // 构造Image对象  
	    int w = img.getWidth(null);    // 得到源图宽  
	    int h = img.getHeight(null);  // 得到源图长  
	    Map<String,String> map = new HashMap<String, String>();
	    map.put("width", String.valueOf(w));
	    map.put("height", String.valueOf(h));
	    return map;
	    
    }
	    
	    
	    @SuppressWarnings("deprecation")  
	    public static void main(String[] args) throws Exception {  
	        System.out.println("开始：" + new Date().toLocaleString());  
	        ImgCompress imgCom = new ImgCompress("//Users//zhangyang//Downloads//22.jpg");  
	        imgCom.resizeFix(1600, 2300);  
	        System.out.println("结束：" + new Date().toLocaleString());  
	    }  
}  

