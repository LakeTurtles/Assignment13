
const time = document.getElementById('time');
const greeting = document.getElementById('greeting');
const name = document.getElementById('name');
const focus = document.getElementById('focus');

function showTime () {
    let today = new Date(),
    hour = today.getHours(),
    min = today.getMinutes(),
    sec = today.getSeconds();

    const amPm = hour >= 12? 'PM' : 'AM';

    //12 hour format
    hour = hour % 12 || 12;

    // output the time

    time.innerHTML = `${hour}<span>:</span>${addZero(min)}<span>:</span>${addZero(sec)}`;

    setTimeout(showTime, 1000);
}

// add zero to clock
 function addZero(n) {
     return (parseInt(n, 10) < 10 ? '0' : '') + n;
 }

 // set background and greeting

 function setBgGreet () {
     let today = new Date(),
     hour = today.getHours();

     if (hour < 12) {
         document.body.style.backgroundImage = "url('img/large.jpg')";
         greeting.textContent = 'Good Morning';
     } else if (hour < 18) {
         document.body.style.backgroundImage = "url('img/large.jpg')";
         greeting.textContent = 'Good Afternoon';
     } else  {
         document.body.style.backgroundImage = "url('img/homepage.png')" ;
         greeting.textContent = 'Good Night';
         document.body.style.color = 'silver';
     }
 }

 // get name
 function getName() {
     if (localStorage.getItem('name') === null) {
         name.textContent = '[Enter Name]';
     } else {
         name.textContent = localStorage.getItem('name');
     }
 }

 //set name
 function setName(e) {
     if (e.type === 'keypress') {
         // make sure enter is pressed
         if (e.which == 13 || e.keyCode == 13) {
            localStorage.setItem('name', e.target.innerText);
            name.blur();
         }

     }else {
         localStorage.setItem('name', e.target.innerText);
     }
 }

  //set focus
  function setFocus(e) {
    if (e.type === 'keypress') {
        // make sure enter is pressed
        if (e.which == 13 || e.keyCode == 13) {
           localStorage.setItem('focus', e.target.innerText);
           focus.blur();
        }

    }else {
        localStorage.setItem('focus', e.target.innerText);
    }
}

 // get focus
 function getFocus() {
    if (localStorage.getItem('focus') === null) {
        focus.textContent = '[Enter Focus]';
    } else {
        focus.textContent = localStorage.getItem('focus');
    }
}

name.addEventListener('keypress', setName);
name.addEventListener('blur', setName);

focus.addEventListener('keypress', setFocus);
focus.addEventListener('blur', setFocus);

// run

showTime();
setBgGreet();
getName();
getFocus();
