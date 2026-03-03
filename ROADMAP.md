# Synapse — Roadmap

## Phase 1 — Tests unitaires (terminée)
- JUnit 5 + Mockito
- FluxValidatorServiceTest — 4 tests (validation champs)
- DataFluxServiceTest — 4 tests (CRUD et statuts)
- Résultat : 9 tests, 0 échec, BUILD SUCCESS

## Phase 2 — Jenkins CI/CD (en cours)
- Jenkins dockerisé
- Pipeline : build → test → package
- Déclenchement automatique sur push Git

## Phase 3 — ERP + WMS simulés
- API ERP : envoi automatique de flux vers Synapse
- API WMS : réception et accusé de réception
- Communication complète ERP → Synapse → WMS

## Phase 4 — Déploiement GCP
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
