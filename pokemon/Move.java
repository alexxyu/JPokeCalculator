package pokemon;

import java.util.StringTokenizer;

public class Move {

	private String name;
	
	private int power;
	private String type;
	private int effect; // 0 = none, 1 = dependent on weight, 2 = dependent on HP, 3 = dependent on speed, 4 = dependent on
					   // stat boosts, 5 = dependent on friendship
	private boolean isPhysical;
	private String description;
	
	public Move(String line){
		
		StringTokenizer st = new StringTokenizer(line, "	");
		name = st.nextToken();
		type = st.nextToken();
		String temp = st.nextToken();
		if(temp.equals("Physical")) isPhysical = true;
		else isPhysical = false;
		temp = st.nextToken();
		if(temp.equals("—")){
			power = 50; // change to add effect later
		}
		else power = Integer.parseInt(temp);		
		description = st.nextToken();
		if(description.equals("null")) description = "No additional effect";
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getPower(){
		
		return power;
		
	}
	
	public boolean isPhysical(){
		
		return isPhysical;
		
	}
	
	public String getType(){
		
		return type;
		
	}
	
	public int getEffect(){
		
		return effect;
		
	}
	
	public String getDescription(){
		
		return description;
		
	}
	
}
