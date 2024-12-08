<template>
  <div class="loginpage">
    <div class="background-image"></div>
    <div class="form-log-in">
      <div class="text-wrapper-2">CRIAR CONTA</div>

      <!-- Nome Completo -->
      <div class="input-field">
        <div class="div">Nome completo</div>
        <div class="input">
          <input type="text" v-model="nomeCompleto" />
        </div>
        <div v-if="errorMessages.nomeCompleto" class="error-message">
          {{ errorMessages.nomeCompleto }}
        </div>
      </div>

      <!-- Nome de Usuário -->
      <div class="input-field-2">
        <div class="div">Nome de usuário</div>
        <div class="input">
          <input type="text" v-model="username" @input="validateUsername" />
        </div>
        <div v-if="errorMessages.username" class="error-message">
          {{ errorMessages.username }}
        </div>
      </div>

      <!-- Telefone -->
      <div class="input-field-3">
        <div class="div">Telefone celular</div>
        <div class="input">
          <input type="tel" v-model="telefone" @input="validateTelefone" />
        </div>
        <div v-if="errorMessages.telefone" class="error-message">
          {{ errorMessages.telefone }}
        </div>
      </div>

      <!-- Data de Nascimento -->
      <div class="input-field-4">
        <div class="div">Data de nascimento</div>
        <div class="input">
          <input type="date" v-model="dataNascimento" />
        </div>
        <div v-if="errorMessages.dataNascimento" class="error-message">
          {{ errorMessages.dataNascimento }}
        </div>
      </div>

      <!-- CPF -->
      <div class="input-field-5">
        <div class="div">CPF</div>
        <div class="input">
          <input type="text" v-model="cpf" />
        </div>
        <div v-if="errorMessages.cpf" class="error-message">
          {{ errorMessages.cpf }}
        </div>
      </div>

      <!-- Email -->
      <div class="input-field-6">
        <div class="div">Email</div>
        <div class="input">
          <input type="email" v-model="email" @input="validateEmail" />
        </div>
        <div v-if="errorMessages.email" class="error-message">
          {{ errorMessages.email }}
        </div>
      </div>

      <!-- Senha -->
      <div class="input-field-7">
        <div class="div">Crie sua senha</div>
        <div class="input">
          <input type="password" v-model="senha" />
        </div>
        <div v-if="errorMessages.senha" class="error-message">
          {{ errorMessages.senha }}
        </div>
      </div>

      <!-- Confirmar Senha -->
      <div class="input-field-8">
        <div class="div">Confirme sua senha</div>
        <div class="input">
          <input type="password" v-model="confirmarSenha" />
        </div>
        <div v-if="errorMessages.confirmarSenha" class="error-message">
          {{ errorMessages.confirmarSenha }}
        </div>
      </div>

      <!-- Botão de Prosseguir -->
      <button class="button" @click="handleSubmit">
        <div class="text-wrapper">Prosseguir</div>
      </button>

      <!-- Link para Login -->
      <div class="tem-conta-clique">
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
    align-items: center;
    display: flex;
    flex-direction: column;
    height: 100vh;
    justify-content: center;
  }
  
  .form-log-in {
    background-color: #1b1b1bcc;
    border: 0.64px solid;
    border-color: #ff0000;
    border-radius: 3.03px;
    height: 500px;
    position: relative;
    width: 888px;
    margin-top: -7%;
  }
  
  .form-log-in .button {
    all: unset;
    box-sizing: border-box;
    position: absolute;
    top: 70%;
    left: 237px;
    width: 414px;
    padding: 10px;
    background-color: #ff0000;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    font-weight: bold;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 5.16px;
    overflow: hidden;
  }

  .form-log-in .button:hover {
    background-color: black;
  }
  
  
  
  .form-log-in .text-wrapper {
    color: var(--color-text-neutral-default);
    font-family: "Inter-Bold", Helvetica;
    font-size: 12.1px;
    font-weight: 700;
    letter-spacing: 0;
    line-height: 12.1px;
    margin-top: -0.64px;
    position: relative;
    white-space: nowrap;
    width: fit-content;
  }
  
  .form-log-in .div {
    color: #ffffff;
    font-family: "Inter-Regular", Helvetica;
    font-size: 12.1px;
    font-weight: 400;
    left: 0;
    letter-spacing: 0;
    line-height: 17.0px;
    position: absolute;
    top: -1px;
    width: 414px;
  }
  
  .form-log-in .input {
    align-items: center;
    background-color: var(--color-background-neutral-tertiary);
    border: 0.64px solid #ffffff;
    border-radius: 3.03px;
    display: flex;
    height: 28px;
    left: -1px;
    overflow: hidden;
    position: absolute;
    top: 21px;
    width: 416px;
  }
  
  .form-log-in input {
    all: unset;
    font-family: Inter, sans-serif;
    width: 100%;
    font-size: 100%;
    padding: 1px;
    color: #ffffff;
    background-color: transparent;
  }

  .form-log-in .input-field {
    height: 48px;
    left: 15px;
    position: absolute;
    top: 70px;
    width: 414px;
  }
  
  .form-log-in .input-field-2 {
    height: 48px;
    left: 15px;
    position: absolute;
    top: 135px;
    width: 414px;
  }
  
  .form-log-in .input-field-3 {
    height: 48px;
    left: 15px;
    position: absolute;
    top: 199px;
    width: 414px;
  }
  
  .form-log-in .input-field-4 {
    height: 48px;
    left: 15px;
    position: absolute;
    top: 264px;
    width: 414px;
    
  }
  
  .form-log-in .input-field-5 {
    height: 48px;
    left: 444px;
    position: absolute;
    top: 70px;
    width: 414px;
  }
  
  .form-log-in .input-field-6 {
    height: 48px;
    left: 444px;
    position: absolute;
    top: 135px;
    width: 414px;
  }
  
  .form-log-in .input-field-7 {
    height: 48px;
    left: 444px;
    position: absolute;
    top: 199px;
    width: 414px;
  }

  .form-log-in .input-field-8 {
    height: 48px;
    left: 444px;
    position: absolute;
    top: 264px;
    width: 414px;
    
  }
  
  .form-log-in .text-wrapper-2 {
    -webkit-text-stroke: 0.48px #000000;
    color: #ffffff;
    font-family: "Inter-Bold", Helvetica;
    font-size: 30.9px;
    font-weight: 700;
    left: 336px;
    letter-spacing: 0;
    line-height: 30.9px;
    position: absolute;
    text-shadow: 0px 1.93px 2.42px #00000040;
    top: 21px;
    white-space: nowrap;
    width: 215px;
  }
  
  .form-log-in .tem-conta-clique {
    color: #ffffff;
    font-family: "Inter-Regular", Helvetica;
    font-weight: 400;
    left: 340px;
    letter-spacing: 0;
    line-height: 17.0px;
    position: absolute;
    top: 420px;
    font-size: 14px;
}

  .form-log-in .tem-conta-clique a{
  color: #ff0000;
  font-weight: bold;
  text-decoration: none;
}
  
  
  .form-log-in .span {
    color: #f2f2f2;
    font-family: "Inter-Regular", Helvetica;
    font-size: 12.1px;
    font-weight: 400;
    letter-spacing: 0;
    line-height: 17.0px;
  }
  
  .form-log-in .text-wrapper-3 {
    font-family: "Inter-Bold", Helvetica;
    font-weight: 700;
  }

  .error-bottom-right {

  color: #ff0000;
  font-size: 12px;
  margin-left: 30%;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-left: 30%;
}



</style>

  