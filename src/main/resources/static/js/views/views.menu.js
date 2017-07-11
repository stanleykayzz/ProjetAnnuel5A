/**
 * Created by maxime.
 *
 * version 1.0.0
 */
;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.menu = Core.views.menu || {};

    /**
     * Manage the menu buttons
     */
    Core.views.menu.manageMenuButtons = function () {
        utils.addListener(data.getMenu(), "click", function (e) {
            if(e.target.tagName === "A"){
                if(window.client !== null && window.client !== undefined)
                    window.client.reloadTokenDate();

                var pageName = e.target.id.substring(4);
                views.includeContainer.switchView(pageName);
            }
        }, false);
    };

    /**
     * add the contextual menu buttons when the client if connected
     */
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

                createButton("btn_restaurant", "Restaurant", menuLastChild);
                createButton("btn_festiveRoom", "Salle des fêtes", menuLastChild);

                if(client.status === 0)
                    createButton("btn_admin", "Administration", menuLastChild);

                if(client.status === 1)
                    createButton("btn_clientListBook", "Réservations", menuLastChild);

                createButton("btn_listArticle", "Article", menuLastChild);
                createButton("btn_compte", "Compte", menuLastChild);
                createButton("btn_logout", "Déconnecter", menuLastChild);
            } else {
                removeButtons();
                createButton("btn_connexion", "Connexion", menuLastChild);
            }
        }();
    };

    /**
     * Reload on the same page when the client press F5
     */
    Core.views.menu.reloadPage = function () {
        utils.addListener(document, "keydown", function(e){
            if(e.keyCode === 116){
                utils.pauseEvent(e);
                utils.empty(data.getIncludeContainer());
                utils.include(data.currentPath, data.currentPath.split(".")[0]);
            }
        }, false);
    };

})();