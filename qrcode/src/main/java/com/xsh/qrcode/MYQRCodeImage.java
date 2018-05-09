package com.xsh.qrcode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MYQRCodeImage implements QRCodeImage{

	BufferedImage bufferedImage;  
	  
    public MYQRCodeImage(BufferedImage bufferedImage) {//构造函数，获取解析对象  
        this.bufferedImage = bufferedImage;  
    }  
    @Override  
    public int getHeight() {  
        return bufferedImage.getHeight();  
    }  
  
    @Override  
    public int getPixel(int arg0, int arg1) {  
        return bufferedImage.getRGB(arg0, arg1);  
    }  
  
    @Override  
    public int getWidth() {  
        return bufferedImage.getWidth();  
    }  

}
