<template>
    <div class="not-found-container">
      <h1>404</h1>
      <h2>Página não encontrada</h2>
      <p>Ops! Parece que você se perdeu. A página que está tentando acessar não existe ou foi movida.</p>
      <router-link to="/home" class="back-home-button">Voltar para a Página Inicial</router-link>
    </div>
  </template>
  
  <script>
  export default {
    name: 'NotFoundPage',
  };
  </script>
  
  <style scoped>
  .not-found-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    text-align: center;
    background: linear-gradient(145deg, #ff7eb3, #000000);
    color: white;
    font-family: 'Arial', sans-serif;
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
    max-width: 600px;
    line-height: 1.6;
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
  </style>
  