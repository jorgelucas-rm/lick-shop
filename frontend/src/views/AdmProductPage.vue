<template>
  <div class="create-product">
    <h1>Criar Produto</h1>
    <form @submit.prevent="submitProduct">
      <div class="form-group">
        <label for="name">Nome do Produto:</label>
        <input v-model="newProduct.nome" id="name" type="text" required />
      </div>
      
      <div class="form-group">
        <label for="short-description">Descrição Curta:</label>
        <input v-model="newProduct.descricaoCurta" id="short-description" type="text" required />
      </div>

      <div class="form-group">
        <label for="detailed-description">Descrição Detalhada:</label>
        <textarea v-model="newProduct.descricaoDetalhada" id="detailed-description" required></textarea>
      </div>

      <div class="form-group">
        <label for="cost-price">Valor Custo:</label>
        <input v-model="newProduct.valorCusto" id="cost-price" type="number" required />
      </div>

      <div class="form-group">
        <label for="sale-price">Valor Venda:</label>
        <input v-model="newProduct.valorVenda" id="sale-price" type="number" required />
      </div>

      <div class="form-group">
        <label for="image">Imagem:</label>
        <input v-model="newProduct.imagemList" id="image" type="text" required />
        <small>Insira a URL da imagem do produto</small>
      </div>

      <div class="form-group">
        <label for="brand">Marca:</label>
        <select v-model="newProduct.marca.id" id="brand" required>
          <option value="" disabled>Selecione uma Marca</option>
          <option v-for="marca in marcas" :key="marca.id" :value="marca.id">{{ marca.nome }}</option>
        </select>
      </div>

      <div class="form-group">
        <label for="category">Categoria:</label>
        <select v-model="newProduct.categoria.id" id="category" required>
          <option value="" disabled>Selecione uma Categoria</option>
          <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">{{ categoria.nome }}</option>
        </select>
      </div>

      <button type="submit" class="submit-btn">Criar Produto</button>
    </form>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      newProduct: {
        nome: '',
        descricaoCurta: '',
        descricaoDetalhada: '',
        valorCusto: 0,
        valorVenda: 0,
        imagemList: [],
        marca: {
          id: ''
        },
        categoria: {
          id: ''
        },
      },
      marcas: [],
      categorias: [],
    };
  },
  created() {
    this.fetchMarcas();
    this.fetchCategorias();
  },
  methods: {
    // Função para buscar as marcas
    async fetchMarcas() {
      try {
        const response = await api.get('/api/v1/marca');
        if (response.status === 200) {
          this.marcas = response.data; // Atualiza as marcas no estado
        } else {
          console.error('Erro ao buscar marcas:', response);
        }
      } catch (error) {
        console.error('Erro ao buscar marcas:', error);
      }
    },

    // Função para buscar as categorias
    async fetchCategorias() {
      try {
        const response = await api.get('/api/v1/categoria');
        if (response.status === 200) {
          this.categorias = response.data; // Atualiza as categorias no estado
        } else {
          console.error('Erro ao buscar categorias:', response);
        }
      } catch (error) {
        console.error('Erro ao buscar categorias:', error);
      }
    },

    // Função para enviar os dados do novo produto
    async submitProduct() {
      const productData = {
        nome: this.newProduct.nome,
        descricaoCurta: this.newProduct.descricaoCurta,
        descricaoDetalhada: this.newProduct.descricaoDetalhada,
        valorCusto: this.newProduct.valorCusto,
        valorVenda: this.newProduct.valorVenda,
        imagemList: [this.newProduct.imagemList], // Supondo que imagemList é uma lista de URLs
        marca: {
          id: this.newProduct.marca.id
        },
        categoria: {
          id: this.newProduct.categoria.id
        },
        dataCriacao: new Date().toISOString(),
        dataAtualizacao: new Date().toISOString(),
      };

      try {
        const response = await api.post('/api/v1/produto', productData);
        if (response.status === 201) {
          this.$router.push('/admin/products'); // Redireciona após a criação
        }
      } catch (error) {
        console.error('Erro ao criar produto:', error);
      }
    },
  },
};
</script>

<style scoped>
.create-product {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 2rem;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
select,
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

textarea {
  resize: vertical;
}

.submit-btn {
  background-color: #3498db;
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.submit-btn:hover {
  background-color: #2980b9;
}
</style>
