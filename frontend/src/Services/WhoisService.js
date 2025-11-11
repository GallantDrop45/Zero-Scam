import api from './api';

export const whoisService = {
  verificarLink: async (link) => {
    try {
      const response = await api.post('/verificar-link', { link });
      return response.data;
    } catch (error) {
      console.error('Erro ao verificar link:', error);
      throw error;
    }
  }
};