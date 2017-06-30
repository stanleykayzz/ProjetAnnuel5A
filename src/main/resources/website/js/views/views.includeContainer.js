;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.includeContainer = Core.views.includeContainer || {};

    Core.views.includeContainer.signin = function () {
        var loginBtn;
        var showSingupBtn, showforgetpasswordBtn;
        var loginContainer, signupContainer, forgerpasswordContainer;
        var captchaElement;

        var iniVariables   = function () {
            loginBtn  = document.getElementById("btn_login");

            showSingupBtn = document.getElementById("show_signup");
            showforgetpasswordBtn = document.getElementById("show_forgetpassword");

            loginContainer  = document.getElementById("loginbox");
            signupContainer = document.getElementById("signupBox");
            forgerpasswordContainer = document.getElementById("forgetpasswordBox");

            captchaElement = document.getElementById("captchaID");
        }();
        var showViewEvents = function () {
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
        }();
        var requestEvent  = function () {
            utils.removeListener(loginBtn, "click");
            utils.addListener(loginBtn, "click", function () {
                var email    = document.getElementById("emailBtn").value;
                var password = document.getElementById("passwordBtn").value;

                Core.class.client.login(email, password);
            }, false);
        }();
    };

    Core.views.includeContainer.signup = function () {
        var signupBtn;
        var showLoginBtn;
        var loginContainer, signupContainer;
        var captchaElement;

        var iniVariables   = function () {
            signupBtn = document.getElementById("btn_signup");
            showLoginBtn  = document.getElementById("show_login");

            loginContainer  = document.getElementById("loginbox");
            signupContainer = document.getElementById("signupBox");

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
        }();
        var requestEvents  = function () {
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

                formValid = utils.form.formValidator({
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

    Core.views.includeContainer.forgetPassword = function () {
        var showLoginFromForgetPassword;
        var loginContainer, forgerpasswordContainer;

        var iniVariables   = function () {
            showLoginFromForgetPassword = document.getElementById("forgetpassword_show_login");

            loginContainer  = document.getElementById("loginbox");
            forgerpasswordContainer = document.getElementById("forgetpasswordBox");
        }();
        var showViewEvents = function () {
            utils.removeListener(showLoginFromForgetPassword, "click");
            utils.addListener(showLoginFromForgetPassword, "click", function () {
                document.getElementById("error_container").textContent = "";
                forgerpasswordContainer.style.display = "none";
                loginContainer.style.display  = "inline-block";
            }, false);
        }();
        var requestEvents  = function () {
        }();
    };

    Core.views.includeContainer.account = function () {
        var name, firstName, birthday, email, phone,
            country, city, address, postalCode, sexe;

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
        }();
        var viewEvents    = function () {
            var container_account, container_update, btn_update;

            btn_update = document.getElementById("btn_update");

            container_update  = document.getElementById("show_update");
            container_account = document.getElementById("show_account");

            utils.addListener(btn_update, "click", function () {
                container_account.style.display = "none";
                container_update.style.display  = "inline-block";
            }, false);
        }();
    };

    Core.views.includeContainer.updateAccount = function () {
        var phone_update, country_update, city_update, address_update, postalCode_update,
            new_password_update, verif_paswword_update, current_password_update, jsonPassword;

        var btn_update;

        var initVariables = function () {
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
            phone_update.value      = client.phone;
            country_update.value    = client.country;
            city_update.value       = client.city;
            address_update.value    = client.address;
            postalCode_update.value = client.postalCode;
        }();
        var viewEvents    = function () {
            var container_account, container_update;

            var btn_back = document.getElementById("btn_update_account_back");

            container_account = document.getElementById("show_account");
            container_update  = document.getElementById("show_update");

            utils.removeListener(btn_back, "click");
            utils.addListener(btn_back, "click", function () {
                container_update.style.display  = "none";
                container_account.style.display = "inline-block";
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

                var formValid = utils.form.formValidator(json, phone_update.style);

                if(formValid === true){
                    var date = utils.formatDate(client.birthday, "update_account");

                    var clientJson = '{'+
                        '"phone":      "'+ phone_update.value      +'",'+
                        '"country":    "'+ country_update.value    +'",'+
                        '"city":       "'+ city_update.value       +'",'+
                        '"address":    "'+ address_update.value    +'",'+
                        '"postalCode": "'+ postalCode_update.value +'",'+
                        '"password":   "'+ jsonPassword.value      +'"'+
                        '}';

                    client.update(clientJson, current_password_update.value);
                }
            }, false);
        }();
    };

    Core.views.includeContainer.confirmationCode = function () {
        var btn_code = document.getElementById("btn_code");
        var ipt_code = document.getElementById("codeBtn");

        utils.addListener(btn_code, "click", function () {
            Core.class.client.confirmation(ipt_code.value);
        }, false);
    };

})();