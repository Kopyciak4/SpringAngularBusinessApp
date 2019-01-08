import { User } from "./user";

export interface Account {
    name: string;
    surname: string;
    email: string;
    adress: string;
    accountID: number;
    user: User 
}