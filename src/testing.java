import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class testing {
	
    String HashMapPath="";

    public testing() {
        
    }
    
     public testing(String path) {
      HashMapPath=path;  
    }
    public String [] queryProcessing(String[] docNames,String queryFile)
    {

    LinkedList<String> llqueryTokens=new LinkedList<String>();
     int i,j;
     corpusProcessing cp =new corpusProcessing();
     HashMap<String,Integer> hmquery=countWord(cp.readFromFile(queryFile));
     Set  set=hmquery.keySet();
     Iterator itr=set.iterator();
            while(itr.hasNext())
            {
                String token=(String) itr.next();
               llqueryTokens.add(token);
            }
            
       LinkedList<Integer> llNoofDocsThatContainTerm=new LinkedList<Integer>();
       docmentFrequency(llqueryTokens,docNames,llNoofDocsThatContainTerm);

       //All Doucments Weight Count
       //All Doucments Weight Count
        int totalNoOfDocs;
        double IDF=0.0,weight=0.0,TFi=0.0,docf=0.0,doclog=0.0;
        Double[][] weightofterms=new Double[llqueryTokens.size()][docNames.length];
        Double[][] weightofQuery=new Double[llqueryTokens.size()][1];
        totalNoOfDocs=docNames.length;
       

         for(j=0;j<llqueryTokens.size();j++)
         {
            for(i=0;i<docNames.length;i++)  
            {  
                if(FindWordInFile(docNames[i],llqueryTokens.get(j)))
                { 
                    docf=llNoofDocsThatContainTerm.get(j);
                    doclog=totalNoOfDocs/docf;
                    IDF=Math.log10(doclog);
                    TFi=TermFrequencyInDocument(docNames[i],llqueryTokens.get(j));
                    weight=IDF*TFi;
                    weightofterms[j][i]=weight;
                 }
                else
                {
                    double w=0.0;
                    weightofterms[j][i]=w;
                }
            
            }
            
         }
        //Query Weight Count//////////////////////////////////////////////////////
        //Query Weight Count//////////////////////////////////////////////////////
        for(j=0;j<llqueryTokens.size();j++)
         {          if(llNoofDocsThatContainTerm.get(j)>0)
                    {
                    docf=llNoofDocsThatContainTerm.get(j);
                    doclog=totalNoOfDocs/docf;
                    IDF=Math.log10(doclog);
                    TFi=hmquery.get(llqueryTokens.get(j));        
                    weight=IDF*TFi;
                    weightofQuery[j][0]=weight;
                    }
                    else
                    {
                        weightofQuery[j][0]=0.0;
                        String msg="\""+llqueryTokens.get(j).toUpperCase()+"\" not Found in any Document";
                         JOptionPane.showMessageDialog(null,msg);
                    }
                        
         }
       
       
        
        return CosineSimiliraties(llqueryTokens.size(),weightofterms,weightofQuery,docNames);
    }
      public void docmentFrequency(LinkedList<String> llqueryTokens,String [] doclist,LinkedList<Integer>llNoofDocsThatContainTerm)
    {
        int docCount=0;
        
        for(int j=0;j<llqueryTokens.size();j++)
         {  docCount=0;
            for(int i=0;i<doclist.length;i++)
            {
                if(FindWordInFile(doclist[i],llqueryTokens.get(j)))
                {
                    docCount=docCount+1;
                }
             }
            llNoofDocsThatContainTerm.add(docCount);
         
        }        
        
    }
    public Boolean FindWordInFile(String fileName,String term)
    {
        StringTokenizer stk;
        Boolean found=false;
         try {
             BufferedReader br = new BufferedReader(new FileReader(HashMapPath+fileName));
             while(br.ready())
            {                
              stk=new StringTokenizer(br.readLine()," ");
              if(stk.nextToken().equalsIgnoreCase(term))
              {
              found=true;
              break;
              }
              
             }
           br.close();    
        }
         catch(FileNotFoundException fnf)
          
     {
         
        System.out.println("File Not Found (FindWordInFile)");
     }
            catch (IOException ex) {
            System.out.println("File Not Read (FindWordInFile)");
            }   
        return found;
        
    }
    public HashMap<String,Integer> countWord(LinkedList<String> ll)
    {
         int count,i=0,value=0;
         LinkedList<String> lls=new LinkedList<String>();
         corpusProcessing cp =new corpusProcessing();
        for(i=0;i<ll.size();i++)
          {
          lls.add(cp.stem(ll.get(i))); 
          }
         ll.clear();
//         
         HashMap<String, Integer> c= new HashMap<String, Integer>();
          
         i=0;
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
     public int TermFrequencyInDocument(String fileName,String userWord)
    {
     int v=0,c=0;        
        try {
             BufferedReader br = new BufferedReader(new FileReader(HashMapPath+fileName));
             while(br.ready())
            {                
              StringTokenizer stk=new StringTokenizer(br.readLine()," ");
              String fileword=stk.nextToken();
              String token=stk.nextToken();
      
              if(userWord.equalsIgnoreCase(fileword))
              {
                      char[] ch=token.toCharArray();
                      for(int i=0;i<ch.length;i++)
                      {
                         if(i==0)
                         {
                             if(ch[i]==48){v=0;}if(ch[i]==49){v=1;}if(ch[i]==50){v=2;}if(ch[i]==51){v=3;}if(ch[i]==52){v=4;}if(ch[i]==53){v=5;}
                             if(ch[i]==54){v=6;}if(ch[i]==55){v=7;}if(ch[i]==56){v=8;}if(ch[i]==57){v=9;}
                         }
                         else
                         {
                            if(ch[i]==48){c=0;}if(ch[i]==49){c=1;}if(ch[i]==50){c=2;}if(ch[i]==51){c=3;}if(ch[i]==52){c=4;}if(ch[i]==53){c=5;}
                             if(ch[i]==54){c=6;}if(ch[i]==55){c=7;}if(ch[i]==56){c=8;}if(ch[i]==57){c=9;}

                             v= ((v * 10) + c);

                         }
               
                       }//for
              break;
             } //if
           
        }//while
              br.close();    
    }//try
     catch(FileNotFoundException fnf)
             
     {
         
        System.out.println("File Not Found (TermFrequency in docs)");
     }
        catch (IOException ex) {
            System.out.println("File Not Read (TermFrequency in docs)");
            }
    
        return v;
    }
      public String [] CosineSimiliraties(int totalWordsinQuery,Double [][] docWeight,Double [][] queryWeight,String [] docNames)
    {
        int i,j;
        Double weight=0.0;
        Double totalweight[]=new Double[docNames.length];
             for(j=0;j<docNames.length;j++)
                {
                 for(i=0;i<totalWordsinQuery;i++)  
                 {
         
                    weight+=docWeight[i][j]*queryWeight[i][0];
                }
                totalweight[j]=weight;
                weight=0.0;
            }
        
      
        String files[]=new String[docNames.length];
for(i=0;i<docNames.length;i++) 
{
    files[i]=docNames[i];

}

    double dswap;
     String sswap;
    for(i=0;i<files.length;i++)
    {
        for(j=i;j<files.length;j++)
        {
            if(totalweight[i]<totalweight[j])
            {

                dswap=totalweight[i];
                totalweight[i]=totalweight[j];
                totalweight[j]=dswap;

                sswap=files[i];
                files[i]=files[j];
                files[j]=sswap;
                }
        }
     }   
  
  j=0;
  StringBuilder sb=new StringBuilder();
  String resultFiles []=new String[100];
   sb.append("Serial No.\tFileName\tNetWeight\n");   
  if(totalweight[j]>0)
  {
      while(j<totalweight.length&&j<100)       
      {
          if(totalweight[j]>0)
          {

         
           resultFiles[j]=files[j];   
              
          }
          else
          {
              break;
          }
          j++;

      }
  }
   else
      {
        JOptionPane.showMessageDialog(null,"Sorry...\n Zero Result Found\n Try again ");
      }
 // System.out.println(sb.toString());
     return resultFiles;
 }

}
