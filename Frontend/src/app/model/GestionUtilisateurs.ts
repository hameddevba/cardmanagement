import {Rol} from "./Rol";

export interface GestionUtilisateurs {
  username: string,
  email: string,
  password: string,
  roles: Rol[]
}
