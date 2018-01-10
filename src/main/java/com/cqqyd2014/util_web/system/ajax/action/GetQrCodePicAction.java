package com.cqqyd2014.util_web.system.ajax.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.common.action.DownloadFromServerAbstractAction;
import com.cqqyd2014.util.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;



@SuppressWarnings("serial")
@ParentPackage("bfkjs-default")
@Namespace("/common")
public class GetQrCodePicAction  extends DownloadFromServerAbstractAction {
	

	String msg;




	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	String file_name;
	

	     
	byte[] bytes;


		@Override
		public String setDownloadFileName() {
			// TODO Auto-generated method stub
			return "qrcode.png";
		}

		@Override
		public byte[] setByteDate() {
			// TODO Auto-generated method stub
						
			
			return bytes;
		}
		   @Action(value="get_qrcode", results = { @Result(name = "success", type = "stream", params = {  
		 	        "contentType", "${content_type}",  
		 	        "inputName", "inputStream", "contentDisposition",  
		 	        "attachment;filename=\"${file_name}\"", "bufferSize",  
		 	        "4096"  }) })  
		@Override
		public String execute_download() {
			// TODO Auto-generated method stub
			   
		        int width = 100; // 图像宽度  
		        int height = 100; // 图像高度  
		        String format = "png";// 图像类型  
		        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
		        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
		        BitMatrix bitMatrix=null;
				try {
					bitMatrix = new MultiFormatWriter().encode(msg,  
					        BarcodeFormat.QR_CODE, width, height, hints);
				} catch (WriterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 生成矩阵  
		        
		        BufferedImage bi=MatrixToImageWriter.toBufferedImage(bitMatrix);
		        ByteArrayOutputStream out=new ByteArrayOutputStream();
		        try {
					ImageIO.write(bi,format,out);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        bytes=out.toByteArray();
			return SUCCESS;
		}


		@Override
		public String setContentType() {
			// TODO Auto-generated method stub
			
			
			return "image/png";
		}  
}