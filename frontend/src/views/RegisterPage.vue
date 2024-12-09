<template>
  <div class="loginpage">
    <div class="background-image"></div>
    <div class="form-log-in container py-5">
      <div class="text-center text-wrapper-2 mb-4">CRIAR CONTA</div>

      <!-- Formulário -->
      <form @submit.prevent="handleSubmit">
        <div class="row justify-content-center">
          <!-- Primeira Coluna (4 campos) -->
          <div class="col-12 col-md-6">
            <!-- Nome Completo -->
            <div class="mb-3">
              <label for="nomeCompleto" class="form-label">Nome completo</label>
              <input
                type="text"
                id="nomeCompleto"
                v-model="nomeCompleto"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.nomeCompleto" class="error-message">
                {{ errorMessages.nomeCompleto }}
              </div>
            </div>

            <!-- Nome de Usuário -->
            <div class="mb-3">
              <label for="username" class="form-label">Nome de usuário</label>
              <input
                type="text"
                id="username"
                v-model="username"
                class="form-control custom-input"
                @input="validateUsername"
              />
              <div v-if="errorMessages.username" class="error-message">
                {{ errorMessages.username }}
              </div>
            </div>

            <!-- Telefone -->
            <div class="mb-3">
              <label for="telefone" class="form-label">Telefone celular</label>
              <input
                type="tel"
                id="telefone"
                v-model="telefone"
                class="form-control custom-input"
                @input="validateTelefone"
              />
              <div v-if="errorMessages.telefone" class="error-message">
                {{ errorMessages.telefone }}
              </div>
            </div>

            <!-- Data de Nascimento -->
            <div class="mb-3">
              <label for="dataNascimento" class="form-label">Data de nascimento</label>
              <input
                type="date"
                id="dataNascimento"
                v-model="dataNascimento"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.dataNascimento" class="error-message">
                {{ errorMessages.dataNascimento }}
              </div>
            </div>
          </div>

          <!-- Segunda Coluna (4 campos) -->
          <div class="col-12 col-md-6">
            <!-- CPF -->
            <div class="mb-3">
              <label for="cpf" class="form-label">CPF</label>
              <input
                type="text"
                id="cpf"
                v-model="cpf"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.cpf" class="error-message">
                {{ errorMessages.cpf }}
              </div>
            </div>

            <!-- Email -->
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input
                type="email"
                id="email"
                v-model="email"
                class="form-control custom-input"
                @input="validateEmail"
              />
              <div v-if="errorMessages.email" class="error-message">
                {{ errorMessages.email }}
              </div>
            </div>

            <!-- Senha -->
            <div class="mb-3">
              <label for="senha" class="form-label">Crie sua senha</label>
              <input
                type="password"
                id="senha"
                v-model="senha"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.senha" class="error-message">
                {{ errorMessages.senha }}
              </div>
            </div>

            <!-- Confirmar Senha -->
            <div class="mb-3">
              <label for="confirmarSenha" class="form-label">Confirme sua senha</label>
              <input
                type="password"
                id="confirmarSenha"
                v-model="confirmarSenha"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.confirmarSenha" class="error-message">
                {{ errorMessages.confirmarSenha }}
              </div>
            </div>
          </div>

          <!-- Botão de Prosseguir -->
          <div class="col-12 mb-3">
            <button
              type="submit"
              class="btn btn-primary w-100 py-2 custom-button"
            >
              Prosseguir
            </button>
          </div>
        </div>
      </form>

      <!-- Link para Login -->
      <div class="text-center mt-3 tem-conta-clique">
        <span>Já tem uma conta?</span>
        <router-link to="/login">Clique aqui</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { validaCPF } from "@/services/validaCPF"; // Certifique-se de ter um serviço de validação de CPF

export default {
  data() {
    return {
      nomeCompleto: "",
      username: "",
      telefone: "",
      senha: "",
      confirmarSenha: "",
      email: "",
      cpf: "",
      dataNascimento: "",
      errorMessages: {}
    };
  },
  methods: {
    formatarData(data) {
      // Converte "yyyy-MM-dd" para "dd/MM/yyyy"
      const partes = data.split("-");
      return `${partes[2]}/${partes[1]}/${partes[0]}`;
    },
    handleSubmit() {
      // Reinicia mensagens de erro
      this.errorMessages = {};

      // Validações
      if (!this.nomeCompleto) {
        this.errorMessages.nomeCompleto = "Nome completo é obrigatório.";
      }
      if (!this.username) {
        this.errorMessages.username = "Nome de usuário é obrigatório.";
      }
      if (!this.telefone) {
        this.errorMessages.telefone = "Telefone celular é obrigatório.";
      }
      if (!this.dataNascimento) {
        this.errorMessages.dataNascimento = "Data de nascimento é obrigatória.";
      } else {
        const hoje = new Date();
        const dataNascimento = new Date(this.dataNascimento);
        let idade = hoje.getFullYear() - dataNascimento.getFullYear();
        const mes = hoje.getMonth() - dataNascimento.getMonth();

        if (mes < 0 || (mes === 0 && hoje.getDate() < dataNascimento.getDate())) {
          idade--;
        }

        if (idade < 18) {
          this.errorMessages.dataNascimento = "Você deve ter pelo menos 18 anos.";
        }
      }
      if (!this.cpf) {
        this.errorMessages.cpf = "CPF é obrigatório.";
      } else if (!validaCPF(this.cpf)) {
        this.errorMessages.cpf = "CPF inválido.";
      }
      if (!this.email) {
        this.errorMessages.email = "Email é obrigatório.";
      }
      if (!this.senha) {
        this.errorMessages.senha = "Senha é obrigatória.";
      }
      if (!this.confirmarSenha) {
        this.errorMessages.confirmarSenha = "Confirmação de senha é obrigatória.";
      } else if (this.senha !== this.confirmarSenha) {
        this.errorMessages.confirmarSenha = "As senhas não coincidem.";
      }

      // Se houver algum erro, impede o envio
      if (Object.keys(this.errorMessages).length > 0) {
        return;
      }

      // Formata a data de nascimento antes de enviar
      const dataNascimentoFormatada = this.formatarData(this.dataNascimento);

      // Sincroniza dados com o Vuex Store
      this.$store.commit("setUser", {
        nome: this.nomeCompleto,
        cpf: this.cpf,
        dataNascimento: dataNascimentoFormatada, // Data formatada
      });
      this.$store.commit("setRegister", {
        username: this.username,
        senha: this.senha,
        email: this.email,
      });

      console.log("Dados enviados para o store com sucesso.");
      this.$router.push("/local");
    }
  }
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
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh; /* Ajusta a altura para ocupar a tela inteira */
  padding: 20px;
  overflow-y: auto; /* Habilita o scroll da página */
}

.form-log-in {
  background-color: #1b1b1bcc;
  color: white;
  border: 0.64px solid;
  border-color: #ff0000;
  border-radius: 3.03px;
  position: relative;
  max-width: 900px;
  width: 100%;
  padding: 30px;
  box-sizing: border-box;
  overflow-y: auto;
}

.text-wrapper-2 {
  font-family: "Roboto", sans-serif;
  font-size: 24px;
  font-weight: 500;
  line-height: 34px;
  color: white;
}

.custom-input {
  background: transparent;
  border: 1px solid red;
  color: white;
  padding: 8px;
  font-size: 14px;
}

.custom-input:focus {
  outline: none;
  border-color: #ff0000;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
}

.tem-conta-clique {
  font-family: "Roboto", sans-serif;
  font-size: 14px;
  line-height: 22px;
  color: white;
}

.tem-conta-clique a {
  color: #ff0000;
  text-decoration: none;
}

/* Botão com estilo melhorado */
.custom-button {
  background-color: #ff0000;
  border: none;
  border-radius: 5px;
  color: white;
  font-size: 16px;
  font-weight: bold;
  transition: background-color 0.3s, transform 0.3s;
}

.custom-button:hover {
  background-color: #cc0000;
  transform: scale(1.05);
}
</style>
