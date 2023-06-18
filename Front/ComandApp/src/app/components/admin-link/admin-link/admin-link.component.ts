import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-admin-link',
  templateUrl: './admin-link.component.html',
  styleUrls: ['./admin-link.component.scss']
})
export class AdminLinkComponent {
  isAdmin = false;;

  constructor(private authService: AuthService) {
    this.isAdmin = this.authService.isAdmin();
  }
}
