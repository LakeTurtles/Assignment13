

class UI {
    constructor() {
    }
    hidePreloader() {
        document.querySelector('.preloader').style.display = "none";
    }

    showNav(){
    // document.querySelector('.nav').classList.add('nav--show');
    // document.querySelector('.nav').classList.hide('nav--show');
    document.querySelector('.nav').classList.toggle('nav--show');
    document.querySelector('.preloader').classList.toggle('toggle_preloader');
    }

    videoControls(){
        let videoButton = document.querySelector('.video__switch-btn');
        if(!videoButton.classList.contains('btnSlide')){
            videoButton.classList.add('btnSlide');
            document.querySelector('.video__item').pause();
        }
        else {
            videoButton.classList.remove('btnSlide');
            document.querySelector('.video__item').play();
        }
    }
  
   
}



//  window event listener 

eventListeners();
function eventListeners(){

    const ui = new UI();

window.addEventListener('load', function(){
    ui.hidePreloader();
});



document.querySelector('.navBtn').addEventListener('click', function (){
    ui.showNav();
});



  // control the video
    document.querySelector('.video__switch').addEventListener('click', function(){
        ui.videoControls();
 })

}






