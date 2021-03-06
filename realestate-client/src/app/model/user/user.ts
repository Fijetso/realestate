import { UserKind } from './../user-kind/user-kind';
export class User {
    id: number;
    name: string;
    email: string;
    phone: string;
    password: string;
    birthdate: Date;
    gender: boolean;
    userKind: number;
}
