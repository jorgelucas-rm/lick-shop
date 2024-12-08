const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9922, // Define a porta do servidor de desenvolvimento
    proxy: {
      '/api': {
        target: 'backend:8080', // O endereço da sua API
        changeOrigin: true, // Muda a origem da requisição para evitar CORS
        pathRewrite: {
          '^/api': '', // Remove '/api' do início da URL da requisição
        },
      },
    },
  },
})
