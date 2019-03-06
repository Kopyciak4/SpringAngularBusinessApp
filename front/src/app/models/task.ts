import { User } from "./user";

export interface Task {
    taskId: number,
    taskName: string,
    taskDescription: string,
    taskOwner: User,
    }


    