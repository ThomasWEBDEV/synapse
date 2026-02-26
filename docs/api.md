# Synapse API — Documentation

**Base URL** : `http://localhost:8080/api`

---

## Endpoints

### Health

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /health | Statut de l'API |

**Réponse** :
```json
{
  "status": "UP",
  "service": "Synapse API",
  "version": "1.0.0"
}
```

---

### Flux

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | /flux | Liste tous les flux |
| GET | /flux/{id} | Récupère un flux par ID |
| GET | /flux/status/{status} | Filtre par statut |
| POST | /flux | Crée un nouveau flux |
| PATCH | /flux/{id}/status | Met à jour le statut |

**Statuts disponibles** : `PENDING`, `SUCCESS`, `FAILED`, `RETRY`

**Exemple POST /flux** :
```json
{
  "source": "ERP",
  "destination": "WMS",
  "payload": "{\"order_id\": \"CMD-001\", \"quantity\": 150}"
}
```
