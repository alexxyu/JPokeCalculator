package pokemon;

import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

public class Pokemon {
		
	public String name;
	public String dexNum;

	private int baseHP, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpeed;
	private int level = 100;
	private int hp, atk, def, spAtk, spDef, speed;
	private int hpev = 0, atkev = 0, defev = 0, spAtkev = 0, spDefev = 0, speedev = 0;
	
	private int atkNV = 1, defNV = 1, spAtkNV = 1, spDefNV = 1, speedNV = 1;
	// remember to change this!
	
	private String type1;
	private String type2;
	
	private boolean isMega;
	
	private ImageIcon front; // normal front sprite
	private ImageIcon back; // normal back sprite

	private ImageIcon megaF1; // mega front sprite
	private ImageIcon megaB1; // mega back sprite
	
	private int catchRate;
	
	// private int abilityEffect;
	
	private ArrayList<String> learnset = new ArrayList<String>();
	
	public Pokemon(String line){
		
		StringTokenizer st = new StringTokenizer(line, "	");
		dexNum = st.nextToken();
		name = st.nextToken().toString();
		type1 = st.nextToken().toString();
		type2 = st.nextToken().toString();
		baseHP = Integer.parseInt(st.nextToken());
		baseAtk = Integer.parseInt(st.nextToken());
		baseDef = Integer.parseInt(st.nextToken());
		baseSpAtk = Integer.parseInt(st.nextToken());
		baseSpDef = Integer.parseInt(st.nextToken());
		baseSpeed = Integer.parseInt(st.nextToken());
		isMega = Boolean.parseBoolean(st.nextToken());
		
		// System.out.println(name);
		
		URL url;
		
		if(isMega){
			
			if(name.equals("Mewtwo-Mega Y")){
				url = DmgGI.class.getResource("animations_f/mewtwo-mega-y.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/mewtwo-mega-y.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else if(name.equals("Mewtwo-Mega X")){
				url = DmgGI.class.getResource("animations_f/mewtwo-mega-x.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/mewtwo-mega-x.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else if(name.equals("Charizard-Mega Y")){
				url = DmgGI.class.getResource("animations_f/charizard-mega-y.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/charizard-mega-y.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else if(name.equals("Charizard-Mega X")){
				url = DmgGI.class.getResource("animations_f/charizard-mega-x.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/charizard-mega-x.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else if(name.equals("Groudon-Primal")){
				url = DmgGI.class.getResource("animations_f/groudon-primal.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/groudon-primal.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else if(name.equals("Kyogre-Primal")){
				url = DmgGI.class.getResource("animations_f/kyogre-primal.gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/kyogre-primal.gif");
				megaB1 = new ImageIcon(url);
			}
			
			else{
				url = DmgGI.class.getResource("animations_f/"+name.toLowerCase().replaceAll("[. ]*", "")+".gif");
				megaF1 = new ImageIcon(url);
				url = DmgGI.class.getResource("animations_b/"+name.toLowerCase().replaceAll("[. ]*", "")+".gif");
				megaB1 = new ImageIcon(url);
			}
		}
		
		else{
			url = DmgGI.class.getResource("animations_f/"+name.toLowerCase().replaceAll("[-'. ]*", "")+".gif");
			front = new ImageIcon(url);
			url = DmgGI.class.getResource("sprites/"+dexNum+"b.gif");
			back = new ImageIcon(url);
		}

		calcStats();
		
	}
	
	public Pokemon(String line, String c){
		
		StringTokenizer st = new StringTokenizer(line, "	");
		name = st.nextToken();
		type1 = st.nextToken();
		type2 = st.nextToken();
		baseHP = Integer.parseInt(st.nextToken());
		baseSpeed = Integer.parseInt(st.nextToken());
		catchRate = Integer.parseInt(st.nextToken());
		
		hp = ((31 + 2 * baseHP + (hpev/4) ) * level/100 ) + 10 + level;
		
	}
	
	
	// hp stat = ((iv + 2 * baseHP + (hpev/4) ) * level/100 ) + 10 + level
	// other stats = ((( iv + 2 * baseStat + (evs/4) ) * level/100 ) + 5) * natureMod
	
	private void calcStats(){
		hp = ((31 + 2 * baseHP + (hpev/4) ) * level/100 ) + 10 + level;
		atk = (((31 + 2 * baseAtk + (atkev/4) ) * level/100 ) + 5) * atkNV;
		def = (((31 + 2 * baseDef + (defev/4) ) * level/100 ) + 5) * defNV;
		spAtk = (((31 + 2 * baseSpAtk + (spAtkev/4) ) * level/100 ) + 5) * spAtkNV;
		spDef = (((31 + 2 * baseSpDef + (spDefev/4) ) * level/100 ) + 5) * spDefNV;
		speed = (((31 + 2 * baseSpeed + (speedev/4) ) * level/100 ) + 5) * speedNV;
	}
	
	
	public void setName(String n){
		name = n;
	}

	public void setMoves(String line){
		
		int i=0;
		
		StringTokenizer st = new StringTokenizer(line, ",");
		st.nextToken();
		while(i<st.countTokens()){

			learnset.add(st.nextToken());
			i++;
			
		}
		
	}
	
	public int getHP(){
		return hp;
	}
	
	public int getAtk(){
		return atk;
	}
	public int getbaseAtk(){
		return baseAtk;
	}
	
	public int getDef(){
		return def;
	}
	
	public int getSpAtk(){
		return spAtk;
	}
	
	public int getSpDef(){
		return spDef;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public int getLevel(){
		return level;
	}
	
	public String getType1(){
		return type1;
	}
	
	public String getType2(){
		return type2;
	}
	
	public String getName(){
		return name;
	}
	
	public ImageIcon getFrontSprite(){
		if(isMega) return megaF1;
		return front;
	}
	
	public ImageIcon getBackSprite(){
		if(isMega) return megaB1;
		return back;
	}
	
	
	public void printMoves(){
		for(int i=0; i<learnset.size(); i++){
			System.out.println(learnset.get(i));
			
		}
	}
	
	public int getCatchRate(){
		return catchRate;
	}
	
	public void setLevel(int _level){
		
		level = _level;
		calcStats();
		
	}
	
}
