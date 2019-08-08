# Demo-Kafka

Ce repository à été crée pour la présentation d'Apache Kafka lors de la TechNight d'Excilys du 26 septembre 2019.

Ce dernier contient deux API REST nommées respectivement CLIENT et COMPTE. 

Dans cette démo nous simulons très basiquement le fonctionnement d'une banque avec des clients reliés à des comptes. Chaque client va réaliser différentes opérations banquaires qui seront assurées par l'API COMPTE. 

Apache Kafka étant vulgairement un bus de données, nous allons l'utiliser afin de faire communiquer ces deux API de manière asynchrone. 

# Apache ZooKeeper

<img src="https://upload.wikimedia.org/wikipedia/en/thumb/8/81/Apache_ZooKeeper_Logo.svg/1200px-Apache_ZooKeeper_Logo.svg.png" />


### Téléchargement

Vous devez télécharger le fichier apache-zookeeper-3.5.5-bin.tar.gz

```
https://www.apache.org/dyn/closer.cgi/zookeeper/
```

### Installation

* Vous devez au préalable avoir votre JDK de Java présent dans une variable d'environnement système nommée "JAVA_HOME". C'est important vous pourriez avoir des problèmes par la suite.
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

### Téléchargement

Vous devez télécharger le fichier kafka_2.12-2.3.0.tgz

```
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.3.0/kafka_2.12-2.3.0.tgz 
```

### Installation

* Desarchiver le fichier dans un dossier (ex : D:\ )

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
