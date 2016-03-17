package jpaint.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import jpaint.colorpicker.colorpicker;


public class JPaint extends JPanel implements MouseListener {

	/**
	 * 
	 *  For Some Reason This Works Perfectly On MacIntosh OS Beta 10.10 
	 * 	Even The Color Picking System Will Not Delete The Graphics System Lines.
	 * 
	 * 	Java version "1.8.0_05"
	 *	Java(TM) SE Runtime Environment (build 1.8.0_05-b13)
	 *	Java HotSpot(TM) 64-Bit Server VM (build 25.5-b02, mixed mode)
	 * 
	 **/
	
	 static JRadioButton ovalcheck = new JRadioButton("Oval");
	 static JRadioButton rectcheck = new JRadioButton("Rectangle");
	 static JRadioButton linecheck = new JRadioButton("Line");
	 static JRadioButton multilinecheck = new JRadioButton("Multi-Line");
	 static int linecount = 0;
	 static int multilinecount = 0;
	 static int ovalcount = 0;
	 static int x1 , y1, x2, y2;
	 static int multix1 , multiy1;
	 static int ovalx1 , ovaly1;
	 static Color color = colorpicker.color;

	 
    public static void main(String[] args) {
        JFrame window = new JFrame("JPaint");
        JPaint content = new JPaint();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(0, 0);
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        window.setSize(width, height - 40);
        window.setVisible(true);
        window.setLayout(null);
        
     // add to a container
        window.add(ovalcheck);
        ovalcheck.setBounds(20, 20, 120, 30);
      
        window.add(rectcheck);
        rectcheck.setBounds(150, 20, 120, 30);
        
        window.add(linecheck);
        linecheck.setBounds(280, 20, 120, 30);
        
        window.add(multilinecheck);
        multilinecheck.setBounds(410, 20, 120, 30);
        
     // set state
        ovalcheck.setSelected(false);
        rectcheck.setSelected(true);
        linecheck.setSelected(false);
        multilinecheck.setSelected(false);
      
        ButtonGroup group = new ButtonGroup();
        
        group.add(ovalcheck);
        group.add(rectcheck);
        group.add(linecheck);
        group.add(multilinecheck);

        
        ovalcheck.setToolTipText("If enabled, ovals of random color and sizes are generated on left click.");
        rectcheck.setToolTipText("If enabled, rectangles of random color and sizes are generated on left click.");
        linecheck.setToolTipText("If enabled, a line of a random color will be drawn between two left click points.");
        multilinecheck.setToolTipText("If enabled, a line of a random color will be drawn between the last left click point and another left click point.");

        
     // check state
       

     
    }
   

   public JPaint() {
       
	   int R = (int)(Math.random()*256);
	   int G = (int)(Math.random()*256);
	   int B= (int)(Math.random()*256);
	   Color colorbg = new Color(R, G, B);
	   
      setBackground(colorbg);		/** Sets The Background Of The JFrame **/
      addMouseListener(this);			/** Adds The Mouse Listener To The Frame **/ 
   }
   

  
   public void mousePressed(MouseEvent evt) {
      
      if ( evt.isShiftDown() ) { /** Tells If Shift Is Down Clear The Screen  **/
    	 
    	  int R = (int)(Math.random()*256);
    	  int G = (int)(Math.random()*256);
    	  int B= (int)(Math.random()*256);
    	  Color colorbg = new Color(R, G, B);
    	  
    	  this.setBackground(colorbg);
    	  
    	  repaint();
         return;
      }
      
      int x = evt.getX(); 
      int y = evt.getY();
      
      Graphics g = getGraphics(); 

      
      int width = (int)(Math.random()*200);
      int height = (int)(Math.random()*200);

      
      if ( evt.isMetaDown() ) {
    	
    	  color =
                  JColorChooser.showDialog( JPaint.this,
                     "Choose a color", color );
        	  
      } else {
        	  
    	  	if (rectcheck.isSelected()) {
    	  		g.setColor(color);		/** Sets The Color Of The Shape **/
        	    g.fillRect( x - (width / 2), y - (height / 2), width, height ); 		/** Sets The Position Centered On The Mouse **/
        	 } 
        	 if (ovalcheck.isSelected()) {
        		 
        		 if (ovalcount == 1){
        			 g.setColor(color);  
        			 g.fillOval(ovalx1, ovaly1, x, y);
   		  		
     			  ovalcount = 0;
     		  	}//end of else if
     		  	else {
     		  		ovalx1 = x;
     		  		ovaly1 = y;
     		  		
     		  		ovalcount = ovalcount + 1;
     		  	}	
     		  }	//end of linecheck condition  
        		 
        		 
        	 
        	 if (linecheck.isSelected()) {

        		  	if (linecount == 1){
      		  			g.setColor(color);  
      		  			g.drawLine( x1, y1, x, y);
      		  		
        			  linecount = 0;
        		  	}//end of else if
        		  	else {
        		  		x1 = x;
        		  		y1 = y;
        		  		
        		  		linecount = linecount + 1;
        		  	}	
        		  }	else if(!linecheck.isSelected()) { 	 /** Checks If The Line System Is Not Selected And Sets The Value Of Line Count To Zero (0) **/
        			  
        			  linecount = 0;
        			  
        		  }
      
        	 if (multilinecheck.isSelected()) {

      		  	if (multilinecount >= 1){
    		  			g.setColor(color);  
    		  			g.drawLine( multix1, multiy1, x, y);
    		  		
    		  			multix1 = x;
    		  			multiy1 = y;
    		  		
      		  	}//end of else if
      		  	else {
      		  		multix1 = x;
      		  		multiy1 = y;
      		  		
      		  		multilinecount = multilinecount + 1;
      		  	}	
 		  	 }
        	 else if(!multilinecheck.isSelected()) { /** Checks If The MultiLine System Is Not Selected And Sets The Value Of MultiLine Count To Zero (0) **/
   			  
   			  multilinecount = 0;
   			  
   		  }
      
      }
      
      g.dispose(); 
      
   } 
   
   
   public void mouseEntered(MouseEvent evt) { }
   public void mouseExited(MouseEvent evt) { }
   public void mouseClicked(MouseEvent evt) { }
   public void mouseReleased(MouseEvent evt) { }
   public void mouseDraged(MouseEvent evt) { }
   
}