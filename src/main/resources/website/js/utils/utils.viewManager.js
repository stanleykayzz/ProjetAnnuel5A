;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.viewManager = Core.utils.viewManager || {};

    Core.utils.viewManager.manageMenuButtons = function () {
        utils.addListener(data.getMenu(), "click", function (e) {
            if(e.target.tagName === "A"){
                Core.utils.empty(data.getIncludeContainer());
                var pageObject;

                switch (e.target.id){
                    case "btn_index" :
                        pageObject = data.viewList.accueil;
                        break;
                    case "btn_about" :
                        pageObject = data.viewList.about;
                        break;
                    case "btn_restaurant" :
                        pageObject = data.viewList.restaurant;
                        break;
                    case "btn_room" :
                        pageObject = data.viewList.chambre;
                        break;
                    case "btn_user" :
                        pageObject = data.viewList.connexion;
                        break;
                    case "btn_contact" :
                        pageObject = data.viewList.contact;
                        break;
                }

                utils.include(pageObject.viewPath, pageObject.name);
                utils.manageImages(pageObject.listImage);
                data.currentPath = pageObject.viewPath;
            }
        }, false);
    };

    Core.utils.viewManager.addSignInButton = function () {
        var userBtn = data.getUserButton();
        userBtn.textContent = "Sign in";
        userBtn.className   = "disconnected";
    };

    Core.utils.viewManager.reloadPage = function () {
        utils.addListener(document, "keydown", function(e){
            if(e.keyCode === 116){
                utils.pauseEvent(e);
                utils.empty(data.getIncludeContainer());
                utils.include(data.currentPath, data.currentPath.split(".")[0]);
            }
        }, false);
    };

    Core.utils.viewManager.initViewEvents = function (viewName) {

        var viewSignin = function () {
            //var email = "momo@hotmail.fr";
            var loginBtn = document.getElementById("btn_login");

            utils.removeListener(loginBtn, "click");
            utils.addListener(loginBtn, "click", function () {
                var email    = document.getElementById("emailBtn").value;
                var password = document.getElementById("passwordBtn").value;

                Core.class.client.login(email, password);
            }, false);
        };

        switch (viewName){
            case "connexion" :
                viewSignin();
                break;
        }
    };
})();