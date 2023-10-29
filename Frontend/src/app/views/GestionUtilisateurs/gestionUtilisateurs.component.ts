import { Component, OnInit } from '@angular/core';

import { GestionUtilisateurs } from '../../model/GestionUtilisateurs';
import { GestionUtilisateursService } from 'src/app/service/gestion-utilisateurs.service';
import { AuthService } from 'src/app/shared/auth.service';
import { REQUIRED, currency_list , roles} from 'src/app/shared/constants';
import { allowNavigation, ruler } from 'src/app/shared/pagination';
import { NumberedPagination } from 'src/app/shared/numbered-pagination.interface';
import {Role} from "../../model/Role";
import { Router } from '@angular/router';

export interface CacheGestionUtilisateurs {
  edit: boolean,
  data: GestionUtilisateurs
}

@Component({
  templateUrl: 'gestionUtilisateurs.component.html',
  styleUrls: ['gestionUtilisateurs.component.scss']
})
export class GestionUtilisateursComponent implements OnInit {
  editCache: { [key: string]: CacheGestionUtilisateurs } = {};
  rapports: GestionUtilisateurs[] = [];
  aideuser: GestionUtilisateurs[] = [];
  role: Role[] = [];
  roles: Role[] = [];
  aiderol: Role[] = [];
  i=0;
  errorVisible: boolean = false;
  sucessVisible: boolean = false;
  sucessModification: boolean = false;
  errorModification: boolean = false;
  hasModificationRights: boolean = false;
  deviseItems = currency_list;
  numPage = 0;
  maxPages = 0;
  pageSize: number = 10;
  rulerLength: number = 5;
  isrole: Boolean=false;
  usernameDelete:string="";
  constructor(private service: GestionUtilisateursService, private authService: AuthService, public router:Router) {
  }
  parseDate(target: any): Date | undefined {
    if (target.value)
      return new Date(target.value);
    return undefined;
  }
  updateEditCache(): void {
    this.rapports.forEach(item => {
      this.editCache[item.username] = {
        edit: false,
        data: { ...item }
      };
    });
  }

  updateEditCachetrue(r: Role, username2: String, ischek: Boolean): void {
    this.rapports.forEach(item => {
        if(ischek) {
          item.roles.push(r);
          const index = item.roles.findIndex(x => x.id === r.id);
          item.roles[index]
        }else {
          const index = item.roles.findIndex(x => x.id === r.id);
          if (index > -1) {
            item.roles.splice(index, 1);
          }
        }
      if (item.username == username2) {
      this.editCache[item.username] = {
        edit: true,
        data: {...item}
      };
    }
    });
  }


  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.rapports.findIndex(item => item.username === id);
    this.editCache[id] = {
      data: { ...this.rapports[index] },
      edit: false
    };
    window.location.reload();
  }

  saveEdit(id: string): void {
    const index = this.rapports.findIndex(item => item.username === id);
    this.service.update(this.editCache[id].data).subscribe((response) => {
      if (response) {
        this.showSuccessModification();
      } else this.showErrorModification();
    },
      (error) => {
        this.showErrorModification();
      }
    );
    Object.assign(this.rapports[index], this.editCache[id].data);
    this.editCache[id].edit = false;
    window.location.reload();
  }

  deleteUser(): void {
    const index = this.rapports.findIndex(item => item.username === this.usernameDelete);
    this.service.deleteUser(this.editCache[this.usernameDelete].data).subscribe((response) => {
        if (response) {
          this.showSuccessModification();
        } else this.showErrorModification();
      },
      (error) => {
        this.showErrorModification();
      }
    );
    Object.assign(this.rapports[index], this.editCache[this.usernameDelete].data);
    this.editCache[this.usernameDelete].edit = false;
    this.visible = !this.visible;
    window.location.reload();
  }

  saveUser(): void {
    this.rapports.push({
      username:"",
      email:"",
      password:"",
    roles:[]
    });
    this.updateEditCache();
    this.editCache[""].edit = true;

  }
  ngOnInit(): void {
    this.hasModificationRights = this.authService.hasModificationRight();
      this.fetchRole();
      this.fetchData();
  }

  fetchRole() {
    this.service.findRoles().subscribe((data) => {
      this.roles = data;
      //this.updateEditCache();
    });
  }

  fetchData() {

    this.service.findAll().subscribe((data) => {
      this.rapports = data;
      this.i=0;
      this.rapports.forEach((item) =>{
        this.role.push(item.roles[this.i]);
        ++this.i;
      });
      //this.saveUser();
      this.updateEditCache();
    });
    this.rapports.sort()
  }

   findRole(name: String, username: String): Boolean {
      this.i=0;
     this.isrole= false;
     this.rapports.forEach((item) =>{
       if (item.username==username) {
         this.isrole= false;
         item.roles.forEach((item2) => {
           if (item2.name == name ) {
             this.isrole = true;
           }
         });
       }
      });

    return this.isrole;
  }
  onCheckboxChange(e: any, username: String, r: Role) {
      this.updateEditCachetrue(r,username,e.target.checked);
  }



  get pagination(): NumberedPagination {
    const { numPage, maxPages, rulerLength } = this;
    const pages = ruler(numPage, maxPages, rulerLength);
    return { numPage, maxPages, pages } as NumberedPagination;
  }

  addErrorToast() {
    this.errorVisible = true;
  }
  addSuccessToast() {
    this.sucessVisible = true;
  }
  showErrorModification() {
    this.errorModification = true;
  }
  showSuccessModification() {
    this.sucessModification = true;
  }

  public visible = false;

  toggleLiveDemo() {
    this.visible = !this.visible;
  }

  confirmeDelete(id:string) {
    this.usernameDelete=id;
    this.visible = !this.visible;
  }

  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

}
