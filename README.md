# Synapse — Enterprise Middleware API

![License](https://img.shields.io/badge/license-MIT-blue)
![Platform](https://img.shields.io/badge/platform-Linux%20%7C%20Docker-lightgrey)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Vue.js](https://img.shields.io/badge/Vue.js-3.x-brightgreen)

API middleware d'intégration de données entre systèmes d'information (ERP/WMS).

## Projet

Simulation d'une infrastructure middleware réelle permettant la circulation,
la validation et la transformation de données entre systèmes d'information.

Ce projet reproduit un besoin métier concret dans le secteur industriel :
assurer la communication entre les logiciels de production et de logistique
via une API centralisée et maintenable.

## Contexte

Projet développé dans le cadre d'une recherche d'alternance en développement
Full Stack (BAC+5 Concepteur-Développeur). L'objectif est de démontrer des
compétences techniques concrètes en :

- Conception et développement d'API REST (Spring Boot)
- Architecture middleware et intégration de systèmes
- Développement frontend de monitoring (Vue.js)
- Containerisation et déploiement (Docker)
- Tests unitaires (JUnit 5 + Mockito)
- Pipeline CI/CD (Jenkins)
- Conception de bases de données (MySQL)

## Résultats

![Dashboard](screenshots/dashboard-overview.jpg)

### API testée et fonctionnelle
```bash
GET  /api/health  → {"status":"UP","service":"Synapse API","version":"1.0.0"}
GET  /api/stats   → {"success":1,"failed":1,"total":4,"pending":2,"retry":0}
GET  /api/flux    → Liste complète des flux
POST /api/flux    → Création d'un flux ERP → WMS
```

![Stats API](screenshots/api-stats.jpg)

### Pipeline CI/CD Jenkins

![Pipeline Jenkins](screenshots/jenkins-pipeline-success.jpg)

![Tests Jenkins](screenshots/jenkins-tests-success.jpg)

### ERP simulé - envoi automatique de commandes

L'ERP génère et envoie automatiquement des commandes vers Synapse toutes les 30 secondes :
```
Commande envoyée à Synapse : CMD-2C8FC293
Commande envoyée à Synapse : CMD-0E936C02
```

## Architecture
```
[ERP Simule] ──> [API Spring Boot] ──> [WMS Simule]
      |                  |
      |             [MySQL - Logs]
      |                  |
      └──────── [Vue.js - Monitoring]
```

## Stack Technique

| Couche           | Technologie             |
|------------------|-------------------------|
| Back-end         | Java 21 + Spring Boot 3 |
| Front-end        | Vue.js 3                |
| Base de données  | MySQL 8                 |
| Conteneurisation | Docker + Compose        |
| CI/CD            | Jenkins                 |
| Tests            | JUnit 5 + Mockito       |
| Cloud (cible)    | Google Cloud Platform   |
| Versioning       | Git / GitHub            |

## Structure du Projet
```
synapse/
├── backend/           # API Spring Boot - middleware central
├── frontend/          # Dashboard Vue.js - monitoring
├── erp/               # Simulateur ERP - envoi automatique de commandes
├── wms/               # Simulateur WMS (en cours)
├── docs/              # Spécifications, architecture
├── screenshots/       # Captures dashboard, API, Jenkins
├── Jenkinsfile        # Pipeline CI/CD
├── docker-compose.yml
└── README.md
```

## Fonctionnalités

- [x] API REST Spring Boot (réception flux ERP)
- [x] Validation et transformation des données
- [x] Persistance MySQL (historique des flux)
- [x] Dashboard Vue.js (statut des flux en temps réel)
- [x] Dockerisation complète
- [x] Gestion des statuts (PENDING, SUCCESS, FAILED, RETRY)
- [x] Endpoint statistiques
- [x] Tests unitaires (9 tests, 0 échec)
- [x] Pipeline Jenkins (build, test, package)
- [x] Simulateur ERP - envoi automatique toutes les 30s
- [ ] Simulateur WMS - réception et traitement
- [ ] Retry automatique
- [ ] Déploiement GCP

## Lancement
```bash
# Cloner le projet
git clone https://github.com/ThomasWEBDEV/synapse.git
cd synapse

# Lancer tous les services
docker-compose up -d

# Lancer le dashboard
cd frontend && npm run serve
```

| Service  | URL                        |
|----------|----------------------------|
| API      | http://localhost:8080/api  |
| Dashboard| http://localhost:8081      |
| Jenkins  | http://localhost:8090      |

## Auteur

Thomas

Etudiant BAC+5 Concepteur-Développeur Full Stack
Alternance développement - Septembre 2026 - Bretagne
GitHub : https://github.com/ThomasWEBDEV

## Licence

MIT License — Libre utilisation à des fins éducatives et professionnelles.
