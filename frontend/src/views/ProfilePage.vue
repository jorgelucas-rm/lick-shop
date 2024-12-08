<template>
    <div class="user-page">
      <h1>Bem-vindo, {{ user.name }}!</h1>
      <p><strong>Email:</strong> {{ user.email }}</p>
      <p><strong>Telefone:</strong> {{ user.phone }}</p>
      
      <button @click="logout">Sair</button>
    </div>
  </template>
  
  <script>
  import localStorageService from '@/services/localStorage';
  
  export default {
    data() {
      return {
        user: {
          name: '',
          email: '',
          phone: ''
        }
      };
    },
    created() {
      this.loadUserData();
    },
    methods: {
      // Carregar dados do usuário a partir do localStorage (ou API, se necessário)
      loadUserData() {
        const token = localStorageService.getToken();
  
        if (token) {
          // Exemplo de como você poderia pegar os dados do usuário do localStorage (ou de uma API)
          const userData = JSON.parse(localStorageService.getUserData());
          this.user = userData || {};
        }
      },
      // Função de logout
      logout() {
        localStorageService.removeToken(); // Remove o token de autenticação
        localStorageService.removeUserData(); // Remove os dados do usuário
        this.$router.push({ name: 'Login' }); // Redireciona para a página de login
      }
    }
  };
  </script>
  
  <style scoped>
  .user-page {
    padding: 20px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #ff3b30;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 16px;
  }
  
  button:hover {
    background-color: #ff5c4d;
  }
  </style>
  