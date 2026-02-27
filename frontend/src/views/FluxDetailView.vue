<template>
  <div class="detail">
    <h1>Détail du flux #{{ id }}</h1>

    <div v-if="flux" class="card">
      <div class="row">
        <span class="label">Source</span>
        <span class="value">{{ flux.source }}</span>
      </div>
      <div class="row">
        <span class="label">Destination</span>
        <span class="value">{{ flux.destination }}</span>
      </div>
      <div class="row">
        <span class="label">Statut</span>
        <FluxStatusBadge :status="flux.status" />
      </div>
      <div class="row">
        <span class="label">Payload</span>
        <pre class="payload">{{ flux.payload }}</pre>
      </div>
      <div class="row">
        <span class="label">Créé le</span>
        <span class="value">{{ formatDate(flux.createdAt) }}</span>
      </div>
    </div>

    <div v-else class="loading">Chargement...</div>

    <button @click="$router.push('/')">Retour</button>
  </div>
</template>

<script>
import FluxStatusBadge from '@/components/FluxStatusBadge.vue'
import FluxService from '@/services/FluxService'

export default {
  name: 'FluxDetailView',
  components: { FluxStatusBadge },
  props: ['id'],
  data() {
    return { flux: null }
  },
  methods: {
    formatDate(date) {
      if (!date) return '-'
      return new Date(date).toLocaleString('fr-FR')
    },
    async loadFlux() {
      try {
        const response = await FluxService.getById(this.id)
        this.flux = response.data
      } catch (error) {
        console.error('Erreur chargement flux :', error)
      }
    }
  },
  mounted() {
    this.loadFlux()
  }
}
</script>

<style scoped>
.detail {
  padding: 30px;
  font-family: sans-serif;
}
h1 {
  margin-bottom: 24px;
  font-size: 22px;
  color: #1a1a2e;
}
.card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
}
.row {
  display: flex;
  align-items: flex-start;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
}
.label {
  width: 120px;
  font-weight: 600;
  color: #666;
  font-size: 13px;
}
.value { color: #1a1a2e; }
.payload {
  background: #f8f9fa;
  padding: 10px;
  border-radius: 4px;
  font-size: 13px;
  flex: 1;
}
button {
  padding: 8px 20px;
  background: #1a1a2e;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
</style>
