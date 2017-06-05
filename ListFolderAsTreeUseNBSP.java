package listFolderAsTree;

import java.io.File;  
import java.io.IOException;  
import java.util.Collection;  

import org.apache.commons.io.DirectoryWalker;  
import org.apache.commons.io.FilenameUtils;  
 
/**
 * 批量重命名文件的一个demo，依赖于apache的commons.io组件
 */
@SuppressWarnings("rawtypes")  
public class ListFolderAsTreeUseNBSP extends DirectoryWalker {
	//遍历目录深度限制，0表示不限制
	private int limitDeep = 0;
	public static void main(String[] args) throws IOException {
		File targetFolder = new File("E:\\000000000000000000000000000000000000000_ss\\java\\phpwind_v9.0.1_20141223");
		System.out.println( "代码路径：" + targetFolder.getAbsolutePath() + "<br>" );
		System.out.println( "------------------------------------------------------" + targetFolder.getName().replaceAll( "0{3,}|_{2,}", "" ) + "目录结构---begin-----------------------------<br>" );
    	long start = System.currentTimeMillis();
        new ListFolderAsTreeUseNBSP().startListFiles( targetFolder );
        long end = System.currentTimeMillis();
        System.out.println( "------------------------------------------------------" + targetFolder.getName().replaceAll( "0{3,}|_{2,}", "" ) + "目录结构---end-------------------------------<br>" );
		System.out.println( "执行时间： " + ( end - start ) + "毫秒。" );
    } 
	
    public ListFolderAsTreeUseNBSP() {
        super();  
    }  
    public ListFolderAsTreeUseNBSP(String extension,String newExtension) {
    	super();  
    	this.extension = extension;  
    	this.newExtension=newExtension;
    }  
    @SuppressWarnings("unused")
	private String extension = null;//extension表示文件的后缀
    @SuppressWarnings("unused")
	private String newExtension=null;//newExtension表示新文件的后缀
    /** 启动对某个文件夹的筛选 */  
    @SuppressWarnings("unchecked")  
    public void startListFiles(File rootDir) throws IOException {
        walk(rootDir, null);  
    }
 
    @Override
    protected void handleFile(File file, int depth, Collection results) throws IOException {  
            //调用具体业务逻辑  
    	if( file.getName().equals( ".classpath" ) ||
    		file.getName().equals( ".project" ) ){
    		return;
    	}
    	listFileNameByLevel( file, depth );
    }  
    
    @SuppressWarnings("unchecked")
	@Override
    protected void handleDirectoryStart(File directory, int depth, Collection results) throws IOException {
    	// TODO Auto-generated method stub
    	super.handleDirectoryStart(directory, depth, results);
    }
    
    @Override
    protected boolean handleDirectory(File directory, int depth, Collection results) throws IOException {
    	// TODO Auto-generated method stub
    	if( directory.getName().equals( "test-classes" ) || 
    		directory.getName().equals( ".settings" ) || 
    		directory.getName().equals( "classes" ) ){
    		return false;
    	}
    	listFileNameByLevel( directory, depth );
		return true;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    protected void handleDirectoryEnd(File directory, int depth, Collection results) throws IOException {
    	// TODO Auto-generated method stub
    	super.handleDirectoryEnd(directory, depth, results);
    }
    
    private String whiteGap( int depth ){
    	String gap = "|&nbsp;&nbsp;";
    	if( 0 == depth ){
    		gap = "|─";
    	}
    	for (int i = 0; i < depth; i++) {
    		if( i == depth - 1 ){
    			gap += "|─";
    		}else{
    			gap += "|&nbsp;&nbsp;";
    		}
		}
    	return gap;
    }
    /** 重命名文件 */  
    private void listFileNameByLevel(File file, int depth) throws IOException {
    	if( 0 == limitDeep ){
    		System.out.println( whiteGap( depth ) + file.getName() + "<br>" );
    	}else{
    		if( depth <= limitDeep ){
    			System.out.println( whiteGap( depth ) + file.getName() + "<br>" );
    		}
    	}
    }
}