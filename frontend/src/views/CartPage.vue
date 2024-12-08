<template>
    <div class="cart-page">
      <h1>Carrinho de Compras</h1>
      <div v-if="cartItems.length === 0">
        <p>Seu carrinho está vazio.</p>
      </div>
      <div v-else>
        <div class="cart-items">
          <CartItem
            v-for="item in cartItems"
            :key="item.id"
            :item="item"
            @remove="removeItemFromCart"
          />
        </div>
        <div class="cart-summary">
          <h2>Resumo do Pedido</h2>
          <p>Total: R$ {{ totalAmount.toFixed(2) }}</p>
          <button @click="checkout">Finalizar Compra</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  </script>
  
  <style scoped>
  .cart-page {
    padding: 20px;
  }
  .cart-items {
    margin-bottom: 20px;
  }
  .cart-summary {
    margin-top: 20px;
  }
  .cart-summary button {
    padding: 10px;
    background-color: #ff4081;
    color: white;
    border: none;
    cursor: pointer;
  }
  .cart-summary button:hover {
    background-color: #e60073;
  }
  </style>
  