<template>
  <div class="home-page">
    <div class="carousel position-relative">
      <div class="carousel-arrow left" @click="prevSlide">❮</div>
      <div class="carousel-arrow right" @click="nextSlide">❯</div>
      <div class="carousel-wrapper" :style="carouselStyle">
        <img
          v-for="(slide, index) in slides"
          :key="index"
          :src="slide.image"
          :alt="'Slide ' + (index + 1)"
          class="banner-image img-fluid"
        />
      </div>
    </div>

    <div class="central-box">
      <h1 class="title">Destaques</h1>

      <section class="product-highlight">
        <div v-for="product in products.slice(0, 4)" :key="product.id" class="product-card">
          <router-link :to="`/details/${product.id}`" class="text-decoration-none">
            <div class="card-img-top-container">
              <img :src="getImageUrl(product)" :alt="product.nome" class="card-img-top" />
            </div>
            <div class="card-body">
              <h3 class="card-title">{{ product.nome }}</h3>
            </div>
            <div class="card-footer">
              <p class="card-price">R$ {{ formatPrice(product.valorVenda) }}</p>
            </div>
          </router-link>
        </div>
      </section>

      <section class="quick-filters">
        <div class="filter-container">
          <router-link to="/products?category=Preservativo" class="filter-item">
            <img class="filter-icon" src="@/assets/preservativos.png" alt="Preservativos" />
            <span class="filter-text">Preservativos</span>
          </router-link>
          <router-link to="/products?category=Bdsm" class="filter-item">
            <img class="filter-icon" src="@/assets/bdsm.png" alt="BDSM" />
            <span class="filter-text">BDSM</span>
          </router-link>
          <router-link to="/products?category=Vibradores" class="filter-item">
            <img class="filter-icon" src="@/assets/dildo.png" alt="Vibradores" />
            <span class="filter-text">Vibradores</span>
          </router-link>
          <router-link to="/products?category=Lubrificante" class="filter-item">
            <img class="filter-icon" src="@/assets/lubrificante.png" alt="Lubrificantes" />
            <span class="filter-text">Lubrificantes</span>
          </router-link>
        </div>
      </section>

      <section class="additional-products">
        <div v-for="product in products.slice(2, 12)" :key="product.id" class="product-card">
          <router-link :to="`/details/${product.id}`" class="text-decoration-none">
            <div class="card-img-top-container">
              <img :src="getImageUrl(product)" :alt="product.nome" class="card-img-top" />
            </div>
            <div class="card-body">
              <h3 class="card-title">{{ product.nome }}</h3>
            </div>
            <div class="card-footer">
              <p class="card-price">R$ {{ formatPrice(product.valorVenda) }}</p>
            </div>
          </router-link>
        </div>
      </section>

      <router-link to="/products" class="view-all-button">Ver Todos os Produtos</router-link>
    </div>

    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

    <div v-if="isLoading" class="loading-message">Carregando...</div>
  </div>
</template>

<script>
import banner1 from "@/assets/banner1.png";
import banner2 from "@/assets/banner2.png";
import banner3 from "@/assets/banner3.png";
import { getProducts } from "@/services/api";

export default {
  data() {
    return {
      currentSlide: 0,
      slides: [
        { image: banner1 },
        { image: banner2 },
        { image: banner3 },
      ],
      products: [],
      isLoading: true,
      errorMessage: "",
      carouselInterval: null,
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
    async loadProducts() {
      this.isLoading = true;
      this.errorMessage = "";
      try {
        const response = await getProducts();
        this.products = Array.isArray(response.data) ? response.data : [];
        this.isLoading = false;
      } catch (error) {
        this.errorMessage = "Erro ao carregar os produtos. Tente novamente mais tarde.";
        this.isLoading = false;
        console.error("Erro ao carregar produtos:", error);
      }
    },
    getImageUrl(product) {
      return (
        product?.imagemList?.[0] ||
        "http://207.244.237.78:9921/uploads/images/default.jpeg"
      );
    },
    formatPrice(value) {
      return Number(value).toFixed(2).replace(".", ",");
    },
  },
  mounted() {
    this.carouselInterval = setInterval(() => {
      this.nextSlide();
    }, 5000);
    this.loadProducts();
  },
  beforeUnmount() {
    clearInterval(this.carouselInterval);
  },
};
</script>

<style scoped>
.home-page {
  background-color: #f9f9f9;
  color: #333;
  font-family: "Poppins", sans-serif;
  text-align: center;
  padding-bottom: 40px;
}

.carousel {
  position: relative;
  margin: 0 auto;
  margin-bottom: 20px;
  margin-top: 20px;
  overflow: hidden;
  border-radius: 10px;
  width: 70%;
}

.carousel-wrapper {
  display: flex;
  transition: transform 0.5s ease-in-out;
}

.banner-image {
  margin-top: 10px;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}

.carousel-arrow {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  font-size: 24px;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 12px;
  cursor: pointer;
  z-index: 1;
  color: white;
  border-radius: 50%;
  transition: background-color 0.3s ease;
}

.carousel-arrow:hover {
  background-color: rgba(0, 0, 0, 0.7);
}

.carousel-arrow.left {
  left: 15px;
}

.carousel-arrow.right {
  right: 15px;
}

.central-box {
  background-color: #fff;
  padding: 30px;
  margin: 0 auto;
  width: 90%;
  max-width: 1200px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.title {
  font-size: 28px;
  margin: 0 0 20px;
  text-align: left;
  font-weight: 600;
  color: #2c3e50;
  text-transform: uppercase;
  letter-spacing: 1.5px;
  border-left: 4px solid #e91e63;
  padding-left: 10px;
}

.product-highlight,
.additional-products {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  justify-content: center;
  margin-bottom: 40px;
}

.product-card {
  background: #f5f5f5;
  width: 100%;
  max-width: 250px;
  height: 320px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  position: relative;
}

.product-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
}

.card-img-top-container {
  width: 100%;
  height: 160px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  background-color: #fff;
}

.card-img-top {
  object-fit: contain;
  width: 100%;
  height: 100%;
  transition: transform 0.3s ease;
}

.product-card:hover .card-img-top {
  transform: scale(1.05);
}

.card-body {
  padding: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.card-title {
  font-size: 1.1rem;
  font-weight: 500;
  color: #2c3e50;
  margin: 0;
  white-space: normal;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  line-clamp: 2;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.card-footer {
  background-color: #f5f5f5;
  padding: 10px;
  border-top: 1px solid #eee;
  text-align: center;
  position: absolute;
  bottom: 0;
  width: 100%;
}

.card-price {
  font-size: 1.2rem;
  font-weight: 600;
  color: #e91e1e;
  margin: 0;
}

.quick-filters .filter-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
  gap: 20px;
  justify-content: center;
  margin: 30px 0;
}

.filter-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 180px;
  background: linear-gradient(135deg, #f50a0a, #7c0a0a);
  border-radius: 12px;
  color: white;
  font-weight: 500;
  text-align: center;
  padding: 20px;
  text-decoration: none;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.filter-item:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.filter-icon {
  width: 60px;
  height: 60px;
  margin-bottom: 15px;
  filter: drop-shadow(0 2px 4px rgba(0, 0, 0, 0.2));
}

.filter-text {
  font-size: 1.1rem;
}

.view-all-button {
  background-color: #ff0000;
  color: white;
  padding: 12px 24px;
  border-radius: 8px;
  margin-top: 30px;
  text-decoration: none;
  display: inline-block;
  font-weight: 500;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.view-all-button:hover {
  background-color: #c2185b;
  transform: translateY(-2px);
}

.error-message {
  color: #e91e63;
  margin-top: 20px;
  font-size: 1.1rem;
}

.loading-message {
  font-size: 1.2rem;
  color: #e91e63;
  margin-top: 20px;
}

@media (max-width: 768px) {
  .carousel {
    width: 95%;
  }

  .banner-image {
    height: 300px;
  }

  .central-box {
    width: 95%;
    padding: 20px;
  }

  .product-card {
    max-width: 180px;
    height: 280px;
  }

  .card-img-top-container {
    height: 140px;
  }

  .card-title {
    font-size: 1rem;
  }

  .filter-item {
    width: 100%;
    height: 160px;
  }

  .filter-icon {
    width: 50px;
    height: 50px;
  }
}

@media (max-width: 576px) {
  .title {
    font-size: 24px;
  }

  .product-highlight,
  .additional-products {
    grid-template-columns: repeat(auto-fit, minmax(160px, 1fr));
  }

  .product-card {
    max-width: 160px;
    height: 260px;
  }

  .card-img-top-container {
    height: 120px;
  }
}
</style>