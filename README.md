# Demo-Kafka

Ce repository à été créé pour la présentation d'Apache Kafka lors de la TechNight d'Excilys du 28 novembre 2019.

Ce dernier contient deux API REST nommées respectivement CLIENT et COMPTE. 

Dans cette démo nous simulons très basiquement le fonctionnement d'une banque avec des clients reliés à des comptes. 
Chaque client va réaliser différentes opérations banquaires qui seront assurées par l'API COMPTE. 

L'objectif de ce TP est de faire une initiation à la communication via Kafka. Ici nous verrons pas de Kafka Connect. Je vous laisse creuser de votre côté si la technologie vous intéresse. 

Apache Kafka étant vulgairement un bus de données, nous allons l'utiliser afin de faire communiquer ces deux API de manière asynchrone. 

En cas de problèmes lors de l'installation j'ai mis a disposition des solutions à des problèmes récurrent. Avant de perdre du temps à chercher sur internet n'hésitez pas à les regarder.

Toutes les instructions qui vont suivres sont nescessaires et indispensables pour faire fonctionner Kafka : 
# Apache ZooKeeper

<img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/81/Apache_ZooKeeper_Logo.svg/1200px-Apache_ZooKeeper_Logo.svg.png" />

### Présentation

ZooKeeper est un projet Apache open source qui fournit un service centralisé fournissant des informations de configuration, des namings, une synchronisation et des services de groupe sur de grands clusters de systèmes répartis. 
L'objectif est de rendre ces systèmes plus faciles à gérer avec une propagation améliorée et plus fiable des modifications.
Avoir un ZooKeeper est nécessaire pour faire tourner un Apache Kafka.

### Téléchargement

Vous devez télécharger le fichier apache-zookeeper-3.5.5-bin.tar.gz

```
https://www.apache.org/dyn/closer.cgi/zookeeper/
```

### Installation

* Vous devez au préalable avoir votre JDK de Java présent dans une variable d'environnement système nommée "JAVA_HOME". [C'est important vous pourriez avoir des problèmes par la suite]().
* Desarchiver le fichier dans un dossier (Pour l'exemple nous utiliserons le dossier D:\ pour l'installation )
* Créer une nouvelle variable d'environnement système ZOOKEEPER_HOME pointant sur votre nouveau dossier


```
ZOOKEEPER_HOME          D:\apache-zookeeper-3.5.5
```
* Ajouter la nouvelle variable ZOOKEEPER_HOME à votre variable d'environnement Path
* Créer un dossier usr/zookeeper/data en dehors du dossier actuel. Ce dossier sera utilisé pour stocker les logs de Zookeeper et de Kafka. Exemple : 
```
D:\usr\zookeeper\data
```
* Dans ce dossier D:\usr\zookeeper\data -> créer un fichier sans extension nommé [myid]() et y écrire : 
```
1
```
* Aller dans le dossier D:\apache-zookeeper-3.5.5\conf et renommer le fichier [zoo_sample.cfg]() par [zoo.cfg]()
* Remplacer le contenu du fichier [zoo.cfg]() par : 
 ```
 tickTime=2000
 initLimit=5
 syncLimit=5
 dataDir=/usr/zookeeper/data (mettre le chemin correspondant au dossier créé précédemment)
 clientPort=2181
 server.1=localhost:2888:3888
 ```

### Utilisation

Retourner dans l'autre dossier D:\apache-zookeeper-3.5.5 puis aller dans le dossier "\bin" et executer le serveur Zookeeper

Sous Windows :
 ```
 D:\apache-zookeeper-3.5.5\bin> .\zkServer.cmd
 ```
Sous Linux :
 ```
 D:\apache-zookeeper-3.5.5\bin> ./zkServer.sh
 ```

Et voilà le ZooKeeper est installé et lancé prêt pour utilisation. Un dossier "D:\usr\zookeeper" va être crée avec des logs et un dossier de version.

# Apache Kafka

<img src="https://blog.zenika.com/wp-content/uploads/2017/09/kafka-logo-title.png" />

### Présentation



### Téléchargement

Vous devez télécharger le fichier kafka_2.12-2.3.0.tgz

```
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.3.0/kafka_2.12-2.3.0.tgz 
```

### Installation

* Desarchiver le fichier dans un dossier (ex : D:\ )
* Aller dans le dossier "Config", puis dans le fichier [server.properties]() et ajoutez la ligne suivantes : 
```
delete.topic.enable=true
```

### Utilisation

* Aller dans le dossier D:\kafka_2.12-2.3.0\bin et executez le serveur Kafka

Sous windows, aller dans le dossier windows puis executer la commande : 

```
 D:\kafka_2.12-2.3.0\bin\windows> .\kafka-server-start.bat D:\kafka_2.12-2.3.0\config\server.properties
```

Sous Linux :

```
 D:\kafka_2.12-2.3.0\bin> ./kafka-server-start.sh D:\kafka_2.12-2.3.0\config\server.properties
```

* Il faut créer un nouveau TOPIC via la commande suivante :

Sous windows : 

```
D:\kafka_2.12-2.3.0\bin\windows> .\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic operationsCompte
```

Sous Linux : 

```
D:\kafka_2.12-2.3.0\bin\windows> .\kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 100 --topic operationsCompte
```

### Travail à faire

Maintenant que votre environnement est installé, vous pouvez commencer à coder. Dans ce git se trouve un exemple d'implémentation (Producer, Consummer, Config etc.)
Si vous êtes bloqués n'hésitez pas à regarder le code. 

Les étapes : 

* Utiliser le script bdd.sql pour mettre en place la structure de la base de données
* Créer deux API REST BASIQUES Compte et Client permettant de créer, récupérer, supprimer des comptes/clients etc.
* Stocker les propriétés du serveur dans votre [application.yml]() et les utiliser dans une classe de Configuration Kafka nommée [KafkaConfig.java](). Cette classe de configuration doit fournir des configurations de producers, un Producer Factory et un Kafka Template. Si cette étape vous parait trop compliqué allez regarder mon fichier [application.yml]() et une des deux [KafkaConfig.java]() de mon repo. 
* Créer un producer dans un package "Kafka/Producer" de votre API Client. Ce producer devra réaliser des envois de messages sur le topic "operationsComptes" que nous avons créé précédemment. 
* Créer un consumer dans un package "Kafka/Consumer" de votre API Compte. Ce consumer devra écouter sur le topic où le producer de votre API Client envoi des messages et afficher le message dans la console.
* Lorsque vous arrivez a faire communiquer vos API, Mettez en place un modèle de données pour la communication de vos API (ex: [MessageKafka.java]()).
* Via un ObjectMapper envoyez en récupérez ce type d'objet dans vos producer et consumer afin de faire communiquer vos API sur un même model.
* Faites vous plaisirs et réalisez des opérations banquaires diverses (ex: des opérations de débit ou de crédit envoyés depuis l'API Client) entre les deux API. Vous pouvez le faire via un Swagger, en ligne de commande, ou via un FRONT pour les plus motivés !


# En cas de problèmes

### Problèmes lors de l'installation de Zookeeper

Ces problèmes sont beaucoup plus réccurents sur Linux (surtout si vous n'êtes pas spécialement à l'aise sur cet OS). En général les problèmes d'installation viennent de deux choses : 
* Soit vous avez fait des bétises dans vos variables d'environnement. N'oubliez pas qu'il est IMPERATIF d'avoir une variable d'environnement JAVA_HOME et ZOOKEEPER_HOME. Ces dernières doivent bien évidemment apparaitre dans votre variable PATH. 
* Soit c'est lié à votre version de JAVA. Pour une raison que j'ignore, sur Linux, Zookeeper à l'aire de vouloir une version de l'openjdk dans le JAVA_HOME. Par exemple l'openjdk 8. Ce n'est pas un problème que j'ai rencontré sur Windows mais il faut tout de même rester vigilant.

Pour tout autre problèmes, vous avez accès aux messages d'erreurs dans un fichier texte du dossier "Logs". 

### Problèmes de Timeout

Kafka conserve un historique de l'ensemble des messages envoyés sur tous les topics. Tout cet historique est chargé au démarrage de Kafka. Selon la taille de votre architecture ou son ancienneté il est possible que ce chargement prenne trop de temps et fasse un "Time out". 
Par défaut la valeur de Time Out est set à 6000 ms dans les properties de votre Kafka. Il est possible de changer cette valeur et de lui mettre 60000 ms par exemple et donc de régler ce problème.

### Problèmes de Logs

Souvent ce problème vient avec le précédent. De manières générale lorsque vous apportez des changements concernant les propriétés de votre Kafka il est important de clean les logs car ces derniers sont utilsés au démarrage de Kafka.
Pour les supprimer c'est simple ! Si l'on prend notre exemple, nos logs sont stockés dans le dossier : D:\usr\zookeeper et D:\usr\kafka
Il faut donc arrêter votre Zookeeper et votre Kafka et ensuite supprimer ces deux dossiers. Pas de panique, il seront à nouveau générés par votre Zookeeper et votre Kafka lors du prochain démarrage.
Attention, les Logs au sens Kafka n'a rien à voir avec les Log que l'on connait en informatique. Allez voir dans ma présentation pour avoir des informations sur les Logs Kafka.
Par conséquent supprimer les Logs est potentiellement une bonne solutions pour vous aider a debugger votre environnement mais ce n'est pas du tout une bonne pratique en cas de situation réelle. 

### Supprimer un TOPIC

Plus haut dans le tutoriel je vous ai précisé d'ajouter la ligne : 

```
delete.topic.enable=true
```

C'est important de le faire avant l'installation car si vous ajoutez cette ligne en cours de route il est très compliqué pour Kafka d'accepter la suppression de TOPIC. 
Je parle en connaissance de cause. Mais vu que vous êtes des gens biens et que vous avez suivi le tutoriel avec attention ça ne devrait pas arriver :grin: 

### Autres problèmes ou questions

Je vous ai mis juste au dessus les deux problèmes majeurs que j'ai rencontré lors de la mise en place de cette architecture. J'imagine que vous en trouverez d'autres auxquels je n'ai pas répondu. 
Dans ce cas n'hésitez pas à m'envoyer un mail : mnouville@excilys.com

J'essayerais de vous répondre le plus vite dans la mesure du possible et de vous aider.

De même si vous pensez que j'ai fais une erreur, que j'ai oublié de préciser quelque chose n'hésitez pas à me le signaler pour que je corrige !

Merci d'avance !  
