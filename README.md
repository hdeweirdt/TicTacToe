# TicTacToe

An implementation of the all-known Tic-Tac-Toe game (aka 3-in-a-row).

## Functional Requirements
- The user starts the first game as “X”, the computer as “O”. After every game they switch sides (e.g. the user will play as “O” in the second game, and as “X” again in the third
game).
- The game stops whenever one of the players reaches the goal of having a 3-in-a-row (win/loss), or whenever all places are occupied without having a 3-in-a-row (draw).
- The user can play by simply clicking on an empty place during her/his turn.
- There should be a clear indication whose turn it is: the user’s or the computer’s.
- The user should be able to switch (at any time) between 2 levels of computer AI: normal and hard.

## Still to-do

- ~~Ignore user input when AI is calculating next move.~~
- ~~Refactor AI implementation to a depth-limited version of minimax, and let changing difficulty change the allowed depth of the search.~~
- Implement memoization for the BoardEvaluator: cache scores of previously seen boards, rotations of the board, or equivalents of the board but with the symbols switched.
- ~~BUG: Sometimes the coordination between UI and VM is out of sync, current implementation might not be perfect yet.~~
- Controller and ViewModel could use some more tests to ensure everything works as expected.
- Implement saveStateHandle in VM to survice process destruction by OS
