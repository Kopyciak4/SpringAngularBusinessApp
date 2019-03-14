import { Task } from "./task";
import { Role } from "./role";

export interface User {
    login: string;
    password: string;
    userID: number;
    tasks: Task[];
}
