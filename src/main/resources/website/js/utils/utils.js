;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils = Core.utils || {};
    var _eventHandlers = {};
    var _timeoutsID = [];

    Core.utils.ajaxRequest = function (objectService, paramRequest, paramBody, option) {
        var requestUrl, requestBody;

        var initVariables = function () {
            if(paramRequest !== null && paramRequest !== undefined)
                requestUrl = data.basicUrl + objectService.url + "?" + paramRequest;
            else
                requestUrl = data.basicUrl + objectService.url;

            if(paramBody !== null && paramBody !== undefined)
                requestBody = paramBody;
            else
                requestBody = "";
        }();

        var xhr = new XMLHttpRequest();
        xhr.open(objectService.method, requestUrl , false);
        xhr.setRequestHeader('Content-type', 'application/json');

        xhr.onload = function (re) {
            if(xhr.status === 200 || xhr.status === 201){
                if(option === true && (this.responseText == undefined || this.responseText == "")){
                    objectService.error(xhr.status);
                } else {
                    var response = JSON.parse(this.responseText);
                    objectService.func(response);
                }
            } else {
                objectService.error(xhr.status);
            }
        };

        xhr.onerror = function (res) {
            objectService.error(xhr.status);
        };

        xhr.send(requestBody);
    };

    Core.utils.include = function (path, name) {
        var fullPath = data.pathHtml + path;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", fullPath , true);
        xhr.onload = function () {
            document.getElementById("include_content").innerHTML = this.responseText;
            views.menu.initViewEvents(name);
        };

        xhr.send();
    };

    Core.utils.empty = function (element) {
        while(element.firstChild){
            element.removeChild(element.firstChild);
        }
    };

    Core.utils.removeTimeouts = function () {
        var length = _timeoutsID.length;
        for(var  i = 0 ; i < length ; i++){
            window.clearTimeout(_timeoutsID[i]);
        }

        _timeoutsID = [];
    };

    Core.utils.manageImages = function (background) {
        var divHeader = document.getElementById("main_header");
        divHeader.style.backgroundImage = "url(" + background[background.length-1] +")";

        var timeOut = function(i){
            utils.removeTimeouts();

            if (i == background.length-1)
                i = 0;
            else
                i += 1;

            var tmID = setTimeout(function(){
                $(".intro-header").fadeOut(1000,function(){
                    divHeader.style.backgroundImage = "url(" + background[i] +")";
                    $("#main_header").fadeIn(1000);
                })

                timeOut(i);
            }, 7000);

            _timeoutsID[_timeoutsID.length] = tmID;
        };

        timeOut(0);
    };

    Core.utils.captcha = function (element) {
        var catchpaElement, value1, value2, operator, result;
        var type = Math.floor((Math.random() * 3) + 1);
        value1 = Math.floor((Math.random() * 10) + 1);
        value2 = Math.floor((Math.random() * 10) + 1);

        switch (type){
            case 1:
                operator = "+";
                data.captchaResult = value1 + value2;
                break;
            case 2:
                operator = "-";
                data.captchaResult = value1 - value2;
                break;
            case 3:
                operator = "*";
                data.captchaResult = value1 * value2;
                break;
        }

        catchpaElement = '<span>'+ value1 +'</span><span> '+ operator +' </span><span>'+ value2 +'</span> = <input id="captcha_value" type="text" style ="width: 100px; text-align: center;">'
            +'<div id="captcha_reload" href="#" style="margin-left: 10px; display: inline-block; cursor: pointer;"><i class="glyphicon glyphicon-repeat"></i></div>'
            +'<span id="captcha_error" style="margin-left: 10px;color: red; font: 16px Lora,Times New Roman,serif;"></span>';
        element.innerHTML = catchpaElement;

        utils.removeListener(document.getElementById("captcha_reload"), "click");
        utils.addListener(document.getElementById("captcha_reload"), "click", function () {
            utils.captcha(element);
        }, false);
    };

    Core.utils.checkDate = function (type, value) {
        var currentTime = new Date();

        switch (type){
            case "year":
                if(value > 1900 && value <= currentTime.getFullYear())
                    return true;
                else
                    return false;

                break;

            case "month":
                if(value >= 1 && value <= 12)
                    return true;
                else
                    return false;

                break;

            case "day":
                if(value >= 1 && value <= 31)
                    return true;
                else
                    return false;

                break;
        };
    };

    Core.utils.formatDate = function (time, type) {
        var year, month, day;
        var dateObject = new Date(time);

        var initVariables = function () {
            year = dateObject.getFullYear();

            if(dateObject.getMonth() < 9)
                month = "0" + (parseInt(dateObject.getMonth()) + 1);
            else
                month = (parseInt(dateObject.getMonth()) + 1);

            if(dateObject.getDate() < 10)
                day = "0" + dateObject.getDate();
            else
                day = dateObject.getDate();
        }();


        switch (type){
            case "view_account":
                return day + "/" + month + "/" + year;
            case  "update_account":
                return year + "-" + month + "-" + day;
        }
    };

    Core.utils.setDatepickerLanguage = function () {
        $.datepicker.regional['fr'] = {clearText: 'Effacer', clearStatus: '',
            closeText: 'Fermer', closeStatus: 'Fermer sans modifier',
            prevText: '<Préc', prevStatus: 'Voir le mois précédent',
            nextText: 'Suiv>', nextStatus: 'Voir le mois suivant',
            currentText: 'Courant', currentStatus: 'Voir le mois courant',
            monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin',
                'Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
            monthNamesShort: ['Jan','Fév','Mar','Avr','Mai','Jun',
                'Jul','Aoû','Sep','Oct','Nov','Déc'],
            monthStatus: 'Voir un autre mois', yearStatus: 'Voir un autre année',
            weekHeader: 'Sm', weekStatus: '',
            dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
            dayNamesShort: ['Dim','Lun','Mar','Mer','Jeu','Ven','Sam'],
            dayNamesMin: ['Di','Lu','Ma','Me','Je','Ve','Sa'],
            dayStatus: 'Utiliser DD comme premier jour de la semaine', dateStatus: 'Choisir le DD, MM d',
            dateFormat: 'dd/mm/yy', firstDay: 0,
            initStatus: 'Choisir la date', isRTL: false};
        $.datepicker.setDefaults($.datepicker.regional['fr']);
    };

    Core.utils.emailValidator = function (email) {
        var rgx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return rgx.test(email.value);
    };

    Core.utils.verifSessionStorage = function (token_date) {
        var currentDate = new Date();
        var tokenDate   = new Date(token_date);

        var diffHours  = currentDate.getHours()   - tokenDate.getHours();
        var diffMin    = currentDate.getMinutes() - tokenDate.getMinutes();
        var diffMinExc = (tokenDate.getMinutes() +  60) - currentDate.getMinutes();

        if(tokenDate.getFullYear()  !== currentDate.getFullYear()
            || tokenDate.getMonth() !== currentDate.getMonth()
            || tokenDate.getDate()  !== currentDate.getDate())
            return false;
        
        if(diffHours === 0){
            if(diffMin <= 15){                
                return true;
            } else {
                return false;
            }
            
        } else if(diffHours === 1 && diffMinExc <= 15){
            return true;
        }
        return false;
    };

    /**
     * Facade pattern
     *
     * @param node
     * @param event
     * @param handler
     * @param capture
     */
    Core.utils.addListener = function (node, event, handler, capture) {
        if(!(node in _eventHandlers)) {
            _eventHandlers[node] = {};
        }
        if(!(event in _eventHandlers[node])) {
            _eventHandlers[node][event] = [];
        }
        _eventHandlers[node][event].push([handler, capture]);
        node.addEventListener(event, handler, capture);
    };

    /**
     *
     * @param node
     * @param event
     */
    Core.utils.removeListener = function (node, event) {
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
    };

    /**
     *
     * @param e
     * @returns {boolean}
     */
    Core.utils.pauseEvent = function (e){
        if(e.stopPropagation) e.stopPropagation();
        if(e.preventDefault) e.preventDefault();
        e.cancelBubble=true;
        e.returnValue=false;

        return false;
    };
})();
