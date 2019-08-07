# Demo-Kafka

Ce repository à été crée pour la présentation d'Apache Kafka lors de la TechNight d'Excilys du 26 septembre 2019.

Ce dernier contient deux API REST nommées respectivement CLIENT et COMPTE. 

Dans cette démo nous simulons très basiquement le fonctionnement d'une banque avec des clients reliés à des comptes. Chaque client va réaliser différentes opérations banquaires qui seront assurées par l'API COMPTE. 

Apache Kafka étant vulgairement un bus de données, nous allons l'utiliser afin de faire communiquer ces deux API de manière asynchrone. 
