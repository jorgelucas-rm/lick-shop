import api from '../api';

// Funções de Marcas

// Carregar todas as Marcas
export const getMarcas = async () => {
  try {
    const response = await api.get('/api/v1/marca');
    return response.data;
  } catch (error) {
    console.error("Erro ao carregar as marcas:", error);
    throw error;
  }
};

// Adicionar uma nova Marca
export const addMarca = async (marca) => {
  try {
    const response = await api.post('/api/v1/marca', marca);
    return response.data;
  } catch (error) {
    console.error("Erro ao adicionar marca:", error);
    throw error;
  }
};

// Atualizar uma Marca existente
export const updateMarca = async (id, marca) => {
  try {
    const response = await api.put(`/api/v1/marca/${id}`, marca);
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar marca:", error);
    throw error;
  }
};

// Deletar uma Marca
export const deleteMarca = async (id) => {
  try {
    await api.delete(`/api/v1/marca/${id}`);
  } catch (error) {
    console.error("Erro ao excluir marca:", error);
    throw error;
  }
};

// Funções de Categorias

// Carregar todas as Categorias
export const getCategorias = async () => {
  try {
    const response = await api.get('/api/v1/categoria');
    return response.data;
  } catch (error) {
    console.error("Erro ao carregar as categorias:", error);
    throw error;
  }
};

// Adicionar uma nova Categoria
export const addCategoria = async (categoria) => {
  try {
    const response = await api.post('/api/v1/categoria', categoria);
    return response.data;
  } catch (error) {
    console.error("Erro ao adicionar categoria:", error);
    throw error;
  }
};

// Atualizar uma Categoria existente
export const updateCategoria = async (id, categoria) => {
  try {
    const response = await api.put(`/api/v1/categoria/${id}`, categoria);
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar categoria:", error);
    throw error;
  }
};

// Deletar uma Categoria
export const deleteCategoria = async (id) => {
  try {
    await api.delete(`/api/v1/categoria/${id}`);
  } catch (error) {
    console.error("Erro ao excluir categoria:", error);
    throw error;
  }
};
