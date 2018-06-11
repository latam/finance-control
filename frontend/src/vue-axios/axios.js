import axios from 'axios'

export default axios.create({
  baseUrl: '/api',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  }
})
