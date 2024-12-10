<template>
    <div style="background-color: #f9f9f9; min-height: 100vh; padding: 20px;">
      <div class="container mt-4">
        <div class="text-center mb-4">
          <h1>Anúncios Baseados em Interesses</h1>
        </div>
        <div class="content">
          <p>
            Utilizamos tecnologias como cookies para oferecer anúncios personalizados. Isso nos ajuda a mostrar conteúdo mais relevante para você.
          </p>
          <ul>
            <li>Os anúncios são baseados em suas interações no site.</li>
            <li>Você pode gerenciar suas preferências de anúncios nas configurações do navegador.</li>
            <li>Para mais controle, acesse a <a href="https://optout.aboutads.info/" target="_blank">página de opt-out de anúncios</a>.</li>
          </ul>
          <p>
            Caso prefira não receber anúncios personalizados, você ainda verá anúncios genéricos.
          </p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "InterestBasedAds",
  };
  </script>
  
  <style scoped>
  h1 {
    color: #2c3e50;
    font-size: 2.5rem;
    margin-bottom: 1rem;
  }
  
  p, ul {
    font-size: 1.1rem;
    line-height: 1.6;
    color: #555;
  }
  
  a {
    color: #3498db;
    text-decoration: none;
  }
  
  a:hover {
    text-decoration: underline;
  }
  
  .content {
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  }
  </style>
  