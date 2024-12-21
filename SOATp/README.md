# TP : SOA avec Spring

## Introduction

Le développement d’un système orienté services (SOA) repose sur l’interconnexion de différents services applicatifs pour offrir des fonctionnalités cohérentes et intégrées. Dans ce TP, l'objectif est de combiner un service SOAP et un service REST à l'aide de Spring Integration pour permettre une communication fluide entre les deux. Par la suite, nous allons sécuriser ces services en utilisant des standards modernes tels que WS-Security pour SOAP et OAuth2 pour REST, tout en mettant en place une stratégie de versioning pour assurer l'évolutivité des services.

## Partie 1 : Intégration d’un service SOAP et REST avec Spring Integration

### Étape 1 : Créer un service SOAP pour gérer les commandes clients
- Définir les fonctionnalités du service SOAP (gestion des commandes : création, récupération).
- Rédiger un fichier WSDL pour structurer et formaliser les opérations offertes par le service SOAP.
- Implémenter et déployer le service SOAP en utilisant Spring et une bibliothèque comme JAX-WS.

### Étape 2 : Créer un service REST pour gérer les informations sur les produits
- Entité Product { private String id; private String name; private double price; }
- Définir un service REST qui expose des endpoints pour la gestion des produits (ajout, modification, récupération).
- Implémenter le service REST avec Spring Boot en respectant les principes RESTful (URI clairs, méthodes HTTP appropriées, etc.).

### Étape 3 : Intégrer les deux services avec Spring Integration
- Configurer un système d’échange de données entre le service SOAP et le service REST.
- Utiliser Spring Integration pour définir des flux d’intégration permettant de transformer et acheminer les données entre les services.