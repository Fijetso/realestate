import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { RealEstate } from './../../../model/real-estate/real-estate';
import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  reList: RealEstate[];
  public editorValue = '';
  editNews: any;
  startDate = new Date();
  constructor(private api: ApiService, private graphql: GraphQueryService, private fb: FormBuilder) {
    // this.graphql.getAllTrade();
    this.editNews = this.fb.group({
      title: '',
      composteDate: new Date(),
      category: '1',
      editorValue: 'Nội dung bài viết'
    });
  }

  ngOnInit() {
    // this.reList= this.api.getAllRE();
  }

  // delete(id: any){
  //   let result = confirm('Do you want delelte it ?');
  //   if(result){
  //     this.api.deleteRE(id);
  //   }
  // }

  onSubmit() {
    console.log(this.editNews.value);
  }
}
