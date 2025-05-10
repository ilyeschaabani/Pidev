export interface Project {
    id: string;
    title: string;
    description: string;
    documents: string[];
    evaluations: Evaluation[];
  }
  
  export interface Evaluation {
    mentor: string;
    comment: string;
    grade: number;
  }
  
  export interface TopicGenerationRequest {
    keywords: string;
  }
  
  export interface GeneratedTopic {
    topic: string;
    description: string;
  }