package pokemon;

public class Type {

	public double normal(String powerType) {
		if(powerType.equals("Fighting")) return 2;
		else if(powerType.equals("Ghost")) return 0;
		else return 1;
	}

	public double grass(String powerType) {
		if(powerType.equals("Flying")||powerType.equals("Poison")||powerType.equals("Bug")||powerType.equals("Fire")||powerType.equals("Ice")) return 2;
		else if(powerType.equals("Ground")||powerType.equals("Grass")||powerType.equals("Water")||powerType.equals("Electric")) return 0.5;
		else return 1;
	}

	public double water(String powerType) {
		if(powerType.equals("Grass")||powerType.equals("Electric")) return 2;
		else if(powerType.equals("Steel")||powerType.equals("Fire")||powerType.equals("Water")||powerType.equals("Ice")) return 0.5;
		else return 1;
	}

	public double fire(String powerType) {
		if(powerType.equals("Rock")||powerType.equals("Water")||powerType.equals("Ground")) return 2;
		else if(powerType.equals("Bug")||powerType.equals("Steel")||powerType.equals("Fire")||powerType.equals("Ice")||powerType.equals("Fairy")||powerType.equals("Grass")) return 0.5;
		else return 1;
	}

	public double dark(String powerType) {
		if(powerType.equals("Fighting")||powerType.equals("Bug")||powerType.equals("Fairy")) return 2;
		else if(powerType.equals("Ghost")||powerType.equals("Dark")) return 0.5;	
		else if(powerType.equals("Psychic")) return 0;	
		else return 1;
	}

	public double psychic(String powerType) {
		if(powerType.equals("Bug")||powerType.equals("Ghost")||powerType.equals("Dark")) return 2;
		else if(powerType.equals("Fighting")||powerType.equals("Psychic")) return 0.5;
		else return 1;
	}

	public double ghost(String powerType) {
		if(powerType.equals("Ghost")||powerType.equals("Dark")) return 2;
		else if(powerType.equals("Poison")||powerType.equals("Bug")) return 0.5;
		else if(powerType.equals("Normal")||powerType.equals("Fighting")) return 0;
		else return 1;
	}

	public double dragon(String powerType) {
		if(powerType.equals("Ice")||powerType.equals("Dragon")||powerType.equals("Fairy")) return 2;
		else if(powerType.equals("Fire")||powerType.equals("Water")||powerType.equals("Grass")||powerType.equals("Electric")) return 0.5;
		else return 1;
	}

	public double ice(String powerType) {
		if(powerType.equals("Fighting")||powerType.equals("Rock")||powerType.equals("Steel")||powerType.equals("Fire")) return 2;
		else if(powerType.equals("Ice")) return 0.5;
		else return 1;
	}

	public double fighting(String powerType) {
		if(powerType.equals("Flying")||powerType.equals("Psychic")||powerType.equals("Fairy")) return 2;
		else if(powerType.equals("Rock")||powerType.equals("Bug")||powerType.equals("Dark")) return 0.5;
		else return 1;
	}

	public double poison(String powerType) {
		if(powerType.equals("Ground")||powerType.equals("Psychic")) return 2;
		else if(powerType.equals("Fighting")||powerType.equals("Poison")||powerType.equals("Grass")||powerType.equals("Fairy")) return 0.5;
		else return 1;
	}

	public double ground(String powerType) {
		if(powerType.equals("Water")||powerType.equals("Grass")||powerType.equals("Ice")) return 2;
		else if(powerType.equals("Poison")||powerType.equals("Rock")) return 0.5;
		else if(powerType.equals("Electric")) return 0;
		else return 1;
	}

	public double flying(String powerType) {
		if(powerType.equals("Rock")||powerType.equals("Electric")||powerType.equals("Ice")) return 2;
		else if(powerType.equals("Fighting")||powerType.equals("Grass")) return 0.5;
		else if(powerType.equals("Ground")) return 0;
		else return 1;
	}

	public double electric(String powerType) {
		if(powerType.equals("Ground")) return 2;
		else if(powerType.equals("Flying")||powerType.equals("Steel")||powerType.equals("Electric")) return 0.5;
		else return 1;
	}

	public double bug(String powerType) {
		if(powerType.equals("Flying")||powerType.equals("Rock")||powerType.equals("Fire")) return 2;
		else if(powerType.equals("Fighting")||powerType.equals("Ground")||powerType.equals("Grass")) return 0.5;
		else return 1;
	}

	public double rock(String powerType) {
		if(powerType.equals("Fighting")||powerType.equals("Ground")||powerType.equals("Steel")||powerType.equals("Water")||powerType.equals("Grass")) return 2;
		else if(powerType.equals("Normal")||powerType.equals("Flying")||powerType.equals("Poison")||powerType.equals("Fire")) return 0.5;
		else return 1;
	}

	public double steel(String powerType) {
		if(powerType.equals("Fighting")||powerType.equals("Ground")||powerType.equals("Fire")) return 2;
		else if(powerType.equals("Poison")) return 0;
		else if(powerType.equals("Water")||powerType.equals("Electric")||powerType.equals("Ghost")||powerType.equals("Dark")) return 1;
		else return 0.5;
	}
	
	public double fairy(String powerType) {

		
		if(powerType.equals("Poison")||powerType.equals("Steel")) return 2;	
		else if(powerType.equals("Fighting")||powerType.equals("Bug")||powerType.equals("Dark")) return 1;
		else if(powerType.equals("Dragon")) return 0;
		else return 0.5;
	}

}
