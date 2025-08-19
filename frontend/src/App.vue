<template>
  <div id="app">
    <HeaderNav/>

    <main>
      <router-view />
    </main>

    <FooterDown />
  </div>
</template>

<script>
import HeaderNav from './components/HeaderNav';
import FooterDown from './components/FooterDown';
import { authStore } from './services/AuthStore';

export default {
  components: {
    HeaderNav,
    FooterDown,
  },
  computed: {
    isLoggedIn() {
      return authStore.isLoggedIn;
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
