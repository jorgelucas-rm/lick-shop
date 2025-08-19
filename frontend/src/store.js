import { createStore } from 'vuex';
import api from './services/api'; 

export default createStore({
  state: {
    user: {},
    register: {},
    location: {},
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setRegister(state, register) {
      state.register = register;
    },
    setLocation(state, location) {
      state.location = location;
    },
    clearData(state) {
      state.user = {};
      state.register = {};
      state.location = {};
    },
  },
  actions: {
    async registerUser({ state }) {
      const requestData = {
        userTO: {
          nome: state.user.nome,
          cpf: state.user.cpf,
          nascimento: state.user.dataNascimento,
        },
        enderecoTO: {
          cep: state.location.cep,
          logradouro: state.location.logradouro,
          bairro: state.location.bairro,
          cidade: state.location.cidade,
          estado: state.location.estado,
          complemento: state.location.complemento,
        },
        registerTO: {
          usuario: state.register.username,
          senha: state.register.senha,
          email: state.register.email,
        },
      };

      try {
        const response = await api.post('/api/v1/usuario', requestData);

        if (response.status !== 200) {
          throw new Error('Erro ao enviar dados.');
        }

        console.log('Registro concluído com sucesso!', response.data);

        this.commit('clearData');
      } catch (error) {
        console.error('Erro no registro:', error);
        throw error;
      }
    },
  },
});
