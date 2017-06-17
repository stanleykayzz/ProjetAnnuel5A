;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.utils.viewManager = Core.utils.viewManager || {};

    Core.utils.viewManager.manageMenuButtons = function () {
        utils.addListener(data.getMenu(), "click", function (e) {
            if(e.target.tagName === "A"){
                Core.utils.empty(data.getIncludeContainer());
                var pageObject;

                switch (e.target.id){
                    case "btn_index" :
                        pageObject = data.viewList.accueil;
                        break;
                    case "btn_about" :
                        pageObject = data.viewList.about;
                        break;
                    case "btn_restaurant" :
                        pageObject = data.viewList.restaurant;
                        break;
                    case "btn_room" :
                        pageObject = data.viewList.chambre;
                        break;
                    case "btn_user" :
                        pageObject = data.viewList.connexion;
                        break;
                    case "btn_contact" :
                        pageObject = data.viewList.contact;
                        break;
                }

                utils.include(pageObject.viewPath, pageObject.name);
                utils.manageImages(pageObject.listImage);
                data.currentPath = pageObject.viewPath;
            }
        }, false);
    };

    Core.utils.viewManager.switchView = function (value) {
        var pageObject = null;

        switch (value){
            case "accueil" :
                pageObject = data.viewList.accueil;
                break;
            case "about" :
                pageObject = data.viewList.about;
                break;
            case "restaurant" :
                pageObject = data.viewList.restaurant;
                break;
            case "chambre":
                pageObject = data.viewList.chambre;
                break;
            case "user" :
                pageObject = data.viewList.connexion;
                break;
            case "contact" :
                pageObject = data.viewList.contact;
                break;
        }

        if(pageObject !== null){
            Core.utils.empty(data.getIncludeContainer());
            utils.include(pageObject.viewPath, pageObject.name);
            data.currentPath = pageObject.viewPath;
        }
    };

    Core.utils.viewManager.addSignInButton = function () {
        var userBtn = data.getUserButton();
        userBtn.textContent = "Connexion";
        userBtn.className   = "disconnected";
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

    Core.utils.viewManager.initViewEvents = function (viewName) {

        var viewSignin = function () {
            var loginBtn, signupBtn, forgetpassword_Btn;
            var showSingupBtn, showLoginBtn, showforgetpasswordBtn, showLoginFromForgetPassword;
            var loginContainer, signupContainer, forgerpasswordContainer;
            var captchaElement;
            
            var iniVariables = function () {
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
            var requestEvents = function () {
                utils.removeListener(loginBtn, "click");
                utils.addListener(loginBtn, "click", function () {
                    var email    = document.getElementById("emailBtn").value;
                    var password = document.getElementById("passwordBtn").value;

                    Core.class.client.login(email, password);
                }, false);

                utils.removeListener(signupBtn, "click");
                utils.addListener(signupBtn, "click", function () {
                    var client, firstname, lastname, email, 
                        password_1, password_2, day, month, year, birthday, phone,
                        country, city, address, postalcode;

                    var captchaInput = document.getElementById("captcha_value");
                    var validation = true;
                    
                    var initVariables = function () {
                        firstname  = document.getElementById("signup_name");
                        lastname   = document.getElementById("signup_firstname");
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
                    var verifValues = function () {
                        if(firstname.value === null || firstname.value === undefined || firstname.value === ""){
                            validation = false;
                            firstname.style.border = "1px solid red";
                        } else {
                            firstname.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(lastname.value === null || lastname.value === undefined || lastname.value === "") {
                            validation = false;
                            lastname.style.border = "1px solid red";
                        } else {
                            lastname.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(utils.emailValidation(email) === false){
                            validation = false;
                            email.style.border = "1px solid red";
                        } else {
                            email.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(password_1.value.length < 6 || password_1.value === firstname.value || password_1.value === lastname.value){
                            validation = false;
                            password_1.style.border = "1px solid red";
                        } else {
                            password_1.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(password_2.value  !== password_1.value){
                            validation = false;
                            password_2.style.border = "1px solid red";
                        } else {
                            password_2.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(day.value === null || day.value === undefined || day.value === "" || Core.utils.checkDate("day", day.value) === false){
                            validation = false;
                            day.style.border = "1px solid red";
                        } else {
                            day.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(month.value === null || month.value === undefined || month.value === "" || Core.utils.checkDate("month", month.value) === false){
                            validation = false;
                            month.style.border = "1px solid red";
                        } else {
                            month.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(year.value === null || year.value === undefined || year.value === "" || Core.utils.checkDate("year", year.value) === false){
                            validation = false;
                            year.style.border = "1px solid red";
                        } else {
                            year.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(phone.value === null || phone.value === undefined || phone.value === ""){
                            validation = false;
                            phone.style.border = "1px solid red";
                        } else {
                            phone.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(country.value === null || country.value === undefined || country.value === ""){
                            validation = false;
                            country.style.border = "1px solid red";
                        } else {
                            country.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(city.value === null || city.value === undefined || city.value === ""){
                            validation = false;
                            city.style.border = "1px solid red";
                        } else {
                            city.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(address.value === null || address.value === undefined || address.value === ""){
                            validation = false;
                            address.style.border = "1px solid red";
                        } else {
                            address.style.border = "1px solid rgb(204, 204, 204)";
                        }

                        if(postalcode.value === null || postalcode.value === undefined || postalcode.value === ""){
                            validation = false;
                            postalcode.style.border = "1px solid red";
                        } else {
                            postalcode.style.border = "1px solid rgb(204, 204, 204)";
                        }
                    }();

                    if(validation === false)
                        document.getElementById("error_container").textContent = "Veuillez remplir tous les champs";

                    if(captchaInput.value != data.captchaResult){
                        document.getElementById("captcha_error").textContent = "Mauvaise réponse";
                        return;
                    }

                    if(validation === true){
                        birthday = year.value + "-" + month.value + "-" + day.value;
                        client = '{'+
                            '"name"       : "'+ lastname.value   +'",'+
                            '"firstName"  : "'+ firstname.value  +'",'+
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

        switch (viewName){
            case "connexion" :
                viewSignin();
                break;
        }
    };
})();