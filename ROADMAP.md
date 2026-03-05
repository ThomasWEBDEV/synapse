# Synapse — Roadmap

## Phase 1 — Tests unitaires (terminée)
- JUnit 5 + Mockito
- FluxValidatorServiceTest — 4 tests (validation champs)
- DataFluxServiceTest — 4 tests (CRUD et statuts)
- Résultat : 9 tests, 0 échec, BUILD SUCCESS

## Phase 2 — Jenkins CI/CD (terminée)
- Jenkins dockerisé sur port 8090
- Pipeline : Checkout - Build - Tests - Package
- 9 tests automatisés a chaque push Git
- Résultat : BUILD SUCCESS

## Phase 3 — ERP + WMS simulés (terminée)
- ERP : envoi automatique de commandes vers Synapse toutes les 30s
- WMS : réception et traitement des flux
- Communication complète ERP -> Synapse -> WMS opérationnelle

## Phase 4 — Déploiement GCP (terminée)
- Image Docker poussée sur Google Artifact Registry
- API déployée sur Google Cloud Run
- URL publique : https://synapse-backend-852589566429.europe-west1.run.app
- Profil Spring Boot GCP avec base H2 en mémoire

---

**Stack cible complète**

| Technologie | Usage |
|-------------|-------|
| Spring Boot | Backend API |
| Vue.js 3 | Dashboard monitoring |
| MySQL 8 | Persistance |
| Docker | Conteneurisation |
| Jenkins | CI/CD pipeline |
| GCP Cloud Run | Déploiement cloud |
| GCP Artifact Registry | Stockage images Docker |
| JUnit 5 + Mockito | Tests unitaires |
