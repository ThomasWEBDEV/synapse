import api from './api'

export default {
  getAll() {
    return api.get('/flux')
  },

  getById(id) {
    return api.get(`/flux/${id}`)
  },

  getByStatus(status) {
    return api.get(`/flux/status/${status}`)
  },

  create(flux) {
    return api.post('/flux', flux)
  },

  updateStatus(id, status) {
    return api.patch(`/flux/${id}/status`, null, { params: { status } })
  },

  getHealth() {
    return api.get('/health')
  }
}
