<template>
  <div class="forgot-my-password">
    <div class="background-image"></div>
    <div class="loginpage">
      <div class="form-log-in">
        <div class="text-wrapper-2">Esqueci minha senha</div>
        <p class="tem-conta-clique">
          Preencha abaixo seu endereço de e-mail para receber as instruções
          necessárias e criar uma nova senha.
        </p>

        <div class="input-field">
          <div class="text-wrapper">Email</div>
          <div class="input">
            <input type="email" placeholder="Digite seu email" />
          </div>
        </div>

        <div class="button-group">
          <button class="button">
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
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: center;
}

.form-log-in {
  background-color: #1b1b1bcc;
  border: 0.64px solid #ff0000;
  border-radius: 3.03px;
  height: 500px;
  width: 888px;
  position: relative;
  margin-top: -7%;
  padding: 20px;
}

.text-wrapper-2 {
  color: #ffffff;
  font-family: "Inter-Bold", Helvetica, sans-serif;
  font-size: 30.9px;
  font-weight: 700;
  text-shadow: 0px 1.93px 2.42px #00000040;
  text-align: center;
  margin-bottom: 20px;
}

.tem-conta-clique {
  color: #ffffff;
  font-family: "Inter-Regular", Helvetica, sans-serif;
  font-size: 12.1px;
  margin-bottom: 20px;
  text-align: center;
}

.input-field {
  margin: 20px 0;
}

.text-wrapper {
  color: #ffffff;
  font-family: "Inter-Regular", Helvetica, sans-serif;
  font-size: 12.1px;
  margin-bottom: 5px;
}

.input {
  display: flex;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.2);
  border: 0.64px solid #ffffff;
  border-radius: 3.03px;
  padding: 8px 12px;
}

.input input {
  all: unset;
  color: #ffffff;
  font-family: "Inter-Regular", Helvetica, sans-serif;
  font-size: 12px;
  width: 100%;
}

.button-group {
  display: flex;
  justify-content: center;
}

.button {
  background-color: #ffffff;
  border-radius: 3.03px;
  padding: 10px 20px;
  font-family: "Inter-Regular", Helvetica, sans-serif;
  font-size: 13.3px;
  cursor: pointer;
  text-align: center;
}

.div {
  color: #1b1b1b;
  font-family: "Inter-Regular", Helvetica, sans-serif;
}
</style>
