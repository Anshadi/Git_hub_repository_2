let count = 0;
const intervalId = setInterval(updateClock, 1000);

function updateClock() {
  const clockElement = document.getElementById("clock");
  const now = new Date();
  clockElement.innerText = now.toLocaleTimeString();

  count += 1;
  if (count === 5) {
    alert("Game over");
    clearInterval(intervalId);
  }
}

updateClock();
