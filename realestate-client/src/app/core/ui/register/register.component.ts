import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  minDate = new Date(1900, 0, 1);
  maxDate = new Date(2002, 0, 1);
  constructor() { }

  ngOnInit() {
  }


}
