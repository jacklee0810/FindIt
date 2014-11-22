package findIt;
import java.io.File;
import java.util.ArrayList;
/**
 * ��ȡĿ¼�б�
 * @author SkyFire
 *
 */

class FindItFileList{
	/**
	 * ��ȡĿ¼�б�
	 * @param dir Ŀ¼
	 * @return �����ļ��б�
	 */
	static ArrayList<String> getFileList(String dir){
		ArrayList<String> fileList=new ArrayList<String>();
		File file=new File(dir);
		if (file.isDirectory()) {  
			File[] dirFile = file.listFiles();  
	        for (File f : dirFile) {
	        	/*
	        	 * �����Ŀ¼����ݹ��ȡ�����뵱ǰ�б�
	        	 */
	            if (f.isDirectory()) {
	                fileList.addAll(getFileList(f.getAbsolutePath()));  
	            } else {  
	                fileList.add(f.getAbsolutePath());
	            }  
	        }
	    }
		return fileList;
	}
}