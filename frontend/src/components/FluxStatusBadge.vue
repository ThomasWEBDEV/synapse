<template>
  <span :class="badgeClass">{{ status }}</span>
</template>

<script>
export default {
  name: 'FluxStatusBadge',
  props: {
    status: {
      type: String,
      required: true
    }
  },
  computed: {
    badgeClass() {
      return {
        badge: true,
        'badge-pending': this.status === 'PENDING',
        'badge-success': this.status === 'SUCCESS',
        'badge-failed': this.status === 'FAILED',
        'badge-retry': this.status === 'RETRY'
      }
    }
  }
}
</script>

<style scoped>
.badge {
  padding: 4px 10px;
  border-radius: 12px;
  font-size: 12px;
  font-weight: 600;
}
.badge-pending  { background: #fff3cd; color: #856404; }
.badge-success  { background: #d1e7dd; color: #0a3622; }
.badge-failed   { background: #f8d7da; color: #842029; }
.badge-retry    { background: #cfe2ff; color: #084298; }
</style>
