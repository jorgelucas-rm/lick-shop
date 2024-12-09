<template>
    <div class="contact-page">
      <div class="background-image"></div>
      <div class="form-contact container py-5">
        <div class="text-center text-wrapper mb-4">Fale Conosco</div>
  
        <div class="row">
          <!-- Informações de Contato -->
          <div class="col-12 col-md-6 mb-4">
            <div class="content">
              <h2>Formas de Contato</h2>
              <ul class="contact-list">
                <li><strong>E-mail:</strong> <a href="mailto:contato@exemplo.com">feedback@lickshop.com</a></li>
                <li><strong>Telefone:</strong> +55 (85) 96969-0000</li>
                <li><strong>WhatsApp:</strong> +55 (85) 90000-6969</li>
              </ul>
            </div>
          </div>
  
          <!-- Formulário de Contato -->
          <div class="col-12 col-md-6 mb-4">
            <div class="content">
              <h2>Envie uma Mensagem</h2>
              <form @submit.prevent="handleSubmit">
                <div class="mb-3">
                  <label for="name" class="form-label">Nome</label>
                  <input type="text" id="name" class="form-control custom-input" v-model="form.name" required />
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">E-mail</label>
                  <input type="email" id="email" class="form-control custom-input" v-model="form.email" required />
                </div>
                <div class="mb-3">
                  <label for="message" class="form-label">Mensagem</label>
                  <textarea id="message" class="form-control custom-input" v-model="form.message" rows="5" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary custom-button w-100 py-2">Enviar</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "ContactPage",
    data() {
      return {
        form: {
          name: "",
          email: "",
          message: "",
        },
      };
    },
    methods: {
      handleSubmit() {
        alert(`Obrigado pela sua mensagem, ${this.form.name}! Entraremos em contato em breve.`);
        this.form.name = "";
        this.form.email = "";
        this.form.message = "";
      },
    },
  };
  </script>
  
  <style scoped>
  /* Fundo da página */
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
  
  .contact-page {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    padding: 20px;
  }
  
  .form-contact {
    background-color: #1b1b1bcc;
    color: black;
    border-radius: 10px;
    position: relative;
    max-width: 900px;
    width: 100%;
    padding: 40px;
    box-sizing: border-box;
    overflow-y: auto;
  }
  
  .text-wrapper {
    font-family: "Roboto", sans-serif;
    font-size: 28px;
    font-weight: 500;
    line-height: 34px;
    color: white;
    margin-bottom: 2rem;
  }
  
  /* Formulário */
  .content {
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .form-label {
    font-weight: bold;
    color: #333;
  }
  
  .custom-input {
    background: transparent;
    border: 1px solid #ccc;
    color: #333;
    padding: 10px;
    font-size: 14px;
    border-radius: 5px;
    width: 100%;
    margin-bottom: 1.5rem;
  }
  
  .custom-input:focus {
    outline: none;
    border-color: #ff0000;
  }
  
  textarea.custom-input {
    resize: vertical;
  }
  
  .btn-primary {
    background-color: #ff0000;
    border-color: #ff0000;
    border-radius: 5px;
    color: white;
    font-size: 16px;
    font-weight: bold;
    transition: background-color 0.3s, transform 0.3s;
  }
  
  .btn-primary:hover {
    background-color: #cc0000;
    transform: scale(1.05);
  }
  
  </style>
  