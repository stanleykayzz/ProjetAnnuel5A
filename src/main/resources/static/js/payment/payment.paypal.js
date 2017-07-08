;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.payment.paypal = Core.payment.paypal || {};

    Core.payment.paypal.generateButton = function (price, container) {
         var script = document.createElement("script");
         script.async = "async";
         script.src = "https://www.paypalobjects.com/js/external/paypal-button.min.js?merchant=residencedeshautsdemenaye-facilitator@outlook.com";
         script.setAttribute("data-button", "buynow");
         script.setAttribute("data-name", "test");
         script.setAttribute("data-quantity", "1");
         script.setAttribute("data-amount", price);
         script.setAttribute("data-currency", "EUR");
         script.setAttribute("data-shipping", "0");
         script.setAttribute("data-tax", "0");
         script.setAttribute("data-env", "sandbox");
         script.setAttribute("data-callback", "http://localhost:63342/ProjetAnnuel2017/static/index.html?token="+client.token);

         var div = document.createElement("div");
         div.classList.add("paypal_container");

         div.appendChild(script);
         container.appendChild(div);
    };

})();