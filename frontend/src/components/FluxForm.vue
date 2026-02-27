<template>
  <div class="flux-form">
    <h2>Créer un flux</h2>
    <form @submit.prevent="submitForm">
      <div class="field">
        <label>Source</label>
        <input v-model="form.source" type="text" placeholder="ex: ERP" required />
      </div>
      <div class="field">
        <label>Destination</label>
        <input v-model="form.destination" type="text" placeholder="ex: WMS" required />
      </div>
      <div class="field">
        <label>Payload (JSON)</label>
        <textarea v-model="form.payload" placeholder='{"order_id": "CMD-001"}' required></textarea>
      </div>
      <div v-if="error" class="error">{{ error }}</div>
      <div v-if="success" class="success">Flux créé avec succès !</div>
      <button type="submit">Envoyer</button>
    </form>
  </div>
</template>

<script>
import FluxService from '@/services/FluxService'

export default {
  name: 'FluxForm',
  emits: ['flux-created'],
  data() {
    return {
      form: {
        source: '',
        destination: '',
        payload: ''
      },
      error: null,
      success: false
    }
  },
  methods: {
    async submitForm() {
      this.error = null
      this.success = false
      try {
        await FluxService.create(this.form)
        this.success = true
        this.form = { source: '', destination: '', payload: '' }
        this.$emit('flux-created')
      } catch (err) {
        this.error = err.response?.data?.error || 'Erreur lors de la création'
      }
    }
  }
}
</script>

<style scoped>
.flux-form {
  background: white;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 24px;
  margin-bottom: 30px;
}
h2 {
  font-size: 16px;
  margin-bottom: 16px;
  color: #1a1a2e;
}
.field {
  margin-bottom: 14px;
}
label {
  display: block;
  font-size: 13px;
  font-weight: 600;
  color: #666;
  margin-bottom: 4px;
}
input, textarea {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  font-size: 14px;
}
textarea {
  height: 80px;
  resize: vertical;
}
button {
  padding: 8px 20px;
  background: #1a1a2e;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}
.error   { color: #dc3545; font-size: 13px; margin-bottom: 10px; }
.success { color: #198754; font-size: 13px; margin-bottom: 10px; }
</style>
