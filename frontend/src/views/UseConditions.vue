<template>
    <div style="background-color: #f9f9f9; min-height: 100vh; padding: 20px;">
      <div class="container mt-4">
        <div class="text-center mb-4">
          <h1>Condições de Uso</h1>
        </div>
        <div class="content">
          <p>
            Ao acessar este site, você concorda com os seguintes termos:
          </p>
          <ul>
            <li>O conteúdo do site é fornecido apenas para uso informativo.</li>
            <li>É proibido copiar, reproduzir ou redistribuir qualquer material sem autorização.</li>
            <li>Violações podem resultar em sanções legais.</li>
          </ul>
          <p>
            Para dúvidas, entre em contato através da nossa <a href="/contato">página de contato</a>.
          </p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "TermsOfUse",
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
  