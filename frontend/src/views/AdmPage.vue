<template>
  <div class="admin-page">
    <div class="admin-sidebar">
      <div class="admin-logo">
        <img src="@/assets/logo.png" alt="Logo" class="logo" />
      </div>
      <ul class="admin-menu">
        <li @click="currentSection = 'dashboard'" :class="{ active: currentSection === 'dashboard' }">Dashboard</li>
        <li @click="currentSection = 'users'" :class="{ active: currentSection === 'users' }">Gerenciar Usuários</li>
        <li @click="currentSection = 'products'" :class="{ active: currentSection === 'products' }">Gerenciar Produtos</li>
        <li @click="currentSection = 'reports'" :class="{ active: currentSection === 'reports' }">Relatórios</li>
      </ul>
    </div>

    <div class="admin-content">
      <!-- Dashboard -->
      <div v-if="currentSection === 'dashboard'" class="section dashboard">
        <h1>Dashboard</h1>
        <div class="stats">
          <div class="stat">
            <h2>Total de Usuários</h2>
            <p>{{ users.length }}</p>
          </div>
          <div class="stat">
            <h2>Total de Produtos</h2>
            <p>{{ products.length }}</p>
          </div>
          <div class="stat">
            <h2>Relatórios Recentes</h2>
            <p>{{ reports.length }}</p>
          </div>
        </div>
      </div>

      <!-- Gerenciar Usuários -->
      <div v-if="currentSection === 'users'" class="section users">
        <h1>Gerenciar Usuários</h1>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Email</th>
              <th>Data de Cadastro</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.name }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.registrationDate }}</td>
              <td>
                <button @click="editUser(user.id)">Editar</button>
                <button @click="deleteUser(user.id)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
        <button class="add-btn" @click="addUser">Adicionar Usuário</button>
      </div>

      <!-- Gerenciar Produtos -->
      <div v-if="currentSection === 'products'" class="section products">
        <h1>Gerenciar Produtos</h1>
        <table class="admin-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Preço</th>
              <th>Categoria</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="product in products" :key="product.id">
              <td>{{ product.id }}</td>
              <td>{{ product.name }}</td>
              <td>{{ product.price }}</td>
              <td>{{ product.category }}</td>
              <td>
                <button @click="editProduct(product.id)">Editar</button>
                <button @click="deleteProduct(product.id)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
        <button class="add-btn" @click="addProduct">Adicionar Produto</button>
      </div>

      <!-- Relatórios -->
      <div v-if="currentSection === 'reports'" class="section reports">
        <h1>Relatórios</h1>
        <div v-for="report in reports" :key="report.id" class="report-item">
          <h3>{{ report.title }}</h3>
          <p>{{ report.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      currentSection: 'dashboard',
      users: [],
      products: [],
      reports: [],
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    // Função para carregar os dados do usuário, produto e relatório
    async loadData() {
      await this.fetchUsers();
      await this.fetchProducts();
      await this.fetchReports();
    },
    // Buscar Usuários
    async fetchUsers() {
      try {
        const response = await api.get('/api/v1/usuario');
        this.users = response.data;
      } catch (error) {
        console.error('Erro ao buscar usuários:', error);
      }
    },
    // Buscar Produtos
    async fetchProducts() {
      try {
        const response = await api.get('/api/v1/produto');
        this.products = response.data;
      } catch (error) {
        console.error('Erro ao buscar produtos:', error);
      }
    },
    // Buscar Relatórios (Caso a API tenha esta rota)
    async fetchReports() {
      try {
        const response = await api.get('/api/v1/reports');
        this.reports = response.data;
      } catch (error) {
        console.error('Erro ao buscar relatórios:', error);
      }
    },
    // Editar Usuário
    editUser(userId) {
      this.$router.push(`/admin/users/${userId}`);
    },
    // Excluir Usuário
    async deleteUser(userId) {
      try {
        await api.delete(`/api/v1/usuario/${userId}`);
        this.fetchUsers(); // Recarregar a lista de usuários após exclusão
      } catch (error) {
        console.error('Erro ao excluir usuário:', error);
      }
    },
    // Adicionar Novo Usuário
    addUser() {
      this.$router.push('/admin/users/new');
    },
    // Editar Produto
    editProduct(productId) {
      this.$router.push(`/admin/products/${productId}`);
    },
    // Excluir Produto
    async deleteProduct(productId) {
      try {
        await api.delete(`/api/v1/produto/${productId}`);
        this.fetchProducts(); // Recarregar a lista de produtos após exclusão
      } catch (error) {
        console.error('Erro ao excluir produto:', error);
      }
    },
    // Adicionar Novo Produto
    addProduct() {
      this.$router.push('/admin/products/new');
    },
  },
};
</script>



<style scoped>
.admin-page {
  display: flex;
  height: 100vh;
  font-family: Arial, sans-serif;
}

.admin-sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: #fff;
  padding: 20px;
  display: flex;
  flex-direction: column;
}

.admin-logo img {
  width: 100%;
  margin-bottom: 20px;
}

.admin-menu {
  list-style-type: none;
  padding: 0;
}

.admin-menu li {
  margin: 15px 0;
  cursor: pointer;
  transition: background-color 0.3s;
  padding: 10px;
  border-radius: 5px;
}

.admin-menu li:hover,
.admin-menu li.active {
  background-color: #34495e;
}

.admin-content {
  flex-grow: 1;
  padding: 20px;
  background-color: #ecf0f1;
  overflow-y: auto;
}

h1 {
  font-size: 2rem;
  margin-bottom: 20px;
}

.stats {
  display: flex;
  gap: 20px;
}

.stat {
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 200px;
}

.stat h2 {
  margin-bottom: 10px;
}

.admin-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.admin-table th,
.admin-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.admin-table th {
  background-color: #f9f9f9;
}

.add-btn {
  background-color: #3498db;
  color: #fff;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  margin-top: 20px;
  border-radius: 5px;
}

.add-btn:hover {
  background-color: #2980b9;
}

.report-item {
  background-color: #fff;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.report-item h3 {
  margin-bottom: 10px;
}
</style>