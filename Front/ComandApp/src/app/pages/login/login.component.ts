import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  displayError: any = { email: false, password: false };
  invalid = false;

  constructor(private authService: AuthService) {}

  onSubmit(): void {
    this.displayErrors();
    if (this.username?.valid && this.password?.valid) {
      this.authService.login(this.username.value, this.password.value).subscribe({
        error: () => {
          this.displayErrors(false);
          this.invalid = true;
        },
      });
    }
  }

  get username(): AbstractControl | null {
    return this.loginForm.get('username');
  }

  get password(): AbstractControl | null {
    return this.loginForm.get('password');
  }

  displayErrors(display = true): void {
    Object.keys(this.displayError).forEach(
      (key: string) => (this.displayError[key] = display)
    );
  }
}
