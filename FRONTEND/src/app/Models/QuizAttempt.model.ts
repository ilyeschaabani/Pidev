export interface QuizAttempt {
    id?: string;
    evaluationId: string;
    userId: string;
    answers: {
      questionId: string;
      selectedAnswer: string;
      isCorrect: boolean;
      timeSpent: number; // en secondes
    }[];
    score: number;
    completedAt: Date;
    status: 'in_progress' | 'completed' | 'abandoned';
  }