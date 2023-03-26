export interface IUser {
  id?: string;
  username?: string;
  fullName?: string;
  email?: string;
  phoneNumber?: string;
  activated?: boolean;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}

export class User implements IUser {
  id?: string;
  username?: string;
  fullName?: string;
  email?: string;
  phoneNumber?: string;
  activated?: boolean;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
}
