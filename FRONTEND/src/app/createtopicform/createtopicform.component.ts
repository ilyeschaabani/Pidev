import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventTopicService } from '../services/eventtopic/event-topic.service';
import { Router } from '@angular/router';  // Import Router
import * as L from 'leaflet';  // Import Leaflet
import { FileStorageService } from '../services/fileStorage/file-storage.service';
import { GeminiServiceService } from '../services/gemini/gemini-service.service';
interface Media{
  mediaType:"IMAGE"|"VIDEO",
  media:string
}

@Component({
  selector: 'app-createtopicform',
  templateUrl: './createtopicform.component.html',
  styleUrls: ['./createtopicform.component.css']
})
export class CreatetopicformComponent {
  topicForm: FormGroup;
  userId="14526426456" //localStorage.getItem('loggedUserId');
  map: L.Map | undefined;
  marker: L.Marker | undefined;
  lat = 33.8869;  // Latitude of Tunisia
  lng = 9.5375;  
  fileUpload:Media={
    mediaType:"IMAGE", 
    media:""
  }
  loadingIAtitle=false;
  loadinIAdescprtion=false;
  constructor(private eventTopicService: EventTopicService,private router:Router,private fileStorageService:FileStorageService,private geminiService:GeminiServiceService) {
    this.topicForm = new FormGroup({
      title: new FormControl('', [Validators.required, Validators.minLength(3)]),
      description: new FormControl('', [Validators.required, Validators.minLength(10)]),
      isEvent:new FormControl(false),
      location:new FormControl(''),
      eventDate:new FormControl(null),
      
    });
    this.topicForm.get('isEvent')?.valueChanges.subscribe((isEvent: boolean) => {
      const locationControl = this.topicForm.get('location');
      const eventDateControl = this.topicForm.get('eventDate');
  
      if (isEvent) {
        locationControl?.setValidators([Validators.required]);
        eventDateControl?.setValidators([Validators.required]);
        this.changeMapDisplay("block")
      } else {
        locationControl?.clearValidators();
        eventDateControl?.clearValidators();
        this.changeMapDisplay("none")
      }
  
      locationControl?.updateValueAndValidity();
      eventDateControl?.updateValueAndValidity();
    });
  }
  ngOnInit(): void {
    this.initMap();  // Initialize the map on component load
    this.changeMapDisplay("none");
  }
  initMap(): void {
    this.map = L.map('map').setView([this.lat, this.lng], 5); // Center map

    // Set up OpenStreetMap tiles
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);
    const customIcon = L.icon({
      iconUrl: 'assets/img/icon-map.png',  // Path to your custom icon
      iconSize: [32, 32],  // Custom size
      iconAnchor: [16, 32],  // Icon anchor point
      popupAnchor: [0, -32],  // Popup anchor point
    });
    // Add a draggable marker on the map
    this.marker = L.marker([this.lat, this.lng], { draggable: true,icon:customIcon }).addTo(this.map);

    // Update the location input when the marker is dragged
    this.marker.on('dragend', (event: any) => {
      const position = event.target.getLatLng();
      this.lat = position.lat;
      this.lng = position.lng;
      this.topicForm.get('location')?.setValue(`Lat: ${this.lat}, Lng: ${this.lng}`);
    });
  }
  createTopic(): void {
    if (this.topicForm.valid) {
      let data:any={
        title: this.topicForm.get('title')?.value,
        description: this.topicForm.get('description')?.value,
        userId:this.userId
      }
      if(this.topicForm.get('isEvent')?.value){
        const formattedDate = this.topicForm.get('eventDate')?.value + 'T00:00:00'; // Add time manually if you don't collect it
        data.eventDate = formattedDate;
        data["event"]=true;
        data["location"]=this.topicForm.get('location')?.value;
        data["eventDate"]=formattedDate
      }
      if(this.fileUpload.media!=''){
        
        data={
          ...data,
          ...this.fileUpload,  }
      }
      this.eventTopicService.create(data).subscribe((data)=>{
        if(data){
            this.router.navigate(['/forum']);  // Redirect to /forum/create
        }
      })  
    }
  }
  changeMapDisplay(display:string){
    const mapCont = document.getElementById("mapCont") as HTMLDivElement;

if (mapCont) {
  mapCont.style.display =display;
}
  }
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
     const file = input.files[0];
     const fileType=this.getFileType(file);
   this.fileStorageService.uploadFile(file,fileType).subscribe((data) => {
    this.fileUpload.media=data.fileName;
    this.fileUpload.mediaType=fileType;
  })}
  }

  getFileType(file: File) {
    const mimeType = file.type;

    if (mimeType.startsWith('image/')) {
      return 'IMAGE';
    } else if (mimeType.startsWith('video/')) {
      return 'VIDEO';
    }  
    return 'IMAGE'
  }
  useIA(){
    const title=this.topicForm.get('title')?.value?.trim();
    const description=this.topicForm.get('description')?.value.trim();

    if(title&& title!=''){
      this.loadingIAtitle=true;
      this.geminiService.reformulateText(title,"title of event ").subscribe((data)=>{
        if(data!=null){
          this.topicForm.get('title')?.setValue(data.text);
          this.loadingIAtitle=false;
        }
      })
    }
    if(description&& description!=''){
      this.loadinIAdescprtion=true;
      this.geminiService.reformulateText(description,"description of event ").subscribe((data)=>{
        if(data!=null){
          this.topicForm.get('description')?.setValue(data.text);
          this.loadinIAdescprtion=false;
        }
      })
    }
  }
}
