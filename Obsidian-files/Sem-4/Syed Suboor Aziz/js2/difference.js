function TimeDifference(date1, date2) {
  const diffInMs = Math.abs(date2 - date1);

  const days = Math.floor(diffInMs / (1000 * 60 * 60 * 24));
  const hours = Math.floor(
    (diffInMs % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
  );

  return { days, hours };
}

const date1 = new Date("2024-01-01T00:00:00");
const date2 = new Date("2024-01-05T12:34:56");

const detailedDifference = TimeDifference(date1, date2);

console.log(
  `Difference is ${detailedDifference.days} days, ${detailedDifference.hours} hours.`
);
