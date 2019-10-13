package pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DmgGI implements ActionListener{
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static final int BG_X = 100;
	public static final int BG_Y = 300;
	
	private static JFrame frame;
	private JPanel panel;
	
	private JLabel userLabel;
	private JLabel enemyLabel;
	private JLabel p1Label;
	private JLabel p2Label;
	private JLabel moveLabel;	
	private JLabel moveDescription;
	private JLabel result;
	
	private DamageCalc calc;
	
	private JButton submit; 				// submits all info
	
	private JComboBox<String> p1List; 		// user pokemon
	private JComboBox<String> p2List; 		// target pokemon
	
	private JComboBox<String> moveList;
	
	private String p1, p2; 					// names of the two pokemon
	private String move; 					// name of the selected move
	
	private ImageIcon userIcon; 			// user gif
	private ImageIcon enemyIcon; 			// enemy gif
	
	private Image bg;
	
	private int userX, userY;
	private int enemyX, enemyY;
	
	public DmgGI(){
		
		calc = new DamageCalc();
		
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize(){
		
		frame = new JFrame("Pokémon Damage Calculator");
		frame.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bg = new ImageIcon("grassBg.png").getImage();
		
		panel = new JPanel() {
            
			@Override
            
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 100, 300, null);
            }
            
        };
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(frame);
		
		addUIComponentToPanel();
		
		frame.setVisible(true);
		frame.setResizable(false);

	}
	
	private void addUIComponentToPanel(){
		
		p1Label = new JLabel();
		p1Label.setBounds(20, 20, 100, 20);
		p1Label.setText("Pokémon 1:");
		panel.add(p1Label);
		
		p1List = new JComboBox<>(DmgCoordinator.pokemonNames);
		p1List.setBounds(90, 20, 200, 20);
		p1List.setSelectedIndex(0);
		p1List.addActionListener(this);
		panel.add(p1List);
		
		p2Label = new JLabel();
		p2Label.setBounds(20, 50, 100, 20);
		p2Label.setText("Pokémon 2:");
		panel.add(p2Label);
		
		p2List = new JComboBox<>(DmgCoordinator.pokemonNames);
		p2List.setBounds(90, 50, 200, 20);
		p2List.setSelectedIndex(0);
		p2List.addActionListener(this);
		panel.add(p2List);

		moveLabel = new JLabel();
		moveLabel.setBounds(20, 80, 100, 20);
		moveLabel.setText("User's move:");
		panel.add(moveLabel);
		
		moveList = new JComboBox<>(DmgCoordinator.moveNames);
		moveList.setBounds(90, 80, 200, 20);
		moveList.setSelectedIndex(0);
		moveList.addActionListener(this);
		panel.add(moveList);

		submit = new JButton("Submit");
		submit.setBounds(20, 160, 100, 20);
		submit.setActionCommand("submit");
		submit.addActionListener(this);
		panel.add(submit);

		URL url = DmgGI.class.getResource("sprites/001b.gif");
		userIcon = new ImageIcon(url);

		userX = BG_X;
		userY = BG_Y+bg.getHeight(null)-userIcon.getIconHeight();
		
		userLabel = new JLabel(userIcon);
		userLabel.setOpaque(false);
		userLabel.setBounds(userX, userY, userIcon.getIconWidth(), userIcon.getIconHeight());
		panel.add(userLabel);

		url = DmgGI.class.getResource("animations_f/bulbasaur.gif");
		enemyIcon = new ImageIcon(url);
		
		enemyX = BG_X+300-enemyIcon.getIconWidth()/2;
		enemyY = BG_Y+146-enemyIcon.getIconHeight();
		
		enemyLabel = new JLabel(enemyIcon);
		enemyLabel.setOpaque(false);
		enemyLabel.setBounds(enemyX, enemyY, enemyIcon.getIconWidth(), enemyIcon.getIconHeight());
		enemyLabel.setDoubleBuffered(true);
		enemyLabel.setLayout(null);
		panel.add(enemyLabel);
		
		moveDescription = new JLabel();
		moveDescription.setBounds(20, 70, 800, 100);
		panel.add(moveDescription);
		
		result = new JLabel();
		result.setBounds(20, 200, 300, 20);
		result.setFont(Coordinator.pokeFont);
		panel.add(result);
	
		panel.setBackground(Color.white);

	}

	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("submit")){
			p1 = (String) p1List.getSelectedItem();
			userIcon = DmgCoordinator.findPokemon(p1).getBackSprite();
			userX = BG_X;
			userY = BG_Y+bg.getHeight(null)-userIcon.getIconHeight();
			userLabel.setIcon(userIcon);
			userLabel.setBounds(userX, userY, userIcon.getIconWidth(), userIcon.getIconHeight());
			
			p2 = (String) p2List.getSelectedItem();
			enemyIcon = DmgCoordinator.findPokemon(p2).getFrontSprite();
			enemyX = BG_X+300-enemyIcon.getIconWidth()/2;
			enemyY = BG_Y+146-enemyIcon.getIconHeight();
			enemyLabel.setIcon(enemyIcon);
			enemyLabel.setBounds(enemyX, enemyY, enemyIcon.getIconWidth(), enemyIcon.getIconHeight());
			move = (String) moveList.getSelectedItem();
			
			moveDescription.setText(move+": "+DmgCoordinator.findMove(move).getDescription());
			
			checkDamageCalc();
		}

	}

	private void checkDamageCalc(){
		
		if(p1!=null&&p2!=null&&move!=null){

			if(DmgCoordinator.findMove(move).isPhysical()){
				result.setText("Damage dealt: " + Double.toString(Math.round(calc.calcDamagePhys(DmgCoordinator.findPokemon(p1), DmgCoordinator.findPokemon(p2), DmgCoordinator.findMove(move))*100.0)/100.0)+"%");
			}
			else result.setText("Damage dealt: " + Double.toString(Math.round(calc.calcDamageSpec(DmgCoordinator.findPokemon(p1), DmgCoordinator.findPokemon(p2), DmgCoordinator.findMove(move))*100.0)/100.0)+"%");
	
		}
		
	}
	
}
