<template>
  <div class="background-image"></div>
  <div class="loginpage">
    <div class="FormLogIn">
      <div class="Login">Login</div>
      <form @submit.prevent="handleLogin">
        <div class="InputField">
          <div class="Label">Usuário</div>
          <input v-model="username" type="text" class="Username" required />
        </div>
        <div class="InputField">
          <div class="Label">Senha</div>
          <input v-model="password" type="password" class="Password" required />
        </div>
        <div class="Group1">
          <div class="MantenhaMeConectado">Mantenha-me conectado</div>
          <div class="Switch" :class="{ active: keepMeConnected }" @click="toggleKeepMeConnected">
            <div class="HandleShape"></div>
          </div>
        </div>
        <router-link to="/recovery" class="EsqueciMinhaSenha">Esqueci minha senha</router-link>
        <button type="submit" class="button"><div class="Entrar">Entrar</div></button>
        <div class="AindaNOTemContaCriarConta">
          Ainda não tem conta? <router-link to="/register" class="text">Crie uma conta</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';
import localStorageService from '@/services/localStorage'; // Importando o serviço de localStorage

export default {
  data() {
    return {
      username: '',
      password: '',
      keepMeConnected: false,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await api.post('/api/v1/login', {
          username: this.username,
          password: this.password,
        });
        const { token } = response.data;

        // Salvar o token no localStorage se o usuário escolher "manter-me conectado"
        if (this.keepMeConnected) {
          localStorageService.saveToken(token);
        } else {
          sessionStorage.setItem('authToken', token); // Usando sessionStorage se não escolher manter-se conectado
        }

        // Redirecionar para a página principal ou área autenticada
        this.$router.push({ name: 'dashboard' });
      } catch (error) {
        console.error('Erro ao fazer login:', error);
        alert('Erro ao realizar o login. Verifique suas credenciais.');
      }
    },

    toggleKeepMeConnected() {
      this.keepMeConnected = !this.keepMeConnected;
    }
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
  background-image: url('@/assets/background.jpg'); /* Caminho para a imagem */
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.loginpage {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: transparent; /* Ajuste para que a imagem de fundo seja visível */
  position: relative;
  overflow: hidden;
}

.FormLogIn {
  width: 438.96px;
  height: 520.8px;
  flex-shrink: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 438px;
  background: rgba(27, 27, 27, 0.8);
  border-radius: 5px;
  border: 1px solid #ff0000;
  padding: 20px;
  box-sizing: border-box;
  z-index: 1; /* Garante que o formulário esteja sobre a imagem */
}

.Login {
  color: #F00;
  -webkit-text-stroke-width: 0.7409698963165283;
  -webkit-text-stroke-color: #0D0D0D;
  font-family: 'Inter', sans-serif;
  font-size: 47.422px;
  font-style: normal;
  font-weight: 700;
  line-height: 100%;
}

.Label {
  color: #f3f3f3;
  font-size: 1rem;
}

.InputField {
  display: flex;
  margin-top: 5%;
  margin-left: -4%;
  width: 350px;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
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

.button {
  width: 100%;
  padding: 12px;
  margin-top: 20px;
  background: #ff0000;
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  font-size: 1.2rem;
  text-align: center;
}

.Group1 {
  display: flex;
  align-items: center;
  margin-top: 20px;
}

.MantenhaMeConectado {
  color: #f3f3f3;
  font-size: 0.9rem;
  margin-right: 10px;
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
  left: 18px; /* Move a alça para a direita quando ativado */
}

.EsqueciMinhaSenha {
  font-size: 0.9rem;
  color: #f3f3f3;
  text-decoration: underline;
  margin-top: 15px;
}

.AindaNOTemContaCriarConta {
  color: #f3f3f3;
  font-size: 1rem;
  margin-top: 15%;
  text-align: center;
}

.text {
  color: #ff0000;
  text-decoration: underline;
}
</style>
