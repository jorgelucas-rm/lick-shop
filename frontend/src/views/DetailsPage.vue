<template>
  <div class="product-page">
    <div v-if="product" class="product-container">
      <div class="product-image">
        <div class="zoom-container">
          <img
            :src="product.imagemList[0]"
            alt="Imagem do Produto"
            ref="productImage"
            @mousemove="zoomImage"
            @mouseleave="resetZoom"
          />
        </div>
      </div>

      <div class="product-details">
        <h1>{{ product.nome }}</h1>
        <p class="description">{{ product.descricaoDetalhada }}</p>
        <p class="price">Preço: <span>R$ {{ product.valorVenda.toFixed(2) }}</span></p>

        <div class="quantity-selector">
          <label for="quantity">Quantidade:</label>
          <input
            id="quantity"
            type="number"
            v-model.number="quantity"
            min="1"
          />
        </div>

        <div class="action-buttons">
          <button class="buy-button" @click="addToCart">Adicionar ao Carrinho</button>
        </div>
      </div>
    </div>

    <div v-else class="loading">
      <p>Carregando...</p>
    </div>

    <div v-if="showModal" class="modal-overlay">
      <div class="modal-content">
        <h2>Item Adicionado ao Carrinho!</h2>
        <p>Adicionado {{ quantity }}x {{ product?.nome }} ao carrinho.</p>
        <div class="modal-buttons">
          <button class="modal-button cart-button" @click="goToCart">Ir para o Carrinho</button>
          <button class="modal-button continue-button" @click="continueShopping">Continuar Comprando</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from "@/services/api";

export default {
  data() {
    return {
      product: null,
      quantity: 1,
      showModal: false
    };
  },
  async created() {
    const { id } = this.$route.params;
    try {
      const response = await api.get(`/api/v1/produto/${id}`);
      this.product = response.data;
      if (!this.product.imagemList || this.product.imagemList.length === 0) {
        this.product.imagemList = ['caminho/para/imagem/default.jpg'];
      }
    } catch (error) {
      console.error("Erro ao carregar o produto:", error);
    }
  },
  methods: {
    addToCart() {
      this.showModal = true;
    },
    goToCart() {
      this.showModal = false;
      this.$router.push('/cart');
    },
    continueShopping() {
      this.showModal = false;
      this.$router.push('/');
    },
    zoomImage(event) {
      const image = this.$refs.productImage;
      const { offsetX, offsetY } = event;
      const { offsetWidth, offsetHeight } = image;
      const x = (offsetX / offsetWidth) * 100;
      const y = (offsetY / offsetHeight) * 100;
      image.style.transformOrigin = `${x}% ${y}%`;
      image.style.transform = "scale(2)";
    },
    resetZoom() {
      const image = this.$refs.productImage;
      image.style.transform = "scale(1)";
      image.style.transformOrigin = "center center";
    }
  }
};
</script>

<style scoped>
.product-page {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  background: #f2f2f2;
  font-family: 'Roboto', sans-serif;
}

.product-container {
  display: flex;
  flex-wrap: wrap;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  max-width: 1000px;
  width: 100%;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  gap: 20px;
}

.product-image {
  flex: 1;
  min-width: 300px;
  max-width: 450px;
}

.zoom-container {
  overflow: hidden;
  border-radius: 10px;
  cursor: zoom-in;
}

.zoom-container img {
  width: 100%;
  transition: transform 0.3s ease;
  object-fit: contain;
}

.product-details {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.product-details h1 {
  font-size: 2rem;
  color: #222;
  margin-bottom: 10px;
}

.description {
  font-size: 1rem;
  color: #555;
  line-height: 1.6;
  margin-bottom: 15px;
}

.price {
  font-size: 1.5rem;
  color: #e63946;
  margin: 15px 0;
}

.price span {
  font-weight: bold;
}

.quantity-selector {
  margin: 15px 0;
  display: flex;
  align-items: center;
  gap: 10px;
}

.quantity-selector label {
  font-weight: 500;
  color: #333;
}

.quantity-selector input {
  width: 60px;
  padding: 5px;
  font-size: 1rem;
}

.action-buttons {
  margin-top: 20px;
}

.buy-button {
  width: 100%;
  padding: 15px;
  background: #000;
  color: #fff;
  border: none;
  border-radius: 8px;
  font-weight: bold;
  font-size: 1rem;
  cursor: pointer;
  transition: background 0.3s ease;
}

.buy-button:hover {
  background: #218838;
}

.loading {
  text-align: center;
  font-size: 1.2rem;
  color: #555;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  max-width: 400px;
  width: 90%;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
}

.modal-content h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
  color: #222;
}

.modal-content p {
  font-size: 1rem;
  color: #555;
  margin-bottom: 20px;
}

.modal-buttons {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}

.modal-button {
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  width: 48%;
}

.cart-button {
  background: #218838;
  color: #fff;
}

.cart-button:hover {
  background: #1e7e34;
}

.continue-button {
  background: #555;
  color: #fff;
}

.continue-button:hover {
  background: #444;
}

</style>
