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
        data.currentPath = data.viewList.accueil.viewPath;

        if(window.sessionStorage.getItem("token")     != null
            && window.sessionStorage.getItem("token") != undefined)
            Core.class.client.reloadClient();

        utils.manageImages(data.viewList.contact.listImage);
        utils.viewManager.reloadPage();
        utils.viewManager.addContextualMenuButtons();
        utils.viewManager.manageMenuButtons();
        
        utils.include(data.viewList.accueil.viewPath, data.viewList.accueil.name);
    };

}).call(this); //Get current Context (window) into script