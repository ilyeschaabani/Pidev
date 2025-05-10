import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators'; // Correct import

interface RequestPayload {
  contents: { parts: { text: string }[] }[];
}

interface ResponsePayload {
  candidates: {
    content: { parts: { text: string }[] };
    finishReason: string;
    avgLogprobs: number;
  }[];
  usageMetadata: {
    promptTokenCount: number;
    candidatesTokenCount: number;
    totalTokenCount: number;
    promptTokensDetails: { modality: string; tokenCount: number }[];
    candidatesTokensDetails: { modality: string; tokenCount: number }[];
  };
  modelVersion: string;
}

interface ExtractedText {
  text: string;
}
@Injectable({
  providedIn: 'root'
})
export class GeminiServiceService {
  key="AIzaSyDWSHJbMuqvJfcV5Xxeb2MPwMWlRRugJlE";
  api=`https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${this.key}`;


  constructor(private http:HttpClient) { 

  }
  reformulateText(inputText: string, context: string): Observable<ExtractedText | null> {
    const payload: RequestPayload = {
      contents: [
        {
          parts: [
            {
              text: `Reformulate the following text and return the result as a JSON object {text: result}, keeping the original language of the input text. Take the context as the subject of reformulation. Context: ${context}. Text: \`${inputText}\``,
            },
          ],
        },
      ],
    };
  
   
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
     });

    return this.http.post<ResponsePayload>(this.api, payload, { headers }).pipe(
      map((response) => {
        if (response && response.candidates && response.candidates.length > 0) {
          const candidate = response.candidates[0];
          const parts = candidate.content.parts;
          if (parts && parts.length > 0) {
            const text = parts[0].text;
            try {
              // Attempt to parse the JSON from the response
              const jsonString = text.substring(text.indexOf('{'), text.lastIndexOf('}') + 1);
              return JSON.parse(jsonString) as ExtractedText;
            } catch (error) {
              console.error('Failed to parse JSON from response:', error);
              return null;
            }
          }
        }
        return null;
      })
    );
  }

}
