<template>
  <div class="user-page">
    <h1>Bem-vindo, {{ user.nome }}!</h1>
    <form @submit.prevent="updateUserData">
      <div>
        <label for="nome">Nome:</label>
        <input type="text" v-model="user.nome" id="nome" />
      </div>

      <div>
        <label for="cpf">CPF:</label>
        <input type="text" v-model="user.cpf" id="cpf" />
      </div>

      <div>
        <label for="nascimento">Data de Nascimento:</label>
        <input type="date" v-model="user.nascimento" id="nascimento" />
      </div>

      <div>
        <label for="cep">CEP:</label>
        <input type="text" v-model="user.endereco[0].cep" id="cep" />
      </div>

      <div>
        <label for="logradouro">Logradouro:</label>
        <input type="text" v-model="user.endereco[0].logradouro" id="logradouro" />
      </div>

      <div>
        <label for="bairro">Bairro:</label>
        <input type="text" v-model="user.endereco[0].bairro" id="bairro" />
      </div>

      <div>
        <label for="cidade">Cidade:</label>
        <input type="text" v-model="user.endereco[0].cidade" id="cidade" />
      </div>

      <div>
        <label for="estado">Estado:</label>
        <input type="text" v-model="user.endereco[0].estado" id="estado" />
      </div>

      <div>
        <label for="numero">Número:</label>
        <input type="text" v-model="user.endereco[0].numero" id="numero" />
      </div>

      <div>
        <label for="complemento">Complemento:</label>
        <input type="text" v-model="user.endereco[0].complemento" id="complemento" />
      </div>

      <button type="submit">Atualizar</button>
    </form>

    <button @click="logout">Sair</button>
  </div>
</template>

<script>
import localStorageService from '@/services/localStorage';
import api from '@/services/api';

export default {
  data() {
    return {
      user: {
        id: '',
        nome: '',
        cpf: '',
        nascimento: '',
        endereco: [
          {
            cep: '',
            logradouro: '',
            bairro: '',
            cidade: '',
            estado: '',
            numero: '',
            complemento: '',
            enderecoCompleto: ''
          }
        ]
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
        const userData = JSON.parse(localStorageService.getUserData());
        this.user = userData || {};
      }
    },

    // Função para atualizar os dados do usuário
    updateUserData() {
      const token = localStorageService.getToken();

      if (token) {
        const userId = this.user.id; // ID do usuário logado
        const updatedUser = {
          nome: this.user.nome,
          cpf: this.user.cpf,
          nascimento: this.user.nascimento,
          endereco: this.user.endereco
        };

        // Enviando a requisição PUT para a API
        api.put(`/api/v1/usuario/${userId}`, updatedUser, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        })
          .then(response => {
            // Sucesso na atualização, atualizar os dados no localStorage
            localStorageService.setUserData(JSON.stringify(response.data));
            this.user = response.data; // Atualiza o estado local com os novos dados
            alert('Dados atualizados com sucesso!');
          })
          .catch(error => {
            console.error('Erro ao atualizar dados:', error);
            alert('Erro ao atualizar os dados. Tente novamente.');
          });
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

form {
  display: flex;
  flex-direction: column;
}

form div {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

input {
  padding: 10px;
  font-size: 16px;
}

button {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #45a049;
}

button[type="submit"] {
  margin-top: 20px;
}
</style>
