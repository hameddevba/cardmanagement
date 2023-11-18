import { Component, OnInit } from '@angular/core';

import { RequestsService } from 'src/app/service/requests.service';
import {
  Request,
  RequestValidation,
  emptyRequestValidation,
} from 'src/app/model/Request';
import { CardType } from 'src/app/model/CardType';
import { CardTypeService } from 'src/app/service/card.type.service';
import { Account } from 'src/app/model/Account';
import { AccountService } from 'src/app/service/account.service';
import {
  isValid,
  validateRequestForSave,
} from 'src/app/utils/validation-utils';
import { Observable } from 'rxjs';

export enum Colors {
  '' = '',
  primary = 'primary',
  secondary = 'secondary',
  success = 'success',
  info = 'info',
  warning = 'warning',
  danger = 'danger',
  dark = 'dark',
  light = 'light',
}
@Component({
  templateUrl: 'request.create.component.html',
  styleUrls: ['request.create.component.scss'],
})
export class RequestCreateComponent implements OnInit {
  selectAccount(event: any) {
    console.log(event);
  }
  request: Request = {} as Request;
  renew = false;
  modalOpen = false;
  suggestions: Account[] = [];
  searchText = '';
  months = Array.from(Array(12).keys()).map((x) => new Date(2000, x, 2));
  cardTypes: CardType[] = [];
  validation: RequestValidation = emptyRequestValidation();
  constructor(
    private service: RequestsService,
    private cardTypeService: CardTypeService,
    private accountService: AccountService,
  ) {}

  ngOnInit(): void {
    this.fetchData('');
    this.cardTypeService.findAll().subscribe((data) => {
      this.cardTypes = data;
    });
  }
  save() {
    this.validation = validateRequestForSave(this.request, this.renew);

    if (isValid(this.validation)) return;
    this.service.save(this.request).subscribe({
      next: (data) => {
        this.request = data;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
  // handleInputChange() {
  //   if (!this.searchText || this.searchText.length < 3) {
  //     this.suggestions = [];
  //     return;
  //   }
  //   this.accountService.find(this.searchText, 10).subscribe(
  //     (data) => {
  //       this.suggestions = data;
  //     },
  //     (error) => {
  //       console.log(error);
  //     },
  //   );
  // }
  addAccount(id?: number) {
    console.log(id);
  }
  openModal() {
    this.modalOpen = true;
    this.searchText = '';
  }

  handleModalChange(event: boolean) {
    this.modalOpen = event;
    this.searchText = '';
  }
  fetchData(seachWord: string): Observable<Account[]> {
    console.log(
      '🚀 ~ file: request.create.component.ts:99 ~ RequestCreateComponent ~ fetchData ~ this.accountService:',
      this,
    );
    return this.accountService.find(seachWord ?? '');
  }
  formatData(account: Account): string {
    return account.accountNumber;
  }
}
