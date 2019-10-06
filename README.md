# Titan-Humans
This is homeWork for DataStructure lessons. Check more detail on ReadMe.

Ms./Mrs. User,

This document have got some informations about my homework and game.

Please read carefully.

* On first round,If there are more than one people on each tile, they merge into one.  And if there are more than one titan on each tile, they merge into one. Just on first round.


* If a player encounters with a Titan;

		—>  three-meters – if the player score is higher than the titan score, the
			titan is dead, otherwise the player score is decreased by one and the
			player is placed in a random position.

 		—> five-meters – if the player score is higher than the titan score, the
			titan is dead, otherwise the player score is decreased by half and the
			player is placed in a random position.

		—>  15-meters – if the player score is higher than the titan score, the titan
			score is decreased by half (if the titan score is one, then the titan is
			dead) and the player is placed in a random position, otherwise the
			player is dead.


* If a player moves to the obstacle, it remains inactive in its place for the
same turn. If a titan moves to the obstacle, the obstacle is destroyed and
the titan completes its movement.

* 15-meter titans occupy four tiles space and three- and five-meters titans
occupy two tiles space. All players and obstacles occupy one tile space.

* If game stoped suddenly check console who wins or detail errors. If there is an error please report to saidcankiran@icloud.com 


* What’s the mean of colors?
	
	—>White - Obstacle
	
	—>Magenta - Low Level Titan (initial score: 20)
	
	—>Maroon - Med Level Titan (initial score: 50)
	
	—>Green - High Level Titan (initial score: 100)
	
	—>Navy - Low Level User
	
	—>Cyan - Med Level User
	
	—>Red - High Level User

*If you change the game features you can edit config.txt.

* Initial config Settings;
	
	height : 200
	
	width : 200
	
	numberOfPeople : 50
	
	numberOf3mTitan : 30
	
	numberOf5mTitan : 15
	
	numberOf15mTitan : 5
	
	numberOfObstacle : 200
	
	initialScoreOf3mTitan : 20
	
	initialScoreOf5mTitan : 50
	
	initialScoreOf15mTitan : 100

	


