package server.utils;

import server.exception.ArgumentException;

/**
 * Created by molla on 22/06/2017.
 */
public class Chiffrement {
    private static short TAILLE_BLOC = 4;

    public static byte[] dechiffrer(byte[] uneCle, byte[] uneSource) {
        byte[][] aDechiffrer = new byte[(uneSource.length - TAILLE_BLOC) / TAILLE_BLOC][];
        byte[][] dechiffre /* déchiffré */ = new byte[aDechiffrer.length][];
        byte[] destination = new byte[uneSource.length - TAILLE_BLOC];

        if (uneSource.length % TAILLE_BLOC != 0) {
            //"Le tableau source n'a pas une taille valide. Spécifiez la taille des blocs et réessayez."
            throw new ArgumentException();
        }

        byte[] iv = new byte[TAILLE_BLOC];

        // Initialisation du vecteur d'initialisation
        for (int i = 0; i < iv.length; i++) {
            iv[i] = uneSource[destination.length + i];
        }

        // Création du tableau de blocs à partir du fichier
        for (int j = 0; j < aDechiffrer.length; j++) {
            aDechiffrer[j] = new byte[TAILLE_BLOC];
            for (int i = 0; i < TAILLE_BLOC; i++) {
                aDechiffrer[j][i] = uneSource[j * TAILLE_BLOC + i];
            }
        }

        // Déchiffrement
        for (int numBloc = 0; numBloc < aDechiffrer.length; numBloc++) {
            byte[] resultA = exclusiveOR(aDechiffrer[numBloc], uneCle);
            dechiffre[numBloc] = new byte[TAILLE_BLOC];
            // 1ère phase
            byte[] resultB = new byte[TAILLE_BLOC];
            resultB = exclusiveOR(iv, resultA);
            // 2ème phase
            for (int i = 0; i < TAILLE_BLOC; i++) {
                dechiffre[numBloc][i] = resultB[i];
                iv[i] = aDechiffrer[numBloc][i];
            }
        }

        // Création du fichier de destination à partir du tableau de blocs
        for (int j = 0; j < dechiffre.length; j++) {
            for (int i = 0; i < TAILLE_BLOC; i++) {
                destination[j * TAILLE_BLOC + i] = dechiffre[j][i];
            }
        }

        return destination;
    }

    // Source : https://stackoverflow.com/questions/20802857/xor-function-for-two-hex-byte-arrays
    private static byte[] exclusiveOR(byte[] arr1, byte[] arr2) {
        //"arr1 and arr2 are not the same length"
        if (arr1.length != arr2.length)
            throw new ArgumentException();

        byte[] result = new byte[arr1.length];

        for (int i = 0; i < arr1.length; ++i)
            result[i] = (byte) (arr1[i] ^ arr2[i]);

        return result;
    }
}
