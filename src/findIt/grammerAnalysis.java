package findIt;
import java.util.ArrayList;

/**
 * 语法分析辅助类
 * @author SkyFire
 *
 */
class grammerAnalysis{
	/**获取表达式引号数量
	 * @param expression 表达式
	 * @return 表达式中的引号数量
	 */
	static int quotesNum(String expression){
		int brkt=0;
		for(char var:expression.toCharArray()){
			if(var=='"'){
				++brkt;
			}
		}
		return brkt;
	}
	
	
	/**
	 * 提取关键字，并删除关键字
	 * @param expression 表达式
	 * @param keyWordVec 存储关键字的容器
	 * @return 去除关键字后的序列
	 */

	static String keyWordExtraction(String expression,ArrayList<String> keyWordVec){
		//keyWordVec=new ArrayList<String>();
		keyWordVec.clear();
		StringBuilder temp=new StringBuilder();
		StringBuilder tempExp=new StringBuilder();
		boolean inFlag=false;
		for(char var:expression.toCharArray()){
			if(var=='"'){
				if(inFlag){
					inFlag=false;
					keyWordVec.add(temp.toString());
				}else{
					inFlag=true;
					temp.delete(0,temp.length());
				}
			}else{
				if(inFlag){
					temp.append(var);
				}else{
					tempExp.append(var);
				}
			}
		}
		return tempExp.toString();
	}
	
	/**
	 * 
	 * @param op 运算符序列
	 * @return是否合法
	 */
	
	static boolean opTest(String op){
		for(char var:op.toCharArray()){
			if(		var!='&'&&
					var!='|'&&
					var!='^'
					){
				return false;
			}
		}
		return true;
	}
}