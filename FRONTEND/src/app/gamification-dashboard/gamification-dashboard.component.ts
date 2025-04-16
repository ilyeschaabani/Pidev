import { Component } from '@angular/core';
import { GamificationService } from '../services/gamification/gamification.service';
import { forkJoin } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../services/Auth/auth.service';
import { QuizResultService } from '../services/questionresult/quiz-result.service';

@Component({
  selector: 'app-gamification-dashboard',
  templateUrl: './gamification-dashboard.component.html',
  styleUrls: ['./gamification-dashboard.component.css']
})
export class GamificationDashboardComponent {
  getScoreClass(arg0: any): string|string[]|Set<string>|{ [klass: string]: any; }|null|undefined {
    throw new Error('Method not implemented.');
    }
    getBadgeIcon(_t71: any): string|string[]|Set<string>|{ [klass: string]: any; }|null|undefined {
    throw new Error('Method not implemented.');
    }
      idUser!: string;
    quizResults: any[] = [];
    userStats: any = {};
    badges: string[] = [];
    isLoading = true;
    currentLevelProgress = 0;
    
    constructor(
      private quizResultService: QuizResultService,
      private gamificationService: GamificationService,
      private authService: AuthService,
      private toastr: ToastrService
    ) {}
    
    ngOnInit(): void {
      this.authService.getCurrentUser().subscribe({
        next: (userId: string) => {
          this.idUser = userId;
          this.loadDashboardData();
        },
        error: () => {
          this.toastr.error('Erreur lors de la récupération de l\'utilisateur');
        }
      });
      this.loadDashboardData();
    }
    
    loadDashboardData(): void {
      this.isLoading = true;
    
      forkJoin([
        this.quizResultService.getUserResults(this.idUser),
        this.gamificationService.getUserStats(this.idUser),
        this.gamificationService.getUserBadges(this.idUser)
      ]).subscribe({
        next: (results) => {
          const [quizResults = [], stats = {}, badges = []] = results;
          this.quizResults = quizResults;
          this.userStats = stats;
          this.badges = badges;
          this.calculateLevelProgress();
          this.isLoading = false;
        },
        error: (err) => {
          this.toastr.error('Erreur lors du chargement des données');
          this.isLoading = false;
        }
      });
    }
    
    calculateLevelProgress(): void {
      if (this.userStats?.totalPoints && this.userStats?.level) {
        this.currentLevelProgress = (this.userStats.totalPoints % 100);
      }
    }
    
    saveQuizResult(evaluationId: string, score: number): void {
      this.quizResultService.saveQuizResult(evaluationId, score).subscribe({
        next: () => {
          this.toastr.success('Résultat enregistré avec succès!');
          this.loadDashboardData(); // Rafraîchir les données
        },
        error: (err) => {
          this.toastr.error("Erreur lors de l'enregistrement");
        }
      });
    }

}
