export interface Produto {
  codprod: number;
  descrprod: string;
  compldesc: string;
  codvol: string;
  eangtin: string;
  referencia: string;
}

export interface ProdutoPage {
  produtos: Produto[];
  totalProdutos: number;
  totalPages: number;
}
