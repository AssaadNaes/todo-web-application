import apiClient from './apiClient.js'

const url = '/api/todo'
export default {
  async getCount() {
    try {
      const response = await apiClient.get(url + '/count')
      return {
        data: response.data,
        status: response.status
      }
    } catch (err) {
      console.error('Error counting todos:' + err)
    }
  },

  async get() {
    try {
      const response = await apiClient.get(url)
      console.log('Fetched data from API!')
      return {
        data: response.data,
        status: response.status
      }
    } catch (err) {
      console.error('Error getting todos:' + err)
    }
  },

  async create(todoContent) {
    try {
      const response = await apiClient.post(url, { content: todoContent })
      console.log('Todo ' + todoContent + ' created!')
      return response.status
    } catch (err) {
      console.error('Error creating todo: ' + todoContent + ' ' + err)
    }
  },

  async update(id, todo) {
    try {
      const response = await apiClient.put(url + '/' + id, todo)
      console.log('Todo ' + todo.content + ' updated!')
      return response.status
    } catch (err) {
      console.error('Error updating todo:' + err)
    }
  },

  async delete(id) {
    try {
      const response = await apiClient.delete(url + '/' + id)
      console.log('Todo deleted with id: ' + id)
      return response.status
    } catch (err) {
      console.error('Error deleting todo with id: ' + id + ' ' + err)
    }
  }
}
