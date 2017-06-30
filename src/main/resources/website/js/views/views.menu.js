;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.menu = Core.views.menu || {};

    Core.views.menu.manageMenuButtons = function () {
        utils.addListener(data.getMenu(), "click", function (e) {
            if(e.target.tagName === "A"){
                if(window.client !== null && window.client !== undefined)
                    window.client.reloadTokenDate();

                var pageName = e.target.id.substring(4);
                views.menu.switchView(pageName);
            }
        }, false);
    };

    Core.views.menu.switchView = function (key) {
        var pageObject = data.viewList[key.toString()];

        if(pageObject !== null){
            Core.class.client.reloadClient();
            utils.empty(data.getIncludeContainer());
            utils.include(pageObject.viewPath, pageObject.name);
            data.currentPath = pageObject.viewPath;
        }
    };

    Core.views.menu.addContextualMenuButtons = function () {
        var menu = data.getMenu();
        var menuLastChild = menu.children[menu.children.length-1];

        var createButton = function (id, content, elementAfter) {
            var baliseLi, baliseA;

            baliseLi = document.createElement("li");
            baliseLi.className    = "title_menu_main contextualMenu";
            baliseLi.style.cursor = "pointer";

            baliseA = document.createElement("a");
            baliseA.id = id;
            baliseA.className = "title_menu_main_a";
            baliseA.textContent = content;

            baliseLi.appendChild(baliseA);
            menu.insertBefore(baliseLi, elementAfter);
        };
        var removeButtons = function () {
            var arrayButtons = document.getElementsByClassName("contextualMenu");
            while(arrayButtons.length > 0){
                arrayButtons[0].parentElement.removeChild(arrayButtons[0]);
            }
        };
        var addButtons = function () {
            if(window.client){
                removeButtons();
                createButton("btn_compte", "Compte", menuLastChild);
                createButton("btn_logout", "Déconnecter", menuLastChild);
            } else {
                removeButtons();
                createButton("btn_connexion", "Connexion", menuLastChild);
            }
        }();
    };

    Core.views.menu.reloadPage = function () {
        utils.addListener(document, "keydown", function(e){
            if(e.keyCode === 116){
                utils.pauseEvent(e);
                utils.empty(data.getIncludeContainer());
                utils.include(data.currentPath, data.currentPath.split(".")[0]);
            }
        }, false);
    };

    Core.views.menu.initViewEvents = function (viewName) {
        switch (viewName){
            case "connexion" :
                views.includeContainer.signin();
                views.includeContainer.signup();
                views.includeContainer.forgetPassword();
                break;
            case "logout" :
                client.logout();
                break;
            case "compte" :
                views.includeContainer.account();
                views.includeContainer.updateAccount();
                break;
            case "confirmation" :
                views.includeContainer.confirmationCode();
                break;
            case "chambre":
                views.includeContainer.roomReservation();
                break;
        }
    };
})();