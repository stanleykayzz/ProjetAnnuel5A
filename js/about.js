/*
**about.js
*/

//on définit un tableau des images que l'on souhaite faire défiler sur la page
background = ["img/about-bg.png","img/about5.png","img/about6.png","img/roomHall1.png"]

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