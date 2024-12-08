export function validaCPF(cpf) {
    // Remove caracteres não numéricos
    cpf = cpf.replace(/[^\d]+/g, '');
  
    // Verifica se o CPF tem 11 caracteres
    if (cpf.length !== 11) return false;
  
    // Verifica se todos os números são iguais
    if (/^(\d)\1{10}$/.test(cpf)) return false;
  
    // Validação do primeiro dígito
    let soma = 0;
    let peso = 10;
    for (let i = 0; i < 9; i++) {
      soma += parseInt(cpf.charAt(i)) * peso;
      peso--;
    }
    let primeiroDigito = 11 - (soma % 11);
    if (primeiroDigito === 10 || primeiroDigito === 11) primeiroDigito = 0;
    if (parseInt(cpf.charAt(9)) !== primeiroDigito) return false;
  
    // Validação do segundo dígito
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
  