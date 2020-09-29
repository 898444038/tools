package com.ming.tools.wps.pdf;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
/**
 <repositories>
     <repository>
         <id>com.e-iceblue</id>
         <url>http://repo.e-iceblue.cn/repository/maven-public/</url>
     </repository>
 </repositories>

 <dependencies>
     <dependency>
         <groupId> e-iceblue </groupId>
         <artifactId>spire.pdf</artifactId>
         <version>3.4.2</version>
     </dependency>
 </dependencies>

 免费
 <dependencies>
     <dependency>
         <groupId> e-iceblue </groupId>
         <artifactId>spire.pdf.free</artifactId>
         <version>2.6.3</version>
     </dependency>
 </dependencies>
 http://blog.itpub.net/31499788/viewspace-2663960/
 */
public class Pdf2Html {

    public static void main(String[] args) {
        String pdf = "C:\\Users\\Administrator\\Desktop\\南京市房地产抵押合同.pdf";
        //pdf2word(pdf,"G:\\a.docx");
        pdf2html(pdf,"C:\\Users\\Administrator\\Desktop\\a.html");
    }

    //PDF 转Word
    public static void pdf2word(String in,String out){
        PdfDocument pdf = new PdfDocument(in);
        pdf.saveToFile(out, FileFormat.DOCX);
    }

    // PDF转图片
    public static void pdf2image() throws IOException {
        PdfDocument pdf = new PdfDocument("test.pdf");
        BufferedImage image;
        for(int i = 0; i< pdf.getPages().getCount();i++){
            image = pdf.saveAsImage(i);
            File file = new File( String.format("ToImage-img-%d.png", i));
            ImageIO.write(image, "PNG", file);
        }
        pdf.close();
    }

    //PDF转Html
    public static void pdf2html(String in,String out) {
        PdfDocument pdf = new PdfDocument(in);
        pdf.saveToFile(out, FileFormat.HTML);
    }

    //PDF转为单个svg
    public static void pdf2svg() {
        PdfDocument pdf = new PdfDocument("test.pdf");
        pdf.saveToFile("ToSVG.svg", FileFormat.SVG);
    }

    //多页pdf转为一个svg
    public static void pdfs2svg() {
//        PdfDocument pdf = new PdfDocument("sampe.pdf");
//        pdf.getConvertOptions().setOutputToOneSvg(true);
//        pdf.saveToFile("ToOneSvg.svg",FileFormat.SVG);
    }

    //PDF转XPS
    public static void pdf2xps() {
        PdfDocument pdf = new PdfDocument("test.pdf");
        pdf.saveToFile("ToXPS.xps", FileFormat.XPS);
    }
    /*
【示例6】PDF转PDF/A

 import com.spire.pdf.*;
 import com.spire.pdf.graphics.PdfMargins;
 import java.awt.geom.Dimension2D;

 public class PDFtoPDFA {
     public static void main(String[]args){
         //加载测试文档
         PdfDocument pdf = new PdfDocument();
         pdf.loadFromFile("test.pdf");

         //转换为Pdf_A_1_B格式
         PdfNewDocument newDoc = new PdfNewDocument();
         newDoc.setConformance(PdfConformanceLevel.Pdf_A_1_B);
         PdfPageBase page;
         for ( int i=0;i< pdf.getPages().getCount();i++) {
             page = pdf.getPages().get(i);
             Dimension2D size = page.getSize();
             PdfPageBase p = newDoc.getPages().add(size, new PdfMargins(0));
             page.createTemplate().draw(p, 0, 0);
         }

         //保存结果文件
         newDoc.save("ToPDFA.pdf");
         newDoc.close();

     }
 }

     */
}
