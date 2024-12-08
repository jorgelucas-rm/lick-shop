import axios from 'axios';

const api = axios.create({
  baseURL: 'https://207.244.237.78:9921',
  headers: {
    "Content-Type": "application/json",
    "Accept":"/"
  },
});

export default api;