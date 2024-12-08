<template>
    <div class="cart-item">
      <img :src="item.image" alt="Produto" class="cart-item-image" />
      <div class="cart-item-details">
        <h3>{{ item.nome }}</h3>
        <p>Preço: R$ {{ item.preco.toFixed(2) }}</p>
        <p>Quantidade: {{ item.quantidade }}</p>
        <button @click="removeItem">Remover</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      item: Object,
    },
    methods: {
      removeItem() {
        this.$emit("remove", this.item);
      },
    },
  };
  </script>
  
  <style scoped>
  .cart-item {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
  .cart-item-image {
    width: 100px;
    height: 100px;
    object-fit: cover;
    margin-right: 20px;
  }
  .cart-item-details {
    display: flex;
    flex-direction: column;
  }
  .cart-item-details h3 {
    margin: 0;
  }
  .cart-item-details button {
    margin-top: 10px;
  }
  </style>
  