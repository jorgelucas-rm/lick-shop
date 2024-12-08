<template>
  <div class="header-wrapper">
    <div class="header-logged">
      <div class="block">
        <router-link to="/home">
          <img class="designer-1-1" src="@/assets/logo.png" alt="Logo" />
          <img class="lickshop" src="@/assets/nome.png" alt="Nome da Loja" />
        </router-link>
      </div>
    </div>

    <div class="SearchBar">
      <input
        type="text"
        class="SearchInput"
        placeholder="Digite o que deseja buscar"
        v-model="searchQuery"
        @keyup.enter="handleSearch"
      />
    </div>

    <div class="right-section">
      <div class="navigation">
        <router-link to="/waystocontact" class="navigationpill">Formas de Contato</router-link>
        <div class="RedLine"></div>
        <router-link to="/aboutus" class="navigationpill">Sobre Nós</router-link>
        <div class="RedLine"></div>
        <router-link to="/wishlist" class="navigationpill">Lista de Desejos</router-link>
        <div class="RedLine"></div>
        <router-link to="/cart" class="navigationpill">
          Carrinho
          <span class="Notification">{{ cartItems }}</span>
        </router-link>
      </div>
      <div class="header-auth">
        <router-link to="/profile" class="navigationpill">{{ userName }}</router-link>
        <button class="logout-button">Sair</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',
      isLoggedIn: true, // ou baseado em uma verificação real de login
      userName: 'Usuário', // nome do usuário autenticado
      cartItems: 0, // Número de itens no carrinho
    };
  },
  methods: {
    handleSearch() {
      if (this.searchQuery.trim()) {
        console.log('Buscando por:', this.searchQuery);
        // Aqui você pode adicionar a lógica para enviar a busca para a API ou realizar outra ação
      }
    },
  },
};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.header-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background: #000000;
  border-bottom: 1px solid #ff0000;
}

.header-logged {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: calc(1100px * 0.95);
  padding: calc(11px * 0.95) calc(22px * 0.95);
}

.block {
  display: flex;
  align-items: center;
  gap: calc(11px * 0.95);
}

.designer-1-1 {
  width: calc(39px * 0.95);
  height: auto;
}

.lickshop {
  height: calc(27.5px * 0.95);
  object-fit: cover;
}

/* Navegação */
.navigation {
  display: flex;
  align-items: center;
  gap: calc(19.8px * 0.95);
  margin-left: auto;
}

.navigationpill {
  padding: calc(5.5px * 0.95);
  color: #f3f3f3;
  font-size: calc(9.9px * 0.95);
  font-family: Inter, sans-serif;
  border-radius: 5px;
  text-align: center;
  text-decoration: none;
}

.navigationpill:hover {
  color: #ff0000;
}

/* Busca e perfil */
.right-section {
  display: flex;
  align-items: center;
  gap: calc(13.2px * 0.95);
}

.SearchBar {
  margin-left: calc(16.5px * 0.95);
  margin-right: calc(16.5px * 0.95);
}

.SearchInput {
  width: calc(700px * 0.95);
  max-width: calc(495px * 0.95);
  padding: calc(5px * 0.95);
  font-size: calc(13.2px * 0.95);
  font-family: Inter, sans-serif;
  border-radius: 5px;
  border: 1px solid #ff0000;
  background-color: black;
  color: white;
  box-sizing: border-box;
}

.search-icon {
  padding: calc(5.5px * 0.95) calc(11px * 0.95);
  color: #ffffff;
  cursor: pointer;
}

/* Perfil */
.header-auth {
  display: flex;
  align-items: center;
  gap: calc(11px * 0.95);
}

.button {
  font-family: Inter, sans-serif;
  text-decoration: none;
  background: #f3f3f3;
  border: 1px solid #2c2c2c;
  border-radius: 5px;
  padding: calc(5.5px * 0.95) calc(11px * 0.95);
  color: #2c2c2c;
  font-size: calc(14.3px * 0.95);
  cursor: pointer;
}

.button-out {
  font-family: Inter, sans-serif;
  text-decoration: none;
  background: #2c2c2c;
  border-radius: 5px;
  border: 1px solid #2c2c2c;
  padding: calc(5.5px * 0.95) calc(11px * 0.95);
  color: #f3f3f3;
  font-size: calc(14.3px * 0.95);
  cursor: pointer;
}

.RedLine {
  width: calc(1.1px * 0.95);
  height: calc(27.5px * 0.95);
  background: #ff0000;
}
</style>
