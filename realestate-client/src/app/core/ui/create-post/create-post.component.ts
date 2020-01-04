import { GraphQueryService } from './../../../services/graphql/graph-query.service';
import { RealEstate } from './../../../model/real-estate/real-estate';
import { ApiService } from './../../../services/api/api.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';


const categoryList = [
  {
    id: 1,
    name: '360 Bất động sản'
  },
  {
    id: 2,
    name: 'Tin thị trường'
  },
  {
    id: 3,
    name: 'Truyền thông'
  },
  {
    id: 4,
    name: 'Phong thuỷ'
  },
  {
    id: 5,
    name: 'Kiến trúc'
  },
  {
    id: 6,
    name: 'Kinh nghiệm'
  }

];

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
  categoryList: any;
  constructor(private api: ApiService, private graphql: GraphQueryService, private fb: FormBuilder, private toastr: ToastrService) {
    this.categoryList = categoryList;
    this.editNews = this.fb.group({
      title: 'Tiêu đề',
      author: 'Danh Thanh',
      composeDate: new Date(),
      category: 1,
      editorValue: 'Nội dung bài viết'
    });
  }

  ngOnInit() {
  }
  onSubmit() {
    console.log(this.editNews.value);
    this.saveNews();
  }
  saveNews() {
    const title = this.editNews.get('title').value;
    const composeDate = this.editNews.get('composeDate').value.toLocaleString();
    const content = this.editNews.get('editorValue').value;
    const categoryId = this.editNews.get('category').value;
    const author = this.editNews.get('author').value;

    console.log(this.editNews.get('composeDate').value.toLocaleString());
    this.graphql.saveNews(title, composeDate, content, categoryId, author).subscribe(res => {
      console.log(res);
      this.toastr.success(res.data.saveNews.title, 'Tạo bài đăng thành công');
      return res && res.data;
    }, error => {
      this.toastr.error(error, 'Tạo bài đăng thất bại');
      console.error(error);
      return error;
    });
  }
}
