import { User } from './../../model/user/user';
import { environment } from './../../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { Apollo } from 'apollo-angular';
import { HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import gql from 'graphql-tag';
@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {

  user : User;
  constructor(private apollo: Apollo, private httpLink: HttpLink) {
    this.apollo.create({
      link: this.httpLink.create({uri:environment.api.rootURL+'/graphql'}),
      cache: new InMemoryCache()
    })
   }

  //  login by email password
  login=(user): any => {
    this.apollo.query({
      query: gql`query login{
        ${user.email},
        ${user.password}
      }`
    }).subscribe(result => {
        this.user = result.data as User;
        console.info(this.user);
        return this.user;
    })
  } 
}
