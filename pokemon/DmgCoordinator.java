package pokemon;

import java.io.BufferedReader;
import java.io.FileReader;

public class DmgCoordinator {

	public static final Pokemon[] pokemons = new Pokemon[795];
	public static final String[] pokemonNames = new String[795];
	public static final Move[] moves = new Move[364];
	public static final String[] moveNames = new String[364];

	@SuppressWarnings("resource")
	public DmgCoordinator(){
		
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run(){
		    	new Load();
		    }

		});
		t.start();
		
		try{
			int i=0;

			BufferedReader br = new BufferedReader(new FileReader("pokemonList.txt"));
			
			String line = br.readLine();
			
			while(line!=null || i>=pokemons.length){
				
				if(line==null) break;
				pokemons[i] = new Pokemon(line);
				pokemonNames[i] = pokemons[i].getName();
				line = br.readLine();
				
				i++;
			}
			
			i=0;
			br = new BufferedReader(new FileReader("moves.txt"));
			line = br.readLine();
			
			while(line!=null || i>=moves.length){
				
				if(line==null) break;
				moves[i] = new Move(line);
				moveNames[i] = moves[i].getName();
				line = br.readLine();
				
				i++;
			}
			
			br.close();

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		/* QUICK TESTS
		System.out.println(Calc.calcDamagePhys(findPokemon("Deoxys"), findPokemon("Skiddo"), 90, "Psychic", 1.5)+"%");
		System.out.println(findPokemon("Deoxys").getName()+" "+findPokemon("Deoxys").getHP());
		System.out.println(findPokemon("Skiddo").getName()+" "+findPokemon("Skiddo").getHP());
		*/
		
		// findPokemon("Venusaur").printMoves();
		
		new DmgGI();
		
	}
	
	public static Pokemon findPokemon(String name) {
		
	    for(int i=0; i<pokemons.length; i++){
	    	
	        if(pokemons[i].getName().equals(name)) return pokemons[i];
	    }
	    
	    return null;

	}
	
	public static Move findMove(String name) {
	    for(int i=0; i<moves.length; i++){

	    	if(moves[i].getName().equals(name)) return moves[i];
	    }
	    
	    return null;

	}
	
	public static Pokemon[] getPokemon(){
		
		return pokemons;
		
	}
	
}
