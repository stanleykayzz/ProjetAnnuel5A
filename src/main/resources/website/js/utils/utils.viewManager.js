;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.viewManager = Core.utils.viewManager || {};

    Core.utils.viewManager.manageMenuButtons = function () {
        utils.addListener(data.getMenu(), "click", function (e) {
            if(e.target.tagName === "A"){
                if(window.client !== null && window.client !== undefined)
                    window.client.reloadTokenDate();

                var pageName = e.target.id.substring(4);
                utils.viewManager.switchView(pageName);
            }
        }, false);
    };

    Core.utils.viewManager.switchView = function (key) {
        var pageObject = data.viewList[key.toString()];

        if(pageObject !== null){
            Core.class.client.reloadClient();            
            Core.utils.empty(data.getIncludeContainer());
            utils.include(pageObject.viewPath, pageObject.name);
            data.currentPath = pageObject.viewPath;
        }
    };

    Core.utils.viewManager.addContextualMenuButtons = function () {
        var menu = data.getMenu();
        var menuLastChild = menu.children[menu.children.length-1];

        var createButton = function (id, content, elementAfter) {
            var baliseLi, baliseA;

            baliseLi = document.createElement("li");
            baliseLi.className    = "title_menu_main contextualMenu";
            baliseLi.style.cursor = "pointer";

            baliseA = document.createElement("a");
            baliseA.id = id;
            baliseA.className = "title_menu_main_a";
            baliseA.textContent = content;

            baliseLi.appendChild(baliseA);
            menu.insertBefore(baliseLi, elementAfter);
        };
        var removeButtons = function () {
            var arrayButtons = document.getElementsByClassName("contextualMenu");
            while(arrayButtons.length > 0){
                arrayButtons[0].parentElement.removeChild(arrayButtons[0]);
            }
        };
        var addButtons = function () {
            if(window.client){
                removeButtons();
                createButton("btn_compte", "Compte", menuLastChild);
                createButton("btn_logout", "Déconnecter", menuLastChild);
            } else {
                removeButtons();
                createButton("btn_connexion", "Connexion", menuLastChild);
            }
        }();
    };

    Core.utils.viewManager.reloadPage = function () {
        utils.addListener(document, "keydown", function(e){
            if(e.keyCode === 116){
                utils.pauseEvent(e);
                utils.empty(data.getIncludeContainer());
                utils.include(data.currentPath, data.currentPath.split(".")[0]);
            }
        }, false);
    };

    Core.utils.viewManager.formValidator = function (jsonObject, style) {
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
            }

            if(val === true){
                if(jsonObject[key].tagName != "SELECT")
                    jsonObject[key].style = style;
            }
        }

        return validation;
    };

    Core.utils.viewManager.initViewEvents = function (viewName) {
        var viewSignin = function () {
            var loginBtn, signupBtn, forgetpassword_Btn;
            var showSingupBtn, showLoginBtn, showforgetpasswordBtn, showLoginFromForgetPassword;
            var loginContainer, signupContainer, forgerpasswordContainer;
            var captchaElement;

            var iniVariables   = function () {
                loginBtn  = document.getElementById("btn_login");
                signupBtn = document.getElementById("btn_signup");
                forgetpassword_Btn = document.getElementById("btn_sendforgetpassword");

                loginContainer  = document.getElementById("loginbox");
                signupContainer = document.getElementById("signupBox");
                forgerpasswordContainer = document.getElementById("forgetpasswordBox");
                signupBtn = document.getElementById("btn_signup");
                forgetpassword_Btn = document.getElementById("btn_sendforgetpassword");

                showSingupBtn = document.getElementById("show_signup");
                showLoginBtn  = document.getElementById("show_login");
                showforgetpasswordBtn = document.getElementById("show_forgetpassword");
                showLoginFromForgetPassword = document.getElementById("forgetpassword_show_login");

                loginContainer  = document.getElementById("loginbox");
                signupContainer = document.getElementById("signupBox");
                forgerpasswordContainer = document.getElementById("forgetpasswordBox");

                captchaElement = document.getElementById("captchaID");
            }();
            var initYear       = function () {
                var monthElement = document.getElementById("signup_date_year");
                var minYear = 1900;
                var date = new Date();
                var length = date.getFullYear() - minYear + 1;

                for(var i = 0 ; i < length ; i++){
                    var option = document.createElement("option");
                    option.value = date.getFullYear() - i;
                    option.textContent = date.getFullYear() - i;

                    monthElement.appendChild(option);
                }
            }();
            var showViewEvents = function () {
                utils.removeListener(showLoginBtn, "click");
                utils.addListener(showLoginBtn, "click", function () {
                    document.getElementById("error_container").textContent = "";
                    signupContainer.style.display = "none";
                    loginContainer.style.display  = "inline-block";
                }, false);

                utils.removeListener(showSingupBtn, "click");
                utils.addListener(showSingupBtn, "click", function () {
                    document.getElementById("error_container").textContent = "";
                    loginContainer.style.display  = "none";
                    signupContainer.style.display = "inline-block";
                    utils.captcha(captchaElement);
                }, false);

                utils.removeListener(showforgetpasswordBtn, "click");
                utils.addListener(showforgetpasswordBtn, "click", function () {
                    document.getElementById("error_container").textContent = "";
                    loginContainer.style.display  = "none";
                    forgerpasswordContainer.style.display = "inline-block";
                }, false);

                utils.removeListener(showLoginFromForgetPassword, "click");
                utils.addListener(showLoginFromForgetPassword, "click", function () {
                    document.getElementById("error_container").textContent = "";
                    forgerpasswordContainer.style.display = "none";
                    loginContainer.style.display  = "inline-block";
                }, false);
            }();
            var requestEvents  = function () {
                utils.removeListener(loginBtn, "click");
                utils.addListener(loginBtn, "click", function () {
                    var email    = document.getElementById("emailBtn").value;
                    var password = document.getElementById("passwordBtn").value;

                    Core.class.client.login(email, password);
                }, false);

                utils.removeListener(signupBtn, "click");
                utils.addListener(signupBtn, "click", function () {
                    var client, firstname, lastname, sexe, female, male, email,
                        password_1, password_2, day, month, year, birthday, phone,
                        country, city, address, postalcode;

                    var captchaInput = document.getElementById("captcha_value");
                    var formValid, sexeValid;

                    var initVariables = function () {
                        firstname  = document.getElementById("signup_name");
                        lastname   = document.getElementById("signup_firstname");
                        female     = document.getElementById("signup_sexe_female");
                        male       = document.getElementById("signup_sexe_male");
                        email      = document.getElementById("signup_email");
                        password_1 = document.getElementById("signup_password");
                        password_2 = document.getElementById("signup_password2");
                        day        = document.getElementById("signup_date_day");
                        month      = document.getElementById("signup_date_month");
                        year       = document.getElementById("signup_date_year");
                        phone      = document.getElementById("signup_phone");
                        country    = document.getElementById("signup_country");
                        city       = document.getElementById("signup_city");
                        address    = document.getElementById("signup_address");
                        postalcode = document.getElementById("signup_postalcode");
                    }();

                    var checkSexe = function () {
                        if(female.checked == false && male.checked == false){
                            female.style.border = "1px solid red";
                            male.style.border   = "1px solid red";
                            sexeValid = false;
                        } else {
                            if(female.checked == true){
                                sexe = 1;
                                sexeValid = true;
                            } else if(male.checked == true){
                                sexe = 0;
                                sexeValid = true;
                            }
                        }
                    }();

                    formValid = utils.viewManager.formValidator({
                        firstname  : firstname,
                        lastname   : lastname,
                        email      : email,
                        password   : password_1,
                        password_2 : password_2,
                        day        : day,
                        month      : month,
                        year       : year,
                        phone      : phone,
                        country    : country,
                        city       : city,
                        address    : address,
                        postalcode : postalcode
                    });

                    if(formValid === false || sexeValid === false)
                        document.getElementById("error_container").textContent = "Veuillez remplir tous les champs";

                    if(captchaInput.value != data.captchaResult){
                        document.getElementById("captcha_error").textContent = "Mauvaise réponse";
                        return;
                    }

                    if(formValid === true && sexeValid === true){
                        birthday = year.value + "-" + month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name") + "-" + day.value;
                        client = '{'+
                            '"name"       : "'+ lastname.value   +'",'+
                            '"firstName"  : "'+ firstname.value  +'",'+
                            '"sexe"       : "'+ sexe             +'",'+
                            '"birthday"   : "'+ birthday         +'",'+
                            '"email"      : "'+ email.value      +'",'+
                            '"phone"      : "'+ phone.value      +'",'+
                            '"country"    : "'+ country.value    +'",'+
                            '"city"       : "'+ city.value       +'",'+
                            '"address"    : "'+ address.value    +'",'+
                            '"postalCode" : "'+ postalcode.value +'",'+
                            '"password"   : "'+ password_1.value +'"'+
                        '}';


                        Core.class.client.signup(client);
                    } else {
                        utils.captcha(captchaElement);
                    }
                }, false);
            }();
        };
        var viewAccount = function () {
            var name, firstName, birthday, email, phone,
                country, city, address, postalCode, sexe;

            var phone_update, country_update, city_update, address_update, postalCode_update,
                new_password_update, verif_paswword_update, current_password_update, jsonPassword;
            var btn_update;
            var validation = true;

            var initVariables = function () {
                name       = document.getElementById("user_lastname");
                firstName  = document.getElementById("user_firstname");
                birthday   = document.getElementById("user_birthday");
                email      = document.getElementById("user_email");
                phone      = document.getElementById("user_tel");
                country    = document.getElementById("user_country");
                city       = document.getElementById("user_city");
                address    = document.getElementById("user_address");
                postalCode = document.getElementById("user_postal_code");
                sexe       = document.getElementById("user_sexe");

                new_password_update     = document.getElementById("user_update_password_new");
                verif_paswword_update   = document.getElementById("user_update_password_verif");
                current_password_update = document.getElementById("user_update_password_current");
                phone_update      = document.getElementById("user_update_tel");
                country_update    = document.getElementById("user_update_country");
                city_update       = document.getElementById("user_update_city");
                address_update    = document.getElementById("user_update_address");
                postalCode_update = document.getElementById("user_update_postal_code");
                btn_update        = document.getElementById("btn_update_account");
            }();
            var setContent    = function () {
                name.textContent       = client.name;
                firstName.textContent  = client.firstName;
                birthday.textContent   = utils.formatDate(client.birthday, "view_account");
                email.textContent      = client.email;
                phone.textContent      = client.phone;
                country.textContent    = client.country;
                city.textContent       = client.city;
                address.textContent    = client.address;
                postalCode.textContent = client.postalCode;

                if(client.sexe == 0)
                    sexe.textContent = "Homme";
                else if(client.sexe == 1)
                    sexe.textContent = "Femme";

                phone_update.value      = client.phone;
                country_update.value    = client.country;
                city_update.value       = client.city;
                address_update.value    = client.address;
                postalCode_update.value = client.postalCode;
             }();
            var viewEvents    = function () {
                var btn_update, btn_delete;
                var container_account, container_update;

                btn_update = document.getElementById("btn_update");
                btn_delete = document.getElementById("btn_delete");

                container_account = document.getElementById("show_account");
                container_update  = document.getElementById("show_update");

                utils.addListener(btn_update, "click", function () {
                    container_account.style.display = "none";
                    container_update.style.display  = "inline-block";
                }, false);
            }();
            var requestEvents = function () {
                utils.removeListener(btn_update, "click");
                utils.addListener(btn_update, "click", function () {               
                    var json = {
                        phone      : phone_update,
                        country    : country_update,
                        city       : city_update,
                        address    : address_update,
                        postalcode : postalCode_update
                    };

                    if(new_password_update.value !== null && new_password_update.value !== undefined & new_password_update.value !== ""){
                        json.password  = new_password_update;
                        json.password_2 = verif_paswword_update;
                        jsonPassword = new_password_update;
                    } else {
                        jsonPassword = current_password_update;
                    }

                    var formValid = utils.viewManager.formValidator(json, phone_update.style);

                    if(formValid === true){
                        var date = utils.formatDate(client.birthday, "update_account")

                        var clientJson = '{'+
                            '"name":       "'+ client.name          +'",'+
                            '"firstName":  "'+ client.firstName     +'",'+
                            '"birthday":   "'+ date                 +'",'+
                            '"email":      "'+ client.email         +'",'+
                            '"phone":      "'+ phone_update.value   +'",'+
                            '"country":    "'+ country_update.value +'",'+
                            '"city":       "'+ city_update.value    +'",'+
                            '"address":    "'+ address_update.value +'",'+
                            '"postalCode": "'+ address_update.value +'",'+
                            '"password":   "'+ jsonPassword.value   +'"'+
                            '}';

                        client.update(clientJson, current_password_update.value);
                    }
                }, false);
            }();
        };
        var viewConfirmation = function () {
            var btn_code = document.getElementById("btn_code");
            var ipt_code = document.getElementById("codeBtn");

            utils.addListener(btn_code, "click", function () {
                Core.class.client.confirmation(ipt_code.value);
            }, false);
        };

        switch (viewName){
            case "connexion" :
                viewSignin();
                break;
            case "logout" :
                client.logout();
                break;
            case "compte" :
                viewAccount();
                break;
            case "confirmation" :
                viewConfirmation();
                break;
        }
    };
})();