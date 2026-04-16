import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReportService } from '../services/report/report.service';

@Component({
  selector: 'app-reportform',
  templateUrl: './reportform.component.html',
  styleUrls: ['./reportform.component.css']
})
export class ReportformComponent {
  userId="14526426456" //localStorage.getItem('loggedUserId');
  raison:string=''
  @Input() eventTopicId!:string |null;
  @Input() commentId!:string |null;
@Output() formSubmitted= new EventEmitter();
  constructor(private reportService:ReportService){

  }
  onSubmit(){
    let data:any={
      userId:this.userId,
      raison:this.raison
    }
    if(this.eventTopicId!=null){
       data["eventTopicId"]=this.eventTopicId;
    }else if(this.commentId!=null){
      data["commentId"]=this.commentId;
    }
    this.reportService.addReport(data).subscribe(()=>this.formSubmitted.emit())
  }

}
