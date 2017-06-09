;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils = Core.utils || {};
    var _eventHandlers = {};
    var _timeoutsID = [];

    Core.utils.ajaxRequest = function (objectService, paramRequest, paramBody) {
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
        }(); // () -> Load automatically the function

        var xhr = new XMLHttpRequest();
        xhr.open(objectService.method, requestUrl , false);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.overrideMimeType('application/json;charset=utf-8');

        xhr.onload = function (res) {
            if(xhr.status === 200){
                var response = JSON.parse(this.responseText);
                objectService.func(response);
            } else {
                objectService.error(xhr.status);
            }
        };

        xhr.send(requestBody);
    };

    Core.utils.include = function (path, name) {
        var fullPath = data.pathHtml + path;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", fullPath , true);
        xhr.onload = function () {
            document.getElementById("include_content").innerHTML = this.responseText;
            utils.viewManager.initViewEvents(name);
        };

        xhr.send();
    };

    Core.utils.removeTimeouts = function () {
        var length = _timeoutsID.length;
        for(var  i = 0 ; i < length ; i++){
            window.clearTimeout(_timeoutsID[i]);
        }

        _timeoutsID = [];
    };

    Core.utils.empty = function (element) {
        while(element.firstChild){
            element.removeChild(element.firstChild);
        }
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

    Core.utils.addListener = function (node, event, handler, capture) {
        if(!(node in _eventHandlers)) {
            // _eventHandlers stores references to nodes
            _eventHandlers[node] = {};
        }
        if(!(event in _eventHandlers[node])) {
            // each entry contains another entry for each event type
            _eventHandlers[node][event] = [];
        }
        // capture reference
        _eventHandlers[node][event].push([handler, capture]);
        node.addEventListener(event, handler, capture);
    };

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

    Core.utils.pauseEvent = function (e){
        if(e.stopPropagation) e.stopPropagation();
        if(e.preventDefault) e.preventDefault();
        e.cancelBubble=true;
        e.returnValue=false;

        return false;
    };
})();
