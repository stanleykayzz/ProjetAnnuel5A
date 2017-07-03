# CRM Hôtellerie
# La Résidence des hauts de Menaye

One Paragraph of project description goes here

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
JAVA 8
Maven 3
```

### Installing

A step by step series of examples that tell you have to get a development env running

Go in your folder dev and run this commands:

```
mvn -U clean install -Pdev
java -jar target/Projet_Annuel_2017-1.0-SNAPSHOT.jar -Dspring.profile="dev"
```

You can connect at the databse at this url

```
localhost:8080/h2
      OR
localhosot:8080/h2-console/
```

You can find the identifiant login, in `resources/application.yml`, at 

```
datasource:
    username: username
    password: password
```



## Running the tests

`mvn -U clean install -Ptest`



## Deployment

You can deploy everywhere if you have java 8 and Maven 3 installed.
You can just deploy to heroku, before you must install [heroku-cli](https://devcenter.heroku.com/articles/heroku-cli)
Sign up and follow this tutorial for [started and configured heroku](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)
And after you can deploy this application on the service with 
```
cd ProjetAnnuel5A
git init 
git push heroku master:master
```




## Built With

* [Spring boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Stanleykayzz** - *initial work front and full Android app* - [stanleykayzz](https://github.com/stanleykayzz)
* **maximem75** - *full work logic front* - [maximem75](https://github.com/maximem75)
* **ileossa** - *Full work back and deployement * - [ileossa](https://github.com/ileossa)


## License

This project is licensed under the Apache2 License - see the [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0) file for details