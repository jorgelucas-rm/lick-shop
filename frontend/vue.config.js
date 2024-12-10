const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9922, // Define a porta do servidor de desenvolvimento
    proxy: {
      '/api': {
        target: 'https://api.lickshop.acilab.com.br',
        changeOrigin: true,
        secure: false,
        logLevel: 'debug' // Mostra logs detalhados no console
      }
    }
  },
})
