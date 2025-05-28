import { Component, OnInit } from '@angular/core';
import { ApoliceService } from './apolice.service';
import { Apolice } from './apolice.model';

/**
 * Componente Angular para exibir lista de apólices
 * Este é apenas um exemplo demonstrativo de frontend Angular
 */
@Component({
  selector: 'app-apolice-list',
  templateUrl: './apolice-list.component.html',
  styleUrls: ['./apolice-list.component.css']
})
export class ApoliceListComponent implements OnInit {
  apolices: Apolice[] = [];
  loading = false;
  error: string | null = null;
  tipoSeguros = ['AUTO', 'VIDA', 'RESIDENCIAL', 'EMPRESARIAL', 'SAUDE', 'VIAGEM'];
  filtroTipo: string | null = null;

  constructor(private apoliceService: ApoliceService) { }

  ngOnInit(): void {
    this.carregarApolices();
  }

  carregarApolices(): void {
    this.loading = true;
    this.error = null;

    this.apoliceService.listarApolices()
      .subscribe(
        (data) => {
          this.apolices = data;
          this.loading = false;
        },
        (error) => {
          this.error = 'Erro ao carregar apólices: ' + error.message;
          this.loading = false;
        }
      );
  }

  filtrarPorTipo(): void {
    if (!this.filtroTipo) {
      this.carregarApolices();
      return;
    }

    this.loading = true;
    this.error = null;

    this.apoliceService.buscarPorTipo(this.filtroTipo)
      .subscribe(
        (data) => {
          this.apolices = data;
          this.loading = false;
        },
        (error) => {
          this.error = 'Erro ao filtrar apólices: ' + error.message;
          this.loading = false;
        }
      );
  }

  cancelarApolice(id: number): void {
    if (confirm('Tem certeza que deseja cancelar esta apólice?')) {
      this.apoliceService.cancelarApolice(id)
        .subscribe(
          () => {
            this.carregarApolices();
            alert('Apólice cancelada com sucesso!');
          },
          (error) => {
            this.error = 'Erro ao cancelar apólice: ' + error.message;
          }
        );
    }
  }
} 