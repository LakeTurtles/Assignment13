//get modal element
var modal = document.getElementById('simpleModal');
//get modal button
var modalBtn = document.getElementById('modalBtn');
//get close button, will retrieve an array of all divs with the class name, so specify array postion index
var closeBtn = document.getElementsByClassName('closeBtn')[0];

//listen for open click
modalBtn.addEventListener('click', openModal);
//listen for close click
closeBtn.addEventListener('click', closeModal);
//listen for outside click
window.addEventListener('click', outsideClick);

//function to close modal if outside click
function outsideClick(e) {
  if (e.target == modal) {
    modal.style.display = 'none';
  }
}

//function open Modal
function openModal() {
  console.log('Modal was opened');
  modal.style.display = 'block';
}

function closeModal() {
  console.log('Modal was closed');
  modal.style.display = 'none';
}
