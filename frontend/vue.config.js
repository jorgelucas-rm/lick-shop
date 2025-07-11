const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9922, // Define a porta do servidor de desenvolvimento
    proxy: {
      '/api': {
        target: 'https://207.244.237.78:9921', // O endereço da sua API
        changeOrigin: true, // Muda a origem da requisição para evitar CORS
        pathRewrite: {
          '^/api': '', // Remove '/api' do início da URL da requisição
        },
      },
    },

  },
})
