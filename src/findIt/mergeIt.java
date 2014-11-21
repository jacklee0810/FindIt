package findIt;
import java.util.ArrayList;

/**
 * 结果组合器
 * @author SkyFire
 *
 */
class mergeIt{
	/**
	 * 逻辑运算
	 * @param ls1 列表1
	 * @param ls2 列表2
	 * @param op 运算符
	 * @return 逻辑运算结果
	 */
	static ArrayList<String> merge(ArrayList<String> ls1,ArrayList<String> ls2,char op){
		ArrayList<String> tempArr=new ArrayList<String>();
		switch (op){
		case '&':
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