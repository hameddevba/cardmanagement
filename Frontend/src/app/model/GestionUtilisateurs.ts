import {Role} from "./Role";

export interface GestionUtilisateurs {
  username: string,
  email: string,
  password: string,
  roles: Role[]
}
