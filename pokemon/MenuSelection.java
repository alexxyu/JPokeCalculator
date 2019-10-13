package pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuSelection extends JPanel implements ActionListener{

	public static final int PANEL_WIDTH = 300;
	public static final int PANEL_HEIGHT = 110;
	
	public static JFrame frame;
	
	private JButton dmgCalc;
	private JButton IVCalc;
	private JButton captureCalc;
	
	public MenuSelection(){
		
		initialize();
	}
	
	private void initialize(){
		
		frame = new JFrame("Poketem");
		frame.setBounds(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2-PANEL_WIDTH/2, 
				java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2-PANEL_HEIGHT/2, PANEL_WIDTH, PANEL_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(this);
		frame.setLocationRelativeTo(frame);
		
		dmgCalc = new JButton("Damage Calculator");
		dmgCalc.setActionCommand("dmgCalc");
		dmgCalc.addActionListener(this);
		dmgCalc.setBounds(0, 0, PANEL_WIDTH, 30);
		add(dmgCalc);
		
		IVCalc = new JButton("IV Calculator");
		IVCalc.setActionCommand("IVCalc");
		IVCalc.addActionListener(this);
		IVCalc.setBounds(0, 100, 200, 30);
		add(IVCalc);
		
		captureCalc = new JButton("Capture Rate Calculator");
		captureCalc.setActionCommand("captureCalc");
		captureCalc.addActionListener(this);
		captureCalc.setBounds(0, 100, 200, 30);
		add(captureCalc);
		
		frame.setVisible(true);
		frame.setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("dmgCalc")){
			
			Thread t = new Thread(new Runnable() {
			    @Override
			    public void run(){
			    	new DmgCoordinator();
			    }

			});
			t.start();
		}
		
		else if(e.getActionCommand().equals("IVCalc")){
			
			Thread t = new Thread(new Runnable() {
			    @Override
			    public void run(){
			    	new IVGI();
			    }

			});
			t.start();
		}
		
		else if(e.getActionCommand().equals("captureCalc")){
			
			Thread t = new Thread(new Runnable() {
			    @Override
			    public void run(){
			    	new CaptureCalcGI();
			    }

			});
			t.start();
		}
		
	}

}
