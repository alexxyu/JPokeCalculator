package pokemon;

//import java.awt.Graphics;
//import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class CaptureCalcGI extends JPanel implements ActionListener{

	public static final Pokemon[] pokemons = new Pokemon[721];
	public static final String[] pokemonNames = new String[721];
	public static final String[] statuses = new String[]{"No Status", "Sleep", "Paralysis", "Freeze", "Burn"};
	public static final String[] balls = new String[]{"Poke Ball", "Great Ball", "Ultra Ball", "Master Ball", "Net Ball", 
														"Nest Ball", "Repeat Ball", "Timer Ball", "Premier Ball",
														"Luxury Ball", "Dive Ball", "Dusk Ball", "Quick Ball", "Heal Ball"};
	public static final String[] oPowers = new String[]{"No Power", "Capture Power Lv. 1", "Capture Power Lv. 2", 
														"Capture Power Lv. 3", "Capture Power S", "Capture Power MAX"};
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	private JFrame frame;
	
	// private Image bg;
	
	private JButton submit;
	
	private JComboBox<String> pList;
	private JComboBox<String> statusList;
	private JComboBox<String> ballList;
	private JComboBox<String> oPowerList;
	
	private JSlider hpBar;
	
	private JLabel selectPokemon;
	private JLabel hpSelect;
	private JLabel hp;				// label that displays hpBar selection
	private JLabel status;
	private JLabel ball;
	private JLabel oPower;
	private JLabel ballCS;			// displays pokeball condition question
	private JLabel level;
	private JLabel chance;
	
	private JTextField levelField;
	private JTextField turnField;
	
	private JTextArea infoField;
	
	private JCheckBox ballCondition;
	
	private double ballMod;
	private double statusMod;
	private double capturePowerMod;
	
	private Pokemon selectedPokemon;
	
	private double hpPercentage;
	
	public CaptureCalcGI(){
		
		initialize();
	}
	
	private void initialize(){
		
		MenuSelection.frame.dispose();
		
		// bg = new ImageIcon("pokeballs.jpg").getImage();
		
		try{
			int i=0;

			BufferedReader br = new BufferedReader(new FileReader("cRates.txt"));
			
			String line = br.readLine();
			
			while(line!=null || i>=pokemons.length){
				
				if(line==null) break;
				pokemons[i] = new Pokemon(line, "");
				pokemonNames[i] = pokemons[i].getName();
				line = br.readLine();
				
				i++;
			}
			
			br.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		addUIComponents();
				
		frame = new JFrame("Capture Rate Calculator");
		frame.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);
		frame.getContentPane().add(this);
		frame.setVisible(true);
		frame.setResizable(false);
	
	}
	
	private void addUIComponents(){
		
		selectPokemon = new JLabel("Pokemon: ");
		selectPokemon.setBounds(10, 10, 80, 20);
		add(selectPokemon);
		
		pList = new JComboBox<>(pokemonNames);
		pList.setBounds(80, 10, 200, 20);
		add(pList);
		
		level = new JLabel("Pokemon's level:");
		level.setBounds(10, 40, 100, 20);
		add(level);
		
		levelField = new JTextField();
		levelField.setBounds(100, 40, 50, 20);
		levelField.setColumns(3);
		levelField.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				check();
			}
			public void removeUpdate(DocumentEvent e) {
				//check();
			}
			public void changedUpdate(DocumentEvent e) {
				check();
			}
			
			public void check(){
				String t = levelField.getText();
				
				if(!t.matches("\\d+")){
					
					JOptionPane.showMessageDialog(null, "Please enter a valid integer!");
					Runnable r = new Runnable() {
				        @Override
				        public void run() {
				        	levelField.setText("");
				        }
				    };       
				    SwingUtilities.invokeLater(r);
					
				}
				
				else if(Integer.parseInt(t)>100||Integer.parseInt(t)<1){
					
					JOptionPane.showMessageDialog(null, "Please enter a level between 1 and 100!");
					Runnable r = new Runnable() {
				        @Override
				        public void run() {
				        	levelField.setText("");
				        }
				    };       
				    SwingUtilities.invokeLater(r);
					
				}
			}

		});
		add(levelField);
		
		hpSelect = new JLabel("Pokemon's HP:");
		hpSelect.setBounds(10, 70, 100, 20);
		add(hpSelect);
		
		hp = new JLabel("");
		hp.setBounds(300, 70, 100, 20);
		
		hpBar = new JSlider(0, 100);
		hpBar.setBounds(100, 70, 200, 20);
		hpBar.setMinorTickSpacing(5);
	    hpBar.setMajorTickSpacing(25);
	    hpBar.setPaintTicks(true);
	    hpBar.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent evt) {
	        	JSlider slider = (JSlider) evt.getSource();
	        	if (!slider.getValueIsAdjusting()) {
	        		int value = slider.getValue();
	        		hp.setText(String.valueOf(value)+"%");
	        		add(hp);
	        		
	        		repaint();
	          }
	        }
	    });
		add(hpBar);

		status = new JLabel("Status: ");
		status.setBounds(10, 100, 80, 20);
		add(status);
		
		statusList = new JComboBox<>(statuses);
		statusList.setBounds(80, 100, 200, 20);
		add(statusList);
		
		oPower = new JLabel("O-Power Boost: ");
		oPower.setBounds(10, 130, 100, 20);
		add(oPower);
		
		oPowerList = new JComboBox<>(oPowers);
		oPowerList.setBounds(90, 130, 200, 20);
		add(oPowerList);
		
		ball = new JLabel("Pokeball: ");
		ball.setBounds(10, 160, 100, 20);
		add(ball);
		
		ballCS = new JLabel();
		ballCS.setVisible(false);
		add(ballCS);
		
		ballCondition = new JCheckBox();
		ballCondition.setVisible(false);
		add(ballCondition);
		
		turnField = new JTextField();
		turnField.setVisible(false);
		add(turnField);
		
		ballList = new JComboBox<>(balls);
		ballList.setBounds(80, 160, 80, 20);
		ballList.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				check();
				
			}
			
		});
		add(ballList);

		submit = new JButton("Submit");
		submit.setBounds(10, 250, 80, 20);
		submit.addActionListener(this);
		submit.setActionCommand("submit");
		add(submit);
		
		chance = new JLabel();
		chance.setBounds(730, 0, 100, 30);
		chance.setFont(Coordinator.pokeFont);
		
		infoField = new JTextArea("The game starts by calculating the final capture rate, in a \n", 20, 30);
		infoField.setBounds(400, 300, 350, 250);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		infoField.setBorder(BorderFactory.createCompoundBorder(border, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		infoField.setEditable(false);
		infoField.setFont(new Font("Arial", Font.PLAIN, 12));
		infoField.append("manner essentially identical to the fifth-generation games: \nX = (((3M - 2H) * G * C * B) / (3M)) * S * O");
		infoField.append("\n\nThe chance of capture is therefore dependent upon several \nfactors - status condition, the intrisic capture rate that varies\n ");
		infoField.append("by pokemon, the type of pokeball used.");
		add(infoField);
		
		repaint();
	}
	
	/*
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }
    */
	
	private void check(){
		
		/* Displays conditional text, checkboxes, etc. depending on selected pokeball */
		
		if(ballList.getSelectedItem().equals("Repeat Ball")){

			turnField.setVisible(false);
			
    		ballCS.setText("Caught before?");
    		ballCS.setBounds(10, 190, 100, 20);
    		ballCS.setVisible(true);

    		ballCondition.setBounds(85, 190, 20, 20);
    		ballCondition.setVisible(true);
    		
    		repaint();	
    		
		}
		
		else if(ballList.getSelectedItem().equals("Timer Ball")){
			
			ballCondition.setVisible(false);
			
			ballCS.setText("Turn number?");
			ballCS.setBounds(10, 190, 100, 20);
			ballCS.setVisible(true);
			
			turnField.setBounds(85, 190, 50, 20);
			turnField.getDocument().addDocumentListener(new DocumentListener() {

				public void insertUpdate(DocumentEvent e) {
					check();
				}
				public void removeUpdate(DocumentEvent e) {
					//check();
				}
				public void changedUpdate(DocumentEvent e) {
					check();
				}
				
				public void check(){
					String t = turnField.getText();
					
					if(!t.matches("\\d+")){
						
						JOptionPane.showMessageDialog(null, "Please enter a valid integer!");
						Runnable r = new Runnable() {
					        @Override
					        public void run() {
					        	turnField.setText("");
					        }
					    };       
					    SwingUtilities.invokeLater(r);
						
					}
					
					else if(Integer.parseInt(t)<1){
						
						JOptionPane.showMessageDialog(null, "Please enter a level between 1 and 100!");
						Runnable r = new Runnable() {
					        @Override
					        public void run() {
					        	turnField.setText("");
					        }
					    };    
					    SwingUtilities.invokeLater(r);
					    
					}
					
				}

			});
			turnField.setVisible(true);
			
			repaint();
			
		}
		
		else if(ballList.getSelectedItem().equals("Quick Ball")){
			
			turnField.setVisible(false);
			
			ballCS.setText("First turn?");
			ballCS.setBounds(10, 190, 100, 20);
			ballCS.setVisible(true);
			
    		ballCondition.setBounds(80, 190, 20, 20);
    		ballCondition.setVisible(true);
    		
    		repaint();
    		
		}
						
		else if(ballList.getSelectedItem().equals("Dive Ball")){
			
			turnField.setVisible(false);
			
			ballCS.setText("Underwater, surfing, or fishing?");
			ballCS.setBounds(10, 190, 200, 20);
			ballCS.setVisible(true);

    		ballCondition.setBounds(160, 190, 20, 20);
    		ballCondition.setVisible(true);
    		
    		repaint();
    		
		}
		
		else if(ballList.getSelectedItem().equals("Dusk Ball")){
			
			turnField.setVisible(false);
			
			ballCS.setText("In a cave or at night?");
			ballCS.setBounds(10, 190, 100, 20);
			ballCS.setSize(ballCS.getPreferredSize());
			ballCS.setVisible(true);

    		ballCondition.setBounds(120, 190, 20, 20);
    		ballCondition.setVisible(true);
    		
    		repaint();
    		
		}
		
		else{
			
			ballCS.setVisible(false);
			ballCondition.setVisible(false);
			turnField.setVisible(false);
			repaint();

		}
		
	}

	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		if(command.equals("submit")){

			goCalc();
			
		}
		
	}
	
	private void goCalc(){
		
		try{
			hpPercentage = hpBar.getValue()/100d;
			
			selectedPokemon = findPokemon(pList.getSelectedItem().toString());
			selectedPokemon.setLevel(Integer.parseInt(levelField.getText()));

			/* Check for type of ball and calculate proper ball modifier */
			if(ballList.getSelectedItem().toString().equals("Master Ball")){			
				chance.setText("100%");
				add(chance);
				repaint();
				return;
			}
			else if(ballList.getSelectedItem().toString().equals("Repeat Ball")&&ballCondition.isSelected()) ballMod = 3;	
			else if(ballList.getSelectedItem().toString().equals("Timer Ball")){
				ballMod = 1 + (Integer.parseInt(turnField.getText()) * 1229/4096d);
				if(ballMod>4) ballMod = 4;
				
			}
			else if(ballList.getSelectedItem().toString().equals("Dive Ball")&&ballCondition.isSelected()) ballMod = 4;
			else if(ballList.getSelectedItem().toString().equals("Dusk Ball")&&ballCondition.isSelected()) ballMod = 3.5;
			else if(ballList.getSelectedItem().toString().equals("Net Ball")){
				
				if(selectedPokemon.getType1().equals("Bug")||selectedPokemon.getType1().equals("Water")||
						selectedPokemon.getType2().equals("Bug")||selectedPokemon.getType2().equals("Water")) ballMod = 3;
				
				else ballMod = 1;
			}
			else if(ballList.getSelectedItem().equals("Nest Ball")){
				
				ballMod = ((41 - selectedPokemon.getLevel()) / 10);
				if(ballMod<1) ballMod = 1;
			}
			else if(ballList.getSelectedItem().toString().equals("Great Ball")) ballMod = 2;
			else if(ballList.getSelectedItem().toString().equals("Ultra Ball")) ballMod = 2;
				
			else ballMod = 1;
			
			/* Check for status effect */
			if(statusList.getSelectedItem().toString().equals("Sleep")||statusList.getSelectedItem().toString().equals("Freeze"))	
				statusMod = 2.5;
		
			else if(statusList.getSelectedItem().toString().equals("Burn")||statusList.getSelectedItem().toString().equals("Paralysis"))
				statusMod = 1.5;
		
			else statusMod = 1;
			
			/* Check for O-Power modifier */
			if(oPowerList.getSelectedItem().toString().equals("Capture Power Lv. 1")) capturePowerMod = 1.5;
			else if(oPowerList.getSelectedItem().toString().equals("Capture Power Lv. 2")) capturePowerMod = 2;
			else if(oPowerList.getSelectedItem().toString().equals("No Power")) capturePowerMod = 1;
			else capturePowerMod = 2.5;
			
			chance.setText(Double.toString(Math.round(CaptureCalc.calc((int) (hpPercentage * selectedPokemon.getHP()), 
					selectedPokemon.getCatchRate(), selectedPokemon.getHP(), ballMod, statusMod, capturePowerMod)*100.0)/100.0)+"%");
			add(chance);
			repaint();
		}catch(NullPointerException e){ 
			JOptionPane.showMessageDialog(null, "Please fill in all fields!");
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please fill in all fields!");
		}
		
	}
	
	public Pokemon findPokemon(String name){

		for(int i=0; i<pokemons.length; i++){
	    	
	        if(pokemons[i].getName().equals(name)) return pokemons[i];
	    }
		return null;
		
	}

}
