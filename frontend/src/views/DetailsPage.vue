<template> 
  <div class="product-page">
    <div v-if="product" class="product-container">
      <!-- Imagem do Produto -->
      <div class="product-image">
        <img :src="product.imagemList[0]" alt="Imagem do Produto" />
      </div>

      <!-- Detalhes do Produto -->
      <div class="product-details">
        <h1>{{ product.nome }}</h1>
        <p class="description">{{ product.descricaoDetalhada }}</p>
        <p class="price">Preço: <span>R$ {{ product.valorVenda.toFixed(2) }}</span></p>

        <!-- Botões -->
        <div class="action-buttons">
          <button class="buy-button">Adicionar ao Carrinho</button>
          <button class="wishlist-button">Adicionar à Lista de Desejos</button>
        </div>
      </div>
    </div>

    <!-- Mensagem de Carregamento -->
    <div v-else class="loading">
      <p>Carregando...</p>
    </div>
  </div>
</template>

<script>
import api from "@/services/api";

export default {
  data() {
    return {
      product: null,
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
};
</script>

<style scoped>
/* Container Principal */
.product-page {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  background: #f9f9f9;
  font-family: 'Roboto', sans-serif;
}

/* Estilo do Card de Produto */
.product-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  max-width: 1200px;
  width: 100%;
}

/* Estilo da Imagem */
.product-image img {
  max-width: 100%;
  border-radius: 10px;
  object-fit: contain;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Estilo dos Detalhes */
.product-details {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.product-details h1 {
  font-size: 2rem;
  color: #333;
}

.product-details .description {
  font-size: 1rem;
  color: #666;
  line-height: 1.6;
  margin: 15px 0;
}

.product-details .price {
  font-size: 1.5rem;
  color: #e63946;
  margin: 20px 0;
}

.product-details .price span {
  font-weight: bold;
}

/* Estilo dos Botões */
.action-buttons {
  display: flex;
  gap: 10px;
}

.buy-button,
.wishlist-button {
  flex: 1;
  padding: 12px;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: background 0.3s ease;
}

.buy-button {
  background: #28a745;
  color: #fff;
}

.buy-button:hover {
  background: #218838;
}

.wishlist-button {
  background: #ffc107;
  color: #fff;
}

.wishlist-button:hover {
  background: #e0a800;
}

/* Estilo de Carregamento */
.loading {
  text-align: center;
  font-size: 1.2rem;
  color: #666;
}
</style>
