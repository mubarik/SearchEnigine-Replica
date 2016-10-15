
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.naming.spi.DirectoryManager;
public class functionCalling {
	
	 public String [] fncCalling(String corpusPath)
	    {
	        System.out.println("Start"); 
	         corpusPath="F:\\CorpusIR\\docs\\";
	        String hashMapPath="F:\\corpusIR\\HashMaps\\";
	        
	        String titelPath="F:\\corpusIR\\titel\\"; 
	//Make docs from SGML         
	       //corpusProcessing cp =new corpusProcessing();
	      // cp.MakeDocsFromSGML("reut2-000.sgm",17156,corpusPath,titelPath);
	// ONLY CALL when corpus UPDATE
	       createHashMap(corpusPath,hashMapPath);

	         
	
	         
	          return   searching(hashMapPath);
	        
	    }
	    public void createHashMap(String corpusPath,String HMPath )
	    {
	        //System.out.println(corpusPath);
	        corpusProcessing cp =new corpusProcessing();
	        
	//files contain full path of file and FILE NAME
	        String [] files=cp.countFilesAndDirectories(corpusPath);
	       
	  
	//create HASH MAPS
	        for(int i=0;i<files.length;i++)
	        {
	            StringTokenizer stk=new StringTokenizer(files[i],"@");
	// Create Directory with name HashMaps
	            cp.createHashMap(cp.countWord(cp.readFromFile(stk.nextToken())),HMPath.concat(stk.nextToken()));
	        }
	         
	         
	    
	    }
	
	    public String [] searching(String HMPath)
	    {
	    	String test[] = null;
	       /* File f=new File(HMPath);
	        String fileName[]=f.list();
	               Searching se=new Searching(HMPath);
	        
	        return se.queryProcessing(fileName,"query.txt");
	        */
	    	return test;
	    }

}
