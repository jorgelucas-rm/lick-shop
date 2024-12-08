import axios from 'axios';

const api = axios.create({
  baseURL: 'https://api.lickshop.acilab.com.br',
  headers: {
    "Content-Type": "application/json",
    "Accept":"/"
  },
});

export default api;