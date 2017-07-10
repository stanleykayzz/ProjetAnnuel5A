;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.template = Core.utils.template || {};

    Core.utils.template.create = function (arrayHeaders, jsonValues, container) {
        var div_table = document.createElement("div");
        div_table.classList.add("div_table");

        var createHeader = function () {
            var div_row_header = document.createElement("div");
            div_row_header.classList.add("div_row");

            var div_cell_header = document.createElement("div");
            div_cell_header.classList.add("div_cell");
            div_cell_header.classList.add("header_list");

            for(var i = 0; i < arrayHeaders.length ; i++){
                var clone = div_cell_header.cloneNode(true);
                clone.textContent = h;
                div_row_header.appendChild(clone);
            }
    
            div_table.appendChild(div_row_header);
        }();

    };

    Core.utils.template.manageListDisplay = function (id) {
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
