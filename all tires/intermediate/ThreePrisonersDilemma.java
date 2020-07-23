public class ThreePrisonersDilemma {
	
	/* 
	 This Java program models the two-player Prisoner's Dilemma game.
	 We use the integer "0" to represent cooperation, and "1" to represent 
	 defection. 
	 
	 Recall that in the 2-players dilemma, U(DC) > U(CC) > U(DD) > U(CD), where
	 we give the payoff for the first player in the list. We want the three-player game 
	 to resemble the 2-player game whenever one player's response is fixed, and we
	 also want symmetry, so U(CCD) = U(CDC) etc. This gives the unique ordering
	 
	 U(DCC) > U(CCC) > U(DDC) > U(CDC) > U(DDD) > U(CDD)
	 
	 The payoffs for player 1 are given by the following matrix: */
	
	static int[][][] payoff = {  
		{{6,3},  //payoffs when first and second players cooperate 
		 {3,0}}, //payoffs when first player coops, second defects
		{{8,5},  //payoffs when first player defects, second coops
	     {5,2}}};//payoffs when first and second players defect
	
	/* 
	 So payoff[i][j][k] represents the payoff to player 1 when the first
	 player's action is i, the second player's action is j, and the
	 third player's action is k.
	 
	 In this simulation, triples of players will play each other repeatedly in a
	 'match'. A match consists of about 100 rounds, and your score from that match
	 is the average of the payoffs from each round of that match. For each round, your
	 strategy is given a list of the previous plays (so you can remember what your 
	 opponent did) and must compute the next action.  */
	
	
	abstract class Player {
		// This procedure takes in the number of rounds elapsed so far (n), and 
		// the previous plays in the match, and returns the appropriate action.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			throw new RuntimeException("You need to override the selectAction method.");
		}
		
		// Used to extract the name of this player class.
		final String name() {
			String result = getClass().getName();
			return result.substring(result.indexOf('$')+1);
		}
	}
	
	/* Here are four simple strategies: */
	
	class NicePlayer extends Player {
		//NicePlayer always cooperates
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			return 0; 
		}
	}

	class Adaptive extends Player {
		//NicePlayer always cooperates
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) 
			{
				return 0;
				
			}
			if (oppHistory1[n-1]==0 && oppHistory2[n-1]==0 && myHistory[n-1]==1)
			{
				return 1;
			}
			if (oppHistory1[n-1]==0 && oppHistory2[n-1]==0 && myHistory[n-1]==0)
			{
				return 0;
			}
			if (oppHistory1[n-1]==0 && oppHistory2[n-1]==1 && myHistory[n-1]==1)
			{
				return 1;
			}
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==0 && myHistory[n-1]==1)
			{
				return 1;
			}
			if (oppHistory1[n-1]==0 && oppHistory2[n-1]==1 && myHistory[n-1]==0)
			{
				return 1;
			}
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==0 && myHistory[n-1]==0)
			{
				return 1;
			}
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1 && myHistory[n-1]==1)
			{
				return 0;
			}
			else
			{
				return 1;
			}
			 

		}
	}
	
	class NastyPlayer extends Player {
		//NastyPlayer always defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			return 1; 
		}
	}

	class TrickyPlayer extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && (oppHistory1[n-1]==1 && oppHistory1[n-2]==1) || (oppHistory2[n-1]==1 && oppHistory2[n-2]==1))
			{
				return 1;
			}
			else return 0;
		}
	}


	class TrickyPlayer2 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && (oppHistory1[n-1]==1 && oppHistory1[n-2]==1) && (oppHistory2[n-1]==1 && oppHistory2[n-2]==1))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer3 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer31 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

		class TrickyPlayer3_biased extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer31_biased extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer3_biased2 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer4 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer41 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer4_biased extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}

	class TrickyPlayer4_biased2 extends Player {
		//Defect if opponent has cooperated at least once in the past and has
        //defected for the last 2 turns in a row.
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int flag=0;
			int counter=0;
			for (int i=0; i<n; i++) 
			{
				if (oppHistory1[i]==1 && oppHistory2[i]==1) 
				{
					flag=1;
				}
			}
			if (n==0) return 0;
			if (n==1) return 0;
			else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
			{
				return 1;
			}
			else return 0;
		}
	}



	class Alternating1 extends Player {
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			else 
			{
				return 1-myHistory[n-1];
			} 
		}
	}

	class Alternating2 extends Player {
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 1;
			else 
			{
				return 1-myHistory[n-1];
			} 
		}
	}
	
	class RandomPlayer extends Player {
		//RandomPlayer randomly picks his action each time
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (Math.random() < 0.5)
				return 0;  //cooperates half the time
			else
				return 1;  //defects half the time
		}
	}

	class RandomPlayer2 extends Player {
		//RandomPlayer randomly picks his action each time
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (Math.random() < 1/3)
				return 0;  //cooperates half the time
			else
				return 1;  //defects half the time
		}
	}

	class RandomPlayer3 extends Player {
		//RandomPlayer randomly picks his action each time
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (Math.random() < 2/3)
				return 0;  //cooperates half the time
			else
				return 1;  //defects half the time
		}
	}
	
	class TolerantPlayer extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > opponentCoop)
				return 1;
			else
				return 0;
		}
	}

	class UpToU1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (Math.random() < prob)
			// {	System.out.println('D');
				return 1;
			// }
			else
			// {	System.out.println('C');
				return 0;
			// }
		}
	}

	class UpToU1_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (Math.random() < prob)
			// {	System.out.println('D');
				return 1;
			// }
			else
			// {	System.out.println('C');
				return 0;
			// }
		}
	}

	class UpToU1_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (Math.random() < prob)
			// {	System.out.println('D');
				return 1;
			// }
			else
			// {	System.out.println('C');
				return 0;
			// }
		}
	}

	class UpToU1_discount3 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.8, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.8, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.8, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.8, (n-i));
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (Math.random() < prob)
			// {	System.out.println('D');
				return 1;
			// }
			else
			// {	System.out.println('C');
				return 0;
			// }
		}
	}

	class UpToU1_forgiving extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (prob>0.2)
			{
				if (Math.random() < (prob-0.2))
				// {	System.out.println('D');
					return 1;
				// }
				else
				// {	System.out.println('C');
					return 0;
				// }
			}
			else
				return 0;
		}
	}

	class UpToU1_forgiving3_41 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (prob>3/4)
			{
				if (Math.random() < prob)
				// {	System.out.println('D');
					return 1;
				// }
				else
				// {	System.out.println('C');
					return 0;
				// }
			}
			else
				return 0;
		}
	}

	class UpToU1_forgiving3_42 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);
			// System.out.println(opponentDefect);
			// System.out.println(opponentCoop);
			// System.out.println(prob);
			if (prob<3/4)
			{
				if (Math.random() < prob)
				// {	System.out.println('D');
					return 0;
				// }
				else
				// {	System.out.println('C');
					return 1;
				// }
			}
			else
				return 1;
		}
	}

		class UpToU2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 1;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			double prob = opponentDefect / (opponentCoop + opponentDefect);

			if (Math.random() < prob)
				return 1;
			else
				return 0;
		}
	}


	class TolerantPlayer_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > opponentCoop || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > opponentCoop || oppHistory1[n-1]==1 || oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if (opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount1_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount1_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) || oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if (opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount2_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_discount2_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2)|| oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if (opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount2_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2)|| (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount2_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.9, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.9, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.9, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2)|| oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if (opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount1_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_discount1_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			double opponentCoop1 = 0;
			double opponentDefect1 = 0;
			double opponentCoop2 = 0;
			double opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + Math.pow(0.99, (n-i));
				else
					opponentDefect1 = opponentDefect1 + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + Math.pow(0.99, (n-i));
				else
					opponentDefect2 = opponentDefect2 + Math.pow(0.99, (n-i));
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) || oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if (opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_both_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if ((opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) || oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if (opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_individual_either_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop1 = 0;
			int opponentDefect1 = 0;
			int opponentCoop2 = 0;
			int opponentDefect2 = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop1 = opponentCoop1 + 1;
				else
					opponentDefect1 = opponentDefect1 + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop2 = opponentCoop2 + 1;
				else
					opponentDefect2 = opponentDefect2 + 1;
			}
			if ((opponentDefect1 > opponentCoop1 || opponentDefect2 > opponentCoop2) || oppHistory1[n-1]==1 || oppHistory2[n-1]==1) 
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			if (opponentDefect > opponentCoop)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer2_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			if (opponentDefect > (opponentCoop+opponentDefect)/3)
			{
				return 1;
			}
			else
				return 0;
		}
	}

	class TolerantPlayer3_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
			{
				return 1;
			}
			else
				return 0;
		}
	}

	class TolerantPlayer_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			if (opponentDefect > opponentCoop)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer2_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			if (opponentDefect > (opponentCoop+opponentDefect)/3)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer3_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
				return 1;
			else
				return 0;
		}
	}


	class TolerantPlayer2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > (opponentCoop+opponentDefect)/3)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer2_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > (opponentCoop+opponentDefect)/3 || oppHistory1[n-1]==1 || oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer2_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > (opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer3 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer3_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || oppHistory1[n-1]==1 || oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer3_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer4 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 3*(opponentCoop+opponentDefect)/4)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer4_Bboth extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 3*(opponentCoop+opponentDefect)/4 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer4_Beither extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0;
			int opponentCoop = 0;
			int opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + 1;
				else
					opponentDefect = opponentDefect + 1;
			}
			if (opponentDefect > 3*(opponentCoop+opponentDefect)/4 || oppHistory1[n-1]==1 || oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer4_discount1 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.99, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.99, (n-i));
			}
			if (opponentDefect > 3*(opponentCoop+opponentDefect)/4)
				return 1;
			else
				return 0;
		}
	}

	class TolerantPlayer4_discount2 extends Player {
		//TolerantPlayer looks at his opponents' histories, and only defects
		//if at least half of the other players' actions have been defects
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			double opponentCoop = 0;
			double opponentDefect = 0;
			for (int i=0; i<n; i++) {
				if (oppHistory1[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			for (int i=0; i<n; i++) {
				if (oppHistory2[i] == 0)
					opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
				else
					opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
			}
			if (opponentDefect > 3*(opponentCoop+opponentDefect)/4)
				return 1;
			else
				return 0;
		}
	}
	
	class FreakyPlayer extends Player {
		//FreakyPlayer determines, at the start of the match, 
		//either to always be nice or always be nasty. 
		//Note that this class has a non-trivial constructor.
		int action;
		FreakyPlayer() {
			if (Math.random() < 0.5)
				action = 0;  //cooperates half the time
			else
				action = 1;  //defects half the time
		}
		
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			return action;
		}	
	}

	class T4TPlayer_1stD extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 1; //cooperate by default
			if (Math.random() < 0.5)
				return oppHistory1[n-1];
			else
				return oppHistory2[n-1];
		}	
	}

	class T4TPlayer extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			if (Math.random() < 0.5)
				return oppHistory1[n-1];
			else
				return oppHistory2[n-1];
		}	
	}

	class T4TPlayer_either extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 || oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}	
	}

	class T4TPlayer_both extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}	
	}

	class T4TPlayer_both_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			if (n>90)
			{
				return 1;
			}
			else if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
		}	
	}


	class Joss extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			if (Math.random() < 0.1) 
			{
				return 1;
			}
			else
			{
				if (Math.random() < 0.5)
					return oppHistory1[n-1];
				else
					return oppHistory2[n-1];
			}
			
		}	
	}

	class T42TPlayer extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1) return 0; //cooperate by default
			if (Math.random() < 0.5){
				if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1) 
					{ 
						return 1;
					} 
				else 
					{
						return 0;
					}
									}
			else{
				if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1) 
					{
					return 1;
					} 	
				else{
					return 0;
					}
				}
		}	
	}

	class T43TPlayer extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1 || n==2) return 0; //cooperate by default
			if (Math.random() < 0.5){
				if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
					{ 
						return 1;
					} 
				else 
					{
						return 0;
					}
									}
			else{
				if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
					{
					return 1;
					} 	
				else{
					return 0;
					}
				}
		}	
	}

	class T44TPlayer extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default
			if (Math.random() < 0.5){
				if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
					{ 
						return 1;
					} 
				else 
					{
						return 0;
					}
									}
			else{
				if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
					{
					return 1;
					} 	
				else{
					return 0;
					}
				}
		}	
	}

	class T44TPlayer_deter extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default

			if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
				{ 
					return 1;
				} 
									
			else{
				if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
					{
					return 1;
					} 	
				else{
					return 0;
					}
				}
		}	
	}

	class T45TPlayer extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1 || n==2 || n==3 || n==4) return 0; //cooperate by default
			if (Math.random() < 0.5){
				if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1 && oppHistory1[n-5]==1)
					{ 
						return 1;
					} 
				else 
					{
						return 0;
					}
									}
			else{
				if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1 && oppHistory2[n-5]==1) 
					{
					return 1;
					} 	
				else{
					return 0;
					}
				}
		}	
	}

	class T4_3TPlayer_accumulate extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			int flag=0;
			int counter=0;
			int start=0;
			for (int i=n-1; i>=0; i--) 
			{
				if (myHistory[i]==1 && flag==0) 
				{
					start=i;
					flag=1;
				}
			}

			if (start>0) 
			{
				for (int i=start; i<n; i++) 
				{
				if (oppHistory1[i]==1)
				{
					counter=counter+1;
				}
				if (oppHistory2[i]==1)
				{
					counter=counter+1;
				}
				}
				if (counter==3)
				{
					return 1;				
				}
				else
				{
					return 0;
				}
			}
			else
			{
				return 0;
			}
		}	
	}

	class T4_10TPlayer_accumulate extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			int flag=0;
			int counter=0;
			int start=0;
			for (int i=n-1; i>=0; i--) 
			{
				if (myHistory[i]==1 && flag==0) 
				{
					start=i;
					flag=1;
				}
			}

			if (start>0) 
			{
				for (int i=start; i<n; i++) 
				{
				if (oppHistory1[i]==1)
				{
					counter=counter+1;
				}
				if (oppHistory2[i]==1)
				{
					counter=counter+1;
				}
				}
				if (counter==10)
				{
					return 1;				
				}
				else
				{
					return 0;
				}
			}
			else
			{
				return 0;
			}
		}	
	}

	class T4_3in10TPlayer_accumulate extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0) return 0; //cooperate by default
			int flag=0;
			int counter=0;
			if (n<10) {
				for (int i=0; i<n; i++)
				{
					if (oppHistory1[i]==1)
					{
						counter=counter+1;
					}
					if (oppHistory2[i]==1)
					{
						counter=counter+1;
					}
						
				}
				
			}

			else
			{
				for (int i=n-10; i<n; i++) 
				{
				if (oppHistory1[i]==1)
				{
					counter=counter+1;
				}
				if (oppHistory2[i]==1)
				{
					counter=counter+1;
				}
				}
				
			}
			if (counter==3)
			{
				return 1;				
			}
			else
			{
				return 0;
			}
				
		}	
	}

	class ForgivingJoss extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n==0 || n==1) return 0; //cooperate by default
			if (Math.random() < 0.1) 
			{
				return 1;
			}
			else{
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1) 
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
				}
		}	
	}

	class OneOff extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n < 5) 
			{
				return 1;
			}
			if (n==5 || n==6 || n==7|| n==8|| n==9|| n==10) {
				return 0;
			}
			if (oppHistory1[9]==1 && oppHistory1[10]==1 && oppHistory2[9]==1 && oppHistory2[10]==1) {
				return 1;
			}
			else{
				return 0;
				}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver2_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver3 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver3_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver4 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer_discount2_ver4_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_10 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_20_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class TrickyPlayer3_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class TrickyPlayer3_nasty_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class TrickyPlayer3_nasty_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_20_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_15_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<15)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_10_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_20_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_15_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<15)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_10_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_30_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_30_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_40_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_40_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_40_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_40_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_30_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_30_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}



	class TrickyPlayer3_and_T4TPlayer_both_nasty_50_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_50_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_20_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}

	class TrickyPlayer3_and_T4TPlayer_both_nasty_20_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;
				
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
		}	
	}



	class T4TPlayer_both_and_TrickyPlayer3_nasty_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 || oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_50_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_60_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<60)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_65_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<65)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_70_biased extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<70)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) && ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_50_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_60_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<60)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_65_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<65)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TrickyPlayer3_nasty_70_biased2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<70)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
			 	int flag=0;
				int counter=0;
				for (int i=0; i<n; i++) 
				{
					if (oppHistory1[i]==1 && oppHistory2[i]==1) 
					{
						flag=1;
					}
				}
				if (n==0) return 0;
				if (n==1) return 0;
				else if (flag==1 && ((oppHistory1[n-1]==1 || oppHistory1[n-2]==1)) || ((oppHistory2[n-1]==1 && oppHistory2[n-2]==1)))
				{
					return 1;
				}
				else return 0;

			}
		}	
	}




	class T4TPlayer_both_and_TolerantPlayer3_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_60 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<60)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer_10 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > opponentCoop)
					return 1;
				else
					return 0;

			}
		}	
	}

	


	class T43TPlayer_and_TolerantPlayer3_10 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer3_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer3_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer3_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T43TPlayer_and_TolerantPlayer3_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class TolerantPlayer3_and_T4TPlayer_both extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n>10)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3)
					return 1;
				else
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer4_discount2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<10)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					else
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
				}
				if (opponentDefect > 3*(opponentCoop+opponentDefect)/4)
					return 1;
				else
					return 0;

			}
		}	
	}




	class T4TPlayer_both_and_UpToU1 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				double prob = opponentDefect / (opponentCoop + opponentDefect);
				// System.out.println(opponentDefect);
				// System.out.println(opponentCoop);
				// System.out.println(prob);
				if (Math.random() < prob)
				// {	System.out.println('D');
					return 1;
				// }
				else
				// {	System.out.println('C');
					return 0;

			}
		}	
	}

	class T4TPlayer_both_and_UpToU1_ver2 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
			 	int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				double prob = opponentDefect / (opponentCoop + opponentDefect);
				// System.out.println(opponentDefect);
				// System.out.println(opponentCoop);
				// System.out.println(prob);
				if (Math.random() < prob)
				// {	System.out.println('D');
					return 1;
				// }
				else
				// {	System.out.println('C');
					return 0;

			}
		}	
	}


	class T4TPlayer_both_and_TolerantPlayer3_Bboth_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_50_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_40_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_30_nasty extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			if (n>90)
			{
				return 1;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T4TPlayer_both_and_TolerantPlayer3_Bboth_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0) return 0; //cooperate by default
				if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
					return 1;
				else
					return 0;
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}



	class T4TPlayer_both_and_TolerantPlayer_individual_both_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
			if (n==0) return 0; //cooperate by default
			if (oppHistory1[n-1]==1 && oppHistory2[n-1]==1)
				return 1;
			else
				return 0;
			}
			else
			{
			 	int opponentCoop1 = 0;
				int opponentDefect1 = 0;
				int opponentCoop2 = 0;
				int opponentDefect2 = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop1 = opponentCoop1 + 1;
					else
						opponentDefect1 = opponentDefect1 + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop2 = opponentCoop2 + 1;
					else
						opponentDefect2 = opponentDefect2 + 1;
				}
				if (opponentDefect1 > opponentCoop1 && opponentDefect2 > opponentCoop2) 
					return 1;
				else
					return 0;
			}
		}	
	}



	class T43TPlayer_and_TolerantPlayer_Bboth extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0 || n==1 || n==2) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory1[n-3]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > opponentCoop || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}



	class T44TPlayer_and_TolerantPlayer3_Bboth_50 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<50)
			{
				if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T44TPlayer_and_TolerantPlayer3_Bboth_40 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<40)
			{
				if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T44TPlayer_and_TolerantPlayer3_Bboth_30 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<30)
			{
				if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}

	class T44TPlayer_and_TolerantPlayer3_Bboth_20 extends Player {
		//Picks a random opponent at each play, 
		//and uses the 'tit-for-tat' strategy against them 
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			if (n<20)
			{
				if (n==0 || n==1 || n==2 || n==3) return 0; //cooperate by default
				if (Math.random() < 0.5){
					if (oppHistory1[n-1]==1 && oppHistory1[n-2]==1 && oppHistory1[n-3]==1 && oppHistory1[n-4]==1)
						{ 
							return 1;
						} 
					else 
						{
							return 0;
						}
										}
				else{
					if (oppHistory2[n-1]==1 && oppHistory2[n-2]==1 && oppHistory2[n-3]==1 && oppHistory2[n-4]==1) 
						{
						return 1;
						} 	
					else{
						return 0;
						}
					}
			}
			else
			{
				int opponentCoop = 0;
				int opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0)
						opponentCoop = opponentCoop + 1;
					else
						opponentDefect = opponentDefect + 1;
				}
				if (opponentDefect > 2*(opponentCoop+opponentDefect)/3 || (oppHistory1[n-1]==1 && oppHistory2[n-1]==1))
					return 1;
				else
					return 0;
			}
		}	
	}


	 class GosuTheMinion extends NicePlayer {

        // For tracking Defect/Cooperate probabilities
        private double opp1Def = 0;
        private double opp2Def = 0;

        // Thresholds
        private static final double FRIENDLY_THRESHOLD = 0.850;
        private static final double DEFENSIVE_THRESHOLD = 0.750;

        /* ALL HAIL KING CHODY!! */
        int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

            // Start by cooperating
            if (n == 0) {

                return 0;
            }

            // Calculate probability for Def/Coop (Opponent 1)
            opp1Def += oppHistory1[n - 1];
            double opp1DefProb = opp1Def / oppHistory1.length;
            double opp1CoopProb = 1.000 - opp1DefProb;

            // Calculate probability for Def/Coop (Opponent 2)
            opp2Def += oppHistory2[n - 1];
            double opp2DefProb = opp2Def / oppHistory2.length;
            double opp2CoopProb = 1.000 - opp2DefProb;

            /*System.out.printf("Opponent 1: %.3f, %.3f, Opponent 2: %.3f, %.3f%n",
					opp1CoopProb, opp1DefProb, opp2CoopProb, opp2DefProb);*/
            if (opp1CoopProb >= FRIENDLY_THRESHOLD
                    && opp2CoopProb >= FRIENDLY_THRESHOLD
                    && oppHistory1[n - 1] == 0
                    && oppHistory2[n - 1] == 0) {

                // Good chance that both opponents will cooperate
                // Just cooperate so that everyone will be happy
                return 0;

            } else if ((opp1DefProb >= DEFENSIVE_THRESHOLD || opp2DefProb >= DEFENSIVE_THRESHOLD)
                    && (oppHistory1[n - 1] == 1 || oppHistory2[n - 1] == 1)) {

                // Given that one of the opponents have been relatively nasty,
                // and one of them has defected in the previous turn,
                // high prob that one of them will defect again,
                // defect to protect myself!
                return 1;

            } else if (n >= 2) {

                // Check if either opponent has defected in the last 2 turns
                if (oppHistory1[n - 1] == 1 || oppHistory2[n - 1] == 1
                        || oppHistory1[n - 2] == 1 || oppHistory2[n - 2] == 1) {

                    // DESTROY them!!
                    return 1;
                } else {

                    // Just be friendly!
                    return 0;
                }
            } else {

                // At this moment, both players are not that friendly,
                // and yet neither of them are relatively nasty.
                // Just be friendly for now.
                return 0;
            }
        }
    }


    class Bummer extends NastyPlayer {

        //Count the number of defects by opp
        int intPlayer1Defects = 0;
        int intPlayer2Defects = 0;

        //Store the round where agent retaliate against defects
        int intRoundRetailate = -1;

        //Number of rounds where agent coop to observer opp actions
        int intObservationRound = 1;

        //Number of rounds where agent retaliate defects with defects
        //After this round, see opp actions to check if they decide to coop again
        int intGrudgeRound = 3;

        int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

            //Record Defects count
            if (n > 0) {
                intPlayer1Defects += oppHistory1[n - 1];
                intPlayer2Defects += oppHistory2[n - 1];
            }

            //Start by cooperating
            if (n < intObservationRound) {
                return 0; //cooperate by default
            }

            //Loop rounds where agent coop to reverse the effects of retaliation
            if (intRoundRetailate < -1) {
                intRoundRetailate += 1;
                intPlayer1Defects = 0;
                intPlayer2Defects = 0;

                return 0;
            }

            //Check at round retaliated + threshold to measure if opp wishes to coop again
            if (intRoundRetailate > -1 && n == intRoundRetailate + intGrudgeRound + 1) {

                //Count the number of coop during retaliate round to check opp coop level
                int intPlayer1Coop = 0;
                int intPlayer2Coop = 0;

                for (int intCount = 0; intCount < intGrudgeRound; intCount++) {
                    intPlayer1Coop += oppHistory1[n - 1 - intCount] == 0 ? 1 : 0;
                    intPlayer2Coop += oppHistory2[n - 1 - intCount] == 0 ? 1 : 0;
                    //intPlayer1Coop += oppHistory1[n - 1 - intCount] == 1 ? 1 : 0;
                    //intPlayer2Coop += oppHistory2[n - 1 - intCount] == 1 ? 1 : 0;
                }

                //If both players wish to coop again, start to coop with them
                if (intPlayer1Coop > 1 && intPlayer2Coop > 1 && (oppHistory1[n - 1] + oppHistory2[n - 1]) == 0) {
                    //Hold round where agent coop to show intention to coop again
                    //Count backwards from -2
                    //-2 indicates 1 round where agent coop to reverse effect of retailation
                    //-5 indicates 4 rounds where agent coop to reverse effect
                    intRoundRetailate = -2;

                    intPlayer1Defects = 0;
                    intPlayer2Defects = 0;

                    return 0;
                } else {
                    intRoundRetailate = n;
                    return 1;
                }

            }

            //Punish Defection by defecting straight away
            //Stores the round defected
            if (intPlayer1Defects + intPlayer2Defects > 0) {
                intRoundRetailate = n;
                return 1;
            }

            //Coop as default action
            return 0;
        }
    }


    class PM_Low extends Player {

        int myScore = 0;
        int opp1Score = 0;
        int opp2Score = 0;

        int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

            if (n == 0) {
                return 0; // cooperate by default
            }

            // get the recent history index
            int i = n - 1;

            // add up the total score/points for each player
            myScore += payoff[myHistory[i]][oppHistory1[i]][oppHistory2[i]];
            opp1Score += payoff[oppHistory1[i]][oppHistory2[i]][myHistory[i]];
            opp2Score += payoff[oppHistory2[i]][myHistory[i]][oppHistory1[i]];

            // if my score is lower than the any of them
            // it means that at least one of them have defected
            if (myScore >= opp1Score && myScore >= opp2Score) {

                // cooperate if my score is higher or equal than all of them
                return 0;
            }

            return 1; // defect if my score is lower than any of them
        }
    }

    class Naing_Htet_Player extends Player {
        int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

            // Rule 1: our agent will cooperate in the first round
            if (n == 0)  {
                return 0;
            }

            // Rule 2: our agent will defect in the last few rounds, NastyPlayer mode is turned on
            if (n > 95) {
                return 1;
            }

            // Rule 3: if all players including our agent cooperated in the previous round,
            // then our agent will continue to cooperate
            if (myHistory[n-1] == 0 && oppHistory1[n-1] == 0 && oppHistory2[n-1] == 0) {
                return 0;
            }

            // Rule 4: check opponents history to see if they have defected before
            for (int i = 0; i < n; i++) {
                if (oppHistory1[i] == 1 || oppHistory2[i] == 1) {
                    // if either one of them defected before, our agent will always defect
                    return 1;
                }
            }
            // Rule 5: Otherwise, by default nature, our agent will always cooperate
            return 0;
        }
    }

    class tsKennethTeo_Player extends Player {
    // A Tolerant Tit for Tat player that considers action of both
    // opponents instead of just looking at one.
	// If opponents are not acting in unison, fall back to being an  
	// alternator
	int selectAction(int n, int[] myHistory, int[] oppHistory1,
	        int[] oppHistory2) {

	        // Cooperate on the first two rounds
	        if (n==0 || n==1) {
	            return 0;
	        } 
	        // Defect on the last two rounds
	        else if (n==98||n==99) {
	             return 1;  
	        }
	                
	        // If both opponents are nasty, turn nasty as well. Only give
	        // two chances before defecting
	        if ((oppHistory1[n-1]==1&&oppHistory1[n-2]==1)
	            &&(oppHistory2[n-1]==1&&oppHistory2[n-2]==1)) {
	            return 1;
	        }
	        // If both opponents in synchronisation, possibly Tit for tat, 
	        //   return Tit for tat
	        else if (oppHistory1[n-1] == oppHistory2[n-1] 
	            && oppHistory1[n-2] == oppHistory2[n-2]) {
	            return oppHistory1[n-1];
	        }
	        // Opponents not acting in unision, alternate between 0 and 1
	        else {
	            if(myHistory[n-1] == 1) {
	                return 0;
	            } else {
	                return 1;
	            }
	        }
	    }
	}


	class Chen_Zhiwei_Player extends Player {

	int myScore = 0;
	int opp1Score = 0;
	int opp2Score = 0;

	int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		// First Law: Always cooperate in first 2 rounds
		if (n < 2) return 0;

		// Second Law: Tolerate 2 consecutive defects from both opp
		// If 2 consecutive defects from both opp, then defect
		if (oppHistory1[n-1] == 1 && oppHistory1[n-2] == 1 &&
			oppHistory2[n-1] == 1 && oppHistory2[n-2] == 1)
			return 1;

		// Third Law: if one of the opponents is Nasty, then always defect
		boolean isOpp1Nasty, isOpp2Nasty;
		isOpp1Nasty = isNasty(n, oppHistory1);
		isOpp2Nasty = isNasty(n, oppHistory2);
		if (isOpp1Nasty || isOpp2Nasty) return 1;

		// Fourth Law: if one of the opponents is Random, then always defect
		boolean isOpp1Random, isOpp2Random;
		isOpp1Random = isRandom(n, oppHistory1);
		isOpp2Random = isRandom(n, oppHistory2);
		if (isOpp1Random || isOpp2Random) return 1;

		// Fifth Law: if my current score is lower than one of the opp, then always defect
		myScore += payoff[myHistory[n-1]][oppHistory1[n-1]][oppHistory2[n-1]];
		opp1Score += payoff[oppHistory1[n-1]][oppHistory2[n-1]][myHistory[n-1]];
		opp2Score += payoff[oppHistory2[n-1]][oppHistory1[n-1]][myHistory[n-1]];
		if (myScore < opp1Score || myScore < opp2Score) return 1;

		// Sixth Law: If above laws don't apply, then be a T4TPlayer
		if (Math.random() < 0.5) return oppHistory1[n-1];
		else return oppHistory2[n-1];
	}

	boolean isNasty(int n, int[] oppHistory) {
		int cnt = 0;
		for (int i=0; i<n; i++){
			if (oppHistory[i] == 1)
				cnt++;
		}

		if (cnt == n) return true;
		else return false;
	}

	boolean isRandom(int n, int[] oppHistory) {
		int sum = 0;
		double eps = 0.025;
		for (int i=0; i<n; i++) {
			sum += oppHistory[i];
		}

		// if ratio is roughly 0.5, then the opponent is highly likely to be random
		double ratio = (double) sum / n;
		if (Math.abs(ratio - 0.5) < eps) return true;
		else return false;
	}
}


	class Mundhra_Shreyas_Sudhir_Player extends Player {
	
	
	private int opp1Defects = 0;
	private int opp2Defects = 0;
	
	int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		// cooperate if this is the first round
		if (n == 0)
			return 0;

		else {
			// find how many times each opponent has defected in the past
			opp1Defects += oppHistory1[n - 1];
			opp2Defects += oppHistory2[n - 1];

			// cooperate if both opponents have mostly cooperated
			if (opp1Defects <= n / 2 && opp2Defects <= n / 2)
				return 0;

			// defect if both opponents have mostly defected
			if (opp1Defects > n / 2 && opp2Defects > n / 2)
				return 1;

			// one opponent has mostly cooperated and another has mostly defected
			else {
				// find scores upto the current round
				float[] scores = calculateScores(myHistory, oppHistory1, oppHistory2);

				// if my agent does not have the least score, use simple majority strategy
				if (scores[1] < scores[0] || scores[2] < scores[0]) {
					return switchToSimpleMajority(n, myHistory, oppHistory1, oppHistory2);
				}
				
				// if my agent has the least score
				else {
					float[][] probDists = new float[2][2];

					// find probability of each action for each opponent
					probDists[0] = findProbabilityDist(oppHistory1);
					probDists[1] = findProbabilityDist(oppHistory2);

					// find expected utility for cooperating and defecting
					float coopUtil = findExpectedUtility(0, probDists);
					float defectUtil = findExpectedUtility(1, probDists);

					// choose action having higher expected utility
					if (coopUtil > defectUtil)
						return 0;

					return 1;
				}
			}
		}
	}
	
	// simple majority strategy
	int switchToSimpleMajority(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		int opponentCoop1 = 0, opponentCoop2 = 0;
		int predAction1, predAction2;

		// find how many times each opponent has cooperated
		for (int i = 0; i < n; i++) {
			if (oppHistory1[i] == 0) {
				opponentCoop1 += 1;
			}
			if (oppHistory2[i] == 0) {
				opponentCoop2 += 1;
			}
		}

		// predict action of opponent 1 that it as performed most of the time
		if (opponentCoop1 > n / 2)
			predAction1 = 0;
		else
			predAction1 = 1;

		// predict action of opponent 2 that it as performed most of the time
		if (opponentCoop2 > n / 2)
			predAction2 = 0;
		else
			predAction2 = 1;

		// choose action that maximizes the payoff for the predicted actions
		if (payoff[0][predAction1][predAction2] > payoff[1][predAction1][predAction2])
			return 0;

		return 1;
	}

	// calculate scores of all the players
	float[] calculateScores(int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		int rounds = myHistory.length;
		float ScoreA = 0, ScoreB = 0, ScoreC = 0;

		for (int i = 0; i < rounds; i++) {
			ScoreA = ScoreA + payoff[myHistory[i]][oppHistory1[i]][oppHistory2[i]];
			ScoreB = ScoreB + payoff[oppHistory1[i]][oppHistory2[i]][myHistory[i]];
			ScoreC = ScoreC + payoff[oppHistory2[i]][myHistory[i]][oppHistory1[i]];
		}

		float[] result = { ScoreA / rounds, ScoreB / rounds, ScoreC / rounds };
		return result;
	}
	
	// find probability distribution of the actions for a given opponent
	float[] findProbabilityDist(int[] history) {
		float[] probDist = new float[2];

		// count the number of times the opponent in question has cooperated or defected
		for (int i = 0; i < history.length; i++) {
			probDist[history[i]]++;
		}

		// find probability that the opponent in question will cooperate or defect
		probDist[0] = probDist[0] / history.length;
		probDist[1] = probDist[1] / history.length;

		return probDist;
	}

	// find expected utility if the agent performs a certain action
	float findExpectedUtility(int action, float[][] probDists) {
		float expectedUtility = 0;

		for (int j = 0; j < 2; j++) {
			for (int k = 0; k < 2; k++) {
				expectedUtility += probDists[0][j] * probDists[1][k] * payoff[action][j][k];
			}
		}

		return expectedUtility;
	}
}


	






	
	/* In our tournament, each pair of strategies will play one match against each other. 
	 This procedure simulates a single match and returns the scores. */
	float[] scoresOfMatch(Player A, Player B, Player C, int rounds) {
		int[] HistoryA = new int[0], HistoryB = new int[0], HistoryC = new int[0];
		float ScoreA = 0, ScoreB = 0, ScoreC = 0;
		
		for (int i=0; i<rounds; i++) {
			int PlayA = A.selectAction(i, HistoryA, HistoryB, HistoryC);
			int PlayB = B.selectAction(i, HistoryB, HistoryC, HistoryA);
			int PlayC = C.selectAction(i, HistoryC, HistoryA, HistoryB);
			ScoreA = ScoreA + payoff[PlayA][PlayB][PlayC];
			ScoreB = ScoreB + payoff[PlayB][PlayC][PlayA];
			ScoreC = ScoreC + payoff[PlayC][PlayA][PlayB];
			HistoryA = extendIntArray(HistoryA, PlayA);
			HistoryB = extendIntArray(HistoryB, PlayB);
			HistoryC = extendIntArray(HistoryC, PlayC);
		}
		float[] result = {ScoreA/rounds, ScoreB/rounds, ScoreC/rounds};
		return result;
	}
	
//	This is a helper function needed by scoresOfMatch.
	int[] extendIntArray(int[] arr, int next) {
		int[] result = new int[arr.length+1];
		for (int i=0; i<arr.length; i++) {
			result[i] = arr[i];
		}
		result[result.length-1] = next;
		return result;
	}
	
	/* The procedure makePlayer is used to reset each of the Players 
	 (strategies) in between matches. When you add your own strategy,
	 you will need to add a new entry to makePlayer, and change numPlayers.*/
	
	int numPlayers = 138;
	Player makePlayer(int which) {
		switch (which) {
		case 0: return new NicePlayer();
		case 1: return new NastyPlayer();
		case 2: return new T4TPlayer();
/*		case 4: return new TolerantPlayer();*/
		case 3: return new FreakyPlayer();
		
		case 5: return new UpToU1();
/*		case 6: return new T4TPlayer_1stD();*/
		case 7: return new T42TPlayer();
		case 8: return new Joss();
		case 9: return new T4TPlayer_both();
		case 10: return new T4TPlayer_either();
		/*case 11: return new TolerantPlayer2();*/
/*		case 12: return new TolerantPlayer3();*/
/*		case 13: return new TolerantPlayer4();*/
/*		case 14: return new TolerantPlayer_discount1();*/
		case 15: return new TolerantPlayer_discount2();
		case 16: return new ForgivingJoss();
		case 17: return new T43TPlayer();
		/*case 18: return new T4_3TPlayer_accumulate();*/
		/*case 19: return new TolerantPlayer2_discount1();*/
		/*case 20: return new TolerantPlayer3_discount1();*/
/*		case 21: return new TolerantPlayer2_discount2();*/
		case 22: return new TolerantPlayer3_discount2();
/*		case 23: return new TolerantPlayer4_discount1();*/
		case 24: return new TolerantPlayer4_discount2();
		case 25: return new UpToU2();
		case 26: return new TolerantPlayer_individual_either_Bboth();
		
		
		
		/*case 27: return new TolerantPlayer_individual_either();*/
/*		case 28: return new TolerantPlayer_individual_both();*/
/*		case 29: return new TolerantPlayer_individual_either_discount1();*/
		case 30: return new TolerantPlayer_individual_either_discount2();
/*		case 31: return new TolerantPlayer_individual_both_discount1();*/
		case 32: return new TolerantPlayer_individual_both_discount2();
		case 33: return new T4_10TPlayer_accumulate();
		case 34: return new T4_3in10TPlayer_accumulate();
		case 35: return new Adaptive();
		case 36: return new Alternating1();
		case 37: return new TrickyPlayer();
		case 38: return new OneOff();
		case 39: return new TolerantPlayer2_Bboth();
		case 40: return new TolerantPlayer2_Beither();
		case 41: return new TolerantPlayer3_Beither();
		case 42: return new TolerantPlayer3_Bboth();
		case 43: return new TolerantPlayer4_Beither();
		case 44: return new TolerantPlayer4_Bboth();
		case 45: return new TolerantPlayer_Bboth();
		case 46: return new TolerantPlayer_Beither();
		case 47: return new TolerantPlayer_individual_both_discount1_Bboth();
		case 48: return new TolerantPlayer_individual_both_discount1_Beither();
		case 49: return new TolerantPlayer_individual_both_discount2_Beither();
		case 50: return new TolerantPlayer_individual_both_discount2_Bboth();
		case 51: return new TolerantPlayer_individual_either_discount2_Bboth();
		case 52: return new TolerantPlayer_individual_either_discount2_Beither();
		case 53: return new TolerantPlayer_individual_either_discount1_Bboth();
		case 54: return new TolerantPlayer_individual_either_discount1_Beither();
		case 55: return new TolerantPlayer_individual_both_Bboth();
		case 56: return new TolerantPlayer_individual_both_Beither();
		case 57: return new UpToU1_forgiving();
		case 58: return new TolerantPlayer_individual_either_Beither();
		// case 25: return new RandomPlayer2();
		// case 2: return new RandomPlayer();
		// case 26: return new RandomPlayer3();
		
		case 59: return new UpToU1_discount1();
		case 60: return new UpToU1_discount2();
		case 61: return new UpToU1_forgiving3_41();
		case 62: return new UpToU1_forgiving3_42();
		case 63: return new UpToU1_discount3();
		case 64: return new T44TPlayer();
		case 65: return new T4TPlayer_both_and_TolerantPlayer_discount2();
		case 66: return new T4TPlayer_both_and_UpToU1();
		case 67: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver2();
		case 68: return new T4TPlayer_both_and_UpToU1_ver2();
		case 69: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver3();
		case 70: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver4();
		case 71: return new T4TPlayer_both_and_TolerantPlayer3_10();
		case 72: return new T4TPlayer_both_and_TolerantPlayer4_discount2();
		case 73: return new TolerantPlayer3_and_T4TPlayer_both();
		case 74: return new T43TPlayer_and_TolerantPlayer_Bboth();
		case 75: return new T44TPlayer_and_TolerantPlayer3_Bboth_50();
		case 76: return new T44TPlayer_and_TolerantPlayer3_Bboth_40();
		case 77: return new T44TPlayer_and_TolerantPlayer3_Bboth_30();
		case 78: return new T44TPlayer_and_TolerantPlayer3_Bboth_20();
/*		case 79: return new T43TPlayer_and_TolerantPlayer3_10();*/
		case 80: return new T4TPlayer_both_and_TolerantPlayer3_20();
		case 81: return new T4TPlayer_both_and_TolerantPlayer3_30();
		case 82: return new T4TPlayer_both_and_TolerantPlayer3_40();
		case 83: return new T4TPlayer_both_and_TolerantPlayer3_50();
/*		case 84: return new T4TPlayer_both_and_TolerantPlayer3_60();*/
		case 85: return new T4TPlayer_both_and_TolerantPlayer_individual_both_40();
		case 86: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_20();
		case 87: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_30();
		case 88: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_40();
		case 89: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_50();
/*		case 90: return new T43TPlayer_and_TolerantPlayer3_50();*/
/*		case 91: return new T43TPlayer_and_TolerantPlayer3_20();*/
/*		case 92: return new T43TPlayer_and_TolerantPlayer3_30();*/
/*		case 93: return new T43TPlayer_and_TolerantPlayer3_40();*/
/*		case 94: return new T43TPlayer_and_TolerantPlayer_10();*/
/*		case 95: return new T43TPlayer_and_TolerantPlayer_20();*/
/*		case 96: return new T43TPlayer_and_TolerantPlayer_30();*/
		case 97: return new Alternating2();
		case 98: return new GosuTheMinion();
		case 99: return new Bummer();
		case 100: return new PM_Low();
		case 101: return new Naing_Htet_Player();
		case 102: return new Chen_Zhiwei_Player();
		case 103: return new Mundhra_Shreyas_Sudhir_Player();
		case 104: return new T4TPlayer_both_and_TolerantPlayer_discount2_nasty();
		case 105: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver3_nasty();
		case 106: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_40_nasty();
		case 107: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver4_nasty();
		case 108: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_30_nasty();
		case 109: return new T4TPlayer_both_and_TolerantPlayer3_Bboth_50_nasty();
		case 110: return new T4TPlayer_both_and_TolerantPlayer3_20_nasty();
		case 111: return new T4TPlayer_both_and_TolerantPlayer_discount2_ver2_nasty();
		case 112: return new T4TPlayer_both_nasty();
		case 113: return new tsKennethTeo_Player();
		case 114: return new T44TPlayer_deter();
		case 115: return new T45TPlayer();
		case 116: return new TrickyPlayer2();
		case 117: return new TrickyPlayer3();
		case 118: return new TrickyPlayer4();
		case 119: return new T4TPlayer_both_and_TrickyPlayer3_nasty_20();
		case 120: return new T4TPlayer_both_and_TrickyPlayer3_nasty_30();
		case 121: return new T4TPlayer_both_and_TrickyPlayer3_nasty_40();
		case 122: return new T4TPlayer_both_and_TrickyPlayer3_nasty_50();
		case 123: return new TrickyPlayer3_nasty();
		case 124: return new TrickyPlayer3_and_T4TPlayer_both_nasty_20();
		case 125: return new TrickyPlayer3_and_T4TPlayer_both_nasty_30();
		case 126: return new TrickyPlayer3_and_T4TPlayer_both_nasty_40();
		case 127: return new TrickyPlayer3_and_T4TPlayer_both_nasty_50();
		case 128: return new TrickyPlayer3_biased();
		case 129: return new TrickyPlayer4_biased();
		case 130: return new T4TPlayer_both_and_TrickyPlayer3_nasty_20_biased();
		case 131: return new T4TPlayer_both_and_TrickyPlayer3_nasty_30_biased();
		case 132: return new T4TPlayer_both_and_TrickyPlayer3_nasty_40_biased();
		case 133: return new T4TPlayer_both_and_TrickyPlayer3_nasty_50_biased();
		case 134: return new TrickyPlayer3_nasty_biased();
		case 135: return new TrickyPlayer3_and_T4TPlayer_both_nasty_20_biased();
		case 136: return new TrickyPlayer3_and_T4TPlayer_both_nasty_30_biased();
		case 137: return new TrickyPlayer3_and_T4TPlayer_both_nasty_40_biased();
		case 13: return new TrickyPlayer3_and_T4TPlayer_both_nasty_50_biased();
		case 31: return new TrickyPlayer3_biased2();
		case 95: return new TrickyPlayer4_biased2();
		case 79: return new T4TPlayer_both_and_TrickyPlayer3_nasty_20_biased2();
		case 94: return new T4TPlayer_both_and_TrickyPlayer3_nasty_30_biased2();
		case 96: return new T4TPlayer_both_and_TrickyPlayer3_nasty_40_biased2();
		case 6: return new T4TPlayer_both_and_TrickyPlayer3_nasty_50_biased2();
		case 84: return new TrickyPlayer3_nasty_biased2();
		case 28: return new TrickyPlayer3_and_T4TPlayer_both_nasty_20_biased2();
		case 91: return new TrickyPlayer3_and_T4TPlayer_both_nasty_30_biased2();
		case 23: return new TrickyPlayer3_and_T4TPlayer_both_nasty_40_biased2();
		case 14: return new TrickyPlayer3_and_T4TPlayer_both_nasty_50_biased2();
		case 93: return new TrickyPlayer31();
		case 92: return new TrickyPlayer31_biased();
/*		case 152: return new TrickyPlayer41();*/
		case 4: return new T4TPlayer_both_and_TrickyPlayer3_nasty_60_biased();
		case 90: return new T4TPlayer_both_and_TrickyPlayer3_nasty_60_biased2();
		case 18: return new T4TPlayer_both_and_TrickyPlayer3_nasty_70_biased();
		case 20: return new T4TPlayer_both_and_TrickyPlayer3_nasty_70_biased2();
		case 12: return new T4TPlayer_both_and_TrickyPlayer3_nasty_15_biased();
		case 21: return new T4TPlayer_both_and_TrickyPlayer3_nasty_15_biased2();
		case 27: return new T4TPlayer_both_and_TrickyPlayer3_nasty_65_biased();
		case 29: return new T4TPlayer_both_and_TrickyPlayer3_nasty_65_biased2();
		case 11: return new T4TPlayer_both_and_TrickyPlayer3_nasty_10_biased();
		case 19: return new T4TPlayer_both_and_TrickyPlayer3_nasty_10_biased2();
		}
		throw new RuntimeException("Bad argument passed to makePlayer");
	}
	
	/* Finally, the remaining code actually runs the tournament. */
	
	public static void main (String[] args) {
		ThreePrisonersDilemma instance = new ThreePrisonersDilemma();
		instance.runTournament();
	}
	
	boolean verbose = true; // set verbose = false if you get too much text output
	
	void runTournament() {
		float[] totalScore = new float[numPlayers];

		// This loop plays each triple of players against each other.
		// Note that we include duplicates: two copies of your strategy will play once
		// against each other strategy, and three copies of your strategy will play once.

		for (int i=0; i<numPlayers; i++) for (int j=i; j<numPlayers; j++) for (int k=j; k<numPlayers; k++) {

			Player A = makePlayer(i); // Create a fresh copy of each player
			Player B = makePlayer(j);
			Player C = makePlayer(k);
			int rounds = 90 + (int)Math.rint(20 * Math.random()); // Between 90 and 110 rounds
			float[] matchResults = scoresOfMatch(A, B, C, rounds); // Run match
			totalScore[i] = totalScore[i] + matchResults[0];
			totalScore[j] = totalScore[j] + matchResults[1];
			totalScore[k] = totalScore[k] + matchResults[2];
			if (verbose)
				System.out.println(A.name() + " scored " + matchResults[0] +
						" points, " + B.name() + " scored " + matchResults[1] + 
						" points, and " + C.name() + " scored " + matchResults[2] + " points.");
		}
		int[] sortedOrder = new int[numPlayers];
		// This loop sorts the players by their score.
		for (int i=0; i<numPlayers; i++) {
			int j=i-1;
			for (; j>=0; j--) {
				if (totalScore[i] > totalScore[sortedOrder[j]]) 
					sortedOrder[j+1] = sortedOrder[j];
				else break;
			}
			sortedOrder[j+1] = i;
		}
		
		// Finally, print out the sorted results.
		if (verbose) System.out.println();
		System.out.println("Tournament Results");
		for (int i=0; i<numPlayers; i++) 
			System.out.println(makePlayer(sortedOrder[i]).name() + ": " 
				+ totalScore[sortedOrder[i]] + " points.");
		
	} // end of runTournament()
	
} // end of class PrisonersDilemma

