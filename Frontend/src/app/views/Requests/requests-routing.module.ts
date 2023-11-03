import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RequestsListComponent } from './list/requests.list.component';
import { RequestDetailsComponent } from './details/request.details.component';
import { RequestCreateComponent } from './create/request.create.component';

const routes: Routes = [
  {
    path: '',
    component: RequestsListComponent,
    data: {
      title: $localize`Gestion des demandes`,
    },
  },
  {
    path: 'details/:id',
    component: RequestDetailsComponent,
    data: {
      title: $localize`Details du demande`,
    },
  },
  {
    path: 'add',
    component: RequestCreateComponent,
    data: {
      title: $localize`ajout du demande`,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RequestsRoutingModule {}
