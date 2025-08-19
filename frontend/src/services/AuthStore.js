import { reactive } from 'vue';
import localStorageService from '@/services/LocalStorageService';

export const authStore = reactive({
  isLoggedIn: localStorageService.isAuthenticated(),
  userId: localStorageService.getAuthId() || null,
  userName: localStorageService.getAuthNome() || '',
  isAdmin: localStorageService.getAuthIsAdmin() || false,

  setAuthData(token, id, nome, isAdmin, persistent = true) {
    localStorageService.saveToken(token, persistent);
    localStorageService.saveAuthData(id, nome, isAdmin, persistent);
    this.isLoggedIn = true;
    this.userId = id;
    this.userName = nome;
    this.isAdmin = isAdmin;
  },

  updateLoginStatus() {
    this.isLoggedIn = localStorageService.isAuthenticated();
    this.userId = localStorageService.getAuthId();
    this.userName = localStorageService.getAuthNome();
    this.isAdmin = localStorageService.getAuthIsAdmin();
  },

  resetAuthStatus() {
    this.isLoggedIn = false;
    this.userId = null;
    this.userName = '';
    this.isAdmin = false;
    localStorageService.clearAll();
  },
});