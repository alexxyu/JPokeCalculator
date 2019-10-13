package pokemon;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Load {

	public Load(){
		
		final JFrame frame = new JFrame("Loading...");

	    JPanel panel2 = new JPanel ();
	    
	    frame.setSize ( 175, 65 );
	    frame.add(panel2);
	    frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
	    frame.setLocationRelativeTo ( null );

	    try {
			SwingUtilities.invokeAndWait ( new Runnable ()
			{
			    public void run ()
			    {
			        frame.setVisible ( true );
			    }
			} );
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	    final JProgressBar progressBar = new JProgressBar ( 0, 100 );
	    panel2.add ( progressBar );

	    for ( int i = 0; i < 100; i++ )
	    {
	        try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	        final int finalI = i;

	        SwingUtilities.invokeLater ( new Runnable ()
	        {
	            public void run ()
	            {
	                progressBar.setValue ( finalI );
	            }
	        } );
	    }
	    
	    frame.dispose();
	    MenuSelection.frame.dispose();

	}
	
}
