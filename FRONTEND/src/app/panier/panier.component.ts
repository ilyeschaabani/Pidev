import { Component,OnInit } from '@angular/core';
import { PanierService } from '../services/panier/panier.service';
import { Panier } from '../Models/panier.model';
import { loadStripe } from '@stripe/stripe-js';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  panier!: Panier;
  userId = 1; // Hardcoded for now or get from login

  constructor(private panierService: PanierService
    ,private http: HttpClient
  ) {}

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

  
stripePromise = loadStripe('pk_test_51RD85GICeCCeN2gWoRIVNP5ZwD1q7i77XshMoCB00wSN8mZfEHEAbYxR4pCRdeIO1E9p949p8137pWRkKg9zfdnU00cC3WhmnX'); // use your publishable key
checkout(): void {
  this.http.get<Panier>(`http://localhost:8083/api/panier/${this.userId}`).subscribe(panier => {
    this.http.post<any>('http://localhost:8083/checkout', panier).subscribe(async res => {
      const stripe = await this.stripePromise;
      if (stripe) {
        stripe.redirectToCheckout({ sessionId: res.id });
      }
    });
  });

}

}
