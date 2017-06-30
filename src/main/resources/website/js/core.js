;(function(undefined) {
    "use strict";

    var mInstances = {};

    var Core = Core || {};

    /**
     * The current version of Core
     */
    Core.version = "1.0.0";

    /**
     * EXPORT:
     * *******
     */
    if(typeof this.Core !== "undefined")
        throw "An object called Core is already in the global scope.";

    this.Core = Core;

    this.onload = function(){
        //Declaration variables in Global Scope
        window.utils   = Core.utils   || {};
        window.data    = Core.data    || {};
        window.class   = Core.class   || {};
        window.service = Core.service || {};
        window.views   = Core.views   || {};
        data.currentPath = data.viewList.accueil.viewPath;

        if(window.sessionStorage.getItem("token")     != null
            && window.sessionStorage.getItem("token") != undefined)
            Core.class.client.reloadClient();

        views.menu.reloadPage();
        views.menu.addContextualMenuButtons();
        views.menu.manageMenuButtons();

        utils.manageImages(data.viewList.contact.listImage);
        utils.setDatepickerLanguage();
        utils.include(data.viewList.accueil.viewPath, data.viewList.accueil.name);
    };

}).call(this); //Get current Context (window) into script