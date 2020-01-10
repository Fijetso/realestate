import gql from 'graphql-tag';

export const LOGIN_MUTATION = gql`mutation LoginMutation($email: String!, $password: String!) {
  login(email: $email, password: $password)
}`;
export interface LoginMutationResponse {
  login: string;
}

export const GET_ALL_TRADE_QUERY = gql`{trades(count:100){
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

export const UPDATE_TRADE = gql`mutation UpdateTradeMutation($tradeId:Int!, $cost:Long!){
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

export const REGISTER_MUTATION = gql`mutation register($name:String!,$email:String!,$password:String!,$job: String, $phone: String!){
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


export const SAVENEWS_MUTATION = gql`mutation saveNews($title:String!,$composeDate:String!,$content:String!,$categoryId:Long,$author:String){
  saveNews(title:$title, composeDate:$composeDate, content:$content, categoryId:$categoryId,author:$author){
    id,
    title,
    content,
    composeDate
  }
}
`;
export interface SaveNewsMutationResponse {
  saveNews: any;
}

export const SAVEBOOKING_MUTATION = gql`mutation saveBooking($name: String, $phone: String!, $email: String!, $timeStart: String, $timeEnd: String, $tradeId: Long!){
  saveBooking(name:$name, phone:$phone, email:$email, timeStart:$timeStart,timeEnd:$timeEnd,tradeId:$tradeId){
    name,
    phone,
    email,
    timeStart,
    timeEnd,
    trade{
    id
    }
  }
}
`;
export interface SaveTradeMutationRespone {
  saveTrade: any;
}
export const SAVETRADE_MUTATION = gql`mutation saveTrade($description: String, $cost: Long, $userId: Long, $realEstateKindId: Long, $tradeKindId: Long,
  $detailAddress: String, $wardId: Long, $length: Float, $width: Float, $square: Float, $direction: String,
  $floors: String, $legalDocuments: String, $bathrooms: Int, $bedrooms: Int, $utilities: String,
  $others: String, $longitude: Float, $latitude: Float, $realImages: [String]){
  saveTrade(description: $description, cost: $cost,userId: $userId, realEstateKindId: $realEstateKindId, tradeKindId: $tradeKindId,
    detailAddress: $detailAddress, wardId: $wardId, length: $length, width: $width, square :$square, direction: $direction,
    floors :$floors, legalDocuments : $legalDocuments,bathrooms: $bathrooms, bedrooms :$bedrooms, utilities : $utilities,
    others : $others, longitude : $longitude,latitude : $latitude,realImages :$realImages){
    id,
    description,
    tradeStatus,
    coordinate{
      longitude,
      latitude
    }
  }
}
`;
export interface SaveBookingMutationResponse {
  saveBooking: any;
}

export const UPDATEUSER_MUTATION = gql`mutation updateUser($userId: Long!,$job: String,$phone: String, $gender: Boolean){
  updateUser(userId:$userId,job: $job, phone :$phone, gender : $gender){
    id,
    name,
    job,
    phone,
    gender,
    roles {
        id,
        name
    }
  }
}
`;
export interface UpdateUserMutationResponse {
  updateUser: any;
}

