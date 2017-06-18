;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";
    
    Core.service.client = Core.service.client || {};

    Core.service.client.login = function () {
        return {
            method : "GET",
                url : "/client/login",
                func : function (clt) {
                window.client = new Core.class.client(clt);
                utils.viewManager.switchView("accueil");
                client.createSessionStorage(client.token, client.tokenDate);
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
            method : "GET",
            url : "/client/logout",
            func : function () {
                client.removeSessionStorage();
                window.client = null;
                utils.viewManager.switchView("accueil");
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.client.update = function () {
        return {
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
            method : "GET",
            url : "/client/reloadToken",
            func : function (newTokenDate) {
                client.tokenDate = newTokenDate;
                client.createSessionStorage(client.token, client.tokenDate);
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.client.getClientByToken = function () {
        return {
            method : "GET",
            url : "/client/getByToken",
            func : function (clt) {
                console.log(clt);
                window.client = new Core.class.client(clt);
            },
            error : function(statusCode){
            }
        };
    };
})();
