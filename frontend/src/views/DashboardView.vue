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
        <span class="stat-label">Success</span>
      </div>
      <div class="stat-card pending">
        <span class="stat-value">{{ countByStatus('PENDING') }}</span>
        <span class="stat-label">Pending</span>
      </div>
      <div class="stat-card failed">
        <span class="stat-value">{{ countByStatus('FAILED') }}</span>
        <span class="stat-label">Failed</span>
      </div>
    </div>

    <FluxForm @flux-created="loadFlux" />
    <FluxTable :fluxList="fluxList" />
  </div>
</template>

<script>
import FluxTable from '@/components/FluxTable.vue'
import FluxForm from '@/components/FluxForm.vue'
import FluxService from '@/services/FluxService'

export default {
  name: 'DashboardView',
  components: { FluxTable, FluxForm },
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
.dashboard {
  padding: 30px;
  font-family: sans-serif;
}
h1 {
  margin-bottom: 24px;
  font-size: 22px;
  color: #1a1a2e;
}
.stats {
  display: flex;
  gap: 16px;
  margin-bottom: 30px;
}
.stat-card {
  flex: 1;
  padding: 20px;
  border-radius: 8px;
  background: #f8f9fa;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #e0e0e0;
}
.stat-value {
  font-size: 28px;
  font-weight: 700;
  color: #1a1a2e;
}
.stat-label {
  font-size: 13px;
  color: #666;
  margin-top: 4px;
}
.stat-card.success { border-left: 4px solid #198754; }
.stat-card.pending { border-left: 4px solid #ffc107; }
.stat-card.failed  { border-left: 4px solid #dc3545; }
</style>
