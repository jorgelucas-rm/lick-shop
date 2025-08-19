<template>
  <div class="background-image"></div>
  <div class="loginpage d-flex justify-content-center align-items-center">
    <div class="FormLogIn p-4 rounded shadow-lg">
      <div class="Login text-center text-danger font-weight-bold mb-4">Login</div>
      <form @submit.prevent="handleLogin">
        <div class="InputField mb-3">
          <label for="username" class="Label text-white">Usuário</label>
          <input 
            id="username"
            v-model="username" 
            type="text" 
            class="Username form-control" 
            required 
          />
        </div>
        <div class="InputField mb-3">
          <label for="password" class="Label text-white">Senha</label>
          <input 
            id="password"
            v-model="password" 
            type="password" 
            class="Password form-control" 
            required 
          />
        </div>
        <div class="Group1 d-flex justify-content-between align-items-center mb-3">
          <div class="MantenhaMeConectado text-white">Mantenha-me conectado</div>
          <div 
            class="Switch" 
            :class="{ active: keepMeConnected }" 
            @click="toggleKeepMeConnected"
          >
            <div class="HandleShape"></div>
          </div>
        </div>
        <router-link to="/recovery" class="EsqueciMinhaSenha text-white">Esqueci minha senha</router-link>
        <button type="submit" class="button mt-4" :disabled="isLoading">
          <div v-if="isLoading">Entrando...</div>
          <div v-else>Entrar</div>
        </button>
        <div class="AindaNOTemContaCriarConta text-white text-center mt-3">
          Ainda não tem conta? 
          <router-link to="/register" class="text-danger">Crie uma conta</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';
import { authStore } from '@/services/AuthStore';

export default {
  data() {
    return {
      username: '',
      password: '',
      keepMeConnected: false,
      isLoading: false,
    };
  },
  methods: {
    async handleLogin() {
      this.isLoading = true;
      try {
        const loginResponse = await api.post('/api/v1/login', {
          username: this.username,
          password: this.password,
        });
        const token = loginResponse.data;

        const decodedToken = JSON.parse(atob(token.split('.')[1]));
        const id = decodedToken.id;
        const nome = decodedToken.nome || decodedToken.name || this.username;
        const isAdmin = decodedToken.roles?.includes('ADMIN') || decodedToken.isAdmin || false;

        const storage = this.keepMeConnected ? localStorage : sessionStorage;
        storage.setItem('auth_token', token);
        storage.setItem('auth_id', id);
        storage.setItem('auth_nome', nome);
        storage.setItem('auth_isAdmin', JSON.stringify(isAdmin));

        authStore.isLoggedIn = true;
        authStore.isAdmin = isAdmin;

        this.$emit('loginStatusChanged');

        // Redireciona
        this.$router.push({ name: 'Home' });
      } catch (error) {
        if (error.response?.status === 401) {
          alert('Usuário ou senha incorretos.');
        } else if (error.response) {
          alert(`Erro ${error.response.status}: ${error.response.data || 'Erro desconhecido'}`);
        } else if (error.request) {
          alert('Não foi possível conectar ao servidor. Verifique sua conexão.');
        } else {
          alert('Erro ao processar a solicitação. Tente novamente.');
        }
      } finally {
        this.isLoading = false;
      }
    },
    toggleKeepMeConnected() {
      this.keepMeConnected = !this.keepMeConnected;
    },
  },
};
</script>



<style scoped>
.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('@/assets/background.jpg');
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.loginpage {
  height: 100vh;
}

.FormLogIn {
  width: 100%;
  max-width: 450px;
  background: rgba(27, 27, 27, 0.8);
  border-radius: 5px;
  border: 1px solid #ff0000;
  padding: 20px;
}

.Login {
  color: #f00;
  font-size: 47px;
  font-weight: 700;
}

.text-white {
  color: white !important;
}

.text-danger {
  color: #ff0000 !important;
}

.InputField {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.Username,
.Password {
  width: 100%;
  padding: 10px;
  background: transparent;
  border: 1px solid #ff3333;
  border-radius: 4px;
  color: white;
  font-size: 1rem;
}

.Switch {
  width: 36px;
  height: 20px;
  background: #666;
  border-radius: 50px;
  position: relative;
  cursor: pointer;
  transition: background 0.3s;
}

.Switch.active {
  background: #ff0000;
}

.HandleShape {
  width: 16px;
  height: 16px;
  background: white;
  border-radius: 50%;
  position: absolute;
  top: 2px;
  left: 2px;
  transition: left 0.3s;
}

.Switch.active .HandleShape {
  left: 18px;
}

.EsqueciMinhaSenha {
  font-size: 0.9rem;
  text-decoration: underline;
}

.AindaNOTemContaCriarConta {
  font-size: 1rem;
  text-align: center;
  margin-top: 15px;
}

.button {
  width: 100%;
  padding: 12px;
  background: #ff0000;
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  font-size: 1.2rem;
  text-align: center;
}

.button:hover {
  background-color: #cc0000;
}
</style>