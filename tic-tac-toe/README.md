# tic-tac-toe

In the Tic-Tac-Toe game, you will see the approach of the game is implemented. In this game, two players will be played and you have one print board on the screen where from 1 to 9 number will be displayed or you can say it box number. Now, you have to choose X or O for the specific box number. For example, if you have to select any number then for X or O will be shown on the print board, and turn for next will be there. The task is to create a Java program to implement a 3×3 Tic-Tac-Toe game for two players.

**Game board :**

```
      |---|---|---|        
      | 1 | 2 | 3 |
      |-----------|
      | 4 | 5 | 6 |
      |-----------|
      | 7 | 8 | 9 |
      |---|---|---|  
```

**Sample Input :**

Enter a slot number to place X in: 3

**Sample Output :**

```
      |---|---|---|        
      | 1 | 2 | X |
      |-----------|
      | 4 | 5 | 6 |
      |-----------|
      | 7 | 8 | 9 |
      |---|---|---|  
```

**Sample Input :**

Now, O’s turn, Enter a slot number to place O in: 5

**Sample Output :**

```
      |---|---|---|        
      | 1 | 2 | X |
      |-----------|
      | 4 | O | 6 |
      |-----------|
      | 7 | 8 | 9 |
      |---|---|---|  

```

### How to Play the Game :

* Both the players choose either X or O to mark their cells. 
* There will be a 3×3 grid with numbers assigned to each of the 9 cells.
* The player who chose X begins to play first.
* He enters the cell number where he wishes to place X.
* Now, both O and X play alternatively until any one of the two wins.
* Winning criteria: Whenever any of the two players has fully filled one row/ column/ diagonal with his symbol (X/ O), he wins and the game ends.
* If neither of the two players wins, the game is said to have ended in a draw.