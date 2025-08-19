<template>
<div style="background-color: #ffe4e1; min-height: 100vh; padding: 20px;">
  <div class="container mt-4">
    <div class="text-center mb-4">
      <h1>Lista de Produtos</h1>
    </div>

    <div class="row">
      <aside class="col-lg-3 mb-4">
        <div class="card shadow-sm">
          <div class="card-body">
            <h4>Filtros</h4>
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
            <div class="mb-3">
              <label for="priceRange" class="form-label">Faixa de preço (R$)</label>
              <input
                id="priceRange"
                type="range"
                class="form-range"
                min="0"
                max="5000"
                step="50"
                v-model.number="priceRange"
                @input="filterProducts"
              />
              <input
                id="priceInput"
                type="number"
                class="form-control mt-2"
                min="0"
                max="5000"
                step="0.01"
                v-model.number="priceRange"
                @input="validatePriceInput"
                placeholder="Digite o preço máximo"
              />
              <p class="text-center mt-2">Até <strong>R$ {{ formatPrice(priceRange) }}</strong></p>
            </div>
            <button class="btn btn-danger w-100" @click="resetFilters">Limpar Filtros</button>
          </div>
        </div>
      </aside>

      <main style="margin-top: -55px;" class="col-lg-9">
        <div v-if="isLoading" class="text-center">
          <div class="spinner-border text-danger" role="status">
            <span class="visually-hidden">Carregando...</span>
          </div>
        </div>
        <div v-else-if="errorMessage" class="alert alert-danger text-center">
          {{ errorMessage }}
        </div>
        <div v-else-if="filteredProducts.length === 0" class="text-center">
          Nenhum produto encontrado.
        </div>
        <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-3">
          <div
            v-for="product in paginatedProducts"
            :key="product.id"
            class="col"
          >
            <div class="card h-100 shadow-sm border-0 rounded-3 product-card">
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
                <div class="card-footer p-2">
                  <p class="card-price text-danger mb-0">R$ {{ formatPrice(product.valorVenda) }}</p>
                </div>
              </router-link>
            </div>
          </div>
        </div>

        <nav v-if="filteredProducts.length > 0" class="mt-4">
          <ul class="pagination justify-content-center">
            <li class="page-item" :class="{ disabled: currentPage === 1 }">
              <button
                class="page-link"
                @click="changePage('prev')"
                :disabled="currentPage === 1"
              >
                Anterior
              </button>
            </li>
            <li class="page-item disabled">
              <span class="page-link">Página {{ currentPage }} de {{ totalPages }}</span>
            </li>
            <li class="page-item" :class="{ disabled: currentPage === totalPages }">
              <button
                class="page-link"
                @click="changePage('next')"
                :disabled="currentPage === totalPages"
              >
                Próximo
              </button>
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
      categories: [],
      brands: [],
      selectedCategory: this.$route.query.category || "",
      selectedBrand: "",
      searchQuery: this.$route.query.search || "",
      priceRange: 5000,
      currentPage: 1,
      itemsPerPage: 20,
      isLoading: true,
      errorMessage: "",
      retryCount: 0,
      maxRetries: 3,
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
      this.isLoading = true;
      this.errorMessage = "";
      try {
        const params = this.searchQuery ? { nome: this.searchQuery } : {};
        const response = await api.get("/api/v1/produto", { params });
        this.products = Array.isArray(response.data) ? response.data.map(product => ({
          id: product.id,
          nome: product.nome,
          valorVenda: Number(product.valorVenda || product.valor_venda || 0),
          imagemList: product.imagemList || product.imagens || [],
          categoria: product.categoria?.nome || product.categoria || "",
          marca: product.marca || product.marcaNome || "",
        })) : [];

        this.categories = [...new Set(this.products.map(p => p.categoria).filter(c => c))].sort();
        this.brands = [...new Set(this.products.map(p => p.marca).filter(b => b))].sort();

        if (this.products.length === 0 && this.retryCount < this.maxRetries && !this.searchQuery) {
          const fallbackResponse = await api.get("/api/v1/produto", { params: { nome: "" } });
          this.products = Array.isArray(fallbackResponse.data) ? fallbackResponse.data.map(product => ({
            id: product.id,
            nome: product.nome,
            valorVenda: Number(product.valorVenda || product.valor_venda || 0),
            imagemList: product.imagemList || product.imagens || [],
            categoria: product.categoria?.nome || product.categoria || "",
            marca: product.marca || product.marcaNome || "",
          })) : [];
          this.categories = [...new Set(this.products.map(p => p.categoria).filter(c => c))].sort();
          this.brands = [...new Set(this.products.map(p => p.marca).filter(b => b))].sort();
        }

        this.filterProducts();
        this.isLoading = false;
        this.retryCount = 0;
      } catch (error) {
        console.error("Erro ao carregar produtos:", error);
        if (this.retryCount < this.maxRetries) {
          this.retryCount++;
          console.log(`Tentativa ${this.retryCount} de ${this.maxRetries}`);
          setTimeout(() => this.loadProducts(), 2000);
        } else {
          this.errorMessage = "Erro ao carregar os produtos. Tente novamente mais tarde.";
          this.isLoading = false;
        }
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
    validatePriceInput() {
      if (this.priceRange < 0) {
        this.priceRange = 0;
      } else if (this.priceRange > 5000) {
        this.priceRange = 5000;
      }
      this.filterProducts();
    },
    changePage(direction) {
      if (direction === "next" && this.currentPage < this.totalPages) {
        this.currentPage++;
      } else if (direction === "prev" && this.currentPage > 1) {
        this.currentPage--;
      }
      window.scrollTo(0, 0);
    },
    filterProducts() {
      this.filteredProducts = this.products.filter((product) => {
        const matchesCategory =
          !this.selectedCategory ||
          product.categoria === this.selectedCategory;
        const matchesBrand =
          !this.selectedBrand || product.marca === this.selectedBrand;
        const matchesPrice = product.valorVenda <= this.priceRange;
        const matchesSearch =
          !this.searchQuery ||
          product.nome.toLowerCase().includes(this.searchQuery.toLowerCase());
        return matchesCategory && matchesBrand && matchesPrice && matchesSearch;
      });
      this.currentPage = 1;
    },
    resetFilters() {
      this.selectedCategory = this.$route.query.category || "";
      this.selectedBrand = "";
      this.searchQuery = this.$route.query.search || "";
      this.priceRange = 5000;
      this.filterProducts();
    },
  },
  mounted() {
    this.loadProducts();
  },
  watch: {
    "$route.query": {
      handler(newQuery) {
        this.selectedCategory = newQuery.category || "";
        this.searchQuery = newQuery.search || "";
        this.filterProducts();
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.product-card {
  background: #f5f5f5;
  width: 100%;
  max-width: 250px;
  height: 340px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
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
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  min-height: 100px;
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
  flex-shrink: 0;
}

.card-price {
  font-size: 1.2rem;
  font-weight: 600;
  color: #e91e1e;
  margin: 0;
}

@media (max-width: 768px) {
  .product-card {
    max-width: 180px;
    height: 300px;
  }

  .card-img-top-container {
    height: 140px;
  }

  .card-body {
    min-height: 80px;
  }

  .card-title {
    font-size: 1rem;
  }
}

@media (max-width: 576px) {
  .product-card {
    max-width: 160px;
    height: 280px;
  }

  .card-img-top-container {
    height: 120px;
  }

  .card-body {
    min-height: 70px;
  }
}
</style>