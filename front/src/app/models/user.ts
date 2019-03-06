import { Task } from "./task";

export interface User {
login: string;
password: string;
userID: number;
tasks: Task[];
}
