import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { RealEstate } from './../../../model/real-estate/real-estate';
import { ApiService } from './../../../services/api/api.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { State } from '../home-page/marketting/marketting.component';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
 
  reList: RealEstate[];
  constructor(private api: ApiService,private graphql : GraphQueryService) {
    this.graphql.getAllTrade();
  }

  ngOnInit() {
    this.reList= this.api.getAllRE();
  }

  delete(id: any){
    let result = confirm('Do you want delelte it ?');
    if(result){
      this.api.deleteRE(id);
    }
  }
}
