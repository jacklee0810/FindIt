package findIt;
import java.util.ArrayList;

/**
 * ��������
 * @author SkyFire
 *
 */
class mergeIt{
	/**
	 * �߼�����
	 * @param ls1 �б�1
	 * @param ls2 �б�2
	 * @param op �����
	 * @return �߼�������
	 */
	static ArrayList<String> merge(ArrayList<String> ls1,ArrayList<String> ls2,char op){
		ArrayList<String> tempArr=new ArrayList<String>();
		switch (op){
		case '&':
			/*
			 * ������ͬ�ģ�����
			 */
			tempArr=new ArrayList<String>();
			outer:
			for(String t1:ls1){
				for(String t2:ls2){
					if(t1.equals(t2)){
						tempArr.add(t1);
						continue outer;
					}
				}
			}
			break;
		case '|':
			/*
			 * �����һ���б�Ȼ����ҵ�һ���б���û�еģ�����
			 */
			tempArr=new ArrayList<String>(ls1);
			outer:
			for(String t2:ls2){
				for(String t1:ls1){
					if(t1.equals(t2)){
						continue outer;
					}
				}
				tempArr.add(t2);
			}
			break;
		case '^':
			/*
			 * ���ҵڶ����б���û�еģ�����
			 */
			tempArr=new ArrayList<String>();
			outer:
			for(String t1:ls1){
				for(String t2:ls2){
					if(t1.equals(t2)){
						continue outer;
					}
				}
				tempArr.add(t1);
			}
			break;
		}
		return tempArr;
	}
}