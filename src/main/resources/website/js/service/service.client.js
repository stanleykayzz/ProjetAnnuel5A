;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";
    
    Core.service.client = Core.service.client || {};

    Core.service.client.login = function () {
        return {
            name   : "login",
            method : "GET",
            url    : "/client/login",
            func : function (clt) {
                window.client = new Core.class.client(clt);
                var pageObject = data.viewList.confirmation;
                console.log(Core.utils.crypto.getCbc());
                /*var viewSuccess  = function () {
                    utils.empty(data.getIncludeContainer());
                    data.getIncludeContainer().innerHTML = ""+
                        "<div style='display: inline-block; width: 100%; color: #3c763d; text-align: center; padding-bottom: 40px;'>"+
                        "</br>Veuillez saisir le code pour valider l'email."+
                        "</h2></div>";
                }();
                var redirection = function () {
                    var timeOut = function(){
                        var tmID = setTimeout(function(){
                            Core.utils.empty(data.getIncludeContainer());
                            utils.viewManager.switchView("confirmation");
                        }, 5000);
                    }();
                }();*/
                //client.createSessionStorage(client.token, client.tokenDate);
            },
            error : function(statusCode){
                document.getElementById("error_container").textContent = "Identifiants incorrects";

                document.getElementById("emailBtn").value = "";
                document.getElementById("passwordBtn").value = "";
            }
        };
    };

    Core.service.client.signup = function () {
        return {
            name : "signup",
            method : "POST",
            url : "/client",
            func : function (clt) {
                var pageObject = data.viewList.connexion;

                var viewSuccess  = function () {
                    utils.empty(data.getIncludeContainer());
                    data.getIncludeContainer().innerHTML = ""+
                        "<div style='display: inline-block; width: 100%; color: #3c763d; text-align: center; padding-bottom: 40px;'>"+
                        "</br>Inscription réussi, vous allez être redirigé vers la page de connexion."+
                        "</br>N'oubliez pas de valider votre email.</h2></div>";
                }();
                var redirection = function () {
                    var timeOut = function(){
                        var tmID = setTimeout(function(){
                            Core.utils.empty(data.getIncludeContainer());
                            utils.include(pageObject.viewPath, pageObject.name);
                        }, 8000);
                    }();
                }();
            },
            error : function(statusCode){
                document.getElementById("error_container").textContent = "L'email existe déjà.";
                document.getElementById("signup_email").style.border = "1px solid red";
                document.getElementById("emailBtn").value = "";
                document.getElementById("passwordBtn").value = "";
            }
        };
    };

    Core.service.client.logout = function () {
        return {
            name : "logout",
            method : "GET",
            url : "/client/logout",
            func : function () {
                Core.class.client.removeSessionStorage();
                window.client = null;
                utils.viewManager.switchView("accueil");
                Core.utils.viewManager.addContextualMenuButtons();
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.client.update = function () {
        return {
            name : "update",
            method : "POST",
            url : "/client/update",
            func : function (clt) {
                window.client = new Core.class.client(clt);
                client.createSessionStorage(client.token, client.tokenDate);
                utils.viewManager.switchView("compte");
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.client.reloadToken = function () {
        return {
            name : "reloadToken",
            method : "GET",
            url : "/client/reloadToken",
            func : function (newTokenDate) {
                client.tokenDate = newTokenDate;
                client.createSessionStorage(client.tokenDate);
             },
            error : function(statusCode){
                Core.class.client.removeSessionStorage();
                window.client = null;
                utils.viewManager.switchView("user");
                utils.viewManager.addContextualMenuButtons();                
            }
        };
    };

    Core.service.client.getClientByToken = function () {
        return {
            name : "getClientByToken",
            method : "GET",
            url : "/client/getByToken",
            func : function (clt) {
                window.client = new Core.class.client(clt);
            },
            error : function(statusCode){
                Core.class.client.removeSessionStorage();
            }
        };
    };

    Core.service.client.confirmation = function () {
        return {
            name : "confirmation",
            method : "GET",
            url : "/client/confirmation",
            func : function (clt) {
                window.client = new Core.class.client(clt);
                client.createSessionStorage(client.token, client.tokenDate);
                utils.viewManager.switchView("accueil");
                utils.viewManager.addContextualMenuButtons();
            },
            error : function(statusCode){
                //Core.class.client.removeSessionStorage();
            }
        };
    };
})();