import gql from 'graphql-tag';

export const LOGIN_MUTATION = gql `mutation LoginMutation($email: String!, $password: String!) {
  login(email: $email, password: $password)
}`;
export interface LoginMutationResponse {
    login: string;
}

export const GET_ALL_TRADE_QUERY = gql `{trades(count:100){
    description,
    cost,
    tradeStatus,
    tradeKind{
      name
    },
    realEstateKind{
      name
    },
    address,
    details{
      length,
      width,
      square
    },
    coordinate{
      longitude,
      latitude
    },
    realImages{
      imageLink
    }
  }
}`;
export interface GetAllTradeResponse {
    trades: any;
}

export const UPDATE_TRADE = gql `mutation UpdateTradeMutation($tradeId:Int!, $cost:Long!){
  updateTrade(tradeId:$tradeId, cost: $cost){
   id,
    tradeStatus,
    details{
      id,
      bathrooms
    },
    tradeKind{
      id,
	  name
    }
  }
}
`;
export interface UpdateTradeResponse {
  updateTrade: any;
}

export const REGISTER_MUTATION = gql `mutation register($name:String!,$email:String!,$password:String!,$job: String, $phone: String!){
  register(name:$name,email: $email, password: $password,job: $job, phone: $phone){
    name,
    email,
    phone,
    job
  }
}
`;
export interface RegisterMutationResponse {
  register: any;
}
