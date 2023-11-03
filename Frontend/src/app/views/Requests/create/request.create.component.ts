import { Component, OnInit } from '@angular/core';

import { AuthService } from 'src/app/shared/auth.service';
import { RequestsService } from 'src/app/service/requests.service';
import { Request } from 'src/app/model/Request';
import { ActivatedRoute } from '@angular/router';

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
  request?: Request;
  constructor(
    private service: RequestsService,
    private authService: AuthService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    console.log('create');
  }
}
