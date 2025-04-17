import { Component } from '@angular/core';
import { GamificationService } from '../services/gamification/gamification.service';
import { forkJoin } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../services/Auth/auth.service';
import { QuizResult, QuizResultService } from '../services/questionresult/quiz-result.service';

@Component({
  selector: 'app-gamification-dashboard',
  templateUrl: './gamification-dashboard.component.html',
  styleUrls: ['./gamification-dashboard.component.css']
})
export class GamificationDashboardComponent {
  quizResults: QuizResult[] = [];
  totalPoints: number = 0;
  level: number = 1;
  progression: number = 0;
  badges: string[] = [];
 


  ngOnInit() {
    this.loadQuizResults();
    this.calculateGamification();
  }

  loadQuizResults() {
    const keys = Object.keys(localStorage).filter(key => key.startsWith('score_'));
    this.quizResults = keys.map(key => {
      return {
        id: key, // ou un identifiant unique (à adapter si besoin)
        idEvaluation: key.replace('score_', ''),
        idUser: '1', // ✅ corrigé en string pour correspondre à QuizResult
        quizId: key.replace('score_', ''),
        score: parseInt(localStorage.getItem(key) || '0', 10),
        date: new Date().toISOString() // format ISO standard (optionnel)
      } as QuizResult; // on force le typage si besoin
    });
  }
  

  calculateGamification() {
    this.totalPoints = this.quizResults.reduce((sum: any, result: { score: any; }) => sum + result.score, 0);
    this.level = Math.floor(this.totalPoints / 100) + 1;
    this.progression = this.totalPoints % 100;

    // Attribution des badges
    this.badges = [];
    for (let result of this.quizResults) {
      if (result.score === 100 && !this.badges.includes('🎯 Parfait')) {
        this.badges.push('🎯 Parfait');
      }
    }
    if (this.totalPoints >= 200) this.badges.push('🔥 Expert');
    if (this.totalPoints >= 300) this.badges.push('🏆 Champion');
  }

  resetProgression() {
    for (let key of Object.keys(localStorage)) {
      if (key.startsWith('score_')) {
        localStorage.removeItem(key);
      }
    }
    this.quizResults = [];
    this.totalPoints = 0;
    this.level = 1;
    this.progression = 0;
    this.badges = [];
  }}