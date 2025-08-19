<template>
  <div class="background-image"></div>
  <div class="profile-page d-flex justify-content-center align-items-center">
    <div class="UserProfile p-4 rounded shadow-lg">
      <div class="ProfileTitle text-center text-danger font-weight-bold mb-4">Perfil do Usuário</div>
      <div v-if="isLoading" class="text-center text-white">Carregando dados...</div>
      <div v-else-if="userData">
        <div v-if="!isEditing">
          <div class="section mb-4">
            <h5 class="text-danger">Informações Pessoais</h5>
            <p class="text-white"><strong>Nome:</strong> {{ userData.nome }}</p>
            <p class="text-white"><strong>CPF:</strong> {{ userData.cpf }}</p>
            <p class="text-white"><strong>Data de Nascimento:</strong> {{ formatDate(userData.nascimento) }}</p>
          </div>
          <div class="section mb-4">
            <h5 class="text-danger">Endereços</h5>
            <div v-for="(endereco, index) in userData.endereco" :key="index" class="mb-3">
              <p class="text-white"><strong>Endereço {{ index + 1 }}:</strong> {{ endereco.enderecoCompleto }}</p>
              <p class="text-white"><strong>CEP:</strong> {{ endereco.cep }}</p>
              <p class="text-white"><strong>Logradouro:</strong> {{ endereco.logradouro }}</p>
              <p class="text-white"><strong>Bairro:</strong> {{ endereco.bairro }}</p>
              <p class="text-white"><strong>Cidade:</strong> {{ endereco.cidade }}</p>
              <p class="text-white"><strong>Estado:</strong> {{ endereco.estado }}</p>
              <p class="text-white"><strong>Número:</strong> {{ endereco.numero }}</p>
              <p class="text-white"><strong>Complemento:</strong> {{ endereco.complemento || 'N/A' }}</p>
            </div>
            <p v-if="userData.endereco.length === 0" class="text-white">Nenhum endereço cadastrado.</p>
          </div>
          <div class="section mb-4">
            <h5 class="text-danger">Informações da Conta</h5>
            <p class="text-white"><strong>Usuário:</strong> {{ userData.conta.usuario }}</p>
            <p class="text-white"><strong>Email:</strong> {{ userData.conta.email }}</p>
            <p class="text-white"><strong>Cargo:</strong> {{ userData.conta.cargo }}</p>
            <p class="text-white"><strong>Data de Criação:</strong> {{ formatDateTime(userData.conta.dataCriacao) }}</p>
            <p class="text-white"><strong>Data de Atualização:</strong> {{ formatDateTime(userData.conta.dataAtualizacao) }}</p>
          </div>
          <div class="section mb-4">
            <h5 class="text-danger">Carrinho</h5>
            <div v-for="(item, index) in userData.carrinho" :key="index" class="mb-3">
              <p class="text-white"><strong>Produto {{ index + 1 }}:</strong> {{ item.nome }}</p>
              <p class="text-white"><strong>Marca:</strong> {{ item.marca.nome }}</p>
              <p class="text-white"><strong>Categoria:</strong> {{ item.categoria.nome }}</p>
              <p class="text-white"><strong>Descrição Curta:</strong> {{ item.descricaoCurta }}</p>
              <p class="text-white"><strong>Valor de Venda:</strong> R$ {{ item.valorVenda.toFixed(2) }}</p>
            </div>
            <p v-if="userData.carrinho.length === 0" class="text-white">Carrinho vazio.</p>
          </div>
        </div>
        <form v-else @submit.prevent="handleUpdate">
          <div class="section mb-4">
            <h5 class="text-danger">Informações Pessoais</h5>
            <div class="InputField mb-3">
              <label class="Label text-white">Nome</label>
              <input v-model="editedData.nome" type="text" class="form-control" required>
            </div>
            <div class="InputField mb-3">
              <label class="Label text-white">CPF</label>
              <input v-model="editedData.cpf" type="text" class="form-control" required>
            </div>
            <div class="InputField mb-3">
              <label class="Label text-white">Data de Nascimento</label>
              <input v-model="editedData.nascimento" type="date" class="form-control" required>
            </div>
          </div>
          <div class="section mb-4">
            <h5 class="text-danger">Endereços</h5>
            <div v-if="editedData.endereco.length > 0">
              <div class="InputField mb-3">
                <label class="Label text-white">CEP</label>
                <input v-model="editedData.endereco[0].cep" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Logradouro</label>
                <input v-model="editedData.endereco[0].logradouro" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Bairro</label>
                <input v-model="editedData.endereco[0].bairro" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Cidade</label>
                <input v-model="editedData.endereco[0].cidade" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Estado</label>
                <input v-model="editedData.endereco[0].estado" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Número</label>
                <input v-model="editedData.endereco[0].numero" type="text" class="form-control" required>
              </div>
              <div class="InputField mb-3">
                <label class="Label text-white">Complemento</label>
                <input v-model="editedData.endereco[0].complemento" type="text" class="form-control">
              </div>
            </div>
            <p v-else class="text-white">Nenhum endereço para editar.</p>
          </div>
          <div class="section mb-4">
            <h5 class="text-danger">Informações da Conta</h5>
            <div class="InputField mb-3">
              <label class="Label text-white">Usuário</label>
              <input v-model="editedData.conta.usuario" type="text" class="form-control" required>
            </div>
            <div class="InputField mb-3">
              <label class="Label text-white">Email</label>
              <input v-model="editedData.conta.email" type="email" class="form-control" required>
            </div>
            <div class="InputField mb-3">
              <label class="Label text-white">Nova Senha (deixe em branco para manter)</label>
              <input v-model="editedData.conta.senha" type="password" class="form-control">
            </div>
          </div>
          <button type="submit" class="button mt-4" :disabled="isSaving">Salvar Alterações</button>
          <button type="button" class="button cancel mt-2" @click="cancelEdit">Cancelar</button>
        </form>
        <div class="d-flex justify-content-between mt-4" v-if="!isEditing">
          <button class="button edit" @click="startEdit">Alterar</button>
          <button class="button delete" @click="handleDelete">Apagar Conta</button>
        </div>
      </div>
      <div v-else class="text-center text-white">Erro ao carregar dados do usuário.</div>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';
import { authStore } from '@/services/AuthStore';
import localStorageService from '@/services/LocalStorageService';

export default {
  data() {
    return {
      userData: null,
      editedData: null,
      isLoading: true,
      isEditing: false,
      isSaving: false,
    };
  },
  async mounted() {
    await this.fetchUserData();
  },
  methods: {
  async fetchUserData() {
  this.isLoading = true;
  authStore.updateLoginStatus();
  if (!authStore.isLoggedIn) {
    console.error('Autenticação inválida:', {
      isLoggedIn: authStore.isLoggedIn,
      userId: authStore.userId,
      token: localStorageService.getToken(),
    });
    alert('Usuário não autenticado. Faça login novamente.');
    this.$router.push({ name: 'Login' });
    return;
  }

  try {
    console.log('Buscando dados do usuário autenticado');
    console.log('Token enviado:', localStorageService.getToken());
    const response = await api.get('/api/v1/usuario/me');
    console.log('Dados do usuário carregados:', response.data);
    this.userData = response.data;

    // Atualizar authStore.userId, caso esteja incorreto
    if (response.data.id && response.data.id !== authStore.userId) {
      console.warn('userId atualizado:', response.data.id);
      authStore.setAuthData(
        localStorageService.getToken(),
        response.data.id,
        response.data.nome,
        authStore.isAdmin
      );
    }
  } catch (error) {
    console.error('Erro ao carregar perfil:', {
      status: error.response?.status,
      data: error.response?.data,
      message: error.message,
      headers: error.config?.headers,
    });
    if (error.response?.status === 401) {
      alert('Sessão expirada ou não autorizado. Faça login novamente.');
      authStore.resetAuthStatus();
      this.$router.push({ name: 'Login' });
    } else if (error.response?.status === 403) {
      alert('Você não tem permissão para acessar esses dados.');
      this.$router.push({ name: 'Home' });
    } else if (error.response?.status === 404) {
      alert('Dados do usuário não encontrados.');
    } else {
      alert(`Erro ${error.response?.status || 'desconhecido'}: ${error.response?.data?.message || 'Erro ao carregar perfil.'}`);
    }
  } finally {
    this.isLoading = false;
  }
},
    startEdit() {
      this.editedData = JSON.parse(JSON.stringify(this.userData));
      this.isEditing = true;
    },
    cancelEdit() {
      this.isEditing = false;
      this.editedData = null;
    },
    async handleUpdate() {
      this.isSaving = true;
      const id = this.userData.id;
      try {
        const response = await api.put(`/api/v1/usuario/${id}`, this.editedData);
        this.userData = response.data;
        this.isEditing = false;
        alert('Perfil atualizado com sucesso.');
      } catch (error) {
        console.error('Erro ao atualizar perfil:', error);
        if (error.response?.status === 400) {
          alert('Dados inválidos. Verifique os campos e tente novamente.');
        } else if (error.response?.status === 401) {
          alert('Sessão expirada. Faça login novamente.');
          authStore.resetAuthStatus();
          this.$router.push({ name: 'Login' });
        } else {
          alert(`Erro ao atualizar perfil: ${error.response?.data?.message || 'Tente novamente.'}`);
        }
      } finally {
        this.isSaving = false;
      }
    },
    async handleDelete() {
      if (!confirm('Tem certeza que deseja apagar sua conta? Essa ação é irreversível.')) return;
      const id = this.userData.id;
      try {
        await api.delete(`/api/v1/usuario/${id}`);
        localStorageService.clearAll();
        authStore.resetAuthStatus();
        this.$emit('loginStatusChanged');
        alert('Conta apagada com sucesso.');
        this.$router.push({ name: 'Home' });
      } catch (error) {
        console.error('Erro ao apagar conta:', error);
        alert(`Erro ao apagar conta: ${error.response?.data?.message || 'Tente novamente.'}`);
      }
    },
    formatDate(date) {
      return date ? new Date(date).toLocaleDateString('pt-BR') : 'N/A';
    },
    formatDateTime(datetime) {
      return datetime ? new Date(datetime).toLocaleString('pt-BR') : 'N/A';
    },
  },
};
</script>

<style scoped>
.background-image {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('@/assets/background.jpg');
  background-size: cover;
  background-position: center;
  z-index: -1;
}

.profile-page {
  height: 100vh;
}

.UserProfile {
  width: 100%;
  max-width: 600px;
  background: rgba(27, 27, 27, 0.8);
  border-radius: 5px;
  border: 1px solid #ff0000;
  padding: 20px;
  overflow-y: auto;
  max-height: 90vh;
}

.ProfileTitle {
  color: #f00;
  font-size: 47px;
  font-weight: 700;
}

.section h5 {
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.InputField {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.form-control {
  width: 100%;
  padding: 10px;
  background: transparent;
  border: 1px solid #ff3333;
  border-radius: 4px;
  color: white;
  font-size: 1rem;
}

.button {
  width: 100%;
  padding: 12px;
  background: #ff0000;
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  font-size: 1.2rem;
  text-align: center;
}

.button:hover {
  background-color: #cc0000;
}

.button.edit {
  width: 48%;
  background: #ff9900;
}

.button.edit:hover {
  background: #cc7700;
}

.button.delete {
  width: 48%;
  background: #990000;
}

.button.delete:hover {
  background: #660000;
}

.button.cancel {
  width: 100%;
  background: #666666;
}

.button.cancel:hover {
  background: #444444;
}
</style>