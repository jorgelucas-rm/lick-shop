const localStorageService = { 
  // Salva o token no localStorage
  saveToken(token) {
    localStorage.setItem('authToken', token);
  },

  // Lê o token do localStorage
  getToken() {
    return localStorage.getItem('authToken');
  },

  // Remove o token do localStorage
  removeToken() {
    localStorage.removeItem('authToken');
  },

  // Verifica se o token existe no localStorage
  isAuthenticated() {
    return !!this.getToken(); // Retorna true se o token existir
  },

  // Salva o estado de admin no localStorage
  saveIsAdmin(isAdmin) {
    localStorage.setItem('isAdmin', isAdmin);
  },

  // Lê o estado de admin do localStorage
  getIsAdmin() {
    return localStorage.getItem('isAdmin') === 'true'; // Retorna true se for admin
  },

  // Remove o estado de admin do localStorage
  removeIsAdmin() {
    localStorage.removeItem('isAdmin');
  }
};

export default localStorageService;
