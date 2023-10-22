import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { GestionUtilisateurs } from '../model/GestionUtilisateurs';
import {Rol} from "../model/Rol";

@Injectable({
  providedIn: 'root'
})
export class GestionUtilisateursService {
  constructor(private http: HttpClient) { }
  findAll() {
    return this.http.get<GestionUtilisateurs[]>(environment.apiURL + `/api/auth/users`);
  }
  findRoles() {
    return this.http.get<Rol[]>(environment.apiURL + `/api/auth/roles`);
  }
  deleteUser(gestionUtilisateurs: GestionUtilisateurs) {
    const headers = new HttpHeaders().set("Access-Control-Allow-Origin", "*");
    return this.http.put<GestionUtilisateurs[]>(environment.apiURL + '/api/auth/signdel', gestionUtilisateurs, { headers });
  }
  update(gestionUtilisateurs: GestionUtilisateurs) {
    const headers = new HttpHeaders()
      .set("Access-Control-Allow-Origin", "*");
    return this.http.put<GestionUtilisateurs[]>(environment.apiURL + '/api/auth/signput', gestionUtilisateurs, { headers });
  }

}
