# Synapse - Architecture du projet

## Vue d'ensemble
```
[ERP Simulé]  ──>  [Synapse API]  ──>  [WMS Simulé]
    :8081               :8080               :8082
                           |
                      [MySQL :3306]
                           |
                   [Dashboard Vue.js]
                           :8083
                           
                   [Jenkins CI/CD]
                       :8090
```

## Structure complète
```
synapse/
│
├── backend/                          # Middleware central - Spring Boot 3 / Java 21
│   ├── Dockerfile                    # Build multi-stage Maven + Eclipse Temurin
│   ├── pom.xml                       # Dépendances : Spring Web, JPA, MySQL, H2, JUnit
│   └── src/
│       ├── main/java/com/synapse/
│       │   ├── App.java              # Point d'entrée Spring Boot
│       │   ├── config/
│       │   │   └── CorsConfig.java   # Autorisation requêtes cross-origin frontend
│       │   ├── controller/
│       │   │   ├── DataFluxController.java     # GET/POST/PATCH /api/flux
│       │   │   ├── HealthController.java        # GET /api/health
│       │   │   ├── StatsController.java         # GET /api/stats
│       │   │   └── GlobalExceptionHandler.java  # Gestion centralisée des erreurs
│       │   ├── dto/
│       │   │   ├── DataFluxDTO.java    # Objet de transfert - données entrantes
│       │   │   └── DataFluxMapper.java # Conversion DTO <-> entité JPA
│       │   ├── model/
│       │   │   ├── DataFlux.java      # Entité JPA - table MySQL data_flux
│       │   │   └── FluxStatus.java    # Constantes PENDING/SUCCESS/FAILED/RETRY
│       │   ├── repository/
│       │   │   └── DataFluxRepository.java  # Requêtes JPA (findByStatus, findBySource)
│       │   └── service/
│       │       ├── DataFluxService.java       # CRUD + transmission automatique vers WMS
│       │       └── FluxValidatorService.java  # Validation des champs obligatoires
│       ├── main/resources/
│       │   ├── application.properties         # Config MySQL, port 8080
│       │   └── application-gcp.properties     # Config H2 pour Cloud Run
│       └── test/java/com/synapse/
│           ├── AppTest.java                        # Test de démarrage
│           └── service/
│               ├── DataFluxServiceTest.java        # 4 tests CRUD avec Mockito
│               └── FluxValidatorServiceTest.java   # 4 tests validation
│
├── erp/                              # Simulateur ERP - Spring Boot 3 / Java 21
│   ├── Dockerfile                    # Build multi-stage Maven
│   ├── pom.xml
│   └── src/main/java/com/synapse/erp/
│       ├── ErpApplication.java       # Point d'entrée + @EnableScheduling
│       ├── model/
│       │   └── ErpOrder.java         # Modèle commande (orderId, product, quantity)
│       ├── service/
│       │   └── ErpService.java       # Envoi automatique vers Synapse toutes les 30s
│       └── controller/
│           └── ErpController.java    # POST /api/erp/send - déclenchement manuel
│
├── wms/                              # Simulateur WMS - Spring Boot 3 / Java 21
│   ├── Dockerfile                    # Build multi-stage Maven
│   ├── pom.xml
│   └── src/main/java/com/synapse/wms/
│       ├── WmsApplication.java       # Point d'entrée Spring Boot
│       └── controller/
│           └── WmsController.java    # POST /api/wms/receive + GET /api/wms/health
│
├── frontend/                         # Dashboard monitoring - Vue.js 3
│   └── src/
│       ├── App.vue                   # Composant racine + navigation
│       ├── main.js                   # Initialisation Vue + Router
│       ├── router/
│       │   └── index.js              # Routes / et /flux/:id
│       ├── services/
│       │   ├── api.js                # Configuration Axios
│       │   └── FluxService.js        # Appels REST vers le backend
│       ├── components/
│       │   ├── FluxForm.vue          # Formulaire de création de flux
│       │   ├── FluxTable.vue         # Tableau des flux avec statuts et dates
│       │   └── FluxStatusBadge.vue   # Badge coloré par statut
│       └── views/
│           ├── DashboardView.vue     # Stats + formulaire + tableau en temps réel
│           └── FluxDetailView.vue    # Détail d'un flux par ID
│
├── docs/                             # Documentation technique
│   ├── api.md                        # Documentation des endpoints REST
│   └── sql/init.sql                  # Script initialisation base de données
│
├── screenshots/                      # Preuves visuelles du projet
│   ├── dashboard-overview.jpg        # Dashboard initial
│   ├── dashboard-erp-wms-live.jpg    # Flux en temps réel ERP -> WMS
│   ├── dashboard-final.jpg           # Dashboard final - 198 flux
│   ├── api-stats.jpg                 # Réponse JSON /api/stats
│   ├── api-health.jpg                # Réponse JSON /api/health
│   ├── jenkins-dashboard.jpg         # Interface Jenkins
│   ├── jenkins-pipeline-success.jpg  # Pipeline CI/CD en vert
│   ├── jenkins-tests-success.jpg     # 9 tests validés
│   ├── wms-flux-received.jpg         # Logs WMS - réception des flux
│   ├── gcp-artifact-registry.jpg     # Image Docker sur GCP
│   └── gcp-cloud-run-live.jpg        # API en production sur Cloud Run
│
├── docker-compose.yml                # Orchestration des 5 services Docker
├── Jenkinsfile                       # Pipeline CI/CD - build, test, package
├── README.md                         # Documentation principale
├── ROADMAP.md                        # Suivi des phases du projet
└── ARCHITECTURE.md                   # Ce fichier - structure détaillée
```

## Flux de données
```
1. ErpService génère une commande toutes les 30 secondes
2. POST http://backend:8080/api/flux
3. FluxValidatorService valide les champs obligatoires
4. DataFlux sauvegardé en MySQL avec statut PENDING
5. DataFluxService transmet le flux au WMS
6. POST http://wms:8082/api/wms/receive
7. WMS accuse réception - statut passe à SUCCESS
8. Dashboard Vue.js affiche les flux en temps réel
```

## Services Docker

| Container        | Image                        | Port  | Rôle                    |
|------------------|------------------------------|-------|-------------------------|
| synapse-mysql    | mysql:8.0                    | 3306  | Base de données         |
| synapse-backend  | synapse-backend:latest       | 8080  | API middleware centrale |
| synapse-erp      | synapse-erp:latest           | 8081  | Simulateur ERP          |
| synapse-wms      | synapse-wms:latest           | 8082  | Simulateur WMS          |
| synapse-jenkins  | jenkins/jenkins:lts          | 8090  | Pipeline CI/CD          |

## Tests unitaires

| Classe testée           | Tests | Couverture                          |
|-------------------------|-------|-------------------------------------|
| FluxValidatorService    | 4     | Validation source, destination, payload |
| DataFluxService         | 4     | CRUD, statuts, exception introuvable |
| AppTest                 | 1     | Démarrage application               |
| Total                   | 9     | 0 échec - BUILD SUCCESS             |

## Déploiement GCP

L'image Docker du backend a été poussée sur Google Artifact Registry et déployée
sur Google Cloud Run dans la région europe-west1 avec un profil Spring Boot dédié
utilisant une base H2 en mémoire.
```bash
# Build et push de l'image
docker build -t europe-west1-docker.pkg.dev/synapse-middleware-app/synapse-repo/synapse-backend:latest .
docker push europe-west1-docker.pkg.dev/synapse-middleware-app/synapse-repo/synapse-backend:latest

# Déploiement Cloud Run
gcloud run deploy synapse-backend \
  --image europe-west1-docker.pkg.dev/synapse-middleware-app/synapse-repo/synapse-backend:latest \
  --platform managed \
  --region europe-west1 \
  --allow-unauthenticated \
  --set-env-vars SPRING_PROFILES_ACTIVE=gcp
```
