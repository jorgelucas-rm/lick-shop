<template>
  <div id="app">
    <HeaderUnlogged/>

    <main>
      <router-view />
    </main>

    <FooterDown />
  </div>
</template>

<script>
import HeaderUnlogged from './components/HeaderUnlogged';
import FooterDown from './components/FooterDown';
import { authStore } from './services/AuthStore'; // Importa o estado global

export default {
  components: {
    HeaderUnlogged,
    FooterDown,
  },
  computed: {
    // Acessa o estado de login diretamente do authStore
    isLoggedIn() {
      return authStore.isLoggedIn; // Verifica se o usuário está logado
    },
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
