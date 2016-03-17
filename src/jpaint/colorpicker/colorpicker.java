package jpaint.colorpicker;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class colorpicker extends JFrame {

	   private JButton changeColor;
	   public static Color color = Color.lightGray;
	   private Container c;

	   public colorpicker()
	   {
	      super( "Using JColorChooser" );

	      c = getContentPane();
	      c.setLayout( new FlowLayout() );

	      changeColor = new JButton( "Change Color" );
	      changeColor.addActionListener(
	         new ActionListener() {
	            public void actionPerformed( ActionEvent e )
	            {
	               color =
	                  JColorChooser.showDialog( colorpicker.this,
	                     "Choose a color", color );

	               if ( color == null )
	                  color = Color.lightGray;

	               c.setBackground( color );
	               
	               
	               c.repaint();
	            }
	         }
	      );
	      c.add( changeColor );

	      setSize( 400, 130 );
	      show();
	   }

	   public static void main( String args[] )
	   {
	      colorpicker app = new colorpicker();

	      app.addWindowListener(
	         new WindowAdapter() {
	            public void windowClosing( WindowEvent e )
	            {
	               System.exit( 0 );
	            }
	         }
	      );
	   }
	}