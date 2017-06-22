;(function () {
    "use strict";

    var TAILLE_BLOC = 4;
    Core.utils.crypto = Core.utils.crypto || {};

    Core.utils.crypto.randomIntFromInterval = function (min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };

    Core.utils.crypto.XOR = function (x, y) {
        var result = new Uint8Array(x.length);
        for (var i = 0; i < x.length; i++) {
            result[i] = x[i] ^ y[i];
        }
        return result;
    };

    Core.utils.crypto.chiffrer = function (uneCle, uneSource) {
        var aChiffrer = new Array < Uint8Array > (uneSource.length / TAILLE_BLOC);
        var chiffre = Array < Uint8Array > (aChiffrer.length);
        var destination = new Uint8Array(uneSource.length + TAILLE_BLOC);

        if ((uneSource.length % TAILLE_BLOC) != 0) {
            throw "Le tableau source n'a pas une taille valide. Spécifiez la taille des blocs et réessayez.";
        }

        var iv = new Uint8Array(TAILLE_BLOC);

        // Initialisation du vecteur d'initialisation
        for (var i = 0; i < iv.length; i++) {
            iv[i] = Core.utils.crypto.randomIntFromInterval(0, 255);
            destination[uneSource.length + i] = iv[i];
        }

        // Création du tableau de blocs à partir du fichier
        for (var j = 0; j < aChiffrer.length; j++) {
            aChiffrer[j] = new Uint8Array(TAILLE_BLOC);
            for (var i = 0; i < TAILLE_BLOC; i++) {
                aChiffrer[j][i] = uneSource[j * TAILLE_BLOC + i];
            }
        }

        for (var numBloc = 0; numBloc < aChiffrer.length; numBloc++) {
            var result1 = new Uint8Array(TAILLE_BLOC);
            result1 = Core.utils.crypto.XOR(iv, aChiffrer[numBloc]);
            var result2 = Core.utils.crypto.XOR(result1, uneCle);
            chiffre[numBloc] = new Uint8Array(TAILLE_BLOC);
            for (var i = 0; i < TAILLE_BLOC; i++) {
                chiffre[numBloc][i] = result2[i];
                iv[i] = result2[i];
            }
        }

        for (var j = 0; j < chiffre.length; j++) {
            for (var i = 0; i < TAILLE_BLOC; i++) {
                destination[j * TAILLE_BLOC + i] = chiffre[j][i];
            }
        }

        return destination;
    };

    Core.utils.crypto.stringToByteArray = function(source) {
        var resultSize = source.length + (TAILLE_BLOC-source.length%TAILLE_BLOC)%TAILLE_BLOC;
        var result = new Uint8Array(resultSize);
        for(var i = 0 ; i < resultSize ; i++) {
            if(i<source.length) {
                result[i] = source.charCodeAt(i);
            }
            else {
                result[i] = 0;
            }
        }

        return result;
    };

    Core.utils.crypto.getCbc = function (password) {
        var key = new Uint8Array(TAILLE_BLOC);

        key[0] = 55;
        key[1] = 66;
        key[2] = 77;
        key[3] = 88;

        return  Core.utils.crypto.chiffrer(key, Core.utils.crypto.stringToByteArray(password));
    };

})();