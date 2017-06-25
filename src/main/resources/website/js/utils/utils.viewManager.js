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
            var initYear = function () {
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
            var requestEvents = function () {
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
                    var validation = true;
                    
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

                        if(female.checked == false && male.checked == false){
                            validation = false;
                            female.style.border = "1px solid red";
                            male.style.border   = "1px solid red";
                        } else {
                            if(female.checked == true)
                                sexe = 1;
                            else if(male.checked == true)
                                sexe = 0;
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

                        if(month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name")     === null
                            || month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name") === undefined
                            || month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name") === ""
                            || Core.utils.checkDate("month", month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name")) === false){

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
            }();
            var setContent  = function () {
                name.textContent       = client.name;
                firstName.textContent  = client.firstName;
                birthday.textContent   = client.birthday;
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
             }();
            var viewEvents = function () {
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


            /*var client = '{'+
                '"name":       "'+ name       +'",'+
                '"firstName":  "'+ firstName  +'",'+
                '"birthday":   "'+ birthday   +'",'+
                '"email":      "'+ email      +'",'+
                '"phone":      "'+ phone      +'",'+
                '"country":    "'+ country    +'",'+
                '"city":       "'+ city       +'",'+
                '"address":    "'+ address    +'",'+
                '"postalCode": "'+ postalCode +'",'+
                '"password":   "'+ password   +'"'+
                '}';*/

            //Core.class.client.update(client);
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