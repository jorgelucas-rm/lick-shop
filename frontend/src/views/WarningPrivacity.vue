<template>
  <div style="background-color: #f9f9f9; min-height: 100vh; padding: 20px;">
    <div class="container mt-4">
      <div class="text-center mb-4">
        <h1>Aviso de Privacidade</h1>
      </div>
      <div class="content">
        <p>
          Sua privacidade é nossa prioridade. Aqui estão as diretrizes sobre como usamos suas informações:
        </p>
        <ul>
          <li>Coletamos dados para melhorar sua experiência no site.</li>
          <li>Não compartilhamos seus dados pessoais sem o seu consentimento.</li>
          <li>Você pode solicitar a exclusão ou modificação de suas informações a qualquer momento.</li>
        </ul>
        <p>
          Para mais detalhes, consulte nossa <a href="/politica-de-privacidade">Política de Privacidade Completa</a>.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PrivacyNotice",
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
