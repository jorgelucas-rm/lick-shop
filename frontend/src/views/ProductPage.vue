<template>
  <div style="background-color: #ffe4e1; min-height: 100vh; padding: 20px;">
    <div class="container mt-4">
      <div class="text-center mb-4">
        <h1>Lista de Produtos</h1>
      </div>

      <div class="row">
        <!-- Sidebar para Filtros -->
        <aside class="col-lg-3 mb-4">
          <div class="card shadow-sm">
            <div class="card-body">
              <h4>Filtros</h4>
              <!-- Categoria -->
              <div class="mb-3">
                <label for="categoryFilter" class="form-label">Categorias</label>
                <select
                  id="categoryFilter"
                  class="form-select"
                  v-model="selectedCategory"
                  @change="filterProducts"
                >
                  <option value="">Todas</option>
                  <option v-for="category in categories" :key="category" :value="category">
                    {{ category }}
                  </option>
                </select>
              </div>
              <!-- Marcas -->
              <div class="mb-3">
                <label for="brandFilter" class="form-label">Marcas</label>
                <select
                  id="brandFilter"
                  class="form-select"
                  v-model="selectedBrand"
                  @change="filterProducts"
                >
                  <option value="">Todas</option>
                  <option v-for="brand in brands" :key="brand" :value="brand">
                    {{ brand }}
                  </option>
                </select>
              </div>
              <!-- Faixa de Preço -->
              <div class="mb-3">
                <label for="priceRange" class="form-label">Faixa de preço (R$)</label>
                <input
                  id="priceRange"
                  type="range"
                  class="form-range"
                  min="0"
                  max="5000"
                  step="50"
                  v-model="priceRange"
                  @input="filterProducts"
                />
                <p class="text-center">Até <strong>R$ {{ priceRange }}</strong></p>
              </div>
              <!-- Botão Reset -->
              <button class="btn btn-danger w-100" @click="resetFilters">Limpar Filtros</button>
            </div>
          </div>
        </aside>

        <!-- Lista de Produtos -->
        <main class="col-lg-9">
          <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
            <div v-if="errorMessage" class="alert alert-danger text-center">
              {{ errorMessage }}
            </div>
            <div v-if="!errorMessage && products.length === 0" class="text-center">
              Nenhum produto encontrado.
            </div>
            <div
              v-for="product in paginatedProducts"
              :key="product.id"
              class="col"
            >
              <div class="card h-100 shadow-sm border-0 rounded-3">
                <router-link :to="`/details/${product.id}`" class="text-decoration-none">
                  <div class="card-img-top-container">
                    <img
                      :src="getImageUrl(product)"
                      :alt="product.nome"
                      class="card-img-top"
                    />
                  </div>
                  <div class="card-body p-3">
                    <h5 class="card-title text-wrap text-black">{{ product.nome }}</h5>
                  </div>
                  <div class="card-footer d-flex justify-content-between align-items-center p-2 fixed-bottom">
                    <p class="card-price text-danger mb-0">R$ {{ product.valorVenda }}</p>
                  </div>
                </router-link>
              </div>
            </div>
          </div>

          <!-- Paginação -->
          <nav v-if="paginatedProducts.length > 0" class="mt-4">
            <ul class="pagination justify-content-center">
              <li class="page-item" :class="{ disabled: currentPage === 1 }">
                <button class="page-link" @click="changePage('prev')">Anterior</button>
              </li>
              <li class="page-item disabled">
                <span class="page-link">Página {{ currentPage }} de {{ totalPages }}</span>
              </li>
              <li class="page-item" :class="{ disabled: currentPage === totalPages }">
                <button class="page-link" @click="changePage('next')">Próxima</button>
              </li>
            </ul>
          </nav>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import api from "@/services/api";

export default {
  name: "ProductList",
  data() {
    return {
      products: [],
      filteredProducts: [],
      categories: ["Vestimentas", "Bdsm", "Vibradores", "Lubrificantes"],
      brands: ["Marca A", "Marca B", "Marca C", "Marca D"],
      selectedCategory: "",
      selectedBrand: "",
      priceRange: 5000,
      currentPage: 1,
      itemsPerPage: 20, // 5 linhas x 4 colunas = 20 itens por página
      errorMessage: "",
    };
  },
  computed: {
    totalPages() {
      return Math.ceil(this.filteredProducts.length / this.itemsPerPage);
    },
    paginatedProducts() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.filteredProducts.slice(start, end);
    },
  },
  methods: {
    async loadProducts() {
      try {
        const response = await api.get("/api/v1/produto");
        this.products = response.data || [];
        this.filteredProducts = this.products;
      } catch (error) {
        this.errorMessage =
          "Erro ao carregar os produtos. Tente novamente mais tarde.";
        console.error("Erro ao carregar produtos:", error);
      }
    },
    getImageUrl(product) {
      return (
        product?.imagemList?.[0] ||
        "https://api.lickshop.acilab.com.br/uploads/images/default.jpeg"
      );
    },
    changePage(direction) {
      if (direction === "next" && this.currentPage < this.totalPages) {
        this.currentPage++;
      } else if (direction === "prev" && this.currentPage > 1) {
        this.currentPage--;
      }
      window.scrollTo(0, 0); // Voltar para o topo da página ao mudar de página
    },
    filterProducts() {
      this.filteredProducts = this.products.filter((product) => {
        const matchesCategory =
          !this.selectedCategory ||
          product.categoria?.nome === this.selectedCategory;
        const matchesBrand =
          !this.selectedBrand || product.marca === this.selectedBrand;
        const matchesPrice = product.valorVenda <= this.priceRange;
        return matchesCategory && matchesBrand && matchesPrice;
      });
    },
    resetFilters() {
      this.selectedCategory = "";
      this.selectedBrand = "";
      this.priceRange = 5000;
      this.filteredProducts = this.products;
    },
  },
  mounted() {
    this.loadProducts();
  },
};
</script>

<style scoped>
.card-img-top-container {
  width: 100%;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.card-img-top {
  object-fit: contain; /* Ajusta a imagem sem cortar */
  width: 100%;
  height: 100%;
}

.card-title {
  font-size: 1.2rem;
  font-weight: 500;
  color: #333;
}

.card-price {
  font-size: 1.1rem;
  font-weight: bold;
  color: #e74c3c;
}

.card-body {
  padding: 1rem;
}

.card-footer {
  background-color: #f8f8f8;
  border-top: 1px solid #ddd;
}

.card-footer p {
  margin-bottom: 0;
}

body {
  background-color: #ffe4e1; /* Fundo rosa */
  animation: fadeIn 1.5s ease;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.text-wrap {
  white-space: normal;
}

.text-black {
  color: black;
}

.fixed-bottom {
  position: absolute;
  bottom: 0;
  width: 100%;
}
</style>
