import api from "../api";
// Funções para produtos
const getProdutos = async () => {
    try {
      const response = await api.get('/api/v1/produto');
      return response.data;
    } catch (error) {
      console.error("Erro ao buscar produtos", error);
      throw error;
    }
  };
  
  const getProdutoById = async (id) => {
    try {
      const response = await api.get(`/api/v1/produto/${id}`);
      return response.data;
    } catch (error) {
      console.error("Erro ao buscar produto", error);
      throw error;
    }
  };
  
  const addProduto = async (produtoData) => {
    try {
      const response = await api.post('/api/v1/produto', produtoData);
      return response.data;
    } catch (error) {
      console.error("Erro ao adicionar produto", error);
      throw error;
    }
  };
  
  const updateProduto = async (id, produtoData) => {
    try {
      const response = await api.put(`/api/v1/produto/${id}`, produtoData);
      return response.data;
    } catch (error) {
      console.error("Erro ao atualizar produto", error);
      throw error;
    }
  };
  
  const deleteProduto = async (id) => {
    try {
      const response = await api.delete(`/api/v1/produto/${id}`);
      return response.data;
    } catch (error) {
      console.error("Erro ao excluir produto", error);
      throw error;
    }
  };
  
  export { getProdutos, getProdutoById, addProduto, updateProduto, deleteProduto };
  