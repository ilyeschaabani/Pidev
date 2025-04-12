import { Component, OnInit } from '@angular/core';
import { PanierService } from '../services/panier.service';
import { Panier } from '../models/panier.model';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  panier!: Panier;
  userId = 1; // Hardcoded for now or get from login

  constructor(private panierService: PanierService) {}

  ngOnInit(): void {
    this.loadPanier();

  }

  loadPanier(): void {
    this.panierService.getPanier(this.userId).subscribe(data => {
      this.panier = data;
    });
  }

  removeFormation(idFormation: string): void {
    this.panierService.removeFormation(this.userId, idFormation).subscribe(() => {
      this.loadPanier();
    });
  }

  clearPanier(): void {
    this.panierService.clearPanier(this.userId).subscribe(() => {
      this.loadPanier();
    });
  }
  
  
}
