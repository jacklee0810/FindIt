package findIt;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

/**
 * 筛选文件
 * @author SkyFire
 *
 */
class FindItMatch{
	/**
	 * 筛选文件
	 * @param fileList 候选文件列表
	 * @param keyWord 关键字
	 * @return 筛选出来的列表
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws XmlException 
	 * @throws OpenXML4JException 
	 * @throws InvalidFormatException 
	 */
	static ArrayList<String> getMatch(ArrayList<String> fileList,String keyWord) throws FileNotFoundException, IOException, InvalidFormatException, OpenXML4JException, XmlException{
		ArrayList<String> resultList=new ArrayList<String>();
		Pattern pattern=Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE);
		Matcher mch=null;
		String matchPDF="^.*\\.[Pp][Dd][Ff]$";
		String matchOffice="(^.*\\.[Dd][Oo][Cc]$)|(^.*\\.[Dd][Oo][Cc][Xx]$)|(^.*\\.[Pp][Pp][Tt]$)|(^.*\\.[Pp][Pp][Tt][Xx]$)|(^.*\\.[Xx][Ll][Ss]$)|(^.*\\.[Xx][Ll][Ss][Xx]$)";
		String matchTxt="^.*\\.[Tt][Xx][Tt]$";
		
		for(String fileName:fileList){
			//System.out.println(fileName);
			if(Pattern.matches(matchPDF, fileName)){
				mch=pattern.matcher(readFile.readPDF(fileName));
				if(mch.find()){
					resultList.add(fileName);
				}
			}else if(Pattern.matches(matchOffice, fileName)){
				mch=pattern.matcher(readFile.readOffice(fileName));
				if(mch.find()){
					resultList.add(fileName);
				}
			}else if(Pattern.matches(matchTxt,fileName)){
				mch=pattern.matcher(readFile.readText(fileName));
				if(mch.find()){
					resultList.add(fileName);
				}
			}
		}
		return resultList;
	}
}