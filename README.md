# Synapse — Enterprise Middleware API

![License](https://img.shields.io/badge/license-MIT-blue)
![Platform](https://img.shields.io/badge/platform-Linux%20%7C%20Docker-lightgrey)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Vue.js](https://img.shields.io/badge/Vue.js-3.x-brightgreen)

API middleware d'intégration de données entre systèmes d'information (ERP/WMS).

## Projet

Simulation d'une infrastructure middleware réelle permettant la circulation,
la validation et la transformation de données entre logiciels internes.

Ce projet reproduit le besoin métier de Kermené (17ème entreprise agroalimentaire
française, filiale E.Leclerc) : remplacer les outils qui font circuler les
informations entre les logiciels de production et de logistique.

## Contexte

Projet développé dans le cadre d'une recherche d'alternance en développement
Full Stack (BAC+5 Concepteur-Développeur). L'objectif est de démontrer des
compétences techniques concrètes en :

- Conception et développement d'API REST (Spring Boot)
- Architecture middleware et intégration de systèmes
- Développement frontend de monitoring (Vue.js)
- Containerisation et déploiement (Docker)
- Conception de bases de données (MySQL)

## Architecture
```
[ERP Simule] ──> [API Spring Boot] ──> [WMS Simule]
                        │
                   [MySQL - Logs]
                        │
                [Vue.js - Monitoring]
```

Flux principal : reception, validation, transformation et routage
de donnees entre systemes heterogenes.

## Stack Technique

| Couche          | Technologie            |
|-----------------|------------------------|
| Back-end        | Java 21 + Spring Boot 3|
| Front-end       | Vue.js 3               |
| Base de donnees | MySQL 8                |
| Conteneurisation| Docker + Compose       |
| CI/CD           | Jenkins                |
| Cloud (cible)   | Google Cloud Platform  |
| Versioning      | Git / GitHub           |

## Structure du Projet
```
synapse/
├── backend/          # API Spring Boot (Java)
├── frontend/         # Dashboard Vue.js
├── docs/             # Specifications, MCD/MLD, architecture
├── scripts/          # Scripts utilitaires
├── docker-compose.yml
└── README.md
```

## Fonctionnalites

- [ ] API REST Spring Boot (reception flux ERP)
- [ ] Validation et transformation des donnees
- [ ] Persistance MySQL (historique des flux)
- [ ] Retry automatique en cas d'echec
- [ ] Dashboard Vue.js (statut des flux en temps reel)
- [ ] Dockerisation complete
- [ ] Pipeline Jenkins

## Prerequis

- Java 21+
- Maven 3.8+
- Node.js 18+
- Docker + Docker Compose
- MySQL 8

## Installation
```bash
git clone https://github.com/ThomasWEBDEV/synapse.git
cd synapse
docker-compose up -d
```

Documentation complete : docs/

## Auteur

Thomas

Etudiant BAC+5 Concepteur-Developpeur Full Stack
Recherche alternance developpement — Septembre 2026
GitHub : https://github.com/ThomasWEBDEV

## Licence

MIT License — Libre utilisation a des fins educatives et professionnelles.
