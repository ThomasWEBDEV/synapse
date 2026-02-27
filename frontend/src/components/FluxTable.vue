<template>
  <table class="flux-table">
    <thead>
      <tr>
        <th>ID</th>
        <th>Source</th>
        <th>Destination</th>
        <th>Statut</th>
        <th>Créé le</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="flux in fluxList" :key="flux.id">
        <td>{{ flux.id }}</td>
        <td>{{ flux.source }}</td>
        <td>{{ flux.destination }}</td>
        <td><FluxStatusBadge :status="flux.status" /></td>
        <td>{{ formatDate(flux.createdAt) }}</td>
      </tr>
      <tr v-if="fluxList.length === 0">
        <td colspan="5" class="empty">Aucun flux disponible</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import FluxStatusBadge from './FluxStatusBadge.vue'

export default {
  name: 'FluxTable',
  components: { FluxStatusBadge },
  props: {
    fluxList: {
      type: Array,
      required: true
    }
  },
  methods: {
    formatDate(date) {
      if (!date) return '-'
      return new Date(date).toLocaleString('fr-FR')
    }
  }
}
</script>

<style scoped>
.flux-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}
.flux-table th, .flux-table td {
  padding: 10px 14px;
  border-bottom: 1px solid #e0e0e0;
  text-align: left;
}
.flux-table th {
  background: #f8f9fa;
  font-weight: 600;
}
.empty {
  text-align: center;
  color: #aaa;
  padding: 20px;
}
</style>
