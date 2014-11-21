package findIt;
import java.util.ArrayList;

/**
 * �﷨����������
 * @author SkyFire
 *
 */
class grammerAnalysis{
	/**��ȡ���ʽ��������
	 * @param expression ���ʽ
	 * @return ���ʽ�е���������
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
	 * ��ȡ�ؼ��֣���ɾ���ؼ���
	 * @param expression ���ʽ
	 * @param keyWordVec �洢�ؼ��ֵ�����
	 * @return ȥ���ؼ��ֺ������
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
	 * @param op ���������
	 * @return�Ƿ�Ϸ�
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