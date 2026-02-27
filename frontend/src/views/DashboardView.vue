<template>
  <div class="dashboard">
    <h1>Synapse — Dashboard</h1>

    <div class="stats">
      <div class="stat-card">
        <span class="stat-value">{{ total }}</span>
        <span class="stat-label">Total flux</span>
      </div>
      <div class="stat-card success">
        <span class="stat-value">{{ countByStatus('SUCCESS') }}</span>
        <span class="stat-label">Succès</span>
      </div>
      <div class="stat-card failed">
        <span class="stat-value">{{ countByStatus('FAILED') }}</span>
        <span class="stat-label">Échecs</span>
      </div>
      <div class="stat-card pending">
        <span class="stat-value">{{ countByStatus('PENDING') }}</span>
        <span class="stat-label">En attente</span>
      </div>
    </div>

    <FluxTable :fluxList="fluxList" />
  </div>
</template>

<script>
import FluxTable from '../components/FluxTable.vue'
import FluxService from '../services/FluxService'

export default {
  name: 'DashboardView',
  components: { FluxTable },
  data() {
    return {
      fluxList: []
    }
  },
  computed: {
    total() {
      return this.fluxList.length
    }
  },
  methods: {
    countByStatus(status) {
      return this.fluxList.filter(f => f.status === status).length
    },
    async loadFlux() {
      try {
        const response = await FluxService.getAll()
        this.fluxList = response.data
      } catch (error) {
        console.error('Erreur chargement flux:', error)
      }
    }
  },
  mounted() {
    this.loadFlux()
  }
}
</script>

<style scoped>
.dashboard {
  padding: 30px;
  font-family: sans-serif;
}
h1 {
  margin-bottom: 24px;
  font-size: 24px;

eof
