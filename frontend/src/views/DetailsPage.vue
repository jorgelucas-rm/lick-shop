<template>
  <div class="product-page">
    <div v-if="product" class="product-container">
      <div class="product-image">
        <img :src="product.imagemList[0]" alt="Product Image" />
      </div>
      <div class="product-details">
        <h1>{{ product.nome }}</h1>
        <p class="description">{{ product.descricaoDetalhada }}</p>
        <p class="price">Preço: <span>R$ {{ product.valorVenda.toFixed(2) }}</span></p>
        <button class="buy-button">Adicionar ao Carrinho</button>
        <button class="wishlist-button">Adicionar à Lista de Desejos</button>
      </div>
    </div>
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

    // Verifica se existe uma lista de imagens
    if (!this.product.imagemList || this.product.imagemList.length === 0) {
      this.product.imagemList = ['caminho/para/imagem/default.jpg']; // imagem padrão
    }
  } catch (error) {
    console.error("Erro ao carregar o produto:", error);
  }
}
,
};
</script>

<style scoped>
/* Container Principal */
.product-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #f5f5f5;
  padding: 20px;
  font-family: 'Arial', sans-serif;
}

/* Estilo do Card de Produto */
.product-container {
  display: flex;
  flex-direction: row;
  gap: 30px;
  background: white;
  border-radius: 10px;
  padding: 20px;
  max-width: 1200px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
}

/* Estilo da Imagem */
.product-image img {
  max-width: 400px;
  width: 100%;
  border-radius: 10px;
  object-fit: contain;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

/* Estilo dos Detalhes */
.product-details {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  max-width: 600px;
}

.product-details h1 {
  font-size: 2rem;
  color: #333;
  margin-bottom: 10px;
}

.product-details .description {
  font-size: 1rem;
  color: #555;
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
.buy-button,
.wishlist-button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 25px;
  text-align: center;
  transition: all 0.3s ease;
  cursor: pointer;
  margin-top: 10px;
}

.buy-button {
  background: #2a9d8f;
  color: white;
  border: none;
}

.buy-button:hover {
  background: #21867a;
}

.wishlist-button {
  background: #e76f51;
  color: white;
  border: none;
}

.wishlist-button:hover {
  background: #d45b3e;
}

/* Estilo de Carregamento */
.loading {
  text-align: center;
  font-size: 1.5rem;
  color: #555;
}
</style>
