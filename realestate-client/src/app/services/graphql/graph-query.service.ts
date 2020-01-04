import { environment } from './../../../environments/environment';
import { RegisterMutationResponse, REGISTER_MUTATION, SAVENEWS_MUTATION, SaveNewsMutationResponse } from './../../model/generated/graphql';
import { Injectable } from '@angular/core';
import { Apollo } from 'apollo-angular';
import { HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { LOGIN_MUTATION, LoginMutationResponse, GET_ALL_TRADE_QUERY, GetAllTradeResponse, UPDATE_TRADE, UpdateTradeResponse } from 'src/app/model/generated/graphql';
import { HttpHeaders, HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {
  headers: any;
  constructor(private apollo: Apollo, private httpLink: HttpLink, private http: HttpClient) {
    this.apollo.create({
      link: this.httpLink.create({ uri: 'http://localhost:8081/graphql' }),
      cache: new InMemoryCache()
    });
  }

  //  login by email password
  login = (emailInput, passwordInput): any => {
    // console.log(emailInput, passwordInput);
    this.apollo.mutate<LoginMutationResponse>({
      mutation: LOGIN_MUTATION,
      variables: {
        email: emailInput,
        password: passwordInput
      }
    }).subscribe(response => {
      const data = response.data.login;
      if (data) {
        // console.log('SET-TOKEN', data);
        localStorage.setItem('login', data);
        return response && data;
      }
    }, error => {
      // console.error('Login failed', error);
      return error;
    });
  }
  // get infor login
  getLoginInfo(loginToken: any) {
    console.log('GET-TOKEN', loginToken);
    const reqHearder = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + loginToken
      }),
    };
    // console.log(reqHearder);
    const result = this.http.get<any>('http://localhost:8081/secured/user/me', reqHearder);
    return result;
  }

  // Get All Trade
  getAllTrade(): any {
    this.apollo.watchQuery<GetAllTradeResponse>({
      query: GET_ALL_TRADE_QUERY
    }).valueChanges.subscribe((response) => {
      // console.log(response && response.data);
      return response && response.data;
    }, error => {
      console.error('Get all trade grapql ' + error);
    });
  }

  // Update Trade
  updateTrade(id, cost): any {
    this.apollo.mutate<UpdateTradeResponse>({
      mutation: UPDATE_TRADE,
      variables: {
        id,
        cost
      }
    });
  }

  logout(): any {
    const reqHearder = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };
    return this.http.post<any>('http://localhost:8081/logout', reqHearder);
  }

  register(name, email, password, phone, job): any {
    return this.apollo.mutate<RegisterMutationResponse>({
      mutation: REGISTER_MUTATION,
      variables: {
        name,
        email,
        password,
        phone,
        job
      }
    }).subscribe(res => {
      // console.log('Register', res.data);
      return res && res.data;
    }, error => {
      // console.error(error)
      return error;
    });
  }


  saveNews(title, composeDate, content, categoryId, author): any {
    return this.apollo.mutate<SaveNewsMutationResponse>({
      mutation: SAVENEWS_MUTATION,
      variables: {
        title,
        composeDate,
        content,
        categoryId,
        author
      }
    });
  }
}
