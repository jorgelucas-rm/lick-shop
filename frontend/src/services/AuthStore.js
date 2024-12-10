import { reactive } from 'vue';
import localStorageService from '@/services/localStorage';

// Estado reativo global para autenticação e administrador
export const authStore = reactive({
  isLoggedIn: localStorageService.isAuthenticated(),
  isAdmin: localStorageService.getIsAdmin(), // Armazena o status de admin

  // Atualiza o estado de login
  updateLoginStatus() {
    this.isLoggedIn = localStorageService.isAuthenticated();
    this.isAdmin = localStorageService.getIsAdmin(); // Atualiza o estado de admin
  },

  // Atualiza o estado de admin (caso o cargo do usuário seja ADMIN)
  updateAdminStatus(isAdmin) {
    this.isAdmin = isAdmin;
    localStorageService.saveIsAdmin(isAdmin); // Salva o estado de admin no localStorage
  },

  // Reseta os status de login e admin (usado no logout)
  resetAuthStatus() {
    this.isLoggedIn = false;
    this.isAdmin = false;
    localStorageService.removeToken(); // Remove o token
    localStorageService.removeIsAdmin(); // Remove o estado de admin
  },
});
