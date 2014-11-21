package findIt;
import java.io.File;
import java.util.ArrayList;
/**
 * 获取目录列表
 * @author SkyFire
 *
 */

class FindItFileList{
	/**
	 * 获取目录列表
	 * @param dir 目录
	 * @return 所有文件列表
	 */
	static ArrayList<String> getFileList(String dir){
		ArrayList<String> fileList=new ArrayList<String>();
		File file=new File(dir);
		if (file.isDirectory()) {  
			File[] dirFile = file.listFiles();  
	        for (File f : dirFile) {  
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