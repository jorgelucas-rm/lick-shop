# Usar a imagem oficial do Node.js
FROM node:16-alpine

# Definir o diretório de trabalho no container
WORKDIR /frontend

# Copiar os arquivos do frontend para o container
COPY package*.json ./

# Instalar as dependências do projeto
RUN npm install

# Copiar o restante dos arquivos do frontend
COPY . .

# Construir a aplicação Vue
RUN npm run build

# Usar uma imagem leve para servir a aplicação
FROM nginx:alpine

# Copiar os arquivos construídos para o diretório do Nginx
COPY --from=0 /frontend/dist /usr/share/nginx/html

# Expor a porta onde a aplicação Vue vai rodar
EXPOSE 80

# Iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]