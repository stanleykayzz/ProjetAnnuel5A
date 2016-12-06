/*
**home.js
*/

//on définit un tableau des images que l'on souhaite faire défiler sur la page
background = ["img/home2.png","img/home3.png","img/home4.png","img/home5.png","img/home6.png","img/home-bg.png"]

//une variable i pour compteur
var i = 0;
//appel de la fonction setInterval qui appel la fonction en paramètre toutes les X secondes
setInterval(
  function(){
  //on ajoute des effets de fadeIn et fadeOut comme transition des images
      $(".intro-header").fadeOut(1000,function(){
          $(".intro-header").css("background-image","url(" + background[i] + ")");
          $(".intro-header").fadeIn(1000);
    })
      i = i + 1;
      if (i == background.length) {
        i =  0;
      }
  }, 5000);