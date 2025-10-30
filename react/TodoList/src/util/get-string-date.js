
export const getStringDate = (targetDate) => {
  let year = targetDate.getFullYear();
  let month = targetDate.getMonth()+1;
  let date = targetDate.getDate();

  if(10>month){
    month = `0${month}`;
  }
  if(10>date){
    date = `0${date}`;
  }
  return `${year}-${month}-${date}`;
}

export default getStringDate;