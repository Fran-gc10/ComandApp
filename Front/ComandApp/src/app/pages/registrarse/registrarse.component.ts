import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/services/alert/alert.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.scss'],
})
export class RegistrarseComponent {
  constructor(
    private userService: UserService,
    private router: Router,
    private alert: AlertService
  ) {}
  signUpForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$'),
    ]),
    password: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
  });
  displayErrors = false;

  onSubmit() {
    this.displayErrors = true;

    if (
      this.signUpForm?.valid &&
      this.email &&
      this.password &&
      this.username &&
      this.name
    )
      this.userService
        .createUser({
          email: this.email.value,
          password: this.password.value,
          nombre: this.name.value,
          nombreUsuario: this.username.value,
        })
        .subscribe({
          next: () => {
            this.alert
              .success('Tu cuenta se ha creado')
              .subscribe(() => this.router.navigateByUrl('login'));
          },
          error: (e) => {
            console.log(`eror`, e);
            this.alert.error(
              'El email utilizado se encuentra en uso, prueba con otro.'
            );
          },
        });
  }

  get email(): AbstractControl | null {
    return this.signUpForm.get('email');
  }

  get password(): AbstractControl | null {
    return this.signUpForm.get('password');
  }

  get username(): AbstractControl | null {
    return this.signUpForm.get('username');
  }

  get name(): AbstractControl | null {
    return this.signUpForm.get('name');
  }
}
