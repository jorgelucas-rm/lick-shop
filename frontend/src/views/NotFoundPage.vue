<template>
  <div class="not-found-container d-flex flex-column justify-content-center align-items-center text-center">
    <h1 class="display-1">404</h1>
    <h2 class="h3">Página não encontrada</h2>
    <p class="lead max-width-600">Ops! Parece que você se perdeu. A página que está tentando acessar não existe ou foi movida.</p>
    <router-link to="/home" class="back-home-button btn btn-danger">Voltar para a Página Inicial</router-link>
  </div>
</template>

<script>
export default {
  name: 'NotFoundPage',
};
</script>

<style scoped>
.not-found-container {
  background: linear-gradient(145deg, #ff7eb3, #000000);
  color: white;
  font-family: 'Arial', sans-serif;
  height: 100vh;
}

h1 {
  font-size: 8rem;
  margin: 0;
  font-weight: bold;
}

h2 {
  font-size: 2rem;
  margin: 0.5rem 0;
}

p {
  font-size: 1.2rem;
  margin: 1rem 0;
  line-height: 1.6;
  max-width: 600px;
}

.back-home-button {
  margin-top: 20px;
  text-decoration: none;
  font-size: 1.2rem;
  color: white;
  background: #ff467e;
  padding: 10px 20px;
  border-radius: 25px;
  transition: all 0.3s ease;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.back-home-button:hover {
  background: #ff285b;
  transform: scale(1.05);
  box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3);
}

/* Responsividade */
@media (max-width: 768px) {
  h1 {
    font-size: 6rem; /* Ajuste o tamanho da fonte no celular */
  }
  h2 {
    font-size: 1.5rem; /* Ajuste para telas menores */
  }
  p {
    font-size: 1rem; /* Ajuste para telas menores */
  }
}

@media (max-width: 576px) {
  h1 {
    font-size: 5rem; /* Ajuste para telas pequenas */
  }
  h2 {
    font-size: 1.25rem; /* Ajuste para telas pequenas */
  }
  .back-home-button {
    font-size: 1rem;
    padding: 8px 16px;
  }
}
</style>
