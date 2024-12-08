<template>
  <div id="app">
    <!-- Renderiza HeaderLogged se o usuário estiver logado, caso contrário renderiza HeaderUnlogged -->
    <HeaderLogged v-if="isLoggedIn" />
    <HeaderUnlogged v-else />
    
    <main>
      <router-view />
    </main>

    <FooterDown />
  </div>
</template>

<script>
import HeaderUnlogged from './components/HeaderUnlogged.vue';
import FooterDown from './components/FooterDown.vue';
import HeaderLogged from './components/HeaderLogged.vue';
import localStorageService from './services/localStorage'; // Serviço de autenticação

export default {
  components: {
    HeaderLogged,
    HeaderUnlogged,
    FooterDown,
  },

  data() {
    return {
      isLoggedIn: localStorageService.isAuthenticated(), // Verifica se o usuário está logado
    };
  },

  created() {
    // Atualiza o estado de login se necessário (ex: quando o usuário loga ou desloga)
    this.isLoggedIn = localStorageService.isAuthenticated();
  },

  methods: {
    updateLoginStatus() {
      // Método para atualizar o estado de login
      this.isLoggedIn = localStorageService.isAuthenticated();
    }
  },
};
</script>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh; 
}

main {
  flex: 1; 
}

footer {
  flex-shrink: 0;
}
</style>
