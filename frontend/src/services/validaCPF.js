export function validaCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');

    if (cpf.length !== 11) return false;

    if (/^(\d)\1{10}$/.test(cpf)) return false;

    let soma = 0;
    let peso = 10;
    for (let i = 0; i < 9; i++) {
      soma += parseInt(cpf.charAt(i)) * peso;
      peso--;
    }
    let primeiroDigito = 11 - (soma % 11);
    if (primeiroDigito === 10 || primeiroDigito === 11) primeiroDigito = 0;
    if (parseInt(cpf.charAt(9)) !== primeiroDigito) return false;

    soma = 0;
    peso = 11;
    for (let i = 0; i < 10; i++) {
      soma += parseInt(cpf.charAt(i)) * peso;
      peso--;
    }
    let segundoDigito = 11 - (soma % 11);
    if (segundoDigito === 10 || segundoDigito === 11) segundoDigito = 0;
    if (parseInt(cpf.charAt(10)) !== segundoDigito) return false;

    return true;
  }
