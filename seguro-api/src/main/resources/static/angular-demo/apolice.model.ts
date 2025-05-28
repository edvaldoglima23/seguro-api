/**
 * Interface que representa uma Apólice no frontend
 */
export interface Apolice {
    id: number;
    numeroApolice: string;
    tipoSeguro: string;
    nomeSegurado: string;
    documentoSegurado: string;
    dataInicioVigencia: string;
    dataFimVigencia: string;
    valorPremio: number;
    valorCobertura: number;
    ativo: boolean;
} 