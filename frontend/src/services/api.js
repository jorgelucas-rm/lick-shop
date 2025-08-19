import axios from 'axios';
import localStorageService from '@/services/LocalStorageService'; 


const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    const token = localStorageService.getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
)
export const getUser = (id) => api.get(`/api/v1/usuario/${id}`);
export const getUsers = () => api.get('/api/v1/usuario');
export const createUser = (data) => api.post('/api/v1/usuario', data);
export const updateUser = (id, data) => api.put(`/api/v1/usuario/${id}`, data);
export const deleteUser = (id) => api.delete(`/api/v1/usuario/${id}`);

export const getProduct = (id) => api.get(`/api/v1/produto/${id}`);
export const getProducts = () => api.get('/api/v1/produto');
export const createProduct = (data) => api.post('/api/v1/produto', data);
export const updateProduct = (id, data) => api.put(`/api/v1/produto/${id}`, data);
export const deleteProduct = (id) => api.delete(`/api/v1/produto/${id}`);

export const getBrand = (id) => api.get(`/api/v1/marca/${id}`);
export const getBrands = () => api.get('/api/v1/marca');
export const createBrand = (data) => api.post('/api/v1/marca', data);
export const updateBrand = (id, data) => api.put(`/api/v1/marca/${id}`, data);
export const deleteBrand = (id) => api.delete(`/api/v1/marca/${id}`);

export const getCategory = (id) => api.get(`/api/v1/categoria/${id}`);
export const getCategories = () => api.get('/api/v1/categoria');
export const createCategory = (data) => api.post('/api/v1/categoria', data);
export const updateCategory = (id, data) => api.put(`/api/v1/categoria/${id}`, data);
export const deleteCategory = (id) => api.delete(`/api/v1/categoria/${id}`);

export const login = (credentials) => api.post('/api/v1/login', credentials);

export default api;