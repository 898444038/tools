package com.ming.tools.wps.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 <dependency>
     <groupId>com.itextpdf</groupId>
     <artifactId>itextpdf</artifactId>
     <version>5.5.1</version>
 </dependency>
 <dependency>
     <groupId>com.itextpdf</groupId>
     <artifactId>itext-asian</artifactId>
     <version>5.2.0</version>
 </dependency>
 */
public class PDFUtils {
    public static void main(String args[]) throws IOException, DocumentException {
//        QuestionnaireSurveyCreateDto dto = new QuestionnaireSurveyCreateDto();
//        KSchoolQuestionnaireSurvey survey = new KSchoolQuestionnaireSurvey();
//
//        System.out.println(JSON.toJSONString(dto));

//        String a = "https://www.bookcloudx.com/k12files/BH001/201810/e02b6f9059744375ad510565d4a869b5|1528537105.jpg";
//        String[] b = a.split("\\|");
//        System.out.println(b);

//        Integer a = 22;e
//        Integer b =55;
//        System.out.printf(String.valueOf(new BigDecimal(a).divide(new BigDecimal(b))));

//        String s = "fmcj\\x7fmkstxmlq\\x80o\\x81sz\\x82{y\\x87\\x93".replaceAll("\\\\x", "%");
//        System.out.println(URLDecoder.decode(s, "UTF-8"));
        test();
    }


    public static void test() throws IOException, DocumentException {
        // pdf模板
        String fileName = "D:\\test\\南京市房地产抵押合同.pdf";
        //读取pdf
        PdfReader reader = new PdfReader(fileName);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        //将要生成的目标PDF文件名称
        PdfStamper ps = new PdfStamper(reader, bos);

        //PdfContentByte under = ps.getUnderContent(1);

        //设置中文字体
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();

        fontList.add(bf);

        //取出报表模板中的所有字段
        AcroFields fields = ps.getAcroFields();

        fields.setSubstitutionFonts(fontList);

        //对表单数据进行赋值
        fillData(fields, ps, data());

        //必须要调用这个，否则文档不会生成的
        ps.setFormFlattening(true);

        ps.close();

        OutputStream fos = new FileOutputStream("D:\\test\\南京市房地产抵押合同new.pdf");

        fos.write(bos.toByteArray());

        fos.flush();

        fos.close();

        bos.close();

    }

    public static void fillData(AcroFields fields, PdfStamper ps, Map<String, String> data) throws IOException, DocumentException {
        // 为字段赋值,注意字段名称是区分大小写的
        for (String key : data.keySet()) {
            if (key.equals("image")){
                /**
                 * 添加图片
                 */
                String imgpath = data.get(key);
                int pageNo = fields.getFieldPositions(key).get(0).page;
                Rectangle signRect = fields.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                // 读图片
                Image image = Image.getInstance(imgpath);
                // 获取操作的页面
                PdfContentByte under = ps.getOverContent(pageNo);
                // 根据域的大小缩放图片
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                // 添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }else {
                String value = data.get(key);
                fields.setField(key, value);
            }
        }

    }

    public static Map<String, String> data() {

        Map<String, String> data = new HashMap<String, String>();

        data.put("a", "on");

        data.put("b", "中国光大银行南京分行个贷合同11");

        data.put("c", "BH202009107876787");

        data.put("d", "on");

        data.put("e", "人民币");
        data.put("f", "壹佰万");
        data.put("Text3", "中国光大银行南京分行");
        data.put("Test4", "於文");

        return data;

    }
}
