# Demo-Kafka

Ce repository à été créé pour la présentation d'Apache Kafka lors de la TechNight d'Excilys du 26 septembre 2019.

Ce dernier contient deux API REST nommées respectivement CLIENT et COMPTE. 

Dans cette démo nous simulons très basiquement le fonctionnement d'une banque avec des clients reliés à des comptes. 
Chaque client va réaliser différentes opérations banquaires qui seront assurées par l'API COMPTE. 

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

* Vous devez au préalable avoir votre JDK de Java présent dans une variable d'environnement système nommée "JAVA_HOME". [C'est important vous pourriez avoir des problèmes par la suite].
* Desarchiver le fichier dans un dossier (ex : D:\ )
* Créer une nouvelle variable d'environnement système ZOOKEEPER_HOME pointant sur votre nouveau dossier


```
ZOOKEEPER_HOME          D:\apache-zookeeper-3.5.5
```
* Ajouter la nouvelle variable ZOOKEEPER_HOME à votre variable d'environnement Path
* Créer un dossier usr/zookeeper/data en dehors du dossier actuel. Exemple : 
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

Et voilà le ZooKeeper est installé et lancé prêt pour utilisation. Un dossier version va automatiquement être créé dans le dossier "D:\usr\zookeeper\data"

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

# En cas de problèmes

### Problèmes de Timeout

Kafka conserve un historique de l'ensemble des messages envoyés sur tous les topics. Tout cet historique est chargé au démarrage de Kafka. Selon la taille de votre architecture ou son ancienneté il est possible que ce chargement prenne trop de temps et fasse un "Time out". 
Par défaut la valeur de Time Out est set à 6000 ms dans les properties de votre Kafka. Il est possible de changer cette valeur et de lui mettre 60000 ms par exemple et donc de régler ce problème.

### Problèmes de Logs

Souvent ce problème vient avec le précédent. De manières générale lorsque vous apportez des changements concernant les propriétés de votre Kafka il est important de clean les logs car ces derniers sont utilsés au démarrage de Kafka.
Pour les supprimer c'est simple ! Si l'on prend notre exemple, nos logs sont stockés dans le dossier : D:\usr\zookeeper et D:\usr\kafka
Il faut donc arrêter votre Zookeeper et votre Kafka et ensuite supprimer ces deux dossiers. Pas de panique, il seront à nouveau générés par votre Zookeeper et votre Kafka lors du prochain démarrage.

### Autres problèmes ou questions

Je vous ai mis juste au dessus les deux problèmes majeurs que j'ai rencontré lors de la mise en place de cette architecture. J'imagine que vous en trouverez d'autres auxquels je n'ai pas répondu. 
Dans ce cas n'hésitez pas à m'envoyer un mail : mnouville@excilys.com

J'essayerais de vous répondre le plus vite dans la mesure du possible et de vous aider. 
