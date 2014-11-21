package findIt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

/**
 * 语法分析器
 * @author SkyFire
 */

class FindItAnalysis{
	private String expression;
	private String dir;
	private String op;
	private ArrayList<String> keyWordVec=new ArrayList<String>();
	private ArrayList<String> fileList;
	private ArrayList<String> result;
	/**
	 * @param gui GUI实例
	 */
	void startAnalysis(FindItGui gui){
		/*
		 * 获取逻辑表达式褐和目录
		 */
		expression=new String(gui.getExpression());
		dir=new String(gui.getDir());
		/*
		 * 测试目录是否存在
		 */
		File file =new File(dir);    
		if  (!(file.exists()&&file.isDirectory())){
			gui.errorOut(expression,dir,"目录不存在！",1);
			return;
		}
		
		/*
		 * 检验表达式中的引号
		 */
		if(grammerAnalysis.quotesNum(expression)%2==1){
			gui.errorOut(expression,dir,"逻辑表达式中引号数量有误！",1);
			return;
		}
		/*
		 * 提取关键字和逻辑操作符
		 */
		op=grammerAnalysis.keyWordExtraction(expression,keyWordVec);
		/*
		 * 测试逻辑操作符数量
		 */
		if(op.length()!=keyWordVec.size()){
			gui.errorOut(expression,dir,"逻辑表达式中操作符数量有误！",1);
			return;
		}
		/*
		 * 测试逻辑操作符合法性
		 */
		if(!grammerAnalysis.opTest(op)){
			gui.errorOut(expression,dir,"逻辑表达式中操作符有误！",1);
			return;
		}
		/*
		 * 获取文件列表
		 */
		gui.receMsg(expression, dir, "正在获取文件列表");
		fileList=FindItFileList.getFileList(dir);
		/*
		 * 初始结果为所有文件
		 */
		result=new ArrayList<String>(fileList);
		/*
		 * 结果生成
		 */
		gui.receMsg(expression, dir, "正在筛选并进行逻辑运算");
		for(int i=0;i<op.length();++i){
			try{
				result=mergeIt.merge(result, FindItMatch.getMatch(fileList,(String) keyWordVec.toArray()[i]),op.charAt(i));
			}catch(IOException e){
				gui.errorOut(expression,dir,e.toString(),0);
			}catch(OpenXML4JException e){
				gui.errorOut(expression,dir,e.toString(),0);
			}catch(XmlException e){
				gui.errorOut(expression,dir,e.toString(),0);
			}
		}
		/*
		 * 输出结果
		 */
		gui.resultOut(expression,dir,result);
	}
}