package pokemon;

public class DamageCalc {

	private static int randomNum;
	private static Type type = new Type();

	public double calcDamagePhys(Pokemon p1, Pokemon p2, Move move) {
		double stabBonus;
		if(move.getType().equals(p1.getType1())||move.getType().equals(p1.getType2())) stabBonus = 1.5;
		else stabBonus = 1;
		double typeMods = calcTypeMod(move.getType(), p2.getType1(), p2.getType2());
		randomNum = (int) 85 + ((int) (Math.random() * 15));
		return (((2 * p1.getLevel() / 5 + 2) * p1.getAtk() * move.getPower() / p2.getDef() / 50 + 2 )* stabBonus * typeMods * randomNum / 100)/p2.getHP()*100;
	}

	public double calcDamageSpec(Pokemon p1, Pokemon p2, Move move) {
		double stabBonus;
		if(move.getType().equals(p1.getType1())||move.getType().equals(p1.getType2())) stabBonus = 1.5;
		else stabBonus = 1;
		double typeMods = calcTypeMod(move.getType(), p2.getType1(), p2.getType2());
		randomNum = (int) 85 + ((int) (Math.random() * 15));
		return (((2 * p1.getLevel() / 5 + 2) * p1.getSpAtk() * move.getPower() / p2.getSpDef() / 50 + 2 )* stabBonus * typeMods * randomNum / 100)/p2.getHP()*100;
	}

	/*
	 * Formula for damage:
	 * ((2A/5+2)*B*C)/D)/50)+2)*X)*Y/10)*Z)/255
	 * 
	 * A = attacker's Level 
	 * B = attacker's Attack or Special 
	 * C = attack Power 
	 * D = defender's Defense or Special 
	 * X = same-Type attack bonus (1 or 1.5)
	 * Y = Type modifiers (40, 20, 10, 5, 2.5, or 0) 
	 * Z = a random number between 217 and 255
	 */
	
	// Calculate type effectiveness based on defender's typing
	
	private static double calcTypeMod(String powerType, String defenderType1, String defenderType2) {

		double firstMult = 0, secondMult = 0;

		if (defenderType1.equals("Normal")) {
			firstMult = type.normal(powerType);

		}

		else if (defenderType1.equals("Water")) {
			firstMult = type.water(powerType);

		}

		else if (defenderType1.equals("Grass")) {
			firstMult = type.grass(powerType);

		}

		else if (defenderType1.equals("Fire")) {
			firstMult = type.fire(powerType);

		}

		else if (defenderType1.equals("Dark")) {
			firstMult = type.dark(powerType);

		}

		else if (defenderType1.equals("Dragon")) {
			firstMult = type.dragon(powerType);

		} 
		
		else if (defenderType1.equals("Ghost")) {
			firstMult = type.ghost(powerType);

		}

		else if (defenderType1.equals("Psychic")) {
			firstMult = type.psychic(powerType);

		}

		else if (defenderType1.equals("Rock")) {
			firstMult = type.rock(powerType);

		}

		else if (defenderType1.equals("Steel")) {
			firstMult = type.steel(powerType);

		}

		else if (defenderType1.equals("Electric")) {
			firstMult = type.electric(powerType);

		}

		else if (defenderType1.equals("Ground")) {
			firstMult = type.ground(powerType);

		}

		else if (defenderType1.equals("Flying")) {
			firstMult = type.flying(powerType);

		}

		else if (defenderType1.equals("Bug")) {
			firstMult = type.bug(powerType);

		}

		else if (defenderType1.equals("Fighting")) {
			firstMult = type.fighting(powerType);

		}

		else if (defenderType1.equals("Ice")) {
			firstMult = type.ice(powerType);

		}

		else if (defenderType1.equals("Poison")) {
			firstMult = type.poison(powerType);

		}
		
		else if (defenderType1.equals("Fairy")) {
			firstMult = type.fairy(powerType);

		}

		if (!defenderType2.equals("null")) {
			if (defenderType2.equals("Normal")) {
				secondMult = type.normal(powerType);

			}

			else if (defenderType2.equals("Water")) {
				secondMult = type.water(powerType);

			}

			else if (defenderType2.equals("Grass")) {
				secondMult = type.grass(powerType);

			}

			else if (defenderType2.equals("Fire")) {
				secondMult = type.fire(powerType);

			}

			else if (defenderType2.equals("Dark")) {
				secondMult = type.dark(powerType);

			} 
			
			else if (defenderType2.equals("Dragon")) {
				secondMult = type.dragon(powerType);

			}

			else if (defenderType2.equals("Ghost")) {
				secondMult = type.ghost(powerType);

			}

			else if (defenderType2.equals("Psychic")) {
				secondMult = type.psychic(powerType);

			}

			else if (defenderType2.equals("Rock")) {
				secondMult = type.rock(powerType);

			}

			else if (defenderType2.equals("Steel")) {
				secondMult = type.steel(powerType);

			}

			else if (defenderType2.equals("Electric")) {
				secondMult = type.electric(powerType);

			}

			else if (defenderType2.equals("Ground")) {
				secondMult = type.ground(powerType);

			}

			else if (defenderType2.equals("Flying")) {
				secondMult = type.flying(powerType);

			} 
			
			else if (defenderType2.equals("Bug")) {
				secondMult = type.bug(powerType);

			} 
			
			else if (defenderType2.equals("Fighting")) {
				secondMult = type.fighting(powerType);

			}

			else if (defenderType2.equals("Ice")) {
				secondMult = type.ice(powerType);

			}

			else if (defenderType2.equals("Poison")) {
				secondMult = type.poison(powerType);

			}
			
			else if (defenderType2.equals("Fairy")) {
				secondMult = type.fairy(powerType);

			}

			return firstMult * secondMult;
		}

		else
			return firstMult;
	}

	
	
}
