package com.ming.tools.image;


import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片转换
 */
public class ImageConvert {

    public static void jpgToGif() throws IOException{
        BufferedImage src = ImageIO.read(new File("d:/1.jpg")); // 读入文件1
        BufferedImage src1 = ImageIO.read(new File("d:/2.jpg")); // 读入文件2
        BufferedImage src2 = ImageIO.read(new File("d:/3.jpg")); // 读入文件3
        AnimatedGifEncoder e = new AnimatedGifEncoder();
        e.setRepeat(0);
        e.start("d:/laoma.gif");//生成gif图片位置名称
        e.setDelay(100);
        e.addFrame(src);
        e.setDelay(1000);
        e.addFrame(src1);
        e.setDelay(100);
        e.addFrame(src2);
        e.finish();
    }

    private static void gifTojpg() throws IOException {
        GifDecoder decoder = new GifDecoder();
        InputStream is = new FileInputStream("d:/123.gif");
        if (decoder.read(is) != 0) {
            System.out.println("读取有错误");
            return;
        }
        is.close();
        System.out.println(" 帧是数量: " + decoder.getFrameCount());
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            BufferedImage frame = decoder.getFrame(i);
            int delay = decoder.getDelay(i);
            System.out.println("延迟时间: " + delay);
            OutputStream out = new FileOutputStream("d:/" + i + "bb.jpg");
            ImageIO.write(frame, "jpeg", out);// 将frame 按jpeg格式 写入out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            out.flush();
            out.close();
        }
    }
}
