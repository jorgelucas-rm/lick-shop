<template>
  <footer class="footerDown">
    <div class="container-fluid">
      <div class="row w-100 align-items-center">
        <!-- LICKSHOP LTDA e Linha Divisória -->
        <div class="col-12 col-md-auto text-center text-md-start d-flex align-items-center">
          <div class="text-wrapper">LICKSHOP LTDA</div>
          <div class="whiteline"></div>
          <div class="text-wrapper-2">©2024-2024</div>
        </div>

        <!-- Links Condições de Uso -->
        <div class="col-12 col-md-auto text-center text-md-start">
          <p class="condi-es-de-uso">
            <router-link to="/useconditions" class="links">Condições de Uso</router-link>
            <router-link to="/privacy" class="links">Aviso de Privacidade</router-link>
            <router-link to="/cookies" class="links">Aviso sobre cookies</router-link>
            <router-link to="/interestbasedads" class="links">Anúncios baseados em interesses</router-link>
          </p>
        </div>

        <!-- Logo -->
        <div class="col-12 col-md-auto text-center text-md-end">
          <img class="block" alt="logo" src="@/assets/nome.png" />
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "FooterDown",
};
</script>

<style scoped>
/* Footer */
.footerDown {
  background-color: #000000;
  border-top: #ff0000 solid 0.5px;
  padding: 10px 0;
}

.text-wrapper {
  color: #ffffff;
  font-family: "Inter-Bold", Helvetica;
  font-size: 11.3px;
  font-weight: 700;
  letter-spacing: 0;
  line-height: 11.3px;
}

.text-wrapper-2 {
  color: #ffffff;
  font-family: "Inter-Regular", Helvetica;
  font-size: 8.5px;
  font-weight: 400;
  letter-spacing: 0;
  line-height: 8.5px;
}

.condi-es-de-uso {
  color: #ffffff;
  font-family: "Inter-Regular", Helvetica;
  font-size: 8.5px;
  font-weight: 400;
  letter-spacing: 0;
  line-height: 8.5px;
}

.links {
  color: #ff0000;
  text-decoration: underline;
  cursor: pointer;
  font-size: 8.5px;
  margin-right: 10px;
}

.whiteline {
  width: 1px;
  height: 40px;
  background: #ffffff;
  margin: 0 10px;
}

.block {
  height: 15px;
  object-fit: cover;
  width: 100px;
}

@media (max-width: 768px) {
  .footerDown .text-wrapper, .footerDown .text-wrapper-2, .footerDown .condi-es-de-uso {
    font-size: 10px;
  }

  .footerDown .links {
    font-size: 10px;
  }

  .footerDown .row {
    flex-direction: column;
    text-align: center;
  }

  .footerDown .whiteline {
    display: none;
  }

  .footerDown .col-12 {
    width: 100%;
  }

  .footerDown .text-center {
    text-align: center;
  }
}

.footerDown .row {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
</style>
