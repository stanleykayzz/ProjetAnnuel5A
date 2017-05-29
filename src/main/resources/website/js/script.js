var preUrl = "./html/";
var _eventHandlers = {};

$(document).ready( function() {
    init();
});

function init(){
	$("#include_content").load("./html/accueil.html");
	manageUserButton();
	init_buttons();
}

function init_buttons(){
	unbindAllButtons();

	addListener(document.getElementById("btn_index"), "click", function () {
		clear_all_timeout();
		removeCurrentScript();
		$("#include_content").empty();
		$("#include_content").load(preUrl + "accueil.html");
		update_image("accueil");
		reloadPage("accueil", null);
	}, false);

	addListener(document.getElementById("btn_room"), "click", function () {
		clear_all_timeout();
		removeCurrentScript();
		$("#include_content").empty();
		$("#include_content").load(preUrl + "chambre.html");
		update_image("room");
		manageScriptImport("js/room.js");
		reloadPage("chambre", "js/room.js");
	}, false);

	addListener(document.getElementById("btn_contact"), "click", function () {
		clear_all_timeout();
		removeCurrentScript();
		$("#include_content").empty();
		$("#include_content").load(preUrl + "contact.html");
		update_image("contact");
		manageScriptImport("js/contact.js");
		reloadPage("contact", "js/contact.js");
	}, false);

	addListener(document.getElementById("btn_restaurant"), "click", function () {
		clear_all_timeout();
		removeCurrentScript();
		$("#include_content").empty();
		$("#include_content").load(preUrl + "restaurant.html");
		update_image("restaurant");
		manageScriptImport("js/restaurant.js");
		reloadPage("restaurant", "js/restaurant.js");
	}, false);

	addListener(document.getElementById("btn_about"), "click", function () {
		clear_all_timeout();
		removeCurrentScript();
		$("#include_content").empty();
		$("#include_content").load(preUrl + "about.html");
		update_image("about");
		reloadPage("about", null);
	}, false);
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
			break;

		case "room":
			current_background = new Array();
			current_background = background_room;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			break;

		case "contact":			
			current_background = new Array();
			current_background = background_contact;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			break;

		case "restaurant":			
			current_background = new Array();
			current_background = background_restaurant;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
			break;

		case "about":			
			current_background = new Array();
			current_background = background_about;
			document.getElementById("main_header").setAttribute("style", "background-image : url('" + current_background[0] + "')");
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
	};

	draw_image(0);
}

function clear_all_timeout(){
	var highestTimeoutId = setTimeout(";");
	for (var i = 0 ; i < highestTimeoutId ; i++) {
	    clearTimeout(i); 
	}
}

function unbindAllButtons() {
	removeAllListeners(document.getElementById("btn_room"), "click");
	removeAllListeners(document.getElementById("btn_index"), "click");
	removeAllListeners(document.getElementById("btn_contact"), "click");
	removeAllListeners(document.getElementById("btn_restaurant"), "click");
	removeAllListeners(document.getElementById("btn_about"), "click");
}

function reloadPage(value, script) {
	$(document).unbind("keydown");
	$(document).keydown(function(e){

		if(e.keyCode === 116){
			pauseEvent(e);
			clear_all_timeout();
			$("#include_content").empty();
			$("#include_content").load(preUrl + value + ".html");
			update_image(value);

			if(script !== null){
				removeCurrentScript();
				manageScriptImport(script);
			}
		}
	});
}

function manageUserButton() {
	var session = sessionStorage.getItem("token");
	var userBtn = document.getElementById("btn_user");

	if(session === null){
		userBtn.textContent = "Sign in";
		userBtn.className   = "disconnected";

		addListener(userBtn, "click", function () {
			removeCurrentScript();
			$("#include_content").empty();
			$("#include_content").load(preUrl + "signin.html");
			reloadPage("signin", null);
		}, false);

	} else {
		userBtn.textContent = "User Name";
		userBtn.className   = "connected";

		addListener(userBtn, "click", function () {

		}, false);
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

function manageScriptImport(value) {
	var script = document.createElement("script");
	script.setAttribute("class", "currentScript");
	script.setAttribute("src", value);

	document.getElementById("script_current_container").innerHTML = "";
	document.getElementById("script_current_container").append(script);
}

function removeCurrentScript() {
	var listScripts = document.getElementsByClassName("currentScript");

	while(listScripts[0] !== null && listScripts[0] !== undefined){
		listScripts[0].parentElement.removeChild(listScripts[0]);
	}
}

function addListener(node, event, handler, capture) {
	if(!(node in _eventHandlers)) {
		// _eventHandlers stores references to nodes
		_eventHandlers[node] = {};
	}
	if(!(event in _eventHandlers[node])) {
		// each entry contains another entry for each event type
		_eventHandlers[node][event] = [];
	}
	// capture reference
	_eventHandlers[node][event].push([handler, capture]);
	node.addEventListener(event, handler, capture);
}

function removeAllListeners(node, event) {
	if(node in _eventHandlers) {
		var handlers = _eventHandlers[node];
		if(event in handlers) {
			var eventHandlers = handlers[event];
			for(var i = eventHandlers.length; i--;) {
				var handler = eventHandlers[i];
				node.removeEventListener(event, handler[0], handler[1]);
			}
		}
	}
}

function pauseEvent(e){
	if(e.stopPropagation) e.stopPropagation();
	if(e.preventDefault) e.preventDefault();
	e.cancelBubble=true;
	e.returnValue=false;
	return false;
}