import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-predictsourour',
  templateUrl: './predictsourour.component.html',
  styleUrls: ['./predictsourour.component.css'],
  animations: [
    trigger('fadeIn', [
      state('void', style({ opacity: 0 })),
      transition(':enter', [animate('600ms ease-in')]),
    ])
  ]
})
export class PredictsourourComponent {
  formData = {
    total_posts: null,
    helpful_post: null,
    nice_code_post: null,
    timeonline: null
  };

  result: any = null;

  constructor(private http: HttpClient) {}

  submitForm() {
    this.http.post<any>('http://127.0.0.1:5007/predict', this.formData)
      .subscribe(
        res => {
          this.result = res;
        },
        err => {
          console.error('Erreur de prédiction', err);
        }
      );
  }

}

