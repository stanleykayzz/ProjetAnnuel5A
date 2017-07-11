;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.includeContainer = Core.views.includeContainer || {};

    Core.views.includeContainer.switchView = function (key) {
        var pageObject = data.viewList[key.toString()];

        if (pageObject !== null) {
            Core.class.client.reloadClient();
            utils.empty(data.getIncludeContainer());
            utils.include(pageObject.viewPath, pageObject.name);
            utils.manageImages(pageObject.listImage, data.mainImageID);
            data.currentPath = pageObject.viewPath;
        }
    };

    Core.views.includeContainer.initViewEvents = function (viewName) {
        switch (viewName) {
            case "connexion" :
                Core.views.clientSignInAndUp.initView();
                break;
            case "logout" :
                client.logout();
                break;
            case "compte" :
                Core.views.initView();
                break;
            case "confirmation" :
                views.code.confirmationCode();
                break;
            case "chambre":
                views.room.initView();
                break;
            case "restaurant":
                views.restaurant.initView();
                break;
            case "festiveRoom":
                Core.class.festiveRoom.initView();
                break;
            case "clientListBook":
                views.clientListBook.initView();
                break;
            case "admin":
                views.admin.initView();
                break;
            case "listArticle":
                views.article.initView();
                break;
            case "contact":
                Core.views.contact.initView();
                break;
        }
    };
    
})();