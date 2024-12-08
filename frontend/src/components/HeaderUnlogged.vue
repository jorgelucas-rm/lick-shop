<template>
  <header class="navbar navbar-expand-lg navbar-dark" style="background-color: #000000; border-bottom: 1px solid #ff0000;">
    <div class="container-fluid">
      <!-- Logo e Nome da Loja -->
      <router-link class="navbar-brand d-flex align-items-center" to="/home">
        <img src="@/assets/logo.png" alt="Logo" class="logo me-2" />
        <img src="@/assets/nome.png" alt="Nome da Loja" class="nome" />
      </router-link>

      <!-- Botão do menu hamburguer -->
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarContent"
        aria-controls="navbarContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Conteúdo do menu -->
      <div class="collapse navbar-collapse" id="navbarContent">
        <!-- Barra de Busca -->
        <div class="SearchBar mx-auto">
          <input
            type="text"
            class="form-control SearchInput"
            placeholder="Digite o que deseja buscar"
            v-model="searchQuery"
            @keyup.enter="handleSearch"
          />
        </div>

        <!-- Navegação -->
        <ul class="navbar-nav ms-auto">
          <li class="nav-item d-flex align-items-center">
            <router-link class="nav-link navigationpill" to="/waystocontact">Formas de Contato</router-link>
            <div class="RedLine"></div>
          </li>
          <li class="nav-item d-flex align-items-center">
            <router-link class="nav-link navigationpill" to="/aboutus">Sobre Nós</router-link>
            <div class="RedLine"></div>
          </li>
          <li class="nav-item d-flex align-items-center">
            <router-link class="nav-link navigationpill" to="/wishlist">Lista de Desejos</router-link>
            <div class="RedLine"></div>
          </li>
          <li class="nav-item">
            <router-link class="nav-link navigationpill" to="/cart">
              Carrinho
              <span class="badge bg-danger">{{ cartItems }}</span>
            </router-link>
          </li>
        </ul>

        <!-- Botões de Autenticação -->
        <div class="d-flex align-items-center">
          <router-link
            v-if="!isLoggedIn"
            to="/login"
            class="btn btn-outline-light me-2"
          >
            Entrar
          </router-link>
          <router-link
            v-if="!isLoggedIn"
            to="/register"
            class="btn btn-light me-2"
          >
            Registrar
          </router-link>
          <div v-else class="text-white me-3">
            Bem-vindo, <strong>{{ userName }}</strong>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: "",
      isLoggedIn: false,
      userName: "",
      cartItems: 0,
    };
  },
  methods: {
    handleSearch() {
      if (this.searchQuery.trim()) {
        console.log("Buscando por:", this.searchQuery);
        // Aqui você pode adicionar a lógica para enviar a busca para a API ou realizar outra ação
      }
    },
  },
};
</script>

<style scoped>
/* Geral */
body {
  font-family: 'Arial', sans-serif;
  font-size: 10px;
}

.logo {
  width: 40px;
  height: auto;
}

.nome {
  height: 30px;
  object-fit: cover;
}

/* Navbar */
.navbar {
  padding: 0.5rem 1rem;
}

.SearchBar {
  margin-left: 1rem;
  margin-right: 1rem;
  flex: 1;
  max-width: 600px; /* Aumentando o tamanho máximo */
}

.SearchInput {
  width: 100%;
  height: 40px; /* Ajustando a altura */
  padding: 10px;
  font-size: 14px; /* Garantindo fonte maior */
  border-radius: 8px;
  border: 2px solid #ff0000;
  background-color: black;
  color: white;
}

.SearchInput::placeholder {
  color: #ccc;
}

.navigationpill {
  color: #f3f3f3;
  text-decoration: none;
  font-size: 13px; /* Fonte igual para os links */
}

.navigationpill:hover {
  color: #ff0000;
}

.RedLine {
  width: 2px; /* Um pouco mais grosso */
  height: 30px;
  background-color: #ff0000;
  margin: 0 10px;
}
</style>
