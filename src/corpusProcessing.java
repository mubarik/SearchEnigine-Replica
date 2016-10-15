

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
public class corpusProcessing {
	
	
	  public String [] countFilesAndDirectories(String folderPath)
	  {
	        File f=new File(folderPath);
	        File [] files =f.listFiles();
	        int totalDocs=0,totalDirectories=0;
	        
	// Count Total number of documents and directories 
	         for (int i=0;i<files.length;i++)
	        {
	            if(files[i].isFile())
	            {         
	                totalDocs++;
	            }
	 
	        }
	        System.out.println("Total Number Of Documents In Corpus:\t"+totalDocs); 
	       
	        String filespathANDname[]=new String[totalDocs];
	        
	
	        for (int i=0;i<files.length;i++)
	        {
	            if(files[i].isFile())
	            {  // Concatination Full Path AND FileName       
	               filespathANDname[i]=files[i].getAbsolutePath().concat("@").concat(files[i].getName()); 
	            }
	    
	            
	            System.out.println(filespathANDname[i]);
	        }

	        
	     return   filespathANDname;     
	    }
	  public void MakeDocsFromSGML(String path,int doccounter,String docPath,String titelpath)
	  {
	      
	     
	      
	      LinkedList<String> ll=readFromFile(path);

	      BufferedWriter bw;
	      int i=1;
	      
	      try {
	              
	          
	          while(i<ll.size())//&&doccounter<5
	          {
	//Extarct Body
	              if(ll.get(i-1).equalsIgnoreCase("datelin")&&ll.get(i).equalsIgnoreCase("bodi"))
	              {
	                  while(!(ll.get(i-1).equalsIgnoreCase("bodi")&&ll.get(i).equalsIgnoreCase("text")))
	                  {
	                     if(!ll.get(i).equalsIgnoreCase("bodi"))
	                     {
	                      bw = new BufferedWriter(new FileWriter(docPath+doccounter+".txt",true));
	                      bw.write(ll.get(i)+" ");
	                      bw.close();
	                     }
	                      i++;
	                  }
	                   doccounter++;            
	              }
	//Extarct Titel              
	              else if(ll.get(i-1).equalsIgnoreCase("text")&&ll.get(i).equalsIgnoreCase("titl"))
	              {
	                  while(!((ll.get(i-1).equalsIgnoreCase("titl")&&ll.get(i).equalsIgnoreCase("datelin"))||(ll.get(i-1).equalsIgnoreCase("titl")&&ll.get(i).equalsIgnoreCase("author"))))
	                  {
	                      if(!ll.get(i).equalsIgnoreCase("titl"))
	                      {
	                      bw = new BufferedWriter(new FileWriter(titelpath+doccounter+".txt",true));
	                      bw.write(ll.get(i)+" ");
	                      bw.close();
	                      }
	                      i++;
	                  }
	                  
	              }

	              else
	              {
	                  i++;
	              }
	          }
	      }
	      
	      catch (FileNotFoundException fnf)
	        {
	            System.out.println("File Not Found (SGML123) !!!");
	        }
	        catch (IOException ex)
	        {
	            System.out.println("File Not Write (SGML) !!!");
	        }

	      System.out.println("End of Docs Processing:");
	  
	  }
	  public LinkedList<String> readFromFile(String path)
	  {
	        char [] values = {'`','?','"','=','/',' ','.','(',')','{','}','[',']','\'','\"','\\',';',',','-',':','_','!','<','>','&','#','2'};
	        String delimiters=new String(values);
	        String str; 
	        LinkedList<String> ll=new LinkedList<String>();
	         try
	        {
	            BufferedReader br = new BufferedReader(new FileReader(path));
	            while(br.ready())
	            {
	                StringTokenizer stk=new StringTokenizer(br.readLine(),delimiters);
	                while(stk.hasMoreTokens())
	                {    
	                  str=stk.nextToken().toLowerCase();
	                 // Removing Stop Words
	                  if(str.equalsIgnoreCase("of")||str.equalsIgnoreCase("in")||str.equalsIgnoreCase("on")||str.equalsIgnoreCase("to")||str.equalsIgnoreCase("a")||str.equalsIgnoreCase("an")||str.equalsIgnoreCase("the")||str.equalsIgnoreCase("they")||str.equalsIgnoreCase("he")||str.equalsIgnoreCase("she")||str.equalsIgnoreCase("it")||str.equalsIgnoreCase("we")||str.equalsIgnoreCase("why")||str.equalsIgnoreCase("where")||str.equalsIgnoreCase("is")||str.equalsIgnoreCase("am")||str.equalsIgnoreCase("are")||str.equalsIgnoreCase("were")||str.equalsIgnoreCase("has")||str.equalsIgnoreCase("have")||str.equalsIgnoreCase("had")||str.equalsIgnoreCase("will")||str.equalsIgnoreCase("shall")||str.equalsIgnoreCase("would")||str.equalsIgnoreCase("could")||str.equalsIgnoreCase("be")||str.equalsIgnoreCase("here")||str.equalsIgnoreCase("i")||str.equalsIgnoreCase("there")||str.equalsIgnoreCase("was"))
	                 {
	                 
	                  }
	                 else
	                 {
	                     ll.add(stem(str).toLowerCase());
	                 }
	                }
	            }
	              br.close();
	        }
	       catch (FileNotFoundException fnf)
	        {
	            System.out.println("File Not Found (Read From File) !!!\t"+path);
	        }
	        catch (IOException ex)
	        {
	            System.out.println("File Not READ (Read From File) !!!");
	        }

	    return ll;
	    
	    }
	  public HashMap<String,Integer> countWord(LinkedList<String> lls)
	  {
	         int count,i=0,value=0;
	         HashMap<String, Integer> c= new HashMap<String, Integer>();
	         while(i<lls.size())
	         {
	                 if(c.containsKey(lls.get(i)))
	                 {
	                      value=c.get(lls.get(i));
	                    value=value+1;
	                      c.put(lls.get(i), value);
	                  }
	                 else
	                 {
	                      c.put(lls.get(i), 1);
	                      
	                 }
	         i++;
	         }
	         
	         
	     return c;
	    
	    }
	  public  void createHashMap(HashMap<String,Integer> hm,String fullFilePath)
	  {
	    try {
	             BufferedWriter bw = new BufferedWriter(new FileWriter(fullFilePath));
	                          
	             Set  set=hm.keySet();
	             Iterator itr=set.iterator();
//	            
	             while(itr.hasNext())
	            {
	               String token=(String)itr.next();
	               int count=hm.get(token);
	               bw.write(token+" "+count+"\n");
	             }
	            bw.close();    
	        }
	            catch (IOException ex) {
	            System.out.println("File Not Write (HASH MAP) !!!");
	            }
	    }
	  public String stem(String st)
	  {
	         Stemmer s=new Stemmer();
	        char ch[]=new char[st.length()];
	        st.getChars(0, st.length(), ch,0);
	        s.add(ch, ch.length);
	        s.stem();
	        return s.toString();
	   
	}

}
