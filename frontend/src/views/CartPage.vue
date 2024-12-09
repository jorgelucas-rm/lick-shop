<template>
  <div class="cart-item">
    <p>{{ item.nome }}</p>
    <p>Quantidade: {{ item.quantidade }}</p>
    <!-- Verifica se valorVenda é um número válido antes de usar toFixed -->
    <p>Preço: R$ {{ item.valorVenda && !isNaN(item.valorVenda) ? item.valorVenda.toFixed(2) : 'Indisponível' }}</p>
    <button @click="removeItem">Remover</button>
  </div>
</template>

<script>
export default {
  name: "CartItem",
  props: {
    item: Object,
  },
  methods: {
    removeItem() {
      this.$emit("remove", this.item.id); // Emite o ID do item para o componente pai
    },
  },
};
</script>

<style scoped>
.cart-item {
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
}
.cart-item button {
  background-color: #ff4081;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}
.cart-item button:hover {
  background-color: #e60073;
}
</style>
