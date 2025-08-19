<template>
  <div class="register-page">
    <div class="background-image"></div>
    <div class="form-container">
      <div class="text-center text-wrapper-2 mb-4">CRIAR CONTA</div>

      <div v-if="currentStep === 1">
        <div class="row justify-content-center">
          <div class="col-12 col-md-6">
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
            <div class="mb-3">
              <label for="username" class="form-label">Nome de usuário</label>
              <input
                type="text"
                id="username"
                v-model="username"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.username" class="error-message">
                {{ errorMessages.username }}
              </div>
            </div>
            <div class="mb-3">
              <label for="telefone" class="form-label">Telefone celular</label>
              <input
                type="tel"
                id="telefone"
                v-model="telefone"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.telefone" class="error-message">
                {{ errorMessages.telefone }}
              </div>
            </div>
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
          <div class="col-12 col-md-6">
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
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input
                type="email"
                id="email"
                v-model="email"
                class="form-control custom-input"
              />
              <div v-if="errorMessages.email" class="error-message">
                {{ errorMessages.email }}
              </div>
            </div>
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
          <div class="col-12 mb-3">
            <button
              type="button"
              @click="nextStep"
              class="btn btn-primary w-100 py-2 custom-button"
            >
              Próximo
            </button>
          </div>
        </div>
      </div>

      <div v-if="currentStep === 2">
        <div class="row justify-content-center">
          <div class="col-12 col-md-6">
            <div class="mb-3">
              <label for="cep" class="form-label">CEP</label>
              <div class="input-row">
                <input
                  type="text"
                  id="cep"
                  v-model="cep"
                  class="form-control custom-input"
                  placeholder="Ex.: 30260080"
                />
                <button
                  type="button"
                  :disabled="loading"
                  @click="buscarCep"
                  class="btn btn-secondary"
                >
                  {{ loading ? "Buscando..." : "Buscar" }}
                </button>
              </div>
              <div v-if="errorMessages.cep" class="error-message">
                {{ errorMessages.cep }}
              </div>
            </div>
            <div class="mb-3">
              <label for="logradouro" class="form-label">Logradouro</label>
              <input
                type="text"
                id="logradouro"
                v-model="logradouro"
                class="form-control custom-input"
                placeholder="Logradouro"
              />
            </div>
            <div class="mb-3">
              <label for="bairro" class="form-label">Bairro</label>
              <input
                type="text"
                id="bairro"
                v-model="bairro"
                class="form-control custom-input"
                placeholder="Bairro"
              />
            </div>
          </div>
          <div class="col-12 col-md-6">
            <div class="mb-3">
              <label for="cidade" class="form-label">Cidade</label>
              <input
                type="text"
                id="cidade"
                v-model="cidade"
                class="form-control custom-input"
                placeholder="Cidade"
              />
            </div>
            <div class="mb-3">
              <label for="estado" class="form-label">Estado</label>
              <input
                type="text"
                id="estado"
                v-model="estado"
                class="form-control custom-input"
                placeholder="Estado"
              />
            </div>
            <div class="mb-3">
              <label for="complemento" class="form-label">Complemento (Opcional)</label>
              <input
                type="text"
                id="complemento"
                v-model="complemento"
                class="form-control custom-input"
                placeholder="Complemento"
              />
            </div>
          </div>
          <div class="col-12 mb-3">
            <button
              type="button"
              @click="previousStep"
              class="btn btn-secondary w-45 py-2 mr-2"
            >
              Voltar
            </button>
            <button
              type="button"
              @click="handleSubmit"
              class="btn btn-primary w-45 py-2 custom-button"
            >
              Cadastrar
            </button>
          </div>
        </div>
      </div>

      <div class="text-center mt-3 tem-conta-clique">
        <span>Já tem uma conta?</span>
        <router-link to="/login">Clique aqui</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { validaCPF } from "@/services/validaCPF";
import api from '@/services/api';

export default {
  data() {
    return {
      currentStep: 1,
      nomeCompleto: "",
      username: "",
      telefone: "",
      dataNascimento: "",
      cpf: "",
      email: "",
      senha: "",
      confirmarSenha: "",
      cep: "",
      logradouro: "",
      bairro: "",
      cidade: "",
      estado: "",
      complemento: "",
      loading: false,
      errorMessages: {}
    };
  },
  methods: {
    formatarData(data) {
      const partes = data.split("-");
      return `${partes[2]}/${partes[1]}/${partes[0]}`;
    },
    nextStep() {
      this.errorMessages = {};
      if (!this.nomeCompleto) this.errorMessages.nomeCompleto = "Nome completo é obrigatório.";
      if (!this.username) this.errorMessages.username = "Nome de usuário é obrigatório.";
      if (!this.telefone) this.errorMessages.telefone = "Telefone celular é obrigatório.";
      if (!this.dataNascimento) {
        this.errorMessages.dataNascimento = "Data de nascimento é obrigatória.";
      } else {
        const hoje = new Date();
        const dataNascimento = new Date(this.dataNascimento);
        let idade = hoje.getFullYear() - dataNascimento.getFullYear();
        const mes = hoje.getMonth() - dataNascimento.getMonth();
        if (mes < 0 || (mes === 0 && hoje.getDate() < dataNascimento.getDate())) idade--;
        if (idade < 18) this.errorMessages.dataNascimento = "Você deve ter pelo menos 18 anos.";
      }
      if (!this.cpf) this.errorMessages.cpf = "CPF é obrigatório.";
      else if
        (!validaCPF(this.cpf)) this.errorMessages.cpf = "CPF inválido.";
      if (!this.email) this.errorMessages.email = "Email é obrigatório.";
      if (!this.senha) this.errorMessages.senha = "Senha é obrigatória.";
      if (!this.confirmarSenha) this.errorMessages.confirmarSenha = "Confirmação de senha é obrigatória.";
      else if (this.senha !== this.confirmarSenha) this.errorMessages.confirmarSenha = "As senhas não coincidem.";

      if (Object.keys(this.errorMessages).length === 0) {
        this.currentStep = 2;
      }
    },
    previousStep() {
      this.currentStep = 1;
    },
    async buscarCep() {
      if (!this.cep) {
        this.errorMessages.cep = "Por favor, insira um CEP válido.";
        return;
      }
      const formattedCep = this.cep.replace(/[-\s]/g, "");
      if (formattedCep.length !== 8) {
        this.errorMessages.cep = "CEP deve conter 8 números.";
        return;
      }
      this.loading = true;
      this.errorMessages.cep = "";
      try {
        const data = await this.fetchCepData(formattedCep);
        if (data) this.preencherCamposComEndereco(data);
        else throw new Error("Erro ao buscar CEP");
      } catch (error) {
        console.error(error.message);
        this.errorMessages.cep = "Erro ao buscar CEP. Tentando novamente...";
        setTimeout(async () => {
          try {
            const data = await this.fetchCepData(formattedCep);
            if (data) this.preencherCamposComEndereco(data);
            else throw new Error("Erro ao buscar CEP");
          } catch (retryError) {
            this.errorMessages.cep = "Erro ao buscar CEP. Verifique os dados ou tente novamente.";
          } finally {
            this.loading = false;
          }
        }, 2000);
      }
    },
    async fetchCepData(cep) {
      try {
        const response = await api.get(`/api/v1/endereco/${cep}`);
        return response.data;
      } catch (error) {
        console.error("Erro na requisição de CEP:", error);
        throw new Error("Falha na requisição");
      }
    },
    preencherCamposComEndereco(data) {
      if (!data || !data.logradouro || !data.bairro || !data.cidade || !data.estado) {
        this.errorMessages.cep = "Dados incompletos retornados pela API.";
        return;
      }
      this.logradouro = data.logradouro || "";
      this.bairro = data.bairro || "";
      this.cidade = data.cidade || "";
      this.estado = data.estado || "";
      this.complemento = data.complemento || "";
      this.loading = false;
    },
    async handleSubmit() {
      this.errorMessages = {};
      if (!this.cep) this.errorMessages.cep = "CEP é obrigatório.";
      if (!this.logradouro) this.errorMessages.logradouro = "Logradouro é obrigatório.";
      if (!this.bairro) this.errorMessages.bairro = "Bairro é obrigatório.";
      if (!this.cidade) this.errorMessages.cidade = "Cidade é obrigatória.";
      if (!this.estado) this.errorMessages.estado = "Estado é obrigatório.";

      if (Object.keys(this.errorMessages).length > 0) return;

      const dataNascimentoFormatada = this.formatarData(this.dataNascimento);

      this.$store.commit("setUser", {
        nome: this.nomeCompleto,
        cpf: this.cpf,
        dataNascimento: dataNascimentoFormatada,
      });
      this.$store.commit("setRegister", {
        username: this.username,
        senha: this.senha,
        email: this.email,
      });
      this.$store.commit("setLocation", {
        cep: this.cep,
        logradouro: this.logradouro,
        bairro: this.bairro,
        cidade: this.cidade,
        estado: this.estado,
        complemento: this.complemento,
      });

      try {
        await this.$store.dispatch("registerUser");
        alert("Cadastro realizado com sucesso!");
        this.$store.commit("clearData");
        this.$router.push("/login");
      } catch (error) {
        alert("Erro ao realizar o cadastro. Tente novamente.");
      }
    }
  }
};
</script>

<style scoped>
.register-page {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
  overflow-y: auto;
}

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

.form-container {
  background-color: #1b1b1bcc;
  color: white;
  border: 0.64px solid #ff0000;
  border-radius: 3.03px;
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

.input-row {
  display: flex;
  gap: 10px;
}

.input-row button {
  padding: 8px 15px;
  background-color: #ff0000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.input-row button:disabled {
  background-color: #999;
  cursor: not-allowed;
}

.w-45 {
  width: 45%;
  display: inline-block;
}

.mr-2 {
  margin-right: 10px;
}

@media (max-width: 768px) {
  .form-container {
    padding: 15px;
  }
  .input-row {
    flex-direction: column;
  }
  .input-row button {
    width: 100%;
    margin-top: 10px;
  }
  .w-45 {
    width: 100%;
    margin-bottom: 10px;
  }
  .mr-2 {
    margin-right: 0;
  }
}
</style>