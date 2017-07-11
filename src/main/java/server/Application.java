package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Main permettant de lancer toute l'application.
 * Pour executer faire clique droit, puis executer.
 * Regarder dans la console(qui doit s'afficher) les différents LOGs indiquant le démarrage de l'application
 * Si le message application launched apparait vous pouvez allez sur l'url définis est le port spécifié,
 * par défaut locahost:8080
 *
 * Pour pouvoir changer la configation
 * dans la dossier ressources/application.yml, contient les diverses informations permettant de changer le nom de l'application, définirs les prioritées des LOGs (warning, debug, error, info)
 * les logs qui apparaissent dans la consoles sont stocké aussi dans le dossier LOGS à la racine du projet, ils peuvent être utilisé par divers outils de tracking d'anomalies et de suivis d'incident.
 *
 * Reglage des règles de l'upload de fichier
 *  il limite de 10 Mo par fichier à été définies, avec les règles suivantes
 *                  multipart:
 *                            max-file-size: 10Mb
 *                            max-request-size: 10Mb
 *
 * Des profils on été aussi configuré pour facilité la maintenance, sur la ligne 1 de application.yml, on peut voir les parametres
 *                          spring:
 *                               profiles:
 *                                    active: dev
 *
 * Ou en parametre de la JVM -Dspring.profiles.active:dev
 *
 *Pour active l'un des profile changer la valeur dev par  test/prod/dev
 *
 *
 * ARCHITECTURE DE L'APPLICATION
 * configuration/
 *      DatabaseConfig          :   permet d'initialiser les connecteur à la base de donnée au lancement de l'application utilisé uniquement si le profil "prod" est activé dans ressources/application.yml
 *
 * controller/                  : permet de mettre en place les chemins d'accès pour les applications extérieurs
 * exceptions/                  : mise en place d'exception personnalisées spéciales, suivant les cas de figure
 * model/                       : représentes les tables dans la base de données, toujours avoirs un constructeur vide et public pour être utilisé par hibernate
 *      valuesUtils/            : valeurs permettant de "fixer" certains champs, comme le rôle de l'utilisateur Adinistrateur ou Client
 * repository/                  : permet de mettre en place les requete Sql, grâce à hiberneate et son interface.
 * service/                     : les divers service utilisé par l'application elle-même
 *       client/                : spécifique au client
 *       impl/                  : spécifique à la énération de document
 *       mail/                  : spécifique à l'envoie des mails
 *ressources/                   : le dossiers qui contient tout les fichiers autre que du code java
 *       files/                 : le dossier final ou son enregistré les docuemtn pdf
 *       static/                : le dossier ou est mis le site web, toute les pages  html, images, javaScript
 *       templates/             : les templates utilisés pour le service mail ou sur la generation de document
 *
 * Vous pouvez gérer les dépendances de l'application grâce à Maven (vers 3) est les dépots près configuré de spring-boot, vous pouvez rajouter aussi n'import qu'elle package  se trouvant sur maven-central, ou bien personnel.
 *
 * L'annotaion EnableSwagger2, permet d'activer une documentation sur les différents controller définis dans le package controller. Vous pouvez y accéder via l'url : http://localhost:8080/swagger-ui.html
 *
 * L'annotaion EnableScheduling permet d'activer les cron définis dans service/mail/newLetterService
 *
 * Si vous avez besoin de générer des élément au lancement comme le compte administrateur(password/mot de passe) ou une chambre, restaurant. cf DataLoaderAtLaunchApp
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
