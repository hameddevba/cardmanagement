import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import {
  AvatarModule,
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  FormModule,
  GridModule,
  HeaderModule,
  ModalModule,
  NavModule,
  PaginationModule,
  ProgressModule,
  TableModule,
  TabsModule,
  ToastModule,
} from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';
import { ChartjsModule } from '@coreui/angular-chartjs';

import { RequestsListComponent } from './list/requests.list.component';
import { RequestDetailsComponent } from './details/request.details.component';
import { RequestCreateComponent } from './create/request.create.component';

import { HttpClientModule } from '@angular/common/http';
import { RequestsRoutingModule } from './requests-routing.module';
import { RequestsService } from '../../service/requests.service';

@NgModule({
  imports: [
    RequestsRoutingModule,
    CardModule,
    NavModule,
    IconModule,
    TabsModule,
    CommonModule,
    GridModule,
    ProgressModule,
    ReactiveFormsModule,
    ButtonModule,
    FormModule,
    FormsModule,
    ButtonModule,
    HeaderModule,
    ButtonGroupModule,
    ChartjsModule,
    AvatarModule,
    TableModule,
    ToastModule,
    HttpClientModule,
    PaginationModule,
    ModalModule,
  ],
  declarations: [
    RequestsListComponent,
    RequestDetailsComponent,
    RequestCreateComponent,
  ],
  providers: [RequestsService],
})
export class RequestsModule {}
