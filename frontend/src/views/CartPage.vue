<template>
  <div class="cart-item bg-white shadow-md rounded-lg p-4 flex flex-col md:flex-row items-center gap-4">
    <div class="relative w-full md:w-1/4 max-w-[150px] group">
      <img
        :src="item.imageUrl || 'https://via.placeholder.com/150'"
        alt="Product Image"
        class="w-full h-auto object-cover rounded-md"
        @mousemove="handleMouseMove"
        @mouseenter="handleMouseEnter"
        @mouseleave="handleMouseLeave"
      />
      <div
        v-if="isZoomActive"
        class="zoom-lens absolute bg-gray-200 opacity-50 pointer-events-none"
        :style="lensStyle"
      ></div>
      <div
        v-if="isZoomActive"
        class="zoom-result absolute bg-white border border-gray-300 shadow-lg"
        :style="resultStyle"
      ></div>
    </div>

    <div class="flex-1">
      <h3 class="text-lg font-semibold text-gray-800">{{ item.nome }}</h3>
      <p class="text-sm text-gray-600">Quantidade: {{ item.quantidade }}</p>
      <p class="text-sm text-gray-600">
        Preço: R$ {{ item.valorVenda && !isNaN(item.valorVenda) ? item.valorVenda.toFixed(2) : 'Indisponível' }}
      </p>
    </div>

    <button
      @click="removeItem"
      class="bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700 transition-colors duration-200"
    >
      Remover
    </button>
  </div>
</template>

<script>
export default {
  name: "CartItem",
  props: {
    item: {
      type: Object,
      required: true,
      default: () => ({
        id: null,
        nome: '',
        quantidade: 1,
        valorVenda: 0,
        imageUrl: ''
      })
    },
  },
  data() {
    return {
      isZoomActive: false,
      lensStyle: {},
      resultStyle: {},
      zoomLevel: 2,
    };
  },
  methods: {
    removeItem() {
      this.$emit("remove", this.item.id);
    },
    handleMouseEnter(event) {
      this.isZoomActive = true;
      this.setupZoom(event);
    },
    handleMouseLeave() {
      this.isZoomActive = false;
    },
    handleMouseMove(event) {
      if (!this.isZoomActive) return;

      const img = event.target;
      const rect = img.getBoundingClientRect();
      const lensSize = 100;
      const zoomResultSize = 300;

      let x = event.clientX - rect.left;
      let y = event.clientY - rect.top;

      x = Math.max(lensSize / 2, Math.min(x, rect.width - lensSize / 2));
      y = Math.max(lensSize / 2, Math.min(y, rect.height - lensSize / 2));

      this.lensStyle = {
        width: `${lensSize}px`,
        height: `${lensSize}px`,
        top: `${y - lensSize / 2}px`,
        left: `${x - lensSize / 2}px`,
      };

      const bgX = -((x - lensSize / 2) * this.zoomLevel);
      const bgY = -((y - lensSize / 2) * this.zoomLevel);

      this.resultStyle = {
        width: `${zoomResultSize}px`,
        height: `${zoomResultSize}px`,
        backgroundImage: `url(${this.item.imageUrl || 'https://via.placeholder.com/150'})`,
        backgroundSize: `${rect.width * this.zoomLevel}px ${rect.height * this.zoomLevel}px`,
        backgroundPosition: `${bgX}px ${bgY}px`,
        top: '0',
        left: `${rect.width + 10}px`,
      };
    },
    setupZoom(event) {
      const img = event.target;
      const rect = img.getBoundingClientRect();
      this.resultStyle.backgroundImage = `url(${this.item.imageUrl || 'https://via.placeholder.com/150'})`;
      this.resultStyle.backgroundSize = `${rect.width * this.zoomLevel}px ${rect.height * this.zoomLevel}px`;
    },
  },
};
</script>

<style scoped>
.cart-item {
  transition: all 0.3s ease;
}

.cart-item img {
  max-height: 150px;
}

.zoom-lens {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 2px solid #ccc;
}

.zoom-result {
  width: 300px;
  height: 300px;
  z-index: 10;
}

@media (max-width: 768px) {
  .zoom-result {
    display: none;
  }
  .zoom-lens {
    display: none;
  }
}
</style>