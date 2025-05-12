import { Component, OnInit, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { ApiService } from '../services/PredictionService/api.service';
import { FormBuilder, FormGroup } from '@angular/forms';

interface ChatMessage {
  text: string;
  sender: 'user' | 'bot';
}

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.css']
})
export class PredictionComponent implements OnInit, AfterViewChecked {
  chatMessages: ChatMessage[] = [];
  userInput: string = '';
  predictionResult: any;
  chatContext: any = {};
  isLoading: boolean = false;

  @ViewChild('chatContainer') private chatContainer!: ElementRef;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    // Start the conversation
    this.addBotMessage("Bonjour! Je vais vous aider à estimer le niveau de difficulté d'un cours. Quel est le nom du cours?");
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    try {
      this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }

  addBotMessage(text: string): void {
    this.chatMessages.push({ text, sender: 'bot' });
  }

  sendMessage(): void {
    if (!this.userInput.trim()) return;

    // Add user message to chat
    this.chatMessages.push({ text: this.userInput, sender: 'user' });
    const message = this.userInput;
    this.userInput = '';
    this.isLoading = true;

    // Send to API
    this.apiService.chatWithBot(message, this.chatContext).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.chatContext = response.context || {};
        
        if (response.reply) {
          this.addBotMessage(response.reply);
        }

        if (response.predictedDifficulty) {
          this.predictionResult = {
            predicted_difficulty_level: response.predictedDifficulty
          };
        }
      },
      error: (err) => {
        this.isLoading = false;
        this.addBotMessage("Désolé, une erreur s'est produite. Veuillez réessayer.");
        console.error('Chat error:', err);
      }
    });
  }
}