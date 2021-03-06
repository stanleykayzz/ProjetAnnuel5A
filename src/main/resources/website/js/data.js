;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.data = Core.data || {};

    Core.data = {
        pathHtml   : "html/",
        pathScript : "js/",
        viewList   : {
            accueil    : {
                name      : "accueil",
                viewPath  : "accueil.html",
                listImage : ["img/home-bg.jpg"]
            },
            connexion : {
                name       : "connexion",
                viewPath   : "signin.html",
                listImage : ["img/contact1.jpg","img/contact2.jpg","img/contact3.jpg","img/contact4.jpg","img/contact5.jpg"]
            },
            chambre : {
                name      : "chambre",
                viewPath  : "chambre.html",
                listImage : ["img/room1.jpg","img/room2.jpg","img/room3.jpg","img/room4.jpg","img/room5.jpg","img/room6.jpg","img/room7.jpg","img/room8.jpg","img/room9.jpg","img/room10.jpg"]
            },
            restaurant : {
                name      : "restaurant",
                viewPath  : "restaurant.html",
                listImage : ["img/restau1.jpg","img/restau2.jpg","img/restau3.jpg","img/restau4.jpg","img/restau5.jpg","img/restau6.jpg","img/restau7.jpg"]
            },
            contact : {
                name      : "contact",
                viewPath  : "contact.html",
                listImage : ["img/contact1.jpg","img/contact2.jpg","img/contact3.jpg","img/contact4.jpg","img/contact5.jpg"]
            },
            compte : {
                name      : "compte",
                viewPath  : "compte.html",
                listImage : ["img/contact1.jpg","img/contact2.jpg","img/contact3.jpg","img/contact4.jpg","img/contact5.jpg"]
            },
            about      : {
                name      : "about",
                viewPath  : "about.html",
                listImage : ["img/about-bg.jpg"]
            },
            logout     : {
                name      : "logout",
                viewPath  : "accueil.html",
                listImage : ["img/home-bg.jpg"]
            },
            confirmation : {
                name      : "confirmation",
                viewPath  : "confirmation.html",
                listImage : ["img/contact1.jpg","img/contact2.jpg","img/contact3.jpg","img/contact4.jpg","img/contact5.jpg"]
            }
        },
        basicUrl   : "http://localhost:8080",
        clientUrl  : "/client",
        getMenu : function () {
            return document.getElementById("ul_menu");
        },
        getIncludeContainer : function () {
            return document.getElementById("include_content");
        },
        currentPath : null,
        captchaResult : null
    };
})();