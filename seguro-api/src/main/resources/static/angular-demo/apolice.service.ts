import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Apolice } from './apolice.model';

/**
 * Serviço Angular para comunicação com a API de Apólices
 */
@Injectable({
  providedIn: 'root'
})
export class ApoliceService {
  private apiUrl = '/api/apolices';

  constructor(private http: HttpClient) { }

  /**
   * Listar todas as apólices
   */
  listarApolices(): Observable<Apolice[]> {
    return this.http.get<Apolice[]>(this.apiUrl);
  }

  /**
   * Buscar apólice por ID
   */
  buscarPorId(id: number): Observable<Apolice> {
    return this.http.get<Apolice>(`${this.apiUrl}/${id}`);
  }

  /**
   * Buscar apólices por tipo de seguro
   */
  buscarPorTipo(tipo: string): Observable<Apolice[]> {
    return this.http.get<Apolice[]>(`${this.apiUrl}/tipo/${tipo}`);
  }

  /**
   * Buscar apólices vigentes
   */
  buscarVigentes(): Observable<Apolice[]> {
    return this.http.get<Apolice[]>(`${this.apiUrl}/vigentes`);
  }

  /**
   * Buscar apólices a vencer em 30 dias
   */
  buscarAVencer(): Observable<Apolice[]> {
    return this.http.get<Apolice[]>(`${this.apiUrl}/a-vencer`);
  }

  /**
   * Criar nova apólice
   */
  criarApolice(apolice: Apolice): Observable<Apolice> {
    return this.http.post<Apolice>(this.apiUrl, apolice);
  }

  /**
   * Atualizar apólice existente
   */
  atualizarApolice(id: number, apolice: Apolice): Observable<Apolice> {
    return this.http.put<Apolice>(`${this.apiUrl}/${id}`, apolice);
  }

  /**
   * Cancelar apólice
   */
  cancelarApolice(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
} 