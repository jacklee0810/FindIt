package findIt;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;     
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

/**
 * 读文件
 * @author SkyFire
 *
 */
class readFile{
	/**
	 * 读取pdf
	 * @param fileName 文件名
	 * @return 文件内容
	 * @throws IOException 输入输出异常
	 */
	static String readPDF(String fileName) throws IOException{
		/*
		String tempStr=null;
		FileInputStream fin=new FileInputStream(fileName);
		PDFParser p=new PDFParser(fin);
		p.parse();
		PDFTextStripper ts=new PDFTextStripper();
		tempStr=ts.getText(p.getPDDocument());
		p.clearResources();
		fin.close();
		return tempStr;
		*/
		FileInputStream is = new FileInputStream(fileName);
		PDFTextStripper stripper = new PDFTextStripper();
		PDDocument  pdfDocument = PDDocument.load(is);
		StringWriter writer = new StringWriter();
		stripper.writeText(pdfDocument, writer);
		return writer.getBuffer().toString();
	}
	
	/**
	 * 
	 * @param fileName 文件名
	 * @return 文件内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws OpenXML4JException
	 * @throws XmlException
	 */
	static String readOffice(String fileName) throws  IOException, OpenXML4JException, XmlException {
		File inputFile = new File(fileName);  
		//File inputFile = new File("D:\\test.docx");
		//File inputFile = new File("D:\\test.pptx");   
		//File inputFile = new File("D:\\test.xlsx");   
		//File inputFile = new File("D:\\test.xls");   
		//File inputFile = new File("D:\\test.doc");   
		//File inputFile = new File("D:\\test.ppt");   
		POITextExtractor extractor = ExtractorFactory.createExtractor(inputFile);
		String str= extractor.getText();
		extractor.close();
		return str;
	} 
	
	/**
	 * 
	 * @param fileName 文件名
	 * @return 文件内容
	 * @throws IOException
	 */
	static String readText(String fileName) throws IOException { 
		StringBuilder strBd=new StringBuilder();
		Reader reader=null;
        char[] tempchars = new char[4096];
        int charread = 0;
        reader = new InputStreamReader(new FileInputStream(fileName));
        while ((charread = reader.read(tempchars)) != -1) {
            if ((charread == tempchars.length)
                    && (tempchars[tempchars.length - 1] != '\r')) {
                strBd.append(tempchars);
            } else {
                for (int i = 0; i < charread; i++) {
                    if (tempchars[i] == '\r') {
                        continue;
                    } else {
                    	strBd.append(tempchars[i]);
                    }
                }
            }
        }
        reader.close();
		return strBd.toString();
	} 
}

