;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.includeContainer = Core.views.includeContainer || {};

    Core.views.includeContainer.switchView = function (key) {
        var pageObject = data.viewList[key.toString()];

        if (pageObject !== null) {
            Core.class.client.reloadClient();
            utils.empty(data.getIncludeContainer());
            utils.include(pageObject.viewPath, pageObject.name);
            utils.manageImages(pageObject.listImage, data.mainImageID);
            data.currentPath = pageObject.viewPath;
        }
    };

    Core.views.includeContainer.initViewEvents = function (viewName) {
        switch (viewName) {
            case "connexion" :
                views.includeContainer.signin();
                views.includeContainer.signup();
                views.includeContainer.forgetPassword();
                break;
            case "logout" :
                client.logout();
                break;
            case "compte" :
                views.includeContainer.account();
                views.includeContainer.updateAccount();
                break;
            case "confirmation" :
                views.includeContainer.confirmationCode();
                break;
            case "chambre":
                views.includeContainer.room();
                break;
            case "restaurant":
                views.includeContainer.restaurant();
                break;
            case "festiveRoom":
                Core.class.festiveRoom.initView();
                break;
        }
    };

    Core.views.includeContainer.signin = function () {
        var loginBtn;
        var showSingupBtn, showforgetpasswordBtn;
        var loginContainer, signupContainer, forgerpasswordContainer;
        var captchaElement;

        var iniVariables = function () {
            loginBtn = document.getElementById("btn_login");

            showSingupBtn = document.getElementById("show_signup");
            showforgetpasswordBtn = document.getElementById("show_forgetpassword");

            loginContainer = document.getElementById("loginbox");
            signupContainer = document.getElementById("signupBox");
            forgerpasswordContainer = document.getElementById("forgetpasswordBox");

            captchaElement = document.getElementById("captchaID");
        }();
        var showViewEvents = function () {
            utils.removeListener(showSingupBtn, "click");
            utils.addListener(showSingupBtn, "click", function () {
                document.getElementById("error_container").textContent = "";
                loginContainer.style.display = "none";
                signupContainer.style.display = "inline-block";
                utils.captcha(captchaElement);
            }, false);

            utils.removeListener(showforgetpasswordBtn, "click");
            utils.addListener(showforgetpasswordBtn, "click", function () {
                document.getElementById("error_container").textContent = "";
                loginContainer.style.display = "none";
                forgerpasswordContainer.style.display = "inline-block";
            }, false);
        }();
        var requestEvent = function () {
            utils.removeListener(loginBtn, "click");
            utils.addListener(loginBtn, "click", function () {
                var email = document.getElementById("emailBtn").value;
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

        var iniVariables = function () {
            signupBtn = document.getElementById("btn_signup");
            showLoginBtn = document.getElementById("show_login");

            loginContainer = document.getElementById("loginbox");
            signupContainer = document.getElementById("signupBox");

            captchaElement = document.getElementById("captchaID");
        }();
        var initYear = function () {
            var monthElement = document.getElementById("signup_date_year");
            var minYear = 1900;
            var date = new Date();
            var length = date.getFullYear() - minYear + 1;

            for (var i = 0; i < length; i++) {
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
                loginContainer.style.display = "inline-block";
            }, false);
        }();
        var requestEvents = function () {
            utils.removeListener(signupBtn, "click");
            utils.addListener(signupBtn, "click", function () {
                var client, firstname, lastname, sexe, female, male, email,
                    password_1, password_2, day, month, year, birthday, phone,
                    country, city, address, postalcode;

                var captchaInput = document.getElementById("captcha_value");
                var formValid, sexeValid;

                var initVariables = function () {
                    firstname = document.getElementById("signup_name");
                    lastname = document.getElementById("signup_firstname");
                    female = document.getElementById("signup_sexe_female");
                    male = document.getElementById("signup_sexe_male");
                    email = document.getElementById("signup_email");
                    password_1 = document.getElementById("signup_password");
                    password_2 = document.getElementById("signup_password2");
                    day = document.getElementById("signup_date_day");
                    month = document.getElementById("signup_date_month");
                    year = document.getElementById("signup_date_year");
                    phone = document.getElementById("signup_phone");
                    country = document.getElementById("signup_country");
                    city = document.getElementById("signup_city");
                    address = document.getElementById("signup_address");
                    postalcode = document.getElementById("signup_postalcode");
                }();

                var checkSexe = function () {
                    if (female.checked == false && male.checked == false) {
                        female.style.border = "1px solid red";
                        male.style.border = "1px solid red";
                        sexeValid = false;
                    } else {
                        if (female.checked == true) {
                            sexe = 1;
                            sexeValid = true;
                        } else if (male.checked == true) {
                            sexe = 0;
                            sexeValid = true;
                        }
                    }
                }();

                formValid = utils.form.formValidator({
                    firstname: firstname,
                    lastname: lastname,
                    email: email,
                    password: password_1,
                    password_2: password_2,
                    day: day,
                    month: month,
                    year: year,
                    phone: phone,
                    country: country,
                    city: city,
                    address: address,
                    postalcode: postalcode
                });

                if (formValid === false || sexeValid === false)
                    document.getElementById("error_container").textContent = "Veuillez remplir tous les champs";

                if (captchaInput.value != data.captchaResult) {
                    document.getElementById("captcha_error").textContent = "Mauvaise réponse";
                    return;
                }

                if (formValid === true && sexeValid === true) {
                    birthday = year.value + "-" + month.getElementsByTagName("option")[month.selectedIndex].getAttribute("name") + "-" + day.value;
                    client = '{' +
                        '"name"       : "' + lastname.value + '",' +
                        '"firstName"  : "' + firstname.value + '",' +
                        '"sexe"       : "' + sexe + '",' +
                        '"birthday"   : "' + birthday + '",' +
                        '"email"      : "' + email.value + '",' +
                        '"phone"      : "' + phone.value + '",' +
                        '"country"    : "' + country.value + '",' +
                        '"city"       : "' + city.value + '",' +
                        '"address"    : "' + address.value + '",' +
                        '"postalCode" : "' + postalcode.value + '",' +
                        '"password"   : "' + password_1.value + '"' +
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

        var iniVariables = function () {
            showLoginFromForgetPassword = document.getElementById("forgetpassword_show_login");

            loginContainer = document.getElementById("loginbox");
            forgerpasswordContainer = document.getElementById("forgetpasswordBox");
        }();
        var showViewEvents = function () {
            utils.removeListener(showLoginFromForgetPassword, "click");
            utils.addListener(showLoginFromForgetPassword, "click", function () {
                document.getElementById("error_container").textContent = "";
                forgerpasswordContainer.style.display = "none";
                loginContainer.style.display = "inline-block";
            }, false);
        }();
        var requestEvents = function () {
        }();
    };

    Core.views.includeContainer.account = function () {
        var name, firstName, birthday, email, phone,
            country, city, address, postalCode, sexe;

        var initVariables = function () {
            name = document.getElementById("user_lastname");
            firstName = document.getElementById("user_firstname");
            birthday = document.getElementById("user_birthday");
            email = document.getElementById("user_email");
            phone = document.getElementById("user_tel");
            country = document.getElementById("user_country");
            city = document.getElementById("user_city");
            address = document.getElementById("user_address");
            postalCode = document.getElementById("user_postal_code");
            sexe = document.getElementById("user_sexe");
        }();
        var setContent = function () {
            name.textContent = utils.capitalizeFirstLetter(client.name);
            firstName.textContent = utils.capitalizeFirstLetter(client.firstName);
            birthday.textContent = utils.formatDate(client.birthday, "view_account");
            email.textContent = client.email;
            phone.textContent = client.phone;
            country.textContent = utils.capitalizeFirstLetter(client.country);
            city.textContent = utils.capitalizeFirstLetter(client.city);
            address.textContent = client.address;
            postalCode.textContent = client.postalCode;

            if (client.sexe == 0)
                sexe.textContent = "Homme";
            else if (client.sexe == 1)
                sexe.textContent = "Femme";
        }();
        var viewEvents = function () {
            var container_account, container_update, btn_update;

            btn_update = document.getElementById("btn_update");

            container_update = document.getElementById("show_update");
            container_account = document.getElementById("show_account");

            utils.addListener(btn_update, "click", function () {
                container_account.style.display = "none";
                container_update.style.display = "inline-block";
            }, false);
        }();
    };

    Core.views.includeContainer.updateAccount = function () {
        var phone_update, country_update, city_update, address_update, postalCode_update,
            new_password_update, verif_paswword_update, current_password_update, jsonPassword;

        var error_container = document.getElementById("error_container");
        var btn_update;

        var initVariables = function () {
            new_password_update = document.getElementById("user_update_password_new");
            verif_paswword_update = document.getElementById("user_update_password_verif");
            current_password_update = document.getElementById("user_update_password_current");
            phone_update = document.getElementById("user_update_tel");
            country_update = document.getElementById("user_update_country");
            city_update = document.getElementById("user_update_city");
            address_update = document.getElementById("user_update_address");
            postalCode_update = document.getElementById("user_update_postal_code");
            btn_update = document.getElementById("btn_update_account");
        }();
        var setContent = function () {
            phone_update.value = client.phone;
            country_update.value = utils.capitalizeFirstLetter(client.country);
            city_update.value = utils.capitalizeFirstLetter(client.city);
            address_update.value = client.address;
            postalCode_update.value = client.postalCode;
        }();
        var viewEvents = function () {
            var container_account, container_update;

            var btn_back = document.getElementById("btn_update_account_back");

            container_account = document.getElementById("show_account");
            container_update = document.getElementById("show_update");

            utils.removeListener(btn_back, "click");
            utils.addListener(btn_back, "click", function () {
                container_update.style.display = "none";
                container_account.style.display = "inline-block";
            }, false);
        }();
        var requestEvents = function () {
            utils.removeListener(btn_update, "click");
            utils.addListener(btn_update, "click", function () {
                error_container.textContent = "";
                var json = {
                    phone: phone_update,
                    country: country_update,
                    city: city_update,
                    address: address_update,
                    postalcode: postalCode_update
                };

                if (current_password_update.value === null
                    || current_password_update.value === undefined
                    || current_password_update.value === "") {

                    error_container.textContent = "Veuillez saisir votre mot de passe pour modifier les informations.";
                    return;
                }
                if (new_password_update.value !== null
                    && new_password_update.value !== undefined
                    && new_password_update.value !== "") {

                    json.password = new_password_update;
                    json.password_2 = verif_paswword_update;
                    jsonPassword = new_password_update;
                } else {
                    jsonPassword = current_password_update;
                }


                var formValid = utils.form.formValidator(json, phone_update.style);

                if (formValid === true) {
                    var date = utils.formatDate(client.birthday, "update_account");
                    console.log(jsonPassword);
                    var clientJson = '{' +
                        '"phone":      "' + phone_update.value + '",' +
                        '"country":    "' + country_update.value + '",' +
                        '"city":       "' + city_update.value + '",' +
                        '"address":    "' + address_update.value + '",' +
                        '"postalCode": "' + postalCode_update.value + '",' +
                        '"password":   "' + jsonPassword.value + '"' +
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

    Core.views.includeContainer.room = function () {
        var startDatepicker, endDatepicker, type, btnSearch;
        var container;
        var startDateID = "#reservation_start_date";
        var endDateID = "#reservation_end_date";

        utils.reservation.datePicker(startDateID, new Date(), null);
        utils.reservation.datePicker(endDateID, new Date(), null);

        var initVariables = function () {
            startDatepicker = document.getElementById("reservation_start_date");
            endDatepicker = document.getElementById("reservation_end_date");
            type = document.getElementById("reservation_type");
            btnSearch = document.getElementById("btn_search");
            container = document.getElementById("include_room");
        }();
        var manageEvents = function () {
            $(startDateID).datepicker("option", "onSelect", function () {
                var minDate = $(startDateID).datepicker("getDate");
                $(endDateID).datepicker("option", "minDate", minDate);
                utils.empty(container);
            });

            $(endDateID).datepicker("option", "onSelect", function () {
                utils.empty(container);
            });

            utils.addListener(btnSearch, "click", function () {
                utils.empty(container);
                var jsonValidator = {
                    datepicker_start: startDatepicker,
                    datepicker_end: endDatepicker
                };

                var formValid = utils.form.formValidator(jsonValidator, startDatepicker.style);

                var jsonRoom = {
                    dateStart: startDatepicker.value,
                    dateEnd: endDatepicker.value,
                    datepicker_type: type.value
                };

                var startString = startDatepicker.value.split("/");
                var endString = endDatepicker.value.split("/");

                var formatDateStart = startString[2] + "-" + startString[1] + "-" + startString[0];
                var formatDateEnd = endString[2] + "-" + endString[1] + "-" + endString[0];

                var result = utils.getDays(new Date(formatDateStart), new Date(formatDateEnd));

                if (result.day > 0) {
                    Core.class.room.search(jsonRoom);
                }
            }, false);
        }();
    };

    Core.views.includeContainer.roomSearch = function (listRoom) {
        var container, searchContainer, bookingContainer, listReservationContainer;
        var startDatepicker, endDatepicker, type_input, reason, list_reservation, btn_book;
        var startString, endString, formatDateStart, formatDateEnd;
        var list_simple, list_double, list_junior, list_executive;
        var jsonQuantity;

        var getDays = function () {
            return utils.getDays(new Date(formatDateStart), new Date(formatDateEnd));
        };
        var viewList = function (id, content, quantity) {
            var div;
            if (document.getElementById(id) === null || document.getElementById(id) === undefined) {
                div = document.createElement("div");
                div.id = id;
                div.style.display = "block";
            } else {
                div = document.getElementById(id);
                utils.empty(div);
            }

            div.style.marginTop = "5px";

            var span_container = document.createElement("span");
            span_container.classList.add("md_text_span");
            span_container.style.marginLeft = "5px";
            span_container.textContent += content + " " + quantity;

            var button_delete = document.createElement("span");
            button_delete.classList.add("glyphicon");
            button_delete.classList.add("glyphicon-remove");
            button_delete.style.cursor = "pointer";
            button_delete.name = id.split("type_")[1];

            utils.addListener(button_delete, "click", function (e) {
                delete jsonQuantity[e.target.name];
                utils.empty(e.target.parentElement);
            }, false);


            div.appendChild(button_delete);
            div.appendChild(span_container);
            list_reservation.appendChild(div);

        };
        var viewRoom = function (id, number, json) {
            var divRoom, imageRoom, contentRoom, container_title,
                titleRoom, disponible_container, disponible,
                disponible_result, costbynight_container, costbynight,
                costbynight_result_euro, costbynight_result_franc,
                cost_container, cost, cost_result_euro, cost_result_franc,
                description_container, description_text_container, description_result,
                day_container, day, day_result, btn_add,
                quantity, quantity_container, quantity_result;

            var initVariables = function () {
                divRoom = document.createElement("div");
                divRoom.id = id;
                divRoom.classList.add("room_type_div");

                imageRoom = document.createElement("img");
                imageRoom.classList.add("room_type_image");
                imageRoom.src = json.imagePath;

                contentRoom = document.createElement("div");
                contentRoom.classList.add("room_content");

                container_title = document.createElement("div");
                container_title.classList.add("reserv_container");

                titleRoom = document.createElement("span");
                titleRoom.classList.add("room_title");
                titleRoom.textContent = json.name;

                disponible_container = document.createElement("div");
                disponible_container.classList.add("reserv_container");
                disponible_container.style.textAlign = "left";
                disponible_container.style.paddingLeft = "10px";

                disponible = document.createElement("span");
                disponible.classList.add("title_span");
                disponible.classList.add("available");
                disponible.textContent = "Disponible : ";

                disponible_result = document.createElement("span");
                disponible_result.classList.add("title_span");
                disponible_result.classList.add("available");
                disponible_result.textContent = number;

                costbynight_container = document.createElement("div");
                costbynight_container.classList.add("reserv_container");
                costbynight_container.style.textAlign = "left";
                costbynight_container.style.paddingLeft = "10px";

                costbynight = document.createElement("span");
                costbynight.classList.add("title_span");
                costbynight.textContent = "Coût par nuit : ";

                costbynight_result_euro = document.createElement("span");
                costbynight_result_euro.classList.add("text_span");
                costbynight_result_euro.textContent = json.costEuro + " €";

                costbynight_result_franc = document.createElement("span");
                costbynight_result_franc.classList.add("text_span");
                costbynight_result_franc.textContent = json.costByNight + " francs CFA";

                cost_container = document.createElement("div");
                cost_container.classList.add("reserv_container");
                cost_container.style.textAlign = "left";
                cost_container.style.paddingLeft = "10px";

                cost = document.createElement("span");
                cost.classList.add("title_span");
                cost.textContent = "Coût du séjour : ";

                cost_result_euro = document.createElement("span");
                cost_result_euro.classList.add("text_span");
                cost_result_euro.textContent = json.costEuro * getDays().day + " €";

                cost_result_franc = document.createElement("span");
                cost_result_franc.classList.add("text_span");
                cost_result_franc.textContent = json.costByNight * getDays().day + " francs CFA";

                description_container = document.createElement("div");
                description_container.classList.add("reserv_container");

                description_text_container = document.createElement("div");
                description_text_container.classList.add("description_text_container");

                description_result = document.createElement("span");
                description_result.classList.add("description_span");
                description_result.textContent = json.description;

                day_container = document.createElement("div");
                day_container.classList.add("reserv_container");
                day_container.style.textAlign = "left";
                day_container.style.paddingLeft = "10px";

                day = document.createElement("span");
                day.classList.add("title_span");
                day.textContent = "Durée du séjour : ";

                day_result = document.createElement("span");
                day_result.classList.add("text_span");
                day_result.textContent = getDays().day + " jours";

                quantity_container = document.createElement("div");
                quantity_container.classList.add("reserv_container");
                quantity_container.style.textAlign = "left";
                quantity_container.style.paddingLeft = "10px";

                quantity = document.createElement("span");
                quantity.classList.add("title_span");
                quantity.textContent = "Quantité : ";

                quantity_result = document.createElement("input");
                quantity_result.classList.add("quantity_input");
                quantity_result.classList.add("text_span");
                quantity_result.type = "number";
                quantity_result.value = "1";
                quantity_result.min = "1";
                quantity_result.max = number;
                utils.addListener(quantity_result, "change", function (e) {
                    cost_result_euro.textContent = (json.costEuro * getDays().day) * e.target.value + " €";
                    cost_result_franc.textContent = (json.costByNight * getDays().day) * e.target.value + " francs CFA";
                }, false);

                utils.addListener(quantity_result, "keydown", function (e) {
                    utils.pauseEvent(e);
                }, false);

                btn_add = document.createElement("a");
                btn_add.id = "btn_type_" + json.id;
                btn_add.classList.add("btn_reservation");
                btn_add.name = json.id;
                btn_add.textContent = "AJOUTER";
            }();
            var appendElements = function () {
                divRoom.appendChild(imageRoom);
                divRoom.appendChild(contentRoom);

                container_title.appendChild(titleRoom);

                contentRoom.appendChild(container_title);
                contentRoom.appendChild(description_text_container);
                contentRoom.appendChild(costbynight_container);
                contentRoom.appendChild(cost_container);
                contentRoom.appendChild(day_container);
                contentRoom.appendChild(quantity_container);
                contentRoom.appendChild(disponible_container);
                contentRoom.appendChild(btn_add);

                disponible_container.appendChild(disponible);
                disponible_container.appendChild(disponible_result);

                cost_container.appendChild(cost);
                cost_container.appendChild(cost_result_euro);
                cost_container.appendChild(cost_result_franc);

                costbynight_container.appendChild(costbynight);
                costbynight_container.appendChild(costbynight_result_euro);
                costbynight_container.appendChild(costbynight_result_franc);

                day_container.appendChild(day);
                day_container.appendChild(day_result);

                quantity_container.appendChild(quantity);
                quantity_container.appendChild(quantity_result);

                description_text_container.appendChild(description_result);

                container.appendChild(divRoom);
            }();

            return btn_add;
        };
        var initVariables = function () {
            container = document.getElementById("include_room");
            searchContainer = document.getElementById("search_container");
            bookingContainer = document.getElementById("include_reservation");
            type_input = document.getElementById("reservation_type");
            reason = document.getElementById("reservation_reason");
            list_reservation = document.getElementById("list_reservation");
            listReservationContainer = document.getElementById("include_reservation_list");
            listReservationContainer.style.display = "block";

            startDatepicker = document.getElementById("reservation_start_date");
            endDatepicker = document.getElementById("reservation_end_date");

            startString = startDatepicker.value.split("/");
            endString = endDatepicker.value.split("/");

            formatDateStart = startString[2] + "-" + startString[1] + "-" + startString[0];
            formatDateEnd = endString[2] + "-" + endString[1] + "-" + endString[0];

            list_simple = document.getElementById("simpleRoom_content");
            list_double = document.getElementById("doubleRoom_content");
            list_junior = document.getElementById("junior_content");
            list_executive = document.getElementById("executive_content");

            btn_book = document.getElementById("btn_book");

            jsonQuantity = {};
        }();
        var initView = function () {
            var listType = new Object(null);

            for (var r in listRoom) {
                listType[listRoom[r].type] = listType[listRoom[r].type] || {};

                if (listType[listRoom[r].type].number === undefined)
                    listType[listRoom[r].type].number = 1;
                else
                    listType[listRoom[r].type].number += 1;
            }

            for (var type in listType) {
                if (type_input.value === "all" || type_input.value === type) {
                    for (var c in data.listCategories) {
                        if (data.listCategories[c].id == type) {
                            var viewRoomBtn = viewRoom("room_" + type, listType[type].number, data.listCategories[c]);
                        }
                    }
                }
            }

            for (type in listType) {
                if (type_input.value === "all" || type_input.value === type) {
                    var btn = document.getElementById("btn_type_" + type);
                    utils.removeListener(btn, "click");
                    utils.addListener(btn, "click", function (e) {
                        var quantity = e.target.parentElement.getElementsByClassName("quantity_input")[0];
                        var name = e.target.getAttribute("name");

                        jsonQuantity[e.target.name] = {
                            quantity: quantity.value
                        };
                        viewList("list_" + e.target.id, data.listCategories[name].name, "X" + quantity.value);
                    }, false);
                }
            }

            utils.removeListener(btn_book, "click");
            utils.addListener(btn_book, "click", function (e) {
                if (window.client) {
                    var body = {};
                    searchContainer.style.display = "none";
                    bookingContainer.style.display = "block";
                    for (var r in jsonQuantity) {
                        body["reservation_" + r] = {
                            "date_start": formatDateStart,
                            "date_end": formatDateEnd,
                            "type": r,
                            "quantity": jsonQuantity[r].quantity,
                            "reason": reason.value
                        };
                    }
                    Core.class.room.book(body);
                    Core.views.includeContainer.roomBooking(startDatepicker.value, endDatepicker.value, body);
                } else {
                    views.includeContainer.switchView("connexion");
                }
            }, false);
        }();
    };

    Core.views.includeContainer.roomBooking = function (dateStart, dateEnd, list) {
        var labelDateStart, labelDateEnd, labelBook, image,
            container, button_container, btn_return;

        var initVariables = function () {
            container = document.getElementById("include_reservation");
            labelDateStart = document.getElementById("label_start_date");
            labelDateEnd = document.getElementById("label_end_date");
            labelBook = document.getElementById("labelBook");
            image = document.getElementById("image_reservation");
            btn_return = document.getElementById("btn_return");
        }();
        var initView = function () {
            labelDateStart.textContent = dateStart;
            labelDateEnd.textContent = dateEnd;
            for (var l in list) {
                var span = document.createElement("span");
                span.classList.add("text_span");
                span.textContent = "X" + list[l].quantity + " " + utils.capitalizeFirstLetter(data.listCategories[list[l].type].name);

                labelBook.appendChild(span);
            }

            utils.removeListener(btn_return, "click");
            utils.addListener(btn_return, "click", function (e) {
                Core.class.room.cancelBook();
            }, false);
        }();

    };

    Core.views.includeContainer.restaurant = function () {
        var typeElement, numberElement, btn_booking;

        typeElement = document.getElementById("select_time");
        numberElement = document.getElementById("select_number");
        btn_booking = document.getElementById("btn_search_table");


        utils.removeListener(btn_booking, "click");
        utils.addListener(btn_booking, "click", function () {
            var json = {
                type: typeElement.options[typeElement.selectedIndex].value,
                number: numberElement.options[numberElement.selectedIndex].value
            };
            Core.class.restaurant.booking(json);
        }, false);
    };

    Core.views.includeContainer.festiveRoom = function (items) {
        var include_container, search_container;
        var startDateID, endDateID;
        var startDatepicker, endDatepicker,
            startString, endString,
            formatDateStart, formatDateEnd,
            include_items, btn_book,
            list_booked_items, jsonItems,
            error_container, valide_container;
        var label_start_date, label_end_date,
            label_price, list_items, btn_return;

        var resizeContainer = function (type, value) {
            if (type === "+")
                search_container.style.height = search_container.getBoundingClientRect().height + value + "px";

            if (type === "-")
                search_container.style.height = search_container.getBoundingClientRect().height - value + "px";
        };
        var createItem = function (id, object) {
            var div_reservation = document.createElement("div");
            div_reservation.classList.add("div_reservation");

            if (object !== null) {
                var content_left = document.createElement("div");
                content_left.classList.add("content_left");

                var title_span = document.createElement("span");
                title_span.classList.add("title_span_black");
                title_span.style.textAlign = "left";
                title_span.textContent = utils.capitalizeFirstLetter(object.name) + " " + object.unitPrice + "€";

                var input = document.createElement("input");
                input.classList.add("item_input");
                input.min = 0;
                input.max = object.quantity;
                input.value = 0;
                input.style.width = "70%";
                utils.addListener(input, "keyup", function (e) {

                    if (parseInt(e.target.value) > parseInt(e.target.max))
                        e.target.value = e.target.max;

                    if (parseInt(e.target.value) < parseInt(e.target.min))
                        e.target.value = e.target.min;

                    if (e.target.value > 0) {
                        jsonItems[id.split("_")[1]].quantity = e.target.value;
                        viewList("list_" + object.id, e.target, e.target.value, utils.capitalizeFirstLetter(object.name));
                    }
                }, false);

                content_left.appendChild(title_span);
                content_left.appendChild(input);
                div_reservation.appendChild(content_left);
            }

            include_items.appendChild(div_reservation);
        };
        var viewList = function (id, element, value, name) {
            var div;
            if (document.getElementById(id) === null || document.getElementById(id) === undefined) {
                div = document.createElement("div");
                div.id = id;
                div.style.display = "block";
                resizeContainer("+", 20);
            } else {
                div = document.getElementById(id);
                utils.empty(div);
            }

            div.style.marginTop = "5px";

            var span_container = document.createElement("span");
            span_container.classList.add("md_text_span");
            span_container.style.marginLeft = "5px";
            span_container.textContent = name + "  x" + value;

            var button_delete = document.createElement("span");
            button_delete.classList.add("glyphicon");
            button_delete.classList.add("glyphicon-remove");
            button_delete.style.cursor = "pointer";
            button_delete.setAttribute("key", id.split("_")[1]);

            utils.addListener(button_delete, "click", function (e) {
                jsonItems[e.target.getAttribute("key")].quantity = 0;
                e.target.parentElement.parentElement.removeChild(e.target.parentElement);
                resizeContainer("-", 20);
                element.value = "0";
            }, false);

            div.appendChild(button_delete);
            div.appendChild(span_container);
            list_booked_items.appendChild(div);
        };
        var getDays = function () {
            return utils.getDays(new Date(formatDateStart), new Date(formatDateEnd));
        };
        var initVariables = function () {
            search_container = document.getElementById("search_container");
            include_container = document.getElementById("include_book");
            error_container = document.getElementById("error_container");
            valide_container = document.getElementById("valide_container");

            startDatepicker = document.getElementById("reservation_start_date");
            endDatepicker = document.getElementById("reservation_end_date");

            startString = startDatepicker.value.split("/");
            endString = endDatepicker.value.split("/");

            formatDateStart = startString[2] + "-" + startString[1] + "-" + startString[0];
            formatDateEnd = endString[2] + "-" + endString[1] + "-" + endString[0];

            include_items = document.getElementById("include_items");
            btn_book = document.getElementById("btn_book");

            startDateID = "#reservation_start_date";
            endDateID = "#reservation_end_date";

            list_booked_items = document.getElementById("list_booked_items");
            jsonItems = {};

            utils.reservation.datePicker(startDateID, new Date(), null);
            utils.reservation.datePicker(endDateID, new Date(), null);

            label_start_date = document.getElementById("label_start_date");
            label_end_date = document.getElementById("label_end_date");
            label_price = document.getElementById("label_price");
            list_items = document.getElementById("list_items");
            btn_return = document.getElementById("btn_return");
        }();
        var initView = function () {
            var length = 0;
            for (var i in items) {
                length += 1;
                jsonItems[i] = items[i];
                createItem("item_" + i, items[i]);
                jsonItems[i].quantity = 0;
            }

            if (length % 2 == 1)
                createItem("item_null", null);

            var height = length * 5 + 50;
            resizeContainer("+", height);
        }();
        var initEvents = function () {
            $(startDateID).datepicker("option", "onSelect", function () {
                var minDate = $(startDateID).datepicker("getDate");
                $(endDateID).datepicker("option", "minDate", minDate);
                label_start_date.textContent = utils.formatDate();
            });

            utils.removeListener(btn_book, "click");
            utils.addListener(btn_book, "click", function (e) {
                utils.empty(list_items);

                var jsonValidator = {
                    datepicker_start: startDatepicker,
                    datepicker_end: endDatepicker
                };

                var formValid = utils.form.formValidator(jsonValidator, startDatepicker.style);

                var json = {
                    dateStart: startDatepicker.value,
                    dateEnd: endDatepicker.value
                };

                var startString = startDatepicker.value.split("/");
                var endString = endDatepicker.value.split("/");

                var formatDateStart = startString[2] + "-" + startString[1] + "-" + startString[0];
                var formatDateEnd = endString[2] + "-" + endString[1] + "-" + endString[0];

                var result = utils.getDays(new Date(formatDateStart), new Date(formatDateEnd));

                if (result.day >= 0 && formValid === true) {
                    error_container.textContent = "";
                    Core.class.festiveRoom.book(json, jsonItems);

                    label_start_date.textContent = startDatepicker.value;
                    label_end_date.textContent = endDatepicker.value;

                    for(var i = 0 ; i < list_booked_items.children.length ; i++){
                        var item = list_booked_items.children[i].cloneNode(true);
                        item.removeChild(item.firstChild);
                        list_items.appendChild(item);
                    }

                } else {
                    error_container.textContent = "Veuillez choisir les dates de début et de fin de l'évènement.";
                }
            }, false);

            utils.removeListener(btn_return, "click");
            utils.addListener(btn_return, "click", function (e) {
                include_container.style.display = "none";
                search_container.style.display = "inline-block";
            }, false);
        }();
    };
})();