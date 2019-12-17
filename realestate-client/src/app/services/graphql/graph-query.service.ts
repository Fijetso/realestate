import { Injectable } from '@angular/core';
import { Apollo } from 'apollo-angular';
import { HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { LOGIN_MUTATION,LoginMutationResponse,GET_ALL_TRADE_QUERY,GetAllTradeResponse,UPDATE_TRADE,UpdateTradeResponse } from 'src/app/model/generated/graphql';
import { HttpHeaders, HttpClient} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class GraphQueryService {
  headers: any;
  constructor(private apollo: Apollo, private httpLink: HttpLink, private http: HttpClient) {
    this.apollo.create({
      link: this.httpLink.create({uri:'http://localhost:8081/graphql'}),
      cache: new InMemoryCache()
    })
   }

  //  login by email password
  login=(emailInput,passwordInput) => {
    console.info(emailInput,passwordInput);
    this.apollo.mutate<LoginMutationResponse>({
      mutation: LOGIN_MUTATION,
      variables:{
        email: emailInput,
        password: passwordInput
      }
    }).subscribe(response => {
      console.info(response.data.login);
    })
  }
// get infor login
  getLoginInfo(loginToken:any) {
    console.log(loginToken);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearier '+loginToken
      })
    };
    const result = this.http.get('http://localhost:8081/secured/user/me',httpOptions);
    console.info(result);
    return result;
  }

  // Get All Trade
  getAllTrade() :any{
    this.apollo.watchQuery<GetAllTradeResponse>({
      query: GET_ALL_TRADE_QUERY
    }).valueChanges.subscribe((response) => {
        console.info(response.data);
        return response.data
    })
  }

  // Update Trade
  updateTrade(id,cost): any {
    this.apollo.mutate<UpdateTradeResponse>({
      mutation: UPDATE_TRADE,
      variables : {
        id: id,
        cost: cost
      }
    })
  }
}
