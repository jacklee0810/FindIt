package findIt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;

/**
 * �﷨������
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
	 * @param gui GUIʵ��
	 */
	void startAnalysis(FindItGui gui){
		/*
		 * ��ȡ�߼����ʽ�ֺ�Ŀ¼
		 */
		expression=new String(gui.getExpression());
		dir=new String(gui.getDir());
		/*
		 * ����Ŀ¼�Ƿ����
		 */
		File file =new File(dir);    
		if  (!(file.exists()&&file.isDirectory())){
			gui.errorOut(expression,dir,"Ŀ¼�����ڣ�",1);
			return;
		}
		
		/*
		 * ������ʽ�е�����
		 */
		if(grammerAnalysis.quotesNum(expression)%2==1){
			gui.errorOut(expression,dir,"�߼����ʽ��������������",1);
			return;
		}
		/*
		 * ��ȡ�ؼ��ֺ��߼�������
		 */
		op=grammerAnalysis.keyWordExtraction(expression,keyWordVec);
		/*
		 * �����߼�����������
		 */
		if(op.length()!=keyWordVec.size()){
			gui.errorOut(expression,dir,"�߼����ʽ�в�������������",1);
			return;
		}
		/*
		 * �����߼��������Ϸ���
		 */
		if(!grammerAnalysis.opTest(op)){
			gui.errorOut(expression,dir,"�߼����ʽ�в���������",1);
			return;
		}
		/*
		 * ��ȡ�ļ��б�
		 */
		gui.receMsg(expression, dir, "���ڻ�ȡ�ļ��б�");
		fileList=FindItFileList.getFileList(dir);
		/*
		 * ��ʼ���Ϊ�����ļ�
		 */
		result=new ArrayList<String>(fileList);
		/*
		 * �������
		 */
		gui.receMsg(expression, dir, "����ɸѡ�������߼�����");
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
		 * ������
		 */
		gui.resultOut(expression,dir,result);
	}
}