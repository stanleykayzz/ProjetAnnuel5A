$(document).ready( function() {
    init();
});

function init(){
	$("#include_content").load("./html/accueil.html");
	init_buttons();
}


function init_buttons(){
	$("#btn_room").unbind("click");
	$("#btn_room").click(function(){
		clear_all_timeout();	
		$("#include_content").empty();		
		$("#include_content").load("./html/chambre.html");
		update_image("room");
	});

	$("#btn_index").unbind("click");
	$("#btn_index").click(function(){
		clear_all_timeout();
		$("#include_content").empty();		
		$("#include_content").load("./html/accueil.html");
		update_image("accueil");
	});

	$("#btn_contact").unbind("click");
	$("#btn_contact").click(function(){
		clear_all_timeout();	
		$("#include_content").empty();		
		$("#include_content").load("./html/contact.html");
		update_image("contact");
	});

	$("#btn_restaurant").unbind("click");
	$("#btn_restaurant").click(function(){
		clear_all_timeout();	
		$("#include_content").empty();
		$("#include_content").load("./html/restaurant.html");
		update_image("restaurant");
	});

	$("#btn_about").unbind("click");
	$("#btn_about").click(function(){
		clear_all_timeout();	
		$("#include_content").empty();
		$("#include_content").load("./html/about.html");
		update_image("about");
	});
}
      
function update_image(selection){
	var background_room = ["img/room1.jpg","img/room2.jpg","img/room3.jpg","img/room4.jpg","img/room5.jpg","img/room6.jpg","img/room7.jpg","img/room8.jpg","img/room9.jpg","img/room10.jpg"]
	var background_accueil = ["img/home-bg.jpg"];
	var background_contact = ["img/contact1.jpg","img/contact2.jpg","img/contact3.jpg","img/contact4.jpg","img/contact5.jpg"]
	var background_restaurant = ["img/restau1.jpg","img/restau2.jpg","img/restau3.jpg","img/restau4.jpg","img/restau5.jpg","img/restau6.jpg","img/restau7.jpg"]
	var background_about = ["img/about-bg.jpg"];
	var timeoutID;
	var current_background;

	switch(selection){
		case "accueil":
			current_background = new Array();
			current_background = background_accueil;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			document.getElementById("script_current_container").innerHTML = "";
			break;

		case "room":
			current_background = new Array();
			current_background = background_room;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			document.getElementById("script_current_container").innerHTML = "";
			var script_room = document.createElement("script");
			script_room.setAttribute("src", "js/room.js")
			document.getElementById("script_current_container").append(script_room);

			break;

		case "contact":			
			current_background = new Array();
			current_background = background_contact;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			document.getElementById("script_current_container").innerHTML = "";
			break;

		case "restaurant":			
			current_background = new Array();
			current_background = background_restaurant;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			document.getElementById("script_current_container").innerHTML = "";
			break;

		case "about":			
			current_background = new Array();
			current_background = background_about;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			document.getElementById("script_current_container").innerHTML = "";
			break;
	}

	var draw_image = function(index){
		timeoutID = setTimeout(function(){
			if(index < current_background.length && current_background.length>1){

				$("#main_header").attr('style', '');
				 $( "#main_header" ).slideDown(1000, function() {
				   document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[index] + "')");
				 });
			
				index++;
				draw_image(index);
			} else if(current_background.length == 1){
				
			} else {
				index =0;
				draw_image(index);
			}
		}, 5000);
	}

	draw_image(0);

}

function clear_all_timeout(){
	var highestTimeoutId = setTimeout(";");
	for (var i = 0 ; i < highestTimeoutId ; i++) {
	    clearTimeout(i); 
	}
}

function add_footer(){
	var foot =     '<div class="container">'+
			            '<div class="row">'+
			                '<div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">'+
			                    '<img src="img/logoRHM.png" id="bottom_image" />'+
			                    '<p class="copyright text-muted">Copyright &copy; Résidence des Hauts de Menaye 2016</p>'+
			                '</div>'+
			            '</div>'+
			        '</div>';	
	document.getElementsByTagName("footer")[0].innerHTML = foot;
}