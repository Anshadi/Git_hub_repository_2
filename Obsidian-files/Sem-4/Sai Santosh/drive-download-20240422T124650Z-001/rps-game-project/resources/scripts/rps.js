let userMove = '';
let computerMove = '';
let result = '';
const game = {
  win: 0,
  loose: 0,
  tie:0
};
const gameHistory = [];


function generateComputerMove() {
  const randNum = Math.random();
  if (randNum < 1/3) {
    computerMove = 'Rock';
  } else if (randNum < 2/3) {
    computerMove = 'Paper';
  } else{
    computerMove = 'Scissors';
  }
}

function evaluateMoves(userMove,computerMove) {
  if (userMove === computerMove) {
    result = 'Tie';
    game.tie += 1;
  } else if ((userMove === 'Rock' && computerMove === 'Scissors') ||
  (userMove === 'Paper' && computerMove === 'Rock') ||
  (userMove === 'Scissors' && computerMove === 'Paper')) {
    result = 'Win';
    game.win += 1;
  } else{
    result = 'Loose';
    game.loose += 1;
  }
  gameHistory.push({
    userMove: userMove,
    computerMove: computerMove,
    result: result
  });
}
function renderGameSummary(){
  document.getElementById('wins').innerHTML = game.win;
  document.getElementById('looses').innerHTML = game.loose;
  document.querySelector('#ties').innerHTML = game.tie;
  document.querySelector('#gamesPlayed').innerHTML = game.win + game.tie + game.loose;
}

function renderGameHistory(){
    let gameHistoryHTML =  `<tr>
    <th>#</th>
    <th>User Move</th>
    <th>Computer Move</th>
    <th>Result</th>
  </tr>`;
    for(let i = 0; i < gameHistory.length; i++){
      const gameItem = gameHistory[i];
      gameHistoryHTML += 
      `<tr>
        <td>${i+1}</td>
        <td>${gameItem.userMove}</td>
        <td>${gameItem.computerMove}</td>
        <td>${gameItem.result}</td>
      </tr>`
      }
      
  document.getElementById('gameHistory').innerHTML = gameHistoryHTML;
}
