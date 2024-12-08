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
  };
  
  export default localStorageService;
  