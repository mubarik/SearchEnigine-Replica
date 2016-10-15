
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class GuiSearchEngine extends Frame implements ActionListener{
	
	
	
	 Button yes=new Button("Tell me Reputation");
	 TextField tf=new TextField("www.google.com");
	 TextArea ta=new TextArea();
	 
	      public GuiSearchEngine()
	    {
	        
	    super("Search Engine ");
	 
    setLayout(new FlowLayout(FlowLayout.LEFT,60,30));
	    setBackground(Color.gray);
	    setLocation(50,50);
	    add(tf);
	    add(yes);
	    add(ta);
	    yes.addActionListener(this);
	 addWindowListener(new WindowAdapter() 
	        {
	           
	        public void windowClosing(WindowEvent ae) 
	            {      
	                    System.exit(0);
	            }
	        });
	      }
	    public void actionPerformed(ActionEvent ae) 
	    {
	       
	        String option=ae.getActionCommand();
	        if(option.equals("Search")) 
	        {
	          String query=tf.getText().toString();
	            try {
	                BufferedWriter bw = new BufferedWriter(new FileWriter("query.txt"));
	                bw.write(query);
	                bw.close();
	            }
	            catch (IOException ex) {
	            System.out.println("File Not Write");
	            } 
	       /* functionCalling call=new functionCalling();
	          String Result[]=call.fncCalling("");
	          int s=0;
	          s=Result.length;
	          //System.out.println(s); 
	         System.out.println("End");
	          	add(ta);
	                String rs="";
	                int i=0;
			while(Result[i] != null)
			{
				         i=i+1;
				     	System.out.println(i);
								
			}
			System.out.println(i);
			
	               
			for( int j=0;j<i-3;j++){
	System.out.println( Result[j].concat("\n"));
                    	
	                	rs+=Result[j].concat("\n");
	                }
	                
	                ta.setText(rs);
		              */
	            
	            functionCalling call=new functionCalling();
	            String Result[]=null;
	            	
	            	Result=call.fncCalling("");
	           
	            	add(ta);
	                  String rs="";
	                  int j=0;
	                  while(j<100)
	                  {
	                	  if(Result[j] == null)
	                	  {//System.out.println(j);System.out.println("null");
	                		  break;
	                		  
	                	  }
	                	  j=j+1;
	                	  
	                  }
	                  j=j-1;
	                  //System.out.println(j);System.out.println("End");
	  		for(int i=0;i<j;i++)
	                     rs+=Result[i].concat("\n");
	                  ta.setText(rs);
	          
	        }
	        
	        
	    }

}
