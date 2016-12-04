/*
**restaurant.js
*/

//on définit un tableau des images que l'on souhaite faire défiler sur la page
background = ["img/room1.jpg","img/room2.jpg","img/room3.jpg","img/room4.jpg","img/room5.jpg","img/room6.jpg","img/room7.jpg","img/room8.jpg","img/room9.jpg","img/room10.jpg"]

//une variable i pour compteur
$(document).ready(function(){
  init_room();
});


function init_room(){
   $(".intro-header").fadeOut(100,function(){
      $(".intro-header").css("background-image","url(" + background[0] + ")");
      $(".intro-header").fadeIn(100);
    });

  var i = 1;
  //appel de la fonction setInterval qui appel la fonction en paramètre toutes les X secondes
  var carousel = function(){
    setTimeout( function(){
    //on ajoute des effets de fadeIn et fadeOut comme transition des images
      $(".intro-header").fadeOut(100,function(){
        $(".intro-header").css("background-image","url(" + background[i] + ")");
        $(".intro-header").fadeIn(100);
      });

      i++;

      if (i == background.length) {
         i =  0;
      }

      carousel();
    }, 5000);
  }

  carousel();
}