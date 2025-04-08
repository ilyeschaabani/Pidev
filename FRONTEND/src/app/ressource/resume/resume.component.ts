import { Component, Input, OnInit } from '@angular/core';
import { RessourceService } from '../../ServicesRessource/ressource-service.service';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {
  @Input() fileName!:String
  resume=""
constructor(private resourceService:RessourceService){

}
  ngOnInit(): void {
    this.resourceService.getResume(this.fileName).subscribe((data)=>{
      this.resume=data.summary;
    })
}
}
