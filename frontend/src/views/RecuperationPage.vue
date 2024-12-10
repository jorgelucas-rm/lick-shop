<template>
  <div class="forgot-my-password">
    <div class="background-image"></div>
    <div class="loginpage d-flex justify-content-center align-items-center">
      <div class="form-log-in p-4 rounded shadow-lg">
        <div class="text-wrapper-2 text-center text-white mb-4">Esqueci minha senha</div>
        <p class="tem-conta-clique text-white text-center mb-4">
          Preencha abaixo seu endereço de e-mail para receber as instruções
          necessárias e criar uma nova senha.
        </p>

        <div class="input-field mb-3">
          <div class="text-wrapper text-white">Email</div>
          <div class="input">
            <input type="email" placeholder="Digite seu email" class="form-control" />
          </div>
        </div>

        <div class="button-group text-center">
          <button class="button mt-4">
            <div class="div">Enviar</div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
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

.form-log-in {
  width: 100%;
  max-width: 450px;
  background-color: rgba(27, 27, 27, 0.8);
  border: 1px solid #ff0000;
  border-radius: 5px;
  box-sizing: border-box;
  z-index: 1;
  padding: 20px;
}

.text-wrapper-2 {
  font-family: 'Inter-Bold', Helvetica, sans-serif;
  font-size: 30px;
  font-weight: 700;
  text-shadow: 0px 1.93px 2.42px #00000040;
}

.tem-conta-clique {
  font-family: 'Inter-Regular', Helvetica, sans-serif;
  font-size: 12px;
}

.input-field {
  margin: 20px 0;
}

.text-wrapper {
  font-family: 'Inter-Regular', Helvetica, sans-serif;
  font-size: 12px;
}

.input {
  background-color: rgba(255, 255, 255, 0.2);
  border: 1px solid #ffffff;
  border-radius: 5px;
  padding: 8px 12px;
}

.input input {
  all: unset;
  color: white;
  font-family: 'Inter-Regular', Helvetica, sans-serif;
  font-size: 12px;
  width: 100%;
}

.button-group {
  display: flex;
  justify-content: center;
}

.button {
  width: 100%;
  padding: 10px 20px;
  background-color: #ff0000;
  border-radius: 5px;
  font-family: 'Inter-Regular', Helvetica, sans-serif;
  font-size: 13px;
  cursor: pointer;
  text-align: center;
  color: white;
}

.button:hover {
  background-color: #cc0000;
}

@media (max-width: 576px) {
  .form-log-in {
    width: 90%;
    padding: 15px;
  }

  .text-wrapper-2 {
    font-size: 24px;
  }

  .input-field input {
    font-size: 14px;
  }

  .button {
    font-size: 12px;
  }
}
</style>
