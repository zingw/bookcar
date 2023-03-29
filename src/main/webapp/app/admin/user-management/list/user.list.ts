import {IUser} from "../user-management.model";

export class UserListResponse {
  content ?: IUser[];
  totalElement ?: number;
}
