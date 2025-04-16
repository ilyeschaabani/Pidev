import { Component } from '@angular/core';
import { Formation, CategoryResource } from '../Models/formations.model';
import { FormationsService } from '../services/Formations/formations.service';
import { Router } from '@angular/router';
import { PanierService } from '../services/panier/panier.service'; // adjust path if needed

@Component({
  selector: 'app-shop-formation',
  templateUrl: './shop-formation.component.html',
  styleUrls: ['./shop-formation.component.css']
})
export class ShopFormationComponent {
  formations: Formation[] = [];
  categories = Object.values(CategoryResource);
  userId: number = 1;

  constructor(private formationService: FormationsService,
    private panierService: PanierService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formationService.getAllFormations().subscribe(data => {
      this.formations = data;
    });
  }
 
  goToCart(): void {
    this.router.navigate(['/panier']);
  }

  filteredFormations(cat: string): Formation[] {
    return this.formations.filter(f => f.categorie === cat);
  }
  buyFormation(formation: Formation): void {
    // You can later integrate payment gateway or modal
    console.log('Buying formation:', formation.titreFormation);
    alert(`Formation "${formation.titreFormation}" added to cart!`);
  }

  addToCart(userId: number, formationId: string) {
    this.panierService.addFormation(userId, formationId).subscribe({
      next: (res) => {
        console.log('Added to cart:', res);
        alert('Formation added to cart!');
      },
      error: (err) => {
        console.error('Error adding to cart:', err);
        alert('Failed to add formation to cart.');
      }
    });
  }

}
