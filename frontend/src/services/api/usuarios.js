import api from '../api';
const getUsuarios = async () => {
    try {
      const response = await api.get('/api/v1/usuario');
      return response.data;
    } catch (error) {
      console.error("Erro ao buscar usuários", error);
      throw error;
    }
  };
  
  const getUsuarioById = async (id) => {
    try {
      const response = await api.get(`/api/v1/usuario/${id}`);
      return response.data;
    } catch (error) {
      console.error("Erro ao buscar usuário", error);
      throw error;
    }
  };
  
  const updateUsuario = async (id, usuarioData) => {
    try {
      const response = await api.put(`/api/v1/usuario/${id}`, usuarioData);
      return response.data;
    } catch (error) {
      console.error("Erro ao atualizar usuário", error);
      throw error;
    }
  };
  
  const deleteUsuario = async (id) => {
    try {
      const response = await api.delete(`/api/v1/usuario/${id}`);
      return response.data;
    } catch (error) {
      console.error("Erro ao excluir usuário", error);
      throw error;
    }
  };
  
  export { getUsuarios, getUsuarioById, updateUsuario, deleteUsuario };
  