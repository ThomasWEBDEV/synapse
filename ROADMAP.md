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

## Phase 4 — Déploiement GCP (en cours)
- Google Cloud Run (backend)
- Cloud SQL (MySQL managé)
- URL publique accessible

---

**Stack cible complète**

| Technologie | Usage |
|-------------|-------|
| Spring Boot | Backend API |
| Vue.js 3 | Dashboard monitoring |
| MySQL 8 | Persistance |
| Docker | Conteneurisation |
| Jenkins | CI/CD pipeline |
| GCP | Déploiement cloud |
| JUnit 5 + Mockito | Tests unitaires |
