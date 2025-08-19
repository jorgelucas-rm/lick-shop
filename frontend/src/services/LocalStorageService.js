const localStorageService = {
  setStorageType(persistent) {
    this.storage = persistent ? localStorage : sessionStorage;
  },

  saveToken(token, persistent = true) {
    this.setStorageType(persistent);
    this.storage.setItem('auth_token', token);
  },

  saveAuthData(id, nome, isAdmin, persistent = true) {
    this.setStorageType(persistent);
    this.storage.setItem('auth_id', id);
    this.storage.setItem('auth_nome', nome);
    this.storage.setItem('auth_isAdmin', JSON.stringify(isAdmin));
  },

  getToken() {
    return localStorage.getItem('auth_token') || sessionStorage.getItem('auth_token');
  },

  getAuthId() {
    return localStorage.getItem('auth_id') || sessionStorage.getItem('auth_id');
  },

  getAuthNome() {
    return localStorage.getItem('auth_nome') || sessionStorage.getItem('auth_nome');
  },

  getAuthIsAdmin() {
    return JSON.parse(localStorage.getItem('auth_isAdmin') || sessionStorage.getItem('auth_isAdmin') || 'false');
  },

  isAuthenticated() {
    const token = this.getToken();
    return !!token;
  },

  clearAll() {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('auth_id');
    localStorage.removeItem('auth_nome');
    localStorage.removeItem('auth_isAdmin');
    sessionStorage.removeItem('auth_token');
    sessionStorage.removeItem('auth_id');
    sessionStorage.removeItem('auth_nome');
    sessionStorage.removeItem('auth_isAdmin');
  }
};

export default localStorageService;
