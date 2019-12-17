import { environment } from './../../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { Apollo } from 'apollo-angular';
import { HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { LOGIN_MUTATION,LoginMutationResponse } from 'src/app/model/generated/graphql';
@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {
  constructor(private apollo: Apollo, private httpLink: HttpLink) {
    this.apollo.create({
      link: this.httpLink.create({uri:'http://localhost:8081/graphql'}),
      cache: new InMemoryCache()
    })
   }

  //  login by email password
  login=(emailInput,passwordInput): void => {
    console.info(emailInput,passwordInput);
    this.apollo.mutate<LoginMutationResponse>({
      mutation: LOGIN_MUTATION,
      variables:{
        email: emailInput,
        password: passwordInput
      }
    }).subscribe(response => {
      console.info(response.data)
    })
  }
}
