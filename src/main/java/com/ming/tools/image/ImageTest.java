package com.ming.tools.image;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Administrator on 2020/3/30 0030.
 */
public class ImageTest {

    public static void main(String[] args) {
        System.out.println("生成GIF开始...");
        Font font = new Font("微软雅黑", Font.BOLD, 16);//水印字体
        String srcImgPath="E:\\mine2\\tools\\src\\main\\resources\\static\\jpgs2\\"; //源图片地址
        String tarImgPath="C:\\Users\\Administrator\\Desktop\\"+System.currentTimeMillis()+".jpg"; //待存储的地址
        String gifPath="C:\\Users\\Administrator\\Desktop\\"+System.currentTimeMillis()+".gif"; //待存储的地址
        java.util.List<ImageContent> contentList = new ArrayList<ImageContent>();
        contentList.add(new ImageContent(0,29,"你是不是说晚上打游戏"));
        contentList.add(new ImageContent(30,48,"是啊 怎么了?"));
        contentList.add(new ImageContent(49,72,"打你妈游戏 十一点了不见你上线"));

        Color color = new Color(255,255,255,255);                               //水印图片色彩以及透明度
        //new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath,waterMarkContent,color,font);

        java.util.List<BufferedImage> imageList = new ArrayList<BufferedImage>();
        String waterMarkContent="";  //水印内容
        for (int i=0;i<73;i++){
            for (ImageContent content : contentList){
                if(i >= content.getStart()&& i<=content.getEnd()){
                    waterMarkContent = content.getContent();
                    break;
                }
            }
            BufferedImage image = new WaterMarkUtils().addWaterMark2(srcImgPath+i+".jpg", tarImgPath,waterMarkContent,color,font);
            imageList.add(image);
        }

        AnimatedGifEncoder e = new AnimatedGifEncoder();
        e.setRepeat(0);
        e.start(gifPath);//生成gif图片位置名称
        for (BufferedImage image : imageList){
            e.setDelay(50);
            e.addFrame(image);
        }
        e.finish();
        System.out.println("生成GIF成功！");
    }

}
