<template>
  <div style="background-color: #f0f8ff; min-height: 100vh; padding: 20px;">
    <div class="container mt-4">
      <div class="text-center mb-4">
        <h1>O que é Local Storage?</h1>
      </div>
      <div class="content">
        <p>
          O Local Storage é uma tecnologia de armazenamento local do navegador que permite salvar dados de forma persistente no dispositivo do usuário. Ele é útil para guardar informações que não precisam ser enviadas ao servidor.
        </p>
        <h2>Por que usar o Local Storage?</h2>
        <ul>
          <li>Armazenar preferências do usuário, como temas ou configurações.</li>
          <li>Guardar dados temporários sem depender de conexões com o servidor.</li>
          <li>Evitar carregar informações repetidasmente (como dados de formulário).</li>
        </ul>
        <h2>Características do Local Storage</h2>
        <ul>
          <li>Os dados persistem até serem explicitamente removidos.</li>
          <li>Armazena até 5-10 MB de dados, dependendo do navegador.</li>
          <li>Os dados são acessíveis apenas pelo JavaScript do mesmo domínio que os criou.</li>
        </ul>
        <h2>Como usá-lo?</h2>
        <p>
          Aqui está um exemplo básico de como usar o Local Storage:
        </p>
        <pre>
          <code>
            // Salvar um dado
            localStorage.setItem("chave", "valor");
            
            // Recuperar um dado
            const valor = localStorage.getItem("chave");
            
            // Remover um dado
            localStorage.removeItem("chave");
          </code>
        </pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LocalStorageInfo",
};
</script>

<style scoped>
h1 {
  color: #2c3e50;
  font-size: 2.5rem;
  margin-bottom: 1rem;
}

h2 {
  color: #333;
  margin-top: 1.5rem;
}

p, ul {
  font-size: 1.1rem;
  line-height: 1.6;
  color: #555;
}

pre {
  background: #f8f9fa;
  padding: 15px;
  border-radius: 8px;
  font-size: 1rem;
  overflow-x: auto;
}

ul {
  margin-left: 1.5rem;
  list-style-type: disc;
}

.content {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
</style>
