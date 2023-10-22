import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/auth.service';
import { LoginRequest } from 'src/app/shared/loginRequest';
import {Subscription} from "rxjs";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  detour: Subscription | undefined;
  errorModification: boolean=false;
  user: LoginRequest = {
    username: '',
    password: '',
  };
  constructor(
    public fb: FormBuilder,
    public authService: AuthService,
    public router: Router
  ) {

  }

  login() {

    //this.detour= this.authService.signIn(this.user);
       this.authService.http
        .post<any>(`${environment.apiURL}/api/auth/signin`, this.user)
        .subscribe((res: any) => {
            localStorage.setItem('currentUser', JSON.stringify(res));
            this.authService.currentUserSubject.next(res);
            // this.getUserProfile(res._id).subscribe((res) => {
            this.router.navigate(['/']);
            // });
          },
          (error) => {
             this.showErrorModification(true)
          });

   // console.log("Louly",this.detour);
  }

  public showErrorModification(flag: boolean) {
    this.errorModification = flag;
  }
}
