import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { ApiService } from 'src/app/services/api/api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-re',
  templateUrl: './delete-re.component.html',
  styleUrls: ['./delete-re.component.scss']
})
export class DeleteReComponent implements OnInit {

  realEstate: any;
  constructor(private api: ApiService, private fb: FormBuilder, private router: Router) { }

  ngOnInit() {
  }


}
