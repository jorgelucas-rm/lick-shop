<template>
  <header class="navbar navbar-expand-lg navbar-dark" style="background-color: #000000; border-bottom: 1px solid #ff0000;">
    <div class="container-fluid">
      <!-- Logo e Nome da Loja -->
      <router-link class="navbar-brand d-flex align-items-center" to="/">
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
        aria-label="Alternar navegação"
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
            @keyup="handleSearch"
          />
          
          <!-- Lista de Produtos Sugeridos -->
          <ul v-if="suggestedProducts.length" class="suggestions-list">
            <li v-for="product in suggestedProducts" :key="product.id" @click="goToProductDetail(product)">
              {{ product.nome }}
            </li>
          </ul>
        </div>

        <!-- Navegação -->
        <ul class="navbar-nav ms-auto">
          <li class="nav-item d-flex align-items-center">
            <router-link class="nav-link navigationpill" to="/contact">Formas de Contato</router-link>
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
          <!-- Exibe 'Perfil' e 'Sair' quando o usuário está logado -->
          <div v-else>
            <router-link to="/profile" class="btn btn-light me-2">Perfil</router-link>
            <button @click="logout" class="btn btn-outline-light">
              Sair
            </button>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import api from "@/services/api"; // Certifique-se de ajustar a URL da API conforme necessário

export default {
  data() {
    return {
      searchQuery: "",
      suggestedProducts: [],
      isLoggedIn: false,
      userName: "",

      cartItems: 0,
    };
  },
  computed: {
    // Acessa o estado de login diretamente do authStore
    isLoggedIn() {
      return authStore.isLoggedIn;
    },
    // Acessa o nome do usuário para exibir na navbar
    userName() {
      return authStore.userName || "Usuário"; // Use o estado global para o nome
    },
  },
  methods: {
    async handleSearch() {
      if (this.searchQuery.trim()) {
        try {
          const response = await this.fetchProductsByName(this.searchQuery);
          this.suggestedProducts = response.data || [];
        } catch (error) {
          console.error("Erro ao buscar produtos:", error);
        }
      } else {
        this.suggestedProducts = [];

      }
    },
    
    // Método para buscar produtos pela API
    async fetchProductsByName(query) {
      try {
        const response = await api.get(`/api/v1/produto`, {
>>>>>>> develop
          params: { nome: query },
        });
        return response;
      } catch (error) {
        console.error("Erro na busca de produtos:", error);
        return { data: [] };
      }
    },

    // Método para redirecionar para a página de detalhes do produto
    goToProductDetail(product) {
      this.$router.push(`/details/${product.id}`);
      this.suggestedProducts = []; // Limpa as sugestões após o clique
    },
    // Método para logout
    logout() {
      // Remove o token do localStorage
      localStorageService.removeToken();
      // Atualiza o estado global de login
      authStore.updateLoginStatus();
      // Redireciona para a página inicial
      this.$router.push('/');
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
  position: relative;
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

/* Lista de Sugestões */
.suggestions-list {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 8px;
  max-height: 200px;
  overflow-y: auto;
  margin-top: 5px;
  padding: 0;
  list-style-type: none;
}

.suggestions-list li {
  padding: 10px;
  cursor: pointer;
  font-size: 14px;
}

.suggestions-list li:hover {
  background-color: #f0f0f0;
}

/* Navegação */
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

/* Responsividade */
@media (max-width: 768px) {
  .SearchBar {
    max-width: 100%;
  }
}
</style>
