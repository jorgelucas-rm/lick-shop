<template>
  <header class="navbar navbar-expand-lg navbar-dark" style="background-color: #000000; border-bottom: 1px solid #ff0000;">
    <div class="container-fluid">
      <router-link class="navbar-brand d-flex align-items-center" to="/">
        <img src="@/assets/logo.png" alt="Logo" class="logo me-2" />
        <img src="@/assets/nome.png" alt="Nome da Loja" class="nome" />
      </router-link>

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

      <div class="collapse navbar-collapse" id="navbarContent">
        <div class="SearchBar mx-auto">
          <div class="input-group">
            <input
              type="text"
              class="form-control SearchInput"
              placeholder="Digite o que deseja buscar"
              v-model="searchQuery"
              @input="handleSearch"
              @keyup.enter="goToSearchResults"
              aria-label="Barra de pesquisa"
              aria-describedby="search-loading"
            />
          </div>
              <ul v-if="searchQuery.trim().length > 0" class="suggestions-list" role="listbox" aria-label="Sugestões de produtos">
                
                <template v-if="isLoading">
                  <li class="suggestion-item" style="color: #888; cursor: default;">
                    Carregando...
                  </li>
                </template>

                <template v-else-if="suggestedProducts.length > 0">
                  <li
                    v-for="product in suggestedProducts"
                    :key="product.id"
                    @click="goToProductDetail(product)"
                    @keyup.enter="goToProductDetail(product)"
                    role="option"
                    tabindex="0"
                    class="suggestion-item"
                  >
                    {{ product.nome }}
                  </li>
                </template>

                <template v-else>
                  <li class="suggestion-item" style="color: #888; cursor: default;">
                    Nenhum produto encontrado
                  </li>
                </template>

              </ul>
        </div>
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
import api from "@/services/api";
import { authStore } from '@/services/AuthStore';
import localStorageService from '@/services/LocalStorageService';
import debounce from 'lodash/debounce';

export default {
  data() {
    return {
      searchQuery: "",
      suggestedProducts: [],
      cartItems: 0,
      isLoading: false,
    };
  },
  computed: {
    isLoggedIn() {
      return authStore.isLoggedIn;
    },
    userName() {
      return authStore.userName || "Usuário";
    },
  },
  methods: {
    handleSearch: debounce(function () {
      if (this.searchQuery.trim().length >= 1) {
        this.fetchProductsByName();
      } else {
        this.suggestedProducts = [];
      }
    }, 300),

    async fetchProductsByName() {
      this.isLoading = true;
      try {
        const response = await api.get(`/api/v1/produto`, {
          params: { nome: this.searchQuery.trim() },
        });
        this.suggestedProducts = (response.data || []).filter(product =>
          product.nome.toLowerCase().includes(this.searchQuery.trim().toLowerCase())
        );
      } catch (error) {
        console.error("Erro ao buscar produtos:", error);
        this.suggestedProducts = [];
        this.$toast?.error("Erro ao buscar produtos. Tente novamente.");
      } finally {
        this.isLoading = false;
      }
    },

    SearchBarItens() {
      if (this.product.trim().length >= 1) {
        return this.suggestedProducts;
      }
      else {
        return ["Nenhum produto encontrado"];
      }
    },


    goToProductDetail(product) {
      this.$router.push(`/details/${product.id}`);
      this.searchQuery = "";
      this.suggestedProducts = [];
    },

    goToSearchResults() {
      if (this.searchQuery.trim()) {
        this.$router.push(`/search?query=${encodeURIComponent(this.searchQuery)}`);
        this.searchQuery = "";
        this.suggestedProducts = [];
      }
    },

    logout() {
      localStorageService.clearAll();
      authStore.resetAuthStatus();
      this.$router.push('/');
    }
  },
};
</script>

<style scoped>
body {
  font-family: 'Arial', sans-serif;
  font-size: 16px;
}

.logo {
  width: 40px;
  height: auto;
}

.nome {
  height: 30px;
  object-fit: cover;
}

.navbar {
  padding: 0.5rem 1rem;
}

.SearchBar {
  position: relative;
  margin-left: 1rem;
  margin-right: 1rem;
  flex: 1;
  max-width: 600px;
}

.SearchInput {
  width: 100%;
  height: 40px;
  padding: 10px;
  font-size: 14px;
  border-radius: 8px;
  border: 2px solid #ff0000;
  background-color: #1a1a1a;
  color: white;
  transition: border-color 0.3s ease;
}

.SearchInput:focus {
  outline: none;
  border-color: #ff3333;
}

.SearchInput::placeholder {
  color: #ccc;
}

.suggestions-list {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  background-color: #fff;
  border: 1px solid #ff0000;
  border-radius: 8px;
  max-height: 200px;
  overflow-y: auto;
  margin-top: 5px;
  padding: 0;
  list-style-type: none;
  z-index: 1000;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.suggestion-item {
  padding: 10px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.2s ease;
}

.suggestion-item:hover,
.suggestion-item:focus {
  background-color: #f0f0f0;
  outline: none;
}

.navigationpill {
  color: #f3f3f3;
  text-decoration: none;
  font-size: 13px;
  transition: color 0.2s ease;
}

.navigationpill:hover {
  color: #ff0000;
}

.RedLine {
  width: 2px;
  height: 30px;
  background-color: #ff0000;
  margin: 0 10px;
}

@media (max-width: 768px) {
  .SearchBar {
    max-width: 100%;
  }
}
</style>