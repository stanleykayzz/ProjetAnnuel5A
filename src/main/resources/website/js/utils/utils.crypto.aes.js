;(function () {
    "use strict";

    var SIZE_BLOCK = 4;
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

    Core.utils.crypto.crypt = function (key, src) {
        var toCrypt = new Array < Uint8Array > (src.length / SIZE_BLOCK);
        var crypt = Array < Uint8Array > (toCrypt.length);
        var destination = new Uint8Array(src.length + SIZE_BLOCK);

        if ((src.length % SIZE_BLOCK) != 0) {
            throw "Invalide array";
        }

        var iv = new Uint8Array(SIZE_BLOCK);

        for (var i = 0; i < iv.length; i++) {
            iv[i] = Core.utils.crypto.randomIntFromInterval(0, 255);
            destination[src.length + i] = iv[i];
        }

        for (var j = 0; j < toCrypt.length; j++) {
            toCrypt[j] = new Uint8Array(SIZE_BLOCK);
            for (var i = 0; i < SIZE_BLOCK; i++) {
                toCrypt[j][i] = src[j * SIZE_BLOCK + i];
            }
        }

        for (var numBloc = 0; numBloc < toCrypt.length; numBloc++) {
            var result1 = new Uint8Array(SIZE_BLOCK);
            result1 = Core.utils.crypto.XOR(iv, toCrypt[numBloc]);
            var result2 = Core.utils.crypto.XOR(result1, key);
            crypt[numBloc] = new Uint8Array(SIZE_BLOCK);
            for (var i = 0; i < SIZE_BLOCK; i++) {
                crypt[numBloc][i] = result2[i];
                iv[i] = result2[i];
            }
        }

        for (var j = 0; j < crypt.length; j++) {
            for (var i = 0; i < SIZE_BLOCK; i++) {
                destination[j * SIZE_BLOCK + i] = crypt[j][i];
            }
        }

        return destination;
    };

    Core.utils.crypto.stringToByteArray = function(src) {
        var resultSize = src.length + (SIZE_BLOCK-src.length%SIZE_BLOCK)%SIZE_BLOCK;
        var result = new Uint8Array(resultSize);
        for(var i = 0 ; i < resultSize ; i++) {
            if(i<src.length) {
                result[i] = src.charCodeAt(i);
            }
            else {
                result[i] = 0;
            }
        }

        return result;
    };

    Core.utils.crypto.getCbc = function (password) {
        var key = new Uint8Array(SIZE_BLOCK );

        key[0] = 55;
        key[1] = 66;
        key[2] = 77;
        key[3] = 88;

        return  Core.utils.crypto.crypt(key, Core.utils.crypto.stringToByteArray(password));
    };

})();