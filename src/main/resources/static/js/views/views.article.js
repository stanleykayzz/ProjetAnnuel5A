;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.article = Core.views.article || {};

    Core.views.article.initView = function () {
        var fakeContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dapibus viverra ante sit amet aliquet. Sed eget lectus velit. Fusce egestas arcu id odio suscipit bibendum. Sed consectetur lacus faucibus diam aliquet venenatis. Nullam tincidunt mi vel nisi tincidunt fermentum. In quis elit eget metus efficitur vulputate. Fusce finibus consequat tempor. Sed venenatis, est in sodales condimentum, odio diam pulvinar diam, non egestas magna augue non quam. Nam vitae felis ligula. Sed volutpat, massa sed maximus ultrices, odio mauris volutpat orci, ac aliquet justo ex sed lorem."

        +"Nulla vel sem ac diam porttitor tincidunt non ac quam. Aliquam lobortis ornare est vitae gravida. Ut tristique lobortis luctus. Etiam tincidunt leo id neque pretium, eu ultricies dui finibus. In tellus erat, sollicitudin in rhoncus vel, tempor eget nibh. Aenean eget lectus imperdiet, luctus tellus eu, dictum metus. Proin facilisis massa nibh, ac blandit odio tincidunt vitae. Morbi dictum dolor in sem luctus, sed ultricies risus hendrerit."

        +"Morbi in nisl a lacus aliquet imperdiet. Suspendisse gravida, turpis sed blandit accumsan, ipsum diam condimentum magna, at varius est nibh a mauris. Etiam sit amet nulla diam. Phasellus a nunc eget velit egestas mollis. Mauris sed justo vulputate, ultricies purus vel, volutpat leo. Aliquam tristique nisl ante, sed tincidunt sem ullamcorper imperdiet. Nullam quis neque sit amet lectus imperdiet finibus. Etiam sed risus in tortor auctor auctor ut eget leo. Donec laoreet risus ut purus faucibus vulputate."

            +"Fusce in elit sit amet ipsum condimentum rutrum. Nulla ligula eros, scelerisque a neque in, mattis lacinia metus. Quisque at magna magna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Integer vel dolor pretium, viverra ipsum sed, consequat felis. Sed accumsan tincidunt ex, nec imperdiet libero laoreet sit amet. Mauris odio lectus, fringilla quis velit in, vestibulum sodales massa. Sed gravida ligula in lacus efficitur, et condimentum nisi pretium. Sed mattis sagittis metus, at placerat lorem ultricies ac. Nunc lacinia commodo est, ac aliquam risus facilisis non. Praesent id est ac dolor vehicula auctor."

            +"Suspendisse potenti. Suspendisse sit amet facilisis erat, quis interdum nulla. Nunc bibendum accumsan faucibus. Nam sed luctus lorem, laoreet consectetur tortor. Nunc turpis erat, interdum at sem vitae, iaculis viverra nibh. Aliquam laoreet tellus quis dictum elementum. Sed sit amet metus ut massa vestibulum feugiat et sit amet justo. Morbi viverra consectetur velit, in laoreet leo rutrum id. Fusce at felis porta, venenatis dolor ut, auctor neque. Morbi blandit velit elit, sodales porta augue iaculis id. In semper purus odio, nec condimentum justo efficitur sed. Vivamus suscipit urna non eros dignissim, vitae fringilla justo efficitur. Phasellus auctor vel eros et lacinia. Aenean auctor lacus eget laoreet sodales. Phasellus aliquet, nunc ut finibus ornare, risus nunc vehicula ante, et vehicula mi odio non turpis.";
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.article.getList(), paramRequest, null);
        Core.service.article.getList().func({
            article_1: {
                title: "Titre de l'article de test",
                content: fakeContent,
                date: "10/07/2017"
            },
            article_2: {
                title: "Titre de l'article de test",
                content: fakeContent,
                date: "10/07/2017"
            },
            article_3: {
                title: "Titre de l'article de test",
                content: fakeContent,
                date: "10/07/2017"
            },
            article_4: {
                title: "Titre de l'article de test",
                content: fakeContent,
                date: "10/07/2017"
            },
            article_5: {
                title: "Titre de l'article de test",
                content: fakeContent,
                date: "10/07/2017"
            }
        })
    };
})();