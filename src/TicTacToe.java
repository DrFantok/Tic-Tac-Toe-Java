import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
	public static void main(String[] args) {
		RunPlayer Start = new RunPlayer();
		Start.Player();
	}
}
class PlayerAndBot {
	void RandomBot() {
		int Randomly = RunPlayer.randomization.nextInt(3);
		int Randomly2 = RunPlayer.randomization.nextInt(3);
		while (RunPlayer.Table[Randomly][Randomly2] != 'E') {
			Randomly = RunPlayer.randomization.nextInt(3);
			Randomly2 = RunPlayer.randomization.nextInt(3);
		}
		RunPlayer.Table[Randomly][Randomly2] = RunPlayer.BotChar;
	}
	void Player() {
		while (true) {
			int gaps = 0;
			String playerInput = RunPlayer.userInput.nextLine();
			for (int counting = 0; counting < playerInput.length(); counting++) {
				if (playerInput.charAt(counting) == ' ') {
					gaps++;
				}
				else {
					break;
				}
			}
			int gaps2 = gaps;
			if (playerInput.charAt(gaps) > 48 && playerInput.charAt(gaps) < 52 && gaps++ < playerInput.length()) {
				for (int count = gaps; count < playerInput.length(); count++) {
					if (playerInput.charAt(count) == ' ') {
						gaps++;
					}
					else {
						break;
					}
				}
				if (gaps < playerInput.length()) {
					if (playerInput.charAt(gaps) == ',') {
						gaps++;
						for (int count = gaps; count < playerInput.length(); count++) {
							if (playerInput.charAt(count) == ' ') {
								gaps++;
							}
							else {
								break;
							}
						}
						if (playerInput.charAt(gaps) > 48 && playerInput.charAt(gaps) < 52) {
							if (RunPlayer.Table[playerInput.charAt(gaps) - 49][playerInput.charAt(gaps2) - 49] == 'E') {
								RunPlayer.Table[playerInput.charAt(gaps) - 49][playerInput.charAt(gaps2) - 49] = RunPlayer.PlayerChar;
								break;
							}
							else {
								System.out.println("The coordinate has been already taken, please try again");
							}
						}
					}
				}
				else {
					if (RunPlayer.Table[playerInput.charAt(gaps) - 49][playerInput.charAt(gaps2) - 49] == 'E') {
						RunPlayer.Table[playerInput.charAt(gaps) - 49][playerInput.charAt(gaps2) - 49] = RunPlayer.PlayerChar;
						break;
					}
					else {
						System.out.println("The coordinate has been already taken, please try again");
					}
				}
			}
		}
	}
}
class RunPlayer {
	static int notOnPlayAgain = 0;
	static char[][] Table = new char[3][3];
	static char PlayerChar;
	static char BotChar;
	PlayerAndBot playerAndBot = new PlayerAndBot();
	static Random randomization = new Random();
	static Scanner userInput = new Scanner(System.in);
	void Player() {
		if (notOnPlayAgain == 0) {
			System.out.println("Would you like to play tic-tac-toe?");
			while (true) {
				String StartingInput = userInput.nextLine();
				if (StartingInput.equals("Yes")) {
					for (int Counting = 0; Counting < 3; Counting++) {
						for (int Counting2 = 0; Counting2 < 3; Counting2++) {
							RunPlayer.Table[Counting][Counting2] = 'E';
						}
					}
					break;
				}
				if (StartingInput.equals("No")) {
					return;
				}
				if (!StartingInput.equals("Yes") && !StartingInput.equals("No")) {
					System.out.println("Please enter either Yes or No");
				}
			}
		}
		else {
			for (int count = 0; count < 3; count++) {
				for (int count2 = 0; count2 < 3; count2++) {
					Table[count][count2] = 'E';
				}
			}
		}
		System.out.println("Would you like to be X or O");
		String XorO = userInput.nextLine();
		while (!XorO.equals("X") && !XorO.equals("O")) {
			System.out.println("Please put X or O");
			XorO = userInput.nextLine();
		}
		if (XorO.equals("X")) {
			System.out.println("You will be X and the bot will be O");
			PlayerChar = 'X';
			BotChar = 'O';
		}
		if (XorO.equals("O")) {
			System.out.println("You will be O and the bot will be X");
			PlayerChar = 'O';
			BotChar = 'X';
		}
		System.out.println("Randomly deciding who will be the one to make the first move will be...");
		int RandomStarter = randomization.nextInt(2);
		while (true) {
			if (RandomStarter == 0) {
				System.out.println("You were choosen to be the one to make the first move");
			}
			if (RandomStarter == 1) {
				System.out.println("The bot was choosen to be the one to make the first move");	
			}
			while (true) {
				if (RandomStarter == 0) {
					playerAndBot.Player();
					ShowTable();
					RandomStarter = 1;
					System.out.println("--------------------");
				}
				RunPlayer.checkWinLoseTie();
				if (RandomStarter == 1) {
						playerAndBot.RandomBot();
						ShowTable();
						RandomStarter = 0;
						System.out.println("--------------------");
				}
				RunPlayer.checkWinLoseTie();
			}
		}
	}
	void ShowTable() {
		for (int Counting = 0; Counting < 3; Counting++)
        {
            for (int Counting2 = 0; Counting2 < 3; Counting2++)
            {
            	if (Counting == 0) {
	            	if (Counting2 == 2) {
	            		System.out.println(Table[Counting + 2][Counting2]);
	            	}
	            	else {
	            		System.out.print(Table[Counting + 2][Counting2]);
	            	}
            	}
            	if (Counting == 2) {
            		if (Counting2 == 2) {
	            		System.out.println(Table[Counting - 2][Counting2]);
	            	}
	            	else {
	            		System.out.print(Table[Counting - 2][Counting2]);
	            	}
            	}
            	if (Counting == 1) {
            		if (Counting2 == 2) {
	            		System.out.println(Table[Counting][Counting2]);
	            	}
	            	else {
	            		System.out.print(Table[Counting][Counting2]);
	            	}
            	}
            } 
        }
	}
	static boolean checkTiePossibility() {
		for (int count = 0; count < 3; count++) {
			for (int count2 = 0; count2 < 3; count2++) {
				if (Table[count][count2] == 'E') {
					return false;
				}
			}
		}
		return true;
	}
	static void checkWinLoseTie() {
		int tie = 0;
		if (Table[0][0] == PlayerChar && Table[0][1] == PlayerChar && Table[0][2] == PlayerChar || Table[1][0] == PlayerChar && Table[1][1] == PlayerChar && Table[1][2] == PlayerChar || Table[2][0] == PlayerChar && Table[2][1] == PlayerChar && Table[2][2] == PlayerChar || Table[0][0] == PlayerChar && Table[1][0] == PlayerChar && Table[2][0] == PlayerChar || Table[0][1] == PlayerChar && Table[1][1] == PlayerChar && Table[2][1] == PlayerChar || Table[0][2] == PlayerChar && Table[1][2] == PlayerChar && Table[2][2] == PlayerChar || Table[0][0] == PlayerChar && Table[1][1] == PlayerChar && Table[2][2] == PlayerChar || Table[0][2] == PlayerChar && Table[1][1] == PlayerChar && Table[2][0] == PlayerChar) {
			System.out.println("You have won, the bot lost");
			tie = 1;
		}
		if (Table[0][0] == BotChar && Table[0][1] == BotChar && Table[0][2] == BotChar || Table[1][0] == BotChar && Table[1][1] == BotChar && Table[1][2] == BotChar || Table[2][0] == BotChar && Table[2][1] == BotChar && Table[2][2] == BotChar || Table[0][0] == BotChar && Table[1][0] == BotChar && Table[2][0] == BotChar || Table[0][1] == BotChar && Table[1][1] == BotChar && Table[2][1] == BotChar || Table[0][2] == BotChar && Table[1][2] == BotChar && Table[2][2] == BotChar || Table[0][0] == BotChar && Table[1][1] == BotChar && Table[2][2] == BotChar || Table[0][2] == BotChar && Table[1][1] == BotChar && Table[2][0] == BotChar) {
			System.out.println("The bot has won, you lost");
			tie = 1;
		}
		if (RunPlayer.checkTiePossibility() && tie == 0) {
			System.out.println("There is a draw");
		}
		if (RunPlayer.checkTiePossibility() || tie == 1) {
			System.out.println("Would you like to play again?");
			while (true) {
				String yesOrNo = RunPlayer.userInput.nextLine();
				if (yesOrNo.equals("Yes")) {
					RunPlayer.notOnPlayAgain = 1;
					RunPlayer game = new RunPlayer();
					game.Player();
				}
				if (yesOrNo.equals("No")) {
					System.exit(0);
				}
				if (!yesOrNo.equals("No") && !yesOrNo.equals("Yes")) {
					System.out.println("Please enter Yes or No");
				}
			}
		}
	}
}