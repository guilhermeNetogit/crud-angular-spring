import { Contato } from "./contato";

export interface Parceiro {
  id: number;
  position: number;
  name: string;
  weight: number;
  symbol: string;
  contatos?: Contato[];
}
