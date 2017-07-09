;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.templateGenerator = Core.utils.templateGenerator || {};

    Core.utils.templateGenerator.manageDisplay = function (id) {
        var li = document.getElementById(id);

        utils.addListener(li, "click", function (e) {
            if(e.target.classList.contains("active") === true){
                e.target.classList.remove("active");
                e.target.nextElementSibling.style.display = "none";
            } else {
                e.target.classList.add("active");
                e.target.nextElementSibling.style.display = "block";
            }
        }, false);
    };

})();
