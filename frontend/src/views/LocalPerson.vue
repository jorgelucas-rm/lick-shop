<template>
  <div class="login-page">
    <div class="background-image"></div>
    <div class="form-container">
      <label class="form-title" for="cep">CEP</label>
      <!-- Campos do formulário -->
      <div class="input-group">
        <div v-if="errorMessages.cep" class="error-message">
          {{ errorMessages.cep }}
        </div>
        <div class="input-row">
          <input
            type="text"
            id="cep"
            v-model="cep"
            placeholder="Ex.: 30260080"
          />
          <button :disabled="loading" @click="buscarCep">
            {{ loading ? "Buscando..." : "Buscar" }}
          </button>
        </div>
      </div>

      <!-- Outros campos do formulário -->
      <div class="input-group">
        <label for="logradouro">Logradouro</label>
        <input type="text" id="logradouro" v-model="logradouro" placeholder="Logradouro" />
      </div>
      <div class="input-group">
        <label for="bairro">Bairro</label>
        <input type="text" id="bairro" v-model="bairro" placeholder="Bairro" />
      </div>
      <div class="input-group">
        <label for="cidade">Cidade</label>
        <input type="text" id="cidade" v-model="cidade" placeholder="Cidade" />
      </div>
      <div class="input-group">
        <label for="estado">Estado</label>
        <input type="text" id="estado" v-model="estado" placeholder="Estado" />
      </div>
      <div class="input-group">
        <label for="complemento">Complemento (Opcional)</label>
        <input type="text" id="complemento" v-model="complemento" placeholder="Complemento" />
      </div>

      <!-- Botão prosseguir -->
      <button class="submit-button" @click="prosseguir">Prosseguir</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cep: "",
      logradouro: "",
      bairro: "",
      cidade: "",
      estado: "",
      complemento: "",
      loading: false,
      errorMessages: {
        cep: "",
      },
    };
  },
  methods: {
    async buscarCep() {
      if (!this.cep) {
        this.errorMessages.cep = "Por favor, insira um CEP válido.";
        return;
      }

      // Remover traços e espaços do CEP
      const formattedCep = this.cep.replace(/[-\s]/g, "");

      if (formattedCep.length !== 8) {
        this.errorMessages.cep = "CEP deve conter 8 números.";
        return;
      }

      this.loading = true;
      this.errorMessages.cep = ""; // Limpar mensagens de erro anteriores
      try {
        // Requisição à API com o CEP formatado
        const response = await fetch(
          `https://api.lickshop.acilab.com.br/api/v1/endereco/${formattedCep}`
        );

        if (!response.ok) {
          throw new Error("Erro ao se conectar à API.");
        }

        const data = await response.json();

        // Verificar se a resposta contém os dados necessários
        if (!data || !data.logradouro || !data.bairro || !data.cidade || !data.estado) {
          throw new Error("Dados incompletos retornados pela API.");
        }

        // Preencher os campos com os dados recebidos da API
        this.logradouro = data.logradouro || "";
        this.bairro = data.bairro || "";
        this.cidade = data.cidade || "";
        this.estado = data.estado || "";
        this.complemento = data.complemento || ""; // Opcional
      } catch (error) {
        this.errorMessages.cep = "Erro ao buscar CEP. Verifique os dados ou tente novamente.";
        console.error(error.message);
      } finally {
        this.loading = false;
      }
    },
    async prosseguir() {
      if (!this.logradouro || !this.bairro || !this.cidade || !this.estado) {
        alert("Preencha todos os campos obrigatórios.");
        return;
      }

      // Salvar os dados no store
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
        this.$store.commit("clearData"); // Limpar o estado após o registro
      } catch (error) {
        alert("Erro ao realizar o cadastro. Tente novamente.");
      }
    },
  },
};
</script>

<style scoped>
.login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  position: relative;
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

.form-title {
  display: flex;
  justify-content: start;
  font-weight: bold;
}

.form-container {
  background: rgba(27, 27, 27, 0.9);
  border: 2px solid #ff0000;
  border-radius: 8px;
  padding: 20px 30px;
  max-width: 400px;
  width: 100%;
  color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  text-align: center;
}

.input-group {
  margin-bottom: 15px;
  text-align: left;
}

.input-row #cep {
  width: 230px;
  padding: 8px;
  border: 1px solid #ffffff;
  border-radius: 4px;
  background: transparent;
  color: white;
}
.input-group label {
  display: block;
  margin-bottom: 5px;
  font-size: 14px;
  color: #ffffff;
}

.input-group input {
  width: 95%;
  padding: 8px;
  border: 1px solid #ffffff;
  border-radius: 4px;
  background: transparent;
  color: white;
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

.submit-button {
  width: 100%;
  padding: 10px;
  background-color: #ff0000;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  font-weight: bold;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: black;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-left: 30%;
  margin-top: -20px;
}

input:-webkit-autofill {
  background: transparent !important;
  -webkit-box-shadow: 0 0 0px 1000px transparent inset !important;
  color: white !important;
}
</style>
