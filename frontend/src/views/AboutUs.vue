<template>
    <div style="background-color: #ffe4e1; min-height: 100vh; padding: 20px;">
      <div class="container mt-4">
        <div class="text-center mb-4">
          <h1>Sobre Nós</h1>
          <p class="lead">
            A Lickshop é um projeto universitário que oferece uma variedade de produtos para apimentar a sua vida.
          </p>
          <h2 class="mt-4">Conheça nossa equipe</h2>
        </div>
        <div class="row g-4 justify-content-center">
          <div class="col-12 col-md-6 col-lg-4" v-for="(person, index) in team" :key="index">
            <div class="card h-100 shadow-sm text-center">
              <img :src="person.image" class="card-img-top mx-auto rounded-circle mt-4" :alt="person.name" />
              <div class="card-body">
                <h5 class="card-title mb-3">{{ person.name }}</h5>
                <p class="card-text">{{ person.role }}</p>
              </div>
              <div class="card-footer bg-transparent">
                <a :href="person.github" target="_blank" class="btn btn-primary w-100">
                  Acessar GitHub
                </a>
              </div>
            </div>
          </div>
        </div>
        <div class="text-center mt-4 mb-4"> Agradecimento especial ao professor Nator Junior por ter disponibilizado o server de hospedagem e por ter ajudado no desenvolvimento do site.</div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "AboutUs",
    data() {
      return {
        team: [
          {
            name: "Ana Valéria Rodrigues",
            role: "Desenvolvedora Back-end",
            image: require("@/assets/fotovaleria.jpg"),
            github: "https://github.com/BomDiaSol",
          },
          {
            name: "Carlos Eduardo Cardoso",
            role: "Gerente de Produtos",
            image: require("@/assets/fotocadu.jpg"),
            github: "https://github.com/EduCode98",
          },
          {
            name: "Jorge Lucas Rodrigues Martins",
            role: "Desenvolvedor Back-end",
            image: require("@/assets/fotojorge.jpg"),
            github: "https://github.com/jorgelucas-rm",
          },
          {
            name: "Rafael Regis Cavalcante Ramos Costa",
            role: "Desenvolvedor Web",
            image: require("@/assets/fotorafael.jpg"),
            github: "https://github.com/Regis-Rafael",
          },
        ],
      };
    },
  };
  </script>
  
  <style scoped>
  /* Estilo dos cards */
  .card {
    border: none;
    border-radius: 12px;
    background-color: #ffffff;
    padding: 20px 15px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  
  .card:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  }
  
  /* Imagem circular */
  .card-img-top {
    width: 120px;
    height: 120px;
    object-fit: cover;
    border: 4px solid #ffe4e1;
  }
  
  /* Título */
  .card-title {
    font-size: 1.2rem;
    font-weight: bold;
    color: #333;
  }
  
  /* Papel do integrante */
  .card-text {
    font-size: 1rem;
    color: #555;
  }
  
  /* Botão */
  .btn {
    border-radius: 8px;
    font-weight: bold;
    transition: background-color 0.3s ease;
  }
  
  .btn-primary {
    background-color: #007bff;
    border: none;
  }
  
  .btn-primary:hover {
    background-color: #0056b3;
  }
  </style>
  