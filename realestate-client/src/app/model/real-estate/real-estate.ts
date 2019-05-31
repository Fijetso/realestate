import { Address } from './../address/address';
import { User } from './../user/user';
import { RealEstateKind } from '../real-estate-kind/real-estate-kind';
import { TradeKind } from 'src/app/model/trade-kind/trade-kind';
import { RealEstateDetail } from '../real-estate-detail/real-estate-detail';
import { Image } from '../image/image';
import { Booking } from '../booking/booking';
export class RealEstate {
    id: number;
    description: string;
    cost: number;
    user: User;
    tradeKind: TradeKind;
    realEstateKind: RealEstateKind;
    address: Address;
    details: RealEstateDetail;
    realImages: Image[];
    bluePrints: Image[];
    booking: Booking[];
}
