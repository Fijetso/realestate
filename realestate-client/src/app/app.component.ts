import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private _titleService: Title, private router: Router) {}
  title = 'Real Estate Client';
  ngOnInit(): void {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
          return;
      }
      window.scrollTo(0, 0);
      // this._titleService.setTitle('Real Estate Client');
  });
  }
}
