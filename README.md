# Initier un projet Spring Boot 3.2.3 SYGALIN

## Configurer le fichier <span style='color:green;font-size:15px'>application.yml</span>
le fichier application.yml contient les clés et valeurs que le code utilise pour
s'auto lancer en fonction des paramètres. Pour chaque paramètre ci-dessous créer 
une variable d'environnement système approprié
NB : pour les éléments se trouvant dans ${ }, le projet les recupères des variables d'environnement
* <span style='color:green;font-size:15px'>name: </span> (à remplire)
* <span style='color:green;font-size:15px'>database:</span> [mySQL,postgreSQL,H2] ; Choissisez la base de données que votre projet utilise
* <span style='color:green;font-size:15px'>username: </span> ${USER_NAME_DB}
* <span style='color:green;font-size:15px'>password: </span> ${USER_PWD_DB}
* <span style='color:green;font-size:15px'>url:</span> ${DATABASE_URL}
* <span style='color:green;font-size:15px'>dbname:</span> (à remplire)
* <span style='color:green;font-size:15px'>JWT-KEY:</span> ${JWT_KEY_API}

## Les Helpers
Nous avons crées des services reutilisable qui vous permettrons d'executer un certains nombres de tâche; 
pour faire appel à eux injectaient les simplement dans votre méthodes; ces services sont les suivants :
* <span style='color:orange;font-size:19px'>ApplicationTimeZoneManager :</span>avec les méthodes tels que [public OffsetDateTime getTimeZoneOffset(String zoneId),public LocalDateTime moment(LocalDateTime dateTime, String zoneId)]; vous pouvez convertir une date et un time dans le TimeZone voulu
* <span style='color:orange;font-size:19px'>HttpClient : </span> avec son unique méthode [http(Class<T> tClass, String url)] vous pouvez envoyer des requetes vers n'importe quel EndPoint. un Exemple d'utilisation se trouve dans la classe comportant le main
* <span style='color:orange;font-size:19px'>TokenManager : </span> vous permet de générer un token, vérifier un token et récupérer les claims d'un token

## Configuration du projet 
1. ### Type de l'application

Une fois le projet créer en fonction du type voulu (lambda ou application) renommer le fichier <span style='color:green;font-size:15px'> pom.xml </span> correspondant.
Si le projet est de type application, ne modifiez pas le fichier pom.xml; si c'est un projet lambda, supprimez le fichier pom.xml et renommez pom-lambda.xml en pom.xml


