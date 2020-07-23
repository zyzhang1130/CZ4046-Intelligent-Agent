	class Zhang_Zeyu_Player extends Player {
		//hybrid strategy
		//composed from varients of Tic-for-Tac, Tolerant and Nasty Player
		int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {

			//when this history length is within 10, play as Tic-for-Tac-both 
			//(only if both opponents detected for the last round then defect 
			//for this round) cooperate by default for the first round
			if (n<10) {		  
				if (n==0) return 0;
				if (oppHistory1[n-1] == 1 && oppHistory2[n-1] == 1) {
					return 1;
				}
				else {
					return 0;
				}
			}

			//when this history length larger than 95, play as Nastyplayer
			if (n>95) {
				return 1;
			}

			//when this history length larger than 10 and smaller or equal to 95,
			//play as TolerantPlayer with discount factor (value more recent 
			//opponents' decisions) to compute the ratio of the weighted defection 
			//history over the weighted cooperation history. Discount factor is 
			//chosen to be 0.9.
			else  {	 
			 	double opponentCoop = 0;
				double opponentDefect = 0;
				for (int i=0; i<n; i++) {
					if (oppHistory1[i] == 0) {
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					}
					else {
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
					}
				}
				
				for (int i=0; i<n; i++) {
					if (oppHistory2[i] == 0) {
						opponentCoop = opponentCoop + Math.pow(0.9, (n-i));
					}
					else {
						opponentDefect = opponentDefect + Math.pow(0.9, (n-i));
					}
				}

				//If the weighted defection history
				//is more than the weighted cooperation 
				//history, then defect for this round.
				//Otherwise cooperate.
				if (opponentDefect > opponentCoop) {
					return 1;					
				}
				else {
					return 0;					
				}
			}
		}	
	}