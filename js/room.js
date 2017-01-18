/*
**room.js
*/

//on définit un tableau des images que l'on souhaite faire défiler sur la page
background = ["img/roomx.png","img/room3.png","img/room5.png","img/room8.png","img/room10.png","img/room1.png"]

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
	}, 10000);