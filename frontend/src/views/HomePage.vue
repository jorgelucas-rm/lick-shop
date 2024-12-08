<template> 
  <div class="home-page">
    <!-- Carrossel -->
    <div class="carousel">
      <div class="carousel-arrow left" @click="prevSlide">&#10094;</div>
      <div class="carousel-arrow right" @click="nextSlide">&#10095;</div>
      <div class="carousel-wrapper" :style="carouselStyle">
        <img
          v-for="(slide, index) in slides"
          :key="index"
          :src="slide"
          :alt="'Slide ' + (index + 1)"
          class="banner-image"
        />
      </div>
    </div>

    <!-- Conteúdo Centralizado -->
    <div class="central-box">
      <!-- Título -->
      <h1 class="title">DESTAQUES QUENTES</h1>

      <!-- Cards de Produtos Principais -->
      <section class="product-highlight">
        <div v-for="product in products.slice(0, 4)" :key="product.id" class="product-card">
          <router-link :to="`/details/${product.id}`">
            <img :src="getImageUrl(product)" :alt="product.name" class="product-image" />
            <h3 class="product-name">{{ product.name }}</h3>
            <p class="product-price">R$ {{ product.price }}</p>
          </router-link>
        </div>
      </section>

      <!-- Filtros Rápidos -->
      <section class="quick-filters">
        <div class="filter-container">
          <router-link to="/products/vestimentas" class="filter-item fundo-vestimentas">
            <img class="filter-icon" src="@/assets/vestimentas.png" alt="Vestimentas" />
            <span class="filter-text">Vestimentas</span>
          </router-link>
          <router-link to="/products/bdsm" class="filter-item fundo-bdsm">
            <img class="filter-icon" src="@/assets/bdsm.png" alt="BDSM" />
            <span class="filter-text">BDSM</span>
          </router-link>
          <router-link to="/products/dildos" class="filter-item fundo-dildo">
            <img class="filter-icon" src="@/assets/dildo.png" alt="Dildos" />
            <span class="filter-text">Vibradores</span>
          </router-link>
          <router-link to="/products/lubrificantes" class="filter-item fundo-lubrificantes">
            <img class="filter-icon" src="@/assets/lubrificante.png" alt="Lubrificantes" />
            <span class="filter-text">Lubrificantes</span>
          </router-link>

        </div>
      </section>

      <!-- Exibição de Mais Produtos -->
      <section class="additional-products">
        <div v-for="product in products.slice(4, 16)" :key="product.id" class="product-card">
          <router-link :to="`/details/${product.id}`">
            <img :src="getImageUrl(product)" :alt="product.name" class="product-image" />
            <h3 class="product-name">{{ product.name }}</h3>
            <p class="product-price">R$ {{ product.price }}</p>
          </router-link>
        </div>
      </section>

      <!-- Botão para Ver Todos os Produtos -->
      <router-link to="/products" class="view-all-button">Ver Todos os Produtos</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentSlide: 0,
      slides: [
        "@/GIT/lickshop/src/assets/banner1.jpg",
        "https://via.placeholder.com/1360x400?text=Promoção+2",
        "https://via.placeholder.com/1360x400?text=Promoção+3",
      ],
      products: [], // Deve ser carregado via API
    };
  },
  computed: {
    carouselStyle() {
      return {
        transform: `translateX(-${this.currentSlide * 100}%)`,
      };
    },
  },
  methods: {
    prevSlide() {
      this.currentSlide = this.currentSlide === 0 ? this.slides.length - 1 : this.currentSlide - 1;
    },
    nextSlide() {
      this.currentSlide = (this.currentSlide + 1) % this.slides.length;
    },
    getImageUrl(product) {
      return product?.image || "https://via.placeholder.com/150";
    },
  },
};
</script>

<style scoped>
.home-page {
  background-color: black;
  color: white;
  font-family: "Roboto", sans-serif;
  text-align: center;
}

.carousel {
  position: relative;
  height: 400px;
  margin-bottom: 20px;
  overflow: hidden;
}

.carousel-wrapper {
  display: flex;
  transition: transform 0.5s ease-in-out;
}

.banner-image {
  width: 100%;
  height: 400px;
  object-fit: cover;
}

.carousel-arrow {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  font-size: 30px;
  background-color: rgba(0, 0, 0, 0.6);
  padding: 10px;
  cursor: pointer;
  z-index: 1;
  color: white;
}

.carousel-arrow.left {
  left: 10px;
}

.carousel-arrow.right {
  right: 10px;
}

.central-box {
  background-color: #ffe4e1;
  padding: 20px;
  margin: 0 auto;
  width: 80%;
  height: 1000px;
  margin-bottom: 1%
}

.title {
  font-size: 24px;
  margin-top: 0%;
  padding: 0%;
  text-align: start;
  margin-bottom: 10px;
  border-radius: 5px;
  padding: 10px;
  font-weight: bold;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
  text-transform: uppercase;
  letter-spacing: 2px;
  word-spacing: 5px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);

  background-color: #b00003;
}

.subtitle {
  font-size: 18px;
  margin-bottom: 10px;
  color: #d30000;
}


.product-highlight,
.additional-products {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  margin: 20px 0;
}

.product-card {
  background: white;
  color: black;
  width: 220px;
  padding: 10px;
  border-radius: 5px;
  text-align: center;
}

.product-image {
  width: 100%;
  border-radius: 5px;
}

.quick-filters .filter-container {
  display: flex;
  justify-content: space-around;
  margin: 20px 0;
}

.filter-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 150px;
  height: 150px;
  background: #d30000;
  border-radius: 10px;
  color: white;
  font-weight: bold;
  text-align: center;
}

.fundo-bdsm {
  background: #b00003;
}

.fundo-dildo {
  background: #720002;
}

.fundo-lubrificantes {
  background: #420000;
}

.filter-icon {
  width: 80px;
  height: 80px;
  margin-bottom: 10px;
}

.view-all-button {
  display: block;
  width: 200px;
  text-align: center;
  margin: 20px auto;
  padding: 10px 20px;
  background-color: white;
  color: black;
  text-decoration: none;
  border-radius: 5px;
}

.view-all-button:hover {
  background-color: #ffe4e1;
  color: white;
}
</style>
