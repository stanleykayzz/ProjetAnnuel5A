;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.form = Core.utils.form || {};

    Core.utils.form.formValidator = function (jsonObject, style) {
        var validation = true;
        var sexe = null;
        var password;

        for(var key in jsonObject){
            var val = true;
            var current_style = jsonObject[key].style;
            switch (key){
                case "firstname":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "lastname":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === "") {
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "email":
                    if(utils.emailValidator(jsonObject[key]) === false){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "password":
                    if(jsonObject[key].value.length < 6){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    } else {
                        password = jsonObject[key].value;
                    }
                    break;

                case "password_2":
                    if(jsonObject[key].value  !== password){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "day":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === "" || Core.utils.checkDate("day", jsonObject[key].value) === false){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "month":
                    if(jsonObject[key].getElementsByTagName("option")[jsonObject[key].selectedIndex].getAttribute("name")     === null
                        || jsonObject[key].getElementsByTagName("option")[jsonObject[key].selectedIndex].getAttribute("name") === undefined
                        || jsonObject[key].getElementsByTagName("option")[jsonObject[key].selectedIndex].getAttribute("name") === ""
                        || Core.utils.checkDate("month", jsonObject[key].getElementsByTagName("option")[jsonObject[key].selectedIndex].getAttribute("name")) === false){

                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "year":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === "" || Core.utils.checkDate("year", jsonObject[key].value) === false){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "phone":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "country":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "city":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "address":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "postalcode":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined || jsonObject[key].value === ""){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;

                case "datepicker_start":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;
                
                case "datepicker_end":
                    if(jsonObject[key].value === null || jsonObject[key].value === undefined){
                        validation = false;
                        val = false;
                        jsonObject[key].style.border = "1px solid red";
                    }
                    break;
            }

            if(val === true){
                if(jsonObject[key].tagName != "SELECT")
                    jsonObject[key].style = style;
            }
        }

        return validation;
    };
})();